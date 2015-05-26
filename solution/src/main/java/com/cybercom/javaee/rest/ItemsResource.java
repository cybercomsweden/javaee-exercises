/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.rest;

import com.cybercom.javaee.entity.Item;
import com.cybercom.javaee.service.ShoppingListService;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Path("items")
public class ItemsResource {
   
   @Inject
   private ItemResource itemResource;
   
   @EJB
   private ShoppingListService shoppingListService;
   
   @Context
   private UriInfo uriInfo;
   
   @GET
   @Produces(APPLICATION_JSON)
   public Response getItems() {
      
      shoppingListService.foo();
      
      List<Item> items = shoppingListService.getShopplingList();
              
      return Response.ok(new GenericEntity<List<Item>>(items) {}).build();
   }       
   
   @POST
   @Consumes(APPLICATION_JSON)
   public Response create(@Valid Item item) {
      
      shoppingListService.addItemToList(item);
      
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      
      URI itemUri = uriBuilder.segment(item.getId().toString()).build();
      
      return Response.created(itemUri).build();
   }
   
   @Path("{id}")
   public ItemResource itemResource() {
      return itemResource;
   }
}
