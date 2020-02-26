package com.geekbrains.myjunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class MyClass {
    public static void start(Class c) throws InvocationTargetException, IllegalAccessException, RuntimeException, InstantiationException {
        //Обработчик аннотаций
        Method[] methods = c.getDeclaredMethods();
        TestClass testClass = (TestClass) c.newInstance();
        int b = 0, a = 0;
        List<Integer> list = new ArrayList<>();
        for (Method o : methods) {
            if (o.getAnnotation(BeforeSuite.class) != null) {
                b++;
            } else if (o.getAnnotation(AfterSuite.class) != null) {
                a++;
            } else if (o.getAnnotation(Test.class) != null)
                list.add(o.getAnnotation(Test.class).priority());
        }

        if (a > 1)
            throw new RuntimeException("Слишком много AfterSuite");
        if (b > 1)
            throw new RuntimeException("Слишком много BeforeSuite");


        Collections.sort(list);
        for (Method o : methods) {
            if (o.getAnnotation(BeforeSuite.class) != null) {
                o.invoke(testClass);
                break;
            }

        }
        for (Integer integer : list) {
            for (Method o : methods) {
                if (o.getAnnotation(Test.class) != null && o.getAnnotation(Test.class).priority() == integer) {
                    o.invoke(testClass);
                    break;
                }
            }

        }
        for (Method o : methods) {
            if (o.getAnnotation(AfterSuite.class) != null) {
                o.invoke(testClass);
                break;
            }

        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        try {
            start(TestClass.class);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
