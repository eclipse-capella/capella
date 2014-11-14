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
package org.polarsys.capella.common.flexibility.properties.loader;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyOption;

/**
 */
public interface ILoadableProperty extends IProperty {

  /**
   * Returns id of property
   */
  void setEnabled(boolean id_p);

  /**
   * Returns id of property
   */
  void setId(String id_p);

  /**
   * Returns id of property
   */
  void setName(String id_p);

  void setDescription(String description_p);

  /**
   * Returns id of property
   */
  void setGroupId(String id_p);

  void addOption(IPropertyOption item2_p);

  /**
   * {@inheritDoc}
   */
  void addParameter(String key, String value);

}
