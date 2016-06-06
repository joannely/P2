#!/bin/bash

#args
# $1 = executable to run
# $2 = number of files to sort
# $3 = number of ints in each sample
javac *.java

if [ $1 = "qselect" ]; then
  echo "Sample Number,Language,Time,Number of Partitioning Stages,Number of Exchanges,Number of Compares" > quickselect.csv

  for ((i = 1; i <= $2 ; ++i )); do 

    for ((j = 1; j <= $3 ; ++j )); 
      do echo "$((RANDOM%10))" ; 
    done > data$i.txt

    t="$( TIMEFORMAT=%R;time ( java quickselect < data"$i".txt 10 ) 2>&1 1>/dev/null )" 

    # record results of stats into variable s
    s=$(java quickselect_stats < data"$i".txt 10)
    # take only number values
    s=$(echo "$s" | grep -o '[0-9]\+')
    s=(${s[@]})
    p="${s[0]}" # first line holds number of partitioning stages
    x="${s[1]}" # second line holds number of exchanges
    c="${s[2]}" # third line holds number of compares
    # write into quickselect.csv
    echo "$i,Java,$t,$p,$x,$c" >> quickselect.csv
  done  
fi


if [ $1 = "dselect" ]; then
  echo "Sample Number,Language,Time,Number of Partitioning Stages,Number of Exchanges,Number of Compares" > quicksort.csv

  for ((i = 1; i <= $2 ; ++i )); do 

    for ((j = 1; j <= $3 ; ++j )); 
      do echo "$((RANDOM%10))" ; 
    done > data$i.txt

    t="$( TIMEFORMAT=%R;time ( java quickselect < data"$i".txt 10 ) 2>&1 1>/dev/null )" 

    # record results of stats into variable s
    s=$(java quickselect_stats < data"$i".txt 10)
    
    # take only number values
    s=$(echo "$s" | grep -o '[0-9]\+')
    s=(${s[@]})
    p="${s[0]}" # first line holds number of partitioning stages
    x="${s[1]}" # second line holds number of exchanges
    c="${s[2]}" # third line holds number of compares
    # write into quickselect.csv
    echo "$i,Java,$t,$p,$x,$c" >> quickselect.csv
  done  
fi


rm -rf data*.txt