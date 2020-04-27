/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.polarsys.capella.common.flexibility.properties.property.Properties;
import org.polarsys.capella.common.flexibility.properties.property.PropertyGroup;
import org.polarsys.capella.common.flexibility.properties.property.PropertyOption;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.PropertiesSchemaConstants;

/**
 * A class to load given properties from eclipse extension mechanism
 */
public class PropertiesLoader {

  private HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();

  protected Collection<IConfigurationElement> extensions = null;

  public PropertiesLoader() {
    extensions = new ArrayList<IConfigurationElement>();
    extensions.addAll(Arrays.asList(Platform.getExtensionRegistry().getConfigurationElementsFor(PropertiesSchemaConstants.PropertiesSchema_ID)));
    for (IConfigurationElement element : extensions) {
      System.out.println("flexibility.properties: migration is required for " + element.getNamespaceIdentifier());
    }
    extensions.addAll(Arrays.asList(Platform.getExtensionRegistry().getConfigurationElementsFor(PropertiesSchemaConstants.PropertiesSchema_SCHEMA_ID)));

  }

  protected Properties getStoredProperties(String idProperties) {
    return propertiesMap.get(idProperties);
  }

  protected void setStoredProperties(String idProperties, Properties properties) {
    propertiesMap.put(idProperties, properties);
  }

  public IProperties getProperties(String idProperties) {
    Properties storedProperties = getStoredProperties(idProperties);
    if (storedProperties != null) {
      return storedProperties;
    }

    LinkedList<String> initialIds = new LinkedList<String>();
    LinkedList<String> toVisitId = new LinkedList<String>();
    LinkedList<String> visitedId = new LinkedList<String>();
    LinkedList<IConfigurationElement> toVisit = new LinkedList<IConfigurationElement>();

    LinkedList<Properties> createdProperties = new LinkedList<Properties>();
    LinkedList<String> createPropertiesId = new LinkedList<String>();

    //Create a properties for initial propertiesId
    Properties rootProperties = getStoredProperties(idProperties);
    if (rootProperties == null) {
      rootProperties = new Properties(idProperties);
      createdProperties.add(rootProperties);
      createPropertiesId.add(idProperties);
      setStoredProperties(idProperties, rootProperties);
    }

    //Retrieve a list of idProperties to visit
    initToVisit(initialIds, idProperties);
    toVisitId.addAll(initialIds);

    HashMap<Properties, HashSet<String>> mapInheritancy = new HashMap<Properties, HashSet<String>>();

    // Retrieve all properties related to the given idProperties (inheritance)
    while (toVisitId.size() > 0) {
      String idVisit = toVisitId.removeFirst();
      if (!visitedId.contains(idVisit)) {
        visitedId.add(idVisit);

        //Create a properties for wanted propertiesId
        Properties properties = getStoredProperties(idVisit);
        if (properties == null) {
          properties = new Properties(idVisit);
          createdProperties.add(properties);
          createPropertiesId.add(idVisit);
          setStoredProperties(idVisit, properties);
        }

        if (createdProperties.contains(properties)) {

          if (!mapInheritancy.containsKey(properties)) {
            mapInheritancy.put(properties, new HashSet<String>());
          }

          for (String propertiesId : computeInheritancy(properties)) {
            toVisitId.add(propertiesId);
            mapInheritancy.get(properties).add(propertiesId);
          }

        }
      }
    }

    visitedId.clear();

    //Retrieve all extensions to visit
    while (createPropertiesId.size() > 0) {
      String idVisit = createPropertiesId.removeFirst();
      if (!visitedId.contains(idVisit)) {
        visitedId.add(idVisit);

        for (IConfigurationElement extension : extensions) {
          if (PropertiesSchemaConstants.PropertiesSchema_PROPERTIES.equals(extension.getName())) {
            String id = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTIES__ID);
            if (id.equals(idVisit)) {
              toVisit.addFirst(extension);
            }
          }
        }
      }
    }

    //Add relationship between child/parents
    for (Properties properties : mapInheritancy.keySet()) {
      for (String parent : mapInheritancy.get(properties)) {
        Properties parentProperties = getStoredProperties(parent);
        properties.addParent(parentProperties);
      }
    }

    visitedId.clear();

    // Create related properties and a renderer for each of them
    for (IConfigurationElement element : toVisit) {
      String id = element.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTIES__ID);
      Properties properties = getStoredProperties(id);

      if (!createdProperties.contains(properties)) {
        continue;
      }
      for (IConfigurationElement extension : element.getChildren()) {

        // Create a group
        if (PropertiesSchemaConstants.PropertiesSchema_GROUP.equals(extension.getName())) {
          String groupId = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_GROUP__ID);
          String name = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_GROUP__NAME);
          String parentId = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_GROUP__PARENT);
          if (parentId == null) {
            parentId = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_GROUP__PARENT_ID);
            if (parentId != null) {
              System.out.println("flexibility.properties: migration is required on " + groupId + "> 'parendId' attribute instead of 'parent'" + parentId);
            }
          }

          properties.addGroup(new PropertyGroup(groupId, name, parentId));
        }

        // Create a property
        if (PropertiesSchemaConstants.PropertiesSchema_PROPERTY.equals(extension.getName())) {
          String propertyId = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__ID);
          String name = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__NAME);
          String description = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__DESCRIPTION);

          String group_id = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__GROUP);
          if (group_id == null) {
            group_id = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__GROUP_ID);
            if (group_id != null) {
              System.out.println("flexibility.properties: migration is required on " + propertyId + "> 'group_id' attribute instead of 'group'" + group_id);
            }
          }

          String enabled = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__ENABLED);

          ILoadableProperty item = null;
          try {
            item = (ILoadableProperty) extension.createExecutableExtension(PropertiesSchemaConstants.PropertiesSchema_PROPERTY__CLASS);
            item.setName(name);
            item.setDescription(description);
            item.setId(propertyId);
            item.setGroupId(group_id);
            item.setEnabled(Boolean.valueOf(enabled).booleanValue());
          } catch (CoreException exception) {
            // Nothing here
          }

          if (item != null) {
            properties.addItem(item);

            // Associate options
            for (IConfigurationElement option : extension.getChildren(PropertiesSchemaConstants.PropertiesSchema_OPTION)) {
              String id2 = option.getAttribute(PropertiesSchemaConstants.PropertiesSchema_OPTION__ID);
              String name2 = option.getAttribute(PropertiesSchemaConstants.PropertiesSchema_OPTION__NAME);
              String description2 = option.getAttribute(PropertiesSchemaConstants.PropertiesSchema_OPTION__DESCRIPTION);
              String value2 = option.getAttribute(PropertiesSchemaConstants.PropertiesSchema_OPTION__VALUE);
              String enabled2 = option.getAttribute(PropertiesSchemaConstants.PropertiesSchema_OPTION__ENABLED);
              PropertyOption item2 = new PropertyOption(id2, value2, name2, description2, Boolean.valueOf(enabled2).booleanValue());
              item.addOption(item2);
            }

            // Associate arguments
            for (IConfigurationElement argument : extension.getChildren(PropertiesSchemaConstants.PropertiesSchema_ARGUMENT)) {
              item.addParameter(argument.getAttribute(PropertiesSchemaConstants.PropertiesSchema_ARGUMENT__ID),
                  argument.getAttribute(PropertiesSchemaConstants.PropertiesSchema_ARGUMENT__VALUE));
            }

            // Associate parameters
            for (IConfigurationElement argument : extension.getChildren(PropertiesSchemaConstants.PropertiesSchema_PARAMETER)) {
              item.addParameter(argument.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PARAMETER__ID),
                  argument.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PARAMETER__VALUE));
            }

          }
        }
      }
    }

    //Create hierarchical links to initial ids
    if (createdProperties.contains(rootProperties)) {
      for (String id : initialIds) {
        Properties parent = getStoredProperties(id);
        if ((parent != null) && (parent != rootProperties)) {
          rootProperties.addParent(parent);
        }
      }
    }

    //Find to pack elements
    HashSet<IProperties> toPack = new HashSet<IProperties>();
    for (Properties properties : createdProperties) {
      if (properties.getGroups().isEmpty() && properties.getItems().isEmpty()) {
        toPack.add(properties);
      }
    }

    //Pack properties to avoid empty parents.
    for (Properties properties : createdProperties) {
      boolean isPacked = false;
      while (!isPacked) {
        isPacked = true;
        HashSet<IProperties> toPackParent = new HashSet<IProperties>();
        for (IProperties parent : properties.getParents()) {
          if (toPack.contains(parent)) {
            toPackParent.add(parent);
          }
        }

        for (IProperties parent : toPackParent) {
          for (IProperties parentParent : parent.getParents()) {
            properties.addParent(parentParent);
            if (isPacked && toPack.contains(parentParent)) {
              isPacked = false;
            }
          }
          properties.removeParent(parent);
        }
      }
    }

    return rootProperties;
  }

  /**
   * Returns a list parent propertiesId for the given properties. 
   * 
   * By default, read <inheritance> link through eclipse extensions
   * @param properties
   * @return
   */
  protected Collection<String> computeInheritancy(Properties properties) {
    ArrayList<String> parents = new ArrayList<String>();

    for (IConfigurationElement extension : extensions) {
      if (PropertiesSchemaConstants.PropertiesSchema_PROPERTIES.equals(extension.getName())) {
        String id = extension.getAttribute(PropertiesSchemaConstants.PropertiesSchema_PROPERTIES__ID);
        if (id.equals(properties.getPropertiesId())) {
          for (IConfigurationElement parent : extension.getChildren(PropertiesSchemaConstants.PropertiesSchema_INHERITANCE)) {
            String propertiesId = parent.getAttribute(PropertiesSchemaConstants.PropertiesSchema_INHERITANCE__PROPERTIES);
            if (propertiesId == null) {
              propertiesId = parent.getAttribute(PropertiesSchemaConstants.PropertiesSchema_INHERITANCE__PROPERTIESID);
            }
            parents.add(propertiesId);
          }
        }
      }
    }

    return parents;
  }

  /**
   * @param toVisitId
   * @param idProperties
   */
  protected void initToVisit(LinkedList<String> toVisitId, String idProperties) {
    toVisitId.add(idProperties);
  }

}
