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
package org.polarsys.capella.core.data.cs.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

/**
 */
public class Component_RealizedComponentsController extends AbstractMultipleSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement.eGet(semanticFeature);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof ComponentRealization) {
          values.add(((ComponentRealization) obj).getTargetElement());
        }
      }
    }

    return values;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    ComponentRealization link = CsFactory.eINSTANCE.createComponentRealization();
    link.setSourceElement((TraceableElement) semanticElement);
    link.setTargetElement((TraceableElement) object);
    ((List<EObject>) semanticElement.eGet(semanticFeature)).add(link);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(EObject, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    EObject linkToRemove = null;
    for (EObject obj : (List<EObject>) semanticElement.eGet(semanticFeature)) {
      if ((obj instanceof ComponentRealization)
        && ((ComponentRealization) obj).getTargetElement().equals(object))
      {
        linkToRemove = obj;
      }
    }
    if (linkToRemove != null)
      super.doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, linkToRemove);
  }
}
