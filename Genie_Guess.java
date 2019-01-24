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



import javax.swing.JOptionPane;
import java.util.Arrays;

public class Genie_Guess{
/*Global variables Rows & Columns Allowed for program to work.Reduce redundency.
  itemsMatrix is either cards or entries
  savedMatrix saves previous used entries
  morphingArray allows for out of module storage & insuring no duplicates
*/ 
   public static final int ROWS=7;
   public static final int COLUMNS=3;
   public static String [] morphingArray= new String [ROWS*COLUMNS];
   public static String [][] itemsMatrix= new String [ROWS][COLUMNS];
   public static String [][] savedMatrix= new String [ROWS][COLUMNS];

   
   public static void main(String [] args){
    
      String programSelection="Start";
      String choice="Start";
      boolean choseEntriesAtStart=false;
      boolean useSame=false;
      int entryAtLeastOnce=0;
      //Initializing Starting selection Yes for cards or No for Entries.  
      choice=welcomeExplanation();
      if(choice .equalsIgnoreCase ("Yes")){
         programSelection="Cards";
            
      }else if (choice.equalsIgnoreCase ("No")){
         programSelection="Entries";
         choseEntriesAtStart=true;
      }else{
         programSelection="Q";
      }
   
      //Runs program according to selection. Then asks for a new selection or Q for Quiting. 
   
      while(!programSelection .equalsIgnoreCase ("Q")){
      
      
          
          
          //Runs selected Cards or Entries.    
         if (programSelection.equalsIgnoreCase ("Cards")){
            //Loops the Cards selection. Until selection is not cards.
            while (programSelection.equalsIgnoreCase ("Cards")){
               programSelection=cardModule();
            }
               
            // If the user selects Entries it will run this.
         }else if (programSelection.equalsIgnoreCase ("Entries")){
            entryAtLeastOnce++;
             //If this is the first time running Entries selection or if user selects entries at start.
            if(!choseEntriesAtStart || entryAtLeastOnce==1){
               programSelection=entriesModule(useSame); 
               choseEntriesAtStart=true;                 
            }
                  //Will run Entries with the option to use previous entries. 
            else{
               
               while(programSelection .equalsIgnoreCase ("Entries")){ 
                  useSame=useSameData();
                  programSelection=entriesModule(useSame);
                    
               }
            } 
         }
      }                   
   
   }

   /* cardModule finds the guessed card
      returns selected card and prompts for a retry.
   */
   public static String cardModule(){
      boolean cards=true;
      int stage=0;
      int totalItems=52;
      String [][]shuffledDeck=new String [ROWS][COLUMNS];
   
         
      itemsMatrix=getCards();
      shuffledDeck=randomShuffle(itemsMatrix,totalItems,cards);
      String formatedMatrixOriginal=properDisplay(shuffledDeck);
      stage=1;
      int columnOfItem1=getColumnOfItem(formatedMatrixOriginal,stage);
      String[][] loopShuffle1=shuffleMatrix(columnOfItem1,shuffledDeck);
      String formatedMatrix1=properDisplay(loopShuffle1);
      stage=2;
      int columnOfItem2=getColumnOfItem(formatedMatrix1,stage);
      String[][] loopShuffle2=shuffleMatrix(columnOfItem2,loopShuffle1);
      String formatedMatrix2=properDisplay(loopShuffle2);
      stage=3;
      int columnOfItem3=getColumnOfItem(formatedMatrix2,stage);
      String[][] loopShuffle3=shuffleMatrix(columnOfItem3,loopShuffle2);
      String formatedMatrix3=properDisplay(loopShuffle3);
      String eleventhItem=findGuess(loopShuffle3);
      String endCard=printGuess(eleventhItem);
      return endCard;
   
   }
   /* entriesModule finds the guessed entry
      returns selected entry and prompts for a retry.
   */
   public static String entriesModule(boolean useSame){
      boolean cards=false;
      int stage=0;
      int itemMaxInput=21;
      String [][]shuffledDeck=new String [ROWS][COLUMNS];
      
      String formatedMatrixOriginal="";
   
       /*If the user says no=false to use the same items in main.
        Then user will be prompted for new entries.      
      */ 
      if (!useSame){
         itemsMatrix=getEntrySelection(itemMaxInput);
         shuffledDeck=randomShuffle(itemsMatrix,itemMaxInput,cards);
      
         savedMatrix=shuffledDeck;
      
         formatedMatrixOriginal=properDisplay(shuffledDeck);
      }
      
      /*If the user selects yes =true to use the same items in main.
         The same entries will be used from previous run. 
       */
      else{
      //call module that saved previous run to allow for reuse of items.
         shuffledDeck=randomShuffle(itemsMatrix,itemMaxInput,cards);
         formatedMatrixOriginal=properDisplay(shuffledDeck);
      }
      stage=1;
      int columnOfItem1=getColumnOfItem(formatedMatrixOriginal,stage);
      String[][] loopShuffle1=shuffleMatrix(columnOfItem1,shuffledDeck);
      String formatedMatrix1=properDisplay(loopShuffle1);
      stage=2;
      int columnOfItem2=getColumnOfItem(formatedMatrix1,stage);
      String[][] loopShuffle2=shuffleMatrix(columnOfItem2,loopShuffle1);
      String formatedMatrix2=properDisplay(loopShuffle2);
      stage=3;
      int columnOfItem3=getColumnOfItem(formatedMatrix2,stage);
      String[][] loopShuffle3=shuffleMatrix(columnOfItem3,loopShuffle2);
      String formatedMatrix3=properDisplay(loopShuffle3);
      String eleventhItem=findGuess(loopShuffle3);
      String endCard=printGuess(eleventhItem);
      return endCard;
   
    
   }


//Welcomes user to program and prompts choice of play.
   public static String welcomeExplanation(){
   
      String choice="Start";
      JOptionPane.showMessageDialog(null,"Welcome to Genie Guesser!\n");
   
      do{
         choice=JOptionPane.showInputDialog(null,"Would You like to use playing cards?\n(Please Enter: Yes Or No)\n(Q to Quit)");
         if (choice.equalsIgnoreCase ("Yes")){
            choice="yes";
         }else if(choice .equalsIgnoreCase ("No")){
            choice="no";
         }else if(choice .equalsIgnoreCase ("Q")){
            choice="Q";
         }
         else{
            JOptionPane.showMessageDialog(null,"(Please Enter Either Yes Or No Or Q)");
            choice="";
         }
      }while (choice.equalsIgnoreCase ("")||choice.equalsIgnoreCase (" "));
      return choice;
   }
//If the user chose cards this data set pool will be used.
   public static String [][] getCards(){
   
      String [][] cards=
         {
         {"A♣","A♠","A♦","A♥"},
         {"2♣","2♠","2♦","2♥"},
         {"3♣","3♠","3♦","3♥"},
         {"4♣","4♠","4♦","4♥"},
         {"5♣","5♠","5♦","5♥"},
         {"6♣","6♠","6♦","6♥"},
         {"7♣","7♠","7♦","7♥"},
         {"8♣","8♠","8♦","8♥"},
         {"9♣","9♠","9♦","9♥"},
         {"10♣","10♠","10♦","10♥"},
         {"J♣","J♠","J♦","J♥"},
         {"Q♣","Q♠","Q♦","Q♥"},
         {"K♣","K♠","K♦","K♥"}
         
         };
      return cards;
   }

   //Takes in either Cards or Entries and randomely shifts the order. Will reduce 52 cards to 21 for Cards.
   public static String [][] randomShuffle(String [][] dataShuffle,int totalItems,boolean cards){
   
      final int ITEMSMAX=totalItems;
      int lengthArray=totalItems;
      int r=dataShuffle.length;
      int c= dataShuffle[0].length;
      String [] singleArray=new String [totalItems];
      String [][] tempArray=new String [r][c];
      String [][] newArray=new String [ROWS][COLUMNS];
   
      
      int k=0;
      //Takes 2D array and converts it to single array.
      for (int i=0;i<r;i++){
         for (int j=0;j<c;j++){
         
            singleArray[k]=dataShuffle[i][j];
            k++;
         
         
         }
      }
   
      int randomPosition=0;
      int toBeRemoved=0;  
      String [] decreasedItemPool=new String [lengthArray];   
   
       //Goes through 2D Array item by item.        
      for (int i=0;i<r;i++){
         for (int j=0;j<c;j++){
               
         
         //Sends first randomly picked item to get stored.  
            if (i==0 && j==0){ 
               randomPosition=(int)(Math.random()*lengthArray);
               
               tempArray[i][j]=singleArray[randomPosition];
               toBeRemoved=randomPosition;
               decreasedItemPool=removeUsedItem(singleArray,toBeRemoved,lengthArray);
            
            }else{
             //compares each array index for duplicates.
               lengthArray--;
               randomPosition=(int)(Math.random()*lengthArray);
               tempArray[i][j]=decreasedItemPool[randomPosition];
               toBeRemoved=randomPosition;
               decreasedItemPool=removeUsedItem(decreasedItemPool,toBeRemoved,lengthArray);
            
            }
         }
      }
      //Reduces 52 cards to only 21 ( ROWS 7 COLUMNS 3 = 21 items will be stored.) 
      if (cards){
         for (int i=0;i<ROWS;i++){
            for (int j=0;j<COLUMNS;j++){ 
               newArray [i][j]=tempArray[i][j];
            }
         }
      }
      //Will return either newArray which is for Cards . Else will return tempArray which contains entries.                 
      if (cards){
         return newArray; 
      }else{
         return tempArray; 
      }
   
   }
   
//Removes used item & reduces size of array ,so that random doesn't pick a duplicate.
   public static String[] removeUsedItem (String [] array,int toBeRemoved,int lengthArray){
      lengthArray--;
      String [] choicesArray=new String [lengthArray];  
      for (int i=toBeRemoved;i<array.length;i++){
         try{  
            array[i]=array[i+1];   
         }catch(ArrayIndexOutOfBoundsException e){   
         }          
      }
      for (int i=0;i<array.length-1;i++){
         choicesArray[i]=array[i];                
      }
   
                  
   
      return choicesArray;
   }                
   

   //Gets the items from user, while checking for duplicates, and empty entries.
   public static String [][] getEntrySelection(int itemMaxInput){
      int count=0;
      int indexToFill=0;
      boolean duplicateFound=false;
      String [][] anyMatrix=new String[ROWS][COLUMNS];
      JOptionPane.showMessageDialog(null,String.format("Enter a total of %d entries:\n(No Duplicate Items)",itemMaxInput));
      String sendEntry="";
   
      for(int i=0; i<ROWS;i++){
            
         for(int j=0; j<COLUMNS;j++){
            do{
               count++;
               anyMatrix [i][j]=JOptionPane.showInputDialog(String.format("Entry %d: Enter item for spot [%d,%d]:",count,i,j));
               sendEntry=anyMatrix [i][j];                
               if (sendEntry.equalsIgnoreCase ("")|| sendEntry.equalsIgnoreCase (" ")){
                  JOptionPane.showMessageDialog(null,"Please Enter A Valid Input!");
                  count--;
                  //Will send first item to get stored to prevent null exception.
               }else if (count==1){
                  receivingEntry(sendEntry,count,indexToFill);
                  anyMatrix[i][j]=morphingArray[indexToFill];
                  indexToFill++;
                //Compares next item in array to existing items for duplicates.
               }else{
                  duplicateFound=duplicateChecker(sendEntry,count,indexToFill);
                  if (sendEntry.equalsIgnoreCase ("")|| sendEntry.equalsIgnoreCase (" ")){
                     JOptionPane.showMessageDialog(null,"Please Enter A Valid Input!");
                     count--;
                  }
                  if (duplicateFound){
                     JOptionPane.showMessageDialog(null,String.format(":/ Sorry ,Please enter a different entry.\n(That entry already exists in Entry %d)",indexToFill));
                     count--;
                  
                  }else{
                     receivingEntry(sendEntry,count,indexToFill);
                     anyMatrix[i][j]=morphingArray[indexToFill];
                     indexToFill++;
                  }
               }
            } while (sendEntry.equalsIgnoreCase ("")||sendEntry.equalsIgnoreCase (" ")||duplicateFound); 
         
         
               
         }
         
      }
      return anyMatrix;
   }
   /*First run stores item permanently into array.After that it get checked by duplicateChecker 
   first then comes back to be stored.
   */
   public static String [] receivingEntry (String incomingEntry,int count,int indexToFill){
      
      for (int i=indexToFill; i<count;i++){
         
         morphingArray[indexToFill]=incomingEntry;   
      }
      return morphingArray;
   }
   //Checks to see if item is a duplicate.
   public static boolean duplicateChecker(String receivedEntry,int count,int indexToFill){
      boolean duplicateFound=false;
   
      for (int i=0;i<indexToFill ; i++){
         if (morphingArray[i] .equalsIgnoreCase (receivedEntry)){
            duplicateFound=true;           
         }
      }
   
      return duplicateFound;
   }
   //Format the display of columns and rows.
   public static String properDisplay(String [][]formatAnyMatrix){
      String push="";
      int one=1;
      int two=2;
      int three=3;
      String output=String.format("%5s%-38d%-38d%d",push,one,two,three);
   
      for(int i=0; i<ROWS;i++){
         output+=String.format("\n");
         for(int j=0; j<COLUMNS;j++){
            output+=String.format("|\t");
            output+=String.format("%s",formatAnyMatrix [i][j]);
            output+=String.format("\t|"+"%-30s",push);
         }
         
      }
   
   
   
      return output;
   
   }
   //Ask for the user's items column location.
   public static int getColumnOfItem(String formatedMatrixOrignial,int stage){
      int itemColumn=0;
      do{
         try{ 
            if(stage ==1){
               itemColumn=Integer.parseInt(JOptionPane.showInputDialog(null,String.format("Stage %d\nVisualize the item you'd like to pick. "+
                  "\nEnter the column where it exists:\n%s",stage,formatedMatrixOrignial)));
            }else{
               itemColumn=Integer.parseInt(JOptionPane.showInputDialog(null,String.format("Stage %d\nFind the previous selected item again."+
                  "\nEnter the column where it exists:\n%s",stage,formatedMatrixOrignial)));
            }
            if(itemColumn != 1 && itemColumn != 2 && itemColumn != 3){
               JOptionPane.showMessageDialog(null,"Please, Enter a valid column.\n(1 or 2 or 3)");
            }
         }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Please, Enter a valid column.\n(1 or 2 or 3)");
         }
      }while (itemColumn != 1 && itemColumn != 2 && itemColumn != 3);
      return itemColumn;
   }
   
   /*
   Magic Shuffle based on chosen column.
   */
   public static String [][] shuffleMatrix(int columnChosen,String [][] anyMatrix){
      int column1=1;
      int column2=2;
      int column3=3;
      final int MAXLENGTH=ROWS;
      String[][] orderMatrixBOCS1=new String [ROWS][COLUMNS];
      String[][] orderMatrixBOCS2=new String [ROWS][COLUMNS];
      String[][] orderMatrixBOCS3=new String [ROWS][COLUMNS];
   
     //Shuffle items based on chosen column where item is found.
      if(columnChosen==column1){
      
         String[]arrayC1=new String[MAXLENGTH];
         String[]arrayC2=new String[MAXLENGTH];
         String[]arrayC3=new String[MAXLENGTH];
      
         int u=0;
         int v=0;
         int w=0;
         int x=6;
         int y=6;
         int z=6;
          //If column chosen is 1
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=0;
               arrayC1[u]=anyMatrix[i][j];
               u++; 
            }catch (ArrayIndexOutOfBoundsException e){
               u++;
            }            
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=1;
               arrayC2[v]=anyMatrix[i][j];
               v++; 
            }catch (ArrayIndexOutOfBoundsException e){
               v++;
            }               
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=2;
               arrayC3[w]=anyMatrix[i][j]; 
               w++; 
            }catch (ArrayIndexOutOfBoundsException e){
               w++;
            }              
         }
                                    
         for(int i=0; i<ROWS;i++){
         
            for(int j=0; j<COLUMNS;j++){
               if(x>=0){
                  try{
                     orderMatrixBOCS1[i][j]=arrayC3[x];
                     x--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }
               
               else if(y>=0 && x==-1){
               
                  try{
                     orderMatrixBOCS1[i][j]=arrayC1[y];
                     y--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }else if(z>=0 && y==-1 && x==-1){ 
                  try{
                     orderMatrixBOCS1[i][j]=arrayC2[z];
                     z--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }                   
               }            
            }
         }
         
       //If column chosen is 2
      }else if(columnChosen==column2){
      
         String[]arrayC1=new String[MAXLENGTH];
         String[]arrayC2=new String[MAXLENGTH];
         String[]arrayC3=new String[MAXLENGTH];
      
         int u=0;
         int v=0;
         int w=0;
         int x=6;
         int y=6;
         int z=6;
        
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=0;
               arrayC1[u]=anyMatrix[i][j];
               u++; 
            }catch (ArrayIndexOutOfBoundsException e){
               u++;
            }            
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=1;
               arrayC2[v]=anyMatrix[i][j];
               v++; 
            }catch (ArrayIndexOutOfBoundsException e){
               v++;
            }               
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=2;
               arrayC3[w]=anyMatrix[i][j]; 
               w++; 
            }catch (ArrayIndexOutOfBoundsException e){
               w++;
            }              
         }
                                    
         for(int i=0; i<ROWS;i++){
         
            for(int j=0; j<COLUMNS;j++){
               if(x>=0){
                  try{
                     orderMatrixBOCS2[i][j]=arrayC3[x];
                     x--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }
               
               else if(y>=0 && x==-1){
               
                  try{
                     orderMatrixBOCS2[i][j]=arrayC2[y];
                     y--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }else if(z>=0 && y==-1 && x==-1){ 
                  try{
                     orderMatrixBOCS2[i][j]=arrayC1[z];
                     z--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }                   
               }            
            }
         }
        
      
       //  //If column chosen is 3
      }else if(columnChosen==column3){
      
         String[]arrayC1=new String[MAXLENGTH];
         String[]arrayC2=new String[MAXLENGTH];
         String[]arrayC3=new String[MAXLENGTH];
      
         int u=0;
         int v=0;
         int w=0;
         int x=6;
         int y=6;
         int z=6;
        
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=0;
               arrayC1[u]=anyMatrix[i][j];
               u++; 
            }catch (ArrayIndexOutOfBoundsException e){
               u++;
            }            
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=1;
               arrayC2[v]=anyMatrix[i][j];
               v++; 
            }catch (ArrayIndexOutOfBoundsException e){
               v++;
            }               
         }
         for(int i=ROWS-1; i>=0;i--){
            try{
               int j=2;
               arrayC3[w]=anyMatrix[i][j]; 
               w++; 
            }catch (ArrayIndexOutOfBoundsException e){
               w++;
            }              
         }
                                    
         for(int i=0; i<ROWS;i++){
         
            for(int j=0; j<COLUMNS;j++){
               if(x>=0){
                  try{
                     orderMatrixBOCS3[i][j]=arrayC1[x];
                     x--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }
               
               else if(y>=0 && x==-1){
               
                  try{
                     orderMatrixBOCS3[i][j]=arrayC3[y];
                     y--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }
               }else if(z>=0 && y==-1 && x==-1){ 
                  try{
                     orderMatrixBOCS3[i][j]=arrayC2[z];
                     z--;
                  
                  }catch (ArrayIndexOutOfBoundsException e){
                  }                   
               }            
            }
         }
      }
      if (columnChosen==column1){  
         return orderMatrixBOCS1;
      
      }
      else if (columnChosen==column2){  
         return orderMatrixBOCS2;
      
      }else if (columnChosen==column3){  
         return orderMatrixBOCS3;
      
      }else{
         return anyMatrix;
      }
   
   }
   /*Position row 4 and column 2 will always be the winning item. 
   (11th item starting from left to right)*/
   public static String findGuess(String [][] finalArray){
      String guess="";
      int count=0;
      for(int i=0; i<ROWS;i++){
      
         for(int j=0; j<COLUMNS;j++){
            if (i==3 && j==1){
               guess=finalArray[i][j];
            }
         }
         
      }
   
      return guess;
   }
   
   //Prints guessed value and prompts for retry.
   public static String printGuess(String guess){
   
      String choice="Start";
   
      JOptionPane.showMessageDialog(null,String.format("The item you were looking at was %s",guess));
     
   
   
      do{
         choice=JOptionPane.showInputDialog(null,"If you want to switch to Cards\n(Enter Cards)\n If you want Entries\n(Enter Entries)\nOr Q to quit the program.\n(Enter either Cards or Entry or Q)");
         if(!choice .equalsIgnoreCase ("Cards")&&!choice .equalsIgnoreCase ("Entries")&&!choice.equalsIgnoreCase("Q")){  
         
            JOptionPane.showMessageDialog(null,"Invalid Pick,Please Enter Cards or Entries or Q");
         }
         
      }while(!choice .equalsIgnoreCase ("Cards")&&!choice .equalsIgnoreCase ("Entries")&&!choice.equalsIgnoreCase("Q"));  
      return choice;
   
   
   
   }
   //Uses previous entries.Will prompt to use same entries.
   public static boolean useSameData(){
      
      boolean sameData=false;
      String choice="Start";
      do{
         choice=JOptionPane.showInputDialog("Would you like to use the previous entries?\n(Enter Yes Or No");
         if (choice.equalsIgnoreCase ("Yes")){
            sameData=true;
         }else if(choice .equalsIgnoreCase ("No")){
            sameData=false;
         }else{
            JOptionPane.showMessageDialog(null,"Please Enter Yes Or No:");
            choice="";
         }
      }while (choice.equalsIgnoreCase ("")||choice.equalsIgnoreCase (" "));
      return sameData;
   }
}
