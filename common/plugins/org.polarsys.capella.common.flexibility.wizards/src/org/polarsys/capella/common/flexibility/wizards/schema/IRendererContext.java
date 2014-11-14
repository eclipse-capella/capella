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
package org.polarsys.capella.common.flexibility.wizards.schema;

import org.eclipse.jface.viewers.ILabelProvider;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public interface IRendererContext {

  public Object getParameter(String id_p);

  public void putParameter(String id_p, Object value_p);

  public IRenderers getRenderers();

  public IPropertyContext getPropertyContext();

  public ILabelProvider getLabelProvider();

  public void setPropertyContext(IPropertyContext context_p);

  public IPropertyRenderer getRenderer(IProperty property_p);

  public IProperty getProperty(IPropertyRenderer renderer_p);

  public IGroupRenderer getRenderer(IPropertyGroup propertyGroup_p);

  public IPropertyGroup getPropertyGroup(IGroupRenderer renderer_p);

}
