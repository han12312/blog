package com.danli.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanxiaofei
 */
public class AlgorithmUtil {

    public static int n = 64;
    public static int m = 64;

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
//        1.反转链表(迭代、递归)
//        reverseList();
//        2.二叉树中序遍历(递归)
//        traversal();
//        1.数列描述
//        sequenceDescription();
//        2.整数最小和(例:bb12-34aa)
//        sumInteger();
//        3.人数最多站点(例:3 13 24 14)
//        station();
//        4.报文回路(例:5 12 23 32 12 21、3 13 32 23)
//        messageLoop();
//        5.图形周长
//        perimeter();
//        6.运输时间
//        transportTime();
    }

    public static void reverseList() {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(",");
        ListNode node = new ListNode(Integer.parseInt(str[0]));
        ListNode head = node;
        for (int i = 1; i < str.length; i++) {
            node.next = new ListNode(Integer.parseInt(str[i]));
            node = node.next;
        }
        ListNode result = reverseListD(head);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
        System.out.println();
        ListNode result2 = reverseListG(reverseListD(node));
        while (result2 != null) {
            System.out.print(result2.val);
            result2 = result2.next;
        }
        System.out.println();
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
        String[] str = sc.nextLine().split(",");
        int[] a = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            a[i] = Integer.parseInt(str[i]);
        }
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
            int minVal = list.get(i);
            int maxVal = list.get(i + 1);
            for (int j = minVal; j < maxVal; j++) {
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
            int num = list.get(i);
            int num2 = list2.get(i);
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == num2 && list2.get(j) == num) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            } else {
                System.out.println("False");
                return;
            }
        }
        System.out.println("True");
    }

    public static void perimeter() {
        Scanner sc = new Scanner(System.in);
        int index = Integer.parseInt(sc.nextLine());
        String str = null;
        char[][] chars = new char[64][64];
        int[] nums = null;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (index-- > 0) {
            str = sc.nextLine();
            nums = Arrays.stream(str.split(" ")).mapToInt(Integer::valueOf).toArray();
            for (int i = 1; i < nums.length; i = i + 2) {
                chars[nums[i]][nums[i + 1]] = (char) ('0' + nums[0]);
            }
            int t = calMask(chars, (char) ('0' + nums[0]));
            count = find((int) nums[1], (int) nums[2], chars, (char) ('0' + nums[0]));
            count = count * 4 - t * 2;
            sb.append(count + " ");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static int calMask(char[][] chars, char ch) {
        int count = 0;
        int count2 = 0;
        int total = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            count2 = 0;
            for (int j = 0; j < m; j++) {
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
                total = count2 - 1;
            }
        }
        return total;
    }

    public static int find(int i, int j, char[][] chars, char target) {
        if (i < 0 || j < 0 || i == n || j == m) {
            return 0;
        }
        if (chars[i][j] == target) {
            chars[i][j] = '0';
            return find(i, j - 1, chars, target) + find(i - 1, j, chars, target) + find(i + 1, j, chars, target)
                    + find(i, j + 1, chars, target) + 1;
        } else {
            return 0;
        }
    }

    public static void transportTime() {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        float N = sc.nextInt();
        float[] result = new float[M];
        int firstSpeed = sc.nextInt();
        result[0] = N / firstSpeed;
        for (int i = 1; i < M; i++) {
            int speed = sc.nextInt();
            float v = N / speed;
            if (result[i - 1] > v + 1) {
                result[i] = result[i - 1] - 1;
            } else {
                result[i] = v;
            }
        }
        System.out.println(result[M - 1]);
    }
}
