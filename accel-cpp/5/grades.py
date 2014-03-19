#!/usr/bin/env python

import random

for i in range(1000):
    l = ''
    for i in range(10):
        l += random.choice('abcdefghijklmnopqrstuvwxyz')
        
    for i in range(5):
        l += ' ' + str(random.randint(1, 100))
    
    print l

    
