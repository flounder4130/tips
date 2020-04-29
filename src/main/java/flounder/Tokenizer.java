package flounder;

import opennlp.tools.stemmer.PorterStemmer;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {

    private static final Pattern NO_TAGS = Pattern.compile("<[/]?.*?>");
    private static final Pattern WORDS = Pattern.compile("\\W+");
    private static final PorterStemmer STEMMER = new PorterStemmer();
    
    public static Set<String> tokenizeAndStem(String in) {
        return Arrays.stream(WORDS.split(NO_TAGS.matcher(in).replaceAll(" ")))
                .filter(s -> !toRemove.contains(s.toLowerCase()))
                .map(STEMMER::stem)
                .collect(Collectors.toSet());
    }

    static List<String> toRemove = Arrays.asList(
            "of",
            "a",
            "an",
            "on",
            "to",
            "with",
            "by",
            "click",
            "the",
            "in",
            "be",
            "of",
            "and",
            "for",
            "not",
            "as",
            "you",
            "do",
            "at",
            "this",
            "that",
            "but",
            "by",
            "from",
            "or",
            "will",
            "would",
            "out",
            "if",
            "about",
            "get",
            "which",
            "click",
            "when",
            "can",
            "like",
            "you",
            "know",
            "into",
            "your",
            "any",
            "all",
            "it",
            "|",
            "open",
            "press");

}

