# Description
Sample project showing how to create a proxy API of the Athens Transport API.

# Prerequisites

Java 8 needs to be installed

# Run from source

`./gradlew run`

# Build fat-jar

`./gradlew build`

The fat-jar is now created in `proxy-srv/build/libs/proxy-srv`

# Design

 * All the calls to the actual Athens Transport API end up being made by a dedicated library
   This library can be used without the Proxy Server in any JVM or Android environment that Supports [Retrofit 2](http://square.github.io/retrofit/)
   The library is implemented in Pure Java with the only dependencies being the ones imported by Retrofit
 * The sample proxy server is a Kotlin / Spring Boot application that leverages the retrofit library in order to perform async calls
   This server could easily be changed to a Ratpack based server if higher throughput is needed (as of Spring 4.x, Spring does not leverage non-blocking IO and all async behavior is implemented in Threads)    