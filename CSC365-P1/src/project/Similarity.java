package project;

import java.util.ArrayList;
import java.util.List;

public class Similarity {

    public double similarity(ArrayList<String>[] user, String term) {
        double result = 0;
        for (int i = 0; i < user.length; i++) {
            for(int k = 0; k < user[i].size(); k++) {
                String key = user[i].get(k);
                if (term.equalsIgnoreCase(key))
                    result++;
            }
        }
        return result / user.length;
    }

    public double idf(Hash[] docs, String term) {
        double n = 0;
        for (Hash doc : docs) {
            for (Object word : doc) {
                if (term.equalsIgnoreCase((String) word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.length / n);
    }
    public double tfIdf(ArrayList<String>[] doc, Hash[] docs, String term) {
        return similarity(doc, term) * idf(docs, term);

    }

}