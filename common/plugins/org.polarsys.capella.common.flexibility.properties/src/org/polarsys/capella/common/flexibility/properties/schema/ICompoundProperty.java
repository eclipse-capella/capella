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

/**
 * Describes a compound property. When one of its related properties will change, it will be notified
 */
public interface ICompoundProperty extends IProperty {

  /**
   * When the given properties will change, the current property will be notified by updatedValue and will notify its
   * listeners.
   */
  public String[] getRelatedProperties();

  public void updatedValue(IProperty property, IPropertyContext context);

}
