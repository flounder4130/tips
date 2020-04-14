package flounder;

import opennlp.tools.stemmer.PorterStemmer;
import java.util.*;
import java.util.stream.Collectors;

public class Tokenizer {

    public static Set<String> tokenizeAndStem(String in) {
        PorterStemmer stemmer = new PorterStemmer();
        String temp = in.replaceAll("<[/]?.*?>", " ");
        temp = temp.replaceAll("\n", "");
        temp = temp.replaceAll("\t", "");
        return Arrays.stream(temp.split(" "))
                .filter(s -> !(s.endsWith(".")||s.endsWith(",")))
                .filter(s -> !s.equals(""))
                .filter(s -> !toRemove.contains(s.toLowerCase()))
                .map(stemmer::stem)
                .collect(Collectors.toSet());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    static ArrayList<String> toRemove = new ArrayList(){{
        add("of");
        add("a");
        add("an");
        add("on");
        add("to");
        add("with");
        add("by");
        add("click");
        add("the");
        add("in");
        add("be");
        add("of");
        add("and");
        add("for");
        add("not");
        add("as");
        add("you");
        add("do");
        add("at");
        add("this");
        add("that");
        add("but");
        add("by");
        add("from");
        add("or");
        add("will");
        add("would");
        add("out");
        add("if");
        add("about");
        add("get");
        add("which");
        add("click");
        add("when");
        add("can");
        add("like");
        add("you");
        add("know");
        add("into");
        add("your");
        add("any");
        add("all");
        add("it");
        add("|");
        add("open");
        add("press");
    }};


}

