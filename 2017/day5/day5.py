f = open("input.txt", "r")

jumps = []
line = f.readline()
while line != '':
    jumps.append(int(line))

    line = f.readline()

c = 0
i = 0
while i >= 0 and i < len(jumps):
    c += 1
    j = jumps[i]

    if (jumps[i] >= 3):
        jumps[i] -= 1
    else:
        jumps[i] += 1

    i += j

print(c)
