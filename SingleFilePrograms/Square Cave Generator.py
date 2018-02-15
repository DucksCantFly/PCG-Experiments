
###################################################################################################################################
# Cellular Automata method for generating cave-like levels                                                                        #
# Author: Cory Nelson                                                                                                             #
# Emulates Behavior of: http://www.roguebasin.com/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels #
###################################################################################################################################

import random

def printCave(arg, dim):
    #print("printing cave")
    for i in range(dim):
        for j in range(dim):
            print(arg[i][j], end = "")
        print()


#generate a square graph based on given dimensions
def generateGraph(dim, iterate):

    #initialize array
    cave = [[0] * dim for i in range(dim)]
    # '#' represents wall
    # '.' represents floor
    

    #GENERATE GRAPH
    possibilities = ["#", "."]

    for i in range(dim):
        for j in range(dim):
            random.shuffle(possibilities)
            cave[i][j] = possibilities[0]

    
    #horizontal blanking
    xOffset = int(dim)
    yOffset = int(dim/9)
    #blank y
    i = 0
    while(i < yOffset):
        cave[int(dim/2)][int(dim/2)-i] = "."
        i += 1
    #blank x
    i = 0
    while(i < xOffset):
        cave[int(dim/2)-i][int(dim/2)] = "."
        i += 1
    
    #rules:
    #   A tile is a wall if the 3x3 region centered around it
    #contained at least 5 walls

    x = 0
    iterations = iterate 
    while(x < iterations):
        
        for i in range(dim):
            for j in range(dim):

				###########
				# FIX     #
				# THIS    #
				# SECTION # 
				###########
				
                #CHECK EACH DIRECTION FOR VALIDITY
                # # # # #
                # 1 2 3 #
                # 4 * 5 #
                # 6 7 8 #
                # # # # #
                #if index is invalid, treat index as holding a wall
                wallCount = 0
                
                if(i == 0 or j == 0):
                    wallCount += 1
                elif (cave[i-1][j-1] == "#"):
                    wallCount += 1
                if(i == 0):
                    wallCount += 1
                elif(cave[i-1][j] == "#"):
                    wallCount += 1
                if(i == 0 or j + 1 == dim):
                    wallCount += 1
                elif(cave[i-1][j+1] == "#"):
                    wallCount += 1
                if(j == 0):
                    wallCount += 1
                elif(cave[i][j-1] == "#"):
                    wallCount += 1
                if(cave[i][j] == "#"):
                    wallCount += 1
                if(j+1 == dim):
                    wallCount += 1
                elif(cave[i][j+1] == "#"):
                    wallCount += 1
                if(j == 0 or i+1 == dim):
                    wallCount += 1
                elif(cave[i+1][j-1] == "#"):
                    wallCount +=1
                if(i + 1 == dim):
                    wallCount += 1
                elif(cave[i+1][j] == "#"):
                    wallCount += 1
                if(i + 1 == dim or j + 1== dim):
                   wallCount += 1
                elif(cave[i+1][j+1] == "#"):
                    wallCount +=1

                if(wallCount >= 5):
                   cave[i][j] = "#"
                else:
                   cave[i][j] = "."
        x += 1

    return cave

inputNum = int(input("How many Graphs do you want to generate?\n"))
inputDim = int(input("How many rows should each graph have?\nRECOMMENDED: BETWEEN 15 AND 100\n"))
inputIter = int(input("How many iterations should each graph go through?\nRECOMMENDED: between 8 and 13\n"))
i = 0
while i < inputNum:
    print("\nGraph", i + 1)
    printCave(generateGraph(inputDim,inputIter), inputDim)
    i += 1
input() 
