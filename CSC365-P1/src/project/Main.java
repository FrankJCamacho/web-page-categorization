package project;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    //Program must read the control.txt file for the wikipedia pages(implemented)
    //It must maintain a frequency of the words in each page in a hashtable(implemented)
    //User must be able to enter any other wikipedia URL(implemented)
    //Use similarity metric to compare word frequencies between the user page and the 10 control pages
    //The program reports which page in the control.txt is most similar to that url


    public static void main(String[] args) throws IOException {
        //array of hashtables?
        Similarity similarity = new Similarity();
        Hash userTable = new Hash();
        String[] urlArray = new String[10];

        Hash hash1 = new Hash();
        Hash hash2 = new Hash();
        Hash hash3 = new Hash();
        Hash hash4 = new Hash();
        Hash hash5 = new Hash();
        Hash hash6 = new Hash();
        Hash hash7 = new Hash();
        Hash hash8 = new Hash();
        Hash hash9 = new Hash();
        Hash hash10 = new Hash();

        Hash[] hashTableArray = {hash1, hash2, hash3, hash4, hash5, hash6, hash7, hash8, hash9, hash10};

        File file = new File("/Users/fjcam/Desktop/IntelliJ Projects/CSC365-P1/control.txt");
        Scanner scan = new Scanner(file);

        for(int i = 0; i < urlArray.length; i++) {
                urlArray[i] = scan.nextLine();
        }

        for(int i = 0; i < urlArray.length; i++) {
            String paragraphBody = Jsoup.jsoupText(urlArray[i]);
            for(String iterator : paragraphBody.split(" ")) {
                hashTableArray[i].url = urlArray[i];
                hashTableArray[i].put(iterator);
            }
        }

        String userUrl = JOptionPane.showInputDialog(null, "Enter your wikipedia url:");
        String userParagraphBody = Jsoup.jsoupText(userUrl);

        for(String iterator : userParagraphBody.split(" ")) {
            userTable.put(iterator);
        }

        //iterates through the hash table array and stores a new tfIdf value in each table
        for (Hash table : hashTableArray) {
            for (ArrayList<String> list : userTable.table) {
                for (String term : list) {
                    table.tfIdf += similarity.tfIdf(userTable, table, term);
                }
            }
        }

        //System.out.println(Arrays.toString(hashTableArray));

        //Sorts the arrays in ascending order
        Arrays.sort(hashTableArray);

        //System.out.println(Arrays.toString(hashTableArray));

        //System.out.println("Wikipedia page most similar is: " + hashTableArray[9].url);

        //Outputs most similar wikipedia page to user chosen one
        JOptionPane.showMessageDialog( null, "The most similar wikipedia page is: " + hashTableArray[9].url);

    }
}
