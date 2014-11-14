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
package org.polarsys.capella.common.mdsofa.common.descriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic implementation of the {@link IDescriptor} object.
 */
public class GenericDescriptor implements IDescriptor {
  /**
   * Properties container.
   */
  private Map<String, Object> _properties;
  /**
   * Children descriptors.
   */
  private List<IDescriptor> _children;
  /**
   * Parent descriptor.
   */
  private IDescriptor _parent;

  /**
   * Constructor.
   */
  public GenericDescriptor() {
    _properties = new HashMap<String, Object>(0);
    _children = new ArrayList<IDescriptor>(0);
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.common.descriptor.IDescriptor#getValue(java.lang.String)
   */
  public Object getValue(String propertyName_p) {
    return _properties.get(propertyName_p);
  }

  /**
   * Set the specified value for the given property name in this descriptor.
   * @param propertyName_p
   * @param value_p
   */
  public void setValue(String propertyName_p, Object value_p) {
    _properties.put(propertyName_p, value_p);
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.common.descriptor.IDescriptor#getChildren()
   */
  public List<IDescriptor> getChildren() {
    return _children;
  }

  /**
   * Add all given children.
   * @param descriptors_p
   */
  public void addChildren(List<? extends IDescriptor> descriptors_p) {
    // Declare through addChild, so as to set parent link properly.
    for (IDescriptor descriptor : descriptors_p) {
      addChild(descriptor);
    }
  }

  /**
   * Add a child descriptor.
   * @param descriptor_p
   */
  public void addChild(IDescriptor descriptor_p) {
    _children.add(descriptor_p);
    // Set parent reference.
    if (descriptor_p instanceof GenericDescriptor) {
      ((GenericDescriptor) descriptor_p).setParent(this);
    }
  }

  /**
   * @see org.polarsys.capella.common.mdsofa.common.descriptor.IDescriptor#getParent()
   */
  public IDescriptor getParent() {
    return _parent;
  }

  /**
   * Set parent descriptor.
   * @param parent_p the parent to set
   */
  public void setParent(IDescriptor parent_p) {
    _parent = parent_p;
  }
}
