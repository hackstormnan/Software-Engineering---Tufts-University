import jdk.jshell.spi.SPIResolutionException;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class Unit {
    public static Map<String, Throwable> testClass(String name) {
        Map<String, Throwable> map = new HashMap<>();
        Class<?> aClass = null;
        String testcase_name = "";
        Method problem_method = null;


        try {
            aClass = Class.forName(name);
            Constructor<?> constructor = aClass.getConstructor();
            Object o = constructor.newInstance();
            Method[] methods = aClass.getDeclaredMethods();


            List<Method> test = new ArrayList<>();
            List<Method> beforeclass = new ArrayList<>();
            List<Method> before = new ArrayList<>();
            List<Method> afterclass = new ArrayList<>();
            List<Method> after = new ArrayList<>();


            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    test.add(m);
                } else if (m.isAnnotationPresent(BeforeClass.class)) {
                    beforeclass.add(m);
                } else if (m.isAnnotationPresent(Before.class)) {
                    before.add(m);
                } else if (m.isAnnotationPresent(AfterClass.class)) {
                    afterclass.add(m);
                } else if (m.isAnnotationPresent(After.class)) {
                    after.add(m);
                }
            }

            test.sort(Comparator.comparing(cp -> cp.getName()));
            beforeclass.sort(Comparator.comparing(cp -> cp.getName()));
            before.sort(Comparator.comparing(cp -> cp.getName()));
            afterclass.sort(Comparator.comparing(cp -> cp.getName()));
            after.sort(Comparator.comparing(cp -> cp.getName()));


            for (Method method : beforeclass) {
                problem_method = method;
                testcase_name = method.getName();
                Annotation[] annotations_beforeclass = method.getAnnotations();
                if (annotations_beforeclass.length > 1) {
                    throw new UnsupportedOperationException();
                }

                String s = method.toString();
                if (!s.contains(" static ")) {
                    throw new UnsupportedOperationException();
                }

                method.invoke(o);
            }

            for (Method method : test) {
                Annotation[] annotations_test = method.getAnnotations();
                if (annotations_test.length > 1) {
                    throw new UnsupportedOperationException();
                }

                for (Method method1 : before) {
                    problem_method = method1;
                    testcase_name = method.getName();
                    Annotation[] annotations_before = method.getAnnotations();
                    if (annotations_before.length > 1) {
                        throw new UnsupportedOperationException();
                    }
                    method1.invoke(o);
                }

                try {
                    testcase_name = method.getName();
                    method.invoke(o);
                    map.put(testcase_name, null);
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    map.put(testcase_name, cause);
                }

                for (Method method2 : after) {
                    problem_method = method2;
                    Annotation[] annotations_after = method.getAnnotations();
                    if (annotations_after.length > 1) {
                        throw new UnsupportedOperationException();
                    }
                    testcase_name = method.getName();
                    method2.invoke(o);
                }
            }

            for (Method method : afterclass) {
                problem_method = method;
                Annotation[] annotations_afterclass = method.getAnnotations();
                if (annotations_afterclass.length > 1) {
                    throw new UnsupportedOperationException();
                }
                String s = method.toString();
                if (!s.contains(" static ")) {
                    throw new UnsupportedOperationException();
                }

                testcase_name = method.getName();
                method.invoke(o);
            }


        } catch (Exception e) {
            if (problem_method != null) {
                if (problem_method.isAnnotationPresent(BeforeClass.class) || problem_method.isAnnotationPresent(AfterClass.class)
                        || problem_method.isAnnotationPresent(Before.class)  || problem_method.isAnnotationPresent(After.class)) {
                    throw new RuntimeException();
                }
            }

            throw new UnsupportedOperationException();
        }



        return map;
    }


    public static Map<String, Object[]> quickCheckClass(String name) {
        Map<String, Object[]> map = new HashMap<>();
        Class<?> aClass = null;
        Map<String, List<Object>> name_list_map = new HashMap<>();
        Map<String, List<Object>> final_name_list_map = new HashMap<>();
        List<Object> final_permutations = new ArrayList<>();


        try {
            aClass = Class.forName(name);
            Constructor<?> constructor = aClass.getConstructor();
            Object o = constructor.newInstance();
            Method[] methods = aClass.getMethods();

            HashMap<String, Method> helper_map = new HashMap<>();


            List<String> property_method_name_list = new ArrayList<>();
            for (Method method : methods) {
                helper_map.put(method.getName(), method);                    //method name pair to method

                if (method.isAnnotationPresent(Property.class)) {            //all @property method name
                    property_method_name_list.add(method.getName());
                }
            }

            

            for (String method_name : property_method_name_list) {
                Method method = helper_map.get(method_name);
                AnnotatedType[] annotatedParameterTypes = method.getAnnotatedParameterTypes();


                for (AnnotatedType annotatedParameterType : annotatedParameterTypes) {                //type start loop, ex. @IntRange
                    List<Object> all_permutations = new ArrayList<>();                                //everything in one @property
                    if (annotatedParameterType instanceof AnnotatedParameterizedType) {               //everything in the parameter
                        Annotation annotation = annotatedParameterType.getAnnotations()[0];           //first annotation

                        if (annotation instanceof ListLength) {
                            ListLength listLength = (ListLength) annotation;
                            AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) annotatedParameterType;
                            AnnotatedType[] annotatedActualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
                            Annotation real_annotation = annotatedActualTypeArguments[0].getAnnotations()[0];           //Second Annotation

                            List<Object> data_list = new ArrayList<>();
                            if (real_annotation instanceof IntRange) {
                                IntRange getter = (IntRange) real_annotation;
                                for (int i = getter.min(); i <= getter.max(); i++) {
                                    data_list.add(i);
                                }
                            }
                            if (real_annotation instanceof StringSet) {
                                StringSet getter = (StringSet) real_annotation;
                                for (String string : getter.strings()) {
                                    data_list.add(string);
                                }
                            }
                            if (real_annotation instanceof ForAll) {
                                ForAll getter = (ForAll) real_annotation;
                                String name1 = getter.name();                          //method name
                                int times = getter.times();
                                Method method1 = helper_map.get(name1);                //method
                                for (int i = 0; i < times; i++) {
                                    Object invoke = method1.invoke(o);
                                    data_list.add(invoke);
                                }
                            }
                            List<List<Object>> all = new ArrayList<>();
                            get_list(all, data_list, listLength.min(), listLength.max());
                            all_permutations.addAll(all);

                            List<Object> temp = new ArrayList<>();
                            for (int i = 0; i < Math.min(all_permutations.size(), 100); i++) {
                                try {
                                    Object object = (Boolean) method.invoke(o, all_permutations.get(i));
                                    if (object instanceof Boolean && (boolean) object == false) {
                                        temp.add(all_permutations.get(i));
                                        break;
                                    }
                                } catch (Exception e) {
                                    temp.add(all_permutations.get(i));
                                    break;
                                }
                            }
                            map.put(method_name, null);
                            if (temp.size() > 0) {
                                Object[] objects_final = new Object[temp.size()];
                                for (int i = 0; i < temp.size(); i++) {
                                    objects_final[i] = temp.get(i);
                                }
                                map.put(method_name, objects_final);
                            }

                            return map;
                        }
                    } else {
                        List<Object> data_list = new ArrayList<>();
                        Annotation annotation = annotatedParameterType.getAnnotations()[0];           //first annotation
                        if (annotation instanceof StringSet) {
                            StringSet getter = (StringSet) annotation;
                            for (String string : getter.strings()) {
                                data_list.add(string);
                            }
                        }
                        if (annotation instanceof IntRange) {
                            IntRange getter = (IntRange) annotation;
                            for (int i = getter.min(); i <= getter.max(); i++) {
                                data_list.add(i);
                            }
                        }
                        if (annotation instanceof ForAll) {
                            ForAll getter = (ForAll) annotation;
                            String name1 = getter.name();                          //method name
                            int times = getter.times();
                            Method method1 = helper_map.get(name1);                //method
                            for (int i = 0; i < times; i++) {
                                Object invoke = method1.invoke(o);
                                data_list.add(invoke);
                            }
                        }

                        all_permutations.addAll(data_list);
                    }                                                             // type end loop, ex. @IntRange


                    List<Object> existed_permutations = new ArrayList<>();
                    if (!name_list_map.containsKey(method_name)) {
                        existed_permutations.addAll(all_permutations);
                    } else {
                        //need to fix!!!
                        existed_permutations.addAll(name_list_map.get(method_name));

                        List<Object> temp_final_permutations = new ArrayList<>();


                        get_permutation(temp_final_permutations, existed_permutations, all_permutations);


                        existed_permutations.clear();
                        existed_permutations.addAll(temp_final_permutations);

                    }

                    name_list_map.put(method_name, existed_permutations);

                }                                                                  //one @property ends
            }


            for (String method_name : name_list_map.keySet()) {
                if (!map.containsKey(method_name)) {
                    map.put(method_name, null);
                }
            }
            //all @property starts here
            for (String method_name : name_list_map.keySet()) {
                List<Object> objects = new ArrayList<>();
                List<Object> final_add = name_list_map.get(method_name);
                Method method = helper_map.get(method_name);
                map.put(method_name, null);


                for (int i = 0; i < Math.min(final_add.size(), 100); i++) {
                    List<Object> data_handle = new ArrayList<>();
                    try {
                        String data = final_add.get(i).toString();

                        StringBuilder sb = new StringBuilder();
                        for (int i1 = 0; i1 < data.length(); i1++) {
                            char c = data.charAt(i1);
                            if (c != ' ' && c != '[' && c != ']' && c != ',') {
                                sb.append(c);
                            } else {
                                if (sb.length() != 0) {
                                    String element = sb.toString();
                                    if (check_if_num(element)) {
                                        Boolean neg = check_if_negative(element);
                                        data_handle.add(string_to_int(element, neg));
                                    } else {
                                        data_handle.add(element);
                                    }
                                    sb.setLength(0);
                                }
                            }
                        }

                        if (sb.length() != 0) {
                            String element = sb.toString();
                            if (check_if_num(element)) {
                                Boolean neg = check_if_negative(element);
                                data_handle.add(string_to_int(element, neg));
                            } else {
                                data_handle.add(element);
                            }
                        }

                        if (!(Boolean) method.invoke(o, data_handle.toArray())) {

                            objects.addAll(data_handle);

                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        objects.addAll(data_handle);

                        break;
                    }
                }


                if (objects.size() > 0) {
                    Object[] objects_final = new Object[objects.size()];
                    for (int i = 0; i < objects.size(); i++) {
                        objects_final[i] = objects.get(i);
                    }

                    map.put(method_name, objects_final);
                }
            }


        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }


        return map;
    }


    public static int string_to_int (String string, Boolean neg) {
        int ans = 0;
        if (neg == false) {
            for (int i = 0; i < string.length(); i++) {
                ans = ans * 10 + (string.charAt(i)-'0');
            }
            return ans;
        } else {
            for (int i = 1; i < string.length(); i++) {
                ans = ans * 10 + (string.charAt(i)-'0');
            }
            return -ans;
        }
    }

    public static boolean check_if_num(String string) {
        if (string.charAt(0) == '-') {
            for (int i = 1; i < string.length(); i++) {
                if ((string.charAt(i)-'0') >= 0 && (string.charAt(i)-'0') <= 9) {
                    continue;
                }
                return false;
            }
        } else {
            for (int i = 0; i < string.length(); i++) {
                if ((string.charAt(i)-'0') >= 0 && (string.charAt(i)-'0') <= 9) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    public static boolean check_if_negative(String string) {
        return string.charAt(0) == '-';
    }

    public static void get_list(List<List<Object>> all, List<Object> data_list, int min, int max) {
        for (int i = min; i <= max; i++) {
            List<List<Object>> single_all = new ArrayList<>();
            List<Object> list = new ArrayList<>();
            get_list_by_single_length(single_all, list, data_list, i);
            all.addAll(single_all);
        }
    }

    public static void get_list_by_single_length(List<List<Object>> single_all, List<Object> list, List<Object> data_list, int length) {

        if (list.size() == length) {
            single_all.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < data_list.size(); i++) {
            list.add(data_list.get(i));
            get_list_by_single_length(single_all, list, data_list, length);
            list.remove(list.size()-1);
        }
    }

    public static void get_permutation(List<Object> temp_final_permutation, List<Object> final_permutation, List<Object> all_permutation) {
        for (Object o : final_permutation) {
            for (Object o1 : all_permutation) {
                List<Object> list = new ArrayList<>();
                list.add(o);
                list.add(o1);
                temp_final_permutation.add(new ArrayList<>(list));
            }
        }

    }
}

