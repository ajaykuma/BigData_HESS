use mydbmysq;
create table avrotbl1
row format serde 'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
stored as inputformat
'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
outputformat
'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
TBLproperties
('avro.schema.url'='hdfs://u1:9000/user/avro/schemas/schema1.avsc');

