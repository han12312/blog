package com.blog.utils;

import java.util.*;

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

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
//        1.反转链表(输入：1,2,3,4,5 输出:5,4,3,2,1)
//        reverseList();
//        2.遍历二叉树(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
//        traversal();
//        3.二分法(输入: 5,10,15,20,25,30,35\n 15 输出：2)
//        binarySearch();
//        4.快速排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
//        quickSortMain();
    }

    //    1.反转链表(输入：1,2,3,4,5 输出:5,4,3,2,1)
    public static void reverseList() {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ListNode l1 = createListNode(nums);
        ListNode l2 = createListNode(nums);
        ListNode result1 = iterate(l1);
        System.out.println(printListNode(result1));
        ListNode result2 = recur(l2, null);
        System.out.println(printListNode(result2));
    }

    public static ListNode createListNode(int[] nums) {
        ListNode node = new ListNode(0), head = node;
        for (int num : nums) {
            node.next = new ListNode(num);
            node = node.next;
        }
        return head.next;
    }

    public static ListNode iterate(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode res = recur(cur.next, cur);
        cur.next = pre;
        return res;
    }

    public static List<Integer> printListNode(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
    }

    //    2.遍历二叉树(递归 输入：1,2,3,4,5 输出：4,2,5,1,3)
    static List<Integer> result = new ArrayList<>();

    public static void traversal() {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        TreeNode node = createTree(nums, 0);
        System.out.println(breadth(node));
        inorder(node);
        System.out.println(result);
    }

    public static TreeNode createTree(int[] nums, int n) {
        if (n >= nums.length || nums[n] == 0) {
            return null;
        }
        TreeNode node = new TreeNode(nums[n]);
        node.left = createTree(nums, n * 2 + 1);
        node.right = createTree(nums, n * 2 + 2);
        return node;
    }

    public static List<Integer> breadth(TreeNode node) {
        List<Integer> res = new ArrayList<>();
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
            res.add(tmp.val);
        }
        return res;
    }

    public static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        result.add(node.val);
        inorder(node.right);
    }

    //    3.二分法(输入: 5,10,15,20,25,30,35\n 15 输出：2)
    public static void binarySearch() {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int key = scanner.nextInt();
        System.out.println(binarySearch(nums, key));
    }

    public static int binarySearch(int[] nums, int key) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == key) {
                return mid;
            } else if (nums[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    //    4.快速排序(输入：20,30,5,15,10,25,35 输出：5, 10, 15, 20, 25, 30, 35)
    public static void quickSortMain() {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int tmp = nums[low], l = low, h = high;
        while (l < h) {
            while (l < h && nums[h] >= tmp) {
                h--;
            }
            nums[l] = nums[h];
            while (l < h && nums[l] <= tmp) {
                l++;
            }
            nums[h] = nums[l];
        }
        nums[l] = tmp;
        quickSort(nums, low, l - 1);
        quickSort(nums, l + 1, high);
    }
}