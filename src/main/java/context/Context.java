package context;

import java.util.HashMap;
import java.util.Map;

/**
 * Context application
 *
 * @author Vladimir Shkerin
 * @since 30.01.2017
 */
public class Context {

    private Map<Class<?>, Object> context = new HashMap<>();

    public void add(Class<?> clazz, Object object) {
        if (!context.containsKey(clazz)) {
            context.put(clazz, object);
        }
    }

    public Object get(Class clazz) {
        return context.get(clazz);
    }

}
