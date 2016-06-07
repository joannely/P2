#!/bin/bash

# $1 indicates number of tests, $2 indicates number of integers in each test
# in this project (122B_P2), $1 should be 100, $2 1000
if [[ "$1" = *[[:digit:]]* ]]  && [[ "$2" = *[[:digit:]]* ]]; then
	javac *.java #compile all .java files
	RANDOM=$$
	for n in $(seq 1 $1); do
		#creates text file with $2 integers ranging from 1-10^6
		gshuf -i 1-1000000 -n $2 >> test.txt
		
		k=$(($RANDOM % ($2+1) +1))

		#run the sort, save result in variable x/y/z
		x=$(java quicksortKth < test.txt $k)
		y=$(java quickselect < test.txt $k)
		z=$(java deterministicselect < test.txt $k)

		#if all results match, delete test.txt and proceed to next round
		if [ $x == $y ] && [ $y == $z ]; then
			rm test.txt
		else
			echo Failed test. k value = $k
			cat test.txt
			echo quicksort result: 			$x
			echo quickselect result: 		$y
			echo deterministicselect result: 	$z
			exit 1
		fi
	done
	echo All Tests Passed.

else
	echo "Please supply two integer arguments"
fi

