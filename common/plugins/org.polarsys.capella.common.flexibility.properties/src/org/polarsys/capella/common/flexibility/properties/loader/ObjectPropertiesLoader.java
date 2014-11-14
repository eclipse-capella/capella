/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.flexibility.properties.loader;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.polarsys.capella.common.flexibility.properties.property.Properties;

/**
 * A class to load given properties from class hierarchy
 * 
 * The returned properties is compound of all Properties following adapt(class.canonicalName) 
 * on all hierarchy of classes/interfaces of the common class of the given sources_p elements.
 * 
 */
public class ObjectPropertiesLoader extends PropertiesLoader {

  HashMap<String, Class> _clazzes = new HashMap<String, Class>();

  /**
   * Retrieve a properties for the common class of given sources_p
   * 
   * The returned properties is compound of all Properties following adapt(class.canonicalName) 
   * on all hierarchy of classes/interfaces of the common class
   * @param sources_p
   * @return
   */
  public String getIdentifier(Collection<Object> sources_p) {
    String idProperties = "";
    //Find common class between all sources_p.
    if (!sources_p.isEmpty()) {
      Iterator<Object> sourceIt = sources_p.iterator();
      String initialId = "";
      while (sourceIt.hasNext()) {
        Object source = sourceIt.next();
        if (source == null) {
          continue;
        }
        Class rootClass = source.getClass();

        LinkedList<Class> toVisit = new LinkedList<Class>();
        HashSet<Class> visited = new HashSet<Class>();

        toVisit.add(rootClass);
        while (!toVisit.isEmpty()) {
          Class visit = toVisit.removeFirst();

          if ((visit != null) && !visited.contains(visit)) {
            visited.add(visit);
            _clazzes.put(adapt(visit.getCanonicalName()), visit);
            for (Class itf : visit.getInterfaces()) {
              toVisit.add(itf);
            }
            if (visit.getSuperclass() != null) {
              toVisit.add(visit.getSuperclass());
            }
          }
        }

        initialId += adapt(rootClass.getCanonicalName());
        if (sourceIt.hasNext()) {
          initialId += "__";
        }
      }

      idProperties = initialId;
    }
    return idProperties;
  }

  /**
   * @param toVisitId_p
   * @param idProperties_p
   */
  @Override
  protected void initToVisit(LinkedList<String> toVisitId_p, String idProperties_p) {
    for (String id : idProperties_p.split("__")) {
      toVisitId_p.add(id);
    }
  }

  /**
   * by default the ObjectPropertiesLoader will load properties defined in eclipse extensions 
   * following class.canonicalName as propertiesId.
   * 
   * Allow to retrieve a custom propertiesId from a canonicalName
   * @param canonicalName_p
   * @return
   */
  protected String adapt(String canonicalName_p) {
    return canonicalName_p;
  }

  @Override
  protected Collection<String> computeInheritancy(Properties properties_p) {
    Collection<String> parents = super.computeInheritancy(properties_p);

    //Add additional class relationship
    Class child = _clazzes.get(properties_p.getPropertiesId());
    if (child != null) {
      for (Class itf : child.getInterfaces()) {
        parents.add(adapt(itf.getCanonicalName()));
      }
      if (child.getSuperclass() != null) {
        parents.add(adapt(child.getSuperclass().getCanonicalName()));
      }
    }

    return parents;
  }

}
