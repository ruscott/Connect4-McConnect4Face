package com.thg.accelerator23.connectn.ai.ruglas.miniMax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Player;

public class MiniMaxScoringAlphaBetaAI extends Player {
    int turnCounter=0;
    public MiniMaxScoringAlphaBetaAI(Counter counter) {
        super(counter, com.thg.accelerator23.connectn.ai.ruglas.miniMax.MiniMaxScoringAlphaBetaAI.class.getName());
    }

    @Override
    public int makeMove(Board board) {
        int column;
        MiniMaxScoringAlphaBeta miniMaxScoringAlphaBeta = new MiniMaxScoringAlphaBeta(this.getCounter());
        try {
            miniMaxScoringAlphaBeta.miniMaxMoveAlphaBeta(board, true, 2, 0, -1000000, 1000000, turnCounter);
        } catch (InvalidMoveException e) {
        }
        System.out.println("MiniMaxAlphaBeta");
        System.out.println(miniMaxScoringAlphaBeta.getBestColumn());
        turnCounter += 1;
        return miniMaxScoringAlphaBeta.getBestColumn();
    }

}

