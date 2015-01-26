package io.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Username {
    
    public static Username create(String value){
        return new AutoValue_Username(value);
        
    }
    
    public abstract String value(); 
}
