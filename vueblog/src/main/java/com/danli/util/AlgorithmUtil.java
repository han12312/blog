package com.danli.util;

import java.util.*;
import java.util.regex.*;

/**
 * @author hanxiaofei
 */
public class AlgorithmUtil {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
//        9.两数之和(输入：2,7,11,15\n 9 输出：[0,1])
        twoSum();
//        8.反转链表(迭代、递归 输入：1,2,3,4,5 输出：5,4,3,2,1)
//        reverseList();
//        7.二叉树中序遍历(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
//        traversal();
//        6.数列描述(输入：4 输出：111221)
//        sequenceDescription();
//        5.整数最小和(输入:bb12-34aa 输出：31)
//        sumInteger();
//        4.人数最多站点(输入:3\n 1 3 2 4 1 4 输出：2)
//        station();
//        3.报文回路(输入:5\n 1 2\n 2 3\n 3 2\n 1 2\n 2 1 输出：True)
//        messageLoop();
//        2.图形周长(输入:2\n 1 1 3 2 2 2 3 2 4 3 2 3 3 3 4 4 1 4 2 4 3 4 4 5 2 5 3\n
//                     2 3 7 3 8 4 5 4 6 4 7 4 8 5 4 5 5 5 6 5 7 5 8 6 4 6 5 6 6 6 7 6 8 7 4 7 5 7 6 7 7 7 8
//                  输出：18 20)
//        perimeter();
//        1.运输时间(输入:2 11\n 3\n 2 输出 5.5)
//        transportTime();
    }

    public static void twoSum(){
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = sc.nextInt();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    System.out.println(Arrays.toString(new int[]{i, j}));
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                System.out.println(Arrays.toString(new int[]{map.get(target - nums[i]), i}));
                return;
            }
            map.put(nums[i], i);
        }
        System.out.println(Arrays.toString(new int[0]));
    }

    public static void reverseList() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ListNode node = new ListNode(a[0]);
        ListNode head = node;
        for (int i = 1; i < a.length; i++) {
            node.next = new ListNode(a[i]);
            node = node.next;
        }
        ListNode result = reverseListD(head);
        StringBuilder sb = new StringBuilder();
        while (result != null) {
            sb.append(result.val).append(",");
            result = result.next;
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
        result = reverseListG(reverseListD(node));
        sb = new StringBuilder();
        while (result != null) {
            sb.append(result.val).append(",");
            result = result.next;
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static ListNode reverseListD(ListNode node) {
        ListNode head = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = head;
            head = node;
            node = next;
        }
        return head;
    }

    public static ListNode reverseListG(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode result = reverseListG(node.next);
        node.next.next = node;
        node.next = null;
        return result;
    }

    public static void traversal() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        TreeNode node = createTree(a, 0);
        List<Integer> result = new ArrayList<>();
        inorder(node, result);
        System.out.println(result);
    }

    public static TreeNode createTree(int[] a, int i) {
        if (i >= a.length) {
            return null;
        }
        TreeNode node = new TreeNode(a[i]);
        node.left = createTree(a, 2 * i + 1);
        node.right = createTree(a, 2 * i + 2);
        return node;
    }

    public static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    public static void sequenceDescription() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = "1";
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = null;
            int num = 0;
            boolean flag = true;
            String temp = "";
            while (flag) {
                char c = str.charAt(num);
                if (num + 1 == str.length()) {
                    if (map == null) {
                        str = temp + "1" + c;
                    } else if (map.containsKey(c)) {
                        int a = map.get(c) + 1;
                        str = temp + a + c;
                    } else {
                        str = temp + map.get(str.charAt(num - 1)) + str.charAt(num - 1) + "1" + c;
                    }
                    flag = false;
                    continue;
                }
                if (map == null) {
                    map = new HashMap<>();
                    map.put(c, 1);
                    num++;
                } else if (map.containsKey(c)) {
                    map.merge(c, 1, Integer::sum);
                    num++;
                } else {
                    temp = temp + map.get(str.charAt(num - 1)) + str.charAt(num - 1);
                    map = null;
                }
            }
        }
        System.out.println(str);
    }

    public static void sumInteger() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Long> result = new ArrayList<>();
        Matcher matcher = Pattern.compile("(-?[\\d]+)").matcher(str);
        while (matcher.find()) {
            result.add(Long.valueOf(matcher.group(0)));
        }
        int count = 0;
        for (Long num : result) {
            if (num > 0) {
                while (num > 0) {
                    count += num % 10;
                    num = num / 10;
                }
            } else {
                count += num;
            }
        }
        System.out.println(count);
    }

    public static void station() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                list.add(sc.nextInt());
            }
        }
        List<Integer> temp = new ArrayList<>(list);
        Collections.sort(temp);
        int max = temp.get(temp.size() - 1);
        int[] result = new int[max + 1];
        for (int i = 0; i < list.size(); i = i + 2) {
            for (int j = list.get(i); j < list.get(i + 1); j++) {
                result[j] = result[j] + 1;
            }
        }
        int maxVal = 0;
        int maxValIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > maxVal) {
                maxVal = result[i];
                maxValIndex = i;
            }
        }
        System.out.println(maxValIndex);
    }

    public static void messageLoop() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    list.add(sc.nextInt());
                } else {
                    list2.add(sc.nextInt());
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            int b = list2.get(i);
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == b && list2.get(j) == a) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("False");
                return;
            }
        }
        System.out.println("True");
    }

    public static void perimeter() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        char[][] chars = new char[64][64];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < a.length; j = j + 2) {
                chars[a[j]][a[j + 1]] = (char) a[0];
            }
            int mask = mask(chars, (char) a[0]);
            int total = find(chars, (char) a[0]);
            total = 4 * total - mask * 2;
            sb.append(total).append(" ");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static int mask(char[][] chars, char ch) {
        int count;
        int count2;
        int total = 0;
        for (int i = 0; i < 64; i++) {
            count = 0;
            count2 = 0;
            for (int j = 0; j < 64; j++) {
                if (chars[i][j] == ch) {
                    count++;
                }
                if (chars[j][i] == ch) {
                    count2++;
                }
            }
            if (count > 1) {
                total += count - 1;
            }
            if (count2 > 1) {
                total += count2 - 1;
            }
        }
        return total;
    }

    public static int find(char[][] chars, char ch) {
        int count;
        int total = 0;
        for (int i = 0; i < 64; i++) {
            count = 0;
            for (int j = 0; j < 64; j++) {
                if (chars[i][j] == ch) {
                    chars[i][j] = '0';
                    count++;
                }
            }
            total += count;
        }
        return total;
    }

    public static void transportTime() {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        float N = sc.nextInt();
        float[] result = new float[M];
        result[0] = N / sc.nextInt();
        for (int i = 1; i < M; i++) {
            float time = N / sc.nextInt();
            if (result[i - 1] > time + 1) {
                result[i] = result[i - 1] - 1;
            } else {
                result[i] = time;
            }
        }
        System.out.println(result[M - 1]);
    }
}
