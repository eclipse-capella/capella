/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;

/**
 * Runnable with Result abstract class that returns a boolean.
 */
public abstract class RunnableWithBooleanResult implements RunnableWithResult {
  private EObject _object;
  private Boolean _result;

  private IStatus _status;

  /**
   * @return the object
   */
  public EObject getObject() {
    return _object;
  }

  /**
   * {@inheritDoc}
   */
  public Boolean getResult() {
    return _result;
  }

  /**
   * {@inheritDoc}
   */
  public final IStatus getStatus() {
    return _status;
  }

  /**
   * Set an object can be used in run method if set.
   * @param object
   */
  public void setObject(EObject object) {
    _object = object;
  }

  /**
   * Sets my result.
   * @param result my boolean result
   */
  protected final void setResult(Object result) {
    _result = (Boolean) result;
  }

  /**
   * {@inheritDoc}
   */
  public final void setStatus(IStatus status) {
    _status = status;
  }
}
