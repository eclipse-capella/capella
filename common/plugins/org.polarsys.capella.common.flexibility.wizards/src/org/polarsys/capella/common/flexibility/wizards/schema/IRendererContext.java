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

package org.polarsys.capella.common.flexibility.wizards.schema;

import org.eclipse.jface.viewers.ILabelProvider;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public interface IRendererContext {

  public Object getParameter(String id);

  public void putParameter(String id, Object value);

  public IRenderers getRenderers();

  public IPropertyContext getPropertyContext();

  public ILabelProvider getLabelProvider();

  public void setPropertyContext(IPropertyContext context);

  public IPropertyRenderer getRenderer(IProperty property);

  public IProperty getProperty(IPropertyRenderer renderer);

  public IGroupRenderer getRenderer(IPropertyGroup propertyGroup);

  public IPropertyGroup getPropertyGroup(IGroupRenderer renderer);

}
