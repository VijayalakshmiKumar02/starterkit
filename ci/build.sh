#!/usr/bin/env bash

set -e -x

mkdir resource-app

cd resource-app

git clone starterkit resource-app

#mvn clean compile
#mvn package

mvn install
mvn package
find .
#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
