CURRENT_DATE=`date +%Y/%m/%d`
LESSON=$(basename $PWD)
./gradlew clean build -x test
sleep 3
java -jar ./build/libs/linkedIn_batch-*.*.*-SNAPSHOT.jar type=$1 "run.date(date)=$CURRENT_DATE" "lesson=$LESSON"
read;

