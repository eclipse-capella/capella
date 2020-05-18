/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.osgi.util.NLS;
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
     * @param status the validation diagnostic
     * @return
     * @throws RollbackException if the transaction should rollback
     */
    public void handle(Diagnostic status) throws RollbackException;
  }

  final Callback callback;

  public ReferentialConstraintsResourceSetListener(Callback callback) {
    this.callback = callback;
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

    ReferentialConstraintsValidationHelper<Diagnostic> helper = new ReferentialConstraintsValidationHelper<Diagnostic>(true) {
      @Override
      protected Diagnostic createStatus(EObject source, EObject target, EReference ref) {
        return new BasicDiagnostic(Diagnostic.ERROR, Messages.I_38_ReferenceConstraintsListener_ruleID, 0, NLS.bind(Messages.I_38_ReferenceConstraintsListener_detail,
            new Object[] {
                EObjectLabelProviderHelper.getText(source),
                EObjectLabelProviderHelper.getText(target),
                ref.getName(),
            }), new Object[] { source, target });
      }
    };

    Collection<EObject> targets = helper.getTargets(event.getNotifications());
    List<Diagnostic> results = helper.validate(targets);

    if (!results.isEmpty()) {
      Diagnostic ms = new BasicDiagnostic(PLUGIN_ID, 0, results, results.get(0).getMessage(), null);
      callback.handle(ms);
    }
    return null;
  }


}
