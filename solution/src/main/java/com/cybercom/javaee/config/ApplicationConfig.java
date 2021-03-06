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
package com.cybercom.javaee.config;

import com.cybercom.javaee.api.HelloResource;
import com.cybercom.javaee.exception.ItemNotFoundExceptionMapper;
import com.cybercom.javaee.api.ItemResource;
import com.cybercom.javaee.api.ItemsResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS configuration.
 *
 * @author Ivar Grimstad <ivar.grimstad@gmail.com>
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

   @Override
   public Set<Class<?>> getClasses() {

      final Set<Class<?>> classes = new HashSet<>();

      classes.add(HelloResource.class);
      classes.add(ItemsResource.class);
      classes.add(ItemResource.class);

      classes.add(ItemNotFoundExceptionMapper.class);

      return classes;
   }

   /**
    * This method is required for enabling the ConfigProducer when running in GlassFish. 
    * Is not needed for e.g. WildFly.
    *
    * @see Application#getSingletons()
    * @return set of singletons.
    */
//   @Override
//   public Set<Object> getSingletons() {
//      Set<Object> singletons = new HashSet<>();
//      singletons.add(new ConfigProducer());
//      return singletons;
//   }
}
