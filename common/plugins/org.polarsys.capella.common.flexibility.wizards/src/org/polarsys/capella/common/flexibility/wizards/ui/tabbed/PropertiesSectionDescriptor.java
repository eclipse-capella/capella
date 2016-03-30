/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  IPropertyContext _propertyContext;
  IPropertyGroup _group;
  IRendererContext _rendererContext;

  public PropertiesSectionDescriptor(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    _propertyContext = context;
    _group = group;
    _rendererContext = renderers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return _group.getId() + _propertyContext.getProperties().getPropertiesId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetTab() {
    return _group.getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISection getSectionClass() {
    return createSection(_propertyContext, _rendererContext, _group);
  }

  /**
   */
  protected ISection createSection(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    return new PropertiesSection(context, renderers, group);
  }
}
