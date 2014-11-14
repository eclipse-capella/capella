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
package org.polarsys.capella.core.transition.system.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.rules.AbstractUpdateRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public abstract class AbstractCapellaElementRule extends AbstractUpdateRule {

  public AbstractCapellaElementRule() {
    super();
    registerUpdatedReference(CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
    registerUpdatedReference(CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES);
    registerUpdatedAttribute(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME);
    registerUpdatedAttribute(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION);
    registerUpdatedAttribute(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   */
  @Override
  protected void updateElement(EObject element_p, EObject result_p, IContext context_p) {
    super.updateElement(element_p, result_p, context_p);
  }

  @Override
  public EClass getTargetType(EObject element_p, IContext context_p) {
    return element_p.eClass();
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject result = super.transformDirectElement(element_p, context_p);

    //Theoretically, this should not be performed here, but log message requires a valid name
    if ((element_p instanceof AbstractNamedElement) && (result instanceof AbstractNamedElement)) {
      ((AbstractNamedElement) result).setName(((AbstractNamedElement) element_p).getName());
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  protected void premicesContainement(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesContainement(element_p, needed_p);

    if (isOrderedContainment(element_p)) {
      //Add previous element in the containingFeature list if ordered
      if ((element_p.eContainingFeature() != null) && element_p.eContainingFeature().isOrdered()) {
        Object parentList = element_p.eContainer().eGet(element_p.eContainingFeature());
        if ((parentList != null) && (parentList instanceof EList)) {
          int index = ((EList) parentList).indexOf(element_p);
          if (index > 0) {
            needed_p.addAll(createDefaultCriticalPremices((Collection) Collections.singleton(((EList) parentList).get(index - 1)), "previous")); //$NON-NLS-1$
          }
        }
      }
    }

  }

  protected boolean isOrderedContainment(EObject element_p) {
    return ((element_p.eContainingFeature() != null) && (element_p.eContainingFeature().isOrdered()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES));
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES,
        context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (source_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) source_p;
      //property values are now retrieved with IScopeRetriever
      result_p.addAll(element.getConstraints());
    }
  }

}
