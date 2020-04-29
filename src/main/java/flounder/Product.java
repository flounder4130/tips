package flounder;

import java.util.*;
import java.util.stream.Collectors;

public enum Product {
    IJ("Intellij IDEA "),
    AC("AppCode"),
    CL("CLion"),
    DB("DataGrip"),
    DB_PL("Database plugin"),
    //DCV("dotCover"),
    //DM("dotMemory"),
    //DMU("dotMemory Unit"),
    //DPK("dotPeek"),
    //DT("dotTrace"),
    //EDU("Educational products"),
    GO("GoLand"),
    IJC("Intellij IDEA Community"),
    //INSPECTIONS("Code inspections"),
    //MPS("MPS"),
    PE("PyCharm Edu"),
    //PHPSTORM_INSPECTIONS ("PhpStorm inspections"),
    PS("PhpStorm"),
    PY("PyCharm"),
    PY_CE("PyCharm Community"),
    //RDR("Rider"),
    //RIDER_INSPECTIONS("Rider inspections"),
    RM("RubyMine"),
    //RS("ReSharper"),
    //SC("Section chunks"),
    //TCA("TeamCity add-in"),
    WS("WebStorm");

    private String name;

    Product(String name) {
        this.name = name;
    }

    static List<Product> parseProducts(String in) {
        if (in == null) return Collections.emptyList();
        boolean negation = in.startsWith("!");
        Set<String> filterSet = new HashSet<>(Arrays.asList((negation ? in.substring(1) : in).toUpperCase().split("\\W+")));
        return Arrays.stream(values())
                .filter(product -> negation != filterSet.contains(product.toString()))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
