CURRENT_DATE=`date +%Y/%m/%d`
LESSON=$(basename $PWD)
./gradlew clean build -x test
sleep 3
java -jar ./build/libs/linkedIn_batch-*.*.*-SNAPSHOT.jar --spring.config.location=file:D:/Spring/SpringBootWithBatch/src/main/resources/application.yml "lesson=$LESSON"
read;

