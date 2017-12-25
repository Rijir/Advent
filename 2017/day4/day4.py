f = open("input.txt", "r")
cValid = 0

line = f.readline()
while line != '':
    words = line.split()

    wordSet = set()
    for word in words:
        wordChars = list(word)
        wordChars.sort()
        wordHash = 0
        for i in range(len(wordChars)):
            wordHash += ord(wordChars[i]) * 26**i

        wordSet.add(wordHash)

    if len(words) == len(wordSet):
        cValid += 1

    line = f.readline()

print(cValid)
