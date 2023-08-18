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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition correctly performs System transition and Actor Transitions
 */
public class CreateRule_ES2ES_SystemAllocation extends TopDownTransitionTestCase {
  
  public static final String A = "bd140284-00b1-49ca-875b-d20beea542f1"; //$NON-NLS-1$
  public static final String B = "e93436ce-36cd-4f21-8c4f-06958789b2b4"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_1 = "8ab020dc-a83e-48a7-b32a-eb688997d89f"; //$NON-NLS-1$
  public static final String SYSTEM = "a82620c0-fa55-4eb6-90e6-6df80505d401"; //$NON-NLS-1$
  public static final String SA_2 = "b79bc129-e6e0-4a74-a301-e8db83ea49da"; //$NON-NLS-1$
  
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    performEStoESTransition(Arrays.asList(getObject(ES_CAPABILITY_1)));
    mustBeTransitioned(ES_CAPABILITY_1);
    Component ls = mustBeTransitioned(SYSTEM);
    Component la = mustBeTransitioned(SA_2);
    
    LogicalFunction lfa = mustBeTransitioned(A);
    LogicalFunction lfb = mustBeTransitioned(B);
    assertTrue(ls.getAllocatedFunctions().contains(lfa));
    assertTrue(la.getAllocatedFunctions().contains(lfb));
  }

}
