import java.*;
import java.util.*;
import java.io.*;

public class Utilities {
    public static final String exitWord = "/exit";
    public static final TreeSet hasAlready = new TreeSet();

    private static boolean checkStrings (String s1, String s2) {
        if (s1.startsWith(s2))
            return true;
        if (s1.contains("-")) {
            String[] tokens = s1.split("-");
            for (int i = 0; i < tokens.length; ++i) {
                if (tokens[i].startsWith(s2))
                    return true;
            }
        }
        return false;
    }

    public static TreeMap<String, TreeSet<Integer>> doStuff (String word, HashMap<Integer, TreeSet<String>> dictionary) {
        TreeMap<String, TreeSet<Integer>> result = new TreeMap<>();
        for (Map.Entry<Integer, TreeSet<String>> entry: dictionary.entrySet()) {
            int index = entry.getKey();
            Iterator<String> iter = entry.getValue().iterator();
            while (iter.hasNext()) {
                String str = iter.next();
                if (checkStrings(str, word)) {
                    //System.out.println("IAU LA PULA " + str + " " + word);
                    if (!(result.containsKey(str))) {
                        TreeSet<Integer> value = new TreeSet<>();
                        value.add(index);
                        result.put(str, value);
                    } else {
                        TreeSet<Integer> value = result.get(str);
                        value.add(index);
                        result.put(str, value);
                    }
                }
            }
        }
        return result;
    }
}