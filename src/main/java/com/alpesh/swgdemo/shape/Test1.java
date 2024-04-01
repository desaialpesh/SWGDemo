package com.alpesh.swgdemo.shape;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
//        getOccurence("abcancdsrds");
    Singlton s = Singlton.getSinglton();
    System.out.println(s.hashCode());
    Singlton s1 = Singlton.getSinglton();
        System.out.println(s1.hashCode());
    }

    static void getOccurence(String s){

        Map<Character, Integer> map = new HashMap<>();
        for (int i =0 ;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault((s.charAt(i)),0)+1);
        }

        System.out.println(map);
    }
}

class Singlton{

    private static Singlton singlton;

    private Singlton(){
//        if (singlton == null){
//            singlton = new Singlton();
//        }
    }
    static Singlton getSinglton(){
        if (singlton == null){
            singlton = new Singlton();
            return singlton;
        }
        return singlton;
    }
}
