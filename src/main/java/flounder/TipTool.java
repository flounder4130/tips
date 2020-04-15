package flounder;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.File;
import java.util.*;


public class TipTool {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || !(args[0].equals("count") || args[0].equals("list") || args[0].equals("allstats") || args[0].equals("similarity"))) {
            System.out.println("Invalid arguments");
            System.exit(1);
        }
        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);
        File source = new File("./Tips_of_the_Day.xml");
        Topic topic = serializer.read(Topic.class, source);

        switch (args[0]) {
            case "count":
                if (args.length > 2) {
                    if (args[1].equals("specific")) {
                        System.out.println("Specific for " + args[2] + ": " + topic.getSpecificForProduct(args[2]).size());
                    } else if (args[1].equals("applicable")) {
                        System.out.println("Applicable for " + args[2] + ": " + topic.getNumByProduct(args[2]));
                    } else {
                        System.out.println("Tips total: " + topic.getTips().size());
                    }
                } else {
                    System.out.println("Tips total: " + topic.getTips().size());
                }
                System.out.println();
                break;
            case "list":
                if (args.length > 2) {
                    if (args[1].equals("specific")) {
                        topic.getSpecificForProduct(args[2]).forEach(System.out::println);
                    } else if (args[1].equals("applicable")) {
                        topic.getByProduct(args[2]).forEach(System.out::println);
                    } else {
                        System.out.println("Tips total: " + topic.getTips().size());
                    }
                } else {
                    topic.getTips().forEach(System.out::println);
                }
                System.out.println();
                break;
            case "allstats":
                for (Product p : Product.values()) {
                    System.out.println("Specific for " + p + ": " + topic.getSpecificForProduct(p).size());
                    System.out.println("Applicable for " + p + ": " + topic.getNumByProduct(p));
                    System.out.println("---");
                }
                System.out.println("Total: " + topic.getTips().size());
                System.out.println();
                break;
            case "similarity":
                int threshold = 10;
                if (args.length > 1) threshold = Integer.parseInt(args[1]);
                ArrayList<Similarity> list = new ArrayList<>();
                for (int tip1 = 0; tip1 < topic.getTips().size(); tip1++) {
                    for (int tip2 = tip1 + 1; tip2 < topic.getTips().size(); tip2++) {
                        Similarity s = new Similarity(topic.getTips().get(tip1), topic.getTips().get(tip2));
                        if (s.getValue() > threshold) list.add(s);
                    }
                }
                Collections.sort(list);
                for (Similarity s : list) {
                    System.out.println("---");
                    System.out.println(s);
                }
                System.out.println();
                break;
            default:
                System.out.println("Invalid arguments");
                break;
        }
    }

}


