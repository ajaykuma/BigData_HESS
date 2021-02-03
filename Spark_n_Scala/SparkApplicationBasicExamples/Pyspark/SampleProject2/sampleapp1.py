from pyspark import SparkConf, SparkContext
from pyspark.streaming import StreamingContext
sc = SparkContext(master="local[2]",appName="strmAppl")
ssc = StreamingContext(sc,10)
ssc.checkpoint('testtemp')

streamrdd = ssc.socketTextStream("127.0.0.1",2222)
wordcounts = streamrdd.flatMap(lambda l: l.split(" ")).map(lambda word: (word,1)) \
              .reduceByKey(lambda a,b: a+b)
wordcounts.pprint()

ssc.start()
ssc.awaitTermination()
