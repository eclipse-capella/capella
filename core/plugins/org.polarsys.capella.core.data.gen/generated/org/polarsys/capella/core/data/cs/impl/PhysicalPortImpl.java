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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.impl.PortImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getInformationFlows <em>Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getInvolvedLinks <em>Involved Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isIsStatic <em>Is Static</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAbstractType <em>Abstract Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMinCard <em>Owned Min Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMaxCard <em>Owned Max Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAggregationKind <em>Aggregation Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#isIsPartOfKey <em>Is Part Of Key</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedComponentPortAllocations <em>Owned Component Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getOwnedPhysicalPortRealizations <em>Owned Physical Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getAllocatedComponentPorts <em>Allocated Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getRealizedPhysicalPorts <em>Realized Physical Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPortImpl#getRealizingPhysicalPorts <em>Realizing Physical Ports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalPortImpl extends PortImpl implements PhysicalPort {









	/**
   * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsAbstract()
   * @generated
   * @ordered
   */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsAbstract()
   * @generated
   * @ordered
   */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;













	/**
   * The default value of the '{@link #isIsStatic() <em>Is Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStatic()
   * @generated
   * @ordered
   */
	protected static final boolean IS_STATIC_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isIsStatic() <em>Is Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStatic()
   * @generated
   * @ordered
   */
	protected boolean isStatic = IS_STATIC_EDEFAULT;













	/**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.UNSET;













	/**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;













	/**
   * The cached value of the '{@link #getAbstractType() <em>Abstract Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAbstractType()
   * @generated
   * @ordered
   */
	protected AbstractType abstractType;













	/**
   * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
	protected static final boolean ORDERED_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
	protected boolean ordered = ORDERED_EDEFAULT;













	/**
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
	protected static final boolean UNIQUE_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
	protected boolean unique = UNIQUE_EDEFAULT;













	/**
   * The default value of the '{@link #isMinInclusive() <em>Min Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMinInclusive()
   * @generated
   * @ordered
   */
	protected static final boolean MIN_INCLUSIVE_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isMinInclusive() <em>Min Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMinInclusive()
   * @generated
   * @ordered
   */
	protected boolean minInclusive = MIN_INCLUSIVE_EDEFAULT;













	/**
   * The default value of the '{@link #isMaxInclusive() <em>Max Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMaxInclusive()
   * @generated
   * @ordered
   */
	protected static final boolean MAX_INCLUSIVE_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isMaxInclusive() <em>Max Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMaxInclusive()
   * @generated
   * @ordered
   */
	protected boolean maxInclusive = MAX_INCLUSIVE_EDEFAULT;













	/**
   * The cached value of the '{@link #getOwnedDefaultValue() <em>Owned Default Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefaultValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedDefaultValue;













	/**
   * The cached value of the '{@link #getOwnedMinValue() <em>Owned Min Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedMinValue;













	/**
   * The cached value of the '{@link #getOwnedMaxValue() <em>Owned Max Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedMaxValue;













	/**
   * The cached value of the '{@link #getOwnedNullValue() <em>Owned Null Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedNullValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedNullValue;













	/**
   * The cached value of the '{@link #getOwnedMinCard() <em>Owned Min Card</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinCard()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMinCard;













	/**
   * The cached value of the '{@link #getOwnedMinLength() <em>Owned Min Length</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinLength()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMinLength;













	/**
   * The cached value of the '{@link #getOwnedMaxCard() <em>Owned Max Card</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxCard()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMaxCard;













	/**
   * The cached value of the '{@link #getOwnedMaxLength() <em>Owned Max Length</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxLength()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMaxLength;













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
   * The default value of the '{@link #getAggregationKind() <em>Aggregation Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAggregationKind()
   * @generated
   * @ordered
   */
	protected static final AggregationKind AGGREGATION_KIND_EDEFAULT = AggregationKind.UNSET;













	/**
   * The cached value of the '{@link #getAggregationKind() <em>Aggregation Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAggregationKind()
   * @generated
   * @ordered
   */
	protected AggregationKind aggregationKind = AGGREGATION_KIND_EDEFAULT;













	/**
   * The default value of the '{@link #isIsDerived() <em>Is Derived</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsDerived()
   * @generated
   * @ordered
   */
	protected static final boolean IS_DERIVED_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isIsDerived() <em>Is Derived</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsDerived()
   * @generated
   * @ordered
   */
	protected boolean isDerived = IS_DERIVED_EDEFAULT;













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
   * The default value of the '{@link #isIsPartOfKey() <em>Is Part Of Key</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPartOfKey()
   * @generated
   * @ordered
   */
	protected static final boolean IS_PART_OF_KEY_EDEFAULT = false;













	/**
   * The cached value of the '{@link #isIsPartOfKey() <em>Is Part Of Key</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPartOfKey()
   * @generated
   * @ordered
   */
	protected boolean isPartOfKey = IS_PART_OF_KEY_EDEFAULT;













	/**
   * The cached value of the '{@link #getOwnedComponentPortAllocations() <em>Owned Component Port Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentPortAllocations()
   * @generated
   * @ordered
   */
	protected EList<ComponentPortAllocation> ownedComponentPortAllocations;





	/**
   * The cached value of the '{@link #getOwnedPhysicalPortRealizations() <em>Owned Physical Port Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalPortRealizations()
   * @generated
   * @ordered
   */
	protected EList<PhysicalPortRealization> ownedPhysicalPortRealizations;
















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalPortImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PHYSICAL_PORT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConfigurationItem> getAllocatorConfigurationItems() {


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
    EAnnotation annotation = CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ConfigurationItem> resultAsList = (Collection<ConfigurationItem>) result;
    return new EcoreEList.UnmodifiableEList<ConfigurationItem>(this, CsPackage.Literals.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getIncomingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getOutgoingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalLink> getInvolvedLinks() {


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
    EAnnotation annotation = CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalLink> resultAsList = (Collection<PhysicalLink>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalLink>(this, CsPackage.Literals.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS, resultAsList.size(), resultAsList.toArray());
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

	public boolean isIsAbstract() {

    return isAbstract;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsAbstract(boolean newIsAbstract) {

    boolean oldIsAbstract = isAbstract;
    isAbstract = newIsAbstract;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__IS_ABSTRACT, oldIsAbstract, isAbstract));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsStatic() {

    return isStatic;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsStatic(boolean newIsStatic) {

    boolean oldIsStatic = isStatic;
    isStatic = newIsStatic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__IS_STATIC, oldIsStatic, isStatic));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public VisibilityKind getVisibility() {

    return visibility;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVisibility(VisibilityKind newVisibility) {

    VisibilityKind oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__VISIBILITY, oldVisibility, visibility));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType getAbstractType() {

    if (abstractType != null && abstractType.eIsProxy()) {
      InternalEObject oldAbstractType = (InternalEObject)abstractType;
      abstractType = (AbstractType)eResolveProxy(oldAbstractType);
      if (abstractType != oldAbstractType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE, oldAbstractType, abstractType));
      }
    }
    return abstractType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetAbstractType() {

    return abstractType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAbstractType(AbstractType newAbstractType) {

    AbstractType oldAbstractType = abstractType;
    abstractType = newAbstractType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE, oldAbstractType, abstractType));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type getType() {

    Type type = basicGetType();
    return type != null && type.eIsProxy() ? (Type)eResolveProxy((InternalEObject)type) : type;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type basicGetType() {


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
    EAnnotation annotation = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE, annotation);
    
    try {
      return (Type) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isOrdered() {

    return ordered;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOrdered(boolean newOrdered) {

    boolean oldOrdered = ordered;
    ordered = newOrdered;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__ORDERED, oldOrdered, ordered));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isUnique() {

    return unique;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUnique(boolean newUnique) {

    boolean oldUnique = unique;
    unique = newUnique;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__UNIQUE, oldUnique, unique));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isMinInclusive() {

    return minInclusive;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMinInclusive(boolean newMinInclusive) {

    boolean oldMinInclusive = minInclusive;
    minInclusive = newMinInclusive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE, oldMinInclusive, minInclusive));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isMaxInclusive() {

    return maxInclusive;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMaxInclusive(boolean newMaxInclusive) {

    boolean oldMaxInclusive = maxInclusive;
    maxInclusive = newMaxInclusive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE, oldMaxInclusive, maxInclusive));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedDefaultValue() {

    return ownedDefaultValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDefaultValue(DataValue newOwnedDefaultValue, NotificationChain msgs) {

    DataValue oldOwnedDefaultValue = ownedDefaultValue;
    ownedDefaultValue = newOwnedDefaultValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
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
	public void setOwnedDefaultValue(DataValue newOwnedDefaultValue) {

    if (newOwnedDefaultValue != ownedDefaultValue) {
      NotificationChain msgs = null;
      if (ownedDefaultValue != null)
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedMinValue() {

    return ownedMinValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinValue(DataValue newOwnedMinValue, NotificationChain msgs) {

    DataValue oldOwnedMinValue = ownedMinValue;
    ownedMinValue = newOwnedMinValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE, oldOwnedMinValue, newOwnedMinValue);
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
	public void setOwnedMinValue(DataValue newOwnedMinValue) {

    if (newOwnedMinValue != ownedMinValue) {
      NotificationChain msgs = null;
      if (ownedMinValue != null)
        msgs = ((InternalEObject)ownedMinValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE, null, msgs);
      if (newOwnedMinValue != null)
        msgs = ((InternalEObject)newOwnedMinValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE, null, msgs);
      msgs = basicSetOwnedMinValue(newOwnedMinValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE, newOwnedMinValue, newOwnedMinValue));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedMaxValue() {

    return ownedMaxValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxValue(DataValue newOwnedMaxValue, NotificationChain msgs) {

    DataValue oldOwnedMaxValue = ownedMaxValue;
    ownedMaxValue = newOwnedMaxValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE, oldOwnedMaxValue, newOwnedMaxValue);
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
	public void setOwnedMaxValue(DataValue newOwnedMaxValue) {

    if (newOwnedMaxValue != ownedMaxValue) {
      NotificationChain msgs = null;
      if (ownedMaxValue != null)
        msgs = ((InternalEObject)ownedMaxValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE, null, msgs);
      if (newOwnedMaxValue != null)
        msgs = ((InternalEObject)newOwnedMaxValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE, null, msgs);
      msgs = basicSetOwnedMaxValue(newOwnedMaxValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE, newOwnedMaxValue, newOwnedMaxValue));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedNullValue() {

    return ownedNullValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedNullValue(DataValue newOwnedNullValue, NotificationChain msgs) {

    DataValue oldOwnedNullValue = ownedNullValue;
    ownedNullValue = newOwnedNullValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE, oldOwnedNullValue, newOwnedNullValue);
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
	public void setOwnedNullValue(DataValue newOwnedNullValue) {

    if (newOwnedNullValue != ownedNullValue) {
      NotificationChain msgs = null;
      if (ownedNullValue != null)
        msgs = ((InternalEObject)ownedNullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE, null, msgs);
      if (newOwnedNullValue != null)
        msgs = ((InternalEObject)newOwnedNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE, null, msgs);
      msgs = basicSetOwnedNullValue(newOwnedNullValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE, newOwnedNullValue, newOwnedNullValue));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMinCard() {

    return ownedMinCard;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinCard(NumericValue newOwnedMinCard, NotificationChain msgs) {

    NumericValue oldOwnedMinCard = ownedMinCard;
    ownedMinCard = newOwnedMinCard;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD, oldOwnedMinCard, newOwnedMinCard);
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
	public void setOwnedMinCard(NumericValue newOwnedMinCard) {

    if (newOwnedMinCard != ownedMinCard) {
      NotificationChain msgs = null;
      if (ownedMinCard != null)
        msgs = ((InternalEObject)ownedMinCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD, null, msgs);
      if (newOwnedMinCard != null)
        msgs = ((InternalEObject)newOwnedMinCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD, null, msgs);
      msgs = basicSetOwnedMinCard(newOwnedMinCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD, newOwnedMinCard, newOwnedMinCard));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMinLength() {

    return ownedMinLength;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinLength(NumericValue newOwnedMinLength, NotificationChain msgs) {

    NumericValue oldOwnedMinLength = ownedMinLength;
    ownedMinLength = newOwnedMinLength;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH, oldOwnedMinLength, newOwnedMinLength);
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
	public void setOwnedMinLength(NumericValue newOwnedMinLength) {

    if (newOwnedMinLength != ownedMinLength) {
      NotificationChain msgs = null;
      if (ownedMinLength != null)
        msgs = ((InternalEObject)ownedMinLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH, null, msgs);
      if (newOwnedMinLength != null)
        msgs = ((InternalEObject)newOwnedMinLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH, null, msgs);
      msgs = basicSetOwnedMinLength(newOwnedMinLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH, newOwnedMinLength, newOwnedMinLength));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMaxCard() {

    return ownedMaxCard;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxCard(NumericValue newOwnedMaxCard, NotificationChain msgs) {

    NumericValue oldOwnedMaxCard = ownedMaxCard;
    ownedMaxCard = newOwnedMaxCard;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD, oldOwnedMaxCard, newOwnedMaxCard);
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
	public void setOwnedMaxCard(NumericValue newOwnedMaxCard) {

    if (newOwnedMaxCard != ownedMaxCard) {
      NotificationChain msgs = null;
      if (ownedMaxCard != null)
        msgs = ((InternalEObject)ownedMaxCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD, null, msgs);
      if (newOwnedMaxCard != null)
        msgs = ((InternalEObject)newOwnedMaxCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD, null, msgs);
      msgs = basicSetOwnedMaxCard(newOwnedMaxCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD, newOwnedMaxCard, newOwnedMaxCard));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMaxLength() {

    return ownedMaxLength;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxLength(NumericValue newOwnedMaxLength, NotificationChain msgs) {

    NumericValue oldOwnedMaxLength = ownedMaxLength;
    ownedMaxLength = newOwnedMaxLength;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH, oldOwnedMaxLength, newOwnedMaxLength);
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
	public void setOwnedMaxLength(NumericValue newOwnedMaxLength) {

    if (newOwnedMaxLength != ownedMaxLength) {
      NotificationChain msgs = null;
      if (ownedMaxLength != null)
        msgs = ((InternalEObject)ownedMaxLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH, null, msgs);
      if (newOwnedMaxLength != null)
        msgs = ((InternalEObject)newOwnedMaxLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH, null, msgs);
      msgs = basicSetOwnedMaxLength(newOwnedMaxLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH, newOwnedMaxLength, newOwnedMaxLength));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__FINAL, oldFinal, final_));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AggregationKind getAggregationKind() {

    return aggregationKind;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAggregationKind(AggregationKind newAggregationKind) {

    AggregationKind oldAggregationKind = aggregationKind;
    aggregationKind = newAggregationKind == null ? AGGREGATION_KIND_EDEFAULT : newAggregationKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__AGGREGATION_KIND, oldAggregationKind, aggregationKind));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsDerived() {

    return isDerived;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsDerived(boolean newIsDerived) {

    boolean oldIsDerived = isDerived;
    isDerived = newIsDerived;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__IS_DERIVED, oldIsDerived, isDerived));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__IS_READ_ONLY, oldIsReadOnly, isReadOnly));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsPartOfKey() {

    return isPartOfKey;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsPartOfKey(boolean newIsPartOfKey) {

    boolean oldIsPartOfKey = isPartOfKey;
    isPartOfKey = newIsPartOfKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY, oldIsPartOfKey, isPartOfKey));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Association getAssociation() {

    Association association = basicGetAssociation();
    return association != null && association.eIsProxy() ? (Association)eResolveProxy((InternalEObject)association) : association;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Association basicGetAssociation() {


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
    EAnnotation annotation = InformationPackage.Literals.PROPERTY__ASSOCIATION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PROPERTY__ASSOCIATION, annotation);
    
    try {
      return (Association) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentPortAllocation> getOwnedComponentPortAllocations() {

    if (ownedComponentPortAllocations == null) {
      ownedComponentPortAllocations = new EObjectContainmentEList.Resolving<ComponentPortAllocation>(ComponentPortAllocation.class, this, CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS);
    }
    return ownedComponentPortAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalPortRealization> getOwnedPhysicalPortRealizations() {

    if (ownedPhysicalPortRealizations == null) {
      ownedPhysicalPortRealizations = new EObjectContainmentEList.Resolving<PhysicalPortRealization>(PhysicalPortRealization.class, this, CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS);
    }
    return ownedPhysicalPortRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentPort> getAllocatedComponentPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
    return new EcoreEList.UnmodifiableEList<ComponentPort>(this, CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalPort> getRealizedPhysicalPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalPort> resultAsList = (Collection<PhysicalPort>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalPort>(this, CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalPort> getRealizingPhysicalPorts() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalPort> resultAsList = (Collection<PhysicalPort>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalPort>(this, CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS, resultAsList.size(), resultAsList.toArray());
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
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
        return basicSetOwnedMinValue(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
        return basicSetOwnedMaxValue(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
        return basicSetOwnedNullValue(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
        return basicSetOwnedMinCard(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
        return basicSetOwnedMinLength(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
        return basicSetOwnedMaxCard(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
        return basicSetOwnedMaxLength(null, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedComponentPortAllocations()).basicRemove(otherEnd, msgs);
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
        return ((InternalEList<?>)getOwnedPhysicalPortRealizations()).basicRemove(otherEnd, msgs);
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
      case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS:
        return getAllocatorConfigurationItems();
      case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS:
        return getIncomingInformationFlows();
      case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS:
        return getOutgoingInformationFlows();
      case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS:
        return getInformationFlows();
      case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS:
        return getInvolvedLinks();
      case CsPackage.PHYSICAL_PORT__IS_ABSTRACT:
        return isIsAbstract();
      case CsPackage.PHYSICAL_PORT__IS_STATIC:
        return isIsStatic();
      case CsPackage.PHYSICAL_PORT__VISIBILITY:
        return getVisibility();
      case CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE:
        if (resolve) return getAbstractType();
        return basicGetAbstractType();
      case CsPackage.PHYSICAL_PORT__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case CsPackage.PHYSICAL_PORT__ORDERED:
        return isOrdered();
      case CsPackage.PHYSICAL_PORT__UNIQUE:
        return isUnique();
      case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE:
        return isMinInclusive();
      case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE:
        return isMaxInclusive();
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
        return getOwnedDefaultValue();
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
        return getOwnedMinValue();
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
        return getOwnedMaxValue();
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
        return getOwnedNullValue();
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
        return getOwnedMinCard();
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
        return getOwnedMinLength();
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
        return getOwnedMaxCard();
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
        return getOwnedMaxLength();
      case CsPackage.PHYSICAL_PORT__FINAL:
        return isFinal();
      case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND:
        return getAggregationKind();
      case CsPackage.PHYSICAL_PORT__IS_DERIVED:
        return isIsDerived();
      case CsPackage.PHYSICAL_PORT__IS_READ_ONLY:
        return isIsReadOnly();
      case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY:
        return isIsPartOfKey();
      case CsPackage.PHYSICAL_PORT__ASSOCIATION:
        if (resolve) return getAssociation();
        return basicGetAssociation();
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
        return getOwnedComponentPortAllocations();
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
        return getOwnedPhysicalPortRealizations();
      case CsPackage.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS:
        return getAllocatedComponentPorts();
      case CsPackage.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS:
        return getRealizedPhysicalPorts();
      case CsPackage.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS:
        return getRealizingPhysicalPorts();
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
      case CsPackage.PHYSICAL_PORT__IS_ABSTRACT:
          setIsAbstract((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__IS_STATIC:
          setIsStatic((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__VISIBILITY:
          setVisibility((VisibilityKind)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE:
          setAbstractType((AbstractType)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__ORDERED:
          setOrdered((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__UNIQUE:
          setUnique((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE:
          setMinInclusive((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE:
          setMaxInclusive((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((DataValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
          setOwnedMinValue((DataValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
          setOwnedMaxValue((DataValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
          setOwnedNullValue((DataValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
          setOwnedMinCard((NumericValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
          setOwnedMinLength((NumericValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
          setOwnedMaxCard((NumericValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
          setOwnedMaxLength((NumericValue)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__FINAL:
          setFinal((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND:
          setAggregationKind((AggregationKind)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__IS_DERIVED:
          setIsDerived((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__IS_READ_ONLY:
          setIsReadOnly((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY:
          setIsPartOfKey((Boolean)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
        getOwnedComponentPortAllocations().clear();
        getOwnedComponentPortAllocations().addAll((Collection<? extends ComponentPortAllocation>)newValue);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
        getOwnedPhysicalPortRealizations().clear();
        getOwnedPhysicalPortRealizations().addAll((Collection<? extends PhysicalPortRealization>)newValue);
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
      case CsPackage.PHYSICAL_PORT__IS_ABSTRACT:
        setIsAbstract(IS_ABSTRACT_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__IS_STATIC:
        setIsStatic(IS_STATIC_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE:
        setAbstractType((AbstractType)null);
        return;
      case CsPackage.PHYSICAL_PORT__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE:
        setMinInclusive(MIN_INCLUSIVE_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE:
        setMaxInclusive(MAX_INCLUSIVE_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((DataValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
        setOwnedMinValue((DataValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
        setOwnedMaxValue((DataValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
        setOwnedNullValue((DataValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
        setOwnedMinCard((NumericValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
        setOwnedMinLength((NumericValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
        setOwnedMaxCard((NumericValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
        setOwnedMaxLength((NumericValue)null);
        return;
      case CsPackage.PHYSICAL_PORT__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND:
        setAggregationKind(AGGREGATION_KIND_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__IS_DERIVED:
        setIsDerived(IS_DERIVED_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__IS_READ_ONLY:
        setIsReadOnly(IS_READ_ONLY_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY:
        setIsPartOfKey(IS_PART_OF_KEY_EDEFAULT);
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
        getOwnedComponentPortAllocations().clear();
        return;
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
        getOwnedPhysicalPortRealizations().clear();
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
      case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS:
        return !getAllocatorConfigurationItems().isEmpty();
      case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS:
        return !getIncomingInformationFlows().isEmpty();
      case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS:
        return !getOutgoingInformationFlows().isEmpty();
      case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS:
        return !getInformationFlows().isEmpty();
      case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS:
        return !getInvolvedLinks().isEmpty();
      case CsPackage.PHYSICAL_PORT__IS_ABSTRACT:
        return isAbstract != IS_ABSTRACT_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__IS_STATIC:
        return isStatic != IS_STATIC_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE:
        return abstractType != null;
      case CsPackage.PHYSICAL_PORT__TYPE:
        return basicGetType() != null;
      case CsPackage.PHYSICAL_PORT__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE:
        return minInclusive != MIN_INCLUSIVE_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE:
        return maxInclusive != MAX_INCLUSIVE_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE:
        return ownedMinValue != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE:
        return ownedMaxValue != null;
      case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE:
        return ownedNullValue != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD:
        return ownedMinCard != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH:
        return ownedMinLength != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD:
        return ownedMaxCard != null;
      case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH:
        return ownedMaxLength != null;
      case CsPackage.PHYSICAL_PORT__FINAL:
        return final_ != FINAL_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND:
        return aggregationKind != AGGREGATION_KIND_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__IS_DERIVED:
        return isDerived != IS_DERIVED_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__IS_READ_ONLY:
        return isReadOnly != IS_READ_ONLY_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY:
        return isPartOfKey != IS_PART_OF_KEY_EDEFAULT;
      case CsPackage.PHYSICAL_PORT__ASSOCIATION:
        return basicGetAssociation() != null;
      case CsPackage.PHYSICAL_PORT__OWNED_COMPONENT_PORT_ALLOCATIONS:
        return ownedComponentPortAllocations != null && !ownedComponentPortAllocations.isEmpty();
      case CsPackage.PHYSICAL_PORT__OWNED_PHYSICAL_PORT_REALIZATIONS:
        return ownedPhysicalPortRealizations != null && !ownedPhysicalPortRealizations.isEmpty();
      case CsPackage.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS:
        return !getAllocatedComponentPorts().isEmpty();
      case CsPackage.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS:
        return !getRealizedPhysicalPorts().isEmpty();
      case CsPackage.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS:
        return !getRealizingPhysicalPorts().isEmpty();
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
    if (baseClass == AbstractPhysicalArtifact.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS;
        default: return -1;
      }
    }
    if (baseClass == InformationsExchanger.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS;
        case CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS;
        case CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == AbstractPhysicalLinkEnd.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__INVOLVED_LINKS: return CsPackage.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS;
        default: return -1;
      }
    }
    if (baseClass == Feature.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__IS_ABSTRACT: return CapellacorePackage.FEATURE__IS_ABSTRACT;
        case CsPackage.PHYSICAL_PORT__IS_STATIC: return CapellacorePackage.FEATURE__IS_STATIC;
        case CsPackage.PHYSICAL_PORT__VISIBILITY: return CapellacorePackage.FEATURE__VISIBILITY;
        default: return -1;
      }
    }
    if (baseClass == AbstractTypedElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE: return ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__TYPE: return CapellacorePackage.TYPED_ELEMENT__TYPE;
        default: return -1;
      }
    }
    if (baseClass == MultiplicityElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__ORDERED: return InformationPackage.MULTIPLICITY_ELEMENT__ORDERED;
        case CsPackage.PHYSICAL_PORT__UNIQUE: return InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE;
        case CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE;
        case CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE;
        case CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE;
        case CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE;
        case CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE;
        case CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE;
        case CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD;
        case CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH;
        case CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD;
        case CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__FINAL: return ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL;
        default: return -1;
      }
    }
    if (baseClass == Property.class) {
      switch (derivedFeatureID) {
        case CsPackage.PHYSICAL_PORT__AGGREGATION_KIND: return InformationPackage.PROPERTY__AGGREGATION_KIND;
        case CsPackage.PHYSICAL_PORT__IS_DERIVED: return InformationPackage.PROPERTY__IS_DERIVED;
        case CsPackage.PHYSICAL_PORT__IS_READ_ONLY: return InformationPackage.PROPERTY__IS_READ_ONLY;
        case CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY: return InformationPackage.PROPERTY__IS_PART_OF_KEY;
        case CsPackage.PHYSICAL_PORT__ASSOCIATION: return InformationPackage.PROPERTY__ASSOCIATION;
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
    if (baseClass == AbstractPhysicalArtifact.class) {
      switch (baseFeatureID) {
        case CsPackage.ABSTRACT_PHYSICAL_ARTIFACT__ALLOCATOR_CONFIGURATION_ITEMS: return CsPackage.PHYSICAL_PORT__ALLOCATOR_CONFIGURATION_ITEMS;
        default: return -1;
      }
    }
    if (baseClass == InformationsExchanger.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__INCOMING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__OUTGOING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS: return CsPackage.PHYSICAL_PORT__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == AbstractPhysicalLinkEnd.class) {
      switch (baseFeatureID) {
        case CsPackage.ABSTRACT_PHYSICAL_LINK_END__INVOLVED_LINKS: return CsPackage.PHYSICAL_PORT__INVOLVED_LINKS;
        default: return -1;
      }
    }
    if (baseClass == Feature.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.FEATURE__IS_ABSTRACT: return CsPackage.PHYSICAL_PORT__IS_ABSTRACT;
        case CapellacorePackage.FEATURE__IS_STATIC: return CsPackage.PHYSICAL_PORT__IS_STATIC;
        case CapellacorePackage.FEATURE__VISIBILITY: return CsPackage.PHYSICAL_PORT__VISIBILITY;
        default: return -1;
      }
    }
    if (baseClass == AbstractTypedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE: return CsPackage.PHYSICAL_PORT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.TYPED_ELEMENT__TYPE: return CsPackage.PHYSICAL_PORT__TYPE;
        default: return -1;
      }
    }
    if (baseClass == MultiplicityElement.class) {
      switch (baseFeatureID) {
        case InformationPackage.MULTIPLICITY_ELEMENT__ORDERED: return CsPackage.PHYSICAL_PORT__ORDERED;
        case InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE: return CsPackage.PHYSICAL_PORT__UNIQUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE: return CsPackage.PHYSICAL_PORT__MIN_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE: return CsPackage.PHYSICAL_PORT__MAX_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE: return CsPackage.PHYSICAL_PORT__OWNED_DEFAULT_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE: return CsPackage.PHYSICAL_PORT__OWNED_MIN_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE: return CsPackage.PHYSICAL_PORT__OWNED_MAX_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE: return CsPackage.PHYSICAL_PORT__OWNED_NULL_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD: return CsPackage.PHYSICAL_PORT__OWNED_MIN_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH: return CsPackage.PHYSICAL_PORT__OWNED_MIN_LENGTH;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD: return CsPackage.PHYSICAL_PORT__OWNED_MAX_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH: return CsPackage.PHYSICAL_PORT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL: return CsPackage.PHYSICAL_PORT__FINAL;
        default: return -1;
      }
    }
    if (baseClass == Property.class) {
      switch (baseFeatureID) {
        case InformationPackage.PROPERTY__AGGREGATION_KIND: return CsPackage.PHYSICAL_PORT__AGGREGATION_KIND;
        case InformationPackage.PROPERTY__IS_DERIVED: return CsPackage.PHYSICAL_PORT__IS_DERIVED;
        case InformationPackage.PROPERTY__IS_READ_ONLY: return CsPackage.PHYSICAL_PORT__IS_READ_ONLY;
        case InformationPackage.PROPERTY__IS_PART_OF_KEY: return CsPackage.PHYSICAL_PORT__IS_PART_OF_KEY;
        case InformationPackage.PROPERTY__ASSOCIATION: return CsPackage.PHYSICAL_PORT__ASSOCIATION;
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
    result.append(" (isAbstract: "); //$NON-NLS-1$
    result.append(isAbstract);
    result.append(", isStatic: "); //$NON-NLS-1$
    result.append(isStatic);
    result.append(", visibility: "); //$NON-NLS-1$
    result.append(visibility);
    result.append(", ordered: "); //$NON-NLS-1$
    result.append(ordered);
    result.append(", unique: "); //$NON-NLS-1$
    result.append(unique);
    result.append(", minInclusive: "); //$NON-NLS-1$
    result.append(minInclusive);
    result.append(", maxInclusive: "); //$NON-NLS-1$
    result.append(maxInclusive);
    result.append(", final: "); //$NON-NLS-1$
    result.append(final_);
    result.append(", aggregationKind: "); //$NON-NLS-1$
    result.append(aggregationKind);
    result.append(", isDerived: "); //$NON-NLS-1$
    result.append(isDerived);
    result.append(", isReadOnly: "); //$NON-NLS-1$
    result.append(isReadOnly);
    result.append(", isPartOfKey: "); //$NON-NLS-1$
    result.append(isPartOfKey);
    result.append(')');
    return result.toString();
  }


} //PhysicalPortImpl