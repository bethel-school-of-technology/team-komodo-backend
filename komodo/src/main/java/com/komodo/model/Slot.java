package com.komodo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="appointment")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
// private List<Integer> slots = new ArrayList<Integer>();
   //[1,2,3,4,5,6,0]
   private Date day;
   //One toOne to appointment class
   private Integer slot_1;//appointment id
   private Integer slot_2;
   private Integer slot_3;
   
   //....
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getSlot_1() {
        return slot_1;
    }

    public void setSlot_1(Integer slot_1) {
        this.slot_1 = slot_1;
    }

    public Integer getSlot_2() {
        return slot_2;
    }

    public void setSlot_2(Integer slot_2) {
        this.slot_2 = slot_2;
    }

    public Integer getSlot_3() {
        return slot_3;
    }

    public void setSlot_3(Integer slot_3) {
        this.slot_3 = slot_3;
    }

}
