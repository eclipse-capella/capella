/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * 
 */
public class ExchangesItemCommLink implements IQuery {

  /**
	 * 
	 */
  public ExchangesItemCommLink() {
    // Does nothing
  }

  /**
   * 
   * current.flowSource.ownerElement
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ExchangeItem) {
      ExchangeItem exchangItem = (ExchangeItem) object;
      List<ExchangeItemAllocation> elements = new ArrayList<ExchangeItemAllocation>(1);
      
      // gets the Semantic Editing Domain
      SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(exchangItem);
      // Gets the Cross Referencer
      ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(exchangItem);

      for (Setting setting : inverseReferences) {
        EObject eObject = setting.getEObject();
        if (eObject != null) {
          // filter CommunicationLink 
          if (eObject instanceof CommunicationLink) {
              result.add(eObject);              
          }
        }
      }
      
      if(!elements.isEmpty())
        result.addAll(elements);
      
      
    }
    return result;
  }
}
