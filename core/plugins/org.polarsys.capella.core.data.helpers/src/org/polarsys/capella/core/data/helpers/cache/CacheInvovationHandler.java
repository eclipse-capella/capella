/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.BiFunction;

/**
 * This class creates a proxy caching all calls to methods annotated by @CacheResult with ModelCache.
 */
public class CacheInvovationHandler implements InvocationHandler {

  private Object delegatedHelper;

  private HashSet<Method> cachedMethods = new HashSet<>();

  private BiFunction<Method, Object[], Object> function = null;

  public CacheInvovationHandler(Object target) {
    delegatedHelper = target;

    // Retrieve annotated methods
    for (Method m : target.getClass().getMethods()) {
      for (Class<?> itf : delegatedHelper.getClass().getInterfaces()) {
        try {
          Method m2 = itf.getMethod(m.getName(), m.getParameterTypes());
          if (m2 != null) {
            if (m.isAnnotationPresent(CacheResult.class) || m2.isAnnotationPresent(CacheResult.class)) {
              cachedMethods.add(m2);
            }
          }
        } catch (NoSuchMethodException e) {
          // Nothing here
        } catch (SecurityException e) {
          // Perhaps doing something here
          e.printStackTrace();
        }
      }
    }

    // Create a lambda invoking the method into the delegated handler
    function = ((method, args) -> {
      try {
        return method.invoke(delegatedHelper, args);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
      return null;
    });
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    long dureeCache = 0;
    Object cache = null;
    if (cachedMethods.contains(method)) {
      long init = System.nanoTime();
      cache = ModelCache.getCache(function, method, args);
      long end = System.nanoTime();
      dureeCache = end - init;
    } else {
     // System.out.println();
    }
    if (cache != null) {
      // System.out.printf("Cache: %s %d %d\n", method.getName(), hash, dureeCache);
      // return cache;
    }

    long dureeSansCache = 0;
    long init2 = System.nanoTime();
    Object result = null;
    result = method.invoke(delegatedHelper, args);
    long end2 = System.nanoTime();
    dureeSansCache = end2 - init2;

    if (dureeCache > 0) {
      if (dureeSansCache < dureeCache) {
       //  int hash = args.hashCode();
        // System.out.printf("Cache: %s %d %d\n", method.getName(), hash, dureeCache);
        // System.out.printf("Sans: %s %d %d\n", method.getName(), hash, dureeSansCache);
      } else {
        // System.out.printf("Cache ok: %s \n", method.getName());
      }
    }
    return result;
  }

  public static void toStringRatio() {
    HashSet<Method> methods = new HashSet<>();
  }

  @SuppressWarnings({ "unchecked" })
  public static <T> T createProxy(Class<?> clazz, InvocationHandler handler) {
    try {
      return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <T> T load(Class<?> clazz) {
    try {
      Object instance = clazz.getDeclaredConstructor().newInstance();
      return createProxy(instance.getClass(), createHandler(instance));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static InvocationHandler createHandler(Object instance) {
    return new CacheInvovationHandler(instance);
  }

  public String toString() {
    return delegatedHelper + ":" + CacheInvovationHandler.class.getSimpleName();
  }
}