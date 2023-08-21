/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#isVisibleInDoc <em>Visible In Doc</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#isVisibleInLM <em>Visible In LM</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getReview <em>Review</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getOwnedPropertyValues <em>Owned Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getAppliedPropertyValues <em>Applied Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getSourceReferenceHierarchy <em>Source Reference Hierarchy</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getTargetReferenceHierarchy <em>Target Reference Hierarchy</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.SequenceLinkImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SequenceLinkImpl extends ModelElementImpl implements SequenceLink {

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
   * The cached value of the '{@link #getSourceReferenceHierarchy() <em>Source Reference Hierarchy</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSourceReferenceHierarchy()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainReference> sourceReferenceHierarchy;





	/**
   * The cached value of the '{@link #getTargetReferenceHierarchy() <em>Target Reference Hierarchy</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTargetReferenceHierarchy()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainReference> targetReferenceHierarchy;





	/**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
	protected Constraint condition;





	/**
   * The cached value of the '{@link #getLinks() <em>Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLinks()
   * @generated
   * @ordered
   */
	protected EList<FunctionalChainInvolvementLink> links;





	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected SequenceLinkEnd source;





	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected SequenceLinkEnd target;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SequenceLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.SEQUENCE_LINK;
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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC, oldVisibleInDoc, visibleInDoc));

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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM, oldVisibleInLM, visibleInLM));

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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__SUMMARY, oldSummary, summary));

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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__DESCRIPTION, oldDescription, description));

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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__REVIEW, oldReview, review));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPropertyValue> getOwnedPropertyValues() {

    if (ownedPropertyValues == null) {
      ownedPropertyValues = new EObjectContainmentEList.Resolving<AbstractPropertyValue>(AbstractPropertyValue.class, this, FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES);
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
      ownedEnumerationPropertyTypes = new EObjectContainmentEList.Resolving<EnumerationPropertyType>(EnumerationPropertyType.class, this, FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES);
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
      appliedPropertyValues = new EObjectResolvingEList<AbstractPropertyValue>(AbstractPropertyValue.class, this, FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUES);
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
      ownedPropertyValueGroups = new EObjectContainmentEList.Resolving<PropertyValueGroup>(PropertyValueGroup.class, this, FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS);
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
      appliedPropertyValueGroups = new EObjectResolvingEList<PropertyValueGroup>(PropertyValueGroup.class, this, FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS);
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.SEQUENCE_LINK__STATUS, oldStatus, status));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__STATUS, oldStatus, status));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyLiteral> getFeatures() {

    if (features == null) {
      features = new EObjectResolvingEList<EnumerationPropertyLiteral>(EnumerationPropertyLiteral.class, this, FaPackage.SEQUENCE_LINK__FEATURES);
    }
    return features;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainReference> getSourceReferenceHierarchy() {

    if (sourceReferenceHierarchy == null) {
      sourceReferenceHierarchy = new EObjectResolvingEList<FunctionalChainReference>(FunctionalChainReference.class, this, FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY);
    }
    return sourceReferenceHierarchy;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainReference> getTargetReferenceHierarchy() {

    if (targetReferenceHierarchy == null) {
      targetReferenceHierarchy = new EObjectResolvingEList<FunctionalChainReference>(FunctionalChainReference.class, this, FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY);
    }
    return targetReferenceHierarchy;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getCondition() {

    if (condition != null && condition.eIsProxy()) {
      InternalEObject oldCondition = (InternalEObject)condition;
      condition = (Constraint)eResolveProxy(oldCondition);
      if (condition != oldCondition) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.SEQUENCE_LINK__CONDITION, oldCondition, condition));
      }
    }
    return condition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetCondition() {

    return condition;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setCondition(Constraint newCondition) {

    Constraint oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__CONDITION, oldCondition, condition));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<FunctionalChainInvolvementLink> getLinks() {

    if (links == null) {
      links = new EObjectResolvingEList<FunctionalChainInvolvementLink>(FunctionalChainInvolvementLink.class, this, FaPackage.SEQUENCE_LINK__LINKS);
    }
    return links;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SequenceLinkEnd getSource() {

    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (SequenceLinkEnd)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.SEQUENCE_LINK__SOURCE, oldSource, source));
      }
    }
    return source;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SequenceLinkEnd basicGetSource() {

    return source;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSource(SequenceLinkEnd newSource) {

    SequenceLinkEnd oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__SOURCE, oldSource, source));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SequenceLinkEnd getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (SequenceLinkEnd)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FaPackage.SEQUENCE_LINK__TARGET, oldTarget, target));
      }
    }
    return target;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SequenceLinkEnd basicGetTarget() {

    return target;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(SequenceLinkEnd newTarget) {

    SequenceLinkEnd oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FaPackage.SEQUENCE_LINK__TARGET, oldTarget, target));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES:
        return ((InternalEList<?>)getOwnedPropertyValues()).basicRemove(otherEnd, msgs);
      case FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ((InternalEList<?>)getOwnedEnumerationPropertyTypes()).basicRemove(otherEnd, msgs);
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS:
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
      case FaPackage.SEQUENCE_LINK__INCOMING_TRACES:
        return getIncomingTraces();
      case FaPackage.SEQUENCE_LINK__OUTGOING_TRACES:
        return getOutgoingTraces();
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC:
        return isVisibleInDoc();
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM:
        return isVisibleInLM();
      case FaPackage.SEQUENCE_LINK__SUMMARY:
        return getSummary();
      case FaPackage.SEQUENCE_LINK__DESCRIPTION:
        return getDescription();
      case FaPackage.SEQUENCE_LINK__REVIEW:
        return getReview();
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES:
        return getOwnedPropertyValues();
      case FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES:
        return getOwnedEnumerationPropertyTypes();
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUES:
        return getAppliedPropertyValues();
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS:
        return getOwnedPropertyValueGroups();
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS:
        return getAppliedPropertyValueGroups();
      case FaPackage.SEQUENCE_LINK__STATUS:
        if (resolve) return getStatus();
        return basicGetStatus();
      case FaPackage.SEQUENCE_LINK__FEATURES:
        return getFeatures();
      case FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY:
        return getSourceReferenceHierarchy();
      case FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY:
        return getTargetReferenceHierarchy();
      case FaPackage.SEQUENCE_LINK__CONDITION:
        if (resolve) return getCondition();
        return basicGetCondition();
      case FaPackage.SEQUENCE_LINK__LINKS:
        return getLinks();
      case FaPackage.SEQUENCE_LINK__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case FaPackage.SEQUENCE_LINK__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
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
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC:
          setVisibleInDoc((Boolean)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM:
          setVisibleInLM((Boolean)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__SUMMARY:
          setSummary((String)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__DESCRIPTION:
          setDescription((String)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__REVIEW:
          setReview((String)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        getOwnedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        getOwnedEnumerationPropertyTypes().addAll((Collection<? extends EnumerationPropertyType>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        getAppliedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        getOwnedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        getAppliedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__STATUS:
          setStatus((EnumerationPropertyLiteral)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends EnumerationPropertyLiteral>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY:
        getSourceReferenceHierarchy().clear();
        getSourceReferenceHierarchy().addAll((Collection<? extends FunctionalChainReference>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY:
        getTargetReferenceHierarchy().clear();
        getTargetReferenceHierarchy().addAll((Collection<? extends FunctionalChainReference>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__CONDITION:
          setCondition((Constraint)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__LINKS:
        getLinks().clear();
        getLinks().addAll((Collection<? extends FunctionalChainInvolvementLink>)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__SOURCE:
          setSource((SequenceLinkEnd)newValue);
        return;
      case FaPackage.SEQUENCE_LINK__TARGET:
          setTarget((SequenceLinkEnd)newValue);
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
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC:
        setVisibleInDoc(VISIBLE_IN_DOC_EDEFAULT);
        return;
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM:
        setVisibleInLM(VISIBLE_IN_LM_EDEFAULT);
        return;
      case FaPackage.SEQUENCE_LINK__SUMMARY:
        setSummary(SUMMARY_EDEFAULT);
        return;
      case FaPackage.SEQUENCE_LINK__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case FaPackage.SEQUENCE_LINK__REVIEW:
        setReview(REVIEW_EDEFAULT);
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        return;
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        return;
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        return;
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        return;
      case FaPackage.SEQUENCE_LINK__STATUS:
        setStatus((EnumerationPropertyLiteral)null);
        return;
      case FaPackage.SEQUENCE_LINK__FEATURES:
        getFeatures().clear();
        return;
      case FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY:
        getSourceReferenceHierarchy().clear();
        return;
      case FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY:
        getTargetReferenceHierarchy().clear();
        return;
      case FaPackage.SEQUENCE_LINK__CONDITION:
        setCondition((Constraint)null);
        return;
      case FaPackage.SEQUENCE_LINK__LINKS:
        getLinks().clear();
        return;
      case FaPackage.SEQUENCE_LINK__SOURCE:
        setSource((SequenceLinkEnd)null);
        return;
      case FaPackage.SEQUENCE_LINK__TARGET:
        setTarget((SequenceLinkEnd)null);
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
      case FaPackage.SEQUENCE_LINK__INCOMING_TRACES:
        return !getIncomingTraces().isEmpty();
      case FaPackage.SEQUENCE_LINK__OUTGOING_TRACES:
        return !getOutgoingTraces().isEmpty();
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC:
        return visibleInDoc != VISIBLE_IN_DOC_EDEFAULT;
      case FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM:
        return visibleInLM != VISIBLE_IN_LM_EDEFAULT;
      case FaPackage.SEQUENCE_LINK__SUMMARY:
        return SUMMARY_EDEFAULT == null ? summary != null : !SUMMARY_EDEFAULT.equals(summary);
      case FaPackage.SEQUENCE_LINK__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case FaPackage.SEQUENCE_LINK__REVIEW:
        return REVIEW_EDEFAULT == null ? review != null : !REVIEW_EDEFAULT.equals(review);
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUES:
        return ownedPropertyValues != null && !ownedPropertyValues.isEmpty();
      case FaPackage.SEQUENCE_LINK__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ownedEnumerationPropertyTypes != null && !ownedEnumerationPropertyTypes.isEmpty();
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUES:
        return appliedPropertyValues != null && !appliedPropertyValues.isEmpty();
      case FaPackage.SEQUENCE_LINK__OWNED_PROPERTY_VALUE_GROUPS:
        return ownedPropertyValueGroups != null && !ownedPropertyValueGroups.isEmpty();
      case FaPackage.SEQUENCE_LINK__APPLIED_PROPERTY_VALUE_GROUPS:
        return appliedPropertyValueGroups != null && !appliedPropertyValueGroups.isEmpty();
      case FaPackage.SEQUENCE_LINK__STATUS:
        return status != null;
      case FaPackage.SEQUENCE_LINK__FEATURES:
        return features != null && !features.isEmpty();
      case FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY:
        return sourceReferenceHierarchy != null && !sourceReferenceHierarchy.isEmpty();
      case FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY:
        return targetReferenceHierarchy != null && !targetReferenceHierarchy.isEmpty();
      case FaPackage.SEQUENCE_LINK__CONDITION:
        return condition != null;
      case FaPackage.SEQUENCE_LINK__LINKS:
        return links != null && !links.isEmpty();
      case FaPackage.SEQUENCE_LINK__SOURCE:
        return source != null;
      case FaPackage.SEQUENCE_LINK__TARGET:
        return target != null;
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
        case FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC;
        case FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == ReferenceHierarchyContext.class) {
      switch (derivedFeatureID) {
        case FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY: return FaPackage.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY;
        case FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY: return FaPackage.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY;
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
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC: return FaPackage.SEQUENCE_LINK__VISIBLE_IN_DOC;
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM: return FaPackage.SEQUENCE_LINK__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == ReferenceHierarchyContext.class) {
      switch (baseFeatureID) {
        case FaPackage.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY: return FaPackage.SEQUENCE_LINK__SOURCE_REFERENCE_HIERARCHY;
        case FaPackage.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY: return FaPackage.SEQUENCE_LINK__TARGET_REFERENCE_HIERARCHY;
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



} //SequenceLinkImpl