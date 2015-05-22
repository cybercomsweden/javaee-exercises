/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
public class ItemRepository {
   
   @PersistenceContext(unitName = "shoppinglistPU")
   private EntityManager em;
   
}
