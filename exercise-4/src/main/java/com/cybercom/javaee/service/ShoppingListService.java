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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;

/**
 * Service providing all functionality for a shopping list.
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Stateless
public class ShoppingListService {
   
   private final List<Item> shoppingList = new ArrayList<>();

   /**
    * Adds an item to a shopping list.
    *
    * @param item The item to add
    */
   public void addItemToList(Item item) {
      shoppingList.add(item);
      item.setId(2L);
   }

   /**
    * Updates an item in the shopping list.
    *
    * @param id The item id
    * @param item The updated item information
    */
   public void updateItem(String id, Item item) {
      shoppingList.stream()
              .filter(i -> i.getId().equals(Long.parseLong(id)))
              .forEach(i -> {
                 i.setAmount(item.getAmount());
                 i.setDescription(item.getDescription());
              });
   }

   /**
    * Removes an item from th shopping list.
    *
    * @param id The item id to remove
    */
   public void deleteItem(String id) {
      List<Item> filteredList = shoppingList.stream()
              .filter(i -> !i.getId().equals(Long.parseLong(id)))
              .collect(Collectors.toList());
      
      shoppingList.clear();
      shoppingList.addAll(filteredList);
   }

   /**
    * Fetches an item from the shopping list.
    *
    * @param id The id of the item to fetch
    * @return The item
    */
   public Item getShoppingListItem(String id) {
      Optional<Item> item = shoppingList.stream()
              .filter(i -> i.getId().equals(Long.parseLong(id)))
              .findAny();
      
      return item.orElseThrow(NotFoundException::new);
   }

   /**
    * Fetches the shopping list.
    *
    * @return List of shopping list items
    */
   public List<Item> getShopplingList() {
      return shoppingList;
   }
   @PostConstruct
   private void init() {
      shoppingList.add(new Item(100L, "Milk",2));
      shoppingList.add(new Item(200L, "Apples", 3));
   }
}
