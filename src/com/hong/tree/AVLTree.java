package com.hong.tree;

public class AVLTree {
    private Node root = null;
    private int order = 0;

    /**
     * 左旋转
     * 1.新建一个节点，并根节点的值赋值给新节点
     * 2.新节点的左子树设置为根节点的左子树
     * 3.新节点的右子树设置为根节点的右子树的左子树
     * 4.将根节点的值修改为根节点的右子树的值
     * 5.将新节点设置为根节点的左子树
     * 6.将根子树的右子树的右子树设置为根节点的右子树
     */
    public void leftRotate(Node root){
        Node newNode = new Node(root.val);
        newNode.left = root.left;
        newNode.right = root.right.left;
        root.val = root.right.val;
        root.left = newNode;
        root.right = root.right.right;
    }

    /**
     * 右旋转
     * 1.新建一个节点，并将根节点的值赋值给新节点
     * 2.新节点的右子树设置为根节点的右子树
     * 3.新节点的左子树设置为根节点的左子树的右子树
     * 4.将根节点的值修改为根节点的左子树的值
     * 5.将根节点的右子树设置为新节点
     * 6.将根节点的左子树设置为根节点的左子树的左子树
     * @param root
     */
    public void rightRotate(Node root){
        Node newNode = new Node(root.val);
        newNode.right = root.right;
        newNode.left = root.left.right;
        root.val = root.left.val;
        root.right = newNode;
        root.left = root.left.left;
    }

    /**
     * 获取左子树的高度
     * @return
//     */
    public int leftHeight(){
        if (root.left == null){
            return 0;
        } else{
            return getHeight(root.left);
        }
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight(){
        if (root.right == null){
            return 0;
        } else{
            return getHeight(root.right);
        }
    }

    /**
     * 返回以当前节点为根节点的树的高度
     * @param root
     * @return
     */
    private int getHeight(Node root){
        return Math.max(root.left == null ? 0 : getHeight(root.left),
                root.right == null ? 0 : getHeight(root.right)) + 1;
    }

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

//        // 当右子树的高度大于左子树的高度时，需要进行左旋转
        if (rightHeight() - leftHeight() > 1){
            if (getHeight(root.right.left) - getHeight(root.right.right) > 0){
                rightRotate(root.right);
            }
            leftRotate(root);
        }
//        // 当左子树的高度大于右子树的高度时，需要进行右旋转
        if (leftHeight() - rightHeight() > 1){
            if (getHeight(root.left.right) - getHeight(root.left.left) > 0){
                leftRotate(root.left);
            }
            rightRotate(root);
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

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8}; // 单左旋转
//        int[] arr = {10,12,8,9,7,6}; // 单右旋转
        int[] arr = {10,11,7,6,8,9}; // 双旋转：先左后右
        AVLTree avl = new AVLTree();
        for (int a: arr){
            avl.add(new Node(a));
        }
        avl.show();
        System.out.println(avl.leftHeight());
        System.out.println(avl.rightHeight());
    }
}
