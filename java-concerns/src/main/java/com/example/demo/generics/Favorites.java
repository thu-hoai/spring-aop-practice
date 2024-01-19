package com.example.demo.generics;

import org.bouncycastle.math.ec.custom.sec.SecT113Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Typesafe heterogeneous containers - Item 33 Effective Java
 */
public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");

        // Achieving runtime type safety with a dynamic cast
        // Same trick can be found in the following implementations
        // checkedSet, checkedList, checkedMap, and so forth.
//        favorites.put(type, type.cast(instance));
        favorites.put(Objects.requireNonNull(type), instance);
    };

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    };

    public static void main(String[] args) {
        Favorites favorite = new Favorites();
        favorite.putFavorite(String.class, "Java");
        favorite.putFavorite(Class.class, Favorites.class);
        favorite.putFavorite(Object.class, Favorites.class);

        String favoriteString = favorite.getFavorite(String.class);
        System.out.println(favoriteString);

        // testing for using raw type List and List<Object>
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(41));
        String s = strings.get(0); // compiler-generated cast
        // s.contains("hello world");

        //  List<Object> and List<?>
        List<Object> objList = new ArrayList<>();
        List<?> wildcardList = new ArrayList<String>();
        objList.add(""); // works
//        wildcardList.add("");// compile-time error

        test();
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result ++;
            }
        }
        return result;
    }

    static int numElementsInCommonUnboundWildcard(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result ++;
            }
        }
        return result;
    }
    static void test() {

//        List l1;
//        List<String> stringList = new ArrayList<>();
//        l1 = stringList;
//
//        List<Object>

        List unsafeList = new ArrayList();
        unsafeList.add("a");
        unsafeList.add(123);

        List<Object> unsafeList1 = new ArrayList();
        unsafeList1.add("a");
        unsafeList1.add(123);
        unsafeList1.get(0);

        List<?> wildCardList = new ArrayList<>();
        // wildCardList.add("a");

        List<?> listOfAnyType;
        List<Object> listOfObject = new ArrayList<Object>();
        List<String> listOfString = new ArrayList<String>();
        List<Integer> listOfInteger = new ArrayList<Integer>();

        listOfAnyType = listOfString; //legal
        listOfAnyType = listOfInteger; //legal
        // listOfObjectType = (List<Object>) listOfString; //compiler error - in-convertible types
    }
}
