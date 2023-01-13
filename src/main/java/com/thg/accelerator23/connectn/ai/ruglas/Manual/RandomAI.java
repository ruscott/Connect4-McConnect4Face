package com.thg.accelerator23.connectn.ai.ruglas.Manual;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;

import java.util.Random;

public class RandomAI extends Player {
    public RandomAI(Counter counter) {
        super(counter, com.thg.accelerator23.connectn.ai.ruglas.Manual.RandomAI.class.getName());
    }

    @Override
    public int makeMove(Board board) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(board.getConfig().getWidth());

//      neaten the code above maybe into a while loop
            System.out.println("Random");
            TrashTalk.talkTrash();
            return randomNumber;
        }

}
