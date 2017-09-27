#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import ${package}.convert.Converter;

/**
 * Description:
 * On 2017/9/12 17:02 created by LW
 */
public class EntityHelper<T, E> {
    private Converter<T, E> converter;

    public EntityHelper(Converter<T, E> converter) {
        this.converter = converter;
    }

    public EntityHelper() {

    }

    public boolean process(T to, E from) {
        if (null != converter) {
            try {
                converter.convert(to, from);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
