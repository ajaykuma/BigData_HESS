package main.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HiveClient {
/*
* Before running this example we should start the thrift server
*/
    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {
            // TODO Auto-generated method stub
try {
     Class.forName(driverName);
} catch (ClassNotFoundException e){
     e.printStackTrace();
     System.exit(1);

}
Connection con = DriverManager.getConnection(
             "jdbc:hive2://u1:10000/mydbmysq", "hdu", "abc1234");
Statement stmt = con.createStatement();

    String tableName = "newtbl";
    stmt.executeQuery("drop table " + tableName);
ResultSet res = stmt.executeQuery("create table" + tableName +
                    "(id int, name string, dept string)");
    //show tables
    String sql = "show tables '" + tableName + "'";
    System.out.println("Running: " + sql);
    res = stmt.executeQuery(sql);
            if (res.next()) {
                    System.out.println(res.getString(1));
                    }}}