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

/**
 *
 */
public interface IModifiedProperty {

  /**
   * Returns whether a property has been modified and needs 
   * to be saved (through setValue method) when context.write(property) is called
   * 
   * Such method will preempt cache checking on propertyContext.
   * If method returns true, setValue will be called even if no currentValue has been stored into propertyContext.
   * Otherwise, even if there is a cache, method setValue will not be called.
   * 
   * @param context
   * @return
   */
  public boolean isModified(IPropertyContext context);

}
