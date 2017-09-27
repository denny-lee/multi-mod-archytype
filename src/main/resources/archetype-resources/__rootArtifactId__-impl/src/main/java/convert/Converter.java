#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.convert;

public interface Converter<T, E> {
    void convert(T to, E from);
}
