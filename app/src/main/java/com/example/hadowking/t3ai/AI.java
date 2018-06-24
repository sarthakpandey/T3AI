package com.example.hadowking.t3ai;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class AI {

    class AImove{

        int position = 0;
        int score = 0;

        public AImove(int position, int score) {
            this.position = position;
            this.score = score;
        }

        public AImove(int score) {
            this.score = score;
        }
    }

    private AImove score(Board board){

        int score;

        switch (board.getGameResults()){

            case 0 : score = 0; break;
            case 1 : score = 1; break;
            case 2 : score = -1; break;
            default: score = 2; break;

        }

        return new AImove(score);

    }

    public AImove miniMaxHelper(Board game){

        game.getGameResults();
        if(game.gameOver){
            MainActivity.counter++;
            return score(game);
        }


        int thisScore = 0, bestScore = 0, bestMove = 0;

        Board gameCopy;

        if(game.getCurrentPlayer() == 1){
            bestScore = -1000;
        }else{
            bestScore = 1000;
        }

        List<Integer> moves = game.getAvailabaloSlots();

        for(int i = 0; i < moves.size(); i++) {

            gameCopy = game.copy();

            gameCopy.placePiece(moves.get(i));

            thisScore = miniMaxHelper(gameCopy).score;

            if (game.getCurrentPlayer() == 1) { //For Player

                if (thisScore > bestScore) {

                    bestScore = thisScore;
                    bestMove = moves.get(i);

                }
            } else {
                if (thisScore < bestScore) { //Computer Move

                    bestScore = thisScore;
                    bestMove = moves.get(i);

                }
            }

        }

        AImove temp = new AImove(bestMove, bestScore);

        return temp;

    }


    public AImove miniMax(Board game){

        if(game.isBoardEmpty()){

            return new AImove(1,1);

        }

        if(game.isFirstOccupied()){

            return new AImove(5,5);

        }

        return  miniMaxHelper(game);

    }

}
