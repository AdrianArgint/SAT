import java.util.ArrayList;
import java.util.Collections;

public class Node {
    private int number; //node`s number
    private int adjNo;
    private ArrayList<Integer> adjNodes;

    public Node(int number) {
        this.number = number;
        this.adjNo = 0;
        this.adjNodes = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Integer> getAdjNodes() {
        return adjNodes;
    }

    public int getAdjNo() {
        return adjNo;
    }

    public void addAdj(int nodeNo) {
        this.adjNodes.add(nodeNo);
        this.adjNo++;
        Collections.sort(this.adjNodes);
    }
}
