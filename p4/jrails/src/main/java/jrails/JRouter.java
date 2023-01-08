package jrails;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JRouter {

    HashMap<String, Router> map = new HashMap<>();
    public String get_name(String verb, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(verb);
        stringBuilder.append(path);

        return stringBuilder.toString();
    }

    public void addRoute(String verb, String path, Class clazz, String method) {
        String name = get_name(verb, path);
        Router router = new Router(verb, path, clazz, method);


        map.put(name, router);
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        try {
            String name = get_name(verb, path);
            if (!map.containsKey(name)) {
                return null;
            }

            StringBuilder stringBuilder = new StringBuilder();
            Router router = map.get(name);
            stringBuilder.append(router.clazz.getName());
            stringBuilder.append("#");
            stringBuilder.append(router.method);

            return stringBuilder.toString();
        } catch (Exception exception) {
            throw new UnsupportedOperationException();
        }
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        try {
            String name = get_name(verb, path);
            Router router;
            if (map.containsKey(name)) {
                router = map.get(name);
            } else {
                throw new UnsupportedOperationException();
            }

            Class clazz = router.clazz;
            String method = router.method;
            Method method1 = clazz.getMethod(method, Map.class);

            return (Html) method1.invoke(null, params);

        } catch (Exception exception) {
            throw new UnsupportedOperationException();
        }

    }

    public class Router {
        String verb;
        String path;
        Class clazz;
        String method;

        public Router(String verb, String path, Class clazz, String method) {
            this.verb = verb;
            this.path = path;
            this.clazz = clazz;
            this.method = method;
        }
    }
}
