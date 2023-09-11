REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.server.AgvManager\target\base.app.server.AgvManager-1.4.0-SNAPSHOT.jar;base.app.server.AgvManager\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% srv.TcpSrvAgvManager