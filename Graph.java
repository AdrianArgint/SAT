import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {
    private int noNodes;
    private ArrayList<Node> adjList;

    public Graph(int noNodes) {
        this.noNodes = noNodes;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < noNodes; i++) {
            this.adjList.add(new Node(i));
        }
    }


    public int getNoNodes() {
        return noNodes;
    }

    public ArrayList<Node> getAdjList() {
        return adjList;
    }

    public Node getNode(int nodeNo) {
        return this.adjList.get(nodeNo - 1);
    }

    public int checkHamiltonianCycle() throws IOException {
        int i;
        for(i = 0; i < this.noNodes; i++){
            if(this.adjList.get(i).getAdjNodes().size() == 1)
               {
                FileWriter output = new FileWriter(new File("bexpr.out"), true);
                output.write("x" + (i + 1) + "-" + this.adjList.get(i).getAdjNodes().get(0) + "&");
                output.write("~x" + (i + 1) + "-" + this.adjList.get(i).getAdjNodes().get(0));
                output.close();
                return 1;
               }
        }
        return 0;
    }

    public void addEdge(int firstNode, int secondNode) {
        this.adjList.get(firstNode - 1).addAdj(secondNode - 1);
        this.adjList.get(secondNode - 1).addAdj(firstNode - 1);
    }


    public void printFirstPart() throws IOException {
        FileWriter output = new FileWriter(new File("bexpr.out"), true);
        //nodul 1 separat
        ArrayList<Integer> adj = this.adjList.get(0).getAdjNodes();
        if (this.adjList.get(0).getAdjNo() == 2) {
//            System.out.print("((x1-" + (adj.get(0) + 1));
//            System.out.print("&x1-" + (adj.get(1) + 1) + "))");
            output.write("((x1-" + (adj.get(0) + 1));
            output.write("&x1-" + (adj.get(1) + 1) + "))");
        } else {
//            System.out.print("(");
            output.write("(");
            int noNodesAdj = this.adjList.get(0).getAdjNo();
            for (int i = 0; i < noNodesAdj; i++) {
                for (int j = i + 1; j < noNodesAdj; j++) {

//                    System.out.print("(x1-" + adj.get(i) + " ");
//                    System.out.print("(x1-" + adj.get(j) + " ");
                    output.write("(x1-" + (adj.get(i) + 1) + "&");
                    output.write("x1-" + (adj.get(j) + 1) + "&");
                    for (int k = 0; k < noNodesAdj; k++) {
                        if (k != i && k != j) {
//                            System.out.print("~(x1-" + adj.get(k) + " ");
                            output.write("~x1-" + (adj.get(k) + 1));
                            if (3 + k < noNodesAdj)
                                output.write("&");
                        }
                    }
                    output.write(")");

                    if (i != noNodesAdj - 2)
                        output.write("|");
                }
            }
//            System.out.print(")");
            output.write(")");
        }


        //restul nodurilor
        for (int i = 1; i < this.noNodes; i++) {
            adj = this.adjList.get(i).getAdjNodes();
            if (this.adjList.get(i).getAdjNo() == 2) {
//                System.out.print("&((x" + (i + 1) + "-" + (adj.get(0) + 1));
//                System.out.print("&x" + (i + 1) + "-" + (adj.get(1) + 1) + "))");
                output.write("&((x" + (i + 1) + "-" + (adj.get(0) + 1));
                output.write("&x" + (i + 1) + "-" + (adj.get(1) + 1) + "))");
            } else {
                int noNodesAdj = this.adjList.get(i).getAdjNo();
//                System.out.print("&(");
                output.write("&(");
                int count = 0;
                for (int p = 0; p < noNodesAdj; p++) {
                    for (int j = p + 1; j < noNodesAdj; j++) {
                        if (count == 1) output.write("|");
                        else count = 1;
//                        System.out.print("(x" + (i + 1) + "-" + (adj.get(p) + 1));
//                        System.out.print("&x" + (i + 1) + "-" + (adj.get(j) + 1));
                        output.write("(x" + (i + 1) + "-" + (adj.get(p) + 1));
                        output.write("&x" + (i + 1) + "-" + (adj.get(j) + 1));
                        for (int k = 0; k < noNodesAdj; k++) {
                            if (k != p && k != j)
//                                System.out.print("&~x" + (i + 1) + "-" + (adj.get(k) + 1));
                                output.write("&~x" + (i + 1) + "-" + (adj.get(k) + 1));
                        }
//                        System.out.print(")");
                        output.write(")");
                    }
                }

//                System.out.print(")");
                output.write(")");
            }
            //aici cu a-uri
//            System.out.print("&(");
            output.write("&(");
            for (int j = 1; j <= this.noNodes / 2 + 1; j++) {
//                System.out.print("a" + j + "-" + (i + 1));
                output.write("a" + j + "-" + (i + 1));
                if (j == this.noNodes / 2 + 1) continue;
//                System.out.print("|");
                output.write("|");
            }
            output.write(")");
//            System.out.print(")");

        }
        output.close();
    }

    public void printSecondPart() throws IOException {
        FileWriter output = new FileWriter(new File("bexpr.out"), true);

        int count = 0;
//        System.out.print("&");
        output.write("&");
        for (int i = 0; i < this.noNodes; i++) {
            ArrayList<Integer> adj = this.adjList.get(i).getAdjNodes();
            for (int j = 0; j < adj.size(); j++) {
                if (adj.get(j) > i) {
                    if (count == 1) output.write("&");
                    else count = 1;
//                    System.out.print("((x" + (i + 1) + "-" + (adj.get(j) + 1) + "|");
//                    System.out.print("~x" + (adj.get(j) + 1) + "-" + (i + 1) + ")");
//                    System.out.print("&");
//                    System.out.print("(~x" + (i + 1) + "-" + (adj.get(j) + 1) + "|");
//                    System.out.print("x" + (adj.get(j) + 1) + "-" + (i + 1) + "))");
                    output.write("((x" + (i + 1) + "-" + (adj.get(j) + 1) + "|");
                    output.write("~x" + (adj.get(j) + 1) + "-" + (i + 1) + ")");
                    output.write("&");
                    output.write("(~x" + (i + 1) + "-" + (adj.get(j) + 1) + "|");
                    output.write("x" + (adj.get(j) + 1) + "-" + (i + 1) + "))");
                }
            }
        }
        output.close();
    }

    public void printThirdPart() throws IOException {
        FileWriter output = new FileWriter(new File("bexpr.out"), true);

        //pt nodul 1
        output.write("&");
//        System.out.print("&");
        ArrayList<Integer> adj = this.adjList.get(0).getAdjNodes();
        for (int i = 0; i < adj.size(); i++) {
//            System.out.print("((a1-" + (adj.get(i) + 1) + "|~x1-" + (adj.get(i) + 1) + ")");
//            System.out.print("&(~a1-" + (adj.get(i) + 1) + "|x1-" + (adj.get(i) + 1) + "))");
//            System.out.print("&");
            output.write("((a1-" + (adj.get(i) + 1) + "|~x1-" + (adj.get(i) + 1) + ")");
            output.write("&(~a1-" + (adj.get(i) + 1) + "|x1-" + (adj.get(i) + 1) + "))");
            output.write("&");
        }
        for (int i = 0; i < this.noNodes; i++) {
            if (!adj.contains(i)) {
//                System.out.print("~a1-" + (i + 1) + "&");
                output.write("~a1-" + (i + 1) + "&");
            }
        }
        //pt restul nodurilor
        for (int i = 2; i <= this.noNodes / 2 + 1; i++) {//lungime drum
            StringBuilder string = new StringBuilder();
            for (int j = 1; j < noNodes; j++) {//nod curent
                adj = this.adjList.get(j).getAdjNodes();
                String string1 = "";
                String string2 = "";
                string1 += "((a" + i + "-" + (j + 1) + "|~((";
                string2 += "(~a" + i + "-" + (j + 1) + "|((";
                int count = 0;
                for (int k = 0; k < adj.size(); k++) {
                    if (adj.get(k) == 0) continue;
                    string1 += "(a" + (i - 1) + "-" + (adj.get(k) + 1) + "&x" + (adj.get(k) + 1) + "-" + (j + 1) + ")";
                    string2 += "(a" + (i - 1) + "-" + (adj.get(k) + 1) + "&x" + (adj.get(k) + 1) + "-" + (j + 1) + ")";
                    if (adj.size() - 1 != k && adj.get(adj.size() - 1) != 1) {
                        string1 += "|";
                        string2 += "|";
                    }
                }
                string1 += ")&~(";
                string2 += ")&~(";
                for (int k = 1; k < i; k++) {
                    string1 += "a" + k + "-" + (j + 1);
                    string2 += "a" + k + "-" + (j + 1);
                    if (k != i - 1) {
                        string1 += "|";
                        string2 += "|";
                    }
                }
                string1 += ")))";
                string2 += "))))";

                string.append(string1).append("&").append(string2).append("&");
            }
//            if(i != this.noNodes /2 + 1) string.append("&");
            if (i == this.noNodes / 2 + 1)
                output.write((String) string.subSequence(0, string.length() - 1));
                //                System.out.print(string.subSequence(0, string.length() - 1));
            else
                output.write(String.valueOf(string));

        }
        output.close();
    }
}
