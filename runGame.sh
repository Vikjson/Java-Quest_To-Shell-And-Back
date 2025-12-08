#!/bin/bash
dots="..."

echo "Are you sure you want to go to '$'hell?"
sleep 0.5
for ((i=0; i<${#dots}; i++)); do
    printf "${dots:$i:1}"
    sleep 0.4
done

echo
sleep 0.2
echo "Ok, here we go!"

if mvn clean package; then
    echo "Build succeeded!"
else
    echo "Build failed!"
    exit 1
fi
java -jar target/JavaQuest-To-Shell-And-Back-1.0-SNAPSHOT.jar