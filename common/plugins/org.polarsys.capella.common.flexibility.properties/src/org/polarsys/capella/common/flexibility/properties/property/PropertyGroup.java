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

package org.polarsys.capella.common.flexibility.properties.property;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 * Describes a property group
 */
public class PropertyGroup implements IPropertyGroup {

  String id;
  String parentId;
  String name;

  public PropertyGroup(String id, String name, String parentId) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
  }

  /**
   * Returns id of property
   */
  public String getId() {
    return id;
  }

  /**
   * Returns id of property
   */
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  public String getParentId() {
    return parentId;
  }

}
