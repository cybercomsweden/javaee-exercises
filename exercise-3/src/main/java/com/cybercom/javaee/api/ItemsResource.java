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
package com.cybercom.javaee.api;

import com.cybercom.javaee.entity.Item;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
 * Simple REST resource for items.
 * 
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Path("items")
public class ItemsResource {
   
   @Inject
   private ItemResource itemResource;
   
   @Context
   private UriInfo uriInfo;
   
   /**
    * Get all items.
    * 
    * @return List of all items
    */
   @GET
   @Produces(APPLICATION_JSON)
   public Response getItems() {
      
      List<Item> items = new ArrayList<>();
      
      // This is required for GlassFish (Jersey)
      return Response.ok(new GenericEntity<List<Item>>(items) {}).build();
      
      // This is ok for WildFly (RESTEasy)
//      return Response.ok(items).build();
   }       
   
   /**
    * Create a new item in the shopping list.
    * 
    * @param item The item
    * 
    * @return HTTP 201 (Created) with a URI to the new item in the Location element of the HTTP Header
    */
   @POST
   @Consumes(APPLICATION_JSON)
   public Response create(@Valid Item item) {
      
      item.setId(2L);
      
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      URI itemUri = uriBuilder.segment(item.getId().toString()).build();
      
      return Response.created(itemUri).build();
   }
   
   /**
    * Sub-resource locator for Item.
    * 
    * @return The REST resource for a single item
    */
   @Path("{id}")
   public ItemResource itemResource() {
      return itemResource;
   }
}
