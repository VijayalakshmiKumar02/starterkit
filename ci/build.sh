#!/usr/bin/env bash

set -e -x
vagrant ssh
mkdir home/vagrant/test
cd home/vagrant/test
var=$(pwd)
echo "The current working directory $var."
echo "making directory"
echo "changing directory"
#cd resource-app1
echo "git clone"
git clone starterkit resource-app
#cd resource-app1
mvn install -DskipTests=true
#mvn package
#cp ./target/DeployProcessorSupportTool.jar home/vagrant/test
#mvn install
#mvn package
#find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
