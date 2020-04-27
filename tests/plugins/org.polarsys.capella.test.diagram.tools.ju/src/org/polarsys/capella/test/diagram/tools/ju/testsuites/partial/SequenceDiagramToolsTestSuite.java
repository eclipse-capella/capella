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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.sequence.AddMultipleLifeLinesForExistingComponent;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CancelArmTimer;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateActor;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateArmTimer;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateCombinedFragmentGroupOperand;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateComponent;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateComponentExchange;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateConstraintElement;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateDuration;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateFunction;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateFunctionalExchange;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateMessage;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateOperand;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateReference;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateRole;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateSequenceMessage;
import org.polarsys.capella.test.diagram.tools.ju.sequence.CreateSequenceMessageInteraction;
import org.polarsys.capella.test.diagram.tools.ju.sequence.DeleteMessage;
import org.polarsys.capella.test.diagram.tools.ju.sequence.DeleteSequenceMessageInteraction;
import org.polarsys.capella.test.diagram.tools.ju.sequence.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertAllocatedFunctions;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertInvolvedStatesAndModes;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertRemoveActors;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertRemoveComponents;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertRemoveConstraints;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertRemoveExchangeContext;
import org.polarsys.capella.test.diagram.tools.ju.sequence.InsertRemoveFunctions;
import org.polarsys.capella.test.diagram.tools.ju.sequence.LostAndFoundFunctionalExchange;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class SequenceDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SequenceDiagramToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SequenceDiagramProject");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CreateActor());
    tests.add(new CreateRole());
    tests.add(new CreateComponent());
    tests.add(new CreateFunction());
    tests.add(new CreateComponentExchange());
    tests.add(new CreateFunctionalExchange());
    tests.add(new CreateSequenceMessage());
    tests.add(new CreateMessage());
    tests.add(new DeleteMessage());
    tests.add(new CreateSequenceMessageInteraction());
    tests.add(new DeleteSequenceMessageInteraction());
    tests.add(new LostAndFoundFunctionalExchange());
    tests.add(new CreateArmTimer());
    tests.add(new CancelArmTimer());
    tests.add(new AddMultipleLifeLinesForExistingComponent());
    tests.add(new CreateCombinedFragmentGroupOperand());
    tests.add(new CreateReference());
    tests.add(new CreateOperand());
    tests.add(new CreateDuration());

    tests.add(new InsertRemoveActors());
    tests.add(new InsertRemoveComponents());
    tests.add(new InsertAllocatedFunctions());
    tests.add(new InsertInvolvedStatesAndModes());
    tests.add(new InsertRemoveFunctions());
    tests.add(new InsertRemoveExchangeContext());

    tests.add(new CreateConstraint());
    tests.add(new CreateConstraintElement());
    tests.add(new InsertRemoveConstraints());
    tests.add(new DragAndDropTest());

    return tests;
  }
}
