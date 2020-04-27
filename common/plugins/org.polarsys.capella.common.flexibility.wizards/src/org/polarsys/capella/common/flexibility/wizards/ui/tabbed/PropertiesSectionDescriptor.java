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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

public class PropertiesSectionDescriptor extends AbstractSectionDescriptor {
  IPropertyContext propertyContext;
  IPropertyGroup group;
  IRendererContext rendererContext;

  public PropertiesSectionDescriptor(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    this.propertyContext = context;
    this.group = group;
    this.rendererContext = renderers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return group.getId() + propertyContext.getProperties().getPropertiesId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetTab() {
    return group.getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISection getSectionClass() {
    return createSection(propertyContext, rendererContext, group);
  }

  /**
   */
  protected ISection createSection(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    return new PropertiesSection(context, renderers, group);
  }
}
