#!/bin/bash

javac *.java
if [ "$1" == quicksort ] && [[ "$2" = *[[:digit:]]* ]] && [[ "$3" = *[[:digit:]]* ]]; then
	# create .csv file with headers specific to quicksort
	echo "Sample Number,Language,Time,Number of Partitioning Stages,Number of Exchanges,Number of Compares" > quicksort.csv
	RANDOM=$$
	for n in $(seq 1 $2); do
		# generate test file to sort
		for i in $(seq 1 $3); do
			echo $RANDOM >> test"$n".txt
		done
		# extract real time value from time command
		t="$( TIMEFORMAT=%R;time ( java quicksort_timed < test"$n".txt 10 ) 2>&1 1>/dev/null )" 
		# record results of stats into variable s
		s=$(java quicksort_stats < test"$n".txt 10)
		# take only number values
		s=$(echo "$s" | grep -o '[0-9]\+')
		s=(${s[@]})
		p="${s[0]}" # first line holds number of partitioning stages
		x="${s[1]}" # second line holds number of exchanges
		c="${s[2]}" # third line holds number of compares
		# write into quicksort.csv
		echo "$n,Java,$t,$p,$x,$c" >> quicksort.csv
	done
	# remove all test files gneerated
	rm -rf test*.txt	
elif [ "$1" == mergesort ] && [[ "$2" = *[[:digit:]]* ]] && [[ "$3" = *[[:digit:]]* ]]; then
	# similar steps for mergesort
	echo "Sample Number,Language,Time,Number of Recursive Calls,Number of Transitions,Number of Compares" > mergesort.csv
	RANDOM=$$
	for n in $(seq 1 $2); do
		for i in $(seq 1 $3); do
			echo $RANDOM >> test"$n".txt
		done
		
		t="$( TIMEFORMAT=%R;time ( java mergesort_timed < test"$n".txt ) 2>&1 1>/dev/null )" 
		s=$(java mergesort_stats < test"$n".txt)
		s=$(echo "$s" | grep -o '[0-9]\+')
		s=(${s[@]})
		r="${s[0]}"
		tr="${s[1]}"
		c="${s[2]}"
		echo "$n,Java,$t,$r,$tr,$c" >> mergesort.csv
	done
	rm -rf test*.txt	
else
	echo "Incorrect command line arguments"
fi  
