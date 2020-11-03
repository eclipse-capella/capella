/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
 * on all hierarchy of classes/interfaces of the common class of the given sources elements.
 * 
 */
public class ObjectPropertiesLoader extends PropertiesLoader {

  HashMap<String, Class> _clazzes = new HashMap<String, Class>();

  /**
   * Retrieve a properties for the common class of given sources
   * 
   * The returned properties is compound of all Properties following adapt(class.canonicalName) 
   * on all hierarchy of classes/interfaces of the common class
   * @param sources
   * @return
   */
  public String getIdentifier(Collection<Object> sources) {
    String idProperties = "";
    //Find common class between all sources.
    if (!sources.isEmpty()) {
      Iterator<Object> sourceIt = sources.iterator();
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
   * @param toVisitId
   * @param idProperties
   */
  @Override
  protected void initToVisit(LinkedList<String> toVisitId, String idProperties) {
    for (String id : idProperties.split("__")) {
      toVisitId.add(id);
    }
  }

  /**
   * by default the ObjectPropertiesLoader will load properties defined in eclipse extensions 
   * following class.canonicalName as propertiesId.
   * 
   * Allow to retrieve a custom propertiesId from a canonicalName
   * @param canonicalName
   * @return
   */
  protected String adapt(String canonicalName) {
    return canonicalName;
  }

  @Override
  protected Collection<String> computeInheritancy(Properties properties) {
    Collection<String> parents = super.computeInheritancy(properties);

    //Add additional class relationship
    Class child = _clazzes.get(properties.getPropertiesId());
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
