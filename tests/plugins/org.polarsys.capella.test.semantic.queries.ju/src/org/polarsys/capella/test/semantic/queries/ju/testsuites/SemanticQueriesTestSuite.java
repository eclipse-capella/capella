/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *    Obeo - 2304 In Semantic Browser, add Involved Activities category for Operational Capability
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.semantic.queries.ju.UniqueIDInPluginsTest;
import org.polarsys.capella.test.semantic.queries.ju.categoryregistry.testcases.CategoryRegistry_GatherAllCategories;
import org.polarsys.capella.test.semantic.queries.ju.categoryregistry.testcases.CategoryRegistry_GatherTechnicalCategories;
import org.polarsys.capella.test.semantic.queries.ju.testcases.*;

import junit.framework.Test;

public class SemanticQueriesTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new UniqueIDInPluginsTest());
    tests.add(new AvailableForTypeClassExistTest());
    tests.add(new AbstractFunction_mother_activity_allocation());
    tests.add(new AbstractFunction_mother_function_allocation());
    tests.add(new AbstractFunction_ReferencingScenarios());
    tests.add(new CapellaElement_applied_property_value_groups());
    tests.add(new CapellaElement_applied_property_values());
    tests.add(new PropertyValue_applying_valued_element());
    tests.add(new PropertyValueGroup_applying_valued_element());
    tests.add(new EntryExitPoint_ParentRegionTest());
    tests.add(new State_OwnedEntryExitPointsTest());
    tests.add(new Capability_InvolvedComponents());
    tests.add(new Capability_ExternalReferencedFunctionalChains());
    tests.add(new Capability_ExternalReferencedScenarios());
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
    tests.add(new AllRelatedDiagrams());
    tests.add(new AllRelatedTables());
    tests.add(new ContextualElements());
    tests.add(new ElementOfInterests());
    tests.add(new ElementOfInterestOfRepresentations());
    tests.add(new OperationParent());
    tests.add(new OperationParameters());
    tests.add(new ParameterParent());
    tests.add(new UnionDiscriminant());
    tests.add(new UnionDefaultProperty());
    tests.add(new UnionProperties());
    tests.add(new ClassProperties());
    tests.add(new CollectionOperations());
    tests.add(new PhysicalQuantityUnit());
    tests.add(new NumericValueUnit());
    tests.add(new DataValueType());
    tests.add(new UnitReferencingPhysicalQuantities());
    tests.add(new UnitReferencingNumericValues());
    tests.add(new ExchangesItemExchangeItemElements());
    tests.add(new ExchangeItemElementReferencedProperties());
    tests.add(new ExchangesItemAllocationParent());
    tests.add(new EnumerationLiteralParent());
    tests.add(new EnumerationLiteralDomainValue());
    tests.add(new LiteralBooleanValueParent());
    tests.add(new BinaryExpressionLeftOperand());
    tests.add(new BinaryExpressionRightOperand());
    tests.add(new UnaryExpressionOperand());
    tests.add(new MultiplicityElementDefaultValue());
    tests.add(new MultiplicityElementMaxCard());
    tests.add(new MultiplicityElementMinCard());
    tests.add(new MultiplicityElementMax());
    tests.add(new MultiplicityElementMin());
    tests.add(new MultiplicityElementNull());
    tests.add(new MultiplicityElementIndexes());
    tests.add(new MultiplicityElementMaxLength());
    tests.add(new MultiplicityElementMinLength());
    tests.add(new DataTypeRealizedInformation());
    tests.add(new DataTypeRealizingInformation());
    tests.add(new DataTypeDefaultValue());
    tests.add(new DataTypeElementsMinValue());
    tests.add(new DataTypeElementsMaxValue());
    tests.add(new DataTypeElementsNullValue());
    tests.add(new DataTypeElementsDomainType());
    tests.add(new DataTypeElementsMinLength());
    tests.add(new DataTypeElementsMaxLength());
    tests.add(new OperationalActivity_InvolvingCapabilities());
    tests.add(new OperationalCapability_InvolvedActivities());
    tests.add(new OperationalCapability_ExternalReferencedOperationalProcesses());
    tests.add(new FunctionalChainInternalLinks());
    tests.add(new FunctionalChainFlatFunctions());
    tests.add(new FunctionalChainFlatExchanges());
    tests.add(new FunctionalChainEndingFunctions());
    tests.add(new FunctionalChainStartingFunctions());
    tests.add(new FunctionalChain_ExternalReferencedFunctionalChains());
    tests.add(new OperationalProcessFlatOperationalActivities());
    tests.add(new OperationalProcessFlatInteractions());
    tests.add(new OperationalProcessEndingOperationalActivities());
    tests.add(new OperationalProcessStartingOperationalActivities());
    tests.add(new OperationalProcess_ExternalReferencedOperationalProcesses());
    tests.add(new StateFragment_RelatedFunctions());
    tests.add(new StateFragment_RelatedOperationalActivities());
    tests.add(new Scenario_InvokedMessages());
    tests.add(new Scenario_RelatedStates());
    tests.add(new Scenario_RelatedOperationalActivities());
    tests.add(new Scenario_RelatedFunctions());
    tests.add(new Scenario_ReferencedScenarios());
    tests.add(new Scenario_RepresentedInstances());
    tests.add(new CategoryRegistry_GatherTechnicalCategories());
    tests.add(new CategoryRegistry_GatherAllCategories());
    tests.add(new CommunicationMeansAllocatedInteractions());
    tests.add(new CommunicationMeansConnectedEntities());
    tests.add(new ComponentExchangeAllocatingPhysicalLink());
    tests.add(new ComponentExchangeAllocatedFunctionalExchanges());
    tests.add(new FunctionalExchangeAllocatingComponentExchange());
    tests.add(new FunctionalExchangeAllocatingCommunicationMean());
    tests.add(new PhysicalLinkAllocatedComponentExchanges());
    tests.add(new PhysicalPortIncomingPhysicalLinks());
    tests.add(new FunctionalExchangeConnectedOperationalActivities());
    tests.add(new FunctionalExchangeConnectedFunctions());
    tests.add(new FunctionInputPort_IncomingFunctionalExchanges());
    tests.add(new FunctionOutputPort_OutgoingFunctionalExchanges());
    tests.add(new EntityOutgoingCommunicationMeans());
    tests.add(new EntityIncomingCommunicationMeans());
    tests.add(new PhysicalPathInvolvedPhysicalLinks());
    tests.add(new PhysicalPathInvolvedComponents());
    tests.add(new PartDeploymentLinkDeployedPart());
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
