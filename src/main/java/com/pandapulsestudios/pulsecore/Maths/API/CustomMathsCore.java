package com.pandapulsestudios.pulsecore.Maths.API;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CustomMathsCore {
    public Integer add(Integer... values){
        if(values.length == 0) return 0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total += values[i];
        return total;
    }

    public Long add(Long... values){
        if(values.length == 0) return 0L;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total += values[i];
        return total;
    }

    public Float add(Float... values){
        if(values.length == 0) return 0f;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total += values[i];
        return total;
    }

    public Double add(Double... values){
        if(values.length == 0) return 0.0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total += values[i];
        return total;
    }

    public Integer sub(Integer... values){
        if(values.length == 0) return 0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total -= values[i];
        return total;
    }

    public Long sub(Long... values){
        if(values.length == 0) return 0L;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total -= values[i];
        return total;
    }

    public Float sub(Float... values){
        if(values.length == 0) return 0f;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total -= values[i];
        return total;
    }

    public Double sub(Double... values){
        if(values.length == 0) return 0.0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total -= values[i];
        return total;
    }

    public Integer multi(Integer... values){
        if(values.length == 0) return 0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total *= values[i];
        return total;
    }

    public Long multi(Long... values){
        if(values.length == 0) return 0L;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total *= values[i];
        return total;
    }

    public Float multi(Float... values){
        if(values.length == 0) return 0f;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total *= values[i];
        return total;
    }

    public Double multi(Double... values){
        if(values.length == 0) return 0.0;
        var total = values[0];
        for(var i = 1; i < values.length; i++) total *= values[i];
        return total;
    }

    public Integer divide(Integer... values){
        if(values.length == 0) return 0;
        var total = values[0];
        for(var i = 1; i < values.length; i++){
            if(values[i] == 0) continue;
            total /= values[i];
        }
        return total;
    }

    public Long divide(Long... values){
        if(values.length == 0) return 0L;
        var total = values[0];
        for(var i = 1; i < values.length; i++){
            if(values[i] == 0) continue;
            total /= values[i];
        }
        return total;
    }

    public Float divide(Float... values){
        if(values.length == 0) return 0f;
        var total = values[0];
        for(var i = 1; i < values.length; i++){
            if(values[i] == 0) continue;
            total /= values[i];
        }
        return total;
    }

    public Double divide(Double... values){
        if(values.length == 0) return 0.0;
        var total = values[0];
        for(var i = 1; i < values.length; i++){
            if(values[i] == 0) continue;
            total /= values[i];
        }
        return total;
    }
}
