package org.example.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

@SuppressWarnings("unused")
public class HbaseWrklis {

       public static void main(String[] args) throws IOException{

       // TODO Auto-generated method stub

                             //Create a hbaseAdmin
                             Configuration config = HBaseConfiguration.create();
                             @SuppressWarnings("resource")
                                 HBaseAdmin admin = new HBaseAdmin(config);
                             HTableDescriptor[] tableDescriptor = admin.listTables();
                             {
                             // printing all the table names.
                             for (int i=0; i<tableDescriptor.length;i++ ){
                                System.out.println(tableDescriptor[i].getNameAsString());
                             }}}}