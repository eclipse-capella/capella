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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

public class PropertiesTabDescriptor extends AbstractTabDescriptor {
  IPropertyContext _propertyContext;
  IRendererContext _rendererContext;
  IPropertyGroup _group;

  public PropertiesTabDescriptor(IPropertyContext context_p, IRendererContext rendererContext_p, IPropertyGroup group_p) {
    _propertyContext = context_p;
    _rendererContext = rendererContext_p;
    _group = group_p;
  }

  @Override
  public String getCategory() {
    return "category";
  }

  @Override
  public String getId() {
    return _group.getId();
  }

  @Override
  public String getLabel() {
    return _group.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ISectionDescriptor> getSectionDescriptors() {

    List<ISectionDescriptor> desc = new LinkedList<ISectionDescriptor>();
    IRenderers renderers = _rendererContext.getRenderers();
    IProperties properties = _propertyContext.getProperties();

    Collection<IPropertyGroup> childGroups = Collections.emptyList();
    if (renderers != null) {
      childGroups = renderers.getGroups(properties, _group);
    }

    final Collection<IProperty> childProperties = properties.getItems(_group);

    if ((childGroups.size() == 0) && (childProperties.size() == 0)) {
      return desc;
    }

    for (final IPropertyGroup group : childGroups) {
      desc.add(createSectionDescriptor(_propertyContext, _rendererContext, group));
    }

    return desc;
  }

  /**
   * @param context_p
   * @param properties_p
   * @param renderers_p
   * @param group_p
   * @return
   */
  protected ISectionDescriptor createSectionDescriptor(IPropertyContext context_p, IRendererContext rendererContext_p, IPropertyGroup group_p) {
    return new PropertiesSectionDescriptor(context_p, rendererContext_p, group_p);
  }

}
