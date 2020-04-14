package flounder;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.List;
import java.util.Set;

@Root
@Convert(TipOfTheDay.CustomConverter.class)
public class TipOfTheDay {

    private String content = "";

    private String productString;

    private String id;

    private List<Product> products;

    private Set<String> processed;

    public TipOfTheDay() {
        super();
    }

    void setContent(String content) {
        this.content = content;
    }

    public List<Product> getProducts() {
        if (products == null) products = Product.parseProducts(productString);
        return products;
    }

    @Override
    public String toString(){
        return getDetails();
    }

    private String getDetails(){
        return "id: " + getId() + " |" +
                " products: " + getProductString();
    }

    public Set<String> getProcessed() {
        if (processed==null) processed = Tokenizer.tokenizeAndStem(content);
        return processed;
    }

    private String getProductString() {
        return productString;
    }

    public void setProductString(String productString) {
        this.productString = productString;
    }

    private String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    static class CustomConverter implements Converter<TipOfTheDay> {
        @Override
        public TipOfTheDay read(InputNode node) throws Exception {
            final StringBuilder sb = new StringBuilder(1024);
            concatNodesTree(sb, node);
            TipOfTheDay tip = new TipOfTheDay();
            tip.setContent(sb.toString());
            //id
            InputNode id = node.getAttribute("id");
            if (id!=null) tip.setId(id.getValue());
            //products
            InputNode products = node.getAttribute("product");
            if (products!=null) tip.setProductString(products.getValue());
            return tip;
        }

        private void concatNodesTree(StringBuilder sb, InputNode root) throws Exception {
            if (root.isElement()) sb.append("<").append(root.getName()).append(">");
            String value = root.getValue();
            if (value != null) sb.append(value);
            InputNode node = root.getNext();
            while (node != null) {
                concatNodesTree(sb, node);
                value = root.getValue();
                if (value != null) sb.append(value);
                node = root.getNext();
            }
            if (root.isElement()) sb.append("</").append(root.getName()).append(">");
        }

        @Override
        public void write(OutputNode node, TipOfTheDay value) {
            // not implemented
        }
    }
}


