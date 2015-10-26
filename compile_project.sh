#!/bin/bash

mkdir ${PROJECT_BUILD_DIRECTORY}

cd ${PROJECT_SOURCE_DIRECTORY}
pwd
find -name "*.java" | xargs ${HADOOP_PREFIX}/bin/yarn com.sun.tools.javac.Main -Xlint -d ${PROJECT_BUILD_DIRECTORY}
cd ${PROJECT_BUILD_DIRECTORY}
pwd
find -name "*.class" | xargs jar cf ${PROJECT_NAME}.jar