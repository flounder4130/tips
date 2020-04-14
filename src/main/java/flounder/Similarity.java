package flounder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Similarity implements Comparable<Similarity>{
    private int value;
    private TipOfTheDay tip1;
    private TipOfTheDay tip2;

    public Similarity(TipOfTheDay tip1, TipOfTheDay tip2) {
        Set<String> set1 = new HashSet<>(tip1.getProcessed());
        Set<String> set2 = new HashSet<>(tip2.getProcessed());
        set1.retainAll(set2);
        this.value = set1.size();
        this.tip1 = tip1;
        this.tip2 = tip2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Similarity that = (Similarity) o;
        return (tip1.equals(that.tip1) && tip2.equals(that.tip2))||(tip1.equals(that.tip2) && tip2.equals(that.tip1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tip1, tip2);
    }

    @Override
    public int compareTo(Similarity o) {
        return this.value - o.value;
    }

    public String toString(){
        return tip1.toString() + "\n"+ tip2.toString() + "\nsimilarity: " + value;
    }

    public int getValue() {
        return value;
    }

}