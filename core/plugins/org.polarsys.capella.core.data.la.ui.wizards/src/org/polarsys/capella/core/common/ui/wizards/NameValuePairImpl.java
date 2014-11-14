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
package org.polarsys.capella.core.common.ui.wizards;

import java.util.Set;
import java.util.TreeSet;

import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionComponent;
import org.polarsys.capella.core.ui.toolkit.decomposition.DecompositionItem;
import org.polarsys.capella.core.ui.toolkit.decomposition.NameValuePair;

/**
 * @deprecated Use {@link DecompositionItem} instead
 */
public class NameValuePairImpl implements NameValuePair {
  private String _name;
  private Object _value;
  private int _status;
  private String _statusMessage;
  private Set<String> _decompositionNames;
  private DecompositionComponent _parentComponent;
  private boolean _alreadyDecomposed;

  private boolean _used;

  /**
   * Default constructor
   */
  public NameValuePairImpl() {
    this(null, null, -1, false);
  }

  /**
   * Constructor
   * @param name_p
   *          the name
   * @param value_p
   *          the value
   * @param status_p
   *          the status
   */
  public NameValuePairImpl(String name_p, Object value_p, int status_p) {
    setName(name_p);
    setValue(value_p);
    setStatus(status_p);
    setStatusMessage(""); //$NON-NLS-1$
    _decompositionNames = new TreeSet<String>();
  }

  /**
   * Constructor
   * @param name_p
   *          the name
   * @param value_p
   *          the value
   * @param status_p
   *          the status
   * @param used_p
   *          flag to indicate whether the interface is used or implemented
   */
  public NameValuePairImpl(String name_p, Object value_p, int status_p, boolean used_p) {
    this(name_p, value_p, status_p);
    setUsed(used_p);
  }

  /**
   * @return the name
   */
  public String getName() {
    return _name;
  }

  /**
   * @param name_p
   *          the name to set
   */
  public void setName(String name_p) {
    _name = name_p;
  }

  /**
   * @return the value
   */
  public Object getValue() {
    return _value;
  }

  /**
   * @param value_p
   *          the value to set
   */
  public void setValue(Object value_p) {
    _value = value_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getStatus()
   */
  public int getStatus() {
    return _status;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#setStatus(int)
   */
  public void setStatus(int status_p) {
    _status = status_p;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object object_p) {
    if (object_p instanceof NameValuePair) {
      return getValue().equals(((NameValuePair) object_p).getValue());
    }
    return false;
  }

  /**
   * @return the statusMessage
   */
  public String getStatusMessage() {
    return _statusMessage;
  }

  /**
   * @param statusMessage_p
   *          the statusMessage to set
   */
  public void setStatusMessage(String statusMessage_p) {
    _statusMessage = statusMessage_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#addDecompositionNames(java.lang.String)
   */
  public void addDecompositionNames(String decompositionName_p) {
    _decompositionNames.add(decompositionName_p);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getDecompositionNames()
   */
  public Set<String> getDecompositionNames() {
    return _decompositionNames;
  }

  /**
   * @return the used
   */
  public boolean isUsed() {
    return _used;
  }

  /**
   * @param used_p
   *          the used to set
   */
  public void setUsed(boolean used_p) {
    _used = used_p;
  }

  /**
   * @return the parentComponent
   */
  public DecompositionComponent getParentComponent() {
    return _parentComponent;
  }

  /**
   * @param parentComponent_p
   *          the parentComponent to set
   */
  public void setParentComponent(DecompositionComponent parentComponent_p) {
    _parentComponent = parentComponent_p;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#getCopy()
   */
  public NameValuePair getCopy() {
    NameValuePairImpl copy = new NameValuePairImpl(this.getName(), this.getValue(), this.getStatus(), this.isUsed());
    copy._decompositionNames = this.getDecompositionNames();
    return copy;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.decomposition.NameValuePair#isAlreadyDecomposed()
   */
  public boolean isAlreadyDecomposed() {
    return _alreadyDecomposed;
  }

  /**
   * @param alreadyDecomposed_p the alreadyDecomposed to set
   */
  public void setAlreadyDecomposed(boolean alreadyDecomposed_p) {
    _alreadyDecomposed = alreadyDecomposed_p;
  }
}
