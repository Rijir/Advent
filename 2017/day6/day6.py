def getHash(banks):
    h = 0
    for i in range(len(banks)):
        h += banks[i] * 20**i
    return h

f = open('input.txt')
line = f.readline()
stBanks = line.split()
banks = list(map(int, stBanks))

seen = dict()
c = 0

while getHash(banks) not in seen:
    seen[getHash(banks)] = c
    c += 1
    iMax = banks.index(max(banks))
    iCur = iMax
    distribute = banks[iMax]
    banks[iMax] = 0
    while distribute > 0:
        iCur = (iCur + 1)%len(banks)
        banks[iCur] += 1
        distribute -= 1

print(c - seen[getHash(banks)])
