/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public abstract class AbstractSimpleSemanticFieldController extends AbstractSemanticFieldController implements ISimpleSemanticFieldController {

  /**
   * @see org.polarsys.capella.core.ui.properties.controllers.custom.properties.controllers.ISimpleSemanticFieldController#loadValue(org.polarsys.capella.core.data.capellacore.CapellaElement, org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public EObject loadValue(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    return (EObject) semanticElement.eGet(semanticFeature);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.widgets.SimpleEditableSemanticField#readOpenValues()
   */
  @Override
  public List<EObject> readOpenValues(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), semanticFeature);
    if (null != query) {
      List<EObject> availableElements = query.getAvailableElements(semanticElement);
      list.addAll(availableElements);
    } else {
      EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(semanticElement);
      if (domain instanceof AdapterFactoryEditingDomain){
        IItemPropertySource source = (IItemPropertySource) ((AdapterFactoryEditingDomain) domain).getAdapterFactory().adapt(semanticElement, IItemPropertySource.class);
        if (source != null){
          IItemPropertyDescriptor descriptor = source.getPropertyDescriptor(semanticElement, semanticFeature);
          for (Object e : descriptor.getChoiceOfValues(semanticElement)){
            if (e instanceof EObject){
              list.add((EObject) e);
            }
          }
        }
      }
    }
    return list;
  }
}
