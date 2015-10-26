#!/bin/bash

ssh ${NAME_NODE} <<SSHEOF
export HADOOP_PREFIX=${HADOOP_PREFIX}
cd ${HADOOP_PREFIX}
sbin/start-dfs.sh
SSHEOF 
