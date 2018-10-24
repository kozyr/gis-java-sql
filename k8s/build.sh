set -e

cwd=$(pwd)
cd ..
gradle clean
gradle bootJar
cp build/libs/gis-java-sql-0.0.1.jar $cwd/gis-java.jar
