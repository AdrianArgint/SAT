import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("graph.in"));
        FileWriter output = new FileWriter(new File("bexpr.out"));
        output.close();
        int noNodes;
        noNodes = input.nextInt();
        Graph graph = new Graph(noNodes);

        int firstNode, secondNode;
        firstNode = input.nextInt();
        while (firstNode != -1) {
            secondNode = input.nextInt();
            graph.addEdge(firstNode, secondNode);
            firstNode = input.nextInt();
        }

        //verificam posibilitatea de a avea ciclu(minim 2 muchii la fiecare nod)
        if(graph.checkHamiltonianCycle() == 1)
            return;
        //prima parte/
        //cuprinde x pt fiecare nod si a pt fiecare nod cu exceptia primului

        graph.printFirstPart();
        graph.printSecondPart();
        graph.printThirdPart();

        //graph.printGraph();
    }
}
//((a1-2|~x1-2)&(~a1-2|x1-2))&((a1-3|~x1-3)&(~a1-3|x1-3))&~a1-1&~a1-4
//((a2-2|~(((a1-3&x3-2)|(a1-4&x4-2))&~(a1-2)))&(~a2-2|(((a1-3&x3-2)|(a1-4&x4-2))&~(a1-2)))
//((a2-3|~(((a1-2&x2-3)|(a1-4&x4-3))&~(a1-3)))          &(~a2-3|(((a1-2&x2-3)|(a1-4&x4-3))&~(a1-3))))
//((a2-4|~(((a1-2&x2-4)|(a1-3&x3-4))&~(a1-4)))          &(~a2-4|(((a1-2&x2-4)|(a1-3&x3-4))&~(a1-4))))

//((a3-2|~(((a2-3&x3-2)|(a2-4&x4-2))&~(a1-2|a2-2)))     (~a3-2|(((a2-3&x3-2)|(a2-4&x4-2))&~(a1-2|a2-2))))
//((a3-3|~(((a2-2&x2-3)|(a2-4&x4-3))&~(a1-3|a2-3)))     (~a3-3|(((a2-2&x2-3)|(a2-4&x4-3))&~(a1-3|a2-3))))
//((a3-4|~(((a2-2&x2-4)|(a2-3&x3-4))&~(a1-4|a2-4)))     (~a3-4|(((a2-2&x2-4)|(a2-3&x3-4))&~(a1-4|a2-4))))
//        ((a3-2|~(((a2-3&x3-2)|(a2-5&x5-2))&~(a1-2|a2-2)))&(~a3-2|(((a2-3&x3-2)|(a2-5&x5-2))&~(a1-2|a2-2))))&