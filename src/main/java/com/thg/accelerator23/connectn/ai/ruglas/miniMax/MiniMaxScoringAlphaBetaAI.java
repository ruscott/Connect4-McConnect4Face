package com.thg.accelerator23.connectn.ai.ruglas.miniMax;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thg.accelerator23.connectn.ai.ruglas.Manual.RandomAI;

public class MiniMaxScoringAlphaBetaAI extends Player {
    int turnCounter=0;
    public MiniMaxScoringAlphaBetaAI(Counter counter) {
        super(counter, com.thg.accelerator23.connectn.ai.ruglas.miniMax.MiniMaxScoringAlphaBetaAI.class.getName());
    }

    @Override
    public int makeMove(Board board) {
        RandomAI randomAI = new RandomAI(getCounter());
        while (turnCounter < 20){
            turnCounter += 1;
            return randomAI.makeMove(board);
        }
        MiniMaxScoringAlphaBeta miniMaxScoringAlphaBeta = new MiniMaxScoringAlphaBeta(this.getCounter());
        try {
            miniMaxScoringAlphaBeta.miniMaxMoveAlphaBeta(board, true, 8, 0, -1000000, 1000000, turnCounter);
        } catch (InvalidMoveException e) {
        }
        System.out.println("MiniMaxAlphaBeta");
        System.out.println(miniMaxScoringAlphaBeta.getBestColumn());
        turnCounter += 1;
        return miniMaxScoringAlphaBeta.getBestColumn();
    }

}

