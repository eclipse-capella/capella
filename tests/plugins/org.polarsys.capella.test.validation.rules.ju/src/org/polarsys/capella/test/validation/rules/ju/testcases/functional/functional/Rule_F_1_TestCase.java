/*******************************************************************************
 * Copyright (c) 2024, 2024 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.functional.functional;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Ports are not allowed on non-leaf functions
 * (meaning that a functional exchange has not been allocated down to a leaf function).
 */
public class Rule_F_1_TestCase extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] { "org.polarsys.capella.core.data.fa.validation.F_1" });
  }

  public void Rule_F_1_TestCase_OK() {
    final FunctionInputPort functionInputPort = FaFactory.eINSTANCE.createFunctionInputPort();
    final FunctionOutputPort functionOutputPort = FaFactory.eINSTANCE.createFunctionOutputPort();

    final SystemFunction sysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    sysFunction.getInputs().add(functionInputPort);
    sysFunction.getOutputs().add(functionOutputPort);

    ok(functionInputPort);
    ok(functionOutputPort);
  }

  /**
   * 
   */
  public void Rule_F_1_TestCase_KO() {
    final FunctionInputPort functionInputPort = FaFactory.eINSTANCE.createFunctionInputPort();
    final FunctionOutputPort functionOutputPort = FaFactory.eINSTANCE.createFunctionOutputPort();

    final SystemFunction sysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    sysFunction.getInputs().add(functionInputPort);
    sysFunction.getOutputs().add(functionOutputPort);

    // Make sysFunction an non-leaf function, by assigning it a sub-function
    final SystemFunction subSysFunction = CtxFactory.eINSTANCE.createSystemFunction();
    sysFunction.getOwnedFunctions().add(subSysFunction);

    ko(functionInputPort);
    ko(functionOutputPort);
  }

  @Override
  public void test() throws Exception {
    Rule_F_1_TestCase_OK();
    Rule_F_1_TestCase_KO();
  }

}
