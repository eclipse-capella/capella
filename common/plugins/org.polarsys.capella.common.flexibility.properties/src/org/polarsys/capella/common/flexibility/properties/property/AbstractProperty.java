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
   * @param item2_p
   */
  public void addOption(IPropertyOption item2_p) {
    getOptions().add(item2_p);
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
   * @param id_p the id to set
   */
  public void setId(String id_p) {
    id = id_p;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name_p the name to set
   */
  public void setName(String name_p) {
    name = name_p;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description_p the description to set
   */
  public void setDescription(String description_p) {
    description = description_p;
  }

  /**
   * @return the groupId
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * @param groupId_p the groupId to set
   */
  public void setGroupId(String groupId_p) {
    groupId = groupId_p;
  }

  /**
   * @return the enabled
   */
  public boolean isEnabled(IPropertyContext context_p) {
    return enabled;
  }

  /**
   * @param enabled_p the enabled to set
   */
  public void setEnabled(boolean enabled_p) {
    enabled = enabled_p;
  }

  /**
   * {@inheritDoc}
   */
  public String getParameter(String key_p) {
    return getArgs().get(key_p);
  }

  public boolean isArgumentSet(String key_p) {
    return getArgs().containsKey(key_p);
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
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

}
