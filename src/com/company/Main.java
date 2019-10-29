package com.company;
//import Math.*;

public class Main {


    static void initTable(char tab[][]){

        for (int row = 0;row<10;row++){
            for(int col= 0;col<10;col++){
                tab [col] [row] = '~';

            }

        }

    }

    //
    static void displayTable(char tab[][]) {

        System.out.println("  A B C D E F G H I J");
        for (int row = 0;row<10;row++){
            String affichage ="";
            for(int col= 0;col<10;col++){

                affichage = affichage + tab [col][row] + ' ';
            }
            System.out.println(row + " "+ affichage);
        }




    }

    static void generateShip(int size, char tab [][]){
        int randomPointRow = (int) (Math.random()*10);
        int randomPointCol = (int) (Math.random()*10);

        tab [randomPointCol] [randomPointRow]= 'S';
        if (size == 2){
            if (randomPointCol != 0) {
                tab[randomPointCol][randomPointRow] = 'S';
                tab[randomPointCol - 1][randomPointRow] = 'S';
            }else{
                tab[randomPointCol][randomPointRow] = 'S';
                tab[randomPointCol + 1][randomPointRow] = 'S';

            }
        }

    }
    static boolean isFree (int size, int col, int row, char tab [][]){
        // size = 2
        // col = 1
        // row = 4
        for(int i=0;i<size;i++) {
            if (tab[row][col - i] == 'S') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        char tableau[][] = new char[10][10];

        System.out.println("AHOY MATEY"); // write your code here
        initTable(tableau);
        displayTable(tableau);

        // generate ships in the table
        generateShip(2,tableau);
        generateShip(1,tableau);

        displayTable(tableau);



        /* for(int z=0;z<10000;z++){
             int indRow = (int)(Math.random()*10);
             System.out.println(indRow);


         }*/

    }
}