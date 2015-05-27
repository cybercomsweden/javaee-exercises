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
package com.cybercom.javaee.repository;

import com.cybercom.javaee.entity.Item;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Repository for Item. Provide CRUD operations.
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Dependent
public class ItemRepository {

   @PersistenceContext(unitName = "shoppinglistPU")
   private EntityManager em;

   /**
    * Stores a new item in the database.
    * 
    * @param entity The new item
    */
   public final void create(Item entity) {
      em.persist(entity);
   }

   /**
    * Updates an existing item in the database.
    * 
    * @param entity The item
    */
   public final void update(Item entity) {
      em.merge(entity);
   }

   /**
    * Finds an item in the database.
    * 
    * @param id The item id
    * @return an Optional of Item
    */
   public final Optional<Item> find(Object id) {
      return Optional.ofNullable(em.find(Item.class, id));
   }

   /**
    * Retrieves all items from the database.
    * 
    * @return List of items
    */
   public final List<Item> findAll() {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Item.class));
      return em.createQuery(cq).getResultList();
   }

   /**
    * Deletes an item from the database.
    * 
    * @param entity The entity
    */
   public final void delete(Item entity) {
      em.remove(em.merge(entity));
   }

}
