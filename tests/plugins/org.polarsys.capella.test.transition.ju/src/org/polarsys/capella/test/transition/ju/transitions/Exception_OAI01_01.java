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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create OperationalActivity into Operational Activities named OARoot
 * - Create OperationalActivity into OARoot named OA1
 * - Create OperationalActivity into OA1 named OA11
 * - Create OperationalActivity into OA11 named OA111
 * - Create OperationalActivity into OA11 named OA112
 * - Create OperationalActivity into OA1 named OA12
 * - Create OperationalActivity into OA1 named OA13
 * - Create OperationalActivity into OA1 named OA14
 * - Create FunctionalExchange into OA1 named FE11
 * - Create FunctionalExchange into OA1 named FE12
 * 
 * - Perform transition on OARoot
 * - Remove FE11t and FE12t
 * - Create a OA function and move OA111t and OA112t into
 * - Remove OA11t
 * 
 * Expected Result:\
 * -  A functional transition on root should perform transition of  FE12 and not FE11
 * - Moreover OA11 should not be transitioned
 * </pre>
 * 
 */
public class Exception_OAI01_01 extends TopDownTransitionTestCase {
  private String id_oaroot = "050449ed-dbcf-4344-a922-f3a2b238115b";
  private String id_oa11 = "950baf9e-cadf-4a27-8b2f-4616922f30c9";
  private String id_fe11 = "68f36482-a1bd-4c13-8546-61b5999ac958";
  private String id_fe12 = "565c5728-b664-42de-aec6-947b26d59f5c";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_oaroot)));
    OperationalActivity oa11 = getObject(id_oa11);
    assertNotNull(NLS.bind(Messages.NullElement, "OA11"), oa11);
    // OA11 should not be transitioned
    NamedElement oa11t = (OperationalActivity) ProjectionTestUtils.getAllocatingElement(oa11);
    assertNull(NLS.bind(Messages.ShouldBeTransitioned, oa11.getName()), oa11t);

    FunctionalExchange fe11 = getObject(id_fe11);
    assertNotNull(NLS.bind(Messages.NullElement, "FE11"), fe11);
    // FE11 should not be transitioned
    NamedElement fe11t = (FunctionalExchange) ProjectionTestUtils.getAllocatingElement(fe11);
    assertNull(NLS.bind(Messages.ShouldBeTransitioned, fe11.getName()), fe11t);

    FunctionalExchange fe12 = getObject(id_fe12);
    assertNotNull(NLS.bind(Messages.NullElement, "FE12"), fe12);
    // FE12 must be transitioned
    NamedElement fe12t = (FunctionalExchange) ProjectionTestUtils.getAllocatingElement(fe12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, fe12.getName()), fe12t);
  }

}
