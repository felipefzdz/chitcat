package io.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Username implements Comparable {
    
    public static Username create(String value){
        return new AutoValue_Username(value);
        
    }
    
    public abstract String value();

    @Override
    public int compareTo(Object o) {
        return value().compareTo(((Username)o).value());
    }
    
    
}
