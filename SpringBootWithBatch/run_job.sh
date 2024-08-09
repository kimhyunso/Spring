CURRENT_DATE=`date +%Y/%m/%d`
LESSON=$(basename $PWD)
./gradlew clean build -x test
sleep 3
java -jar ./build/libs/linkedIn_batch-*.*.*-SNAPSHOT.jar --job.name=job --spring.config.location=file:./src/main/resources/application.yml "lesson=$LESSON"
read;

