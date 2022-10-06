/*******************************************************************************
 * Copyright (c) 2017, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.projection.ju;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.projection.scenario.fc2fs.FC2FSExt;
import org.polarsys.capella.core.projection.scenario.fc2fs.FC2FSInitialization;
import org.polarsys.capella.test.framework.api.BasicCommandTestCase;

/**
 * A basic test for the generate interfaces from allocated functions command using a self-describing test model, given
 * the name of a component on which the command should be executed.
 * 
 * The test model itself must contain all required information for the test to execute:
 * 
 * The test model is simply the expected result of the command execution. The test runs then as follows:
 * <ol>
 * <li>The generate interface command is executed. If that execution causes a change in the model, the test fails. This
 * tests that repeated execution does not update the model (the command is thus idempotent)</li>
 * <li>For all component ports of the test component and their connected ports, all required/provided interface
 * references are cleared and stored in the test. Also clears the source reference of all owned transfo links of these
 * interfaces, otherwise the transformation engine will think the interfaces have already been transformed and do
 * nothing.
 * <li>The generate interfaces command is executed on the test component</li>
 * <li>For all component ports of the test component and their connected component ports, all required/provided
 * interfaces are compared with the interfaces stored during 2)</li>
 * <li>The test succeeds if no differences are detected, otherwise it fails.</li>
 */
public class FC2FSTest extends BasicCommandTestCase {

    @Override
    public List<String> getRequiredTestModels() {
        return Collections.singletonList("FunctionalChain"); //$NON-NLS-1$
    }

    @Override
    public void performTest() throws Exception {
        doTestForOperationProcess();
        doTestForFunctionalChain();
    }

    private void doTestForOperationProcess() throws InvocationTargetException, InterruptedException {
        // Get OperationalProcess
        OperationalProcess op = (OperationalProcess) getObject("2c176c1f-b034-4756-b360-b7c8b03b94de");
        Map<String, FunctionalChainInvolvementLink> mapFCIL = new HashMap<>();
        FunctionalChainInvolvementLink fcil1 = (FunctionalChainInvolvementLink) getObject("88ad433d-84ee-4f0d-9b99-ddc1da6b13db");
        mapFCIL.put(((FunctionalExchange)fcil1.getInvolved()).getName(), fcil1);
        FunctionalChainInvolvementLink fcil2 = (FunctionalChainInvolvementLink) getObject("db3a922c-5016-45a9-8b6b-43c6a48cd1cd");
        mapFCIL.put(((FunctionalExchange)fcil2.getInvolved()).getName(), fcil2);
        FunctionalChainInvolvementLink fcil3 = (FunctionalChainInvolvementLink) getObject("5af2bb94-3487-4f97-97e1-48e54d27c072");
        mapFCIL.put(((FunctionalExchange)fcil3.getInvolved()).getName(), fcil3);
        OperationalCapabilityPkg pkg = (OperationalCapabilityPkg) getObject("49beed77-b030-4523-87ce-aa31468e4cd3");

        assertTrue(pkg.getOwnedOperationalCapabilities().isEmpty());

        doTransition(op);

        assertTrue(!pkg.getOwnedOperationalCapabilities().isEmpty());
        assertTrue(!pkg.getOwnedOperationalCapabilities().get(0).getOwnedScenarios().isEmpty());
        
        Scenario scenario = pkg.getOwnedOperationalCapabilities().get(0).getOwnedScenarios().get(0);
        checkSequenceMessagesEIs(op, mapFCIL, scenario);
    }

    private void doTestForFunctionalChain() throws InvocationTargetException, InterruptedException {
        // Get functional Chain
        FunctionalChain fc = (FunctionalChain) getObject("eaee0573-c9c0-4d7a-8017-c4da1e3e83bf");
        Map<String, FunctionalChainInvolvementLink> mapFCIL = new HashMap<>();
        FunctionalChainInvolvementLink fcil1 = (FunctionalChainInvolvementLink) getObject("9fd4ee58-75ed-4cd3-97ee-f5b02196e831");
        mapFCIL.put(((FunctionalExchange)fcil1.getInvolved()).getName(), fcil1);
        FunctionalChainInvolvementLink fcil2 = (FunctionalChainInvolvementLink) getObject("05db359b-0046-4879-a56a-bef7175821e2");
        mapFCIL.put(((FunctionalExchange)fcil2.getInvolved()).getName(), fcil2);
        FunctionalChainInvolvementLink fcil3 = (FunctionalChainInvolvementLink) getObject("af5340c2-93cc-4e9b-867f-39c9d9bf04a4");
        mapFCIL.put(((FunctionalExchange)fcil3.getInvolved()).getName(), fcil3);
        CapabilityRealizationPkg cpkg = (CapabilityRealizationPkg) getObject("767a78a6-3a4f-4104-a13c-be44dedc8928");

        assertTrue(cpkg.getOwnedCapabilityRealizations().isEmpty());

        doTransition(fc);

        assertTrue(!cpkg.getOwnedCapabilityRealizations().isEmpty());
        assertTrue(!cpkg.getOwnedCapabilityRealizations().get(0).getOwnedScenarios().isEmpty());
        
        Scenario scenario = cpkg.getOwnedCapabilityRealizations().get(0).getOwnedScenarios().get(0);
        checkSequenceMessagesEIs(fc, mapFCIL, scenario);
    }

    private void checkSequenceMessagesEIs(FunctionalChain op, Map<String, FunctionalChainInvolvementLink> mapFCIL, Scenario scenario) {
        assertTrue(!scenario.getOwnedTraces().isEmpty());
        assertTrue(scenario.getOwnedTraces().get(0) instanceof TransfoLink);
        
        assertTrue(scenario.getOwnedTraces().get(0).getTargetElement().equals(op));
        
        for (SequenceMessage mess : scenario.getOwnedMessages()) {
            FunctionalChainInvolvementLink fcil = mapFCIL.get(mess.getName());
            assertTrue(fcil != null);
            assertTrue(mess.getExchangedItems().equals(fcil.getExchangedItems()));
        }
    }
    
    private void doTransition(FunctionalChain fc) throws InvocationTargetException, InterruptedException {
        IRunnableWithProgress runnable = new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                monitor.setTaskName("FC2FS");
                FC2FSInitialization initialization = new FC2FSInitialization();
                initialization.execute(Arrays.asList(fc));
            }
        };

        new ProgressMonitorDialog(FC2FSExt.getActiveShell()).run(false, false, runnable);
    }

}
