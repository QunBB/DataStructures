package com.hong.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试一把图是否创建ok
        int n = 8;  //结点的个数
//        String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};

        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
//		graph.insertEdge(0, 1, 1); // A-B
//		graph.insertEdge(0, 2, 1); //
//		graph.insertEdge(1, 2, 1); //
//		graph.insertEdge(1, 3, 1); //
//		graph.insertEdge(1, 4, 1); //

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);



        //显示一把邻结矩阵
        graph.showGraph();

        //测试一把，我们的dfs遍历是否ok
        System.out.println("深度遍历");
        graph.deepTraversing();
//        graph.dfs(); // A->B->C->D->E [1->2->4->8->5->3->6->7]
		System.out.println();
        System.out.println("广度优先!");
        graph.breadthTraversing();
//        graph.bfs(); // A->B->C->D-E [1->2->3->4->5->6->7->8]


    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }

    /**
     * 深度遍历主方法
     */
    public void deepTraversing(){
        isVisited = new boolean[vertexList.size()];
        for (int i=0; i<vertexList.size(); i++){
            if (!isVisited[i]){
                deepTraversing(i);
            }
        }
    }

    /**
     * 以vertex为起点，进行深度遍历递归
     * @param vertex
     */
    private void deepTraversing(int vertex){
        System.out.print(vertexList.get(vertex) + "->");
        isVisited[vertex] = true;

        // 获取vertex的第一个邻接节点
        int next = getNextEdges(vertex, 0);
        while (next != -1){
            if (!isVisited[next]){
                deepTraversing(next);
            } else{
                // 如果该邻接节点已经被访问过，则获取下一个邻接节点
                next = getNextEdges(vertex, next+1);
            }
        }

    }

    /**
     * 广度遍历方法
     */
    private void breadthTraversing(){
        isVisited = new boolean[vertexList.size()];
        int w;
        for (int v=0; v<vertexList.size(); v++){
            // 打印顶点v的所有邻接节点(未被访问过的)
            if (!isVisited[v]){
                System.out.print(vertexList.get(v) + "->");
                isVisited[v] = true;
            }
            w = getNextEdges(v, 0); // 获取v的第一个邻接节点
            while (w != -1){
                if (!isVisited[w]){
                    System.out.print(vertexList.get(w) + "->");
                    isVisited[w] = true;
                }
                w = getNextEdges(v, w+1);
            }
        }
    }

    /**
     * 获取vertex从初始位置为start的下一个邻接节点
     * @param vertex
     * @param start
     * @return
     */
    private int getNextEdges(int vertex, int start){
        for (int j=start; j<vertexList.size(); j++){
            if (edges[vertex][j] == 1){
                return j;
            }
        }
        return -1;
    }


    //图中常用的方法
    //显示图对应的矩阵
    public void showGraph() {
        for(int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    /**
     *
     * @param v1 表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
