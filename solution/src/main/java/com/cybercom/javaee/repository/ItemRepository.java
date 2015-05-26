/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Dependent
public class ItemRepository {
   
   @PersistenceContext(unitName = "shoppinglistPU")
   private EntityManager em;
   
    public final void create(Item entity) {
      em.persist(entity);
   }

   public final void update(Item entity) {
      em.merge(entity);
   }

   public final Optional<Item> find(Object id) {
      return Optional.ofNullable(em.find(Item.class, id));
   }

   public final List<Item> findAll() {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Item.class));
      return em.createQuery(cq).getResultList();
   }

   public final void delete(Item entity) {
      em.remove(em.merge(entity));
   }
   
}
