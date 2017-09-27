#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cache;

import java.util.LinkedHashMap;

/**
 * Description: Token缓存，用于防止创建订单重复提交
 * On 2017/9/15 14:52 created by LW
 */
public class TokenCache extends LinkedHashMap<String, String> {
    private static final int CAPACITY = 100;
    private static final float FACTORY = 0.75f;
    private static final TokenCache cache = new TokenCache();
    private TokenCache() {
        super(CAPACITY, FACTORY, true);
    }

    public static TokenCache getInstance() {
        return cache;
    }

    @Override
    public synchronized String put(String key, String value) {
        return super.put(key, value);
    }

    @Override
    public synchronized void clear() {
        super.clear();
    }
}
