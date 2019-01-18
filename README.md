# Genie_Guesser
Guessing Program 
/*
Eduardo Garcia Espitia
Contact me for internship opportunities: eduardo.di.garcia@gmail.com
January 2018
Welcome!
This Guessing Program is based off a magic card trick that my father taught me as a child.
It consists of 7 rows and 3 columns. Entries could be anything (cards or any input) while total
entries totals 21. This program starts by asking the user to begin with cards or entries.
If the user selects cards then the program will randomely select 21 cards from the pool of 52 cards.
It will then display the 21 items in chart format.
If  the user selects entries then, the program will ask for 21 valid inputs.
After the program has the 21 items it will shuffle the order randomely and display the items in a chart format.
The user will be asked to remember one item from any of the columns.
It will ask the column that the item exists in , 3 times.
Each time the program will shuffle the items in a magical way.
Magical Way:
1. Last row of chosen column will remain on top while decending order will be placed behind.
2. Do the same with the other rows
3. Place the chosen column in the middle like a sandwich followed by other columns top and on bottom
Visual of Magical Way:  
original order`
1
2
3
4
5
6
7

stored order

7
6
5
4
3
2
1
Then Sandwiched chosen column always in the middle.
otherColumn |chosenColumn|otherColumn 

After the third shuffle the guessed item will always be found in row 4 column 2.(java terms:row 3 column 1) 
The program then prints that  spot , presenting you with your guess.
The program will then ask if you'd like to play again , so it expects "cards" or "entries" or "q".
If the user picks entries then if the user has already used entries it will if the user would like to use the same
entries as the previous game , then it will shuffle them readomely,this feature allows for a reuse of entered items.
Else it will prompt for 21 new entries.
This program runs until the user enters Q for quit.
Thank You for Trying Out My Program!
-Eduardo Garcia E.


Current Issue:
Formating 2nd column and 3rd column.
For some reason they don't align.
*/

-Eduardo 
