/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.properties.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 */
public class Properties implements IProperties {

  String idProperties;

  List<IPropertyGroup> groups;

  List<IProperty> items;

  Collection<IProperties> parents;

  LinkedList<IProperty> allProperties = null;

  LinkedList<IPropertyGroup> allGroups = null;

  /**
   * @param idProperties
   */
  public Properties(String idProperties) {
    this.idProperties = idProperties;

    groups = new ArrayList<IPropertyGroup>();
    items = new ArrayList<IProperty>();
    parents = new ArrayList<IProperties>();
  }

  /**
   * @param parent the parent to set
   */
  public void addParent(IProperties parent) {
    if (!parents.contains(parent)) {
      parents.add(parent);
      allGroups = null;
      allProperties = null;
    }
  }

  public void removeParent(IProperties parent) {
    parents.remove(parent);
    allGroups = null;
    allProperties = null;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IProperties> getParents() {
    return parents;
  }

  /**
   * {@inheritDoc}
   */
  public String getPropertiesId() {
    return idProperties;
  }

  public void addGroup(IPropertyGroup group) {
    for (IPropertyGroup iGroup : getGroups()) {
      if (iGroup.getId().equals(group.getId())) {
        return;
      }
    }
    allGroups = null;
    groups.add(group);
  }

  public List<IPropertyGroup> getGroups(IPropertyGroup group) {
    List<IPropertyGroup> ite = new ArrayList<IPropertyGroup>();
    for (IPropertyGroup item : getAllGroups()) {
      if (item != IPropertyGroup.EMPTY) {
        if (group == IPropertyGroup.EMPTY) {
          if ((item.getParentId() == null)) {
            ite.add(item);
          }
        } else if ((item.getParentId() != null) && item.getParentId().equals(group.getId())) {
          ite.add(item);
        }
      }
    }
    return ite;
  }

  public List<IPropertyGroup> getGroups() {
    return groups;
  }

  /**
   * @return
   */
  public Collection<IPropertyGroup> getAllGroups() {
    if (allGroups != null) {
      return allGroups;
    }

    LinkedList<IProperties> toVisit = new LinkedList<IProperties>();
    HashSet<IProperties> visited = new HashSet<IProperties>();
    LinkedList<IPropertyGroup> result = new LinkedList<IPropertyGroup>();

    toVisit.add(this);
    while (toVisit.size() > 0) {
      IProperties parent = toVisit.removeFirst();
      if (!visited.contains(parent)) {
        visited.add(parent);
        LinkedList<IPropertyGroup> temporary = new LinkedList<IPropertyGroup>();
        for (IPropertyGroup group : parent.getGroups()) {
          temporary.add(group);
        }
        while (temporary.size() > 0) {
          IPropertyGroup last = temporary.removeLast();
          if (!result.contains(last)) {
            result.addFirst(last);
          }
        }
      }
      toVisit.addAll(parent.getParents());
    }

    allGroups = result;
    return result;

  }

  public List<IProperty> getItems(IPropertyGroup group) {
    List<IProperty> ite = new ArrayList<IProperty>();
    for (IProperty item : getAllItems()) {
      AbstractProperty property = (AbstractProperty) item;
      if (group == IPropertyGroup.EMPTY) {
        if (property.getGroupId() == null) {
          ite.add(item);
        }
      } else if ((property.getGroupId() != null) && property.getGroupId().equals(group.getId())) {
        ite.add(item);
      }
    }
    return ite;
  }

  public Collection<IProperty> getItems() {
    return items;
  }

  public Collection<IProperty> getAllItems() {
    if (allProperties != null) {
      return allProperties;
    }

    LinkedList<IProperties> toVisit = new LinkedList<IProperties>();
    HashSet<IProperties> visited = new HashSet<IProperties>();

    LinkedList<IProperty> result = new LinkedList<IProperty>();
    HashSet<String> addedIds = new HashSet<String>();

    toVisit.add(this);
    while (toVisit.size() > 0) {
      IProperties properties = toVisit.removeFirst();
      if (!visited.contains(properties)) {
        visited.add(properties);
        LinkedList<IProperty> temporary = new LinkedList<IProperty>();
        for (IProperty property : properties.getItems()) {
          if (!temporary.contains(property)) {
            temporary.add(property);
          }
        }
        while (temporary.size() > 0) {
          IProperty last = temporary.removeLast();
          if (!result.contains(last) && !addedIds.contains(last.getId())) {
            result.addFirst(last);
            addedIds.add(last.getId());
          }
        }
        toVisit.addAll(properties.getParents());
      }
    }

    allProperties = result;
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public IProperty getProperty(String id) {
    for (IProperty item : items) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    for (IProperties parent : getParents()) {
      IProperty item = parent.getProperty(id);
      if (item != null) {
        return item;
      }
    }
    return null;
  }

  /**
   * @param item
   */
  public void addItem(IProperty item) {
    items.add(item);
    allProperties = null;
  }

}
