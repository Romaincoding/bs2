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
    static void storeShip(int size,boolean isHorizontal, int randomPointCol, int randomPointRow, char tab [][]){
        for (int s = 0; s < size; s++) {
            if (isHorizontal) {
                tab[randomPointCol + s][randomPointRow] = 'S';
            } else {
                tab[randomPointCol][randomPointRow + s] = 'S';
            }
        }

    }

    static void generateShip(int size, char tab [][]) {

        boolean shipCreated = false;

        while (shipCreated==false) {

            boolean isHorizontal = (int) (Math.random() * 2) == 1 ? true : false;

            int randomPointRow = (int) (Math.random() * (10));
            int randomPointCol = (int) (Math.random() * (10 - size + 1));

            if (isHorizontal == false) {
                randomPointCol = (int) (Math.random() * (10));
                randomPointRow = (int) (Math.random() * (10 - size + 1));
            }

            if (isFree(size, randomPointCol, randomPointRow, tab, isHorizontal)) {
                storeShip(size, isHorizontal, randomPointCol, randomPointRow, tab);
                shipCreated =true;
            }
        }
    }

    
    static boolean isFree (int size, int col, int row, char tab [][], boolean isHorizontal){
        // size = 2
        // col = 1
        // row = 4
        for(int i=0;i<size;i++) {
            if (isHorizontal == false && (tab[col][row + i] == 'S')) {
                return false;
            }

            if (isHorizontal == true && (tab [col+i][row] =='S')){
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {

        char tableau[][] = new char[10][10];

        System.out.println("AHOY MATEY"); // write your code here
        initTable(tableau);
        generateShip(2,tableau);

        generateShip(5,tableau);

        generateShip(3,tableau);

        //generateShip(3,tableau);
        generateShip(4,tableau);

        displayTable(tableau);

        // generate ships in the table

        /* for(int z=0;z<10000;z++){
             int indRow = (int)(Math.random()*10);
             System.out.println(indRow);


         }*/
    }
}