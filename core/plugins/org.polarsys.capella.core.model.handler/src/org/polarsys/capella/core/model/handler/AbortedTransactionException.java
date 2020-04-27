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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;

/**
 * Exception use to abort a running Transaction.<br>
 * This exception can be thrown in {@link ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)} for instance.
 */
public class AbortedTransactionException extends RollbackException {
  /**
   * Serial UID id.
   */
  private static final long serialVersionUID = 3575711333607994069L;
  /**
   * Message description.
   */
  private String _message;

  /**
   * Constructor.
   * @param status_p
   * @param message_p Reason to abort the transaction.
   */
  public AbortedTransactionException(IStatus status_p, String message_p) {
    super(status_p);
    _message = message_p;
  }

  /**
   * @see java.lang.Throwable#getMessage()
   */
  @Override
  public String getMessage() {
    return _message;
  }
}
