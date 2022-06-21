package game.util;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Pair<T,V>
{
    private Optional<T> first = Optional.empty();
    private Optional<V> second = Optional.empty();

    public Pair(Optional<T> first, Optional<V> second)
    {
        this.first = first;
        this.second = second;
    }
    public Pair(T first, V second)
    {
        this.first = Optional.of(first);
        this.second = Optional.of(second);
    }

    public void setFirst(T first)
    {
        this.first = Optional.of(first);
    }
    public void setSecond(V second)
    {
        this.second = Optional.of(second);
    }

    public Optional<T> getFirst()
    {
        return first;
    }
    public Optional<V> getSecond()
    {
        return  second;
    }
}
