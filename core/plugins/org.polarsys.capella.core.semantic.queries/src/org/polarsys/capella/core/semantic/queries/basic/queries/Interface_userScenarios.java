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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.platform.sirius.tig.ef.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * This query allows to get the scenarios in which the current Interface is used
 */
public class Interface_userScenarios implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object_p) {

    List<Object> result = new ArrayList<Object>();
    if (object_p instanceof Interface) {
      // gets the Semantic Editing Domain
      SemanticEditingDomain semEditDomain = (SemanticEditingDomain) MDEAdapterFactory.getEditingDomain();
      // Gets the Cross Referencer
      ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
      Interface itf = (Interface) object_p;
      // Gets the interface's operations
      for (ExchangeItem operation : itf.getExchangeItems()) {
        // Processes all interface's operations in order to get the scenarios in which they are used
        //
        // Gets the inverse references and try to get <code>EventSentOperation</code> objects (<code>EventReceiptOperation</code> should also work)
        Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(operation);
        for (Setting setting : inverseReferences) {
          EObject eObject = setting.getEObject();
          if (eObject instanceof EventSentOperation) {
            // Here you have a <code>EventSentOperation</code> referencing the operation
            //
            // Gets the inverse references from it look for <code>AbstractEnd</> instances
            Collection<Setting> inverseReferencesForEventSentOp = crossReferencer.getInverseReferences(eObject);
            for (Setting settingForEventSentOp : inverseReferencesForEventSentOp) {
              EObject eObjectForEventSentOp = settingForEventSentOp.getEObject();
              if (eObjectForEventSentOp instanceof AbstractEnd) {
                // Here you have an <code>AbstractEnd</> instance whose container should be a <code>Scenario</>
                EObject eContainer = eObjectForEventSentOp.eContainer();
                if (eContainer instanceof Scenario) {
                  // Got the <code>Scenario</code> and adds it to the result list
                  result.add(eContainer);
                }
              }
            }
          }
        }
      }
    }
    return result;
  }
}