/*
 * The MIT License
 *
 * Copyright 2015 Ivar Grimstad (ivar.grimstad@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cybercom.javaee.service;

import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.exception.ItemNotFoundException;
import com.cybercom.javaee.repository.ItemRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Service providing all functionality for a shopping list.
 * 
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Stateless
public class ShoppingListService {
   
   @Inject
   private ItemRepository itemRepository;
   
   /**
    * Adds an item to a shopping list.
    * 
    * @param item The item to add
    */
   public void addItemToList(Item item) {
      itemRepository.create(item);
   }
   
   /**
    * Updates an item in the shopping list.
    * 
    * @param id The item id
    * @param item The updated item information
    */
   public void updateItem(String id, Item item) {
      Item found = itemRepository.find(Long.parseLong(id))
              .orElseThrow(ItemNotFoundException::new);

      found.setAmount(item.getAmount());
      found.setDescription(item.getDescription());
      
      itemRepository.update(found);
   }
   
   /**
    * Removes an item from th shopping list.
    * 
    * @param id The item id to remove
    */
   public void deleteItem(String id) {
      
      Item item = itemRepository.find(Long.parseLong(id))
              .orElseThrow(ItemNotFoundException::new);
      
      itemRepository.delete(item);
   }
   
   /**
    * Fetches an item from the shopping list.
    * 
    * @param id The id of the item to fetch
    * @return The item
    */
   public Item getShoppingListItem(String id) {
      return itemRepository.find(Long.parseLong(id))
              .orElseThrow(ItemNotFoundException::new);
   }
   
   /**
    * Fetches the shopping list.
    * 
    * @return List of shopping list items
    */
   public List<Item> getShopplingList() {
      return itemRepository.findAll();
   }
}
