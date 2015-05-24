/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.config;

import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.Produces;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@ApplicationScoped
public class ConfigProducer {

   /**
    * Injects a string configuration value.
    *
    * @param ip the injecting class
    * @return string configuration
    */
   @Produces
   @Config
   public String getConfigurationValue(InjectionPoint ip) {
      return getValue(ip.getAnnotated().getAnnotation(Config.class).key());
   }

   /**
    * Injects a BigInteger configuration value.
    *
    * @param ip the injecting class
    * @return string configuration
    */
   @Produces
   @Config
   public BigInteger getConfigurationBigInt(InjectionPoint ip) {
      return new BigInteger(getValue(ip.getAnnotated().getAnnotation(Config.class).key()));
   }

   /**
    * Injects an int configuration value.
    *
    * @param ip the injecting class
    * @return int configuration
    */
   @Config
   @Produces
   public int getConfigurationInt(InjectionPoint ip) {
      try {
         return Integer.parseInt(getValue(ip.getAnnotated().getAnnotation(Config.class).key()));
      } catch (NumberFormatException e) {
//         logger.severe(() -> "Invalid format for " + ip.getAnnotated().getAnnotation(Config.class).key() + "=" + getValue(ip.getAnnotated().getAnnotation(Config.class).key()));
         throw new RuntimeException("Invalid format for " + ip.getAnnotated().getAnnotation(Config.class).key() + "=" + getValue(ip.getAnnotated().getAnnotation(Config.class).key()), e);
      }
   }

   /**
    * Injects a boolean configuration value.
    *
    * @param ip the injecting class
    * @return boolean configuration
    */
   @Config
   @Produces
   public boolean getConfigurationBool(InjectionPoint ip) {

      if (getValue(ip.getAnnotated().getAnnotation(Config.class).key()).equalsIgnoreCase("false")) {
         return Boolean.FALSE;
      }

      return Boolean.TRUE;
   }

   /**
    * Retrieves the configuration value for underlying service.
    *
    * @param configKey configuration key
    * @return configuration value
    */
   protected String getValue(String configKey) {
      if (configKey == null || configKey.isEmpty()) {
         throw new IllegalArgumentException("Configuration key is empty.");
      }
      return "";
   }

   @PostConstruct
   private void init()  {
   }
}
