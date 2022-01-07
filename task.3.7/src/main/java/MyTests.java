import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyTests {

    public static void start(String className) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        start(testClass);
    }

    public static void start(Class testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<String, Method> sortedMethods = new HashMap<>();

        for (Method method : testClass.getDeclaredMethods()) {
            String index;
            if (method.getAnnotation(Test.class) != null) {
                Test ann = method.getDeclaredAnnotation(Test.class);
                int priority = ann.priority();
                if (priority < 1 || priority > 10) throw new RuntimeException("Wrong priority in method "+method.getName());
                index = String.valueOf(priority);
                if (index.length() == 1) {
                    index = "0"+index;
                }
            }
            else if (method.getAnnotation(BeforeSuit.class) != null) {
                index = "0";
                if (sortedMethods.get(index) != null) {
                    throw new RuntimeException("Duplicate unique annotation  "+method.getName());
                }
            }
            else if (method.getAnnotation(AfterSuit.class) != null) {
                index = "A";
                if (sortedMethods.get(index) != null) {
                    throw new RuntimeException("Duplicate unique annotation  "+method.getName());
                }
            }
            else {
                continue;
            }

            String sortedIndex = String.valueOf(index);
            if (sortedMethods.containsKey(sortedIndex)) {
                sortedIndex += "1";
            }
            sortedMethods.put(sortedIndex ,method);
        }

        Constructor constructor = testClass.getDeclaredConstructor();
        Object testObj = constructor.newInstance();

        SortedSet<String> keys = new TreeSet<>(sortedMethods.keySet());
        for (String key : keys) {
            Method method = sortedMethods.get(key);

            System.out.println("["+key+"] name = "+method.getName()+", returnType = "+method.getReturnType().getName()+
                    ", parameters"+ Arrays.toString(method.getParameterTypes()));

            method.setAccessible(true);
            method.invoke(testObj);
        }

    }

}
