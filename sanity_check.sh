#!/bin/bash

# $1 indicates number of tests, $2 indicates number of integers in each test
if [[ "$1" = *[[:digit:]]* ]]  && [[ "$2" = *[[:digit:]]* ]]; then
	javac *.java #compile all .java files
	RANDOM=$$
	for n in $(seq 1 $1); do
		#creates text file with $2 integers ranging from 1-10^6
		shuf -i 1-1000000 -n $2 >> test.txt
		
		k=$(($RANDOM % ($2+1) +1))

		#run the sort, save result in variable x/y/z
		x=$(java quicksortKth < test.txt k)
		y=$(java quickselect < test.txt k)
		z=$(java deterministicselect < test.txt k)

		#############################################
		###### BELOW FROM P1, need to update
		if diff mergesort_failed_test_"$n".txt quicksort_failed_test_"$n".txt; then
			rm mergesort_failed_test_"$n".txt quicksort_failed_test_"$n".txt
		else
			((COUNTER++))
		fi
	done
	#display results
	if [ "$COUNTER" == 0 ]; then
		echo "All tests passed."
	else
		echo "$COUNTER tests failed."
	fi
	rm -rf test*.txt
else
	echo "Please supply two integer arguments"
fi

