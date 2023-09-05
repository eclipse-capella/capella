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

package org.polarsys.capella.core.data.information.impl;

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
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.behavior.AbstractSignal;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.data.modellingcore.impl.AbstractExchangeItemImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.Operation;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exchange Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getIncomingTraces <em>Incoming Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOutgoingTraces <em>Outgoing Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#isVisibleInDoc <em>Visible In Doc</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#isVisibleInLM <em>Visible In LM</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getReview <em>Review</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedPropertyValues <em>Owned Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedEnumerationPropertyTypes <em>Owned Enumeration Property Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getAppliedPropertyValues <em>Applied Property Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedPropertyValueGroups <em>Owned Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getAppliedPropertyValueGroups <em>Applied Property Value Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getTypedElements <em>Typed Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedGeneralizations <em>Owned Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getExchangeMechanism <em>Exchange Mechanism</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedInformationRealizations <em>Owned Information Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getOwnedExchangeItemInstances <em>Owned Exchange Item Instances</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getAllocatorInterfaces <em>Allocator Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getRealizedExchangeItems <em>Realized Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getRealizingExchangeItems <em>Realizing Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemImpl#getRealizingOperations <em>Realizing Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExchangeItemImpl extends AbstractExchangeItemImpl implements ExchangeItem {









	/**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected boolean final_ = FINAL_EDEFAULT;

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
   * The default value of the '{@link #getExchangeMechanism() <em>Exchange Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExchangeMechanism()
   * @generated
   * @ordered
   */
	protected static final ExchangeMechanism EXCHANGE_MECHANISM_EDEFAULT = ExchangeMechanism.UNSET;

	/**
   * The cached value of the '{@link #getExchangeMechanism() <em>Exchange Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExchangeMechanism()
   * @generated
   * @ordered
   */
	protected ExchangeMechanism exchangeMechanism = EXCHANGE_MECHANISM_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedElements()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItemElement> ownedElements;





	/**
   * The cached value of the '{@link #getOwnedInformationRealizations() <em>Owned Information Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInformationRealizations()
   * @generated
   * @ordered
   */
	protected EList<InformationRealization> ownedInformationRealizations;





	/**
   * The cached value of the '{@link #getOwnedExchangeItemInstances() <em>Owned Exchange Item Instances</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedExchangeItemInstances()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItemInstance> ownedExchangeItemInstances;




















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ExchangeItemImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.EXCHANGE_ITEM;
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC, oldVisibleInDoc, visibleInDoc));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM, oldVisibleInLM, visibleInLM));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__SUMMARY, oldSummary, summary));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__DESCRIPTION, oldDescription, description));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__REVIEW, oldReview, review));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractPropertyValue> getOwnedPropertyValues() {

    if (ownedPropertyValues == null) {
      ownedPropertyValues = new EObjectContainmentEList.Resolving<AbstractPropertyValue>(AbstractPropertyValue.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES);
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
      ownedEnumerationPropertyTypes = new EObjectContainmentEList.Resolving<EnumerationPropertyType>(EnumerationPropertyType.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES);
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
      appliedPropertyValues = new EObjectResolvingEList<AbstractPropertyValue>(AbstractPropertyValue.class, this, InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES);
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
      ownedPropertyValueGroups = new EObjectContainmentEList.Resolving<PropertyValueGroup>(PropertyValueGroup.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS);
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
      appliedPropertyValueGroups = new EObjectResolvingEList<PropertyValueGroup>(PropertyValueGroup.class, this, InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS);
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.EXCHANGE_ITEM__STATUS, oldStatus, status));
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__STATUS, oldStatus, status));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EnumerationPropertyLiteral> getFeatures() {

    if (features == null) {
      features = new EObjectResolvingEList<EnumerationPropertyLiteral>(EnumerationPropertyLiteral.class, this, InformationPackage.EXCHANGE_ITEM__FEATURES);
    }
    return features;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Trace> getOwnedTraces() {

    if (ownedTraces == null) {
      ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_TRACES);
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
      namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, InformationPackage.EXCHANGE_ITEM__NAMING_RULES);
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__ABSTRACT, oldAbstract, abstract_));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Generalization> getOwnedGeneralizations() {

    if (ownedGeneralizations == null) {
      ownedGeneralizations = new EObjectContainmentEList.Resolving<Generalization>(Generalization.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS);
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

	public boolean isFinal() {

    return final_;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setFinal(boolean newFinal) {

    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__FINAL, oldFinal, final_));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExchangeMechanism getExchangeMechanism() {

    return exchangeMechanism;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setExchangeMechanism(ExchangeMechanism newExchangeMechanism) {

    ExchangeMechanism oldExchangeMechanism = exchangeMechanism;
    exchangeMechanism = newExchangeMechanism == null ? EXCHANGE_MECHANISM_EDEFAULT : newExchangeMechanism;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM__EXCHANGE_MECHANISM, oldExchangeMechanism, exchangeMechanism));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeItemElement> getOwnedElements() {

    if (ownedElements == null) {
      ownedElements = new EObjectContainmentEList.Resolving<ExchangeItemElement>(ExchangeItemElement.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS);
    }
    return ownedElements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InformationRealization> getOwnedInformationRealizations() {

    if (ownedInformationRealizations == null) {
      ownedInformationRealizations = new EObjectContainmentEList.Resolving<InformationRealization>(InformationRealization.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);
    }
    return ownedInformationRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeItemInstance> getOwnedExchangeItemInstances() {

    if (ownedExchangeItemInstances == null) {
      ownedExchangeItemInstances = new EObjectContainmentEList.Resolving<ExchangeItemInstance>(ExchangeItemInstance.class, this, InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES);
    }
    return ownedExchangeItemInstances;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getAllocatorInterfaces() {


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
    EAnnotation annotation = InformationPackage.Literals.EXCHANGE_ITEM__ALLOCATOR_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.EXCHANGE_ITEM__ALLOCATOR_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, InformationPackage.Literals.EXCHANGE_ITEM__ALLOCATOR_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeItem> getRealizedExchangeItems() {


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
    EAnnotation annotation = InformationPackage.Literals.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ExchangeItem> resultAsList = (Collection<ExchangeItem>) result;
    return new EcoreEList.UnmodifiableEList<ExchangeItem>(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeItem> getRealizingExchangeItems() {


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
    EAnnotation annotation = InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ExchangeItem> resultAsList = (Collection<ExchangeItem>) result;
    return new EcoreEList.UnmodifiableEList<ExchangeItem>(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Operation> getRealizingOperations() {


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
    EAnnotation annotation = InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_OPERATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_OPERATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Operation> resultAsList = (Collection<Operation>) result;
    return new EcoreEList.UnmodifiableEList<Operation>(this, InformationPackage.Literals.EXCHANGE_ITEM__REALIZING_OPERATIONS, resultAsList.size(), resultAsList.toArray());
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
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES:
        return ((InternalEList<?>)getOwnedPropertyValues()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ((InternalEList<?>)getOwnedEnumerationPropertyTypes()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS:
        return ((InternalEList<?>)getOwnedPropertyValueGroups()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES:
        return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__NAMING_RULES:
        return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS:
        return ((InternalEList<?>)getOwnedGeneralizations()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS:
        return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS:
        return ((InternalEList<?>)getOwnedInformationRealizations()).basicRemove(otherEnd, msgs);
      case InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES:
        return ((InternalEList<?>)getOwnedExchangeItemInstances()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.EXCHANGE_ITEM__FINAL:
        return isFinal();
      case InformationPackage.EXCHANGE_ITEM__INCOMING_TRACES:
        return getIncomingTraces();
      case InformationPackage.EXCHANGE_ITEM__OUTGOING_TRACES:
        return getOutgoingTraces();
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC:
        return isVisibleInDoc();
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM:
        return isVisibleInLM();
      case InformationPackage.EXCHANGE_ITEM__SUMMARY:
        return getSummary();
      case InformationPackage.EXCHANGE_ITEM__DESCRIPTION:
        return getDescription();
      case InformationPackage.EXCHANGE_ITEM__REVIEW:
        return getReview();
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES:
        return getOwnedPropertyValues();
      case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES:
        return getOwnedEnumerationPropertyTypes();
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES:
        return getAppliedPropertyValues();
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS:
        return getOwnedPropertyValueGroups();
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS:
        return getAppliedPropertyValueGroups();
      case InformationPackage.EXCHANGE_ITEM__STATUS:
        if (resolve) return getStatus();
        return basicGetStatus();
      case InformationPackage.EXCHANGE_ITEM__FEATURES:
        return getFeatures();
      case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES:
        return getOwnedTraces();
      case InformationPackage.EXCHANGE_ITEM__CONTAINED_GENERIC_TRACES:
        return getContainedGenericTraces();
      case InformationPackage.EXCHANGE_ITEM__NAMING_RULES:
        return getNamingRules();
      case InformationPackage.EXCHANGE_ITEM__TYPED_ELEMENTS:
        return getTypedElements();
      case InformationPackage.EXCHANGE_ITEM__ABSTRACT:
        return isAbstract();
      case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS:
        return getOwnedGeneralizations();
      case InformationPackage.EXCHANGE_ITEM__SUPER_GENERALIZATIONS:
        return getSuperGeneralizations();
      case InformationPackage.EXCHANGE_ITEM__SUB_GENERALIZATIONS:
        return getSubGeneralizations();
      case InformationPackage.EXCHANGE_ITEM__SUPER:
        return getSuper();
      case InformationPackage.EXCHANGE_ITEM__SUB:
        return getSub();
      case InformationPackage.EXCHANGE_ITEM__EXCHANGE_MECHANISM:
        return getExchangeMechanism();
      case InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS:
        return getOwnedElements();
      case InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS:
        return getOwnedInformationRealizations();
      case InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES:
        return getOwnedExchangeItemInstances();
      case InformationPackage.EXCHANGE_ITEM__ALLOCATOR_INTERFACES:
        return getAllocatorInterfaces();
      case InformationPackage.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS:
        return getRealizedExchangeItems();
      case InformationPackage.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS:
        return getRealizingExchangeItems();
      case InformationPackage.EXCHANGE_ITEM__REALIZING_OPERATIONS:
        return getRealizingOperations();
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
      case InformationPackage.EXCHANGE_ITEM__FINAL:
          setFinal((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC:
          setVisibleInDoc((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM:
          setVisibleInLM((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__SUMMARY:
          setSummary((String)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__DESCRIPTION:
          setDescription((String)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__REVIEW:
          setReview((String)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        getOwnedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        getOwnedEnumerationPropertyTypes().addAll((Collection<? extends EnumerationPropertyType>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        getAppliedPropertyValues().addAll((Collection<? extends AbstractPropertyValue>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        getOwnedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        getAppliedPropertyValueGroups().addAll((Collection<? extends PropertyValueGroup>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__STATUS:
          setStatus((EnumerationPropertyLiteral)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends EnumerationPropertyLiteral>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES:
        getOwnedTraces().clear();
        getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__NAMING_RULES:
        getNamingRules().clear();
        getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__ABSTRACT:
          setAbstract((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
        getOwnedGeneralizations().addAll((Collection<? extends Generalization>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__EXCHANGE_MECHANISM:
          setExchangeMechanism((ExchangeMechanism)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS:
        getOwnedElements().clear();
        getOwnedElements().addAll((Collection<? extends ExchangeItemElement>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS:
        getOwnedInformationRealizations().clear();
        getOwnedInformationRealizations().addAll((Collection<? extends InformationRealization>)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES:
        getOwnedExchangeItemInstances().clear();
        getOwnedExchangeItemInstances().addAll((Collection<? extends ExchangeItemInstance>)newValue);
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
      case InformationPackage.EXCHANGE_ITEM__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC:
        setVisibleInDoc(VISIBLE_IN_DOC_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM:
        setVisibleInLM(VISIBLE_IN_LM_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__SUMMARY:
        setSummary(SUMMARY_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__DESCRIPTION:
        setDescription(DESCRIPTION_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__REVIEW:
        setReview(REVIEW_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES:
        getOwnedPropertyValues().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES:
        getOwnedEnumerationPropertyTypes().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES:
        getAppliedPropertyValues().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS:
        getOwnedPropertyValueGroups().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS:
        getAppliedPropertyValueGroups().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__STATUS:
        setStatus((EnumerationPropertyLiteral)null);
        return;
      case InformationPackage.EXCHANGE_ITEM__FEATURES:
        getFeatures().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES:
        getOwnedTraces().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__NAMING_RULES:
        getNamingRules().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__EXCHANGE_MECHANISM:
        setExchangeMechanism(EXCHANGE_MECHANISM_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS:
        getOwnedElements().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS:
        getOwnedInformationRealizations().clear();
        return;
      case InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES:
        getOwnedExchangeItemInstances().clear();
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
      case InformationPackage.EXCHANGE_ITEM__FINAL:
        return final_ != FINAL_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM__INCOMING_TRACES:
        return !getIncomingTraces().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OUTGOING_TRACES:
        return !getOutgoingTraces().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC:
        return visibleInDoc != VISIBLE_IN_DOC_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM:
        return visibleInLM != VISIBLE_IN_LM_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM__SUMMARY:
        return SUMMARY_EDEFAULT == null ? summary != null : !SUMMARY_EDEFAULT.equals(summary);
      case InformationPackage.EXCHANGE_ITEM__DESCRIPTION:
        return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
      case InformationPackage.EXCHANGE_ITEM__REVIEW:
        return REVIEW_EDEFAULT == null ? review != null : !REVIEW_EDEFAULT.equals(review);
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES:
        return ownedPropertyValues != null && !ownedPropertyValues.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES:
        return ownedEnumerationPropertyTypes != null && !ownedEnumerationPropertyTypes.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES:
        return appliedPropertyValues != null && !appliedPropertyValues.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS:
        return ownedPropertyValueGroups != null && !ownedPropertyValueGroups.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS:
        return appliedPropertyValueGroups != null && !appliedPropertyValueGroups.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__STATUS:
        return status != null;
      case InformationPackage.EXCHANGE_ITEM__FEATURES:
        return features != null && !features.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES:
        return ownedTraces != null && !ownedTraces.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__CONTAINED_GENERIC_TRACES:
        return !getContainedGenericTraces().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__NAMING_RULES:
        return namingRules != null && !namingRules.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__TYPED_ELEMENTS:
        return !getTypedElements().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS:
        return ownedGeneralizations != null && !ownedGeneralizations.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__SUPER_GENERALIZATIONS:
        return !getSuperGeneralizations().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__SUB_GENERALIZATIONS:
        return !getSubGeneralizations().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__SUPER:
        return !getSuper().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__SUB:
        return !getSub().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__EXCHANGE_MECHANISM:
        return exchangeMechanism != EXCHANGE_MECHANISM_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM__OWNED_ELEMENTS:
        return ownedElements != null && !ownedElements.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS:
        return ownedInformationRealizations != null && !ownedInformationRealizations.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES:
        return ownedExchangeItemInstances != null && !ownedExchangeItemInstances.isEmpty();
      case InformationPackage.EXCHANGE_ITEM__ALLOCATOR_INTERFACES:
        return !getAllocatorInterfaces().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS:
        return !getRealizedExchangeItems().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS:
        return !getRealizingExchangeItems().isEmpty();
      case InformationPackage.EXCHANGE_ITEM__REALIZING_OPERATIONS:
        return !getRealizingOperations().isEmpty();
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
    if (baseClass == AbstractEvent.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractSignal.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__FINAL: return ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL;
        default: return -1;
      }
    }
    if (baseClass == TraceableElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__INCOMING_TRACES: return ModellingcorePackage.TRACEABLE_ELEMENT__INCOMING_TRACES;
        case InformationPackage.EXCHANGE_ITEM__OUTGOING_TRACES: return ModellingcorePackage.TRACEABLE_ELEMENT__OUTGOING_TRACES;
        default: return -1;
      }
    }
    if (baseClass == PublishableElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC;
        case InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM: return ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == CapellaElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__SUMMARY: return CapellacorePackage.CAPELLA_ELEMENT__SUMMARY;
        case InformationPackage.EXCHANGE_ITEM__DESCRIPTION: return CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION;
        case InformationPackage.EXCHANGE_ITEM__REVIEW: return CapellacorePackage.CAPELLA_ELEMENT__REVIEW;
        case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES: return CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES;
        case InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES: return CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES;
        case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES: return CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES;
        case InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS: return CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS;
        case InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS: return CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS;
        case InformationPackage.EXCHANGE_ITEM__STATUS: return CapellacorePackage.CAPELLA_ELEMENT__STATUS;
        case InformationPackage.EXCHANGE_ITEM__FEATURES: return CapellacorePackage.CAPELLA_ELEMENT__FEATURES;
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
        case InformationPackage.EXCHANGE_ITEM__OWNED_TRACES: return CapellacorePackage.NAMESPACE__OWNED_TRACES;
        case InformationPackage.EXCHANGE_ITEM__CONTAINED_GENERIC_TRACES: return CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES;
        case InformationPackage.EXCHANGE_ITEM__NAMING_RULES: return CapellacorePackage.NAMESPACE__NAMING_RULES;
        default: return -1;
      }
    }
    if (baseClass == Type.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__TYPED_ELEMENTS: return CapellacorePackage.TYPE__TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == GeneralizableElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM__ABSTRACT: return CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT;
        case InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS;
        case InformationPackage.EXCHANGE_ITEM__SUPER_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS;
        case InformationPackage.EXCHANGE_ITEM__SUB_GENERALIZATIONS: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS;
        case InformationPackage.EXCHANGE_ITEM__SUPER: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER;
        case InformationPackage.EXCHANGE_ITEM__SUB: return CapellacorePackage.GENERALIZABLE_ELEMENT__SUB;
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
    if (baseClass == AbstractEvent.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractSignal.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL: return InformationPackage.EXCHANGE_ITEM__FINAL;
        default: return -1;
      }
    }
    if (baseClass == TraceableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.TRACEABLE_ELEMENT__INCOMING_TRACES: return InformationPackage.EXCHANGE_ITEM__INCOMING_TRACES;
        case ModellingcorePackage.TRACEABLE_ELEMENT__OUTGOING_TRACES: return InformationPackage.EXCHANGE_ITEM__OUTGOING_TRACES;
        default: return -1;
      }
    }
    if (baseClass == PublishableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC: return InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_DOC;
        case ModellingcorePackage.PUBLISHABLE_ELEMENT__VISIBLE_IN_LM: return InformationPackage.EXCHANGE_ITEM__VISIBLE_IN_LM;
        default: return -1;
      }
    }
    if (baseClass == CapellaElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.CAPELLA_ELEMENT__SUMMARY: return InformationPackage.EXCHANGE_ITEM__SUMMARY;
        case CapellacorePackage.CAPELLA_ELEMENT__DESCRIPTION: return InformationPackage.EXCHANGE_ITEM__DESCRIPTION;
        case CapellacorePackage.CAPELLA_ELEMENT__REVIEW: return InformationPackage.EXCHANGE_ITEM__REVIEW;
        case CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUES: return InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUES;
        case CapellacorePackage.CAPELLA_ELEMENT__OWNED_ENUMERATION_PROPERTY_TYPES: return InformationPackage.EXCHANGE_ITEM__OWNED_ENUMERATION_PROPERTY_TYPES;
        case CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES: return InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUES;
        case CapellacorePackage.CAPELLA_ELEMENT__OWNED_PROPERTY_VALUE_GROUPS: return InformationPackage.EXCHANGE_ITEM__OWNED_PROPERTY_VALUE_GROUPS;
        case CapellacorePackage.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS: return InformationPackage.EXCHANGE_ITEM__APPLIED_PROPERTY_VALUE_GROUPS;
        case CapellacorePackage.CAPELLA_ELEMENT__STATUS: return InformationPackage.EXCHANGE_ITEM__STATUS;
        case CapellacorePackage.CAPELLA_ELEMENT__FEATURES: return InformationPackage.EXCHANGE_ITEM__FEATURES;
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
        case CapellacorePackage.NAMESPACE__OWNED_TRACES: return InformationPackage.EXCHANGE_ITEM__OWNED_TRACES;
        case CapellacorePackage.NAMESPACE__CONTAINED_GENERIC_TRACES: return InformationPackage.EXCHANGE_ITEM__CONTAINED_GENERIC_TRACES;
        case CapellacorePackage.NAMESPACE__NAMING_RULES: return InformationPackage.EXCHANGE_ITEM__NAMING_RULES;
        default: return -1;
      }
    }
    if (baseClass == Type.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.TYPE__TYPED_ELEMENTS: return InformationPackage.EXCHANGE_ITEM__TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == GeneralizableElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT: return InformationPackage.EXCHANGE_ITEM__ABSTRACT;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS: return InformationPackage.EXCHANGE_ITEM__OWNED_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS: return InformationPackage.EXCHANGE_ITEM__SUPER_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS: return InformationPackage.EXCHANGE_ITEM__SUB_GENERALIZATIONS;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER: return InformationPackage.EXCHANGE_ITEM__SUPER;
        case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB: return InformationPackage.EXCHANGE_ITEM__SUB;
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
    result.append(" (final: "); //$NON-NLS-1$
    result.append(final_);
    result.append(", visibleInDoc: "); //$NON-NLS-1$
    result.append(visibleInDoc);
    result.append(", visibleInLM: "); //$NON-NLS-1$
    result.append(visibleInLM);
    result.append(", summary: "); //$NON-NLS-1$
    result.append(summary);
    result.append(", review: "); //$NON-NLS-1$
    result.append(review);
    result.append(", abstract: "); //$NON-NLS-1$
    result.append(abstract_);
    result.append(", exchangeMechanism: "); //$NON-NLS-1$
    result.append(exchangeMechanism);
    result.append(')');
    return result.toString();
  }


} //ExchangeItemImpl
