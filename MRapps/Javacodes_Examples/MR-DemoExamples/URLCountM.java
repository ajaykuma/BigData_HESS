package org.example.HCodes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class URLCountM extends Mapper<Text, Text,Text, IntWritable> {
    private static final Logger LOG = Logger.getLogger(URLCountM.class.getName());
    
	public final IntWritable iw = new IntWritable();
	
        @Override
	public void map(Text key, Text value, Context context){
		
		try{
                    LOG.log(Level.INFO, "MAPPER_KEY: ".concat(key.toString()).concat(" MAPPER_VALUE: ".concat(value.toString())));
		context.write(key, new IntWritable(Integer.valueOf(value.toString())));
		}
		catch(NumberFormatException | IOException | InterruptedException e){
			LOG.log(Level.SEVERE, "ERROR: ".concat(e.toString()));
		}
	}
}