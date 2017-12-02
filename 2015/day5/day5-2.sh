#!/bin/bash

echo "Starting first grep"
egrep "(..).*\1" $1 > r1
echo "First expression matched:"
cat r1

echo "Starting second grep"
egrep "(.).\1" r1 > r2
echo "Second expression matched:"
cat r2

echo "Answer: " `wc -l r2`
