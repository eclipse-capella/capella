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
   * @param id_p
   * @param scope_p
   * @param group_id_p
   * @param name_p
   */
  public PropertyOption(String id_p, String value_p, String name_p, String description_p, boolean enabled_p) {
    this.id = id_p;
    this.value = value_p;
    this.name = name_p;
    this.enabled = enabled_p;
    this.description = description_p;
  }

  /**
   * @return the id_p
   */
  public String getId() {
    return id;
  }

  /**
   * @return the scope_p
   */
  public String getValue() {
    return value;
  }

  /**
   * @return the name_p
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
