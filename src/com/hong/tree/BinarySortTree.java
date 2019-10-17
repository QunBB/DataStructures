package com.hong.tree;

/**
 * 二叉排序树实现代码
 */
public class BinarySortTree {

    private Node root = null;
    private int order = 1;

    /**
     * 节点添加
     * @param node
     */
    public void add(Node node){
        // 第一次添加元素时
        if (root == null){
            root = node;
            return;
        }

        Node temp = root;
        while (temp != null){
            if (node.val <= temp.val){
                if (temp.left == null){
                    temp.left = node;
                    break;
                } else{
                    temp = temp.left;
                }
            } else{
                if (temp.right == null){
                    temp.right = node;
                    break;
                } else{
                    temp = temp.right;
                }
            }
        }
    }

    /**
     * 打印整颗二叉树
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

    /**
     * 删除节点
     * @param node
     */
    public void del(Node node){
        Node parent = root;
        Node temp = null;
        if (root.val == node.val){ // 需要先判断删除的节点是否为根节点
            if (root.left == null && root.right == null){
                root = null;
            } else if (root.left != null && root.right != null){
                Node left = root.left;
                root = root.right;
                temp = root.left;
                while (temp.left != null){
                    temp = temp.left;
                }
                temp.left = left;
            }
            return;
        } else if (root.val > node.val){
            temp = root.left;
        } else {
            temp = root.right;
        }
        // 迭代找到想删除的节点
        while (temp != null){
            if (temp.val == node.val){
                break;
            } else if (temp.val > node.val){
                parent = temp;
                temp = temp.left;
            } else{
                parent = temp;
                temp = temp.right;
            }
        }
        if (temp == null){
            System.out.println("该节点不存在，无法删除");
            return;
        }
        // 如果要删除的为叶子节点，则直接删除即可
        if (temp.left == null && temp.right == null){
            if (parent.left == temp){
                parent.left = null;
            } else{
                parent.right = null;
            }
        } else if (temp.left != null && temp.right != null) {
            // 删除节点有两个子树
            // 将右子树代替要删除节点的位置，然后左子树放在右子树的最底层左子树
            // 因为整颗左子树都是比右子树小的，所以左子树需要移动到右子树最小的子树上，即右子树上最左的子树
            if (parent.left == temp){
                parent.left = temp.right;
            } else{
                parent.right = temp.right;
            }
            Node left = temp.left;
            temp = temp.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            temp.left = left;
        } else{
            // 删除的节点只有一颗子树
            // 直接将子树替换其位置即可
            if (temp.right != null){
                if (parent.left == temp){
                    parent.left = temp.right;
                } else{
                    parent.left = temp.right;
                }
            } else{
                if (parent.left == temp){
                    parent.left = temp.left;
                } else{
                    parent.left = temp.left;
                }
            }
        }
    }


    /**
     * 主方法：测试代码
     * @param args
     */
    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int a: arr){
            tree.add(new Node(a));
        }
//        tree.del(new Node(2));
        tree.del(new Node(10));
        tree.show();
    }


}



/**
 * 节点类
 */
class Node{
    public int val;
    public Node left = null;
    public Node right = null;

    public Node(int val){
        this.val = val;
    }
}
