package project;

import java.util.ArrayList;

public class Similarity {
    //this method is for the user provided page
    public double similarity(Hash user, String term) {
        double result = 0;
        for (ArrayList<String> list : user.table) {
            for(String word : list) {
                if (term.equalsIgnoreCase(word))
                    result++;
            }
        }
        return result / user.space;
    }
    //this method is for the array of control wikipedia pages
    public double idf(Hash doc, String term) {
        double n = 0;
        for (ArrayList<String> list : doc.table) {
            for (String word : list){
                if (term.equalsIgnoreCase(word)) {
                        n++;

                }
            }
        }
        //makes sure n isn't 0 so there isn't a divide by 0
        if(n == 0) {
            return 0;
        }

        return Math.log(doc.space / n);
    }
    public double tfIdf(Hash doc, Hash docs, String term) {
        return similarity(doc, term) * idf(docs, term);

    }

}
