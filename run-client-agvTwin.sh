#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.server.AGVTwin\target\base.app.server.AGVTwin-1.4.0-SNAPSHOT.jar;base.app.server.AGVTwin\target\dependency\*;

#REM call the java VM, e.g,
java -cp $BASE_CP cli.TcpCliAGVTwin 127.0.0.1