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

package org.polarsys.capella.common.flexibility.properties.schema;

import java.util.Collection;

/**
 * Describes a property associated to an element
 */
public interface IRestraintProperty extends IProperty {

  /**
   * Returns value of property
   */
  public Collection<?> getChoiceValues(IPropertyContext context);

  public boolean isMany();

}
