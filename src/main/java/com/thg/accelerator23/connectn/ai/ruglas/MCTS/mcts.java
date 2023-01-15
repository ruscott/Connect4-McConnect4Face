package com.thg.accelerator23.connectn.ai.ruglas.MCTS;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thg.accelerator23.connectn.ai.ruglas.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ruglas.analysis.GameState;

import java.util.Random;

public class mcts {
    private Board board;
    private Counter counter;

    private int computationsCounter;
    private int computations;

    Board newBoard;

    BoardAnalyser boardAnalyser;


    public mcts(Board board, Counter counter, int computations) {
        this.board = board;
        this.counter = counter;
        this.computations = computations;
        this.boardAnalyser = new BoardAnalyser(board.getConfig());
    }

    public void mctsBestMove() {
        computationsCounter = 0;
        Node tree = new Node(board, 0);

        while (computationsCounter < computations) {
            computationsCounter++;

            Node nextNode = selectNextNode(tree);

            //If node is not game over
            //Expand board


            //Simulate on next node


        }
    }

    private Node selectNextNode(Node tree) {
            Node node = tree;
            while (node.children.size() != 0) {
                if (node.children.size() != 0) {
                node = UCTcalculator.findBestNodeWithUCT(node);
            }}
            return node;
}

private Node expandNodeRando(Node node) {
    Node result = node;
    Board board = node.getBoard();

    for (int column = 0; column < node.getBoard().getConfig().getWidth(); column++) {
        //make move if available
        try {
            Board boardWithMove = new Board(board, column, node.getCounter().getOther());
            Node child = new Node(boardWithMove, column, node.getCounter().getOther());
            child.setParent(node);
            result = child;
        } catch (InvalidMoveException e) {
        }
    }
    return result;}

    private int simulatePlay(Node promisingNode){
        newBoard = board;
        //While game in progress get random next move and make new node
        promisingNode.parent.setVisitCount();
        GameState gameState = boardAnalyser.calculateGameState(promisingNode.getBoard());
        int column = 0;
        Counter currentPlayer = promisingNode.getCounter();
        while (!gameState.isEnd()){
                try{
                    Random rand = new Random();
                    column = rand.nextInt(newBoard.getConfig().getWidth());
                    newBoard = new Board(newBoard, column, currentPlayer);}
                catch (InvalidMoveException e){
                }
                Node child = new Node(newBoard, column, currentPlayer);
                child.setParent(promisingNode);
                promisingNode.addChild(child);
                gameState = boardAnalyser.calculateGameState(newBoard);
                if (gameState.isEnd()) {
                    if (gameState.getWinner() == counter){return 1;}
                    else if (gameState.isDraw()) {return 0;}
                    else return -1;
                }
            currentPlayer = currentPlayer.getOther();
        }


        return 0;
    }
    private void backProp(Counter counter, Node selectedNode) {
        while (selectedNode != null) {
            selectedNode.setVisitCount();
        }
    }

}






//        private void backPropagation(int playerNumber, Node selected) {
//            Node node = selected;
//
//            while (node != null) { // look for the root
//                node.visits++;
//                if (node.board.getLatestMovePlayer() == playerNumber) {
//                    node.score++;
//                }
//
//                node = node.parent;
//            }
//        }

//
//        public Board doMcts() {
//            System.out.println("MCTS working.");
//            Instant start = Instant.now();
//
//            long counter = 0l;
//
//            Node tree = new Node(board);
//
//            while (counter < computations) {
//                counter++;
//
//                //SELECT
//                Node promisingNode = selectPromisingNode(tree);

//                //EXPAND
//                Node selected = promisingNode;
//                if (selected.board.getStatus() == Board.GAME_IN_PROGRESS) {
//                    selected = expandNodeAndReturnRandom(promisingNode);
//                }
//
//                //SIMULATE
//                int playoutResult = simulateLightPlayout(selected);
//
//                //PROPAGATE
//                backPropagation(playoutResult, selected);
//            }
//
//            Node best = tree.getChildWithMaxScore();
//
//            Instant end = Instant.now();
//            long milis = end.toEpochMilli() - start.toEpochMilli();
//
//            System.out.println("Did " + counter + " expansions/simulations within " + milis + " milis");
//            System.out.println("Best move scored " + best.score + " and was visited " + best.visits + " times");
//
//            return best.board;
//        }
//
//        // if node is already a leaf, return the leaf

//            int random = Board.RANDOM_GENERATOR.nextInt(node.children.size());
//
//            return node.children.get(random);
//        }
//

