#!/bin/bash

echo "Starting first grep"
egrep "[aeiou].*[aeiou].*[aeiou]" $1 > r1
echo "First expression matched:"
cat r1

echo "Starting second grep"
egrep "(.)\1" r1 > r2
echo "Second expression matched:"
cat r2

echo "Starting third grep"
egrep -v "ab|cd|pq|xy" r2 > r3
echo "Third expression matched:"
cat r3

echo "Answer: " `wc -l r3`
