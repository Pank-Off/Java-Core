package com.geekbrains.junit.test;

import com.geekbrains.junit.MainClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class MasTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new ArrayList<>(Arrays.asList(1, 2, 4, 4, 2, 3, 4, 1, 7)), new ArrayList<>(Arrays.asList(1, 7)), new ArrayList<>(Arrays.asList(1,1,1,4)), true},
                {new ArrayList<>(Arrays.asList(1, 2, 4, 4, 2, 3, 5, 1, 7)), new ArrayList<>(Arrays.asList(2,3,5,1, 7)), new ArrayList<>(Arrays.asList(1,1,1,2)), false},
                {new ArrayList<>(Arrays.asList(1, 2, 0, 4, 2, 4, 5, 1, 7)), new ArrayList<>(Arrays.asList(5, 1, 7)), new ArrayList<>(Arrays.asList(4,2,3,4)), false},
                {new ArrayList<>(Arrays.asList(1, 2, 1, 2, 2, 3, 5, 1, 7)), new ArrayList<>(Arrays.asList(1, 2, 1, 2, 2, 3, 5, 1, 7)),new ArrayList<>(Arrays.asList(4,2,1,4)), true}
                }
        );
    }
    private MainClass mainClass;
    private List<Integer> listExp, listAct, listMeth2;
    private boolean bool;

    public MasTest(List<Integer> list2, List<Integer> list1, List<Integer> listMet2, boolean bool)
    {
        listExp = list1;
        listAct = list2;
        listMeth2 = listMet2;
        this.bool = bool;
    }

    @Before
    public void init() {
        mainClass = new MainClass();
    }

    @Test
    public void MassivTest()
    {
        Assert.assertEquals(listExp, mainClass.mas(listAct));
    }

    @Test
    public void CheckTest(){
        Assert.assertEquals(bool,mainClass.checkMas(listMeth2));
    }
}
