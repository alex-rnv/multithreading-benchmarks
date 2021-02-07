#!/usr/bin/env bash

cd "$(dirname "$0")" || exit

j8=$(/usr/libexec/java_home -v 1.8.0_275)
j15=$(/usr/libexec/java_home -v 15.0.1)

jv=${1:-j8}
jpath=${!jv}
echo "jpath=$jpath"

jversion=1.8

if [ "$jv" = "j15" ]
then
  jversion=15
fi
echo "jversion=$jversion"
mvnparams="-Pmaven.compiler.source=$jversion -Pmaven.compiler.target=$jversion"
echo "mvnparams=$mvnparams"

rm -rf ../target

JAVA_HOME=$jpath mvn clean package verify "$mvnparams"

test=$2
JAVA_HOME=$jpath java -jar -ea target/benchmarks.jar "$test" -f 1 -wi 1 -i 2

cd -
