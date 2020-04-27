/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.exception;

import org.eclipse.core.runtime.IStatus;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
public class TransitionException extends RuntimeException {

  private static final long serialVersionUID = 6536100605244444430L;

  public TransitionException(Throwable e) {
    super(e);
  }

  public TransitionException(IStatus status) {
    super(status == null ? ICommonConstants.EMPTY_STRING : getMessage(status));
  }

  private static String getMessage(IStatus status) {
    if (status.isMultiStatus()) {
      IStatus[] children = status.getChildren();
      StringBuilder builder = new StringBuilder();
      builder.append(status.getMessage()+ "\n");
      for (IStatus child : children) {
        if (child.isMultiStatus()) {
          builder.append(getMessage(child) + "\n");
        } else {
          builder.append(child.getMessage() + "\n");
        }
      }
      return builder.toString();
    }
    return status.getMessage();
  }

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
}
