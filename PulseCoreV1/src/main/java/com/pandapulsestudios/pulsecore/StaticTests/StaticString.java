package com.pandapulsestudios.pulsecore.StaticTests;

public class StaticString {
    public static String CASE(String text, boolean uppercase, boolean lowercase){
        if(uppercase) return text.toUpperCase();
        else if(lowercase) return text.toLowerCase();
        return text;
    }

    public static Boolean TEST(String a, String b, boolean uppercase, boolean lowercase){
        return CASE(a, uppercase, lowercase).equals(CASE(b, uppercase, lowercase));
    }

    public static String SENTENCE(String[] a, char space){
        var sbuilder = new StringBuilder();
        for(var i = 0; i < a.length; i++){
            sbuilder.append(a[i]);
            if(i < a.length - 1) sbuilder.append(space);
        }
        return sbuilder.toString();
    }
}
