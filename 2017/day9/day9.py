with open('input.txt') as f:
    input = f.read()
    score = 0
    garbage = 0
    i = 0
    groupDepth = 0
    fGarbage = False
    while i < len(input):
        if fGarbage:
            if input[i] == '>':
                fGarbage = False
            elif input[i] == '!':
                i += 1
            else:
                garbage += 1
        else:
            if input[i] == '{':
                groupDepth += 1
            elif input[i] == '}':
                score += groupDepth
                groupDepth -= 1
            elif input[i] == '<':
                fGarbage = True
        i += 1

print('score:', score)
print('garbage:', garbage)
