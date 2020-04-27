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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.loader.ILoadableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyOption;

/**
 */
public abstract class AbstractProperty implements ILoadableProperty {

  private String id;
  private String name;
  private String description;
  private String groupId;

  private HashMap<String, String> args;
  private List<IPropertyOption> childs;

  boolean enabled;

  /**
   * @param item2
   */
  public void addOption(IPropertyOption item2) {
    getOptions().add(item2);
  }

  public List<IPropertyOption> getOptions() {
    if (childs == null) {
      childs = new ArrayList<IPropertyOption>();
    }
    return childs;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the groupId
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  /**
   * @return the enabled
   */
  public boolean isEnabled(IPropertyContext context) {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * {@inheritDoc}
   */
  public String getParameter(String key) {
    return getArgs().get(key);
  }

  public boolean isArgumentSet(String key) {
    return getArgs().containsKey(key);
  }

  public HashMap<String, String> getArgs() {
    if (args == null) {
      args = new HashMap<String, String>();
    }
    return args;
  }

  /**
   * {@inheritDoc}
   */
  public void addParameter(String key, String value) {
    getArgs().put(key, value);
  }

  /**
   * {@inheritDoc}
   */
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

}
