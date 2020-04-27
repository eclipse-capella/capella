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

package org.polarsys.capella.common.flexibility.properties.schema;

import java.util.Collection;
import java.util.List;

/**
 */
public interface IProperties {

  public Collection<IProperties> getParents();

  public String getPropertiesId();

  /** Returns all childs groups for a given group */
  public List<IPropertyGroup> getGroups();

  /** Returns all childs groups for a given group */
  public List<IPropertyGroup> getGroups(IPropertyGroup group);

  /** Returns all childs properties for a given group */
  public List<IProperty> getItems(IPropertyGroup group);

  /** Returns defined properties */
  public Collection<IProperty> getItems();

  /** Returns all defined properties */
  public Collection<IProperty> getAllItems();

  public Collection<IPropertyGroup> getAllGroups();

  public IProperty getProperty(String id);

}
