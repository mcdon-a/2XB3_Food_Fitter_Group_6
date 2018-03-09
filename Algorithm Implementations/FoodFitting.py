from random import random

foods = []
for i in xrange(5000):
    item = ("Cereal"+str(i),int(random()*500)+50)
    foods.append(item)

#foods.sort()

MAX_CAL = 2000
MAX_ITEMS = 5
buckets = [[] for i in xrange(MAX_CAL+1)]
# Insert single foods
for food in foods:
    if food[1]>MAX_CAL: break
    if len(buckets[food[1]]) == MAX_ITEMS: continue
    buckets[food[1]].append([food]) # [food1, food2, etc]

for i in xrange(1,MAX_CAL+1):
    for meal in buckets[i]:
        for food in foods:
            new_cal = i+food[1]
            if new_cal>MAX_CAL: break
            if len(buckets[new_cal]) == MAX_ITEMS: continue
            new_meal = meal[:]+[food]
            buckets[new_cal].append(new_meal)
print "DONE"
