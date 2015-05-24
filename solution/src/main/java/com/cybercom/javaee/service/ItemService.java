/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.service;

import com.cybercom.javaee.config.Config;
import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.repository.ItemRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Stateless
public class ItemService {
   
   @Config(key="jall")
   @Inject
   private String remove;
   
   @Inject
   private ItemRepository itemRepository;
   
   public void foo() {
      System.out.println("Jall:" + remove);
   }
   
   public Item getShoppingListItem(Long id) {
      return itemRepository.find(id);
   }
}
