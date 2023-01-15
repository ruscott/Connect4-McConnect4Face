package com.thg.accelerator23.connectn.ai.ruglas.MCTS;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;

import java.util.ArrayList;
import java.util.List;

public class Node {
private int visitCount=0;
   private Board board;
    private int nodeWins;

   private int column;
    List<Node> children = new ArrayList<>();
    Node parent;

    boolean state;

    Counter counter;

    public Node(Board board, int column, Counter counter) {
        this.board = board;
        this.column = column;
        this.counter = counter;
    }

    public Node getChildWithBestScore() {
        Node bestChild = children.get(0);
        for (int nodeIndex = 0; nodeIndex<children.size(); nodeIndex++){
            if (children.get(nodeIndex).getNodeWins() > bestChild.getNodeWins()) {
                bestChild = children.get(nodeIndex);
            }

        }
        return bestChild;
    }

    public void addChild(Node node){
        children.add(node);
    }

    public int getNodeWins(){return this.nodeWins;}

    public int getVisitCount() {
        return visitCount;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Board getBoard() {
        return board;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public void setVisitCount(){this.visitCount += 1;};

    public Counter getCounter() {
        return counter;
    }
}
