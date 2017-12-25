programs = dict()

with open('input.txt') as f:
    for line in f:
        name_weight_stChildren = line.split('->')
        name_weight = name_weight_stChildren[0]
        if len(name_weight_stChildren) == 2:
            stChildren = name_weight_stChildren[1]
            children = list(map(lambda s: s.strip(), stChildren.split(',')))
        else:
            children = []

        name, stWeight = name_weight.split()
        weight = int(stWeight.strip('()'))
        name = name.strip()

        programs[name] = (weight, children)

child_programs = set()
for name in programs:
    _, children = programs[name]
    for child in children:
        child_programs.add(child)

root_programs = set(programs.keys()) - child_programs
print(root_programs)

def get_tower_weight(program):
    w, children = programs[program]
    if len(children) > 0:
        child_weights = list(map(get_tower_weight, children))
        if not all([x == child_weights[0] for x in child_weights]):
            print(program, "is unbalenced")
            print(children)
            print(child_weights)
        return w + sum(child_weights)
    return w

for r in root_programs:
    get_tower_weight(r)
