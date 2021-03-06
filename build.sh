#!/usr/bin/env bash
if [ $# -eq 0 ] ; then
  mvn -Dspring.profiles.active=test clean package -U
else
  if [ $1 = "-q" ] ; then
    mvn clean
    mvn clean install -DskipTests=true
    mvn -Dmaven.test.skip=true clean package -U
  else
    echo "build.sh [-q]"
    exit 0
  fi
fi
if [ $? -ne 0 ] ; then
  echo "mvn package error"
  exit -1
fi