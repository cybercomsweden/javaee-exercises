/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.service;

import com.cybercom.javaee.config.Config;
import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.exception.ItemNotFoundException;
import com.cybercom.javaee.repository.ItemRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Stateless
public class ShoppingListService {
   
   @Config(key="jall")
   @Inject
   private String remove;
   
   @Inject
   private ItemRepository itemRepository;
   
   public void foo() {
      System.out.println("Jall:" + remove);
   }
   
   public void addItemToList(Item item) {
      itemRepository.create(item);
   }
   
   public void updateItem(Item item) {
      itemRepository.update(item);
   }
   
   public void deleteItem(String id) {
      
      Item item = itemRepository.find(Long.parseLong(id))
              .orElseThrow(ItemNotFoundException::new);
      
      itemRepository.delete(item);
   }
   
   public Item getShoppingListItem(String id) {
      return itemRepository.find(Long.parseLong(id))
              .orElseThrow(ItemNotFoundException::new);
   }
   
   public List<Item> getShopplingList() {
      return itemRepository.findAll();
   }
}
