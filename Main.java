import java.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        HashMap<Integer, TreeSet<String>> dictionary = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            Scanner fileScan = null;
            try {
                fileScan = new Scanner (new File(args[i]));
                TreeSet<String> set = new TreeSet<>();
                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    StringTokenizer split = new StringTokenizer(line, " .[]();,!?'\":\t\r");
                    while (split.hasMoreTokens()) {
                        String toAdd = split.nextToken();
                        if (toAdd.trim().length() != 0)
                            set.add(toAdd.trim().toLowerCase());
                    }
                }
                dictionary.put(i + 1, set);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileScan != null)
                    fileScan.close();
            }
        }
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        while (!(word.equals(Utilities.exitWord))) {
            TreeMap<String, TreeSet<Integer>> map = Utilities.doStuff(word, dictionary);
            if (map.isEmpty())
                System.out.println("No suggestions...");
            else {
                int i = 1;                
                for (Map.Entry<String, TreeSet<Integer>> entry: map.entrySet()) {
                    ++i;
                    System.out.print(entry.getKey() + " : ");
                    Iterator iter = entry.getValue().iterator();
                    while (iter.hasNext()) {
                        System.out.print(iter.next() + " ");
                    }
                    System.out.println("");
                    if (i == 6)
                        break;
                }
            }
            System.out.println("");
            word = scan.nextLine();
        }
        scan.close();
    }
}