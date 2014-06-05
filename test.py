#!/usr/bin/env python
from pylab import *


f = open('the-file-name.txt', 'r')
f = f.read()
f = f.split("\n")
x = []
y = []

for i in range(len(f)-1):
    f[i] = f[i].split(",")
    x.append(f[i][0])
    y.append(f[i][1])

area = 30
scatter(x, y, s=area, c='black', alpha=0.1)

show()
