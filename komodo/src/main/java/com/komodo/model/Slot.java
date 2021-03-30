package com.komodo.model;

import java.util.ArrayList;
import java.util.List;

public class Slot {
    
    private Long id;
    
    private List<Boolean> slots = new ArrayList<Boolean>();
   
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Boolean> getSlots() {
        return slots;
    }
    
    public void setSlots(int slot) {
        for(int i=1; i<6; i++){
            slots.add(false);
        }

        
        slots.set(slot,true); 
        
        
    }

    
}
