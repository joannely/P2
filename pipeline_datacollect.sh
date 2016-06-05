#args
# $1 = executable to run
# $2 = number of files to sort
# $3 = number of ints in each sample

if [ $1 = "quicksort" ]; then
  echo "Sample Number,Language,Time,Number of Partitioning Stages,Number of Exchanges,Number of Compares" > quicksort.csv

  for ((i = 1; i <= $2 ; ++i )); do 

    for ((j = 1; j <= $3 ; ++j )); 
      do echo "$((RANDOM%10))" ; 
    done > data$i.txt

    echo -n "$i,C++," >> quicksort.csv
    TIMEFORMAT=%R;
    myTime=$(time(./"$quicksort" data$i.txt 10) 2>&1)
    echo -n $myTime | sed 's/.*y //g' | tr -d '\n' >> quicksort.csv
    echo -n "," >> quicksort.csv

    g++ -o quicksort_stats quicksort_stats.cpp

    echo -n $(./quicksort_stats data$i.txt 10) | sed 's/.*Stages: //g' | sed 's/ Exch.*//g' | tr -d '\n'>> quicksort.csv
    echo -n "," >> quicksort.csv
    echo -n $(./quicksort_stats data$i.txt 10) | sed 's/.*Exchanges: //g' | sed 's/ Compa.*//g' | tr -d '\n' >> quicksort.csv
    echo -n "," >> quicksort.csv
    echo $(./quicksort_stats data$i.txt 10) | sed 's/.*Compares: //g' >> quicksort.csv
  done  
fi

rm -rf data*.txt