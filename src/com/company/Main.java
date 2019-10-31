package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // creation of a table with five ships
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

    //display table with ships
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

    //set ship
    static void storeShip(int size, boolean isHorizontal, int randomPointCol, int randomPointRow, char tab[][]) {
        for (int s = 0; s < size; s++) {
            if (isHorizontal) {
                tab[randomPointCol + s][randomPointRow] = '#';
            } else {
                tab[randomPointCol][randomPointRow + s] = '#';
            }
        }

    }

    /* choose a random position in the table and
    check if it's empty (no ship),
    choose if the ship is horizontal and vertical
    fill the table with ships until five ships are in the table
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

    // boolean's function which check if there is a ship on a position and control if there is enough space to place the entire ship
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

    //fonction play
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

    static boolean isShip(int col, int row, char tab[][]) {
        if (tab[col][row] == '#') {
            return true;
        }
        return false;
    }

    static boolean isAlreadyHit(int col, int row, char tab[][]) {
        if ((tab[col][row] == 'X') || (tab[col][row] == 'O')) {
            return true;
        }
        return false;
    }

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

    static boolean replay() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Another game ? yes/no : ");
        String input = sc.next();
        if(input.toLowerCase().equals("yes") == true ){
            return true;
        }
        return false;
    }
    //  static boolean isShipAround(int colIdx, int rowIdx, char tab [][]){
    // tab [colIdx] + 1 tab [colIdx] - 1
    // }

    static void updateHistory(int col, int row, ArrayList list){
        String value = "" + col + "," + row;
        list.add(value);
        System.out.println(list);
    }
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
            // Warm welcome to new player
            System.out.println("AHOY MATEY"); // write your code here


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
