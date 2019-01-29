/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

/**
 * A transaction listener that validates referential integrity.
 * The listener must be configured with a callback.
 * 
 * @see ReferentialConstraintsValidationHelper
 */
public class ReferentialConstraintsResourceSetListener extends ResourceSetListenerImpl {

  private static final String PLUGIN_ID = "org.polarsys.capella.core.data.core.validation"; //$NON-NLS-1$
  
  /**
   * A callback interface that allows to customize behaviour of the listener.
   */
  @FunctionalInterface
  public static interface Callback {
    /**
     * Asks the callback to handle the status.
     * @param status the validation (multi)status
     * @return
     * @throws RollbackException if the transaction should rollback
     */
    public void handle(IStatus status) throws RollbackException;
  }

  final Callback callback;

  public ReferentialConstraintsResourceSetListener(Callback callback) {
    this.callback = callback;
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

    ReferentialConstraintsValidationHelper helper = new ReferentialConstraintsValidationHelper(true) {
      @Override
      protected IStatus createStatus(EObject source, EObject target, EReference ref) {
        return new Status(IStatus.ERROR, PLUGIN_ID, String.format("'%s' cannot reference moved element '%s' via '%s'", //$NON-NLS-1$
            EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), ref.getName()));
      }
    };

    Collection<EObject> targets = helper.getTargets(event.getNotifications());
    Collection<IStatus> results = helper.validate(targets);

    if (!results.isEmpty()) {
      MultiStatus ms = new MultiStatus(PLUGIN_ID, 0, results.iterator().next().getMessage(), null);
      for (IStatus s : results) {
        ms.add(s);
      }

      callback.handle(ms);
    }
    return null;
  }


}
