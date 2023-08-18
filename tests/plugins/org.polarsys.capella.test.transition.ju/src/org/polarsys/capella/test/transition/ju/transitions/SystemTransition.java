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

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Contextuality of System Transition
 * 
 * Expected Result:\
 * - Performing System transition on System shall propagate Communication Links, interface references and inner ports
 */
public class SystemTransition extends TopDownTransitionTestCase {
  
  public static final String SYSTEM = "da6047a4-11a6-4bc1-b1fa-95ce413fce48"; //$NON-NLS-1$
  public static final String CP_1 = "f984c5ad-1885-45c2-b6cd-1bcdcc17dde2"; //$NON-NLS-1$
  public static final String LINK_TO_EXCHANGEITEM_1 = "16300ff6-fe2f-4bfc-b1b2-d6bdf06e48e7"; //$NON-NLS-1$
  public static final String INTERFACE_IMPLEMENTATION_TO_INTERFACE_1 = "73045f6b-ab34-47bb-b642-70debf08aeb1"; //$NON-NLS-1$
  public static final String INTERFACE_1 = "bf9cf1ae-f2a0-44cc-9877-65e9bbd3d9b3"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SystemTransition");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
  }

  private void step1() {
    performSystemTransition(Arrays.asList(getObject(SYSTEM)));
    mustBeTransitioned(SYSTEM);
    mustBeTransitioned(CP_1);
    mustBeTransitioned(LINK_TO_EXCHANGEITEM_1);
    mustBeTransitioned(INTERFACE_IMPLEMENTATION_TO_INTERFACE_1);
  }

  private void step2() {
    performInterfaceTransition(Arrays.asList(getObject(INTERFACE_1)));
    mustBeTransitioned(INTERFACE_1);
  }
}
