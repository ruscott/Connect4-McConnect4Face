package com.thg.accelerator23.connectn.ai.ruglas.MCTS;

import com.thehutgroup.accelerator.connectn.player.Board;

import java.util.ArrayList;
import java.util.List;

public class Node {
private int visitCount;
    Board board;
    int nodeWins;

    int column;
    List<Node> children = new ArrayList<>();
    Node parent;

    public Node(Board board, int column) {
        this.board = board;
        this.column = column;
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
}
