package com.example.foodapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

class Node {
    Food item;
    int height;
    Node left, right;

    Node(Food foodItem) {
        this.item = foodItem;
        height = 1;
    }
}

class AVLTree {

    Node root;

    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node insert(Node node, Food item) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new Node(item));

        if (item.getTitle().compareTo(node.item.getTitle()) < 0)
            node.left = insert(node.left, item);
        else if (item.getTitle().compareTo(node.item.getTitle()) > 0)
            node.right = insert(node.right, item);
        else // Duplicate keys not allowed
            return node;

        node.height = 1 + max(height(node.left),
                height(node.right));

        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && item.getTitle().compareTo(node.left.item.getTitle()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && item.getTitle().compareTo(node.right.item.getTitle()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && item.getTitle().compareTo(node.left.item.getTitle()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && item.getTitle().compareTo(node.right.item.getTitle()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void searchAllOccurrences(Node root, String key, ArrayList<Food> result) {
        if(root == null)
            return;

        if(root.item.getTitle().toUpperCase().contains(key.toUpperCase()))
        {
            result.add(root.item);
        }

        searchAllOccurrences(root.left,key,result);
        searchAllOccurrences(root.right,key,result);
    }

    public void preOrder(Node root)
    {
        if(root == null) return;

        Log.v("MyActivity",root.item.getTitle());
//        System.out.println(root.item.getTitle());
        preOrder(root.left);
        preOrder(root.right);
    }
}
