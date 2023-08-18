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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SystemFunction into System Functions named SFRoot
 * - Create FunctionRealization into SFRoot named FunctionRealization
 * - Create SystemFunction into SFRoot named SF1
 * - Create FunctionOutputPort into SF1 named FOP11
 * - Create SystemFunction into SFRoot named SF2
 * - Create FunctionInputPort into SF2 named FIP21
 * - Create FunctionOutputPort into SF2 named FOP21
 * - Create SystemFunction into SFRoot named SF3
 * - Create FunctionInputPort into SF3 named FIP31
 * - Create FunctionOutputPort into SF3 named FOP31
 * - Create SystemFunction into SFRoot named SF4
 * - Create FunctionInputPort into SF4 named FIP41
 * - Create FunctionalChain into SFRoot named FC1
 * - Create FunctionalChainInvolvement into FC1 to SF1
 * - Create FunctionalChainInvolvement into FC1 to FE1
 * - Create FunctionalChainInvolvement into FC1 to SF2
 * - Create FunctionalChainInvolvement into FC1 to FE2
 * - Create FunctionalChainInvolvement into FC1 to SF3
 * - Create FunctionalChainInvolvement into FC1 to FE3
 * - Create FunctionalChainInvolvement into FC1 to SF4
 * - Create FunctionalExchange into SFRoot named FE1
 * - Create FunctionalExchange into SFRoot named FE2
 * - Create FunctionalExchange into SFRoot named FE3
 * - Perform transition on SFRoot
 * - Modify FC1
 * 
 * Expected Result:\
 * - FC1t should not be updated
 * </pre>
 * 
 */
public class UpdateRule_FC01_01 extends TopDownTransitionTestCase {
  private String id_sfroot = "4a75cc0c-61e0-4f30-a93a-c2ab6bd0ace8";
  private String id_fc1 = "58a308b6-1e2f-4871-92a7-f0aa9750ca28";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_sfroot)));
    FunctionalChain fc1 = getObject(id_fc1);
    assertNotNull(NLS.bind(Messages.NullElement, "FC1"), fc1);
    // FC1 must be transitioned
    NamedElement fc1t = (FunctionalChain) ProjectionTestUtils.getAllocatingElement(fc1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, fc1.getName()), fc1t);

    assertTrue(NLS.bind(Messages.ShouldNotBeUpdated, fc1.getName()),
        ((FunctionalChain) fc1t).getInvolvedInvolvements().size() != (fc1).getInvolvedInvolvements().size());
  }

}
