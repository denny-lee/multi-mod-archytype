#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.entity;

/**
 * Description:
 * On 2017/9/5 14:46 created by LW
 */
public class TwoTuple<F, T> {
    private F first;
    private T second;

    public TwoTuple(F first, T second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
