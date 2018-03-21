/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materiTals
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.DependencyChecker;
import org.polarsys.capella.core.model.helpers.intermodelInconsistencyDetection.DependencyViolation;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * An element in model A can only reference an element in model B if there is a LibraryReference
 * from A to B. This live validation rule finds references that violate this restriction.
 */
public class InterModelConsistencyValidationRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {

    DependencyChecker linkChecker = null;

    if (ctx.getEventType() == EMFEventType.NULL) {
      return ctx.createSuccessStatus();
    }

    for (Notification n : ctx.getAllEvents()) {

      if (n.getNotifier() instanceof EObject) {
        // make sure this rule is evaluated only once per transaction
        ctx.skipCurrentConstraintFor((EObject) n.getNotifier());
        if (CapellaResourceHelper.isSemanticElement(n.getNotifier())) {
          if (linkChecker == null) {
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(n.getNotifier());
            if (domain instanceof SemanticEditingDomain) {
              linkChecker = new DependencyChecker((SemanticEditingDomain) domain);
            } else {
              continue;
            }
          }

          if (n.getFeature() instanceof EReference) {
            EReference ref = (EReference) n.getFeature();
            Collection<EObject> check = Collections.emptyList();
            if (n.getEventType() == Notification.SET || n.getEventType() == Notification.ADD) {
              EObject newValue = (EObject) n.getNewValue();
              if (newValue != null) {
                check = Collections.singleton(newValue);
              }
            } else if (n.getEventType() == Notification.ADD_MANY) {
              check = ((Collection<EObject>)n.getNewValue());
            }
            if (ref.isContainment()) {
              for (EObject e : check) {
                linkChecker.checkAllLinks(e);
              }
            } else {
              for (EObject e : check) {
                linkChecker.checkLink((EObject) n.getNotifier(), e, ref);
              }
            }
          }
      }
    }
  }

  if (linkChecker != null && linkChecker.getDependencyViolations().size() > 0) {
    Collection<IStatus> children = new ArrayList<IStatus>();
    for (DependencyViolation v : linkChecker.getDependencyViolations()) {
      Object[] args = new Object[] {
          v.getSource(),
          ILibraryManager.INSTANCE.getModel(v.getSource()).getIdentifier().getName(),
          v.getTarget(),
          ILibraryManager.INSTANCE.getModel(v.getTarget()).getIdentifier().getName(),
          v.getEReference()
      };
      children.add(ctx.createFailureStatus(args));
    }
    return ConstraintStatus.createMultiStatus(ctx, children);
  }

  return ctx.createSuccessStatus();
  }
}
