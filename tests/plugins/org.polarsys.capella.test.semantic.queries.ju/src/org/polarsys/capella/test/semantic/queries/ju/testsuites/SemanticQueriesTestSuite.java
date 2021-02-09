/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.queries.ju.testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.semantic.queries.ju.UniqueIDInPluginsTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_activity_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractFunction_mother_function_allocation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractStateParentActiveElements;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AbstractTypeTypingElements;
import org.polarsys.capella.test.semantic.queries.ju.testcases.AvailableForTypeClassExistTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityExtendedCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityExtendingCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityGeneralizedCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityGeneralizingCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityIncludedCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityIncludingCapabilities;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedFunctionalChains;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_InvolvedFunctions;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_OwnedFunctionalChains;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_RealizedCapability;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapabilityRealization_RealizingCapabilityRealization;
import org.polarsys.capella.test.semantic.queries.ju.testcases.Capability_InvolvedComponents;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_value_groups;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaElement_applied_property_values;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsAbstractCapabilityExtendSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsAbstractCapabilityExtendTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsAbstractCapabilityIncludeSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsAbstractCapabilityIncludeTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsCapabilityExploitationSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsCapabilityExploitationTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsGeneralizationSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsGeneralizationTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInterfaceImplementationSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInterfaceImplementationTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInterfaceUseSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInterfaceUseTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInvolvementSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsInvolvementTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsTraceSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.CapellaRelationshipsTraceTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.ComponentAllocatingPhysicalPorts;
import org.polarsys.capella.test.semantic.queries.ju.testcases.ComponentRepresentingParts;
import org.polarsys.capella.test.semantic.queries.ju.testcases.DataValueRefReferencedProperty;
import org.polarsys.capella.test.semantic.queries.ju.testcases.EntryExitPoint_ParentRegionTest;
import org.polarsys.capella.test.semantic.queries.ju.testcases.ExchangeItem_realizedEI;
import org.polarsys.capella.test.semantic.queries.ju.testcases.ExchangeItem_realizingEI;
import org.polarsys.capella.test.semantic.queries.ju.testcases.FragmentAllocatedFunction;
import org.polarsys.capella.test.semantic.queries.ju.testcases.FragmentAllocatedState;
import org.polarsys.capella.test.semantic.queries.ju.testcases.GeneralizedComponents;
import org.polarsys.capella.test.semantic.queries.ju.testcases.GeneralizingComponents;
import org.polarsys.capella.test.semantic.queries.ju.testcases.InterfaceExchangedItems;
import org.polarsys.capella.test.semantic.queries.ju.testcases.InterfaceGeneralizedElements;
import org.polarsys.capella.test.semantic.queries.ju.testcases.InterfaceGeneralizingElements;
import org.polarsys.capella.test.semantic.queries.ju.testcases.InterfaceInheritedExchangedItems;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PhysicalAllocatedComponentPorts;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyAssociation;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyTypeElements;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValueGroup_applying_valued_element;
import org.polarsys.capella.test.semantic.queries.ju.testcases.PropertyValue_applying_valued_element;
import org.polarsys.capella.test.semantic.queries.ju.testcases.RelationshipAssociationRoles;
import org.polarsys.capella.test.semantic.queries.ju.testcases.RelationshipGeneralizationSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.RelationshipGeneralizationTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.SequenceMessageFunctionSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.SequenceMessageFunctionTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.SequenceMessagePartSource;
import org.polarsys.capella.test.semantic.queries.ju.testcases.SequenceMessagePartTarget;
import org.polarsys.capella.test.semantic.queries.ju.testcases.StateTransition_realizedStateTransition;
import org.polarsys.capella.test.semantic.queries.ju.testcases.StateTransition_realizingStateTransition;
import org.polarsys.capella.test.semantic.queries.ju.testcases.State_OwnedEntryExitPointsTest;

import junit.framework.Test;

public class SemanticQueriesTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new UniqueIDInPluginsTest());
    tests.add(new AvailableForTypeClassExistTest());
    tests.add(new AbstractFunction_mother_activity_allocation());
    tests.add(new AbstractFunction_mother_function_allocation());
    tests.add(new CapellaElement_applied_property_value_groups());
    tests.add(new CapellaElement_applied_property_values());
    tests.add(new PropertyValue_applying_valued_element());
    tests.add(new PropertyValueGroup_applying_valued_element());
    tests.add(new EntryExitPoint_ParentRegionTest());
    tests.add(new State_OwnedEntryExitPointsTest());
    tests.add(new Capability_InvolvedComponents());
    tests.add(new CapabilityRealization_InvolvedFunctionalChains());
    tests.add(new CapabilityRealization_InvolvedFunctions());
    tests.add(new CapabilityRealization_OwnedFunctionalChains());
    tests.add(new CapabilityRealization_RealizedCapability());
    tests.add(new CapabilityRealization_RealizingCapabilityRealization());
    tests.add(new ExchangeItem_realizedEI());
    tests.add(new ExchangeItem_realizingEI());
    tests.add(new CapabilityExtendedCapabilities());
    tests.add(new CapabilityExtendingCapabilities());
    tests.add(new CapabilityIncludedCapabilities());
    tests.add(new CapabilityIncludingCapabilities());
    tests.add(new CapabilityGeneralizedCapabilities());
    tests.add(new CapabilityGeneralizingCapabilities());
    tests.add(new FragmentAllocatedFunction());
    tests.add(new FragmentAllocatedState());
    tests.add(new PhysicalAllocatedComponentPorts());
    tests.add(new ComponentAllocatingPhysicalPorts());
    tests.add(new InterfaceGeneralizedElements());
    tests.add(new InterfaceGeneralizingElements());
    tests.add(new GeneralizedComponents());
    tests.add(new GeneralizingComponents());
    tests.add(new InterfaceInheritedExchangedItems());
    tests.add(new InterfaceExchangedItems());
    tests.add(new RelationshipGeneralizationSource());
    tests.add(new RelationshipGeneralizationTarget());
    tests.add(new RelationshipAssociationRoles());
    tests.add(new AbstractTypeTypingElements());
    tests.add(new PropertyAssociation());
    tests.add(new PropertyTypeElements());
    tests.add(new CapellaRelationshipsGeneralizationSource());
    tests.add(new CapellaRelationshipsGeneralizationTarget());
    tests.add(new CapellaRelationshipsAbstractCapabilityIncludeSource());
    tests.add(new CapellaRelationshipsAbstractCapabilityIncludeTarget());
    tests.add(new CapellaRelationshipsAbstractCapabilityExtendSource());
    tests.add(new CapellaRelationshipsAbstractCapabilityExtendTarget());
    tests.add(new CapellaRelationshipsCapabilityExploitationSource());
    tests.add(new CapellaRelationshipsCapabilityExploitationTarget());
    tests.add(new CapellaRelationshipsInvolvementSource());
    tests.add(new CapellaRelationshipsInvolvementTarget());
    tests.add(new CapellaRelationshipsInterfaceImplementationSource());
    tests.add(new CapellaRelationshipsInterfaceImplementationTarget());
    tests.add(new CapellaRelationshipsInterfaceUseSource());
    tests.add(new CapellaRelationshipsInterfaceUseTarget());
    tests.add(new CapellaRelationshipsTraceSource());
    tests.add(new CapellaRelationshipsTraceTarget());
    tests.add(new SequenceMessageFunctionSource());
    tests.add(new SequenceMessageFunctionTarget());
    tests.add(new SequenceMessagePartSource());
    tests.add(new SequenceMessagePartTarget());
    tests.add(new AbstractStateParentActiveElements());
    tests.add(new ComponentRepresentingParts());
    tests.add(new DataValueRefReferencedProperty());
    tests.add(new StateTransition_realizedStateTransition());
    tests.add(new StateTransition_realizingStateTransition());
    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList("SemanticQueries");
  }

  /**
   * Added in order to launch this test suite without the Capella test framework.
   */
  public static Test suite() {
    return new SemanticQueriesTestSuite();
  }
}
