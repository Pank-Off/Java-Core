package com.geekbrains.myjunit;

public class TestClass {

    @BeforeSuite
    public void FirstTest(){
        System.out.println("FirstTest is Done");
    }

    @Test(priority = 3)
    public void test1(){
        System.out.println("test1 is Done - 3");
    }

    @Test(priority = 6)
    public void test2(){
        System.out.println("test2 is Done - 6");
    }

    @Test(priority = 1)
    public void test3(){
        System.out.println("test3 is Done - 1");
    }

    @Test(priority = 5)
    public void test4(){
        System.out.println("test4 is Done - 5");
    }

    @Test(priority = 8)
    public void test26(){
        System.out.println("test26 is Done - 8");
    }

    @Test(priority = 10)
    public void test8(){
        System.out.println("test8 is Done - 10");
    }

    @Test
    public void test24(){
        System.out.println("test24 is Done - 5");
    }

    @Test(priority = 3)
    public void test34(){
        System.out.println("test34 is Done - 3");
    }


    @AfterSuite
    public void LastTest(){
        System.out.println("LastTest is Done");
    }

}
