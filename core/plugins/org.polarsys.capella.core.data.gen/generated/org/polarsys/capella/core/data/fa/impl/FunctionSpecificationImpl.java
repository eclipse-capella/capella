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

package org.polarsys.capella.core.data.fa.impl;

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
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityGroup;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#isIsControlOperator <em>Is Control Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedParameterSet <em>Owned Parameter Set</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#isIsSingleExecution <em>Is Single Execution</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedNodes <em>Owned Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedEdges <em>Owned Edges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedGroups <em>Owned Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedStructuredNodes <em>Owned Structured Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getOwnedFunctionPorts <em>Owned Function Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.FunctionSpecificationImpl#getSubFunctionSpecifications <em>Sub Function Specifications</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionSpecificationImpl extends NamedElementImpl implements FunctionSpecification {

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
   * The default value of the '{@link #isIsControlOperator() <em>Is Control Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsControlOperator()
   * @generated
   * @ordered
   */
	protected static final boolean IS_CONTROL_OPERATOR_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsControlOperator() <em>Is Control Operator</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsControlOperator()
   * @generated
   * @ordered
   */
	protected boolean isControlOperator = IS_CONTROL_OPERATOR_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedParameterSet() <em>Owned Parameter Set</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedParameterSet()
   * @generated
   * @ordered
   */
	protected EList<AbstractParameterSet> ownedParameterSet;





	/**
   * The cached value of the '{@link #getOwnedParameter() <em>Owned Parameter</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedParameter()
   * @generated
   * @ordered
   */
	protected EList<AbstractParameter> ownedParameter;





	/**
   * The default value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsReadOnly()
   * @generated
   * @ordered
   */
	protected static final boolean IS_READ_ONLY_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsReadOnly()
   * @generated
   * @ordered
   */
	protected boolean isReadOnly = IS_READ_ONLY_EDEFAULT;





	/**
   * The default value of the '{@link #isIsSingleExecution() <em>Is Single Execution</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsSingleExecution()
   * @generated
   * @ordered
   */
	protected static final boolean IS_SINGLE_EXECUTION_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsSingleExecution() <em>Is Single Execution</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsSingleExecution()
   * @generated
   * @ordered
   */
	protected boolean isSingleExecution = IS_SINGLE_EXECUTION_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedNodes() <em>Owned Nodes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedNodes()
   * @generated
   * @ordered
   */
	protected EList<ActivityNode> ownedNodes;





	/**
   * The cached value of the '{@link #getOwnedEdges() <em>Owned Edges</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEdges()
   * @generated
   * @ordered
   */
	protected EList<ActivityEdge> ownedEdges;





	/**
   * The cached value of the '{@link #getOwnedGroups() <em>Owned Groups</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedGroups()
   * @generated
   * @ordered
   */
	protected EList<ActivityGroup> ownedGroups;









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
   * The cached value of the '{@link #getOwnedFunctionPorts() <em>Owned Function Ports</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionPorts()
   * @generated
   * @ordered
   */
	protected EList<FunctionPort> ownedFunctionPorts;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FunctionSpecificationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.FUNCTION_SPECIFICATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES);
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
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES);
    }
    return namingRules;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsControlOperator() {

    return isControlOperator;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsControlOperator(boolean newIsControlOperator) {

    boolean oldIsControlOperator = isControlOperator;
    isControlOperator = newIsControlOperator;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR, oldIsControlOperator, isControlOperator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameterSet> getOwnedParameterSet() {

    if (ownedParameterSet == null) {
      ownedParameterSet = new EObjectResolvingEList<AbstractParameterSet>(AbstractParameterSet.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET);
    }
    return ownedParameterSet;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameter> getOwnedParameter() {

    if (ownedParameter == null) {
      ownedParameter = new EObjectResolvingEList<AbstractParameter>(AbstractParameter.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER);
    }
    return ownedParameter;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsReadOnly() {

    return isReadOnly;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsReadOnly(boolean newIsReadOnly) {

    boolean oldIsReadOnly = isReadOnly;
    isReadOnly = newIsReadOnly;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY, oldIsReadOnly, isReadOnly));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsSingleExecution() {

    return isSingleExecution;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsSingleExecution(boolean newIsSingleExecution) {

    boolean oldIsSingleExecution = isSingleExecution;
    isSingleExecution = newIsSingleExecution;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION, oldIsSingleExecution, isSingleExecution));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityNode> getOwnedNodes() {

    if (ownedNodes == null) {
      ownedNodes = new EObjectContainmentEList.Resolving<ActivityNode>(ActivityNode.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES);
    }
    return ownedNodes;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityEdge> getOwnedEdges() {

    if (ownedEdges == null) {
      ownedEdges = new EObjectContainmentEList.Resolving<ActivityEdge>(ActivityEdge.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES);
    }
    return ownedEdges;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityGroup> getOwnedGroups() {

    if (ownedGroups == null) {
      ownedGroups = new EObjectContainmentEList.Resolving<ActivityGroup>(ActivityGroup.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS);
    }
    return ownedGroups;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StructuredActivityNode> getOwnedStructuredNodes() {


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
    EAnnotation annotation = ActivityPackage.Literals.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ActivityPackage.Literals.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<StructuredActivityNode> resultAsList = (Collection<StructuredActivityNode>) result;
    return new EcoreEList.UnmodifiableEList<StructuredActivityNode>(this, ActivityPackage.Literals.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES, resultAsList.size(), resultAsList.toArray());
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
      inExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, FaPackage.FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS);
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
      outExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, FaPackage.FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS);
    }
    return outExchangeLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionPort> getOwnedFunctionPorts() {

    if (ownedFunctionPorts == null) {
      ownedFunctionPorts = new EObjectContainmentEList.Resolving<FunctionPort>(FunctionPort.class, this, FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS);
    }
    return ownedFunctionPorts;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionSpecification> getSubFunctionSpecifications() {


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
    EAnnotation annotation = FaPackage.Literals.FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionSpecification> resultAsList = (Collection<FunctionSpecification>) result;
    return new EcoreEList.UnmodifiableEList<FunctionSpecification>(this, FaPackage.Literals.FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS, resultAsList.size(), resultAsList.toArray());
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
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES:
        return ((InternalEList<?>)getOwnedNodes()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES:
        return ((InternalEList<?>)getOwnedEdges()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS:
        return ((InternalEList<?>)getOwnedGroups()).basicRemove(otherEnd, msgs);
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS:
        return ((InternalEList<?>)getOwnedFunctionPorts()).basicRemove(otherEnd, msgs);
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
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES:
        return getOwnedTraces();
      case FaPackage.FUNCTION_SPECIFICATION__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES:
        return getNamingRules();
      case FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR:
        return isIsControlOperator();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET:
        return getOwnedParameterSet();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER:
        return getOwnedParameter();
      case FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY:
        return isIsReadOnly();
      case FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION:
        return isIsSingleExecution();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES:
        return getOwnedNodes();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES:
        return getOwnedEdges();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS:
        return getOwnedGroups();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_STRUCTURED_NODES:
        return getOwnedStructuredNodes();
      case FaPackage.FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS:
        return getInExchangeLinks();
      case FaPackage.FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS:
        return getOutExchangeLinks();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS:
        return getOwnedFunctionPorts();
      case FaPackage.FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS:
        return getSubFunctionSpecifications();
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
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR:
          setIsControlOperator((Boolean)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        getOwnedParameterSet().addAll((Collection<? extends AbstractParameterSet>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER:
        getOwnedParameter().clear();
        getOwnedParameter().addAll((Collection<? extends AbstractParameter>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY:
          setIsReadOnly((Boolean)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION:
          setIsSingleExecution((Boolean)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES:
        getOwnedNodes().clear();
        getOwnedNodes().addAll((Collection<? extends ActivityNode>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES:
        getOwnedEdges().clear();
        getOwnedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS:
        getOwnedGroups().clear();
        getOwnedGroups().addAll((Collection<? extends ActivityGroup>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        getInExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        getOutExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS:
        getOwnedFunctionPorts().clear();
        getOwnedFunctionPorts().addAll((Collection<? extends FunctionPort>)newValue);
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
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES:
        getNamingRules().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR:
        setIsControlOperator(IS_CONTROL_OPERATOR_EDEFAULT);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER:
        getOwnedParameter().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY:
        setIsReadOnly(IS_READ_ONLY_EDEFAULT);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION:
        setIsSingleExecution(IS_SINGLE_EXECUTION_EDEFAULT);
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES:
        getOwnedNodes().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES:
        getOwnedEdges().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS:
        getOwnedGroups().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        return;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS:
        getOwnedFunctionPorts().clear();
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
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR:
        return isControlOperator != IS_CONTROL_OPERATOR_EDEFAULT;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET:
        return ownedParameterSet != null && !ownedParameterSet.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER:
        return ownedParameter != null && !ownedParameter.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY:
        return isReadOnly != IS_READ_ONLY_EDEFAULT;
      case FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION:
        return isSingleExecution != IS_SINGLE_EXECUTION_EDEFAULT;
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES:
        return ownedNodes != null && !ownedNodes.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES:
        return ownedEdges != null && !ownedEdges.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS:
        return ownedGroups != null && !ownedGroups.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_STRUCTURED_NODES:
        return !getOwnedStructuredNodes().isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS:
        return inExchangeLinks != null && !inExchangeLinks.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS:
        return outExchangeLinks != null && !outExchangeLinks.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS:
        return ownedFunctionPorts != null && !ownedFunctionPorts.isEmpty();
      case FaPackage.FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS:
        return !getSubFunctionSpecifications().isEmpty();
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
    if (baseClass == AbstractBehavior.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR: return BehaviorPackage.ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET: return BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER: return BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER;
        default: return -1;
      }
    }
    if (baseClass == AbstractActivity.class) {
      switch (derivedFeatureID) {
        case FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY: return ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY;
        case FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION: return ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES: return ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES: return ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS: return ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS;
        case FaPackage.FUNCTION_SPECIFICATION__OWNED_STRUCTURED_NODES: return ActivityPackage.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES;
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
    if (baseClass == AbstractBehavior.class) {
      switch (baseFeatureID) {
        case BehaviorPackage.ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR: return FaPackage.FUNCTION_SPECIFICATION__IS_CONTROL_OPERATOR;
        case BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET: return FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER_SET;
        case BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER: return FaPackage.FUNCTION_SPECIFICATION__OWNED_PARAMETER;
        default: return -1;
      }
    }
    if (baseClass == AbstractActivity.class) {
      switch (baseFeatureID) {
        case ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY: return FaPackage.FUNCTION_SPECIFICATION__IS_READ_ONLY;
        case ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION: return FaPackage.FUNCTION_SPECIFICATION__IS_SINGLE_EXECUTION;
        case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES: return FaPackage.FUNCTION_SPECIFICATION__OWNED_NODES;
        case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES: return FaPackage.FUNCTION_SPECIFICATION__OWNED_EDGES;
        case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS: return FaPackage.FUNCTION_SPECIFICATION__OWNED_GROUPS;
        case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES: return FaPackage.FUNCTION_SPECIFICATION__OWNED_STRUCTURED_NODES;
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
    result.append(" (isControlOperator: "); //$NON-NLS-1$
    result.append(isControlOperator);
    result.append(", isReadOnly: "); //$NON-NLS-1$
    result.append(isReadOnly);
    result.append(", isSingleExecution: "); //$NON-NLS-1$
    result.append(isSingleExecution);
    result.append(')');
    return result.toString();
  }


} //FunctionSpecificationImpl