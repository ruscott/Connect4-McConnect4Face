package com.thg.accelerator23.connectn.ai.ruglas.Manual;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.ruglas.miniMax.GetScore;

import java.util.ArrayList;
import java.util.Random;

public class ChooseMove {
    Board board;
    int boardWidth;
    int boardHeight;
    Counter counter;
    Counter opponentCounter;
    Integer playLocation;

    ChooseMove(Board board, Counter counter) {
        this.board = board;
        this.counter = counter;
        this.opponentCounter = counter.getOther();
        this.boardHeight = board.getConfig().getHeight();
        this.boardWidth = board.getConfig().getWidth();
        this.playLocation = null;
    }

    public void findWinPosition() throws InvalidMoveException {
        for (int column = 0; column < this.boardWidth; column++) {
            if (TestMove.isGameOverAfterMove(this.board, column, this.counter)) {
                this.playLocation = column;
                System.out.println("Win found");
            }
        }
    }

    public void findBlockPosition() throws InvalidMoveException {
        for (int column = 0; column < this.boardWidth; column++) {
            if (TestMove.isGameOverAfterMove(this.board, column, this.opponentCounter)) {
                this.playLocation = column;
                System.out.println("blocking win");
            }
        }
    }

    public void setBestMove() throws InvalidMoveException {
        findWinPosition();
        if (this.playLocation == null ) {
            findBlockPosition();
        }

        if (this.playLocation == null) {
            int bestScore = 0;
            int bestColumn = 0;
            for (int i=0; i<this.boardWidth; i++) {
                Position positionToPlay = new Position(i,getMinY(i, this.board));
                if (this.board.isWithinBoard(positionToPlay)) {
                    GetScore getScore = new GetScore(this.board, this.counter);
                    int positionScore = getScore.getTotalScore(positionToPlay, this.counter);
                    if (positionScore > bestScore) {
                        bestScore = positionScore;
                        bestColumn = i;
                        System.out.println("The best move is in column " + i + " which scores " + bestScore + " points");
                    }
                }
            }
            this.playLocation = bestColumn;
        }

        if (this.playLocation == null) {
            Random rand = new Random();
            this.playLocation = rand.nextInt(board.getConfig().getWidth());
        }
    }

    public Integer getPlayLocation() {
        return this.playLocation;
    }

    public int getMinY(int x, Board board) {
        for (int y = 0; y < board.getConfig().getHeight(); y++) {
            Position minYPosition = new Position(x, y);
            if (!board.hasCounterAtPosition(minYPosition)) {
                return y;
            }
        }
        throw new RuntimeException("no y is vacant");
    }


}