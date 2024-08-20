package com.congdinh.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericTypeResolver {
    @SuppressWarnings("unchecked")
    public static <T> Class<T> resolveGenericType(Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        while (!(type instanceof ParameterizedType) && clazz != Object.class) {
            clazz = clazz.getSuperclass();
            type = clazz.getGenericSuperclass();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        throw new IllegalArgumentException("Class " + clazz.getName() + " does not have generic type parameters.");
    }
}