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
     *
     * @param tab display a 2D table previously initialized
     */
    static void displayTable(char tab[][]) {

        System.out.println("   A B C D E F G H I J");
        for (int row = 0; row < 10; row++) {
            String affichage = "";
            for (int col = 0; col < 10; col++) {

                affichage = affichage + tab[col][row] + ' ';
            }
            System.out.println(row + "  " + affichage);
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

    /**choose a random position in the table and
    check if it's empty (no ship),
    choose if the ship is horizontal and vertical
    fill the table with ships until five ships are in the table
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
     * @param isHorizontal is the orientation of the ship in table
     * @return the boolean value (true or false)
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
     * Convert a string in an index position
     * @param position is a string for example("B6")
     * @return two int , index column , index row
     */
    static int[] convertPositionToIndex(String position) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //System.out.println(position);

        char[] splitString = position.toUpperCase().toCharArray();
        String letter = Character.toString(splitString[0]);
        String number = Character.toString(splitString[1]);
        // System.out.println(letter);
        //  System.out.println(number);


        int index = -1;
        int i = 0;
        for (String l : letters) {
            if (letter.equals(l)) {
                index = i;
                break;
            }
            i++;
        }

        int index2 = -1;
        i = 0;
        for (String n : numbers) {
            if (number.equals(n)) {
                index2 = i;
                break;
            }
            i++;
        }


        int[] result = new int[2];
        result[0] = index;
        result[1] = index2;
        return result;
    }

    /**
     * fonction that allows to shoot on the adversary board by choosing a position
     * and displays a message if is destroyed or not
     * @param colIdx is the number of the column
     * @param rowIdx is the number of the row
     * @param tab of type char [][]
     * @return
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
     * Enter shoot information when is the player turn
     * @param tab of type char [][]
     * @return true if a sheep has been hit
     */
    static boolean playerTurn(char tab[][]) {
        boolean playAgain = true;
        Scanner sc = new Scanner(System.in);
        // Get input from user
        System.out.println("Entrez des coordonnées : ");
        String input = sc.next();
        // Convert input
        int[] position = convertPositionToIndex(input);
        //System.out.println("colonne = " + position[0]);
        //System.out.println("ligne = " + position[1]);
        // get column index
        int colIdx = position[0];
        // get row index
        int rowIdx = position[1];


        if (colIdx != -1 && rowIdx != -1) {
            // Here i got valid position for ROW and COLUMN indexes
            // ...
            playAgain = shoot(colIdx, rowIdx, tab);
        } else {
            System.out.println("entrée invalide, veuillez rentrer des coordonnées valides: ");
        }
        return playAgain;
    }

    /**
     *
     * @param tab of type char [][]
     * @param list Arraylist that stocking previous move
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

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        boolean isPlayerTurn = true;
        boolean replay = true;
        list.add("0,0");


        char playerBoard[][] = new char[10][10];
        char cpuBoard[][] = new char[10][10];

        while (replay) {
            // Warm welcome message to new player
            System.out.println("AHOY MATEY!"); // write your code here


            initTable(playerBoard);
            initTable(cpuBoard);

            while (true) {

                displayTable(cpuBoard);
                displayTable(playerBoard);

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
