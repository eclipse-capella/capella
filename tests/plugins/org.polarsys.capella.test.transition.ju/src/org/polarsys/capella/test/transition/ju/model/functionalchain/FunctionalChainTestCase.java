/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.model.functionalchain;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * This test case tests the transition of Functional Chain concepts (InvolvementFunction, InvolvementLink, ControlNode,
 * SequenceLink, etc.)
 *
 */
public class FunctionalChainTestCase extends TopDownTransitionTestCase {
  public static final String ROOT_SYSTEM_FUNCTION = "fd9646a7-933a-4cfc-b7a7-489aaedf24ea"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_1 = "42c42003-2947-4bcc-9d9d-c3482141daaa"; //$NON-NLS-1$
  public static final String CONSTRAINT_PRECONDITION = "03f7fde0-b47f-47d9-b7d4-11d0d74e9cb0"; //$NON-NLS-1$
  public static final String CONSTRAINT_POSTCONDITION = "c0318b6e-8c4b-427f-aedd-2fb32ed8427a"; //$NON-NLS-1$
  public static final String FCIL_TO_FUNCTIONALEXCHANGE_1 = "47514abb-a26d-4d89-8984-2459c4f9e0e0"; //$NON-NLS-1$
  public static final String CONSTRAINT_EXCHANGE_CONTEXT = "c3774912-f860-46ab-bb4c-72aa49e12572"; //$NON-NLS-1$
  public static final String FCIF_TO_SYSTEMFUNCTION_2 = "a4d794b0-ba3d-4749-bea7-73e8c718c230"; //$NON-NLS-1$
  public static final String FCIF_TO_SYSTEMFUNCTION_1 = "eb4ad48a-3605-4854-bf1f-56e892e8de9e"; //$NON-NLS-1$
  public static final String FCIL_TO_FUNCTIONALEXCHANGE_2 = "69c9afcc-d2c6-4d9c-9e87-f321a5f29571"; //$NON-NLS-1$
  public static final String FCIF_TO_SYSTEMFUNCTION_3 = "ea96236c-9f78-4628-aec9-0ff8fbd0e4d8"; //$NON-NLS-1$
  public static final String FCIF_TO_SYSTEMFUNCTION_5 = "607b947f-adfa-469e-abdc-f71d5f87ec76"; //$NON-NLS-1$
  public static final String FCIL_TO_FUNCTIONALEXCHANGE_4 = "e1dea68e-fb35-47f9-951f-1b940043f3a3"; //$NON-NLS-1$
  public static final String FUNCTIONALCHAIN_2 = "943df8ea-766e-4431-bc86-d941b81695e6"; //$NON-NLS-1$
  public static final String FCR_TO_FUNCTIONALCHAIN_1 = "0ccaee8a-1209-4d48-8d08-2b061bfe0677"; //$NON-NLS-1$
  public static final String FCIF_TO_SYSTEMFUNCTION_4 = "35061671-93fd-4343-8ed0-5d442298cbaa"; //$NON-NLS-1$
  public static final String FCIL_TO_FUNCTIONALEXCHANGE_3 = "af2a4142-66f4-4aed-87b9-35a5612a134b"; //$NON-NLS-1$
  public static final String FCIL_TO_FUNCTIONALEXCHANGE_5 = "5ecc2be9-7a13-4abd-8818-569418618655"; //$NON-NLS-1$
  public static final String AND_CONTROL_NODE_BEGIN = "328b0c2a-6d94-43c4-bde8-221f061196ec"; //$NON-NLS-1$
  public static final String AND_CONTROL_NODE_END = "6932e696-1d58-4441-bf07-4f3df3e4f660"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_1 = "d518903f-8f40-4992-9ba8-14c60bc28865"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_2 = "0bcc079c-22ac-4090-9fcd-4ccdbd69d868"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_3 = "0af903b6-cd54-4ba5-8209-a7b7bc879da2"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_4 = "396cc681-d282-4ff6-8fbb-84dfe47d1216"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_5 = "a95291a0-96ea-4037-bd4b-66f68863d731"; //$NON-NLS-1$
  public static final String SEQUENCE_LINK_6 = "9cfafbf7-2c2b-4d30-88b6-dc1f4e40cec0"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("FunctionalChain");
  }

  @Override
  public void performTest() throws Exception {
    EObject rootSF = getObject(ROOT_SYSTEM_FUNCTION);
    performFunctionalTransition(Arrays.asList(rootSF));
    mustBeTransitionedAndLinkedToTransitioned(FUNCTIONALCHAIN_1, CONSTRAINT_PRECONDITION,
        FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION);
    mustBeTransitionedAndLinkedToTransitioned(FUNCTIONALCHAIN_1, CONSTRAINT_POSTCONDITION,
        FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION);
    mustBeTransitionedAndLinkedToTransitioned(FCIL_TO_FUNCTIONALEXCHANGE_1, CONSTRAINT_EXCHANGE_CONTEXT,
        FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT);
    mustBeTransitioned(FCIF_TO_SYSTEMFUNCTION_2);
    mustBeTransitioned(FCIF_TO_SYSTEMFUNCTION_1);
    mustBeTransitioned(FCIL_TO_FUNCTIONALEXCHANGE_2);
    mustBeTransitioned(FCIF_TO_SYSTEMFUNCTION_3);
    mustBeTransitioned(FCIF_TO_SYSTEMFUNCTION_5);
    mustBeTransitioned(FUNCTIONALCHAIN_2);
    mustBeTransitioned(FCR_TO_FUNCTIONALCHAIN_1);
    mustBeTransitioned(FCIF_TO_SYSTEMFUNCTION_4);
    mustBeTransitioned(FCIL_TO_FUNCTIONALEXCHANGE_3);
    mustBeTransitioned(FCIL_TO_FUNCTIONALEXCHANGE_5);
    mustBeTransitioned(AND_CONTROL_NODE_BEGIN);
    mustBeTransitioned(AND_CONTROL_NODE_END);
    mustBeTransitioned(SEQUENCE_LINK_1);
    mustBeTransitioned(SEQUENCE_LINK_2);
    mustBeTransitioned(SEQUENCE_LINK_3);
    mustBeTransitioned(SEQUENCE_LINK_4);
    mustBeTransitioned(SEQUENCE_LINK_5);
    mustBeTransitioned(SEQUENCE_LINK_6);
  }

  public static Test suite() {
    return new FunctionalChainTestCase();
  }
}
