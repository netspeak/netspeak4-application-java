#!/bin/sh

# You need to have project netspeak4-application-cpp to be checked out.
# Note: The protoc option [ -IPATH | --proto_path=PATH ] does not work.
# You have to cd to the source directory (PATH) temporarily.

cd "$(dirname "$0")"
scriptPath="$( pwd -P )"

# check protoc version
protocVersion=$(protoc --version)
if [ "$protocVersion" != "libprotoc 2.6.1" ]; then
    echo "protoc v2.6.1 is required! Your version is $protocVersion";
    exit 1;
fi

cd ../../../netspeak4-application-cpp/conf/
protoc --java_out=../../netspeak4-application-java/src/main/java/ NetspeakMessages.proto


cd "$scriptPath"
java -jar lib_protostuff-compiler-1.0.0-jarjar.jar netspeak-java-v2protoc-schema.properties
