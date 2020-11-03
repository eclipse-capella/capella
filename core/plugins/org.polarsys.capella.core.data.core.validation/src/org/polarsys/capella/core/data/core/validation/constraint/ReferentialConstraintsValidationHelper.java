/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;

public abstract class ReferentialConstraintsValidationHelper<T> {

  final boolean liveValidation;
  
  public ReferentialConstraintsValidationHelper(boolean liveValidation) {
    this.liveValidation = liveValidation;
  }

  public List<T> validate(Collection<EObject> targets) {
    List<T> result = new ArrayList<>();
    for (EObject e : targets) {
      // only validate incoming references in live mode
      if (liveValidation) {
        validateIncomingReferences(e, result, targets);
      }
      validateOutgoingReferences(e, result);
    }
    return result;
  }
  
  public Collection<EObject> getTargets(Collection<Notification> notifications){

    Collection<EObject> targets = new ArrayList<EObject>();

    for (Notification n : notifications) {

      if (n.getNotifier() instanceof EObject) {

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
    
    return targets;
    
  }

  private void validateSetting(EObject source, EReference ref, EObject target, Collection<T> results) {

    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(source.eClass(), ref);

    if (query != null) {
      List<EObject> availableElements = query.getAvailableElements(source);
      if (!availableElements.contains(target)) {

        // If the target isn't available according to the query, see if it's one of the 
        // current elements according to the query. This filters out
        // some of the queries that use trace typed features to calculate
        // derived elements, and these are useless here
        List<EObject> currentElements = query.getCurrentElements(source, false);
        if (currentElements.contains(target)) {
          results.add(createStatus(source, target, ref));
        }
      }
    }

  }

  public void validateOutgoingReferences(EObject element, Collection<T> results) {

    for (EContentsEList.FeatureIterator<EObject> it = (EContentsEList.FeatureIterator<EObject>)element.eCrossReferences().iterator(); it.hasNext(); ) {
      EObject target = it.next();
      EReference ref = (EReference)it.feature();
      validateSetting(element, ref, target, results);
    }

    if (liveValidation) {
      for (EObject e : element.eContents()) {
        validateOutgoingReferences(e, results);
      }
    }

  }

  public void validateIncomingReferences(EObject element, Collection<T> results, Collection<EObject> sourceFilter){
    SemanticEditingDomain domain = (SemanticEditingDomain) TransactionUtil.getEditingDomain(element);
    for (EStructuralFeature.Setting s : domain.getCrossReferencer().getInverseReferences(element)) {
      EObject source = s.getEObject();
      if (!EcoreUtil.isAncestor(sourceFilter, source)) { // If we do not already validate the source,ref,target triple during validateOutgoing
        EReference ref = (EReference) s.getEStructuralFeature();
        if (!ref.isContainment() &&  !ref.isContainer()) {
          validateSetting(source, ref, element, results);
        }
      }
    }

    if (liveValidation) {
      for (EObject e : element.eContents()) {
        validateIncomingReferences(e, results, sourceFilter);
      }
    }

  }

  protected abstract T createStatus(EObject source, EObject target, EReference ref);

}
