package com.thg.accelerator23.connectn.ai.ruglas.miniMax;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.ruglas.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ruglas.analysis.GameState;

import java.lang.module.Configuration;

public class MiniMaxScoringAlphaBeta {
    Counter counter;

    Counter oppositionCounter;
    int score;

    int bestColumn;

    int bestScore;
    MiniMaxScoringAlphaBeta(Counter counter) {
        this.counter = counter;
        this.oppositionCounter = counter.getOther();
    }
   public int miniMaxMoveAlphaBeta(Board boardPlay, boolean isMax, int depth, int column, int alpha, int beta, int turnCounter) throws InvalidMoveException {
//       System.out.println("depth " + depth + " column " + column + " score "  + score);
       GameConfig config = boardPlay.getConfig();
       GetScore getScore = new GetScore(boardPlay, this.counter);
       BoardAnalyser boardAnalyser = new BoardAnalyser(config);
       GameState gameState = boardAnalyser.calculateGameState(boardPlay);

       if (depth == 0 || gameState.isEnd()) {
           if (gameState.isWin()){
           if (gameState.getWinner() == this.counter){return 1000000*((config.getWidth()*config.getHeight())-turnCounter);}
           else{return -1000000*(config.getHeight()-depth);}}
           else if (gameState.isDraw()){
           return 0;
           }
           else return (getScore.getTotalScore(new Position(column, getMinY(column, boardPlay)), counter, boardPlay)
                       -(getScore.getOpponentScore(new Position(column, getMinY(column, boardPlay)), oppositionCounter, boardPlay)));}
       else if (isMax) {
             bestScore = -1000000;
            for (int xMax=0; xMax<boardPlay.getConfig().getWidth(); xMax++) {
                Position checkPosition = new Position(xMax, getMinY(xMax, boardPlay));
                if (boardPlay.isWithinBoard(checkPosition)){
                Board tempBoard = makeMove(boardPlay, counter, xMax);
                score = miniMaxMoveAlphaBeta(tempBoard, false, depth - 1, xMax, alpha, beta, turnCounter + 1);

                    if (score > bestScore) {
                    bestScore = score;
                    this.bestColumn = xMax;
//                        System.out.println("bestcolumn " + xMax + " depth " + depth);
                }

                    alpha = Math.max(alpha, bestScore);

                    if (beta <= alpha) {
                         break;}

                }}
      return bestScore; }

        else{
            bestScore = 1000000;
           for (int xMin=0; xMin<boardPlay.getConfig().getWidth(); xMin++){
               Position checkPosition = new Position(xMin, getMinY(xMin, boardPlay));
               if (boardPlay.isWithinBoard(checkPosition)){
                Board tempBoard = makeMove(boardPlay, oppositionCounter, xMin);

               score = miniMaxMoveAlphaBeta(tempBoard, true, depth - 1, xMin, alpha, beta, turnCounter + 1);

               if (score<bestScore){
                   this.bestColumn = xMin;
               bestScore = score;
//                   System.out.println("bestcolumn " + xMin + " depth " + depth);
               }
                   beta = Math.min(beta, bestScore);

//////
                   if (beta <= alpha) {
                       break;}


          }
       }return bestScore;}
   }


            private Board makeMove (Board boardPlay, Counter counterPlay,int x) {
        try{
                return new Board(boardPlay, x, counterPlay);}
                catch (InvalidMoveException e) {
                    System.out.println("cant make move");
                }
        return boardPlay;
            }


            public int getMinY ( int x, Board board){
                for (int y = 0; y < board.getConfig().getHeight(); y++) {
                    Position minYPosition = new Position(x, y);
                    if (!board.hasCounterAtPosition(minYPosition)) {
                        return y;
                    }
                }
                return 1000;
            }

    public int getBestColumn() {
        return bestColumn;
    }
}
