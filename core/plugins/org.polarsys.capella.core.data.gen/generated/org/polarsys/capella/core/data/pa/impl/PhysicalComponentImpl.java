/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.pa.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.impl.AbstractPhysicalArtifactImpl;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAbstractTypedElements <em>Abstract Typed Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getTypedElements <em>Typed Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedFunctionalAllocation <em>Owned Functional Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getFunctionalAllocations <em>Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAllocatedFunctions <em>Allocated Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedGeneralizations <em>Owned Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedFeatures <em>Owned Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getContainedProperties <em>Contained Properties</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedInterfaceAllocations <em>Owned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getProvisionedInterfaceAllocations <em>Provisioned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAllocatedInterfaces <em>Allocated Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedCommunicationLinks <em>Owned Communication Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getProduce <em>Produce</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getConsume <em>Consume</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSend <em>Send</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getReceive <em>Receive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getCall <em>Call</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getExecute <em>Execute</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getWrite <em>Write</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAccess <em>Access</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAcquire <em>Acquire</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getTransmit <em>Transmit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#isActor <em>Actor</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#isHuman <em>Human</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedInterfaceUses <em>Owned Interface Uses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getUsedInterfaceLinks <em>Used Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getUsedInterfaces <em>Used Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedInterfaceImplementations <em>Owned Interface Implementations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getImplementedInterfaceLinks <em>Implemented Interface Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getImplementedInterfaces <em>Implemented Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedComponentRealizations <em>Owned Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRealizedComponents <em>Realized Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRealizingComponents <em>Realizing Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getContainedComponentPorts <em>Contained Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getContainedParts <em>Contained Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getContainedPhysicalPorts <em>Contained Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalPath <em>Owned Physical Path</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalLinks <em>Owned Physical Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalLinkCategories <em>Owned Physical Link Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRepresentingParts <em>Representing Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getCapabilityRealizationInvolvements <em>Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployingLinks <em>Deploying Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeploymentLinks <em>Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getNature <em>Nature</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalComponents <em>Owned Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getLogicalInterfaceRealizations <em>Logical Interface Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getSubPhysicalComponents <em>Sub Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getRealizedLogicalComponents <em>Realized Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalComponentImpl#getDeployingPhysicalComponents <em>Deploying Physical Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalComponentImpl extends AbstractPhysicalArtifactImpl implements PhysicalComponent {





	/**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected static final String NAME_EDEFAULT = null;





	/**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected String name = NAME_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedTraces() <em>Owned Traces</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedTraces()
   * @generated
   * @ordered
   */
	protected EList<Trace> ownedTraces;





	/**
   * The cached value of the '{@link #getNamingRules() <em>Naming Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNamingRules()
   * @generated
   * @ordered
   */
	protected EList<NamingRule> namingRules;





	/**
   * The cached value of the '{@link #getOwnedFunctionalAllocation() <em>Owned Functional Allocation</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalAllocation()
   * @generated
   * @ordered
   */
	protected EList<ComponentFunctionalAllocation> ownedFunctionalAllocation;





	/**
   * The cached value of the '{@link #getOwnedComponentExchanges() <em>Owned Component Exchanges</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchanges()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchange> ownedComponentExchanges;





	/**
   * The cached value of the '{@link #getOwnedComponentExchangeCategories() <em>Owned Component Exchange Categories</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchangeCategories()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchangeCategory> ownedComponentExchangeCategories;





	/**
   * The cached value of the '{@link #getInExchangeLinks() <em>In Exchange Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInExchangeLinks()
   * @generated
   * @ordered
   */
	protected EList<ExchangeLink> inExchangeLinks;





	/**
   * The cached value of the '{@link #getOutExchangeLinks() <em>Out Exchange Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOutExchangeLinks()
   * @generated
   * @ordered
   */
	protected EList<ExchangeLink> outExchangeLinks;





	/**
   * The cached value of the '{@link #getOwnedAbstractCapabilityPkg() <em>Owned Abstract Capability Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAbstractCapabilityPkg()
   * @generated
   * @ordered
   */
	protected AbstractCapabilityPkg ownedAbstractCapabilityPkg;





	/**
   * The cached value of the '{@link #getOwnedInterfacePkg() <em>Owned Interface Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfacePkg()
   * @generated
   * @ordered
   */
	protected InterfacePkg ownedInterfacePkg;





	/**
   * The cached value of the '{@link #getOwnedDataPkg() <em>Owned Data Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDataPkg()
   * @generated
   * @ordered
   */
	protected DataPkg ownedDataPkg;





	/**
   * The cached value of the '{@link #getOwnedStateMachines() <em>Owned State Machines</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedStateMachines()
   * @generated
   * @ordered
   */
	protected EList<StateMachine> ownedStateMachines;





	/**
   * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
	protected static final boolean ABSTRACT_EDEFAULT = false;





	/**
   * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedGeneralizations() <em>Owned Generalizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedGeneralizations()
   * @generated
   * @ordered
   */
	protected EList<Generalization> ownedGeneralizations;





	/**
   * The cached value of the '{@link #getOwnedFeatures() <em>Owned Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFeatures()
   * @generated
   * @ordered
   */
	protected EList<Feature> ownedFeatures;





	/**
   * The cached value of the '{@link #getOwnedInterfaceAllocations() <em>Owned Interface Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfaceAllocations()
   * @generated
   * @ordered
   */
	protected EList<InterfaceAllocation> ownedInterfaceAllocations;





	/**
   * The cached value of the '{@link #getOwnedCommunicationLinks() <em>Owned Communication Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCommunicationLinks()
   * @generated
   * @ordered
   */
	protected EList<CommunicationLink> ownedCommunicationLinks;





	/**
   * The default value of the '{@link #isActor() <em>Actor</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isActor()
   * @generated
   * @ordered
   */
	protected static final boolean ACTOR_EDEFAULT = false;





	/**
   * The cached value of the '{@link #isActor() <em>Actor</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isActor()
   * @generated
   * @ordered
   */
	protected boolean actor = ACTOR_EDEFAULT;





	/**
   * The default value of the '{@link #isHuman() <em>Human</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isHuman()
   * @generated
   * @ordered
   */
	protected static final boolean HUMAN_EDEFAULT = false;





	/**
   * The cached value of the '{@link #isHuman() <em>Human</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isHuman()
   * @generated
   * @ordered
   */
	protected boolean human = HUMAN_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedInterfaceUses() <em>Owned Interface Uses</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfaceUses()
   * @generated
   * @ordered
   */
	protected EList<InterfaceUse> ownedInterfaceUses;





	/**
   * The cached value of the '{@link #getOwnedInterfaceImplementations() <em>Owned Interface Implementations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfaceImplementations()
   * @generated
   * @ordered
   */
	protected EList<InterfaceImplementation> ownedInterfaceImplementations;





	/**
   * The cached value of the '{@link #getOwnedComponentRealizations() <em>Owned Component Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentRealizations()
   * @generated
   * @ordered
   */
	protected EList<ComponentRealization> ownedComponentRealizations;





	/**
   * The cached value of the '{@link #getOwnedPhysicalPath() <em>Owned Physical Path</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalPath()
   * @generated
   * @ordered
   */
	protected EList<PhysicalPath> ownedPhysicalPath;





	/**
   * The cached value of the '{@link #getOwnedPhysicalLinks() <em>Owned Physical Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinks()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLink> ownedPhysicalLinks;





	/**
   * The cached value of the '{@link #getOwnedPhysicalLinkCategories() <em>Owned Physical Link Categories</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalLinkCategories()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLinkCategory> ownedPhysicalLinkCategories;





	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final PhysicalComponentKind KIND_EDEFAULT = PhysicalComponentKind.UNSET;





	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected PhysicalComponentKind kind = KIND_EDEFAULT;





	/**
   * The default value of the '{@link #getNature() <em>Nature</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNature()
   * @generated
   * @ordered
   */
	protected static final PhysicalComponentNature NATURE_EDEFAULT = PhysicalComponentNature.UNSET;





	/**
   * The cached value of the '{@link #getNature() <em>Nature</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNature()
   * @generated
   * @ordered
   */
	protected PhysicalComponentNature nature = NATURE_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedDeploymentLinks() <em>Owned Deployment Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDeploymentLinks()
   * @generated
   * @ordered
   */
	protected EList<AbstractDeploymentLink> ownedDeploymentLinks;





	/**
   * The cached value of the '{@link #getOwnedPhysicalComponents() <em>Owned Physical Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalComponents()
   * @generated
   * @ordered
   */
	protected EList<PhysicalComponent> ownedPhysicalComponents;





	/**
   * The cached value of the '{@link #getOwnedPhysicalComponentPkgs() <em>Owned Physical Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<PhysicalComponentPkg> ownedPhysicalComponentPkgs;





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalComponentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return PaPackage.Literals.PHYSICAL_COMPONENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getName() {

    return name;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setName(String newName) {

    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__NAME, oldName, name));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractTypedElement> getAbstractTypedElements() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractTypedElement> resultAsList = (Collection<AbstractTypedElement>) result;
    return new EcoreEList.UnmodifiableEList<AbstractTypedElement>(this, ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES);
    }
    return ownedTraces;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<GenericTrace> getContainedGenericTraces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<GenericTrace> resultAsList = (Collection<GenericTrace>) result;
    return new EcoreEList.UnmodifiableEList<GenericTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<NamingRule> getNamingRules() {

    if (namingRules == null) {
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, PaPackage.PHYSICAL_COMPONENT__NAMING_RULES);
    }
    return namingRules;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<TypedElement> getTypedElements() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.TYPE__TYPED_ELEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.TYPE__TYPED_ELEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<TypedElement> resultAsList = (Collection<TypedElement>) result;
    return new EcoreEList.UnmodifiableEList<TypedElement>(this, CapellacorePackage.Literals.TYPE__TYPED_ELEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocation() {

    if (ownedFunctionalAllocation == null) {
      ownedFunctionalAllocation = new EObjectContainmentEList.Resolving<ComponentFunctionalAllocation>(ComponentFunctionalAllocation.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION);
    }
    return ownedFunctionalAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchange> getOwnedComponentExchanges() {

    if (ownedComponentExchanges == null) {
      ownedComponentExchanges = new EObjectContainmentEList<ComponentExchange>(ComponentExchange.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES);
    }
    return ownedComponentExchanges;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchangeCategory> getOwnedComponentExchangeCategories() {

    if (ownedComponentExchangeCategories == null) {
      ownedComponentExchangeCategories = new EObjectContainmentEList<ComponentExchangeCategory>(ComponentExchangeCategory.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    }
    return ownedComponentExchangeCategories;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getFunctionalAllocations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentFunctionalAllocation> resultAsList = (Collection<ComponentFunctionalAllocation>) result;
    return new EcoreEList.UnmodifiableEList<ComponentFunctionalAllocation>(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractFunction> getAllocatedFunctions() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeLink> getInExchangeLinks() {

    if (inExchangeLinks == null) {
      inExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS);
    }
    return inExchangeLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeLink> getOutExchangeLinks() {

    if (outExchangeLinks == null) {
      outExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS);
    }
    return outExchangeLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapabilityPkg getOwnedAbstractCapabilityPkg() {

    if (ownedAbstractCapabilityPkg != null && ownedAbstractCapabilityPkg.eIsProxy()) {
      InternalEObject oldOwnedAbstractCapabilityPkg = (InternalEObject)ownedAbstractCapabilityPkg;
      ownedAbstractCapabilityPkg = (AbstractCapabilityPkg)eResolveProxy(oldOwnedAbstractCapabilityPkg);
      if (ownedAbstractCapabilityPkg != oldOwnedAbstractCapabilityPkg) {
        InternalEObject newOwnedAbstractCapabilityPkg = (InternalEObject)ownedAbstractCapabilityPkg;
        NotificationChain msgs = oldOwnedAbstractCapabilityPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, null, null);
        if (newOwnedAbstractCapabilityPkg.eInternalContainer() == null) {
          msgs = newOwnedAbstractCapabilityPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, ownedAbstractCapabilityPkg));
      }
    }
    return ownedAbstractCapabilityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapabilityPkg basicGetOwnedAbstractCapabilityPkg() {

    return ownedAbstractCapabilityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedAbstractCapabilityPkg(AbstractCapabilityPkg newOwnedAbstractCapabilityPkg, NotificationChain msgs) {

    AbstractCapabilityPkg oldOwnedAbstractCapabilityPkg = ownedAbstractCapabilityPkg;
    ownedAbstractCapabilityPkg = newOwnedAbstractCapabilityPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedAbstractCapabilityPkg(AbstractCapabilityPkg newOwnedAbstractCapabilityPkg) {

    if (newOwnedAbstractCapabilityPkg != ownedAbstractCapabilityPkg) {
      NotificationChain msgs = null;
      if (ownedAbstractCapabilityPkg != null)
        msgs = ((InternalEObject)ownedAbstractCapabilityPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
      if (newOwnedAbstractCapabilityPkg != null)
        msgs = ((InternalEObject)newOwnedAbstractCapabilityPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
      msgs = basicSetOwnedAbstractCapabilityPkg(newOwnedAbstractCapabilityPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG, newOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterfacePkg getOwnedInterfacePkg() {

    if (ownedInterfacePkg != null && ownedInterfacePkg.eIsProxy()) {
      InternalEObject oldOwnedInterfacePkg = (InternalEObject)ownedInterfacePkg;
      ownedInterfacePkg = (InterfacePkg)eResolveProxy(oldOwnedInterfacePkg);
      if (ownedInterfacePkg != oldOwnedInterfacePkg) {
        InternalEObject newOwnedInterfacePkg = (InternalEObject)ownedInterfacePkg;
        NotificationChain msgs = oldOwnedInterfacePkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, null, null);
        if (newOwnedInterfacePkg.eInternalContainer() == null) {
          msgs = newOwnedInterfacePkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, ownedInterfacePkg));
      }
    }
    return ownedInterfacePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InterfacePkg basicGetOwnedInterfacePkg() {

    return ownedInterfacePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedInterfacePkg(InterfacePkg newOwnedInterfacePkg, NotificationChain msgs) {

    InterfacePkg oldOwnedInterfacePkg = ownedInterfacePkg;
    ownedInterfacePkg = newOwnedInterfacePkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, newOwnedInterfacePkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedInterfacePkg(InterfacePkg newOwnedInterfacePkg) {

    if (newOwnedInterfacePkg != ownedInterfacePkg) {
      NotificationChain msgs = null;
      if (ownedInterfacePkg != null)
        msgs = ((InternalEObject)ownedInterfacePkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, null, msgs);
      if (newOwnedInterfacePkg != null)
        msgs = ((InternalEObject)newOwnedInterfacePkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, null, msgs);
      msgs = basicSetOwnedInterfacePkg(newOwnedInterfacePkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG, newOwnedInterfacePkg, newOwnedInterfacePkg));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataPkg getOwnedDataPkg() {

    if (ownedDataPkg != null && ownedDataPkg.eIsProxy()) {
      InternalEObject oldOwnedDataPkg = (InternalEObject)ownedDataPkg;
      ownedDataPkg = (DataPkg)eResolveProxy(oldOwnedDataPkg);
      if (ownedDataPkg != oldOwnedDataPkg) {
        InternalEObject newOwnedDataPkg = (InternalEObject)ownedDataPkg;
        NotificationChain msgs = oldOwnedDataPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, null, null);
        if (newOwnedDataPkg.eInternalContainer() == null) {
          msgs = newOwnedDataPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, oldOwnedDataPkg, ownedDataPkg));
      }
    }
    return ownedDataPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataPkg basicGetOwnedDataPkg() {

    return ownedDataPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDataPkg(DataPkg newOwnedDataPkg, NotificationChain msgs) {

    DataPkg oldOwnedDataPkg = ownedDataPkg;
    ownedDataPkg = newOwnedDataPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, oldOwnedDataPkg, newOwnedDataPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedDataPkg(DataPkg newOwnedDataPkg) {

    if (newOwnedDataPkg != ownedDataPkg) {
      NotificationChain msgs = null;
      if (ownedDataPkg != null)
        msgs = ((InternalEObject)ownedDataPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, null, msgs);
      if (newOwnedDataPkg != null)
        msgs = ((InternalEObject)newOwnedDataPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, null, msgs);
      msgs = basicSetOwnedDataPkg(newOwnedDataPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG, newOwnedDataPkg, newOwnedDataPkg));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StateMachine> getOwnedStateMachines() {

    if (ownedStateMachines == null) {
      ownedStateMachines = new EObjectContainmentEList.Resolving<StateMachine>(StateMachine.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES);
    }
    return ownedStateMachines;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Feature> getOwnedFeatures() {

    if (ownedFeatures == null) {
      ownedFeatures = new EObjectContainmentEList.Resolving<Feature>(Feature.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES);
    }
    return ownedFeatures;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Property> getContainedProperties() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Property> resultAsList = (Collection<Property>) result;
    return new EcoreEList.UnmodifiableEList<Property>(this, CapellacorePackage.Literals.CLASSIFIER__CONTAINED_PROPERTIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isAbstract() {

    return abstract_;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAbstract(boolean newAbstract) {

    boolean oldAbstract = abstract_;
    abstract_ = newAbstract;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__ABSTRACT, oldAbstract, abstract_));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Generalization> getOwnedGeneralizations() {

    if (ownedGeneralizations == null) {
      ownedGeneralizations = new EObjectContainmentEList.Resolving<Generalization>(Generalization.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS);
    }
    return ownedGeneralizations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Generalization> getSuperGeneralizations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Generalization> resultAsList = (Collection<Generalization>) result;
    return new EcoreEList.UnmodifiableEList<Generalization>(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Generalization> getSubGeneralizations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Generalization> resultAsList = (Collection<Generalization>) result;
    return new EcoreEList.UnmodifiableEList<Generalization>(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<GeneralizableElement> getSuper() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<GeneralizableElement> resultAsList = (Collection<GeneralizableElement>) result;
    return new EcoreEList.UnmodifiableEList<GeneralizableElement>(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<GeneralizableElement> getSub() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<GeneralizableElement> resultAsList = (Collection<GeneralizableElement>) result;
    return new EcoreEList.UnmodifiableEList<GeneralizableElement>(this, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceAllocation> getOwnedInterfaceAllocations() {

    if (ownedInterfaceAllocations == null) {
      ownedInterfaceAllocations = new EObjectContainmentEList.Resolving<InterfaceAllocation>(InterfaceAllocation.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS);
    }
    return ownedInterfaceAllocations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceAllocation> getProvisionedInterfaceAllocations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceAllocation> resultAsList = (Collection<InterfaceAllocation>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceAllocation>(this, CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getAllocatedInterfaces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getOwnedCommunicationLinks() {

    if (ownedCommunicationLinks == null) {
      ownedCommunicationLinks = new EObjectContainmentEList.Resolving<CommunicationLink>(CommunicationLink.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS);
    }
    return ownedCommunicationLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getProduce() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getConsume() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getSend() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getReceive() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getCall() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getExecute() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getWrite() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getAccess() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getAcquire() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getTransmit() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isActor() {

    return actor;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setActor(boolean newActor) {

    boolean oldActor = actor;
    actor = newActor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__ACTOR, oldActor, actor));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isHuman() {

    return human;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setHuman(boolean newHuman) {

    boolean oldHuman = human;
    human = newHuman;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__HUMAN, oldHuman, human));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceUse> getOwnedInterfaceUses() {

    if (ownedInterfaceUses == null) {
      ownedInterfaceUses = new EObjectContainmentEList<InterfaceUse>(InterfaceUse.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES);
    }
    return ownedInterfaceUses;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceUse> getUsedInterfaceLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceUse> resultAsList = (Collection<InterfaceUse>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceUse>(this, CsPackage.Literals.COMPONENT__USED_INTERFACE_LINKS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getUsedInterfaces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__USED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__USED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.COMPONENT__USED_INTERFACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceImplementation> getOwnedInterfaceImplementations() {

    if (ownedInterfaceImplementations == null) {
      ownedInterfaceImplementations = new EObjectContainmentEList<InterfaceImplementation>(InterfaceImplementation.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS);
    }
    return ownedInterfaceImplementations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceImplementation> getImplementedInterfaceLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceImplementation> resultAsList = (Collection<InterfaceImplementation>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceImplementation>(this, CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACE_LINKS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getImplementedInterfaces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.COMPONENT__IMPLEMENTED_INTERFACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentRealization> getOwnedComponentRealizations() {

    if (ownedComponentRealizations == null) {
      ownedComponentRealizations = new EObjectContainmentEList.Resolving<ComponentRealization>(ComponentRealization.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS);
    }
    return ownedComponentRealizations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Component> getRealizedComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.COMPONENT__REALIZED_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Component> getRealizingComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__REALIZING_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__REALIZING_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.COMPONENT__REALIZING_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getProvidedInterfaces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.COMPONENT__PROVIDED_INTERFACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getRequiredInterfaces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.COMPONENT__REQUIRED_INTERFACES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentPort> getContainedComponentPorts() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
    return new EcoreEList.UnmodifiableEList<ComponentPort>(this, CsPackage.Literals.COMPONENT__CONTAINED_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Part> getContainedParts() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__CONTAINED_PARTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__CONTAINED_PARTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Part> resultAsList = (Collection<Part>) result;
    return new EcoreEList.UnmodifiableEList<Part>(this, CsPackage.Literals.COMPONENT__CONTAINED_PARTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalPort> getContainedPhysicalPorts() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalPort> resultAsList = (Collection<PhysicalPort>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalPort>(this, CsPackage.Literals.COMPONENT__CONTAINED_PHYSICAL_PORTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalPath> getOwnedPhysicalPath() {

    if (ownedPhysicalPath == null) {
      ownedPhysicalPath = new EObjectContainmentEList.Resolving<PhysicalPath>(PhysicalPath.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH);
    }
    return ownedPhysicalPath;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLink> getOwnedPhysicalLinks() {

    if (ownedPhysicalLinks == null) {
      ownedPhysicalLinks = new EObjectContainmentEList.Resolving<PhysicalLink>(PhysicalLink.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS);
    }
    return ownedPhysicalLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLinkCategory> getOwnedPhysicalLinkCategories() {

    if (ownedPhysicalLinkCategories == null) {
      ownedPhysicalLinkCategories = new EObjectContainmentEList.Resolving<PhysicalLinkCategory>(PhysicalLinkCategory.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES);
    }
    return ownedPhysicalLinkCategories;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Part> getRepresentingParts() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.COMPONENT__REPRESENTING_PARTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.COMPONENT__REPRESENTING_PARTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Part> resultAsList = (Collection<Part>) result;
    return new EcoreEList.UnmodifiableEList<Part>(this, CsPackage.Literals.COMPONENT__REPRESENTING_PARTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Involvement> getInvolvingInvolvements() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Involvement> resultAsList = (Collection<Involvement>) result;
    return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealizationInvolvement> getCapabilityRealizationInvolvements() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealizationInvolvement> resultAsList = (Collection<CapabilityRealizationInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealizationInvolvement>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealization> getInvolvingCapabilityRealizations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractDeploymentLink> getDeployingLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractDeploymentLink> resultAsList = (Collection<AbstractDeploymentLink>) result;
    return new EcoreEList.UnmodifiableEList<AbstractDeploymentLink>(this, CsPackage.Literals.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractDeploymentLink> getDeploymentLinks() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractDeploymentLink> resultAsList = (Collection<AbstractDeploymentLink>) result;
    return new EcoreEList.UnmodifiableEList<AbstractDeploymentLink>(this, CsPackage.Literals.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalComponentKind getKind() {

    return kind;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(PhysicalComponentKind newKind) {

    PhysicalComponentKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__KIND, oldKind, kind));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PhysicalComponentNature getNature() {

    return nature;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setNature(PhysicalComponentNature newNature) {

    PhysicalComponentNature oldNature = nature;
    nature = newNature == null ? NATURE_EDEFAULT : newNature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_COMPONENT__NATURE, oldNature, nature));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractDeploymentLink> getOwnedDeploymentLinks() {

    if (ownedDeploymentLinks == null) {
      ownedDeploymentLinks = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS);
    }
    return ownedDeploymentLinks;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getOwnedPhysicalComponents() {

    if (ownedPhysicalComponents == null) {
      ownedPhysicalComponents = new EObjectContainmentEList.Resolving<PhysicalComponent>(PhysicalComponent.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS);
    }
    return ownedPhysicalComponents;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponentPkg> getOwnedPhysicalComponentPkgs() {

    if (ownedPhysicalComponentPkgs == null) {
      ownedPhysicalComponentPkgs = new EObjectContainmentEList.Resolving<PhysicalComponentPkg>(PhysicalComponentPkg.class, this, PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS);
    }
    return ownedPhysicalComponentPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalInterfaceRealization> getLogicalInterfaceRealizations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalInterfaceRealization> resultAsList = (Collection<LogicalInterfaceRealization>) result;
    return new EcoreEList.UnmodifiableEList<LogicalInterfaceRealization>(this, PaPackage.Literals.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getSubPhysicalComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponent> getRealizedLogicalComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalFunction> getAllocatedPhysicalFunctions() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, PaPackage.Literals.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getDeployedPhysicalComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getDeployingPhysicalComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION:
        return ((InternalEList<?>)getOwnedFunctionalAllocation()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES:
        return ((InternalEList<?>)getOwnedComponentExchanges()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ((InternalEList<?>)getOwnedComponentExchangeCategories()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG:
        return basicSetOwnedAbstractCapabilityPkg(null, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG:
        return basicSetOwnedInterfacePkg(null, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG:
        return basicSetOwnedDataPkg(null, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES:
        return ((InternalEList<?>)getOwnedStateMachines()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS:
        return ((InternalEList<?>)getOwnedGeneralizations()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES:
        return ((InternalEList<?>)getOwnedFeatures()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedInterfaceAllocations()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS:
        return ((InternalEList<?>)getOwnedCommunicationLinks()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES:
        return ((InternalEList<?>)getOwnedInterfaceUses()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
        return ((InternalEList<?>)getOwnedInterfaceImplementations()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS:
        return ((InternalEList<?>)getOwnedComponentRealizations()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH:
        return ((InternalEList<?>)getOwnedPhysicalPath()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS:
        return ((InternalEList<?>)getOwnedPhysicalLinks()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
        return ((InternalEList<?>)getOwnedPhysicalLinkCategories()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
        return ((InternalEList<?>)getOwnedDeploymentLinks()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
        return ((InternalEList<?>)getOwnedPhysicalComponents()).basicRemove(otherEnd, msgs);
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedPhysicalComponentPkgs()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT__NAME:
        return getName();
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS:
        return getAbstractTypedElements();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES:
        return getOwnedTraces();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES:
        return getNamingRules();
      case PaPackage.PHYSICAL_COMPONENT__TYPED_ELEMENTS:
        return getTypedElements();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION:
        return getOwnedFunctionalAllocation();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES:
        return getOwnedComponentExchanges();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return getOwnedComponentExchangeCategories();
      case PaPackage.PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS:
        return getFunctionalAllocations();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS:
        return getAllocatedFunctions();
      case PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS:
        return getInExchangeLinks();
      case PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS:
        return getOutExchangeLinks();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG:
        if (resolve) return getOwnedAbstractCapabilityPkg();
        return basicGetOwnedAbstractCapabilityPkg();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG:
        if (resolve) return getOwnedInterfacePkg();
        return basicGetOwnedInterfacePkg();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG:
        if (resolve) return getOwnedDataPkg();
        return basicGetOwnedDataPkg();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES:
        return getOwnedStateMachines();
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT:
        return isAbstract();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS:
        return getOwnedGeneralizations();
      case PaPackage.PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS:
        return getSuperGeneralizations();
      case PaPackage.PHYSICAL_COMPONENT__SUB_GENERALIZATIONS:
        return getSubGeneralizations();
      case PaPackage.PHYSICAL_COMPONENT__SUPER:
        return getSuper();
      case PaPackage.PHYSICAL_COMPONENT__SUB:
        return getSub();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES:
        return getOwnedFeatures();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PROPERTIES:
        return getContainedProperties();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
        return getOwnedInterfaceAllocations();
      case PaPackage.PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS:
        return getProvisionedInterfaceAllocations();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_INTERFACES:
        return getAllocatedInterfaces();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS:
        return getOwnedCommunicationLinks();
      case PaPackage.PHYSICAL_COMPONENT__PRODUCE:
        return getProduce();
      case PaPackage.PHYSICAL_COMPONENT__CONSUME:
        return getConsume();
      case PaPackage.PHYSICAL_COMPONENT__SEND:
        return getSend();
      case PaPackage.PHYSICAL_COMPONENT__RECEIVE:
        return getReceive();
      case PaPackage.PHYSICAL_COMPONENT__CALL:
        return getCall();
      case PaPackage.PHYSICAL_COMPONENT__EXECUTE:
        return getExecute();
      case PaPackage.PHYSICAL_COMPONENT__WRITE:
        return getWrite();
      case PaPackage.PHYSICAL_COMPONENT__ACCESS:
        return getAccess();
      case PaPackage.PHYSICAL_COMPONENT__ACQUIRE:
        return getAcquire();
      case PaPackage.PHYSICAL_COMPONENT__TRANSMIT:
        return getTransmit();
      case PaPackage.PHYSICAL_COMPONENT__ACTOR:
        return isActor();
      case PaPackage.PHYSICAL_COMPONENT__HUMAN:
        return isHuman();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES:
        return getOwnedInterfaceUses();
      case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACE_LINKS:
        return getUsedInterfaceLinks();
      case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACES:
        return getUsedInterfaces();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
        return getOwnedInterfaceImplementations();
      case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS:
        return getImplementedInterfaceLinks();
      case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES:
        return getImplementedInterfaces();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS:
        return getOwnedComponentRealizations();
      case PaPackage.PHYSICAL_COMPONENT__REALIZED_COMPONENTS:
        return getRealizedComponents();
      case PaPackage.PHYSICAL_COMPONENT__REALIZING_COMPONENTS:
        return getRealizingComponents();
      case PaPackage.PHYSICAL_COMPONENT__PROVIDED_INTERFACES:
        return getProvidedInterfaces();
      case PaPackage.PHYSICAL_COMPONENT__REQUIRED_INTERFACES:
        return getRequiredInterfaces();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS:
        return getContainedComponentPorts();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PARTS:
        return getContainedParts();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS:
        return getContainedPhysicalPorts();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH:
        return getOwnedPhysicalPath();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS:
        return getOwnedPhysicalLinks();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
        return getOwnedPhysicalLinkCategories();
      case PaPackage.PHYSICAL_COMPONENT__REPRESENTING_PARTS:
        return getRepresentingParts();
      case PaPackage.PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case PaPackage.PHYSICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS:
        return getCapabilityRealizationInvolvements();
      case PaPackage.PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS:
        return getInvolvingCapabilityRealizations();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_LINKS:
        return getDeployingLinks();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYMENT_LINKS:
        return getDeploymentLinks();
      case PaPackage.PHYSICAL_COMPONENT__KIND:
        return getKind();
      case PaPackage.PHYSICAL_COMPONENT__NATURE:
        return getNature();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
        return getOwnedDeploymentLinks();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
        return getOwnedPhysicalComponents();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
        return getOwnedPhysicalComponentPkgs();
      case PaPackage.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS:
        return getLogicalInterfaceRealizations();
      case PaPackage.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS:
        return getSubPhysicalComponents();
      case PaPackage.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS:
        return getRealizedLogicalComponents();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS:
        return getAllocatedPhysicalFunctions();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS:
        return getDeployedPhysicalComponents();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS:
        return getDeployingPhysicalComponents();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT__NAME:
          setName((String)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        getOwnedFunctionalAllocation().addAll((Collection<? extends ComponentFunctionalAllocation>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        getOwnedComponentExchanges().addAll((Collection<? extends ComponentExchange>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        getOwnedComponentExchangeCategories().addAll((Collection<? extends ComponentExchangeCategory>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        getInExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        getOutExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG:
          setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG:
          setOwnedInterfacePkg((InterfacePkg)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG:
          setOwnedDataPkg((DataPkg)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        getOwnedStateMachines().addAll((Collection<? extends StateMachine>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT:
          setAbstract((Boolean)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
        getOwnedGeneralizations().addAll((Collection<? extends Generalization>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES:
        getOwnedFeatures().clear();
        getOwnedFeatures().addAll((Collection<? extends Feature>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
        getOwnedInterfaceAllocations().clear();
        getOwnedInterfaceAllocations().addAll((Collection<? extends InterfaceAllocation>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS:
        getOwnedCommunicationLinks().clear();
        getOwnedCommunicationLinks().addAll((Collection<? extends CommunicationLink>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__ACTOR:
          setActor((Boolean)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__HUMAN:
          setHuman((Boolean)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES:
        getOwnedInterfaceUses().clear();
        getOwnedInterfaceUses().addAll((Collection<? extends InterfaceUse>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
        getOwnedInterfaceImplementations().clear();
        getOwnedInterfaceImplementations().addAll((Collection<? extends InterfaceImplementation>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS:
        getOwnedComponentRealizations().clear();
        getOwnedComponentRealizations().addAll((Collection<? extends ComponentRealization>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH:
        getOwnedPhysicalPath().clear();
        getOwnedPhysicalPath().addAll((Collection<? extends PhysicalPath>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS:
        getOwnedPhysicalLinks().clear();
        getOwnedPhysicalLinks().addAll((Collection<? extends PhysicalLink>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
        getOwnedPhysicalLinkCategories().clear();
        getOwnedPhysicalLinkCategories().addAll((Collection<? extends PhysicalLinkCategory>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__KIND:
          setKind((PhysicalComponentKind)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__NATURE:
          setNature((PhysicalComponentNature)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        getOwnedDeploymentLinks().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
        getOwnedPhysicalComponents().clear();
        getOwnedPhysicalComponents().addAll((Collection<? extends PhysicalComponent>)newValue);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
        getOwnedPhysicalComponentPkgs().clear();
        getOwnedPhysicalComponentPkgs().addAll((Collection<? extends PhysicalComponentPkg>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES:
        getNamingRules().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG:
        setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)null);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG:
        setOwnedInterfacePkg((InterfacePkg)null);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG:
        setOwnedDataPkg((DataPkg)null);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES:
        getOwnedFeatures().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
        getOwnedInterfaceAllocations().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS:
        getOwnedCommunicationLinks().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__ACTOR:
        setActor(ACTOR_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__HUMAN:
        setHuman(HUMAN_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES:
        getOwnedInterfaceUses().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
        getOwnedInterfaceImplementations().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS:
        getOwnedComponentRealizations().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH:
        getOwnedPhysicalPath().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS:
        getOwnedPhysicalLinks().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
        getOwnedPhysicalLinkCategories().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__NATURE:
        setNature(NATURE_EDEFAULT);
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
        getOwnedDeploymentLinks().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
        getOwnedPhysicalComponents().clear();
        return;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
        getOwnedPhysicalComponentPkgs().clear();
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case PaPackage.PHYSICAL_COMPONENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS:
        return !getAbstractTypedElements().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__TYPED_ELEMENTS:
        return !getTypedElements().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION:
        return ownedFunctionalAllocation != null && !ownedFunctionalAllocation.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES:
        return ownedComponentExchanges != null && !ownedComponentExchanges.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ownedComponentExchangeCategories != null && !ownedComponentExchangeCategories.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS:
        return !getFunctionalAllocations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS:
        return !getAllocatedFunctions().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS:
        return inExchangeLinks != null && !inExchangeLinks.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS:
        return outExchangeLinks != null && !outExchangeLinks.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG:
        return ownedAbstractCapabilityPkg != null;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG:
        return ownedInterfacePkg != null;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG:
        return ownedDataPkg != null;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES:
        return ownedStateMachines != null && !ownedStateMachines.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS:
        return ownedGeneralizations != null && !ownedGeneralizations.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS:
        return !getSuperGeneralizations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SUB_GENERALIZATIONS:
        return !getSubGeneralizations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SUPER:
        return !getSuper().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SUB:
        return !getSub().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES:
        return ownedFeatures != null && !ownedFeatures.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PROPERTIES:
        return !getContainedProperties().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS:
        return ownedInterfaceAllocations != null && !ownedInterfaceAllocations.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS:
        return !getProvisionedInterfaceAllocations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_INTERFACES:
        return !getAllocatedInterfaces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS:
        return ownedCommunicationLinks != null && !ownedCommunicationLinks.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__PRODUCE:
        return !getProduce().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONSUME:
        return !getConsume().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SEND:
        return !getSend().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__RECEIVE:
        return !getReceive().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CALL:
        return !getCall().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__EXECUTE:
        return !getExecute().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__WRITE:
        return !getWrite().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ACCESS:
        return !getAccess().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ACQUIRE:
        return !getAcquire().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__TRANSMIT:
        return !getTransmit().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ACTOR:
        return actor != ACTOR_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT__HUMAN:
        return human != HUMAN_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES:
        return ownedInterfaceUses != null && !ownedInterfaceUses.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACE_LINKS:
        return !getUsedInterfaceLinks().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACES:
        return !getUsedInterfaces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS:
        return ownedInterfaceImplementations != null && !ownedInterfaceImplementations.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS:
        return !getImplementedInterfaceLinks().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES:
        return !getImplementedInterfaces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS:
        return ownedComponentRealizations != null && !ownedComponentRealizations.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__REALIZED_COMPONENTS:
        return !getRealizedComponents().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__REALIZING_COMPONENTS:
        return !getRealizingComponents().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__PROVIDED_INTERFACES:
        return !getProvidedInterfaces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__REQUIRED_INTERFACES:
        return !getRequiredInterfaces().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS:
        return !getContainedComponentPorts().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PARTS:
        return !getContainedParts().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS:
        return !getContainedPhysicalPorts().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH:
        return ownedPhysicalPath != null && !ownedPhysicalPath.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS:
        return ownedPhysicalLinks != null && !ownedPhysicalLinks.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES:
        return ownedPhysicalLinkCategories != null && !ownedPhysicalLinkCategories.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__REPRESENTING_PARTS:
        return !getRepresentingParts().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS:
        return !getCapabilityRealizationInvolvements().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS:
        return !getInvolvingCapabilityRealizations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_LINKS:
        return !getDeployingLinks().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYMENT_LINKS:
        return !getDeploymentLinks().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__KIND:
        return kind != KIND_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT__NATURE:
        return nature != NATURE_EDEFAULT;
      case PaPackage.PHYSICAL_COMPONENT__OWNED_DEPLOYMENT_LINKS:
        return ownedDeploymentLinks != null && !ownedDeploymentLinks.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS:
        return ownedPhysicalComponents != null && !ownedPhysicalComponents.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS:
        return ownedPhysicalComponentPkgs != null && !ownedPhysicalComponentPkgs.isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__LOGICAL_INTERFACE_REALIZATIONS:
        return !getLogicalInterfaceRealizations().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__SUB_PHYSICAL_COMPONENTS:
        return !getSubPhysicalComponents().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__REALIZED_LOGICAL_COMPONENTS:
        return !getRealizedLogicalComponents().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_PHYSICAL_FUNCTIONS:
        return !getAllocatedPhysicalFunctions().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYED_PHYSICAL_COMPONENTS:
        return !getDeployedPhysicalComponents().isEmpty();
      case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_PHYSICAL_COMPONENTS:
        return !getDeployingPhysicalComponents().isEmpty();
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractNamedElement.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__NAME: return ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == AbstractType.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS: return ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == Namespace.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES: return CapellacorePackage.NAMESPACE__OWNED_TRACES;
        case PaPackage.PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES: return CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;
        case PaPackage.PHYSICAL_COMPONENT__NAMING_RULES: return CapellacorePackage.NAMESPACE__NAMING_RULES;
        default: return -1;
      }
    }
    if (baseClass == Type.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__TYPED_ELEMENTS: return CapellacorePackage.TYPE__TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == ModellingBlock.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractFunctionalBlock.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
        case PaPackage.PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS;
        case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS;
        case PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS;
        case PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS;
        default: return -1;
      }
    }
    if (baseClass == Block.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG: return CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG: return CsPackage.BLOCK__OWNED_INTERFACE_PKG;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG: return CsPackage.BLOCK__OWNED_DATA_PKG;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES: return CsPackage.BLOCK__OWNED_STATE_MACHINES;
        default: return -1;
      }
    }
    if (baseClass == GeneralizableElement.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__ABSTRACT: return CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS;
        case PaPackage.PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS;
        case PaPackage.PHYSICAL_COMPONENT__SUB_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS;
        case PaPackage.PHYSICAL_COMPONENT__SUPER: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER;
        case PaPackage.PHYSICAL_COMPONENT__SUB: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUB;
        default: return -1;
      }
    }
    if (baseClass == Classifier.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES: return CapellacorePackage.CLASSIFIER__OWNED_FEATURES;
        case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PROPERTIES: return CapellacorePackage.CLASSIFIER__CONTAINED_PROPERTIES;
        default: return -1;
      }
    }
    if (baseClass == InterfaceAllocator.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS;
        case PaPackage.PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS;
        case PaPackage.PHYSICAL_COMPONENT__ALLOCATED_INTERFACES: return CsPackage.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES;
        default: return -1;
      }
    }
    if (baseClass == CommunicationLinkExchanger.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS;
        case PaPackage.PHYSICAL_COMPONENT__PRODUCE: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__PRODUCE;
        case PaPackage.PHYSICAL_COMPONENT__CONSUME: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CONSUME;
        case PaPackage.PHYSICAL_COMPONENT__SEND: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__SEND;
        case PaPackage.PHYSICAL_COMPONENT__RECEIVE: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__RECEIVE;
        case PaPackage.PHYSICAL_COMPONENT__CALL: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CALL;
        case PaPackage.PHYSICAL_COMPONENT__EXECUTE: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__EXECUTE;
        case PaPackage.PHYSICAL_COMPONENT__WRITE: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__WRITE;
        case PaPackage.PHYSICAL_COMPONENT__ACCESS: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACCESS;
        case PaPackage.PHYSICAL_COMPONENT__ACQUIRE: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACQUIRE;
        case PaPackage.PHYSICAL_COMPONENT__TRANSMIT: return CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__TRANSMIT;
        default: return -1;
      }
    }
    if (baseClass == Component.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__ACTOR: return CsPackage.COMPONENT__ACTOR;
        case PaPackage.PHYSICAL_COMPONENT__HUMAN: return CsPackage.COMPONENT__HUMAN;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES: return CsPackage.COMPONENT__OWNED_INTERFACE_USES;
        case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACE_LINKS: return CsPackage.COMPONENT__USED_INTERFACE_LINKS;
        case PaPackage.PHYSICAL_COMPONENT__USED_INTERFACES: return CsPackage.COMPONENT__USED_INTERFACES;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS: return CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;
        case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS: return CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS;
        case PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES: return CsPackage.COMPONENT__IMPLEMENTED_INTERFACES;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS: return CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS;
        case PaPackage.PHYSICAL_COMPONENT__REALIZED_COMPONENTS: return CsPackage.COMPONENT__REALIZED_COMPONENTS;
        case PaPackage.PHYSICAL_COMPONENT__REALIZING_COMPONENTS: return CsPackage.COMPONENT__REALIZING_COMPONENTS;
        case PaPackage.PHYSICAL_COMPONENT__PROVIDED_INTERFACES: return CsPackage.COMPONENT__PROVIDED_INTERFACES;
        case PaPackage.PHYSICAL_COMPONENT__REQUIRED_INTERFACES: return CsPackage.COMPONENT__REQUIRED_INTERFACES;
        case PaPackage.PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS: return CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS;
        case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PARTS: return CsPackage.COMPONENT__CONTAINED_PARTS;
        case PaPackage.PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS: return CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH: return CsPackage.COMPONENT__OWNED_PHYSICAL_PATH;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS: return CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS;
        case PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES: return CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;
        case PaPackage.PHYSICAL_COMPONENT__REPRESENTING_PARTS: return CsPackage.COMPONENT__REPRESENTING_PARTS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == CapabilityRealizationInvolvedElement.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS;
        case PaPackage.PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS;
        default: return -1;
      }
    }
    if (baseClass == DeployableElement.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__DEPLOYING_LINKS: return CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS;
        default: return -1;
      }
    }
    if (baseClass == DeploymentTarget.class) {
      switch (derivedFeatureID) {
        case PaPackage.PHYSICAL_COMPONENT__DEPLOYMENT_LINKS: return CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractNamedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME: return PaPackage.PHYSICAL_COMPONENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == AbstractType.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS: return PaPackage.PHYSICAL_COMPONENT__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == NamedElement.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == Namespace.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.NAMESPACE__OWNED_TRACES: return PaPackage.PHYSICAL_COMPONENT__OWNED_TRACES;
        case CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES: return PaPackage.PHYSICAL_COMPONENT__CONTAINED_GENERIC_TRACES;
        case CapellacorePackage.NAMESPACE__NAMING_RULES: return PaPackage.PHYSICAL_COMPONENT__NAMING_RULES;
        default: return -1;
      }
    }
    if (baseClass == Type.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.TYPE__TYPED_ELEMENTS: return PaPackage.PHYSICAL_COMPONENT__TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == ModellingBlock.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractFunctionalBlock.class) {
      switch (baseFeatureID) {
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION: return PaPackage.PHYSICAL_COMPONENT__OWNED_FUNCTIONAL_ALLOCATION;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES: return PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGES;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES: return PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS: return PaPackage.PHYSICAL_COMPONENT__FUNCTIONAL_ALLOCATIONS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS: return PaPackage.PHYSICAL_COMPONENT__ALLOCATED_FUNCTIONS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS: return PaPackage.PHYSICAL_COMPONENT__IN_EXCHANGE_LINKS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS: return PaPackage.PHYSICAL_COMPONENT__OUT_EXCHANGE_LINKS;
        default: return -1;
      }
    }
    if (baseClass == Block.class) {
      switch (baseFeatureID) {
        case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG: return PaPackage.PHYSICAL_COMPONENT__OWNED_ABSTRACT_CAPABILITY_PKG;
        case CsPackage.BLOCK__OWNED_INTERFACE_PKG: return PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_PKG;
        case CsPackage.BLOCK__OWNED_DATA_PKG: return PaPackage.PHYSICAL_COMPONENT__OWNED_DATA_PKG;
        case CsPackage.BLOCK__OWNED_STATE_MACHINES: return PaPackage.PHYSICAL_COMPONENT__OWNED_STATE_MACHINES;
        default: return -1;
      }
    }
    if (baseClass == GeneralizableElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT: return PaPackage.PHYSICAL_COMPONENT__ABSTRACT;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS: return PaPackage.PHYSICAL_COMPONENT__OWNED_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS: return PaPackage.PHYSICAL_COMPONENT__SUPER_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS: return PaPackage.PHYSICAL_COMPONENT__SUB_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER: return PaPackage.PHYSICAL_COMPONENT__SUPER;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB: return PaPackage.PHYSICAL_COMPONENT__SUB;
        default: return -1;
      }
    }
    if (baseClass == Classifier.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.CLASSIFIER__OWNED_FEATURES: return PaPackage.PHYSICAL_COMPONENT__OWNED_FEATURES;
        case CapellacorePackage.CLASSIFIER__CONTAINED_PROPERTIES: return PaPackage.PHYSICAL_COMPONENT__CONTAINED_PROPERTIES;
        default: return -1;
      }
    }
    if (baseClass == InterfaceAllocator.class) {
      switch (baseFeatureID) {
        case CsPackage.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS: return PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS: return PaPackage.PHYSICAL_COMPONENT__PROVISIONED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES: return PaPackage.PHYSICAL_COMPONENT__ALLOCATED_INTERFACES;
        default: return -1;
      }
    }
    if (baseClass == CommunicationLinkExchanger.class) {
      switch (baseFeatureID) {
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS: return PaPackage.PHYSICAL_COMPONENT__OWNED_COMMUNICATION_LINKS;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__PRODUCE: return PaPackage.PHYSICAL_COMPONENT__PRODUCE;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CONSUME: return PaPackage.PHYSICAL_COMPONENT__CONSUME;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__SEND: return PaPackage.PHYSICAL_COMPONENT__SEND;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__RECEIVE: return PaPackage.PHYSICAL_COMPONENT__RECEIVE;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CALL: return PaPackage.PHYSICAL_COMPONENT__CALL;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__EXECUTE: return PaPackage.PHYSICAL_COMPONENT__EXECUTE;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__WRITE: return PaPackage.PHYSICAL_COMPONENT__WRITE;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACCESS: return PaPackage.PHYSICAL_COMPONENT__ACCESS;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACQUIRE: return PaPackage.PHYSICAL_COMPONENT__ACQUIRE;
        case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__TRANSMIT: return PaPackage.PHYSICAL_COMPONENT__TRANSMIT;
        default: return -1;
      }
    }
    if (baseClass == Component.class) {
      switch (baseFeatureID) {
        case CsPackage.COMPONENT__ACTOR: return PaPackage.PHYSICAL_COMPONENT__ACTOR;
        case CsPackage.COMPONENT__HUMAN: return PaPackage.PHYSICAL_COMPONENT__HUMAN;
        case CsPackage.COMPONENT__OWNED_INTERFACE_USES: return PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_USES;
        case CsPackage.COMPONENT__USED_INTERFACE_LINKS: return PaPackage.PHYSICAL_COMPONENT__USED_INTERFACE_LINKS;
        case CsPackage.COMPONENT__USED_INTERFACES: return PaPackage.PHYSICAL_COMPONENT__USED_INTERFACES;
        case CsPackage.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS: return PaPackage.PHYSICAL_COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS;
        case CsPackage.COMPONENT__IMPLEMENTED_INTERFACE_LINKS: return PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACE_LINKS;
        case CsPackage.COMPONENT__IMPLEMENTED_INTERFACES: return PaPackage.PHYSICAL_COMPONENT__IMPLEMENTED_INTERFACES;
        case CsPackage.COMPONENT__OWNED_COMPONENT_REALIZATIONS: return PaPackage.PHYSICAL_COMPONENT__OWNED_COMPONENT_REALIZATIONS;
        case CsPackage.COMPONENT__REALIZED_COMPONENTS: return PaPackage.PHYSICAL_COMPONENT__REALIZED_COMPONENTS;
        case CsPackage.COMPONENT__REALIZING_COMPONENTS: return PaPackage.PHYSICAL_COMPONENT__REALIZING_COMPONENTS;
        case CsPackage.COMPONENT__PROVIDED_INTERFACES: return PaPackage.PHYSICAL_COMPONENT__PROVIDED_INTERFACES;
        case CsPackage.COMPONENT__REQUIRED_INTERFACES: return PaPackage.PHYSICAL_COMPONENT__REQUIRED_INTERFACES;
        case CsPackage.COMPONENT__CONTAINED_COMPONENT_PORTS: return PaPackage.PHYSICAL_COMPONENT__CONTAINED_COMPONENT_PORTS;
        case CsPackage.COMPONENT__CONTAINED_PARTS: return PaPackage.PHYSICAL_COMPONENT__CONTAINED_PARTS;
        case CsPackage.COMPONENT__CONTAINED_PHYSICAL_PORTS: return PaPackage.PHYSICAL_COMPONENT__CONTAINED_PHYSICAL_PORTS;
        case CsPackage.COMPONENT__OWNED_PHYSICAL_PATH: return PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_PATH;
        case CsPackage.COMPONENT__OWNED_PHYSICAL_LINKS: return PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINKS;
        case CsPackage.COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES: return PaPackage.PHYSICAL_COMPONENT__OWNED_PHYSICAL_LINK_CATEGORIES;
        case CsPackage.COMPONENT__REPRESENTING_PARTS: return PaPackage.PHYSICAL_COMPONENT__REPRESENTING_PARTS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return PaPackage.PHYSICAL_COMPONENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == CapabilityRealizationInvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS: return PaPackage.PHYSICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS;
        case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS: return PaPackage.PHYSICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS;
        default: return -1;
      }
    }
    if (baseClass == DeployableElement.class) {
      switch (baseFeatureID) {
        case CsPackage.DEPLOYABLE_ELEMENT__DEPLOYING_LINKS: return PaPackage.PHYSICAL_COMPONENT__DEPLOYING_LINKS;
        default: return -1;
      }
    }
    if (baseClass == DeploymentTarget.class) {
      switch (baseFeatureID) {
        case CsPackage.DEPLOYMENT_TARGET__DEPLOYMENT_LINKS: return PaPackage.PHYSICAL_COMPONENT__DEPLOYMENT_LINKS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (name: "); //$NON-NLS-1$
    result.append(name);
    result.append(", abstract: "); //$NON-NLS-1$
    result.append(abstract_);
    result.append(", actor: "); //$NON-NLS-1$
    result.append(actor);
    result.append(", human: "); //$NON-NLS-1$
    result.append(human);
    result.append(", kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", nature: "); //$NON-NLS-1$
    result.append(nature);
    result.append(')');
    return result.toString();
  }


} //PhysicalComponentImpl