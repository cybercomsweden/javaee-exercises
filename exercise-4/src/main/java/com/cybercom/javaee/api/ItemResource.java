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
import com.cybercom.javaee.service.ShoppingListService;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 * Simple REST resource for Item.
 * 
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Dependent
public class ItemResource {

   @EJB
   private ShoppingListService shoppingListService;

   /**
    * Updates an existing item.
    * 
    * @id The item id
    * @param item The updated item
    * @return HTTP 200 (OK) if update was successful, HTTP 204 (Not Found) if items does not exist.
    */
   @PUT
   public Response update(@PathParam("id") String id, @Valid Item item) {
      
      shoppingListService.updateItem(id, item);
      
      return Response.ok().build();
   }

   /**
    * Retrieves an item.
    * 
    * @param id The item id
    * @return HTTP 200 (OK) with item in response body, HTTP 404 (Not Found) if item does not exist
    */
   @GET
   @Produces(APPLICATION_JSON)
   public Response getItem(@PathParam("id") String id) {
      
      final Item item = shoppingListService.getShoppingListItem(id);

      return Response.ok(item).build();
   }
   
   /**
    * Deletes an item.
    * 
    * @param id The item id
    * @return HTTP 200 (OK) if deletion was successful, HTTP 404 (Not Found) if item does not exist
    */
   @DELETE
   public Response removeItem(@PathParam("id") String id) {
      
      shoppingListService.deleteItem(id);
      
      return Response.ok().build();
   }
}
