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

package org.polarsys.capella.core.transition.common.exception;

import org.eclipse.core.runtime.IStatus;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
public class TransitionException extends RuntimeException {

  private static final long serialVersionUID = 6536100605244444430L;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    if (getCause() != null) {
      return getCause().toString();
    }
    return getMessage();
  }

  public TransitionException(IStatus status) {
    super(status == null ? ICommonConstants.EMPTY_STRING : status.getMessage());
  }

  public TransitionException(Throwable e) {
    super(e);
  }
}
