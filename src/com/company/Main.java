package com.company;
import java.util.Scanner;
//import Math.*;

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
                tab[randomPointCol + s][randomPointRow] = 'S';
            } else {
                tab[randomPointCol][randomPointRow + s] = 'S';
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

// boolean's function which check if there is a ship on a position and control if there is enough space to place the entire ship
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
// Warm welcome to new player
        System.out.println("AHOY MATEY"); // write your code here
        initTable(tableau);

        displayTable(tableau);
// function which convert entry
       int A = 0;
       int B = 1;
       int C = 2;
       int D = 3;
       int E = 4;
       int F = 5;
       int G = 6;
       int H = 7;
       int I = 8;
       int J = 9;
       String [] letters = {"A", "B", "C", "D", "E", "F","G", "H", "I", "J"};
       System.out.println(letters [1] );

        String c5="C5";
        String  uneLettre = "C";

        if ( 1 == 1 ) // trouver comment tester C5 = 2lettres
        {
            int index = 0;
            int i= 0;
            for (String lettre : letters) {
                if (uneLettre.equals(lettre))
                {
                    index =  i;
                    System.out.println("cc");
                }
                i++;

            }
            System.out.println(index);
        }
        char[] splitString=c5.toCharArray();
        for(char c:splitString){
            System.out.println(c);
        }


// ask for an entry
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a position :");
        String str = sc.nextLine();
        System.out.println("You choose position: " + str);



    }
}