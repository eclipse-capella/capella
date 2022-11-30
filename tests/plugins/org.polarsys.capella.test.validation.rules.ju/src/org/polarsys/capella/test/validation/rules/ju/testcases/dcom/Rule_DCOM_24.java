/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.dcom;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.polarsys.capella.common.flexibility.wizards.ui.CompoundMultiStatus;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.ui.quickfix.resolver.AddAllExchangeItemsToSequenceMessageResolver;
import org.polarsys.capella.test.framework.api.IOracleDefinition;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.framework.helpers.log.FormatedLogger;
import org.polarsys.capella.test.framework.helpers.log.FormatedSysoutLogger;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

/**
 * test on DCOM_24: This rule checks that Sequence Messages allocated ExchangeItems are consistent with their invoked
 * operation ExchangeItems. Only raises a warning if none of the invoked operation ExchangeItems are allocated to the
 * Sequence Message.
 */
public class Rule_DCOM_24 extends AbstractRulesOnDesignTest {

    protected FormatedLogger logger = new FormatedSysoutLogger();

    /**
     * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
     * @generated
     */
    protected EClass getTargetedEClass() {
        return InteractionPackage.Literals.SEQUENCE_MESSAGE;
    }

    /**
     * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
     * @generated
     */
    protected String getRuleID() {
        return "org.polarsys.capella.core.data.interaction.validation.DCOM_24";
    }
    
    @Override
    protected boolean getCheckQuickFix() {
        return true;
    }

    /**
     * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
     * @generated
     */
    protected List<String> getScopeDefinition() {
        return Arrays.asList(
                // SequenceMessages invoking FunctionalExchanges
                "c10f833f-84e7-4dbc-ade9-271b2cc3ff55", "fa1de654-4a15-4bfb-8894-6463cf853843", "fabc8ba8-d847-446f-acf1-b5058b4f98a1",
                // SequenceMessages invoking ComponentExchanges
                "c16b6582-fda0-444e-9f80-c53f5bcdcfa0", "f3f9d688-35c9-41c1-a781-1d3cd1f48dac", "c0a51532-c307-4e97-b263-a0ec5f6bf159",
                // SequenceMessages invoking Interactions
                "fdfc08d0-7202-4cf3-abf6-3c7ab7982bfa", "6b84764a-7d28-4217-9b55-1eba6941613e", "aaba469f-c5df-4080-a3e8-b42a11fe437f",
                // SequenceMessages invoking Interactions with correctly allocated EI
                "c17be500-c92f-461c-a9d4-e679a3f5f7ea", 
                // SequenceMessages invoking ComponentExchanges with correctly allocated EI
                "affe6248-ea74-4e21-9dcc-b8695139b031",
                // SequenceMessages invoking Interactions with subset of allocated EI
                "85aea90c-32a7-40e8-aed1-29e560a1d29d",
                // SequenceMessages invoking ComponentExchanges with subset of allocated EI
                "ff7785c8-6927-41e6-aab7-ecb7835827b2");
    }

    /**
     * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
     * @generated
     */
    protected List<OracleDefinition> getOracleDefinitions() {
        return Arrays.asList(
                // SequenceMessages invoking FunctionalExchanges (1 = missing EI , 0 = no EI and non expected)
                new OracleDefinition("c10f833f-84e7-4dbc-ade9-271b2cc3ff55", 1), new OracleDefinition("fa1de654-4a15-4bfb-8894-6463cf853843", 1),
                new OracleDefinition("fabc8ba8-d847-446f-acf1-b5058b4f98a1", 0),
                // SequenceMessages invoking Interactions and correctly allocated subset of EI (so expected 0)
                new OracleDefinition("85aea90c-32a7-40e8-aed1-29e560a1d29d", 0),
                // SequenceMessages invoking ComponentExchanges (1 = missing EI , 0 = no EI and non expected)
                new OracleDefinition("c16b6582-fda0-444e-9f80-c53f5bcdcfa0", 1), new OracleDefinition("f3f9d688-35c9-41c1-a781-1d3cd1f48dac", 0),
                new OracleDefinition("c0a51532-c307-4e97-b263-a0ec5f6bf159", 1),
                // SequenceMessages invoking Interactions (1 = missing EI , 0 = no EI and non expected)
                new OracleDefinition("fdfc08d0-7202-4cf3-abf6-3c7ab7982bfa", 1), new OracleDefinition("6b84764a-7d28-4217-9b55-1eba6941613e", 1),
                new OracleDefinition("aaba469f-c5df-4080-a3e8-b42a11fe437f", 0),
                // SequenceMessages invoking Interactions and correctly allocated EI (so expected 0)
                new OracleDefinition("c17be500-c92f-461c-a9d4-e679a3f5f7ea", 0),
                // SequenceMessages invoking ComponentExchanges with correctly allocated EI (so expected 0)
                new OracleDefinition("affe6248-ea74-4e21-9dcc-b8695139b031", 0),
                // SequenceMessages invoking ComponentExchanges with correctly allocated subset of EI (so expected 0)
                new OracleDefinition("ff7785c8-6927-41e6-aab7-ecb7835827b2", 0));
    }
    
    @Override
    protected IStatus testCheckQuickFix(List<IMarker> markers) {
        IStatus result = Status.OK_STATUS;
        for(IMarker marker: markers) {
            List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
            if (!((modelElements.isEmpty()) || !(modelElements.get(0) instanceof SequenceMessage))) {
                final SequenceMessage sequenceMessage = (SequenceMessage) modelElements.get(0);
                AddAllExchangeItemsToSequenceMessageResolver resolver = new AddAllExchangeItemsToSequenceMessageResolver();
                
                // Fix the problem
                resolver.run(marker);
                
                Diagnostician diagnostician = new Diagnostician();
                Diagnostic diagnostic = diagnostician.validate(sequenceMessage);

                List<OracleDefinition> oracleDefinitions = getOracleDefinitions();
                Map<String, IOracleDefinition> elementIdToOracleMap = computeElementIdToOracleMap(oracleDefinitions);
                String elementId = getId(sequenceMessage);
                IOracleDefinition rootOracle = elementIdToOracleMap.get(elementId);
                
                boolean res = diagnostic.getSeverity() == Diagnostic.OK && rootOracle != null && rootOracle.getNbExpectedErrors() > 0;
                if (!res) {
                    IStatus error = Status.error("QuickFix not applied");
                    if (result == Status.OK_STATUS) {
                        result = error;
                    } else {
                        CompoundMultiStatus cms = new CompoundMultiStatus("org.polarsys.capella.core.data.interaction.ui.quickfix");
                        cms.add(result);
                        cms.add(error);
                        result = cms;
                    }
                }
            }
        }
        return result;
    }
}
