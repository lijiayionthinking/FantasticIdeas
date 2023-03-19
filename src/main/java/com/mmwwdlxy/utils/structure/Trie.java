package com.mmwwdlxy.utils.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 前缀树
 */
public class Trie {

    private final TreeNode root;

    public Trie() {
        root = new TreeNode(' ');
    }

    public void add(String word) {
        add(root, word);
    }

    private void add(TreeNode treeNode, String word) {
        if (word == null || word.isEmpty()) {
            return;
        }

        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            TreeNode subNode = subExist(treeNode, aChar);
            if (subNode == null) {
                TreeNode tmpNode = new TreeNode(aChar);
                treeNode.add(tmpNode);
                treeNode = tmpNode;
            } else {
                treeNode = subNode;
            }
        }
        treeNode.terminate();
    }

    private TreeNode subExist(TreeNode treeNode, char c) {
        for (TreeNode node : treeNode.subList) {
            if (node.getValue() == c) {
                return node;
            }
        }
        return null;
    }

    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        TreeNode treeNode = root;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            TreeNode subNode = subExist(treeNode, aChar);
            if (subNode == null) {
                return false;
            } else {
                treeNode = subNode;
            }
        }
        return treeNode.isTerminated;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }

        TreeNode treeNode = root;
        char[] chars = prefix.toCharArray();
        for (char aChar : chars) {
            TreeNode subNode = subExist(treeNode, aChar);
            if (subNode == null) {
                return false;
            } else {
                treeNode = subNode;
            }
        }
        return true;
    }

    private static class TreeNode {
        private final char value;
        private boolean isTerminated = false;
        private final List<TreeNode> subList = new ArrayList<>();

        private TreeNode(char value) {
            this.value = value;
        }

        private void add(TreeNode treeNode) {
            subList.add(treeNode);
        }

        private char getValue() {
            return value;
        }

        private void terminate() {
            isTerminated = true;
        }

        private boolean isTerminated() {
            return isTerminated;
        }

        private List<TreeNode> getSubList() {
            return subList;
        }
    }
}
