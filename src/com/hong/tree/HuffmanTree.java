package com.hong.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HuffmanTree {

    private Node root;
    private int order = 0;

    /**
     * 霍夫曼树的构建方法
     * @param arr：这里我们传入的数组的元素就是每个节点的权值
     */
    public void build(int[] arr){
        // 把每个数据都当成节点
        Node[] nodes = new Node[arr.length];
        for (int i=0; i<arr.length; i++){
            nodes[i] = new Node(arr[i]);
        }

        // 对权值进行排序
        bubbleSort(nodes);
        // 这里将数组转化为list，方便下面的删除操作
        List<Node> list = new ArrayList<>(Arrays.asList(nodes));

        while (list.size() > 1){
            // 取出权值最小的两个节点，取出后从list中删除
            Node left = list.get(0);
            Node right = list.get(1);
            list.remove(left);
            list.remove(right);

            // 将权值最小的两个节点组成一个树，根节点的权值为两节点的权值之和
            root = new Node(left.val + right.val);
            root.left = left;
            root.right = right;

            // 将新建的树即根节点，放进list中，并保持有序
            int insert = 0;
            for (int i=0; i<list.size(); i++){
                if (root.val <= list.get(i).val){
                    break;
                }
                insert ++;
            }
            list.add(insert, root);

        }
    }

    /**
     * 冒泡排序，对Node数组按照权值排序
     * @param arr
     */
    public void bubbleSort(Node[] arr){
        Node temp;
        for (int n=0; n<arr.length; n++){
            for (int i=1; i<arr.length-n; i++){
                if (arr[i-1].val > arr[i].val){
                    temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                }
            }
        }
    }

    /**
     * 中序遍历：打印整颗二叉树
     */
    public void show(){
        if (root == null){
            System.out.println("二叉树为空！！！");
            return;
        }

        showRecusion(root);

    }

    /**
     * 打印的递归体
     * @param node
     */
    private void showRecusion(Node node){
        // 一直向左递归，直到左叶子节点
        if (node.left != null){
            showRecusion(node.left);
        }
        // 然后开始回溯，打印父节点，接着打印右叶子节点，刚好符合二叉排序树的排序
        System.out.println(String.format("第一个%d数值为：%d", order, node.val));
        order ++;
        if (node.right != null){
            showRecusion(node.right);
        }
    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        huffmanTree.build(arr);
        huffmanTree.show();

    }
}
