/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.rest;

import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.service.ItemService;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Path("items")
public class ItemsResource {
   
   @Inject
   private ItemResource itemResource;
   
   @EJB
   private ItemService itemService;
   
   @GET
   @Produces(APPLICATION_JSON)
   public Response getItems() {
      itemService.foo();
      return Response.ok(new ArrayList<>()).build();
   }       
   
   @POST
   @Consumes(APPLICATION_JSON)
   public Response create(@Valid Item item) {
      return Response.created(null).build();
   }
   
   @Path("{id}")
   public ItemResource itemResource() {
      return itemResource;
   }
}
