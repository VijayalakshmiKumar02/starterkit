#!/usr/bin/env bash

set -e -x
echo "making directory"
mkdir resource-app1
echo "changing directory"
#cd resource-app1
echo "git clone"
git clone starterkit resource-app1
cd resource-app1
mvn clean compile
#mvn package

#mvn install
mvn package
find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
