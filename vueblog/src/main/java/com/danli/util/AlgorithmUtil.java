package com.danli.util;

import java.util.*;
import java.util.regex.*;

/**
 * @author hanxiaofei
 */
public class AlgorithmUtil {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode createListNode(int[] a) {
        ListNode node = new ListNode(0), head = node;
        for (int i : a) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head.next;
    }

    private static List<Integer> printListNode(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode createTree(int[] a, int i) {
        if (i >= a.length) {
            return null;
        }
        TreeNode node = new TreeNode(a[i]);
        node.left = createTree(a, 2 * i + 1);
        node.right = createTree(a, 2 * i + 2);
        return node;
    }

    private static void breadth(TreeNode node, List<Integer> result) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.remove();
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
            result.add(tmp.val);
        }
    }

    private static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    public static void main(String[] args) {
//        1.反转链表(输入：1,2,3,4,5 输出:5,4,3,2,1)
//        reverseList();
//        2.遍历二叉树(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
//        traversal();
//        3.冒泡排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        bubbleSort();
//        4.插入排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        insertionSort();
//        5.二分法(输入: 5,10,15,20,25,30,35\n 15 输出：2)
//        binarySearch();
//        6.整数最小和(输入:bb12-34aa 输出：31)
//        sumInteger();
//        7.数列描述(输入：4 输出：111221)
//        sequenceDescription();
//        8.人数最多站点(输入:3\n 1 3 2 4 1 4 输出：2)
//        station();
//        9.报文回路(输入:5\n 1 2\n 2 3\n 3 2\n 1 2\n 2 1 输出：True)
//        messageLoop();
//        10.运输时间(输入:2 11\n 3\n 2 输出 5.5)
//        transportTime();
//        11.斐波那契数列(输入：6 输出：8)
//        fibonacci();
//        12.机器人从cur出发，走rest步后，停在aim的方法数，共有位置N(输入：2,6,4,5 输出：13)
//        ways();
//        13.重量w[]，价值v[],背包不超重的最大价值(输入：3,2,4,7,3,1,7\n 5,6,3,19,12,4,2\n 输出：42)
//        bag();
    }

    //    1.反转链表(输入：1,2,3,4,5 输出:5,4,3,2,1)
    public static void reverseList() {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ListNode l1 = createListNode(a);
        ListNode l2 = createListNode(a);
        ListNode result1 = r1(l1);
        System.out.println(printListNode(result1));
        ListNode result2 = r2(l2);
        System.out.println(printListNode(result2));
    }

    public static ListNode r1(ListNode head) {
        ListNode pre = null, cur = head, tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static ListNode r2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = r2(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }

    //    2.遍历二叉树(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
    public static void traversal() {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        TreeNode node = createTree(a, 0);
        List<Integer> result = new ArrayList<>();
        breadth(node, result);
        System.out.println(result);
        result = new ArrayList<>();
        inorder(node, result);
        System.out.println(result);
    }

    //    3.冒泡排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
    public static void bubbleSort() {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    //    4.插入排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
    public static void insertionSort() {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int i, j, tmp;
        for (i = 1; i < nums.length; i++) {
            tmp = nums[i];
            for (j = i - 1; j >= 0; j--) {
                if (nums[j] > tmp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = tmp;
        }
        System.out.println(Arrays.toString(nums));
    }

    //    5.二分法(输入: 5,10,15,20,25,30,35\n 15 输出：2)
    public static void binarySearch() {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int key = scanner.nextInt();
        System.out.println(binarySearch1(a, key));
        System.out.println(binarySearch2(a, key, 0, a.length));
    }

    public static int binarySearch1(int[] a, int key) {
        int low = 0, high = a.length - 1, mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (a[mid] == key) {
                return mid;
            } else if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] a, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (a[mid] == key) {
            return mid;
        } else if (a[mid] < key) {
            return binarySearch2(a, key, mid + 1, high);
        } else {
            return binarySearch2(a, key, low, mid - 1);
        }
    }

    //    6.整数最小和(输入:bb12-34aa 输出：31)
    public static void sumInteger() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<Long> list = new ArrayList<>();
        Pattern compile = Pattern.compile("(-?[\\d]+)");
        Matcher matcher = compile.matcher(s);
        while (matcher.find()) {
            list.add(Long.valueOf(matcher.group(0)));
        }
        int count = 0;
        for (Long num : list) {
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

    //    7.数列描述(输入：4 输出：111221)
    public static void sequenceDescription() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = "1";
        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = null;
            int num = 0;
            boolean flag = true;
            String tmp = "";
            while (flag) {
                char c = str.charAt(num);
                if (num + 1 == str.length()) {
                    if (map == null) {
                        str = tmp + "1" + c;
                    } else if (map.containsKey(c)) {
                        int a = map.get(c) + 1;
                        str = tmp + a + c;
                    } else {
                        str = tmp + map.get(str.charAt(num - 1)) + str.charAt(num - 1) + "1" + c;
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
                    tmp = tmp + map.get(str.charAt(num - 1)) + str.charAt(num - 1);
                    map = null;
                }
            }
        }
        System.out.println(str);
    }

    //    8.人数最多站点(输入:3\n 1 3 2 4 1 4 输出：2)
    public static void station() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                list.add(scanner.nextInt());
            }
        }
        List<Integer> tmp = new ArrayList<>(list);
        Collections.sort(tmp);
        int max = tmp.get(tmp.size() - 1);
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

    //    9.报文回路(输入:5\n 1 2\n 2 3\n 3 2\n 1 2\n 2 1 输出：True)
    public static void messageLoop() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    list.add(scanner.nextInt());
                } else {
                    list2.add(scanner.nextInt());
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

    //    10.运输时间(输入:2 11\n 3\n 2 输出 5.5)
    public static void transportTime() {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        float N = scanner.nextInt();
        float[] result = new float[M];
        result[0] = N / scanner.nextInt();
        for (int i = 1; i < M; i++) {
            float time = N / scanner.nextInt();
            if (result[i - 1] > time + 1) {
                result[i] = result[i - 1] - 1;
            } else {
                result[i] = time;
            }
        }
        System.out.println(result[M - 1]);
    }

    //    11.斐波那契数列(输入：6 输出：8)
    public static void fibonacci() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n + 1];
        System.out.println(f1(n));
        System.out.println(f2(n, dp));
        System.out.println(f3(n));
    }

    public static int f1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n, int[] dp) {
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[1] = 1;
        dp[2] = 1;
        dp[n] = f2(n - 1, dp) + f2(n - 2, dp);
        return dp[n];
    }

    public static int f3(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //    12.机器人从cur出发，走rest步后，停在aim的方法数，共有位置N(输入：2,6,4,5 输出：13)
    public static void ways() {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int cur = a[0], rest = a[1], aim = a[2], N = a[3];
        if (cur < 1 || cur > N || rest < 1 || aim < 1 || aim > N || N < 2) {
            return;
        }
        System.out.println(w1(cur, rest, aim, N));
        int[][] dp = new int[N + 1][rest + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= rest; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(w2(cur, rest, aim, N, dp));
        System.out.println(w3(cur, rest, aim, N));
    }

    public static int w1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return w1(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return w1(N - 1, rest - 1, aim, N);
        }
        return w1(cur - 1, rest - 1, aim, N) + w1(cur + 1, rest - 1, aim, N);
    }

    public static int w2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = w2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = w2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = w2(cur - 1, rest - 1, aim, N, dp) + w2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int w3(int cur, int rest, int aim, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        dp[aim][0] = 1;
        for (int i = 1; i <= rest; i++) {
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[N][i] = dp[N - 1][i - 1];
        }
        return dp[cur][rest];
    }

    //    13.重量w[]，价值v[],背包不超重的最大价值(输入：3,2,4,7,3,1,7\n 5,6,3,19,12,4,2\n 输出：42)
    public static void bag() {
        Scanner scanner = new Scanner(System.in);
        int[] weights = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] values = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int bag = scanner.nextInt();
        if (weights == null || values == null || weights.length != values.length || weights.length == 0) {
            return;
        }
        System.out.println(maxValue(weights, values, 0, bag));
        System.out.println(maxValue2(weights, values, bag));
    }

    public static int maxValue(int[] w, int[] v, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = maxValue(w, v, index + 1, bag);
        int p2 = 0;
        int next = maxValue(w, v, index + 1, bag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int maxValue2(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}