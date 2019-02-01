/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
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
 * <p>
 * NOTE: Live mode is currently only active if the MoveStagingView is active
 * </p>
 */
public class ReferentialConstraintsValidationRule extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {

    Collection<IStatus> results = new ArrayList<IStatus>();

    Collection<EObject> targets = null;

    if (!isLiveValidation(ctx)) {

      targets = Collections.singleton(ctx.getTarget());

    } else {

      targets = new ArrayList<EObject>();

      for (Notification n : ctx.getAllEvents()) {

        if (n.getNotifier() instanceof EObject) {

          // make sure this rule is evaluated only once per transaction
          ctx.skipCurrentConstraintFor((EObject) n.getNotifier());

          // which elements have been moved?
          if (n.getFeature() instanceof EReference && ((EReference) n.getFeature()).isContainment()) {
            if (n.getEventType() == Notification.REMOVE) {
              EObject e = (EObject) n.getOldValue();
              if (e.eResource() != null) {
                targets.add(e);
              }
            } else if (n.getEventType() == Notification.REMOVE_MANY) {
              @SuppressWarnings("unchecked") Collection<EObject> removed = (Collection<EObject>) n.getOldValue();
              for (EObject e : removed) {
                if (e.eResource() != null) {
                  targets.add(e);
                }
              }
            }
          }
        }
      }
    }

    for (EObject e : targets) {
      // only validate incoming references in live mode
      if (isLiveValidation(ctx)) {
        validateIncomingReferences(e, results, ctx, targets);
      }
      validateOutgoingReferences(e, results, ctx);
    }

    if (results.isEmpty()) {
      return ctx.createSuccessStatus();
    }

    IStatus result = ConstraintStatus.createMultiStatus(ctx, Messages.I_37_ReferenceConstraints_title, new Object[0], results);
    return result;
  }

  private void validateOutgoingReferences(EObject element, Collection<IStatus> results, IValidationContext ctx) {

    for (EContentsEList.FeatureIterator<EObject> it = (EContentsEList.FeatureIterator<EObject>)element.eCrossReferences().iterator(); it.hasNext(); ) {
      EObject target = it.next();
      EReference ref = (EReference)it.feature();
      validateSetting(element, ref, target, results, ctx);
    }

    if (isLiveValidation(ctx)) {
      for (EObject e : element.eContents()) {
        validateOutgoingReferences(e, results, ctx);
      }
    }

  }

  private void validateIncomingReferences(EObject element, Collection<IStatus> results, IValidationContext ctx, Collection<EObject> sourceFilter){
    SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(element);
    for (EStructuralFeature.Setting s : domain.getCrossReferencer().getInverseReferences(element)) {
      EObject source = s.getEObject();
      if (!EcoreUtil.isAncestor(sourceFilter, source)) { // If we do not already validate the source,ref,target triple during validateOutgoing
        EReference ref = (EReference) s.getEStructuralFeature();
        if (!ref.isContainment() &&  !ref.isContainer()) {
          validateSetting(source, ref, element, results, ctx);
        }
      }
    }


    if (isLiveValidation(ctx)) {
      for (EObject e : element.eContents()) {
        validateIncomingReferences(e, results, ctx, sourceFilter);
      }
    }

  }

  private boolean isLiveValidation(IValidationContext ctx) {
    return ctx.getEventType() != EMFEventType.NULL;
  }

  private void validateSetting(EObject source, EReference ref, EObject target, Collection<IStatus> results, IValidationContext ctx) {

    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(source.eClass(), ref);

    if (query != null) {
      List<EObject> availableElements = query.getAvailableElements(source);
      if (!availableElements.contains(target)) {
        results.add(ConstraintStatus.createStatus(ctx, Arrays.asList(source, target) ,
            Messages.I_37_ReferenceConstraints_detail,
            EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), ref.getName()));
      }
    }

  }

}
