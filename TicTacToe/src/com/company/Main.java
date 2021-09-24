package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Main {

    static ArrayList<Integer> selectedPositions = new ArrayList<Integer>();
    static ArrayList<Integer> selectedCPUPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char [][] myGameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(myGameBoard);



        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select where you would like to place your marker. Please enter your digit (1-9)");
            int chosenPosition = scanner.nextInt();
            while(chosenPosition > 9 || chosenPosition < 1){
                System.out.println("Please enter a number between 1 and 9");
                chosenPosition = scanner.nextInt();
            }
            while(selectedPositions.contains(chosenPosition) || selectedCPUPositions.contains(chosenPosition  )){
                System.out.println("Position is occupied. Please enter a new position");
                chosenPosition = scanner.nextInt();
            }

            positionSetter(myGameBoard,chosenPosition,"player");

            String gameResult = findWinner();

            Random random = new Random();
            int cpuMove = random.nextInt(9)+1;
            while(selectedPositions.contains(cpuMove) || selectedCPUPositions.contains(cpuMove)){
                //System.out.println("CPU tried an unavailable position. It will try again");
                cpuMove = random.nextInt(9)+1;
            }

            positionSetter(myGameBoard,cpuMove,"cpu");

            printBoard(myGameBoard);

            gameResult = findWinner();
            if(gameResult.length() > 0){
                System.out.println(gameResult);
                break;
            }


        }







    }
    public static void printBoard(char [][] myGameBoard){
        for(char [] myRows : myGameBoard){
            for(char c : myRows){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public static void positionSetter(char [][] myGameBoard, int chosenPosition, String player){
        String input = player.toLowerCase();
        char inputchar = 'X';

        if(input.equals("player")){
            inputchar = 'X';
            selectedPositions.add(chosenPosition);
        }
        else if (input.equals("cpu")){
            inputchar = 'O';
            selectedCPUPositions.add(chosenPosition);
        }
        switch (chosenPosition){
            case 1:
                myGameBoard[0][0] = inputchar;
                break;
            case 2:
                myGameBoard[0][2] = inputchar;
                break;
            case 3:
                myGameBoard[0][4] = inputchar;
                break;
            case 4:
                myGameBoard[2][0] = inputchar;
                break;
            case 5:
                myGameBoard[2][2] = inputchar;
                break;
            case 6:
                myGameBoard[2][4] = inputchar;
                break;
            case 7:
                myGameBoard[4][0] = inputchar;
                break;
            case 8:
                myGameBoard[4][2] = inputchar;
                break;
            case 9:
                myGameBoard[4][4] = inputchar;
                break;
            default:
                break;
        }

    }
    public static String findWinner(){
        List firstRow = Arrays.asList(1,2,3);
        List secondRow = Arrays.asList(4,5,6);
        List thirdRow = Arrays.asList(7,8,9);
        List leftCoulumn = Arrays.asList(1,4,7);
        List middleCoulumn = Arrays.asList(2,5,8);
        List rightCoulumn = Arrays.asList(3,6,9);
        List crossingDiagonal = Arrays.asList(1,5,9);
        List crossingDiagonal2 = Arrays.asList(7,5,3);

        List<List> winningMoves = new ArrayList<List>();

        winningMoves.add(firstRow);
        winningMoves.add(secondRow);
        winningMoves.add(thirdRow);
        winningMoves.add(leftCoulumn);
        winningMoves.add(middleCoulumn);
        winningMoves.add(rightCoulumn);
        winningMoves.add(crossingDiagonal);
        winningMoves.add(crossingDiagonal2);

        for(List l : winningMoves){
            if(selectedPositions.containsAll(l)){
                return "You have won. Congratulations";
            }
            else if(selectedCPUPositions.containsAll(l)){
                return "You have lost. Please try again";
            }
            else if (selectedPositions.size()+ selectedCPUPositions.size() == 9){
                return "Game has ended in a tie. Good game.";
            }

        }


        return "";


    }


}
