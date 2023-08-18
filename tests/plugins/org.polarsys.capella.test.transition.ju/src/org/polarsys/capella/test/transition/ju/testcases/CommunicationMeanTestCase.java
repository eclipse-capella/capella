/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class CommunicationMeanTestCase extends TopDownTransitionTestCase {
  
  private static String CM = "9dc7f585-45ff-401e-badf-001046a5f6dc";
  
  @Override
  public void performTest() throws Exception {
    
    EObject elementSource = getObject(CM);
    assertTrue(elementSource instanceof CommunicationMean);
    CommunicationMean communicationMean = (CommunicationMean) elementSource;
    assertEquals(ComponentExchangeKind.UNSET, communicationMean.getKind());
    
    // The CommunicationMean is transitioned to a ComponentExchange
    performOE2ActorTransition(getObjects(CM));

    EObject elementTarget = mustBeMonoTransitioned(CM);
    assertTrue(elementTarget instanceof ComponentExchange);
    ComponentExchange componentExchange = (ComponentExchange) elementTarget;
    assertEquals(ComponentExchangeKind.FLOW, componentExchange.getKind());
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Transition");
  }
}
