#!/bin/bash

mkdir ${BUILD_DIRECTORY}

cd ${SOURCE_DIRECTORY}
pwd
find -name "*.java" | xargs ${HADOOP_PREFIX}/bin/yarn com.sun.tools.javac.Main -Xlint -d ${BUILD_DIRECTORY}
cd ${BUILD_DIRECTORY}
pwd
find -name "*.class" | xargs jar cf ${PROJECT_NAME}.jar