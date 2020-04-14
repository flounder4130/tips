package flounder;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Root(strict = false, name = "topic")
public class Topic {

    Predicate<TipOfTheDay> multipleProduct = tip -> !(tip.getProducts().size() == 1);

    @SuppressWarnings("unused")
    @ElementList(inline = true, entry = "tip-of-the-day")
    private List<TipOfTheDay> tips;

    @SuppressWarnings("unused") // required for deserialization
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
                .collect(Collectors.toCollection(ArrayList::new));
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
}
