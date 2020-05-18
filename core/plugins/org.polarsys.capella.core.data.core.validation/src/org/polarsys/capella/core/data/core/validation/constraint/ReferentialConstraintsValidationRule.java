/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A live and batch validation rule that finds forbidden references,
 * e.g. a generalization from Class Sub to Class Super, where Sub
 * is in a higher architecture layer than Super is not allowed.
 * <p>
 * In live mode, all moved elements during a transaction are detected.
 * For these moved elements, all incoming and outgoing references are verified.
 * In batch mode, only outgoing references of the constraint target are verified.
 * </p>
 */
public class ReferentialConstraintsValidationRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {

    boolean isLiveValidation = ctx.getEventType() != EMFEventType.NULL;
    ReferentialConstraintsValidationHelper<IStatus> helper = new ReferentialConstraintsValidationHelper<IStatus>(isLiveValidation) {
      @Override
      protected IStatus createStatus(EObject source, EObject target, EReference ref) {
        return ConstraintStatus.createStatus(ctx, Arrays.asList(source, target) ,
            Messages.I_38_ReferenceConstraints_detail,
            EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), ref.getName());
      }
    };

    Collection<EObject> targets = null;
    if (!isLiveValidation) {
      targets = Collections.singleton(ctx.getTarget());
    } else {
      targets = helper.getTargets(ctx.getAllEvents());
      for (Notification n : ctx.getAllEvents()) {
        if (n.getNotifier() instanceof EObject) {
          ctx.skipCurrentConstraintFor((EObject) n.getNotifier());
        }
      }
    }

    Collection<IStatus> results = helper.validate(targets);
    if (results.isEmpty()) {
      return ctx.createSuccessStatus();
    }

    IStatus result = ConstraintStatus.createMultiStatus(ctx, Messages.I_38_ReferenceConstraints_title, new Object[0], results);
    return result;
  }

}
