package com.geekbrains.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainClass {
    public List<Integer> mas(List<Integer> m) {
        List<Integer> array = new ArrayList<>();
        try {
            if (!m.contains(4))
                throw new RuntimeException("Не содержит четверок =(");
            for (int i = m.size() - 1; m.get(i) != 4; i--) {
                array.add(m.get(i));
            }
            Collections.reverse(array);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            return m;
            }
        return array;
    }



    public boolean checkMas(List<Integer> m){
        return (!m.contains(1) || m.contains(4)) && (!m.contains(4) || m.contains(1));
    }
}
