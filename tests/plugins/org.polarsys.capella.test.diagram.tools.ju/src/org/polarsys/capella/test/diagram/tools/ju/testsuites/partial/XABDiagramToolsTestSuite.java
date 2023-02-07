/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.test.diagram.tools.ju.xab.ConstraintRename;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateActor;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateBehaviorPCGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateCEWithDelegations;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateComponent;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateComponentExchangeGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateComponentInStructureXAB;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateComponentPort;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateComponentPortAllocation;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateConstraint;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateConstraintElement;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateEABConfigurationItems;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateFunction;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateFunctionPort;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateFunctionalChain;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateFunctionalExchange;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateNodePCGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreatePhysicalLink;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreatePhysicalPath;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreatePhysicalPort;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreatePortAllocation;
import org.polarsys.capella.test.diagram.tools.ju.xab.CreateRole;
import org.polarsys.capella.test.diagram.tools.ju.xab.DeleteConstraintLink;
import org.polarsys.capella.test.diagram.tools.ju.xab.DeleteSequenceLink;
import org.polarsys.capella.test.diagram.tools.ju.xab.DiagramPartIcon;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropEABTest;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropFunction;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropOABActors;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropPhysicalComponent;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropTest;
import org.polarsys.capella.test.diagram.tools.ju.xab.DragAndDropXABComponents;
import org.polarsys.capella.test.diagram.tools.ju.xab.ElementsFromModeAndStates;
import org.polarsys.capella.test.diagram.tools.ju.xab.ElementsFromScenario;
import org.polarsys.capella.test.diagram.tools.ju.xab.InitializeDiagramInvalidFunction;
import org.polarsys.capella.test.diagram.tools.ju.xab.ManageBehaviorPCsDeployment;
import org.polarsys.capella.test.diagram.tools.ju.xab.ManageEABRealizedPhysicalArtifacts;
import org.polarsys.capella.test.diagram.tools.ju.xab.ManageFunctionAllocation;
import org.polarsys.capella.test.diagram.tools.ju.xab.ManageNodePCsDeployment;
import org.polarsys.capella.test.diagram.tools.ju.xab.ManageRoleAllocation;
import org.polarsys.capella.test.diagram.tools.ju.xab.PhysicalLinkIconAndLabel;
import org.polarsys.capella.test.diagram.tools.ju.xab.PhysicalPathDisplay;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReconnectComponentExchange;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReconnectFunctionalExchange;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReconnectPhysicalLink;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReuseActors;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReuseComponents;
import org.polarsys.capella.test.diagram.tools.ju.xab.ReuseEABConfigurationItems;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideActors;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideActorsMultipart;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideAllocatedRoles;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideAppliedPropertyValuesGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideBehaviorPCGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideComponentExchanges;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideComponentPortAllocations;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideComponents;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideComponentsMultipart;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideConstraints;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideEABConfigurationItems;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideEABRealizedPhysicalArtifacts;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideFETestCase;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideFunctionPorts;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideFunctionalChains;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideFunctionalExchanges;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHideNodePCGroup;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHidePhysicalLinks;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHidePhysicalPath;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHidePortAllocations;
import org.polarsys.capella.test.diagram.tools.ju.xab.ShowHidePorts;
import org.polarsys.capella.test.diagram.tools.ju.xab.SwitchCategoryWithDelegation;
import org.polarsys.capella.test.diagram.tools.ju.xab.SwitchComponentExchangesCategories;
import org.polarsys.capella.test.diagram.tools.ju.xab.SwitchFunctionalExchangesCategories;
import org.polarsys.capella.test.diagram.tools.ju.xab.SwitchPhysicalLinksCategories;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XABDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XABDiagramToolsTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("XABDiagrams", "EmptyProject");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();

    tests.add(new CreatePhysicalLink());
    tests.add(new CreateCEWithDelegations());
    tests.add(new ShowHideFunctionalChains());
    tests.add(new DragAndDropFunction());
    tests.add(new SwitchComponentExchangesCategories());
    tests.add(new ConstraintRename());

    tests.add(new CreateNodePCGroup());
    tests.add(new CreatePhysicalPath());
    tests.add(new CreatePhysicalPort());
    tests.add(new ReconnectPhysicalLink());
    tests.add(new ManageNodePCsDeployment());

    tests.add(new ShowHidePhysicalLinks());
    tests.add(new ShowHideNodePCGroup());
    tests.add(new ShowHideComponentPortAllocations());
    tests.add(new SwitchPhysicalLinksCategories());
    tests.add(new ShowHidePhysicalPath());

    tests.add(new DiagramPartIcon());
    tests.add(new PhysicalLinkIconAndLabel());
    tests.add(new PhysicalPathDisplay());

    tests.add(new CreateBehaviorPCGroup());
    tests.add(new CreateComponent());
    tests.add(new CreateComponentInStructureXAB());
    tests.add(new CreateComponentExchangeGroup());
    tests.add(new CreateActor());
    tests.add(new CreateComponentPort());
    tests.add(new CreateComponentPortAllocation());
    tests.add(new ReconnectComponentExchange());
    tests.add(new ManageBehaviorPCsDeployment());
    tests.add(new ReuseComponents());
    tests.add(new ReuseActors());

    tests.add(new ShowHideComponents());
    tests.add(new ShowHideComponentExchanges());
    tests.add(new ShowHideActors());
    tests.add(new ShowHideBehaviorPCGroup());
    tests.add(new ShowHidePorts());

    tests.add(new ShowHideComponentsMultipart());
    tests.add(new ShowHideActorsMultipart());

    tests.add(new CreateRole());
    tests.add(new ShowHideAllocatedRoles());
    tests.add(new ManageRoleAllocation());

    tests.add(new CreateFunction());
    tests.add(new CreateFunctionalExchange());
    tests.add(new CreateFunctionPort());
    tests.add(new CreateFunctionalChain());
    tests.add(new CreatePortAllocation());
    tests.add(new ManageFunctionAllocation());
    tests.add(new ReconnectFunctionalExchange());
    tests.add(new SwitchFunctionalExchangesCategories());

    tests.add(new XABShowHideFunctionsTestSuite());
    tests.add(new ShowHideFunctionalExchanges());
    tests.add(new ShowHidePortAllocations());
    tests.add(new ShowHideFunctionPorts());
    tests.add(new InitializeDiagramInvalidFunction());

    tests.add(new CreateConstraint());
    tests.add(new CreateConstraintElement());
    tests.add(new ShowHideConstraints());
    tests.add(new ShowHideAppliedPropertyValuesGroup());
    tests.add(new DeleteConstraintLink());
    tests.add(new DeleteSequenceLink());

    tests.add(new ElementsFromScenario());
    tests.add(new ElementsFromModeAndStates());

    tests.add(new CreateEABConfigurationItems());
    tests.add(new ReuseEABConfigurationItems());
    tests.add(new ManageEABRealizedPhysicalArtifacts());
    tests.add(new ShowHideEABConfigurationItems());
    tests.add(new ShowHideEABRealizedPhysicalArtifacts());

    tests.add(new DragAndDropPhysicalComponent());
    tests.add(new DragAndDropTest());
    tests.add(new DragAndDropEABTest());
    tests.add(new DragAndDropOABActors());
    tests.add(new DragAndDropXABComponents());

    tests.add(new ShowHideFETestCase());
    tests.add(new SwitchCategoryWithDelegation());

    return tests;
  }

}
