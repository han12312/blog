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

    public static ListNode createListNode(int[] nums) {
        ListNode node = new ListNode(0), head = node;
        for (int n : nums) {
            node.next = new ListNode(n);
            node = node.next;
        }
        return head.next;
    }

    public static List<Integer> printListNode(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list;
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
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        TreeNode node = createTree(nums, 0);
        System.out.println(breadth(node));
        List<Integer> result = new ArrayList<>();
        inorder(node, result);
        System.out.println(result);
    }

    public static TreeNode createTree(int[] nums, int n) {
        if (nums.length <= n) {
            return null;
        }
        TreeNode node = new TreeNode(nums[n]);
        node.left = createTree(nums, 2 * n + 1);
        node.right = createTree(nums, 2 * n + 2);
        return node;
    }

    public static List<Integer> breadth(TreeNode node) {
        List<Integer> result = new ArrayList<>();
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
        return result;
    }

    public static void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
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
            mid = (high + low) / 2;
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
}