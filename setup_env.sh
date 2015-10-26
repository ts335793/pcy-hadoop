#!/bin/bash

export DOWNLOADS=~/Downloads
export HADOOP_PREFIX=~/PDD/hadoop-2.6.0
# export HADOOP_PREFIX=/tmp/pdd

export NAME_NODE=localhost
# export NAME_NODE=green12
export DATA_NODES=localhost
# export DATA_NODES=(green12 green11 green13)

export PROJECTS_DIRECTORY=~/PDD/

export PROJECT_NAME=PCY
export PROJECT_PREFIX=${PROJECTS_DIRECTORY}/${PROJECT_NAME}
export PROJECT_SOURCE_DIRECTORY=${PROJECT_PREFIX}/src
export PROJECT_BUILD_DIRECTORY=${PROJECT_PREFIX}/build
export PROJECT_MAIN_CLASS=pl.edu.mimuw.students.ts335793.Main

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk/bin/java
# export JAVA_HOME=/usr/java/default
export PATH=/usr/lib/jvm/java-8-openjdk/bin:$PATH
# export PATH=$JAVA_HOME/bin:$PATH
export HADOOP_CLASSPATH=/usr/lib/jvm/java-8-openjdk/lib/tools.jar
# export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar