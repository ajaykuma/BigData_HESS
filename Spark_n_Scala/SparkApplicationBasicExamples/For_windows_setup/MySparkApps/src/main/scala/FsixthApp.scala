package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object FsixthApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local").set("spark.debug.maxToStringFields","100")
//val conf = new SparkConf().setAppName("HelloSpark").set("spark.debug.maxToStringFields","100")
val sc = new SparkContext(conf)
val fileInfoFile_with_Marked_Data = args(0)
val x = sc.textFile(fileInfoFile_with_Marked_Data)
val sample1 = x
val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairx = newDS.map(x => (x.split(",")(0),x))
val fileInfoFile_without_Marked_Data = args(1)

val y = sc.textFile(fileInfoFile_without_Marked_Data)
val sample2 = y
val newDS2 = sample2.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairy = newDS2.map(x => (x.split(",")(0),x))

val pairz = pairx.subtract(pairy)

print("Metadata in this file is as follows")
pairy.take(12).foreach(println)

val outputPath = args(2)
pairz.sortByKey(true).saveAsTextFile(outputPath)

val DataNeeded = "pedestrian"
val x1 = sc.textFile(outputPath + "\\" + "part-00000").filter(line => line.contains(DataNeeded))

val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()
val df = spark.read.format("csv").option("inferSchema","true").load(outputPath + "\\" + "part-00000")
println("fileinfo data in dataframe")
df.show(10)

println("extracting column headings as per metadata in y")
val colHeadingsRow = pairz.values.filter(line => line.startsWith("22,"))
val countCol = colHeadingsRow.flatMap(line => (line.split(",")))
val n = countCol.count().toInt
val colHeadings = colHeadingsRow.flatMap(line => (line.split(","))).take(n).mkString(", ").foreach(print)

x1.saveAsTextFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\SpecificData" + "-" + DataNeeded )
val schemaString = "frameNumber,streamName,refId,objectType,height,direction,movement,occlusion,headOccluded,feetOccluded,overlapped,unsharp,strangePose,crossing,accessory,topLeftX,topLeftY,topRightX,topRightY,bottomRightX,bottomRightY,bottomLeftX,bottomLeftY,box3DGroundLength,box3DGroundWidth,box3DGroundCenterX,box3DGroundCenterXSigma,box3DGroundCenterY,box3DGroundCenterYSigma,box3DClosestPointX,box3DClosestPointY,box3DOrientationAngle,box3DOrientationAngleSigma,box3DHeight,box3DRelVelocityX,box3DRelVelocityXSigma,box3DRelVelocityY,box3DRelVelocityYSigma,box3DDataSource,box3DLidarInterpolationAge,box3DClassificationQuality,lidarDistanceX,lidarDistanceY,lidarVelocityX,lidarVelocityY,isInvalid,isStatic,ObjectId,Ibeo2MarkingsVersion,IdcOdExtractorVersion,clusterID,faceVisible,leftBorderVisibility,rightBorderVisibility"
val fields = schemaString.split(",").map(fieldName => StructField(fieldName, StringType, nullable = false))
val schema = StructType(fields)
val rowRDD = x1.map(_.split(",")).map(attributes => Row(attributes(2), attributes(3),attributes(4), attributes(5),attributes(6), 
    attributes(7),attributes(8), attributes(9),attributes(10), attributes(11),attributes(12), attributes(13),attributes(14), attributes(15),attributes(16), 
    attributes(17),attributes(18), attributes(19),attributes(20), attributes(21),
    attributes(22), attributes(23),attributes(24), attributes(25),attributes(26), attributes(27),attributes(28), attributes(29),attributes(30), 
    attributes(31),attributes(32), attributes(33),attributes(34), attributes(35),attributes(36), attributes(37),attributes(38), attributes(39),
    attributes(40), attributes(41),attributes(42), attributes(43),attributes(44), attributes(45),attributes(46), attributes(47),attributes(48),
    attributes(49),attributes(50), attributes(51),attributes(52), attributes(53), attributes(54), attributes(55)))
val finalDF = spark.createDataFrame(rowRDD, schema)
finalDF.orderBy("framenumber").show(219)
finalDF.orderBy("framenumber").repartition(1).write.format("json").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\DataInJson" + "-" + DataNeeded)
//finalDF.write.format("csv").save("C:\\Users\\alay.singhal\\Desktop\\Codes\\DataInCSV" + "-" + DataNeeded)
sc.stop()
}