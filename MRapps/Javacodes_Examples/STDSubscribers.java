package org.example.hadoopcodes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * This is map-reduce program to solve following problem A Telecom company keeps
 * records for its subscribers called as Call Data Records. CDR is stored in
 * following format
 * FromPhoneNumber|ToPhoneNumber|CallStartTime|CallEndTime|STDFlag Now we have
 * to write a map reduce code to find out all phone numbers who are making more
 * than 60 mins of STD calls. Here if STD Flag is 1 that means it was as STD
 * Call.
 * 
 * 
 * 
 * 
 */
public class STDSubscribers {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		if (args.length != 2) {
			System.err.println("Usage: stdsubscriber <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "STD Subscribers");
		job.setJarByClass(STDSubscribers.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(SumReducer.class);
		job.setReducerClass(SumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public static class SumReducer extends
			Reducer<Text, LongWritable, Text, LongWritable> {
		private LongWritable result = new LongWritable();

		public void reduce(Text key, Iterable<LongWritable> values,
				Reducer<Text, LongWritable, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			long sum = 0;
			for (LongWritable val : values) {
				sum += val.get();
			}
			this.result.set(sum);
			if (sum >= 60) {
				context.write(key, this.result);
			}

		}
	}

	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, LongWritable> {

		Text phoneNumber = new Text();
		LongWritable durationInMinutes = new LongWritable();

		public void map(Object key, Text value,
				Mapper<Object, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			String[] parts = value.toString().split("[|]");
			if (parts[CDRConstants.STDFlag].equalsIgnoreCase("1")) {

				phoneNumber.set(parts[CDRConstants.fromPhoneNumber]);
				String callEndTime = parts[CDRConstants.callEndTime];
				String callStartTime = parts[CDRConstants.callStartTime];
				long duration = toMillis(callEndTime) - toMillis(callStartTime);
				durationInMinutes.set(duration / (1000 * 60));
				context.write(phoneNumber, durationInMinutes);
			}
		}

		private long toMillis(String date) {

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date dateFrm = null;
			try {
				dateFrm = format.parse(date);

			} catch (ParseException e) {

				e.printStackTrace();
			}
			return dateFrm.getTime();
		}
	}

}