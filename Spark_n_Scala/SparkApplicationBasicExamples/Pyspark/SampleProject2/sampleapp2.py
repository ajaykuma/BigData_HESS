from pyspark import SparkConf, SparkContext,StorageLevel
from pyspark.streaming import StreamingContext
sc = SparkContext(master="local[2]",appName="strmAppl")
ssc = StreamingContext(sc,10)
ssc.checkpoint('testtemp')

streamrdd = ssc.socketTextStream("127.0.0.1",2222, StorageLevel.MEMORY_ONLY)
wordcounts = streamrdd.flatMap(lambda l: l.split(" ")).map(lambda word: (word,1)) \
              .reduceByKeyAndWindow(lambda a,b: a+b,30,10)
wordcounts.pprint()

ssc.start()
ssc.awaitTermination()