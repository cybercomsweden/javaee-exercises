/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.xml.bind.annotation.XmlAccessType.NONE;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@XmlRootElement
@XmlAccessorType(NONE)
@Entity
@Table(name = "item")
public class Item implements Serializable {

   @Id
   @GeneratedValue(strategy = AUTO)       
   @Column(name = "id")
   private Long id;

   @XmlAttribute
   @Column(name = "description")
   private String description;
   
   @XmlAttribute
   @Column(name = "amount")
   private int amount;

   public Long getId() {
      return id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

}
