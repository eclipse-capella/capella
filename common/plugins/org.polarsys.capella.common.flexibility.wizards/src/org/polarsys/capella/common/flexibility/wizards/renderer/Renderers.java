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

package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.loader.ILoadableRenderers;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;

/**
 *
 */
public class Renderers implements IRenderers, ILoadableRenderers {

  private HashMap<String, IPropertyRenderer> propertyRenderers;

  private HashMap<String, IGroupRenderer> groupRenderers;

  private Collection<IRenderers> parents;

  protected HashMap<String, IPropertyRenderer> getMapPropertyRenderers() {
    if (propertyRenderers == null) {
      propertyRenderers = new HashMap<String, IPropertyRenderer>();
    }
    return propertyRenderers;
  }

  protected HashMap<String, IGroupRenderer> getMapGroupRenderers() {
    if (groupRenderers == null) {
      groupRenderers = new HashMap<String, IGroupRenderer>();
    }
    return groupRenderers;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IRenderers> getParents() {
    if (parents == null) {
      parents = new ArrayList<IRenderers>();
    }
    return parents;
  }

  /**
   * @param parent the parent to set
   */
  public void addParent(IRenderers parent) {
    if (!getParents().contains(parent)) {
      getParents().add(parent);
    }
  }

  public void addGroupRenderer(String group, IGroupRenderer renderer) {
    getMapGroupRenderers().put(group, renderer);
  }

  public void addPropertyRenderer(String property, IPropertyRenderer renderer) {
    getMapPropertyRenderers().put(property, renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer createRenderer(IProperty property) {
    if (getMapPropertyRenderers().containsKey(property.getId())) {
      return getMapPropertyRenderers().get(property.getId());
    }
    for (IRenderers parent : getParents()) {
      IPropertyRenderer result = parent.createRenderer(property);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IPropertyRenderer> getPropertyRenderers() {
    return getMapPropertyRenderers().values();
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IPropertyGroup> getGroups(IProperties properties, IPropertyGroup group) {
    if (properties == null) {
      return Collections.emptyList();
    }
    final List<IPropertyGroup> values = properties.getGroups(group);
    List<IPropertyGroup> result = new ArrayList<IPropertyGroup>(values);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IProperty> getItems(IProperties properties, IPropertyGroup group) {
    if (properties == null) {
      return Collections.emptyList();
    }
    final List<IProperty> values = properties.getItems(group);
    List<IProperty> result = new ArrayList<IProperty>(values);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<IGroupRenderer> getGroupRenderers() {
    return getMapGroupRenderers().values();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGroupRenderer createRenderer(IPropertyGroup propertyGroup) {
    if (getMapGroupRenderers().containsKey(propertyGroup.getId())) {
      return getMapGroupRenderers().get(propertyGroup.getId());
    }
    for (IRenderers parent : getParents()) {
      IGroupRenderer result = parent.createRenderer(propertyGroup);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

}
