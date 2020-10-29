import java.util.Objects;

public abstract class Rule implements Comparable<Rule> {
    private final Integer priority;
    private final Double weight;

    public Rule(final Integer priority, final Double weight) {
        this.priority = priority;
        this.weight = weight;
    }

    public Integer getPriority() {
        return priority;
    }

    public Double getWeight() {
        return weight;
    }

    public abstract void evaluate(final Netflow netflow);

    public int compareTo(final Rule rule) {
        return priority.compareTo(rule.getPriority());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return getPriority().equals(rule.getPriority()) &&
                getWeight().equals(rule.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriority(), getWeight());
    }
}
