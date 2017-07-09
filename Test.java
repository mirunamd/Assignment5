import java.util.*;

public class Test {
    public static void main(String[] args) {
        String[] dic = {
            "ART",
            "RAT",
            "CAT",
            "CAR"
        };
        testExample(dic); // [A, T, R, C]

        String[] dicEmpty = {};
        testEmptyDic(dicEmpty); // []

        String[] dicCustom = {
            "SCA",
            "CZX",
            "CR",
            "RAD"
        };
        testCustom(dicCustom); // [S, C, Z, R, A, X, D]

        String[] dicOne = {
            "A",
            "D",
            "E",
            "B"
        };
        testOneLetterWords(dicOne); // [A, D, E, B]

        String[] dicSame = {
            "CAT",
            "CAR",
            "CAB",
            "CAN"
        };
        testSamePrefixWords(dicSame); // [C, T, R, B, A, N]

        String[] dicNo = {
            "TREE",
            "PLANT",
            "YOU"
        };
        testNoCommonPrefix(dicNo); // [T, P, Y, R, E, L, A, N, O, U]

        String[] dicDupl = {
            "CAR",
            "CAR",
            "ACE",
            "ACE",
            "ACE"
        };
        testWithDuplicates(dicDupl); // [C, A, R, E]

        String[] dicDuplIn = {
            "CAA",
            "CAAR"
        };
        testWithDuplicatesIn(dicDuplIn); // [C, A, R]

        String[] dicSymb = {
            "£$",
            "$",
            "($"
        };
        testSymbDic(dicSymb); // [£, $, (]

        String[] dicNoCaps = {
            "avc",
            "vrt",
            "true"
        };
        testNoCapsDic(dicNoCaps); // [a, v, t, c, r, u, e]

        String[] dicMixed = {
            "Avc",
            "aVc",
            "%",
            "£%",
            "£$"
        };
        testMixedDic(dicMixed); // 
    }

    private static void testExample(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("ATRC");

        printTestResult(result, got);
    }

    private static void printTestResult(LinkedList < Character > result, LinkedList < Character > alph) {
        if (alph.equals(result))
            System.out.print("OK ");
        else System.out.print("X ");
        System.out.println("Expected: " + result + ", got: " + alph);
    }

    private static LinkedList < Character > createResult(String s) {
        LinkedList < Character > result = new LinkedList < Character > ();

        for (int i = 0; i < s.length(); i++)
            result.add(new Character(s.charAt(i)));

        return result;
    }

    // my tests

    private static void testCustom(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("SCZRAXD");

        printTestResult(result, got);
    }

    private static void testEmptyDic(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("");

        printTestResult(result, got);
    }

    private static void testOneLetterWords(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("ADEB");

        printTestResult(result, got);
    }

    private static void testSamePrefixWords(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("CTRBAN");

        printTestResult(result, got);
    }

    private static void testNoCommonPrefix(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("TPYRELANOU");

        printTestResult(result, got);
    }

    private static void testWithDuplicates(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("CARE");

        printTestResult(result, got);
    }

    private static void testWithDuplicatesIn(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("CAR");

        printTestResult(result, got);
    }

    private static void testSymbDic(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("£$(");

        printTestResult(result, got);
    }

    private static void testNoCapsDic(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("avtcrue");

        printTestResult(result, got);
    }

    private static void testMixedDic(String[] dic) {
        UnknownLanguage lang = new UnknownLanguage();

        LinkedList < Character > got = lang.buildAlphabet(dic);
        LinkedList < Character > result = createResult("Aa%£vcV$");

        printTestResult(result, got);
    }
}
