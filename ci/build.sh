#!/usr/bin/env bash

set -e -x

git clone starterkit resource-app

cd resource-app

#mvn clean compile
#mvn package

#mvn install
mvn package

#java de/bitkings/nitram509/ConcourseJavaMavenTestPrjApplication
#mvn test
echo "done testing"
