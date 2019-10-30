package com.company;


import java.util.Scanner;

public class Main {

    // creation of a table with five ships
    static void initTable(char tab[][]){

        for (int row = 0;row<10;row++){
            for(int col= 0;col<10;col++){
                tab [col] [row] = '~';
            }
        }

        generateShip(2,tab);
        generateShip(5,tab);
        generateShip(3,tab);
        generateShip(3,tab);
        generateShip(4,tab);
    }

    //display table with ships
    static void displayTable(char tab[][]) {

        System.out.println("   A B C D E F G H I J");
        for (int row = 0;row<10;row++){
            String affichage ="";
            for(int col= 0;col<10;col++){

                affichage = affichage + tab [col][row] + ' ';
            }
            System.out.println(row + "  "+ affichage);
        }




    }
    //set ship
    static void storeShip(int size,boolean isHorizontal, int randomPointCol, int randomPointRow, char tab [][]){
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
    static void generateShip(int size, char tab [][]) {

        boolean shipCreated = false;

        while (shipCreated==false) {
            int randomPointCol;
            int randomPointRow;

            boolean isHorizontal = (int) (Math.random() * 2) == 1 ? true : false;

            if (isHorizontal == true) {
                randomPointRow = (int) (Math.random() * (10));
                randomPointCol = (int) (Math.random() * (10 - size + 1));
            }
            else {
                randomPointCol = (int) (Math.random() * (10));
                randomPointRow = (int) (Math.random() * (10 - size + 1));
            }

            if (isFree(size, randomPointCol, randomPointRow, tab, isHorizontal)) {
                storeShip(size, isHorizontal, randomPointCol, randomPointRow, tab);
                shipCreated =true;
            }
        }
    }

    // boolean's function which check if there is a ship on a position and control if there is enough space to place the entire ship
    static boolean isFree (int size, int col, int row, char tab [][], boolean isHorizontal){
        // size = 2
        // col = 1
        // row = 4
        for(int i=0;i<size;i++) {
            if (isHorizontal == false && (tab[col][row + i] == '#')) {
                return false;
            }

            if (isHorizontal == true && (tab [col+i][row] =='#')){
                return false;
            }

        }
        return true;
    }
    static int[] convertPositionToIndex(String position) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        //System.out.println(position);

        char[] splitString = position.toUpperCase().toCharArray() ;
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
    static void shoot(int colIdx, int rowIdx, char tab[][]) {
        if (isShip(colIdx, rowIdx, tab)==true){
            System.out.println("BOOM!");
            tab[colIdx][rowIdx] = 'X';

        }else if(isAlreadyHit(colIdx, rowIdx, tab)==true){
            System.out.println("AlREADY HIT");
        }
        else{
            System.out.println("PLOUF");
            tab[colIdx][rowIdx] = 'O';
        }

    }
    static boolean isShip ( int col, int row, char tab [][]){
        if(tab[col][row]== '#'){
            return true;
        }
        return false;
    }
    static boolean isAlreadyHit ( int col, int row, char tab [][]) {
        if (tab[col][row] == 'X') {
            return true;
        }
        return false;
    }




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char tableau[][] = new char[10][10];


        // Warm welcome to new player
        System.out.println("AHOY MATEY"); // write your code here

        // init
        initTable(tableau);


        while(true){
            // Get input from user
            System.out.println("Entrez des coordonnées : ");
            String input = sc.next();

            // Convert input
            int[] position = convertPositionToIndex(input);
            System.out.println("colonne = " + position[0]);
            System.out.println("ligne = " + position[1]);



            // get column index
            int colIdx = position[0];
            // get row index
            int rowIdx = position[1];


            displayTable(tableau);
            shoot(colIdx, rowIdx, tableau);



                // Here i got valid position for ROW and COLUMN indexes
                // ...


            }


        }


    }

