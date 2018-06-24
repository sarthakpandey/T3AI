package com.example.hadowking.t3ai;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static int WIN = 1;
    private static int FAIL = 2;
    private static int DRAW = 0;
    private static int INCOMPLETE = 4;
   // private static boolean flag = true;

    int[][] gameBoard = new int[3][3];

    private int playCounter = 1;

    public boolean gameOver = false;

    public Board copy(){

        Board c = new Board();

        c.gameOver = this.gameOver;

        c.playCounter = Integer.valueOf(this.playCounter);

        for(int i = 0; i < 3; i++){

            for(int j = 0; j < 3; j++){

                c.gameBoard[i][j] = this.gameBoard[i][j];

            }

        }

        return c;

    }

    public void placePiece(int position){



        if(gameOver)
            return;

        int score = 0;

        if(getCurrentPlayer() == 1)
            score = 1;
        else
            score = 5;


        switch (position){

            case 1 : gameBoard[0][0] = score;break;
            case 2 : gameBoard[0][1] = score; break;
            case 3 : gameBoard[0][2] = score; break;
            case 4 : gameBoard[1][0] = score; break;
            case 5 : gameBoard[1][1] = score; break;
            case 6 : gameBoard[1][2] = score; break;
            case 7 : gameBoard[2][0] = score; break;
            case 8 : gameBoard[2][1] = score; break;
            case 9 : gameBoard[2][2] = score; break;
            default:break;

        }

        ++playCounter;

        if ((getGameResults() != INCOMPLETE))
            gameOver = true;



    }

    public boolean isBoardEmpty(){

       // if(!flag)
          //  return flag;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                if (gameBoard[i][j] != 0) {

                    return false;

                }

            }

        }

        //flag = false;

        return true;

    }

    public boolean isFirstOccupied(){

        if(gameBoard[0][0] != 1)
            return false;

        boolean check = true;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                if(i == 0 && j == 0)
                    continue;

                if(gameBoard[i][j] != 0)
                    check = false;

            }

        }

        return check;

    }

    public int getCurrentPlayer(){

        if(this.playCounter % 2 == 1){
            return 1;
        }else {
            return 2;
        }

    }

    public int getGameResults(){

        int gameResult = INCOMPLETE;

        boolean hasZero = false;

        int rowSum = 0;

        int colSum = 0;

        int diagonalSum = 0;

        int anotherDiagonalSum = 0;

        for(int i = 0; i < 3; i++){

            rowSum = gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2];

            if(rowSum == 15){
                return FAIL;
            }else if(rowSum == 3){

                return WIN;
            }

        }

        for(int i = 0; i < 3; i++){

            colSum = gameBoard[0][i] + gameBoard[1][i] + gameBoard[2][i];

            if(colSum == 15)
                return FAIL;
            else if(colSum == 3)
                return WIN;

        }

        diagonalSum = gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];

        anotherDiagonalSum = gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];

        if(diagonalSum == 15 || anotherDiagonalSum == 15)
            return FAIL;
        else if(diagonalSum == 3 || anotherDiagonalSum == 3)
            return WIN;

        //Checking if it is draw
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 0) {
                    hasZero = true;
                }
            }
        }

        if(!hasZero)
            return DRAW;

        return gameResult;


    }

    public List getAvailabaloSlots(){

        List<Integer> list = new ArrayList<>();

        int flag = 1;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                if(gameBoard[i][j] == 0)
                    list.add(flag);

                flag++;

            }

        }

        return list;

    }


}
