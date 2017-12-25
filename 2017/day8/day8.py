regs = dict()

def getReg(r):
    if r not in regs:
        regs[r] = 0

    return regs[r]

aMax = 0
with open('input.txt') as f:
    for line in f:
        s = line.split()
        r = s[0]
        incDec = s[1]
        a = int(s[2])
        rCond = s[4]
        op = s[5]
        aCond = int(s[6])

        if op == '>':
            pred = lambda x: x > aCond
        elif op == '<':
            pred = lambda x: x < aCond
        elif op == '==':
            pred = lambda x: x == aCond
        elif op == '!=':
            pred = lambda x: x != aCond
        elif op == '>=':
            pred = lambda x: x >= aCond
        elif op == '<=':
            pred = lambda x: x <= aCond
        else:
            assert(False)

        if pred(getReg(rCond)):
            if incDec == 'inc':
                regs[r] = getReg(r) + a
            elif incDec == 'dec':
                regs[r] = getReg(r) - a
            else:
                assert(False)

        if getReg(r) > aMax:
            aMax = getReg(r)

# rMax = ''
# aMax = 0
# for r in regs:
#     if regs[r] > aMax:
#         rMax = r
#         aMax = regs[r]

print(aMax)
