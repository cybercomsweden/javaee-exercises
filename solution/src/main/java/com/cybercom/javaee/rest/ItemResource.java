/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.rest;

import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.service.ItemService;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Dependent
public class ItemResource {

   @EJB
   private ItemService itemService;

   @PUT
   public Response update(@Valid Item item) {
      return Response.ok().build();
   }

   @GET
   @Produces(APPLICATION_JSON)
   public Response getItem(@PathParam("id") String id) {

      return Response.ok(new Item()).build();
   }
}
