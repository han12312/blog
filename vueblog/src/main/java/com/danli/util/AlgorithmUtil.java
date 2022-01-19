package com.danli.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanxiaofei
 */
public class AlgorithmUtil {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        //1.反转链表(迭代、递归)
        reverseList();
        //2.二叉树前中后序遍历(递归)
//        traversal();
        //1.数列描述
//        sequenceDescription();
        //2.整数最小和(例:bb12-34aa)
//        sumInteger();
    }
    public static void reverseList(){
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(",");
        ListNode node = new ListNode(Integer.parseInt(str[0]));
        ListNode head = node;
        for (int i = 1; i < str.length; i++) {
            node.next = new ListNode(Integer.parseInt(str[i]));
            node = node.next;
        }
        ListNode result = reverseListD(head);
        while (result != null){
            System.out.print(result.val);
            result = result.next;
        }
        System.out.println();
        ListNode result2 = reverseListG(reverseListD(node));
        while (result2 != null){
            System.out.print(result2.val);
            result2 = result2.next;
        }
        System.out.println();
    }

    public static ListNode reverseListD(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseListG(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode result = reverseListG(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

    public static void traversal(){
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(",");
        int[] a = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            a[i] = Integer.parseInt(str[i]);
        }
        TreeNode node = createTree(a, 0);
        List<Integer> result = new ArrayList<>();
        preorder(node, result);
        System.out.println("pre-->"+result);
        List<Integer> result2 = new ArrayList<>();
        inorder(node, result2);
        System.out.println("in-->"+result2);
        List<Integer> result3 = new ArrayList<>();
        postorder(node, result3);
        System.out.println("post-->"+result3);
    }

    public static TreeNode createTree(int[] arr, int i){
        if (i >= arr.length){
            return null;
        }
        TreeNode root = new TreeNode(arr[i]);
        root.left = createTree(arr, 2*i+1);
        root.right = createTree(arr, 2*i+2);
        return root;
    }

    public static void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public static void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    public static void sequenceDescription(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = "1";
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = null;
            int num = 0;
            boolean flag = true;
            String temp = "";
            while (flag){
                char c = str.charAt(num);
                if(num + 1 == str.length()){
                    if(map != null && map.containsKey(c)){
                        int i1 = map.get(c) + 1;
                        str = temp +i1 + c;
                    }else if (map == null){
                        str = temp + "1" + c;
                    }else {
                        str = temp + map.get(str.charAt(num - 1)) + str.charAt(num - 1) + "1" + c;
                    }
                    flag = false;
                    continue;
                }
                if (map == null){
                    map = new HashMap<>();
                    map.put(c, 1);
                    num++;
                    continue;
                }
                if (map.containsKey(c)){
                    map.merge(c, 1, Integer::sum);
                    num++;
                }else {
                    temp = temp + map.get(str.charAt(num - 1)) + str.charAt(num - 1);
                    map = null;
                }
            }
        }
        System.out.println(str);
    }

    public static void sumInteger(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        List<Long> result = new ArrayList<>();
        Matcher matcher = Pattern.compile("(-?[\\d]+)").matcher(str);
        while (matcher.find()){
            result.add(Long.valueOf(matcher.group(0)));
        }
        int count = 0;
        for (Long num : result){
            if (num > 0){
                while (num > 0){
                    count += num%10;
                    num = num/10;
                }
            }else {
                count += num;
            }
        }
        System.out.println(count);
    }

}
