/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
   * @param element
   * @param result
   * @param context
   */
  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);
  }

  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return element.eClass();
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    EObject result = super.transformDirectElement(element, context);

    //Theoretically, this should not be performed here, but log message requires a valid name
    if ((element instanceof AbstractNamedElement) && (result instanceof AbstractNamedElement)) {
      ((AbstractNamedElement) result).setName(((AbstractNamedElement) element).getName());
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  protected void premicesContainement(EObject element, ArrayList<IPremise> needed) {
    super.premicesContainement(element, needed);

    if (isOrderedContainment(element)) {
      //Add previous element in the containingFeature list if ordered
      if ((element.eContainingFeature() != null) && element.eContainingFeature().isOrdered()) {
        Object parentList = element.eContainer().eGet(element.eContainingFeature());
        if ((parentList != null) && (parentList instanceof EList)) {
          int index = ((EList) parentList).indexOf(element);
          if (index > 0) {
            needed.addAll(createDefaultCriticalPremices((Collection) Collections.singleton(((EList) parentList).get(index - 1)), "previous")); //$NON-NLS-1$
          }
        }
      }
    }
  }

  protected boolean isOrderedContainment(EObject element) {
    return ((element.eContainingFeature() != null) && (element.eContainingFeature().isOrdered()));
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
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS));
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES,
        context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    if (source instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) source;
      //property values are now retrieved with IScopeRetriever
      result.addAll(element.getConstraints());
    }
  }

}
