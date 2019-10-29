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

        System.out.println("   A B C D E F G H I J");
        for (int row = 0;row<10;row++){
            String affichage ="";
            for(int col= 0;col<10;col++){

                affichage = affichage + tab [col][row] + ' ';
            }
            System.out.println(row + "  "+ affichage);
        }

    }

    public static void main(String[] args) {

        char tableau[][] = new char[10][10];

        System.out.println("Ahoy Matey"); // write your code here
        initTable(tableau);
        displayTable(tableau);

        // generate ships in the table
         String shipOne = "S S S S S";

        /* for(int z=0;z<10000;z++){
             int indRow = (int)(Math.random()*10);
             System.out.println(indRow);


         }*/
    }
}