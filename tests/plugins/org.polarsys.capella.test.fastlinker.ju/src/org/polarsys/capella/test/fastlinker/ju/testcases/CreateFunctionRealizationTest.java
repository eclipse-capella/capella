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
package org.polarsys.capella.test.fastlinker.ju.testcases;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerActivator;

/**
 * Create a Realization trace between a SystemFunction and an OperationalActivity.
 */
public class CreateFunctionRealizationTest extends AbstractFastLinkerTest {

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("TestsFastLinker");
  }

  @Override
  public void test() throws Exception {
    executeCommand(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        //
        // Test data creation.
        //
        Project testProject = getTestProject();
        final String oa1Name = "OperationalActivity1";
        final String sf1Name = "SystemFunction1";
        // Add an Activity to "Root Operational Activity".
        OperationalActivity rootOperationalActivity = ModelQueryHelper.getRootOperationalActivity(testProject);
        OperationalActivity oa1 = OaFactory.eINSTANCE.createOperationalActivity();
        oa1.setName(oa1Name);
        rootOperationalActivity.getOwnedFunctions().add(oa1);
        // Add a SystemFunction to "Root System Function".
        SystemFunction rootSystemFunction = ModelQueryHelper.getRootSystemFunction(testProject);
        SystemFunction sf1 = CtxFactory.eINSTANCE.createSystemFunction();
        sf1.setName(sf1Name);
        rootSystemFunction.getOwnedFunctions().add(sf1);
        //
        // Call FastLinker.
        //
        boolean acceptOA1 = FastLinkerActivator.getDefault().getFastLinkerManager().acceptElementInFastLinker(Collections.singletonList(oa1));
        assertTrue("FastLinker should accept " + oa1Name, acceptOA1);
        FastLinkerActivator.getDefault().getFastLinkerManager().putElementInFastLinker(Collections.singletonList(oa1));
        boolean acceptSF1 = FastLinkerActivator.getDefault().getFastLinkerManager().acceptElementInFastLinker(Collections.singletonList(sf1));
        assertTrue("FastLinker should accept " + sf1Name, acceptSF1);
        FastLinkerActivator.getDefault().getFastLinkerManager().putElementInFastLinker(Collections.singletonList(sf1));
        //
        // Check the link has been correctly created.
        //
        List<FunctionRealization> ownedFunctionRealizations = sf1.getOwnedFunctionRealizations();
        assertTrue(sf1Name + " should contain exactly 1 realization", 1 == ownedFunctionRealizations.size());
        FunctionRealization functionRealization = ownedFunctionRealizations.get(0);
        assertTrue("Source element of the created realization should be " + sf1Name, sf1 == functionRealization.getSourceElement());
        assertTrue("Target element of the created realization should be " + oa1Name, oa1 == functionRealization.getTargetElement());
      }
    });
  }
}
