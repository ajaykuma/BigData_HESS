#!/bin/bash
bin/flume-ng agent --conf-file flume-conf.properties Dflume.root.logger=DEBUG,console -n b1
