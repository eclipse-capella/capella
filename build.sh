#!/bin/sh
set -e

export MAVEN_OPTS="-Xms512M -Xmx2048M -XX:+CMSClassUnloadingEnabled"
mvn clean install 2>&1| tee mvn.out
