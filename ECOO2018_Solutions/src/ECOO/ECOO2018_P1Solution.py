'''
Created on Mar 24, 2018

@author: e23549
'''
fileIn = open("res/DATA11r.txt", 'r')

#expect 10 test cases; read the problem to understand how they should be formatted
for k in range (0, 10):
    
    firstLine = fileIn.readline().split()
    
    T = int(firstLine[0])
    N = int(firstLine[1])
            
    playDaysLeft = 0
            
    for i in range (0, N):
        box = fileIn.readline().strip('\n')
        if box == 'B':
            playDaysLeft += T
        
        if playDaysLeft > 0: 
            playDaysLeft -= 1
        
    print(playDaysLeft)
    
fileIn.close()