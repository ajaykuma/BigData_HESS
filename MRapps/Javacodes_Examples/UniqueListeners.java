package org.example.hadoopcodes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UniqueListeners {
	private enum COUNTERS {
		INVALID_RECORD_COUNT
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		if (args.length != 2) {
			System.err.println("Usage: uniquelisteners <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "Unique listeners per track");
		job.setJarByClass(UniqueListeners.class);
		job.setMapperClass(UniqueListenersMapper.class);
		job.setReducerClass(UniqueListenersReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		org.apache.hadoop.mapreduce.Counters counters = job.getCounters();
		System.out.println("No. of Invalid Records :"
				+ counters.findCounter(COUNTERS.INVALID_RECORD_COUNT)
						.getValue());
	}

	public static class UniqueListenersReducer extends
			Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

		public void reduce(
				IntWritable trackId,
				Iterable<IntWritable> userIds,
				Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {

			Set<Integer> userIdSet = new HashSet<Integer>();
			for (IntWritable userId : userIds) {
				userIdSet.add(userId.get());
			}
			IntWritable size = new IntWritable(userIdSet.size());
			context.write(trackId, size);
		}
	}

	public static class UniqueListenersMapper extends
			Mapper<Object, Text, IntWritable, IntWritable> {

		IntWritable trackId = new IntWritable();
		IntWritable userId = new IntWritable();

		public void map(Object key, Text value,
				Mapper<Object, Text, IntWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {

			String[] parts = value.toString().split("[|]");
			trackId.set(Integer.parseInt(parts[LastFMConstants.TRACK_ID]));
			userId.set(Integer.parseInt(parts[LastFMConstants.USER_ID]));

			if (parts.length == 5) {
				context.write(trackId, userId);
			} else {
				// add counter for invalid records
				context.getCounter(COUNTERS.INVALID_RECORD_COUNT).increment(1L);
			}

		}
	}
}