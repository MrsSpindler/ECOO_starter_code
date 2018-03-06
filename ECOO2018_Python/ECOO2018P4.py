'''
Sample File Reading program for ECOO contests
'''
fileIn = open("res/DATA40.txt", 'r')

#expect 10 test cases; read the problem to understand how they should be formatted
for k in range (0, 10):
    
    print("Reading Test case #",k)
    
    #demonstrating how to read different types of input from a file
    
    #expecting a single integer?  Use this:
    expectedInteger = int(fileIn.readline())
    print(expectedInteger)
    
    #expecting a float? Use this:
    expectedFloat = float(fileIn.readline())
    print(expectedFloat)
    
    #expecting a String? Use this to avoid have the newline character included on the end of the string
    expectedString = fileIn.readline().strip('\n')
    print(expectedString)
    
    #expecting a list of items?  Use this.  Change into a number if needed
    expectedList = fileIn.readline().split()
    
    for item in expectedList:
        print(item)

fileIn.close()