package org.example.HCodes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class URLCountR extends Reducer<Text, IntWritable, Text, IntWritable> {

    private static final Logger LOG = Logger.getLogger(URLCountR.class.getName());

    private IntWritable result = new IntWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context
    ) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        LOG.log(Level.INFO, "REDUCER_VALUE: ".concat(result.toString()));
        context.write(key, result);
    }
}
