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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.FeatureImpl;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getAbstractTypedElements <em>Abstract Typed Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getInvokingSequenceMessages <em>Invoking Sequence Messages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getAllocatingOperations <em>Allocating Operations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getAllocatedOperations <em>Allocated Operations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getOwnedOperationAllocation <em>Owned Operation Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getOwnedExchangeItemRealizations <em>Owned Exchange Item Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.OperationImpl#getRealizedExchangeItems <em>Realized Exchange Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class OperationImpl extends FeatureImpl implements Operation {









	/**
   * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedParameters()
   * @generated
   * @ordered
   */
	protected EList<Parameter> ownedParameters;













	/**
   * The cached value of the '{@link #getOwnedOperationAllocation() <em>Owned Operation Allocation</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedOperationAllocation()
   * @generated
   * @ordered
   */
	protected EList<OperationAllocation> ownedOperationAllocation;





	/**
   * The cached value of the '{@link #getOwnedExchangeItemRealizations() <em>Owned Exchange Item Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedExchangeItemRealizations()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItemRealization> ownedExchangeItemRealizations;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.OPERATION;
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

	public EList<SequenceMessage> getInvokingSequenceMessages() {


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
    EAnnotation annotation = InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SequenceMessage> resultAsList = (Collection<SequenceMessage>) result;
    return new EcoreEList.UnmodifiableEList<SequenceMessage>(this, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Parameter> getOwnedParameters() {

    if (ownedParameters == null) {
      ownedParameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, InformationPackage.OPERATION__OWNED_PARAMETERS);
    }
    return ownedParameters;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Operation> getAllocatingOperations() {


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
    EAnnotation annotation = InformationPackage.Literals.OPERATION__ALLOCATING_OPERATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.OPERATION__ALLOCATING_OPERATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Operation> resultAsList = (Collection<Operation>) result;
    return new EcoreEList.UnmodifiableEList<Operation>(this, InformationPackage.Literals.OPERATION__ALLOCATING_OPERATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Operation> getAllocatedOperations() {


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
    EAnnotation annotation = InformationPackage.Literals.OPERATION__ALLOCATED_OPERATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.OPERATION__ALLOCATED_OPERATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Operation> resultAsList = (Collection<Operation>) result;
    return new EcoreEList.UnmodifiableEList<Operation>(this, InformationPackage.Literals.OPERATION__ALLOCATED_OPERATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationAllocation> getOwnedOperationAllocation() {

    if (ownedOperationAllocation == null) {
      ownedOperationAllocation = new EObjectContainmentEList.Resolving<OperationAllocation>(OperationAllocation.class, this, InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION);
    }
    return ownedOperationAllocation;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeItemRealization> getOwnedExchangeItemRealizations() {

    if (ownedExchangeItemRealizations == null) {
      ownedExchangeItemRealizations = new EObjectContainmentEList.Resolving<ExchangeItemRealization>(ExchangeItemRealization.class, this, InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS);
    }
    return ownedExchangeItemRealizations;
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
    EAnnotation annotation = InformationPackage.Literals.OPERATION__REALIZED_EXCHANGE_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.OPERATION__REALIZED_EXCHANGE_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ExchangeItem> resultAsList = (Collection<ExchangeItem>) result;
    return new EcoreEList.UnmodifiableEList<ExchangeItem>(this, InformationPackage.Literals.OPERATION__REALIZED_EXCHANGE_ITEMS, resultAsList.size(), resultAsList.toArray());
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
      case InformationPackage.OPERATION__OWNED_PARAMETERS:
        return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
      case InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION:
        return ((InternalEList<?>)getOwnedOperationAllocation()).basicRemove(otherEnd, msgs);
      case InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS:
        return ((InternalEList<?>)getOwnedExchangeItemRealizations()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.OPERATION__ABSTRACT_TYPED_ELEMENTS:
        return getAbstractTypedElements();
      case InformationPackage.OPERATION__INVOKING_SEQUENCE_MESSAGES:
        return getInvokingSequenceMessages();
      case InformationPackage.OPERATION__OWNED_PARAMETERS:
        return getOwnedParameters();
      case InformationPackage.OPERATION__ALLOCATING_OPERATIONS:
        return getAllocatingOperations();
      case InformationPackage.OPERATION__ALLOCATED_OPERATIONS:
        return getAllocatedOperations();
      case InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION:
        return getOwnedOperationAllocation();
      case InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS:
        return getOwnedExchangeItemRealizations();
      case InformationPackage.OPERATION__REALIZED_EXCHANGE_ITEMS:
        return getRealizedExchangeItems();
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
      case InformationPackage.OPERATION__OWNED_PARAMETERS:
        getOwnedParameters().clear();
        getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
        return;
      case InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION:
        getOwnedOperationAllocation().clear();
        getOwnedOperationAllocation().addAll((Collection<? extends OperationAllocation>)newValue);
        return;
      case InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS:
        getOwnedExchangeItemRealizations().clear();
        getOwnedExchangeItemRealizations().addAll((Collection<? extends ExchangeItemRealization>)newValue);
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
      case InformationPackage.OPERATION__OWNED_PARAMETERS:
        getOwnedParameters().clear();
        return;
      case InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION:
        getOwnedOperationAllocation().clear();
        return;
      case InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS:
        getOwnedExchangeItemRealizations().clear();
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
      case InformationPackage.OPERATION__ABSTRACT_TYPED_ELEMENTS:
        return !getAbstractTypedElements().isEmpty();
      case InformationPackage.OPERATION__INVOKING_SEQUENCE_MESSAGES:
        return !getInvokingSequenceMessages().isEmpty();
      case InformationPackage.OPERATION__OWNED_PARAMETERS:
        return ownedParameters != null && !ownedParameters.isEmpty();
      case InformationPackage.OPERATION__ALLOCATING_OPERATIONS:
        return !getAllocatingOperations().isEmpty();
      case InformationPackage.OPERATION__ALLOCATED_OPERATIONS:
        return !getAllocatedOperations().isEmpty();
      case InformationPackage.OPERATION__OWNED_OPERATION_ALLOCATION:
        return ownedOperationAllocation != null && !ownedOperationAllocation.isEmpty();
      case InformationPackage.OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS:
        return ownedExchangeItemRealizations != null && !ownedExchangeItemRealizations.isEmpty();
      case InformationPackage.OPERATION__REALIZED_EXCHANGE_ITEMS:
        return !getRealizedExchangeItems().isEmpty();
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
    if (baseClass == AbstractType.class) {
      switch (derivedFeatureID) {
        case InformationPackage.OPERATION__ABSTRACT_TYPED_ELEMENTS: return ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractEvent.class) {
      switch (derivedFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractEventOperation.class) {
      switch (derivedFeatureID) {
        case InformationPackage.OPERATION__INVOKING_SEQUENCE_MESSAGES: return InformationPackage.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES;
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
    if (baseClass == AbstractType.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS: return InformationPackage.OPERATION__ABSTRACT_TYPED_ELEMENTS;
        default: return -1;
      }
    }
    if (baseClass == AbstractEvent.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    if (baseClass == AbstractEventOperation.class) {
      switch (baseFeatureID) {
        case InformationPackage.ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES: return InformationPackage.OPERATION__INVOKING_SEQUENCE_MESSAGES;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //OperationImpl