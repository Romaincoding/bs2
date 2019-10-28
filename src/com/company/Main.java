package com.company;
public class Main {

    //Fonction tableau
    static void  createTable() {
        char tab[][] = new char[10][10];
        System.out.println("  A B C D E F G H I J");
        for (int row = 0;row<10;row++){
            String affichage ="";
            for(int col= 0;col<10;col++){
                tab [col] [row] = '~';
                affichage = affichage + tab [col][row] + ' ';
            }
            System.out.println(row + " "+ affichage);
        }
    }

    public static void main(String[] args) {

        // write your code here
        createTable();

    }
}