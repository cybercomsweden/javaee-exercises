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
 * CDI Producer for configuration properties.
 * 
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@ApplicationScoped
public class ConfigProducer {

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
    * Retrieves the configuration value from the configuration store..
    *
    * @param configKey configuration key
    * @return configuration value
    */
   protected String getValue(String configKey) {
      if (configKey == null || configKey.isEmpty()) {
         throw new IllegalArgumentException("Configuration key is empty.");
      }

      final long now = System.currentTimeMillis();
      final long cacheTimeout = Integer.parseInt(System.getProperty("system.config.cache.timeout")) * 60 * 1000;
      
      if (cacheTimeout <= 0 || now - timestamp > cacheTimeout) {
         loadPropertiesFromFile();
      }

      return configurationProperties.getProperty(configKey);
   }

   @PostConstruct
   private void init() {
      loadPropertiesFromFile();
   }

   private void loadPropertiesFromFile() {
      try (InputStream is = new FileInputStream(System.getProperty("configuration.properties.location"));) {
         configurationProperties.load(is);
         timestamp = System.currentTimeMillis();
      } catch (NullPointerException | IOException e) {
      }
   }
}
