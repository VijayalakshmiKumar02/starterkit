#!/usr/bin/env bash

set -e -x
mkdir jarfolder
cd jarfolder
var=$(pwd)
echo "The current working directory $var."
echo "making directory"
echo "changing directory"
#cd resource-app1
echo "git clone"
git clone starterkit var
mvn install -DskipTests=true
#mvn package
#cp ./target/DeployProcessorSupportTool.jar home/vagrant/test
#mvn install
#mvn package
#find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
