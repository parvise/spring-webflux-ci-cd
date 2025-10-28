package com.practice.micro.all.SD.pattern;

import java.io.Closeable;
import java.io.Serializable;

public class SingletonPattern implements Serializable , Cloneable {

    private static volatile SingletonPattern singletonPattern;

    // Private constructor to prevent instantiation
    private SingletonPattern() {
        if(singletonPattern != null) {
            throw new IllegalStateException("Instance already created. Use getInstance() method.");
        }
    }

    public static SingletonPattern getInstance() {
        if (singletonPattern == null) {
            synchronized (SingletonPattern.class) {
                if (singletonPattern == null) {
                    singletonPattern = new SingletonPattern();
                }
            }
        }
        return singletonPattern;
    }

    public void showMessage() {
        System.out.println("Singleton Pattern Instance: " + this);
    }

    protected Object readResolve() {
        // Ensure that the deserialized object is the same instance
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton Pattern cannot be cloned. Use getInstance() method.");
    }
}
