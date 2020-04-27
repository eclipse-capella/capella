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
package org.polarsys.capella.core.model.handler;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;

import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Based class to implement {@link RunnableWithResult that takes as input a {@link ModelElement} and outputs another {@link ModelElement}.
 */
public abstract class AbstractModelElementRunnable implements RunnableWithResult {
  /**
   * Model element used as input.
   */
  private EObject _element;
  /**
   * Result as a list of ModelElements instances.
   */
  private List<EObject> _result;
  /**
   * Status.
   */
  private IStatus _status;

  /**
   * Set given element as input one.
   * @param element_p the element to set
   */
  public void setElement(EObject element_p) {
    _element = element_p;
  }

  /**
   * Get the element used as input.
   * @return the element
   */
  protected EObject getElement() {
    return _element;
  }

  /**
   * @see org.eclipse.emf.transaction.RunnableWithResult#getResult()
   */
  public List<EObject> getResult() {
    return _result;
  }

  /**
   * Set result of this runnable with given model elements
   * @param result_p
   */
  protected void setResult(List<EObject> result_p) {
    _result = result_p;
  }

  /**
   * @see org.eclipse.emf.transaction.RunnableWithResult#getStatus()
   */
  public IStatus getStatus() {
    return _status;
  }

  /**
   * @see org.eclipse.emf.transaction.RunnableWithResult#setStatus(org.eclipse.core.runtime.IStatus)
   */
  public void setStatus(IStatus status_p) {
    _status = status_p;
  }
}
