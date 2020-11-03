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

package org.polarsys.capella.common.flexibility.properties.property;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 *
 */
public interface IDefaultValueProperty extends IProperty {

  /**
   * Returns the default value for this property
   * {@inheritDoc}
   */
  public Object getDefaultValue(IPropertyContext context);

  public void initializeDefaultValue(IPropertyContext context);
  
}
