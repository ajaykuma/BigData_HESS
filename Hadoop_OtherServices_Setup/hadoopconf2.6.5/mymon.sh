#!/bin/bash
echo "monitoring my cluster"
date
hdfs dfsadmin -report -live > dfsadminrep.txt
cat dfsadminrep.txt | grep "Live datanodes"  --color
cat dfsadminrep.txt | grep "Name:"
cat dfsadminrep.txt | grep "Last contact"
rm -rf dfsadminrep.txt
hdfs dfsadmin -report -dead > dfsadminrep.txt
cat dfsadminrep.txt | grep "Dead datanodes" --color
cat dfsadminrep.txt | grep "Name:"
rm -rf dfsadminrep.txt
date
hdfs fsck / -files -blocks > fsckrep.txt
cat fsckrep.txt | grep "Over-replicated blocks:"
cat fsckrep.txt | grep "Under-replicated blocks" --color
cat fsckrep.txt | grep "Mis-replicated blocks"
rm -rf fsckrep.txt
echo "======================"
echo "checking again"
echo "======================"
