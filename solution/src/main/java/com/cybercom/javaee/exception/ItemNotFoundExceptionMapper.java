/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.exception;

import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

   @Override
   public Response toResponse(ItemNotFoundException exception) {
      return Response.status(NOT_FOUND).build();
   }
}
