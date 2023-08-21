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

package org.polarsys.capella.core.data.cs.impl;

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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.CsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Path Involved Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#isVisibleInDoc <em>Visible In Doc</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#isVisibleInLM <em>Visible In LM</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getReview <em>Review</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getOwnedPropertyValues <em>Owned Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getAppliedPropertyValues <em>Applied Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.AbstractPathInvolvedElementImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractPathInvolvedElementImpl extends ModelElementImpl implements AbstractPathInvolvedElement {









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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractPathInvolvedElementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.ABSTRACT_PATH_INVOLVED_ELEMENT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC, oldVisibleInDoc, visibleInDoc));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM, oldVisibleInLM, visibleInLM));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY, oldSummary, summary));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION, oldDescription, description));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW, oldReview, review));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPropertyValue> getOwnedPropertyValues() {

    if (ownedPropertyValues == null) {
      ownedPropertyValues = new EObjectContainmentEList.Resolving<AbstractPropertyValue>(AbstractPropertyValue.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES);
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
      ownedEnumerationPropertyTypes = new EObjectContainmentEList.Resolving<EnumerationPropertyType>(EnumerationPropertyType.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES);
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
      appliedPropertyValues = new EObjectResolvingEList<AbstractPropertyValue>(AbstractPropertyValue.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES);
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
      ownedPropertyValueGroups = new EObjectContainmentEList.Resolving<PropertyValueGroup>(PropertyValueGroup.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS);
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
      appliedPropertyValueGroups = new EObjectResolvingEList<PropertyValueGroup>(PropertyValueGroup.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS);
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS, oldStatus, status));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS, oldStatus, status));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyLiteral> getFeatures() {

    if (features == null) {
      features = new EObjectResolvingEList<EnumerationPropertyLiteral>(EnumerationPropertyLiteral.class, this, CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES);
    }
    return features;
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES:
        return ((InternalEList<?>)getOwnedPropertyValues()).basicRemove(otherEnd, msgs);
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ((InternalEList<?>)getOwnedEnumerationPropertyTypes()).basicRemove(otherEnd, msgs);
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
        return ((InternalEList<?>)getOwnedPropertyValueGroups()).basicRemove(otherEnd, msgs);
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
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__INCOMING_TRACES:
        return getIncomingTraces();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OUTGOING_TRACES:
        return getOutgoingTraces();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC:
        return isVisibleInDoc();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM:
        return isVisibleInLM();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY:
        return getSummary();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION:
        return getDescription();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW:
        return getReview();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES:
        return getOwnedPropertyValues();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
        return getOwnedEnumerationPropertyTypes();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES:
        return getAppliedPropertyValues();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
        return getOwnedPropertyValueGroups();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS:
        return getAppliedPropertyValueGroups();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS:
        if (resolve) return getStatus();
        return basicGetStatus();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES:
        return getFeatures();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
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
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC:
          setVisibleInDoc((Boolean)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM:
          setVisibleInLM((Boolean)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY:
          setSummary((String)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION:
          setDescription((String)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW:
          setReview((String)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        getOwnedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        getOwnedEnumerationPropertyTypes().addAll((Collection<? extends EnumerationPropertyType>)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        getAppliedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        getOwnedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        getAppliedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS:
          setStatus((EnumerationPropertyLiteral)newValue);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends EnumerationPropertyLiteral>)newValue);
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
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC:
        setVisibleInDoc(VISIBLE_IN_DOC_EDEFAULT);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM:
        setVisibleInLM(VISIBLE_IN_LM_EDEFAULT);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY:
        setSummary(SUMMARY_EDEFAULT);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW:
        setReview(REVIEW_EDEFAULT);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS:
        setStatus((EnumerationPropertyLiteral)null);
        return;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES:
        getFeatures().clear();
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
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__INCOMING_TRACES:
        return !getIncomingTraces().isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OUTGOING_TRACES:
        return !getOutgoingTraces().isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC:
        return visibleInDoc != VISIBLE_IN_DOC_EDEFAULT;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM:
        return visibleInLM != VISIBLE_IN_LM_EDEFAULT;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__SUMMARY:
        return SUMMARY_EDEFAULT == null ? summary != null : !SUMMARY_EDEFAULT.equals(summary);
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__REVIEW:
        return REVIEW_EDEFAULT == null ? review != null : !REVIEW_EDEFAULT.equals(review);
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUES:
        return ownedPropertyValues != null && !ownedPropertyValues.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ownedEnumerationPropertyTypes != null && !ownedEnumerationPropertyTypes.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUES:
        return appliedPropertyValues != null && !appliedPropertyValues.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS:
        return ownedPropertyValueGroups != null && !ownedPropertyValueGroups.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS:
        return appliedPropertyValueGroups != null && !appliedPropertyValueGroups.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__STATUS:
        return status != null;
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__FEATURES:
        return features != null && !features.isEmpty();
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
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
        case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC;
        case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM;
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
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC: return CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_DOC;
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM: return CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT__VISIBLE_IN_LM;
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
    result.append(')');
    return result.toString();
  }


} //AbstractPathInvolvedElementImpl