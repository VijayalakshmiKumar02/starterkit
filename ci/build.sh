#!/usr/bin/env bash

set -e -x
#mkdir jarfolder
#cd jarfolder
#echo "The current working directory $var."
#echo "making directory"
#echo "changing directory"
#cd resource-app1
#echo "git clone"
git clone starterkit resource-app
cd resource-app
#mvn install sonar:sonar
#for entry in `ls $resource-app`; do
 #   echo $entry
#done
#echo "done"
mvn sonar:sonar -Dsonar.host.url=http://sonarqube.mvp2.cognizantone.org/about 

#var=$(pwd)
#echo "The current working directory $var."
#cd target
#var1=$(pwd)
#echo "The current working directory $var1."
#for entry in `ls $target`; do
 #   echo $entry
#done


#mvn package
#cp ./target/DeployProcessorSupportTool.jar home/vagrant/test
#mvn install
#mvn package
#find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
#echo "done testing"
