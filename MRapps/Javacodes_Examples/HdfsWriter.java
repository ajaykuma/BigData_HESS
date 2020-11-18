package org.example.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.ToolRunner;

public class HdfsWriter extends Configured implements Tool {
    
    public static final String FS_PARAM_NAME = "fs.defaultFS";
    
    public int run(String[] args) throws Exception {
        
        if (args.length < 2) {
            System.err.println("HdfsWriter [local input path] [hdfs output path]");
            return 1;
        }
        
        String localInputPath = args[0];
        Path outputPath = new Path(args[1]);

        Configuration conf = getConf();
        System.out.println("configured filesystem = " + conf.get(FS_PARAM_NAME));
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(outputPath)) {
            System.err.println("output path exists");
            return 1;
        }
        OutputStream os = fs.create(outputPath);
        InputStream is = new BufferedInputStream(new FileInputStream(localInputPath));
        IOUtils.copyBytes(is, os, conf);
        return 0;
    }
    
    public static void main( String[] args ) throws Exception {
        int returnCode = ToolRunner.run(new HdfsWriter(), args);
        System.exit(returnCode);
    }
}

