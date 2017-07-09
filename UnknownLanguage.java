import java.util.*;

public class UnknownLanguage {
    public LinkedList < Character > buildAlphabet(String[] dic) {
        if (dic.length == 0) return new LinkedList < Character > ();

        LinkedHashMap < String, Neighbours > l = new LinkedHashMap < > ();
        LinkedHashSet < String > extra = new LinkedHashSet < > ();

        String start = dic[0].charAt(0) + "";
        String last = " ";

        String w1 = " ";

        for (String w2: dic) {
            char cx = w2.charAt(0);

            if (extra.contains(cx + ""))
                extra.remove(cx + "");

            if (!l.containsKey(cx + "")) {

                if (l.containsKey(last)) {
                    Neighbours n = l.get(last);
                    l.put(last, new Neighbours(n.left, cx + ""));
                    l.put(cx + "", new Neighbours(last, " "));

                } else l.put(cx + "", new Neighbours(" ", " "));

            }


            for (char c: (w2.substring(1)).toCharArray()) {
                if (!l.containsKey(c + ""))
                    extra.add(c + "");
            }

            if (w1.charAt(0) == w2.charAt(0)) {
                int l1 = w1.length();
                int l2 = w2.length();
                int min, i;

                if (l1 < l2)
                    min = l1;
                else min = l2;

                for (i = 1; i < min; i++) {
                    char c1 = w1.charAt(i);
                    char c2 = w2.charAt(i);
                    if (c1 == c2) {
                        if (!l.containsKey(c1 + ""))
                            extra.add(c1 + "");
                    } else {
                        if (extra.contains(c1 + ""))
                            extra.remove(c1 + "");

                        if (l.containsKey(c2 + "") && !l.containsKey(c1 + "")) {
                            // A <-> R <-> C     T is c1
                            Neighbours nc2 = l.get(c2 + ""); // R's neighbours A and C

                            String previous = nc2.left; // get the previous node - A
                            Neighbours npr = l.get(previous); // get its left and right neighbours - "" & R

                            l.put(previous, new Neighbours(npr.left, c1 + "")); // null -> A -> T

                            l.put(c1 + "", new Neighbours(previous, c2 + "")); // A <-> T -> R

                            String next = nc2.right; // C
                            l.put(c2 + "", new Neighbours(c1 + "", next)); // T <-> R <-> C         
                        } else {
                            if (!l.containsKey(c1 + "")) {
                                Neighbours n = l.get(last);

                                l.put(last, new Neighbours(n.left, c1 + ""));

                                l.put(c1 + "", new Neighbours(last, " "));
                                last = c1 + "";
                            }

                            extra.add(c2 + "");

                        }
                    }
                }

                if (i < l1) {
                    for (int j = i + 1; j < l1; j++) {
                        char c1 = w1.charAt(j);

                        if (!l.containsKey(c1))
                            extra.add(c1 + "");
                    }
                }

                if (i < l2) {
                    for (int j = i + 1; j < l2; j++) {
                        char c2 = w1.charAt(j);

                        if (!l.containsKey(c2))
                            extra.add(c2 + "");
                    }
                }
            } else last = cx + "";
            w1 = w2;
        }

        LinkedList < Character > result = solve(l, extra, start);
        return result;
    }

    private LinkedList < Character > solve(LinkedHashMap < String, Neighbours > l, LinkedHashSet < String > extra, String start) {
        LinkedList < Character > result = new LinkedList < Character > ();
        String curr = start;

        while (!curr.equals(" ")) {
            result.add(new Character(curr.charAt(0)));
            Neighbours n = l.get(curr);
            curr = n.right;
        }

        for (String s: extra)
            result.add(new Character(s.charAt(0)));

        return result;
    }

    private void print(LinkedHashMap < String, Neighbours > l) { // used for testing
        for (Map.Entry < String, Neighbours > entry: l.entrySet()) {
            String key = entry.getKey();
            Neighbours value = entry.getValue();
            System.out.println(key + " " + value.left + "*" + value.right);
        }
    }
    private void print(LinkedHashSet < String > extra) { // used for testing
        System.out.println("Extra");
        for (String s: extra)
            System.out.print(s + " ");
        System.out.println();
    }
}



class Neighbours {
    String left, right;

    public Neighbours(String left, String right) {
        this.left = left;
        this.right = right;
    }
}
