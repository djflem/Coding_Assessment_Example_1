package org.example;

import java.io.*;
import java.util.HashSet;

// Definition for a singly linked list node
class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

// Definition for a singly linked list
class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Method to insert a new node into the linked list
    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

// Class to help printing
class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}

/*
 * Complete the 'condense' function below.
 *
 * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
 * The function accepts INTEGER_SINGLY_LINKED_LIST head as parameter.
 */

/*
 * For your reference:
 *
 * SinglyLinkedListNode {
 *     int data;
 *     SinglyLinkedListNode next;
 * }
 *
 */

public class Result {
    // Method to remove duplicates from the linked list
    public static SinglyLinkedListNode condense(SinglyLinkedListNode head) {
        // Check if the list is empty
        if (head == null) {
            return null;
        }

        // Set to keep track of seen values
        HashSet<Integer> seen = new HashSet<>();
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode prev = null;

        // Traverse the list and remove duplicates
        while (current != null) {
            if (seen.contains(current.data)) {
                // Skip the current node if it is a duplicate
                prev.next = current.next;
            } else {
                // Mark the value as seen and update prev to current
                seen.add(current.data);
                prev = current;
            }
            // Move to the next node
            current = current.next;
        }
        return head;
    }
}