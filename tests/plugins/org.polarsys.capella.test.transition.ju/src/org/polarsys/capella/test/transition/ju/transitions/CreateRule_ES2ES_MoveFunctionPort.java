/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Ensure that message from a functional exchange with a moved bound to a unrelated component in target architecture is
 * still propagated even if the component is not related to the original instance role
 * 
 * - Create SF1 SF2 FE System and Actor allocating functions
 * - Create an ES with the FE
 * - Transition of Actor and System
 * - Create a new Component LC3 and a Function and move exchange port from actor to it
 * - ES2ES shall create the message between LC1 and LC2
 */
public class CreateRule_ES2ES_MoveFunctionPort extends TopDownTransitionTestCase {

  public static final String ES_CAPABILITY_1 = "ab4b3647-2d23-487d-8ba5-523c42e36f06"; //$NON-NLS-1$
  public static final String MSG_FUNCTIONALEXCHANGE_1 = "f126a854-ecaf-4de3-befc-ef9f2eb2e169"; //$NON-NLS-1$
  
  public static final String LC_1 = "f36c0986-4dc5-480c-8fdf-8a283d70d862"; //$NON-NLS-1$
  public static final String LC_2 = "a9c3b366-37ba-475b-b74b-fdfd65711ab2"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ES2ES_MoveFunctionPort");
  }

  @Override
  public void performTest() throws Exception {
    performEStoESTransition(Arrays.asList(getObject(ES_CAPABILITY_1)));
    
    SequenceMessage msg = mustBeTransitioned(MSG_FUNCTIONALEXCHANGE_1);
    assertNotNull(msg.getInvokedOperation());
    
    Scenario scenario = mustBeTransitioned(ES_CAPABILITY_1);
    Type type1 = scenario.getOwnedInstanceRoles().stream().map(ir -> ir.getRepresentedInstance().getType()).filter(t -> getObject(LC_1).equals(t)).findFirst().get();
    Type type2 = scenario.getOwnedInstanceRoles().stream().map(ir -> ir.getRepresentedInstance().getType()).filter(t -> getObject(LC_2).equals(t)).findFirst().get();
    assertNotNull(type1);
    assertNotNull(type2);
    
  }

}
