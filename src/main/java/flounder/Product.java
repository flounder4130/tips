package flounder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    Product(String name){
        this.name = name;
    }

    static List<Product> parseProducts(String in) {
        if (in==null) return new ArrayList<>();
        List<Product> out = new ArrayList<>();
        String[] products = in.split("\\s*,\\s*");
        if (in.startsWith("!")) {
            ArrayList<Product> remaining = new ArrayList<>(Arrays.asList(Product.values()));
            for (Product p : Product.values()) {
                for (String s : products) {
                    if (s.equals("")) continue;
                    if (s.toUpperCase().equals(p.toString())) remaining.remove(p);
                }
            }
            out = remaining;
        } else {
            for (String s : products) {
                if (s.equals("")) continue;
                out.add(Product.valueOf(s.toUpperCase()));
            }
        }
        return out;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }
}
