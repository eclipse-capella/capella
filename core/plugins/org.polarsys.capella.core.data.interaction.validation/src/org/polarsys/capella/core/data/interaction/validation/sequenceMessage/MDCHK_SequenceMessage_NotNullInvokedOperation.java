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
package org.polarsys.capella.core.data.interaction.validation.sequenceMessage;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 */
public class MDCHK_SequenceMessage_NotNullInvokedOperation extends AbstractModelConstraint {
  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    //
    // Preconditions.
    //
    EMFEventType eType = ctx_p.getEventType();
    if (EMFEventType.NULL != eType) {
      return ctx_p.createSuccessStatus();
    }
    EObject eObj = ctx_p.getTarget();
    if (!(eObj instanceof SequenceMessage)) {
      return ctx_p.createSuccessStatus();
    }
    SequenceMessage sequenceMessage = (SequenceMessage) eObj;
    // Ignore TIMER SequenceMessage (by construction, they don't have a invoked operation).
    if (MessageKind.TIMER == sequenceMessage.getKind()) {
      return ctx_p.createSuccessStatus();
    }
    AbstractEventOperation invokedOperation = sequenceMessage.getInvokedOperation();
    if (null == invokedOperation) {
      String sequenceMessageName = EObjectLabelProviderHelper.getText(sequenceMessage);
      String sequenceMessageMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(sequenceMessage, false);
      String scenarioName = EObjectLabelProviderHelper.getText(sequenceMessage.eContainer());
      String scenarioMetaClass = EObjectLabelProviderHelper.getMetaclassLabel(sequenceMessage.eContainer(), false);
      return ctx_p.createFailureStatus(sequenceMessageName, sequenceMessageMetaClass, scenarioName, scenarioMetaClass);
    }
    return ctx_p.createSuccessStatus();
  }
}
