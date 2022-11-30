/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.SequenceMessageExt;

/**
 */
public class SequenceMessageInvokedOperationExchangeItems extends AbstractModelConstraint {
    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        //
        // Preconditions.
        //
        EMFEventType eType = ctx.getEventType();
        if (EMFEventType.NULL != eType) {
            return ctx.createSuccessStatus();
        }
        EObject eObj = ctx.getTarget();
        if (!(eObj instanceof SequenceMessage)) {
            return ctx.createSuccessStatus();
        }
        SequenceMessage sequenceMessage = (SequenceMessage) eObj;
        // Ignore TIMER SequenceMessage (by construction, they don't have a invoked operation).
        if (MessageKind.TIMER == sequenceMessage.getKind()) {
            return ctx.createSuccessStatus();
        }
        AbstractEventOperation invokedOperation = sequenceMessage.getInvokedOperation();
        if (null != invokedOperation) {
            Collection<AbstractExchangeItem> lstInvokedEI = SequenceMessageExt.getExchangeItemsFromOperation(sequenceMessage);
            if (!lstInvokedEI.isEmpty() && sequenceMessage.getExchangedItems().isEmpty()) {
                String invokedOperationDisplayName = computeDisplayNameForOperation(invokedOperation);
                return ctx.createFailureStatus(sequenceMessage.getName(), invokedOperation.getName(), invokedOperationDisplayName);
            }
        }
        return ctx.createSuccessStatus();
    }
    
    private String computeDisplayNameForOperation(AbstractEventOperation invokedOperation) {
        String result = "";
        if (invokedOperation instanceof FunctionalExchange) {
            AbstractFunction srcFunc = FunctionalExchangeExt.getSourceFunction((FunctionalExchange) invokedOperation);
            if (srcFunc instanceof OperationalActivity) {
              result = "Interaction";
            } else {
                result = EObjectLabelProviderHelper.getMetaclassLabel(invokedOperation, false);
            }
        } else {
            result = EObjectLabelProviderHelper.getMetaclassLabel(invokedOperation, false);
        }
        return result;
    }
}
