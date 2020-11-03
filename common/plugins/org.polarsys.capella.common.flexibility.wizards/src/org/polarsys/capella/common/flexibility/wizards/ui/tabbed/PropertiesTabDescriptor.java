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
  IPropertyContext propertyContext;
  IRendererContext rendererContext;
  IPropertyGroup group;

  public PropertiesTabDescriptor(IPropertyContext context, IRendererContext rendererContext, IPropertyGroup group) {
    this.propertyContext = context;
    this.rendererContext = rendererContext;
    this.group = group;
  }

  @Override
  public String getCategory() {
    return "category";
  }

  @Override
  public String getId() {
    return group.getId();
  }

  @Override
  public String getLabel() {
    return group.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ISectionDescriptor> getSectionDescriptors() {

    List<ISectionDescriptor> desc = new LinkedList<ISectionDescriptor>();
    IRenderers renderers = rendererContext.getRenderers();
    IProperties properties = propertyContext.getProperties();

    Collection<IPropertyGroup> childGroups = Collections.emptyList();
    if (renderers != null) {
      childGroups = renderers.getGroups(properties, group);
    }

    final Collection<IProperty> childProperties = properties.getItems(group);

    if ((childGroups.size() == 0) && (childProperties.size() == 0)) {
      return desc;
    }

    for (final IPropertyGroup group : childGroups) {
      desc.add(createSectionDescriptor(propertyContext, rendererContext, group));
    }

    return desc;
  }

  /**
   * @param context
   * @param rendererContext
   * @param group
   * @return
   */
  protected ISectionDescriptor createSectionDescriptor(IPropertyContext context, IRendererContext rendererContext, IPropertyGroup group) {
    return new PropertiesSectionDescriptor(context, rendererContext, group);
  }

}
