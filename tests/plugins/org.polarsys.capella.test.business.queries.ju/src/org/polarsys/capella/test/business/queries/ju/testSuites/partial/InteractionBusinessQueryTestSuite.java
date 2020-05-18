/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.AbstractCapabilityExtend_Extended;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.AbstractCapabilityGeneralization_Super;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.AbstractCapabilityInclude_Included;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.InstanceRole_RepresentedInstance;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.InteractionUse_ReferencedScenario;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.Scenario_RealizedScenario;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_ExchangedItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_InvokedOperation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_ServiceInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.StateFragment_RelatedAbstractFunction;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.StateFragment_RelatedAbstractState;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

/**
 * @author Erwan Brottier
 */
public class InteractionBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new InteractionBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new AbstractCapabilityExtend_Extended());
    tests.add(new AbstractCapabilityGeneralization_Super());
    tests.add(new AbstractCapabilityInclude_Included());
    tests.add(new InstanceRole_RepresentedInstance());
    tests.add(new InteractionUse_ReferencedScenario());
    tests.add(new Scenario_RealizedScenario());
    tests.add(new SequenceMessage_ExchangedItems());
    tests.add(new SequenceMessage_InvokedOperation());
    tests.add(new SequenceMessage_ServiceInterface());
    tests.add(new StateFragment_RelatedAbstractFunction());
    tests.add(new StateFragment_RelatedAbstractState());    
    return tests;
  }
}
