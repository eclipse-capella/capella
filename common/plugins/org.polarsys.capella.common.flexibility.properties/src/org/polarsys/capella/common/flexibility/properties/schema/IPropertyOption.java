/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.flexibility.properties.schema;

/**
 *
 */
public interface IPropertyOption {

  public String getId();

  /**
   * @return the scope
   */
  public String getValue();

  /**
   * @return the name
   */
  public String getName();

  /**
   * @return the description
   */
  public String getDescription();

  /**
   * @return
   */
  public boolean isEnabled();

}
