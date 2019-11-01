package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Initialize a 2D table with 5 ships randomly generated
     * A ship is represented with a '#' and the sea is represented with '~'
     * @param tab of type char [][]
     * @return none
     */
    static void initTable(char tab[][]) {

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                tab[col][row] = '~';
            }
        }

        generateShip(2, tab);
        generateShip(5, tab);
        generateShip(3, tab);
        generateShip(3, tab);
        generateShip(4, tab);
    }

    /**
     * Display a 2D table previously initialized
     * Display or hide 5 ships on board
     * @param tab display a 2D table previously initialized
     * @param isShipHidden if it's true: hide the ship on the board
     */
    static void displayTable(char tab[][], boolean isShipHidden,String caca ) {
        String RED = "\033[31m";
        String GREEN = "\033[32m";
        String YELLOW = "\033[33m";
        String BLUE = "\033[34m";
        String WHITE = "\033[0m";

        System.out.println(YELLOW + "  A B C D E F G H I J" + WHITE + caca);
        //fill the board with sea and ships
        for (int row = 0; row < 10; row++) {
            String affichage = "";
            for (int col = 0; col < 10; col++) {
                if (isShipHidden == true && tab[col][row] == '#') {
                    affichage = affichage + '~'+ " ";
                }
                else if (tab[col][row] == '#') {
                    affichage = affichage + BLUE + tab[col][row] + WHITE + ' ';
                }
                else if (tab[col][row] == 'O') {
                    affichage = affichage + RED + tab[col][row] + WHITE + ' ';
                }
                else if (tab[col][row] == 'X') {
                    affichage = affichage + GREEN + tab[col][row] + WHITE + ' ';
                }
                else {
                    affichage = affichage + tab[col][row] + ' ';
                }
            }
            System.out.println(YELLOW + row + WHITE + "  " + affichage);
        }
    }

    /**
     * Choose an orientation for a ship and create it
     * @param size represents the size of a ship
     * @param isHorizontal boolean that informs how the ship is positioned (vertical/horizontal)
     * @param randomPointCol random position in the column
     * @param randomPointRow random position on a row
     * @param tab of type char [][]
     * @return none
     */
    static void storeShip(int size, boolean isHorizontal, int randomPointCol, int randomPointRow, char tab[][]) {
        for (int s = 0; s < size; s++) {
            if (isHorizontal) {
                tab[randomPointCol + s][randomPointRow] = '#';
            } else {
                tab[randomPointCol][randomPointRow + s] = '#';
            }
        }
    }

    /**
     * choose a random position in the table and
     * check if it's empty (no ship),
     * choose if the ship is horizontal and vertical
     * fill the table with ships until five ships are in the table
     * @param size represents the size of a ship
     * @param tab  of type char [][]
     */

    static void generateShip(int size, char tab[][]) {

        boolean shipCreated = false;

        while (shipCreated == false) {
            int randomPointCol;
            int randomPointRow;

            boolean isHorizontal = (int) (Math.random() * 2) == 1 ? true : false;

            if (isHorizontal == true) {
                randomPointRow = (int) (Math.random() * (10));
                randomPointCol = (int) (Math.random() * (10 - size + 1));
            } else {
                randomPointCol = (int) (Math.random() * (10));
                randomPointRow = (int) (Math.random() * (10 - size + 1));
            }

            if (isFree(size, randomPointCol, randomPointRow, tab, isHorizontal)) {
                storeShip(size, isHorizontal, randomPointCol, randomPointRow, tab);
                shipCreated = true;
            }
        }
    }

    /** boolean's function which check if there is a ship on a position and control if there is enough space to place the entire ship
     *
     * @param size represents the size of a ship
     * @param col the column's index
     * @param row the row's index
     * @param tab of type char [][]
     * @param isHorizontal is the orientation of the ship in the table
     * @return true if the ship is horizontal
     */
    static boolean isFree(int size, int col, int row, char tab[][], boolean isHorizontal) {
        // size = 2
        // col = 1
        // row = 4
        for (int i = 0; i < size; i++) {
            if (isHorizontal == false && (tab[col][row + i] == '#')) {
                return false;
            }

            if (isHorizontal == true && (tab[col + i][row] == '#')) {
                return false;
            }

        }
        return true;
    }

    /**
     * Convert a string into an index position
     * @param position is a string for example("B6")
     * @return two int , index column , index row
     */
    static int[] convertPositionToIndex(String position) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        // Set the string into capital letters and split it in two characters
        char[] splitString = position.toUpperCase().toCharArray();
        String letter = Character.toString(splitString[0]);
        String number = Character.toString(splitString[1]);

        //Compare the characters with the letters tab and return index when they are equals
        int index = -1;
        int i = 0;
        for (String l : letters) {
            if (letter.equals(l)) {
                index = i;
                break;
            }
            i++;
        }
        //Compare the characters with the letters tab and return index when they are equals
        int index2 = -1;
        i = 0;
        for (String n : numbers) {
            if (number.equals(n)) {
                index2 = i;
                break;
            }
            i++;
        }

        //stock the characters in a variable and return it
        int[] result = new int[2];
        result[0] = index;
        result[1] = index2;
        return result;
    }

    /**
     * fonction that allows to shoot on the opponent board by choosing a position
     * displays a message if it's hit or not
     * displays a message if we have already shot on the position
     * @param colIdx is the number of the column
     * @param rowIdx is the number of the row
     * @param tab of type char [][]
     * @return true if we hit an enemy ship
     */
    static boolean shoot(int colIdx, int rowIdx, char tab[][]) {
        boolean isShootOK = false;

        if (isShip(colIdx, rowIdx, tab) == true) {
            System.out.println("BOOM!");
            tab[colIdx][rowIdx] = 'X';
            isShootOK = true;

        } else if (isAlreadyHit(colIdx, rowIdx, tab) == true) {
            System.out.println("AlREADY HIT");
        } else {
            System.out.println("PLOUF");
            tab[colIdx][rowIdx] = 'O';
        }
        return isShootOK;
    }

    /**
     * Give the information in tha table about the shoot, if is touch or not
     * @param col the column's index
     * @param row the row's index
     * @param tab of type char [][]
     * @return the value of the boolean (true or false)
     */
    static boolean isShip(int col, int row, char tab[][]) {
        if (tab[col][row] == '#') {
            return true;
        }
        return false;
    }

    /**
     * Give the information about the position is already hit
     * @param col the column's index
     * @param row the row's index
     * @param tab of type char [][]
     * @return return the value of the boolean (true or false)
     */
    static boolean isAlreadyHit(int col, int row, char tab[][]) {
        if ((tab[col][row] == 'X') || (tab[col][row] == 'O')) {
            return true;
        }
        return false;
    }

    /**
     * Enter shoot informations when the player turns
     * @param tab of type char [][]
     * @return true if a ship has been hit
     */
    static boolean playerTurn(char tab[][]) {
        boolean playAgain = true;
        Scanner sc = new Scanner(System.in);
        // Get input from user
        System.out.println("Please enter coordinates : ");
        String input = sc.next();
        // Convert input
        int[] position = convertPositionToIndex(input);
        //System.out.println("column = " + position[0]);
        //System.out.println("row = " + position[1]);
        // get column index
        int colIdx = position[0];
        // get row index
        int rowIdx = position[1];

        if (colIdx != -1 && rowIdx != -1) {
            // Here i got valid position for ROW and COLUMN indexes
            // ...
            playAgain = shoot(colIdx, rowIdx, tab);
        } else {
            System.out.println("Wrong entry, please enter correct informations! ");
        }
        return playAgain;
    }

    /**
     *
     * @param tab of type char [][]
     * @param list Arraylist that is stocking previous move
     * @return if a ship has been hit
     */
    static boolean cpuTurn(char tab[][], ArrayList list) {
        int colIdx;
        int rowIdx;
        do {
            colIdx = (int) (Math.random() * 10);
            // get row index
            rowIdx = (int) (Math.random() * 10);
        }while (isHistoryContain(colIdx, rowIdx, list));
        // check if the ship has been hit
        boolean targetReached = shoot(colIdx, rowIdx, tab);
        updateHistory(colIdx, rowIdx, list );
        return targetReached;
    }

    /**
     * Check if ships left on the table
     * @param tab of type char [][]
     * @return false if the ship in not destroyed
     */
    static boolean allShipDestroyed(char tab[][]) {

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (tab[col][row] == '#') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Ask the player if he wants to play again,
     * if he chooses yes, the game restarts
     * if he chooses no, the program stops
     * @return a boolean true for yes and false fo no
     */
    static boolean replay() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Another game ? yes/no : ");
        String input = sc.next();
        if(input.toLowerCase().equals("yes") == true ){
            return true;
        }
        return false;
    }

    /**
     * Stock previous move of Cpu in a Arraylist
     * @param col the column's index
     * @param row the row's index
     * @param list Arraylist that stocking previous move
     * @return none
     */

    static void updateHistory(int col, int row, ArrayList list){
        String value = "" + col + "," + row;
        list.add(value);
        System.out.println(list);
    }

    /**
     * Check if the random position generated by the cpu is different of its previous move
     * @param col the column's index
     * @param row the row's index
     * @param list Arraylist that stock previous move
     * @return true if list contains the same positions
     */
    static boolean isHistoryContain(int col, int row, ArrayList list){
        String value = "" + col + "," + row;
        return list.contains(value);
    }

    /**
     * Check if the random position generated by the cpu is different of its previous move
     * @param col the column's index
     * @param row the row's index
     * @param list Arraylist that stock previous move
     * @return true if list contains the same positions
     */
    static void myPrint(String color,String message){
        switch (color){
            case"Blue": System.out.println("\033[34m" + message +"\033[0m");
                break;
            case"Green": System.out.println("\033[32m" + message +"\033[0m");
                break;
            case"Red": System.out.println("\033[31m" + message +"\033[0m");
                break;
            case"Yellow": System.out.println("\033[33m" + message +"\033[0m");
                break;
            default:
                System.out.println("Error Unknown Color");
        }
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        boolean isPlayerTurn = true;
        boolean replay = true;
        list.add("0,0");

        char cpuBoard[][] = new char[10][10];
        char playerBoard[][] = new char[10][10];

        while (replay) {
            myPrint("Red", "AHOY MATEY!");
            // Warm welcome message to new player

            initTable(playerBoard);
            initTable(cpuBoard);

            while (true) {

                displayTable(cpuBoard, true, " Cpu's Board");
                displayTable(playerBoard, false," Player's Board");

                if (isPlayerTurn) {

                    if (playerTurn(cpuBoard) == false) {
                        isPlayerTurn = false;
                    }
                    if (allShipDestroyed(cpuBoard) == true) {
                        System.out.println("Congratulations you win!");
                        break;
                    }
                } else {
                    if (cpuTurn(playerBoard, list) == false) {
                        isPlayerTurn = true;
                    }

                    if (allShipDestroyed(playerBoard) == true) {
                        System.out.println("Sorry you loose!");
                        break;
                    }
                }
            }
            replay = replay();
        }
    }
}
