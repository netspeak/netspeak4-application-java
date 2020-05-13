# Netspeak 4 application Java

[![Actions Status](https://github.com/netspeak/netspeak4-application-java/workflows/Java%20CI%20with%20Gradle/badge.svg)](https://github.com/netspeak/netspeak4-application-java/actions)

This is the Java interface of the Netspeak 4 core library.

All Java project which use Netspeak 4 need this library. It contains the Java classes of the Netspeak 4 protobuf messages and a JNI to create to Netspeak instances directly in Java.

(See [`netspeak4-application-cpp`](https://github.com/netspeak/netspeak4-application-cpp) for the protobuf message files.)

This is a library. It does not contain a `main` method.

## Getting started

We use [Gradle](https://gradle.org/install/) as our build system. To build this project run `./gradlew build`. Most Java IDEs support Gradle either directly or via some plugin.

## Build

```bash
./gradlew build
```

The compiled JAR will be located at `build/libs/`

### Library dependencies

This Java project is a language binding for the C++ project [`netspeak4-application-cpp`](https://github.com/netspeak/netspeak4-application-cpp) whose
implementation comes in form of a shared library (`.so` file). The present Java
application loads the library at runtime and invokes their native routines via
the Java Native Interface (JNI) method. Precompiled libraries for Ubuntu 10.04
and 12.04 can be found in the lib sub-directory of this project. The native
library itself has some dependencies you need to install as well.

__Note:__ You only need to worry about the JNI if you use it. If you don't use it then it won't cause any errors.

#### Load native library

Set "-Djava.library.path=/usr/lib" as VM argument (assuming that the `libnetspeak4.so` lives there).


---

## Contributors

Michael Schmidt (2018 - 2020)

Martin Trenkmann (2008 - 2013)

Martin Potthast (2008 - 2020)

Benno Stein (2008 - 2020)

