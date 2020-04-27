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

/**
 */
public abstract class AbstractSimpleSemanticFieldController extends AbstractSemanticFieldController implements ISimpleSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature) {
    return (EObject) semanticElement.eGet(semanticFeature);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> list = new ArrayList<EObject>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), semanticFeature);
    if (null != query) {
      list.addAll(query.getAvailableElements(semanticElement));
      list.removeAll(query.getCurrentElements(semanticElement, false));
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
