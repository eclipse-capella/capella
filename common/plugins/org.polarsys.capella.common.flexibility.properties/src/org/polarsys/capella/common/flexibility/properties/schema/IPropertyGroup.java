/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
 * Describes a property associated to an element
 */
public interface IPropertyGroup {

  public static final IPropertyGroup EMPTY = null;

  /**
   * Returns id of property
   */
  public String getParentId();

  /**
   * Returns id of property
   */
  public String getId();

  /**
   * Returns id of property
   */
  public String getName();

}
