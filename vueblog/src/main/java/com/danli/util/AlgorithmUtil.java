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
//        1.反转链表(迭代、递归 输入：1,2,3,4,5 输出：5,4,3,2,1)
//        reverseList();
//        2.二叉树中序遍历(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
//        traversal();
//        3.冒泡排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        bubbleSort();
//        4.选择排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        selectionSort();
//        5.插入排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        insertionSort();
//        6.快速排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        quickSortMain();
//        7.折半查找(输入: 5,10,15,20,25,30,35\n 15 输出：2)
//        binarySearch();
//        8.人数最多站点(输入:3\n 1 3 2 4 1 4 输出：2)
//        station();
//        9.报文回路(输入:5\n 1 2\n 2 3\n 3 2\n 1 2\n 2 1 输出：True)
//        messageLoop();
//        10.数列描述(输入：4 输出：111221)
//        sequenceDescription();
//        11.整数最小和(输入:bb12-34aa 输出：31)
//        sumInteger();
//        12.运输时间(输入:2 11\n 3\n 2 输出 5.5)
//        transportTime();
//        13.图形周长(输入:2\n 1 1 3 2 2 2 3 2 4 3 2 3 3 3 4 4 1 4 2 4 3 4 4 5 2 5 3\n
//                     2 3 7 3 8 4 5 4 6 4 7 4 8 5 4 5 5 5 6 5 7 5 8 6 4 6 5 6 6 6 7 6 8 7 4 7 5 7 6 7 7 7 8
//                  输出：18 20)
//        perimeter();
//        1.两数之和(输入：2,7,11,15\n 9 输出：[0,1])
//        twoSum();
//        2.两数相加(输入: 2,4,3\n 5,6,4 输出: [7,0,8])
//        twoAdd();
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

    public static void bubbleSort() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        boolean flag = false;
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void selectionSort() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min == i){
                continue;
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void insertionSort() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int i, j, temp;
        for (i = 1; i < a.length; i++) {
            temp = a[i];
            for (j = i - 1; j >= 0 && a[j] > temp ; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void quickSortMain() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void binarySearch() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int key = sc.nextInt();
        int low = 0, mid, high = a.length - 1;
        while (low <= high) {
            mid = (high + low) / 2;
            if (key == a[mid]) {
                System.out.println(mid);
                return;
            } else if (key < a[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("未找到");
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

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int index = Partition(a, low, high);
            quickSort(a, low, index - 1);
            quickSort(a, index + 1, high);
        }
    }

    public static int Partition(int[] a, int low, int high) {
        int temp = a[low];
        while (low < high) {
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
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

    public static void twoAdd() {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ListNode nodeCreate = new ListNode(a[0]);
        ListNode node1 = nodeCreate;
        for (int i = 1; i < a.length; i++) {
            nodeCreate.next = new ListNode(a[i]);
            nodeCreate = nodeCreate.next;
        }
        ListNode nodeCreate2 = new ListNode(b[0]);
        ListNode node2 = nodeCreate2;
        for (int i = 1; i < a.length; i++) {
            nodeCreate2.next = new ListNode(b[i]);
            nodeCreate2 = nodeCreate2.next;
        }
        ListNode head = null, tail = null;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int n = node1 != null ? node1.val : 0;
            int n2 = node2 != null ? node2.val : 0;
            int sum = n + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(",");
            head = head.next;
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static void twoSum() {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int n = sc.nextInt();
        //方法1
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == n) {
                    System.out.println(Arrays.toString(new int[]{i, j}));
                }
            }
        }
        //方法2
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(n - nums[i])) {
                System.out.println(Arrays.toString(new int[]{map.get(n - nums[i]), i}));
                return;
            }
            map.put(nums[i], i);
        }
        System.out.println(Arrays.toString(new int[0]));
    }
}
