#!/bin/bash
bin/flume-ng agent --conf-file conf/flume-conf.properties Dflume.root.logger=DEBUG,console -n b1
