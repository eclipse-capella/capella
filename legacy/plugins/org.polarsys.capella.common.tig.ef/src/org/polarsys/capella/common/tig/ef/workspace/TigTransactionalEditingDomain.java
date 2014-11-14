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
package org.polarsys.capella.common.tig.ef.workspace;

import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;

/**
 */
public class TigTransactionalEditingDomain extends TransactionalEditingDomainImpl {
  // Undo/Redo options.
  @SuppressWarnings("unchecked")
  private Map _undoRedoOptions;

  /**
   * Constructor.
   * @param adapterFactory_p
   * @param stack_p
   */
  public TigTransactionalEditingDomain(AdapterFactory adapterFactory_p, TransactionalCommandStack stack_p) {
    super(adapterFactory_p, stack_p);
  }

  /**
   * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#getUndoRedoOptions()
   */
  @SuppressWarnings("unchecked")
  @Override
  public Map getUndoRedoOptions() {
    // Switch No_Triggers to false, to enhance undo/redo behavior.
    if (null == _undoRedoOptions) {
      _undoRedoOptions = super.getUndoRedoOptions();
      _undoRedoOptions.put(Transaction.OPTION_NO_TRIGGERS, Boolean.FALSE);
    }
    return _undoRedoOptions;
  }
}
