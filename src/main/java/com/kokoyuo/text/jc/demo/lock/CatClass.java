package com.kokoyuo.text.jc.demo.lock;

/**
 * @author lixuanwen
 * @date 2020-07-08 15:23
 */
public class CatClass extends AnimalClass{

    public static void say(){
        System.out.println("hello cat");
    }

    public static void main(String[] args) {
        AnimalClass.say();
        CatClass.say();
        AnimalClass animalClass = new CatClass();
        animalClass.say();

        CatClass catClass = new CatClass();
        catClass.say();

        CatClass cc = (CatClass) new AnimalClass();
        cc.say();
    }
}
