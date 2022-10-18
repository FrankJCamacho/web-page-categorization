package project;

import java.util.ArrayList;
import java.util.List;

public class Hash implements Comparable<Hash> {

    //Arrays of ArrayLists
    //Resize doubles size of array

    int size = 100;
    int space = 0;
    double tfIdf = 0;
    String url;
    ArrayList<String>[] table = new ArrayList[size];
    public Hash() {
        for(int row = 0; row < table.length; row++) {
            table[row] = new ArrayList<>();
        }
    }

    boolean contains(String key) {

        int hash = key.hashCode();
        int index = Math.abs(hash % size); //index of arraylist

        for(int col = 0; col < table[index].size(); col++) {
            if(key.equals(table[index].get(col)))
                return true;
        }
        return false;
    }

    void put(String key) {
        //(implemented)check if the hash table is overcapacity, 75% of size, resize if overcapacitiy
        //(implemented)figure out where key goes, figure out the index/hashcode
        //(implemented)mod the hashcode, so it fits in array
        //(implemented)absolute value every hashcode
        //(implemented)put it into our hashtable

        int hash = key.hashCode();
        int index = Math.abs(hash % size);

        if(table[index].size() == 0) {
            space++;
        }

        table[index].add(key);

        if((float)space / table.length >= 0.75f)
            resize();
    }

    void resize(){
        ArrayList<String>[] oldTable = table;
        size = size * 2;
        table = new ArrayList[size];
        for(int row = 0; row < table.length; row++) {
            table[row] = new ArrayList<String>();
        }

        for(int row = 0; row < oldTable.length; row++) {
            for(int col = 0; col < oldTable[row].size(); col++) {
                String key = oldTable[row].get(col);
                put(key);
            }
        }

    }

    @Override
    public int compareTo(Hash o) {
        double dif = this.tfIdf - o.tfIdf;
        if(dif > 0) {
            return 1;
        } else if(dif < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return url + " " + tfIdf;
    }
}
