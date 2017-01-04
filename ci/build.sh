#!/usr/bin/env bash

set -e -x
echo "making directory"
mkdir resource-app
echo "changing directory"
cd resource-app
echo "git clone"
git clone starterkit resource-app

#mvn clean compile
#mvn package

mvn install
mvn package
find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
