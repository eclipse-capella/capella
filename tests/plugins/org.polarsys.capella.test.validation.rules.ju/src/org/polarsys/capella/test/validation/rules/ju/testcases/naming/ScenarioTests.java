/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.naming;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Test name conflict constraints on Scenarios.
 * 
 * By definition, direct children of a Scenario can never have a name conflict.
 * TODO explain why, write tests.
 */
public class ScenarioTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.core.validation.I_08",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }

  private final String NAME1 = "wololo"; //$NON-NLS-1$
  
  /**
   * Two instance roles with the exact same name are allowed.
   */
  public void scenario1(){
    Scenario s1 = InteractionFactory.eINSTANCE.createScenario("sampleScenario"); //$NON-NLS-1$
    s1.getOwnedInstanceRoles().add(InteractionFactory.eINSTANCE.createInstanceRole(NAME1));
    s1.getOwnedInstanceRoles().add(InteractionFactory.eINSTANCE.createInstanceRole(NAME1));
    ok(s1);
  }
  
  /**
   * An instance role and a property value with the exact same name are allowed.
   */
  public void scenario2(){
    Scenario s1 = InteractionFactory.eINSTANCE.createScenario("sampleScenario"); //$NON-NLS-1$
    s1.getOwnedInstanceRoles().add(InteractionFactory.eINSTANCE.createInstanceRole(NAME1));
    s1.getOwnedPropertyValues().add(CapellacoreFactory.eINSTANCE.createBooleanPropertyValue(NAME1));
    ok(s1);
  }

  @Override
  public void test() throws Exception {
    scenario1();
    scenario2();
  }

}
