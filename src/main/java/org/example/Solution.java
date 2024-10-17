package org.example;

import java.io.*;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Set up BufferedWriter, falling back to default if OUTPUT_PATH is not set
        String outputPath = System.getenv("OUTPUT_PATH");
        BufferedWriter bufferedWriter;
        if (outputPath == null || outputPath.isEmpty()) {
            // Default to writing to the console
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
            // Write to the specified file
            bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
        }

        // Create a new linked list
        SinglyLinkedList list = new SinglyLinkedList();

        // Read the number of nodes in the list
        int nodeCount = Integer.parseInt(bufferedReader.readLine().trim());

        // Insert each node into the linked list
        IntStream.range(0, nodeCount).forEach(i -> {
            try {
                int nodeData = Integer.parseInt(bufferedReader.readLine().trim());
                list.insertNode(nodeData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Condense the linked list to remove duplicates
        SinglyLinkedListNode result = Result.condense(list.head);

        // Print the resulting linked list
        SinglyLinkedListPrintHelper.printList(result, "\n", bufferedWriter);
        bufferedWriter.newLine();

        // Close the resources
        bufferedReader.close();
        bufferedWriter.close();
    }
}