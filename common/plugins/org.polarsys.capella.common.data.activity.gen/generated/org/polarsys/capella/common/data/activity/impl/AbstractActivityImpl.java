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

package org.polarsys.capella.common.data.activity.impl;

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
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.data.modellingcore.impl.AbstractNamedElementImpl;
import org.polarsys.capella.common.model.helpers.IHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#isIsControlOperator <em>Is Control Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedParameterSet <em>Owned Parameter Set</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#isIsSingleExecution <em>Is Single Execution</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedNodes <em>Owned Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedEdges <em>Owned Edges</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedGroups <em>Owned Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActivityImpl#getOwnedStructuredNodes <em>Owned Structured Nodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractActivityImpl extends AbstractNamedElementImpl implements AbstractActivity {

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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractActivityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.ABSTRACT_ACTIVITY;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTIVITY__IS_CONTROL_OPERATOR, oldIsControlOperator, isControlOperator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameterSet> getOwnedParameterSet() {

    if (ownedParameterSet == null) {
      ownedParameterSet = new EObjectResolvingEList<AbstractParameterSet>(AbstractParameterSet.class, this, ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER_SET);
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
      ownedParameter = new EObjectResolvingEList<AbstractParameter>(AbstractParameter.class, this, ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER);
    }
    return ownedParameter;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractTrace> getIncomingTraces() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractTrace> resultAsList = (Collection<AbstractTrace>) result;
    return new EcoreEList.UnmodifiableEList<AbstractTrace>(this, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractTrace> getOutgoingTraces() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractTrace> resultAsList = (Collection<AbstractTrace>) result;
    return new EcoreEList.UnmodifiableEList<AbstractTrace>(this, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES, resultAsList.size(), resultAsList.toArray());
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
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY, oldIsReadOnly, isReadOnly));

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
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION, oldIsSingleExecution, isSingleExecution));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ActivityNode> getOwnedNodes() {

    if (ownedNodes == null) {
      ownedNodes = new EObjectContainmentEList.Resolving<ActivityNode>(ActivityNode.class, this, ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES);
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
      ownedEdges = new EObjectContainmentEList.Resolving<ActivityEdge>(ActivityEdge.class, this, ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES);
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
      ownedGroups = new EObjectContainmentEList.Resolving<ActivityGroup>(ActivityGroup.class, this, ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS);
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES:
        return ((InternalEList<?>)getOwnedNodes()).basicRemove(otherEnd, msgs);
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES:
        return ((InternalEList<?>)getOwnedEdges()).basicRemove(otherEnd, msgs);
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS:
        return ((InternalEList<?>)getOwnedGroups()).basicRemove(otherEnd, msgs);
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
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_CONTROL_OPERATOR:
        return isIsControlOperator();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER_SET:
        return getOwnedParameterSet();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER:
        return getOwnedParameter();
      case ActivityPackage.ABSTRACT_ACTIVITY__INCOMING_TRACES:
        return getIncomingTraces();
      case ActivityPackage.ABSTRACT_ACTIVITY__OUTGOING_TRACES:
        return getOutgoingTraces();
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY:
        return isIsReadOnly();
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION:
        return isIsSingleExecution();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES:
        return getOwnedNodes();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES:
        return getOwnedEdges();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS:
        return getOwnedGroups();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES:
        return getOwnedStructuredNodes();
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
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_CONTROL_OPERATOR:
          setIsControlOperator((Boolean)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        getOwnedParameterSet().addAll((Collection<? extends AbstractParameterSet>)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER:
        getOwnedParameter().clear();
        getOwnedParameter().addAll((Collection<? extends AbstractParameter>)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY:
          setIsReadOnly((Boolean)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION:
          setIsSingleExecution((Boolean)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES:
        getOwnedNodes().clear();
        getOwnedNodes().addAll((Collection<? extends ActivityNode>)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES:
        getOwnedEdges().clear();
        getOwnedEdges().addAll((Collection<? extends ActivityEdge>)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS:
        getOwnedGroups().clear();
        getOwnedGroups().addAll((Collection<? extends ActivityGroup>)newValue);
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
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_CONTROL_OPERATOR:
        setIsControlOperator(IS_CONTROL_OPERATOR_EDEFAULT);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER:
        getOwnedParameter().clear();
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY:
        setIsReadOnly(IS_READ_ONLY_EDEFAULT);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION:
        setIsSingleExecution(IS_SINGLE_EXECUTION_EDEFAULT);
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES:
        getOwnedNodes().clear();
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES:
        getOwnedEdges().clear();
        return;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS:
        getOwnedGroups().clear();
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
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_CONTROL_OPERATOR:
        return isControlOperator != IS_CONTROL_OPERATOR_EDEFAULT;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER_SET:
        return ownedParameterSet != null && !ownedParameterSet.isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_PARAMETER:
        return ownedParameter != null && !ownedParameter.isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__INCOMING_TRACES:
        return !getIncomingTraces().isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__OUTGOING_TRACES:
        return !getOutgoingTraces().isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_READ_ONLY:
        return isReadOnly != IS_READ_ONLY_EDEFAULT;
      case ActivityPackage.ABSTRACT_ACTIVITY__IS_SINGLE_EXECUTION:
        return isSingleExecution != IS_SINGLE_EXECUTION_EDEFAULT;
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_NODES:
        return ownedNodes != null && !ownedNodes.isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_EDGES:
        return ownedEdges != null && !ownedEdges.isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_GROUPS:
        return ownedGroups != null && !ownedGroups.isEmpty();
      case ActivityPackage.ABSTRACT_ACTIVITY__OWNED_STRUCTURED_NODES:
        return !getOwnedStructuredNodes().isEmpty();
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
    if (baseClass == TraceableElement.class) {
      switch (derivedFeatureID) {
        case ActivityPackage.ABSTRACT_ACTIVITY__INCOMING_TRACES: return ModellingcorePackage.TRACEABLE_ELEMENT__INCOMING_TRACES;
        case ActivityPackage.ABSTRACT_ACTIVITY__OUTGOING_TRACES: return ModellingcorePackage.TRACEABLE_ELEMENT__OUTGOING_TRACES;
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
    if (baseClass == TraceableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.TRACEABLE_ELEMENT__INCOMING_TRACES: return ActivityPackage.ABSTRACT_ACTIVITY__INCOMING_TRACES;
        case ModellingcorePackage.TRACEABLE_ELEMENT__OUTGOING_TRACES: return ActivityPackage.ABSTRACT_ACTIVITY__OUTGOING_TRACES;
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


} //AbstractActivityImpl