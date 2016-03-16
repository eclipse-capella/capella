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

package org.polarsys.capella.common.flexibility.properties.property;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyOption;

/**
 */
public class PropertyOption implements IPropertyOption {

  String id;
  String value;
  String name;
  String description;
  boolean enabled;

  /**
   * @param id
   * @param value
   * @param name
   * @param description
   * @param enabled
   */
  public PropertyOption(String id, String value, String name, String description, boolean enabled) {
    this.id = id;
    this.value = value;
    this.name = name;
    this.enabled = enabled;
    this.description = description;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the scope
   */
  public String getValue() {
    return value;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return description;
  }

}
