package com.danli.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanxiaofei
 */
public class AlgorithmUtil {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //1.反转链表
//        reverseListMain();
        //1.数列描述
//        sequenceDescriptionMain();
        //2.整数最小和
        //bb12-34aa
        sumIntegerTest();
    }
    public static void reverseListMain(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = reverseListTest(node1);
        while (head != null){
            System.out.print(head.val);
            head = head.next;
        }
        System.out.println();
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode head2 = reverseList2Test(node);
        while (head2 != null){
            System.out.print(head2.val);
            head2 = head2.next;
        }
        System.out.println();
    }

    public static ListNode reverseList(ListNode head){
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

    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void sequenceDescriptionMain() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(sequenceDescriptionTest(n));
    }

    public static String sequenceDescription(int n){
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
        return str;
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

    public static ListNode reverseListTest(ListNode head){
        return null;
    }

    public static ListNode reverseList2Test(ListNode head) {
        return null;
    }

    public static String sequenceDescriptionTest(int n){
        return null;
    }

    public static String sumIntegerTest(){
        return null;
    }

}
