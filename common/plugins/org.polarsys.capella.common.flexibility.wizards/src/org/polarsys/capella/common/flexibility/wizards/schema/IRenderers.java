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

import java.util.Collection;

import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 */
public interface IRenderers {

  public Collection<IPropertyRenderer> getPropertyRenderers();

  public Collection<IGroupRenderer> getGroupRenderers();

  public Collection<IPropertyGroup> getGroups(IProperties properties_p, IPropertyGroup empty_p);

  public Collection<IProperty> getItems(IProperties properties_p, IPropertyGroup group_p);

  public IPropertyRenderer createRenderer(IProperty property_p);

  public IGroupRenderer createRenderer(IPropertyGroup propertyGroup_p);
}
