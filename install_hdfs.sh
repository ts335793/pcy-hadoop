#!/bin/bash

# cannot export array :(
. setup_env.sh

wget -nc -P ${DOWNLOAD_DIRECTORY} ftp://ftp.task.gda.pl/pub/www/apache/dist/hadoop/core/hadoop-2.6.0/hadoop-2.6.0.tar.gz
tar -xvf ${DOWNLOAD_DIRECTORY}/hadoop-2.6.0.tar.gz -C ${DOWNLOAD_DIRECTORY}

for node in ${NAME_NODE} ${DATA_NODES[@]}
do
ssh ${node} <<SSHEOF
mkdir ${HADOOP_PREFIX}
cp ${DOWNLOAD_DIRECTORY}/hadoop-2.6.0/* ${HADOOP_PREFIX} -r
sed -i -e "s|^export JAVA_HOME=\\\${JAVA_HOME}|export JAVA_HOME=\${JAVA_HOME}|g" ${HADOOP_PREFIX}/etc/hadoop/hadoop-env.sh
cat <<EOF > ${HADOOP_PREFIX}/etc/hadoop/core-site.xml
<configuration>
  <property>
    <name>fs.defaultFS</name>
    <value>hdfs://${NAME_NODE}:9000</value>
  </property>
</configuration>
EOF
cat <<EOF > ${HADOOP_PREFIX}/etc/hadoop/hdfs-site.xml
<configuration>
  <property>
    <name>dfs.replication</name>
    <value>1</value>
  </property>
  <property>
    <name>dfs.datanode.data.dir</name>
    <value>${HADOOP_PREFIX}/data</value>
  </property>
</configuration>
EOF
printf "%s\n" "${DATA_NODES[@]}" > ${HADOOP_PREFIX}/etc/hadoop/slaves
echo ${NAME_NODE} > ${HADOOP_PREFIX}/etc/hadoop/masters
SSHEOF
done

ssh ${NAME_NODE} <<SSHEOF
${HADOOP_PREFIX}/bin/hdfs namenode -format
SSHEOF