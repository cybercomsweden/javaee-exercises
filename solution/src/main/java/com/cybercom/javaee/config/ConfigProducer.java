/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybercom.javaee.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@ApplicationScoped
public class ConfigProducer {

   private int cacheTimeout;
   private long timestamp;
   private final Properties configurationProperties = new Properties();

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
      return new BigInteger(getConfigurationValue(ip));
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
         return Integer.parseInt(getConfigurationValue(ip));
      } catch (NumberFormatException e) {
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

      if (getValue(getConfigurationValue(ip)).equalsIgnoreCase("false")) {
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

      final long now = System.currentTimeMillis();

      if (cacheTimeout <= 0 || now - timestamp > cacheTimeout) {
         loadPropertiesFromFile();
      }

      return configurationProperties.getProperty(configKey);
   }

   @PostConstruct
   private void init() {

      cacheTimeout = Integer.parseInt(System.getProperty("system.config.cache.timeout")) * 60 * 1000;
      loadPropertiesFromFile();
   }

   private void loadPropertiesFromFile() {
      try (InputStream is = new FileInputStream(System.getProperty("configuration.properties.location"));) {
         configurationProperties.load(is);
      } catch (NullPointerException | IOException e) {
      }
   }
}
