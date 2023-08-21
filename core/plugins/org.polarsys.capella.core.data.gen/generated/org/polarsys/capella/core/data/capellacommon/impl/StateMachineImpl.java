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

package org.polarsys.capella.core.data.capellacommon.impl;

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
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#isVisibleInDoc <em>Visible In Doc</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#isVisibleInLM <em>Visible In LM</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getReview <em>Review</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedPropertyValues <em>Owned Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getAppliedPropertyValues <em>Applied Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#isIsControlOperator <em>Is Control Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedParameterSet <em>Owned Parameter Set</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedParameter <em>Owned Parameter</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateMachineImpl#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateMachineImpl extends ModelElementImpl implements StateMachine {









	/**
   * The default value of the '{@link #isVisibleInDoc() <em>Visible In Doc</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isVisibleInDoc()
   * @generated
   * @ordered
   */
	protected static final boolean VISIBLE_IN_DOC_EDEFAULT = true;

	/**
   * The cached value of the '{@link #isVisibleInDoc() <em>Visible In Doc</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isVisibleInDoc()
   * @generated
   * @ordered
   */
	protected boolean visibleInDoc = VISIBLE_IN_DOC_EDEFAULT;





	/**
   * The default value of the '{@link #isVisibleInLM() <em>Visible In LM</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isVisibleInLM()
   * @generated
   * @ordered
   */
	protected static final boolean VISIBLE_IN_LM_EDEFAULT = true;

	/**
   * The cached value of the '{@link #isVisibleInLM() <em>Visible In LM</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isVisibleInLM()
   * @generated
   * @ordered
   */
	protected boolean visibleInLM = VISIBLE_IN_LM_EDEFAULT;





	/**
   * The default value of the '{@link #getSummary() <em>Summary</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSummary()
   * @generated
   * @ordered
   */
	protected static final String SUMMARY_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getSummary() <em>Summary</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSummary()
   * @generated
   * @ordered
   */
	protected String summary = SUMMARY_EDEFAULT;





	/**
   * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
	protected String description = DESCRIPTION_EDEFAULT;





	/**
   * The default value of the '{@link #getReview() <em>Review</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReview()
   * @generated
   * @ordered
   */
	protected static final String REVIEW_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getReview() <em>Review</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReview()
   * @generated
   * @ordered
   */
	protected String review = REVIEW_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedPropertyValues() <em>Owned Property Values</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPropertyValues()
   * @generated
   * @ordered
   */
	protected EList<AbstractPropertyValue> ownedPropertyValues;





	/**
   * The cached value of the '{@link #getOwnedEnumerationPropertyTypes() <em>Owned Enumeration Property Types</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEnumerationPropertyTypes()
   * @generated
   * @ordered
   */
	protected EList<EnumerationPropertyType> ownedEnumerationPropertyTypes;





	/**
   * The cached value of the '{@link #getAppliedPropertyValues() <em>Applied Property Values</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAppliedPropertyValues()
   * @generated
   * @ordered
   */
	protected EList<AbstractPropertyValue> appliedPropertyValues;





	/**
   * The cached value of the '{@link #getOwnedPropertyValueGroups() <em>Owned Property Value Groups</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPropertyValueGroups()
   * @generated
   * @ordered
   */
	protected EList<PropertyValueGroup> ownedPropertyValueGroups;





	/**
   * The cached value of the '{@link #getAppliedPropertyValueGroups() <em>Applied Property Value Groups</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAppliedPropertyValueGroups()
   * @generated
   * @ordered
   */
	protected EList<PropertyValueGroup> appliedPropertyValueGroups;





	/**
   * The cached value of the '{@link #getStatus() <em>Status</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getStatus()
   * @generated
   * @ordered
   */
	protected EnumerationPropertyLiteral status;





	/**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
	protected EList<EnumerationPropertyLiteral> features;









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
   * The cached value of the '{@link #getOwnedRegions() <em>Owned Regions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRegions()
   * @generated
   * @ordered
   */
	protected EList<Region> ownedRegions;




	/**
   * The cached value of the '{@link #getOwnedConnectionPoints() <em>Owned Connection Points</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConnectionPoints()
   * @generated
   * @ordered
   */
	protected EList<Pseudostate> ownedConnectionPoints;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StateMachineImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacommonPackage.Literals.STATE_MACHINE;
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

	public boolean isVisibleInDoc() {

    return visibleInDoc;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVisibleInDoc(boolean newVisibleInDoc) {

    boolean oldVisibleInDoc = visibleInDoc;
    visibleInDoc = newVisibleInDoc;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC, oldVisibleInDoc, visibleInDoc));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isVisibleInLM() {

    return visibleInLM;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVisibleInLM(boolean newVisibleInLM) {

    boolean oldVisibleInLM = visibleInLM;
    visibleInLM = newVisibleInLM;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM, oldVisibleInLM, visibleInLM));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getSummary() {

    return summary;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSummary(String newSummary) {

    String oldSummary = summary;
    summary = newSummary;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__SUMMARY, oldSummary, summary));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getDescription() {

    return description;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDescription(String newDescription) {

    String oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__DESCRIPTION, oldDescription, description));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getReview() {

    return review;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReview(String newReview) {

    String oldReview = review;
    review = newReview;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__REVIEW, oldReview, review));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPropertyValue> getOwnedPropertyValues() {

    if (ownedPropertyValues == null) {
      ownedPropertyValues = new EObjectContainmentEList.Resolving<AbstractPropertyValue>(AbstractPropertyValue.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES);
    }
    return ownedPropertyValues;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyType> getOwnedEnumerationPropertyTypes() {

    if (ownedEnumerationPropertyTypes == null) {
      ownedEnumerationPropertyTypes = new EObjectContainmentEList.Resolving<EnumerationPropertyType>(EnumerationPropertyType.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES);
    }
    return ownedEnumerationPropertyTypes;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPropertyValue> getAppliedPropertyValues() {

    if (appliedPropertyValues == null) {
      appliedPropertyValues = new EObjectResolvingEList<AbstractPropertyValue>(AbstractPropertyValue.class, this, CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUES);
    }
    return appliedPropertyValues;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PropertyValueGroup> getOwnedPropertyValueGroups() {

    if (ownedPropertyValueGroups == null) {
      ownedPropertyValueGroups = new EObjectContainmentEList.Resolving<PropertyValueGroup>(PropertyValueGroup.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS);
    }
    return ownedPropertyValueGroups;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PropertyValueGroup> getAppliedPropertyValueGroups() {

    if (appliedPropertyValueGroups == null) {
      appliedPropertyValueGroups = new EObjectResolvingEList<PropertyValueGroup>(PropertyValueGroup.class, this, CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS);
    }
    return appliedPropertyValueGroups;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EnumerationPropertyLiteral getStatus() {

    if (status != null && status.eIsProxy()) {
      InternalEObject oldStatus = (InternalEObject)status;
      status = (EnumerationPropertyLiteral)eResolveProxy(oldStatus);
      if (status != oldStatus) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_MACHINE__STATUS, oldStatus, status));
      }
    }
    return status;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EnumerationPropertyLiteral basicGetStatus() {

    return status;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setStatus(EnumerationPropertyLiteral newStatus) {

    EnumerationPropertyLiteral oldStatus = status;
    status = newStatus;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__STATUS, oldStatus, status));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyLiteral> getFeatures() {

    if (features == null) {
      features = new EObjectResolvingEList<EnumerationPropertyLiteral>(EnumerationPropertyLiteral.class, this, CapellacommonPackage.STATE_MACHINE__FEATURES);
    }
    return features;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__NAME, oldName, name));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR, oldIsControlOperator, isControlOperator));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameterSet> getOwnedParameterSet() {

    if (ownedParameterSet == null) {
      ownedParameterSet = new EObjectResolvingEList<AbstractParameterSet>(AbstractParameterSet.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET);
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
      ownedParameter = new EObjectResolvingEList<AbstractParameter>(AbstractParameter.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER);
    }
    return ownedParameter;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Region> getOwnedRegions() {

    if (ownedRegions == null) {
      ownedRegions = new EObjectContainmentEList.Resolving<Region>(Region.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS);
    }
    return ownedRegions;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Pseudostate> getOwnedConnectionPoints() {

    if (ownedConnectionPoints == null) {
      ownedConnectionPoints = new EObjectContainmentEList.Resolving<Pseudostate>(Pseudostate.class, this, CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS);
    }
    return ownedConnectionPoints;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES:
        return ((InternalEList<?>)getOwnedPropertyValues()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ((InternalEList<?>)getOwnedEnumerationPropertyTypes()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS:
        return ((InternalEList<?>)getOwnedPropertyValueGroups()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS:
        return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
        return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
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
      case CapellacommonPackage.STATE_MACHINE__INCOMING_TRACES:
        return getIncomingTraces();
      case CapellacommonPackage.STATE_MACHINE__OUTGOING_TRACES:
        return getOutgoingTraces();
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC:
        return isVisibleInDoc();
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM:
        return isVisibleInLM();
      case CapellacommonPackage.STATE_MACHINE__SUMMARY:
        return getSummary();
      case CapellacommonPackage.STATE_MACHINE__DESCRIPTION:
        return getDescription();
      case CapellacommonPackage.STATE_MACHINE__REVIEW:
        return getReview();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES:
        return getOwnedPropertyValues();
      case CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES:
        return getOwnedEnumerationPropertyTypes();
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUES:
        return getAppliedPropertyValues();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS:
        return getOwnedPropertyValueGroups();
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS:
        return getAppliedPropertyValueGroups();
      case CapellacommonPackage.STATE_MACHINE__STATUS:
        if (resolve) return getStatus();
        return basicGetStatus();
      case CapellacommonPackage.STATE_MACHINE__FEATURES:
        return getFeatures();
      case CapellacommonPackage.STATE_MACHINE__NAME:
        return getName();
      case CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR:
        return isIsControlOperator();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET:
        return getOwnedParameterSet();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER:
        return getOwnedParameter();
      case CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS:
        return getOwnedRegions();
      case CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
        return getOwnedConnectionPoints();
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
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC:
          setVisibleInDoc((Boolean)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM:
          setVisibleInLM((Boolean)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__SUMMARY:
          setSummary((String)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__DESCRIPTION:
          setDescription((String)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__REVIEW:
          setReview((String)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        getOwnedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        getOwnedEnumerationPropertyTypes().addAll((Collection<? extends EnumerationPropertyType>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        getAppliedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        getOwnedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        getAppliedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__STATUS:
          setStatus((EnumerationPropertyLiteral)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends EnumerationPropertyLiteral>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__NAME:
          setName((String)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR:
          setIsControlOperator((Boolean)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        getOwnedParameterSet().addAll((Collection<? extends AbstractParameterSet>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER:
        getOwnedParameter().clear();
        getOwnedParameter().addAll((Collection<? extends AbstractParameter>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS:
        getOwnedRegions().clear();
        getOwnedRegions().addAll((Collection<? extends Region>)newValue);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
        getOwnedConnectionPoints().clear();
        getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
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
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC:
        setVisibleInDoc(VISIBLE_IN_DOC_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM:
        setVisibleInLM(VISIBLE_IN_LM_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__SUMMARY:
        setSummary(SUMMARY_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__REVIEW:
        setReview(REVIEW_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__STATUS:
        setStatus((EnumerationPropertyLiteral)null);
        return;
      case CapellacommonPackage.STATE_MACHINE__FEATURES:
        getFeatures().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR:
        setIsControlOperator(IS_CONTROL_OPERATOR_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET:
        getOwnedParameterSet().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER:
        getOwnedParameter().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS:
        getOwnedRegions().clear();
        return;
      case CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
        getOwnedConnectionPoints().clear();
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
      case CapellacommonPackage.STATE_MACHINE__INCOMING_TRACES:
        return !getIncomingTraces().isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OUTGOING_TRACES:
        return !getOutgoingTraces().isEmpty();
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC:
        return visibleInDoc != VISIBLE_IN_DOC_EDEFAULT;
      case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM:
        return visibleInLM != VISIBLE_IN_LM_EDEFAULT;
      case CapellacommonPackage.STATE_MACHINE__SUMMARY:
        return SUMMARY_EDEFAULT == null ? summary != null : !SUMMARY_EDEFAULT.equals(summary);
      case CapellacommonPackage.STATE_MACHINE__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case CapellacommonPackage.STATE_MACHINE__REVIEW:
        return REVIEW_EDEFAULT == null ? review != null : !REVIEW_EDEFAULT.equals(review);
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUES:
        return ownedPropertyValues != null && !ownedPropertyValues.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ownedEnumerationPropertyTypes != null && !ownedEnumerationPropertyTypes.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUES:
        return appliedPropertyValues != null && !appliedPropertyValues.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PROPERTY_VALUE_GROUPS:
        return ownedPropertyValueGroups != null && !ownedPropertyValueGroups.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__APPLIED_PROPERTY_VALUE_GROUPS:
        return appliedPropertyValueGroups != null && !appliedPropertyValueGroups.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__STATUS:
        return status != null;
      case CapellacommonPackage.STATE_MACHINE__FEATURES:
        return features != null && !features.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR:
        return isControlOperator != IS_CONTROL_OPERATOR_EDEFAULT;
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET:
        return ownedParameterSet != null && !ownedParameterSet.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER:
        return ownedParameter != null && !ownedParameter.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OWNED_REGIONS:
        return ownedRegions != null && !ownedRegions.isEmpty();
      case CapellacommonPackage.STATE_MACHINE__OWNED_CONNECTION_POINTS:
        return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
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
    if (baseClass == PublishableElement.class) {
      switch (derivedFeatureID) {
        case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC;
        case CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == AbstractNamedElement.class) {
      switch (derivedFeatureID) {
        case CapellacommonPackage.STATE_MACHINE__NAME: return ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME;
        default: return -1;
      }
    }
    if (baseClass == AbstractBehavior.class) {
      switch (derivedFeatureID) {
        case CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR: return BehaviorPackage.ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR;
        case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET: return BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET;
        case CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER: return BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER;
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
    if (baseClass == PublishableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC: return CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_DOC;
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM: return CapellacommonPackage.STATE_MACHINE__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == AbstractNamedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT__NAME: return CapellacommonPackage.STATE_MACHINE__NAME;
        default: return -1;
      }
    }
    if (baseClass == AbstractBehavior.class) {
      switch (baseFeatureID) {
        case BehaviorPackage.ABSTRACT_BEHAVIOR__IS_CONTROL_OPERATOR: return CapellacommonPackage.STATE_MACHINE__IS_CONTROL_OPERATOR;
        case BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER_SET: return CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER_SET;
        case BehaviorPackage.ABSTRACT_BEHAVIOR__OWNED_PARAMETER: return CapellacommonPackage.STATE_MACHINE__OWNED_PARAMETER;
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
    result.append(" (visibleInDoc: "); //$NON-NLS-1$
    result.append(visibleInDoc);
    result.append(", visibleInLM: "); //$NON-NLS-1$
    result.append(visibleInLM);
    result.append(", summary: "); //$NON-NLS-1$
    result.append(summary);
    result.append(", review: "); //$NON-NLS-1$
    result.append(review);
    result.append(", name: "); //$NON-NLS-1$
    result.append(name);
    result.append(", isControlOperator: "); //$NON-NLS-1$
    result.append(isControlOperator);
    result.append(')');
    return result.toString();
  }


} //StateMachineImpl
