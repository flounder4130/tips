package flounder;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Root(strict = false, name = "topic")
public class Topic {

    Predicate<TipOfTheDay> multipleProduct = tip -> 1 != tip.getProducts().size();

    @ElementList(inline = true, entry = "tip-of-the-day")
    private List<TipOfTheDay> tips;

    public Topic() {
    }

    public List<TipOfTheDay> getTips() {
        return tips;
    }

    public List<TipOfTheDay> getByProduct(String product) {
        return getByProduct(Product.valueOf(product.toUpperCase()));
    }

    public List<TipOfTheDay> getByProduct(Product product) {
        return tips.stream()
                .filter(tip -> tip.getProducts().contains(product))
                .collect(Collectors.toList());
    }

    public List<TipOfTheDay> getSpecificForProduct(Product product) {
        List<TipOfTheDay> out = getByProduct(product);
        out.removeIf(multipleProduct);
        return out;
    }

    public List<TipOfTheDay> getSpecificForProduct(String product) {
        return getSpecificForProduct(Product.valueOf(product.toUpperCase()));
    }

    public int getNumByProduct(Product product) {
        return getByProduct(product).size();
    }

    public int getNumByProduct(String product) {
        return getNumByProduct(Product.valueOf(product.toUpperCase()));
    }

    public List<String> getDuplicateIds() {
        List<String> out = new ArrayList<>();
        for (int tip1 = 0; tip1 < tips.size(); tip1++) {
            for (int tip2 = tip1 + 1; tip2 < tips.size(); tip2++) {
                String id1 = tips.get(tip1).getId();
                if (id1 == null) continue;
                String id2 = tips.get(tip2).getId();
                if (id1.equals(id2) && !out.contains(id1)) out.add(tips.get(tip2).getId());
            }
        }
        return out;
    }
}
