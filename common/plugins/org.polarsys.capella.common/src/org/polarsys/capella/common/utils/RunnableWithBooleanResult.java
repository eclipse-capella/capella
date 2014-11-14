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
   * @param object_p
   */
  public void setObject(EObject object_p) {
    _object = object_p;
  }

  /**
   * Sets my result.
   * @param result_p my boolean result
   */
  protected final void setResult(Object result_p) {
    _result = (Boolean) result_p;
  }

  /**
   * {@inheritDoc}
   */
  public final void setStatus(IStatus status_p) {
    _status = status_p;
  }
}
