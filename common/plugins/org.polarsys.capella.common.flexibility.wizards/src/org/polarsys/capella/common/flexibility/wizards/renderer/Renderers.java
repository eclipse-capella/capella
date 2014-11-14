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
   * @param parent_p the parent to set
   */
  public void addParent(IRenderers parent_p) {
    if (!getParents().contains(parent_p)) {
      getParents().add(parent_p);
    }
  }

  public void addGroupRenderer(String group_p, IGroupRenderer renderer_p) {
    getMapGroupRenderers().put(group_p, renderer_p);
  }

  public void addPropertyRenderer(String property_p, IPropertyRenderer renderer_p) {
    getMapPropertyRenderers().put(property_p, renderer_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer createRenderer(IProperty property_p) {
    if (getMapPropertyRenderers().containsKey(property_p.getId())) {
      return getMapPropertyRenderers().get(property_p.getId());
    }
    for (IRenderers parent : getParents()) {
      IPropertyRenderer result = parent.createRenderer(property_p);
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
  public Collection<IPropertyGroup> getGroups(IProperties properties_p, IPropertyGroup group_p) {
    if (properties_p == null) {
      return Collections.emptyList();
    }
    final List<IPropertyGroup> values = properties_p.getGroups(group_p);
    List<IPropertyGroup> result = new ArrayList<IPropertyGroup>(values);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IProperty> getItems(IProperties properties_p, IPropertyGroup group_p) {
    if (properties_p == null) {
      return Collections.emptyList();
    }
    final List<IProperty> values = properties_p.getItems(group_p);
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
  public IGroupRenderer createRenderer(IPropertyGroup propertyGroup_p) {
    if (getMapGroupRenderers().containsKey(propertyGroup_p.getId())) {
      return getMapGroupRenderers().get(propertyGroup_p.getId());
    }
    for (IRenderers parent : getParents()) {
      IGroupRenderer result = parent.createRenderer(propertyGroup_p);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

}
