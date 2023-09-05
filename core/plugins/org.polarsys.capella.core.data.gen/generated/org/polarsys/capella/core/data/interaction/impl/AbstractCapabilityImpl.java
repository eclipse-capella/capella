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

package org.polarsys.capella.core.data.interaction.impl;

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
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getInvolvedInvolvements <em>Involved Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedFunctionalChains <em>Owned Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedScenarios <em>Owned Scenarios</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getIncomingCapabilityAllocation <em>Incoming Capability Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOutgoingCapabilityAllocation <em>Outgoing Capability Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getExtending <em>Extending</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getAbstractCapabilityExtensionPoints <em>Abstract Capability Extension Points</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getIncludes <em>Includes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getIncluding <em>Including</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getIncludedAbstractCapabilities <em>Included Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getIncludingAbstractCapabilities <em>Including Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getExtendedAbstractCapabilities <em>Extended Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getExtendingAbstractCapabilities <em>Extending Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedFunctionalChainAbstractCapabilityInvolvements <em>Owned Functional Chain Abstract Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedAbstractFunctionAbstractCapabilityInvolvements <em>Owned Abstract Function Abstract Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getAvailableInStates <em>Available In States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getOwnedAbstractCapabilityRealizations <em>Owned Abstract Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getInvolvedAbstractFunctions <em>Involved Abstract Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl#getInvolvedFunctionalChains <em>Involved Functional Chains</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractCapabilityImpl extends NamedElementImpl implements AbstractCapability {

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
   * The cached value of the '{@link #getOwnedPropertyValuePkgs() <em>Owned Property Value Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPropertyValuePkgs()
   * @generated
   * @ordered
   */
	protected EList<PropertyValuePkg> ownedPropertyValuePkgs;









	/**
   * The cached value of the '{@link #getOwnedFunctionalChains() <em>Owned Functional Chains</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalChains()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChain> ownedFunctionalChains;





	/**
   * The cached value of the '{@link #getPreCondition() <em>Pre Condition</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPreCondition()
   * @generated
   * @ordered
   */
	protected Constraint preCondition;





	/**
   * The cached value of the '{@link #getPostCondition() <em>Post Condition</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPostCondition()
   * @generated
   * @ordered
   */
	protected Constraint postCondition;





	/**
   * The cached value of the '{@link #getOwnedScenarios() <em>Owned Scenarios</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedScenarios()
   * @generated
   * @ordered
   */
	protected EList<Scenario> ownedScenarios;













	/**
   * The cached value of the '{@link #getExtends() <em>Extends</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExtends()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityExtend> extends_;





	/**
   * The cached value of the '{@link #getAbstractCapabilityExtensionPoints() <em>Abstract Capability Extension Points</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAbstractCapabilityExtensionPoints()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityExtensionPoint> abstractCapabilityExtensionPoints;





	/**
   * The cached value of the '{@link #getSuperGeneralizations() <em>Super Generalizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSuperGeneralizations()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityGeneralization> superGeneralizations;





	/**
   * The cached value of the '{@link #getIncludes() <em>Includes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIncludes()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityInclude> includes;





	/**
   * The cached value of the '{@link #getOwnedFunctionalChainAbstractCapabilityInvolvements() <em>Owned Functional Chain Abstract Capability Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalChainAbstractCapabilityInvolvements()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainAbstractCapabilityInvolvement> ownedFunctionalChainAbstractCapabilityInvolvements;





	/**
   * The cached value of the '{@link #getOwnedAbstractFunctionAbstractCapabilityInvolvements() <em>Owned Abstract Function Abstract Capability Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAbstractFunctionAbstractCapabilityInvolvements()
   * @generated
   * @ordered
   */
	protected EList<AbstractFunctionAbstractCapabilityInvolvement> ownedAbstractFunctionAbstractCapabilityInvolvements;





	/**
   * The cached value of the '{@link #getAvailableInStates() <em>Available In States</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAvailableInStates()
   * @generated
   * @ordered
   */
	protected EList<State> availableInStates;





	/**
   * The cached value of the '{@link #getOwnedAbstractCapabilityRealizations() <em>Owned Abstract Capability Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAbstractCapabilityRealizations()
   * @generated
   * @ordered
   */
	protected EList<AbstractCapabilityRealization> ownedAbstractCapabilityRealizations;












	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractCapabilityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES);
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
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES);
    }
    return namingRules;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PropertyValuePkg> getOwnedPropertyValuePkgs() {

    if (ownedPropertyValuePkgs == null) {
      ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS);
    }
    return ownedPropertyValuePkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Involvement> getInvolvedInvolvements() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Involvement> resultAsList = (Collection<Involvement>) result;
    return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChain> getOwnedFunctionalChains() {

    if (ownedFunctionalChains == null) {
      ownedFunctionalChains = new EObjectContainmentEList<FunctionalChain>(FunctionalChain.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS);
    }
    return ownedFunctionalChains;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getPreCondition() {

    if (preCondition != null && preCondition.eIsProxy()) {
      InternalEObject oldPreCondition = (InternalEObject)preCondition;
      preCondition = (Constraint)eResolveProxy(oldPreCondition);
      if (preCondition != oldPreCondition) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION, oldPreCondition, preCondition));
      }
    }
    return preCondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetPreCondition() {

    return preCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPreCondition(Constraint newPreCondition) {

    Constraint oldPreCondition = preCondition;
    preCondition = newPreCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION, oldPreCondition, preCondition));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getPostCondition() {

    if (postCondition != null && postCondition.eIsProxy()) {
      InternalEObject oldPostCondition = (InternalEObject)postCondition;
      postCondition = (Constraint)eResolveProxy(oldPostCondition);
      if (postCondition != oldPostCondition) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION, oldPostCondition, postCondition));
      }
    }
    return postCondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetPostCondition() {

    return postCondition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPostCondition(Constraint newPostCondition) {

    Constraint oldPostCondition = postCondition;
    postCondition = newPostCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION, oldPostCondition, postCondition));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Scenario> getOwnedScenarios() {

    if (ownedScenarios == null) {
      ownedScenarios = new EObjectContainmentEList<Scenario>(Scenario.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS);
    }
    return ownedScenarios;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityRealization> getIncomingCapabilityAllocation() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapabilityRealization> resultAsList = (Collection<AbstractCapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapabilityRealization>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapabilityRealization> getOutgoingCapabilityAllocation() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapabilityRealization> resultAsList = (Collection<AbstractCapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapabilityRealization>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapabilityExtend> getExtends() {

    if (extends_ == null) {
      extends_ = new EObjectContainmentEList<AbstractCapabilityExtend>(AbstractCapabilityExtend.class, this, InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS);
    }
    return extends_;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityExtend> getExtending() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapabilityExtend> resultAsList = (Collection<AbstractCapabilityExtend>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapabilityExtend>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapabilityExtensionPoint> getAbstractCapabilityExtensionPoints() {

    if (abstractCapabilityExtensionPoints == null) {
      abstractCapabilityExtensionPoints = new EObjectContainmentEList<AbstractCapabilityExtensionPoint>(AbstractCapabilityExtensionPoint.class, this, InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS);
    }
    return abstractCapabilityExtensionPoints;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityGeneralization> getSuperGeneralizations() {

    if (superGeneralizations == null) {
      superGeneralizations = new EObjectContainmentEList<AbstractCapabilityGeneralization>(AbstractCapabilityGeneralization.class, this, InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS);
    }
    return superGeneralizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityGeneralization> getSubGeneralizations() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapabilityGeneralization> resultAsList = (Collection<AbstractCapabilityGeneralization>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapabilityGeneralization>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapabilityInclude> getIncludes() {

    if (includes == null) {
      includes = new EObjectContainmentEList<AbstractCapabilityInclude>(AbstractCapabilityInclude.class, this, InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES);
    }
    return includes;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityInclude> getIncluding() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapabilityInclude> resultAsList = (Collection<AbstractCapabilityInclude>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapabilityInclude>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getSuper() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUPER, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getSub() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__SUB, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getIncludedAbstractCapabilities() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getIncludingAbstractCapabilities() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getExtendedAbstractCapabilities() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getExtendingAbstractCapabilities() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChainAbstractCapabilityInvolvement> getOwnedFunctionalChainAbstractCapabilityInvolvements() {

    if (ownedFunctionalChainAbstractCapabilityInvolvements == null) {
      ownedFunctionalChainAbstractCapabilityInvolvements = new EObjectContainmentEList.Resolving<FunctionalChainAbstractCapabilityInvolvement>(FunctionalChainAbstractCapabilityInvolvement.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS);
    }
    return ownedFunctionalChainAbstractCapabilityInvolvements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractFunctionAbstractCapabilityInvolvement> getOwnedAbstractFunctionAbstractCapabilityInvolvements() {

    if (ownedAbstractFunctionAbstractCapabilityInvolvements == null) {
      ownedAbstractFunctionAbstractCapabilityInvolvements = new EObjectContainmentEList.Resolving<AbstractFunctionAbstractCapabilityInvolvement>(AbstractFunctionAbstractCapabilityInvolvement.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS);
    }
    return ownedAbstractFunctionAbstractCapabilityInvolvements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<State> getAvailableInStates() {

    if (availableInStates == null) {
      availableInStates = new EObjectResolvingEList<State>(State.class, this, InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
    }
    return availableInStates;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractCapabilityRealization> getOwnedAbstractCapabilityRealizations() {

    if (ownedAbstractCapabilityRealizations == null) {
      ownedAbstractCapabilityRealizations = new EObjectContainmentEList.Resolving<AbstractCapabilityRealization>(AbstractCapabilityRealization.class, this, InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS);
    }
    return ownedAbstractCapabilityRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractFunction> getInvolvedAbstractFunctions() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChain> getInvolvedFunctionalChains() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChain> resultAsList = (Collection<FunctionalChain>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChain>(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS, resultAsList.size(), resultAsList.toArray());
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
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS:
        return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS:
        return ((InternalEList<?>)getOwnedFunctionalChains()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS:
        return ((InternalEList<?>)getOwnedScenarios()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS:
        return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS:
        return ((InternalEList<?>)getAbstractCapabilityExtensionPoints()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS:
        return ((InternalEList<?>)getSuperGeneralizations()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES:
        return ((InternalEList<?>)getIncludes()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedFunctionalChainAbstractCapabilityInvolvements()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedAbstractFunctionAbstractCapabilityInvolvements()).basicRemove(otherEnd, msgs);
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS:
        return ((InternalEList<?>)getOwnedAbstractCapabilityRealizations()).basicRemove(otherEnd, msgs);
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
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES:
        return getOwnedTraces();
      case InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES:
        return getNamingRules();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS:
        return getOwnedPropertyValuePkgs();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS:
        return getInvolvedInvolvements();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS:
        return getOwnedFunctionalChains();
      case InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION:
        if (resolve) return getPreCondition();
        return basicGetPreCondition();
      case InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION:
        if (resolve) return getPostCondition();
        return basicGetPostCondition();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS:
        return getOwnedScenarios();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION:
        return getIncomingCapabilityAllocation();
      case InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION:
        return getOutgoingCapabilityAllocation();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS:
        return getExtends();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING:
        return getExtending();
      case InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS:
        return getAbstractCapabilityExtensionPoints();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS:
        return getSuperGeneralizations();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS:
        return getSubGeneralizations();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES:
        return getIncludes();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING:
        return getIncluding();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER:
        return getSuper();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUB:
        return getSub();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES:
        return getIncludedAbstractCapabilities();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES:
        return getIncludingAbstractCapabilities();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES:
        return getExtendedAbstractCapabilities();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES:
        return getExtendingAbstractCapabilities();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return getOwnedFunctionalChainAbstractCapabilityInvolvements();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return getOwnedAbstractFunctionAbstractCapabilityInvolvements();
      case InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES:
        return getAvailableInStates();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS:
        return getOwnedAbstractCapabilityRealizations();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS:
        return getInvolvedAbstractFunctions();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS:
        return getInvolvedFunctionalChains();
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
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        getOwnedPropertyValuePkgs().addAll((Collection<? extends PropertyValuePkg>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS:
        getOwnedFunctionalChains().clear();
        getOwnedFunctionalChains().addAll((Collection<? extends FunctionalChain>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION:
          setPreCondition((Constraint)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION:
          setPostCondition((Constraint)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS:
        getOwnedScenarios().clear();
        getOwnedScenarios().addAll((Collection<? extends Scenario>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS:
        getExtends().clear();
        getExtends().addAll((Collection<? extends AbstractCapabilityExtend>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS:
        getAbstractCapabilityExtensionPoints().clear();
        getAbstractCapabilityExtensionPoints().addAll((Collection<? extends AbstractCapabilityExtensionPoint>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS:
        getSuperGeneralizations().clear();
        getSuperGeneralizations().addAll((Collection<? extends AbstractCapabilityGeneralization>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES:
        getIncludes().clear();
        getIncludes().addAll((Collection<? extends AbstractCapabilityInclude>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        getOwnedFunctionalChainAbstractCapabilityInvolvements().clear();
        getOwnedFunctionalChainAbstractCapabilityInvolvements().addAll((Collection<? extends FunctionalChainAbstractCapabilityInvolvement>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        getOwnedAbstractFunctionAbstractCapabilityInvolvements().clear();
        getOwnedAbstractFunctionAbstractCapabilityInvolvements().addAll((Collection<? extends AbstractFunctionAbstractCapabilityInvolvement>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES:
        getAvailableInStates().clear();
        getAvailableInStates().addAll((Collection<? extends State>)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS:
        getOwnedAbstractCapabilityRealizations().clear();
        getOwnedAbstractCapabilityRealizations().addAll((Collection<? extends AbstractCapabilityRealization>)newValue);
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
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES:
        getNamingRules().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS:
        getOwnedFunctionalChains().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION:
        setPreCondition((Constraint)null);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION:
        setPostCondition((Constraint)null);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS:
        getOwnedScenarios().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS:
        getExtends().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS:
        getAbstractCapabilityExtensionPoints().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS:
        getSuperGeneralizations().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES:
        getIncludes().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        getOwnedFunctionalChainAbstractCapabilityInvolvements().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        getOwnedAbstractFunctionAbstractCapabilityInvolvements().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES:
        getAvailableInStates().clear();
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS:
        getOwnedAbstractCapabilityRealizations().clear();
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
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_PROPERTY_VALUE_PKGS:
        return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS:
        return !getInvolvedInvolvements().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS:
        return ownedFunctionalChains != null && !ownedFunctionalChains.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__PRE_CONDITION:
        return preCondition != null;
      case InteractionPackage.ABSTRACT_CAPABILITY__POST_CONDITION:
        return postCondition != null;
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_SCENARIOS:
        return ownedScenarios != null && !ownedScenarios.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION:
        return !getIncomingCapabilityAllocation().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION:
        return !getOutgoingCapabilityAllocation().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDS:
        return extends_ != null && !extends_.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING:
        return !getExtending().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS:
        return abstractCapabilityExtensionPoints != null && !abstractCapabilityExtensionPoints.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS:
        return superGeneralizations != null && !superGeneralizations.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS:
        return !getSubGeneralizations().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDES:
        return includes != null && !includes.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING:
        return !getIncluding().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUPER:
        return !getSuper().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__SUB:
        return !getSub().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES:
        return !getIncludedAbstractCapabilities().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES:
        return !getIncludingAbstractCapabilities().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES:
        return !getExtendedAbstractCapabilities().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES:
        return !getExtendingAbstractCapabilities().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return ownedFunctionalChainAbstractCapabilityInvolvements != null && !ownedFunctionalChainAbstractCapabilityInvolvements.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS:
        return ownedAbstractFunctionAbstractCapabilityInvolvements != null && !ownedAbstractFunctionAbstractCapabilityInvolvements.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES:
        return availableInStates != null && !availableInStates.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS:
        return ownedAbstractCapabilityRealizations != null && !ownedAbstractCapabilityRealizations.isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS:
        return !getInvolvedAbstractFunctions().isEmpty();
      case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS:
        return !getInvolvedFunctionalChains().isEmpty();
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
    if (baseClass == InvolverElement.class) {
      switch (derivedFeatureID) {
        case InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS: return CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractFunctionalChainContainer.class) {
      switch (derivedFeatureID) {
        case InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS: return FaPackage.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS;
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
    if (baseClass == InvolverElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS: return InteractionPackage.ABSTRACT_CAPABILITY__INVOLVED_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractFunctionalChainContainer.class) {
      switch (baseFeatureID) {
        case FaPackage.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS: return InteractionPackage.ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAINS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //AbstractCapabilityImpl
