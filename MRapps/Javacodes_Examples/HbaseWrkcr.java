package org.example.hadoop;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;


import org.apache.hadoop.conf.Configuration;
public class HbaseWrkcr {

        public static void main(String[] args) throws IOException{
                // TODO Auto-generated method stub

              // Instantiating table descriptor class
              @SuppressWarnings("deprecation")
			HTableDescriptor descriptor = new
              HTableDescriptor("employeeH");

              // Adding column families to table descriptor
              descriptor.addFamily(new HColumnDescriptor("personal"));
              descriptor.addFamily(new HColumnDescriptor("professional"));

               {
              //Create a hbaseAdmin
              Configuration config = HBaseConfiguration.create();
              @SuppressWarnings("resource")
                HBaseAdmin admin = new HBaseAdmin(config);

              // Execute the table through admin
              admin.createTable(descriptor);
              System.out.println(" Table created ");
              }}}