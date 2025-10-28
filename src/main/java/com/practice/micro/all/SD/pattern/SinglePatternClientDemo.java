package com.practice.micro.all.SD.pattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SinglePatternClientDemo {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException, ClassNotFoundException, CloneNotSupportedException {
        SingletonPattern pattern = SingletonPattern.getInstance();

        SingletonPattern p4= (SingletonPattern)pattern.clone(); // This will throw CloneNotSupportedException if not handled


//        Constructor<SingletonPattern> pattern2=SingletonPattern.class.getDeclaredConstructor();
//        pattern2.setAccessible(true);
//        SingletonPattern p3 =pattern2.newInstance();
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("obj.ser"));
        oos.writeObject(pattern);

        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("obj.ser"));
        SingletonPattern p3 = (SingletonPattern) ois.readObject();


        System.out.println("Singleton Pattern Instance: " + p3);
        System.out.println("Singleton Pattern Instance: " + p4);
        System.out.println("Singleton Pattern Instance: " + pattern);

    }
}
