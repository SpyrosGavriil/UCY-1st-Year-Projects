/*
* Author: Spyros Gavriil
* Written: 26/11/2023
* Last updated: 26/11/2023
*
* Compilation: javac -cp your\library\path\stdlib.jar Board.java UserChoice.java Sudoku.java
* Execution: java -cp .;your\library\path\stdlib.jar Sudoku <N> <game-file>
*
* This class is solely based on handling the user's input as an object. We construct the variables, set the variables according to the 
* user's input and we return them as an object.
*
*/
public class UserChoice {

    private int row;
    private int column;
    private int value;
    
    // Default constructor initializes choices to 0
    public UserChoice() {
        row = 0;
        column = 0;
        value = 0;
    }

    // Method to set user choices
    public void setChoice(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    // Getter method for the row choice
    public int getRow() {
        return row;
    }

    // Getter method for the column choice
    public int getColumn() {
        return column;
    }

    // Getter method for the value choice
    public int getValue() {
        return value;
    }
}
