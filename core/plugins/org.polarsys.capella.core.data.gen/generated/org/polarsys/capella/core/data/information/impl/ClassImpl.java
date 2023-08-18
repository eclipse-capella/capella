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
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#isIsPrimitive <em>Is Primitive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getKeyParts <em>Key Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getOwnedDataValues <em>Owned Data Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getOwnedInformationRealizations <em>Owned Information Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getRealizedClasses <em>Realized Classes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ClassImpl#getRealizingClasses <em>Realizing Classes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassImpl extends GeneralClassImpl implements org.polarsys.capella.core.data.information.Class {

	/**
   * The default value of the '{@link #isIsPrimitive() <em>Is Primitive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPrimitive()
   * @generated
   * @ordered
   */
	protected static final boolean IS_PRIMITIVE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsPrimitive() <em>Is Primitive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPrimitive()
   * @generated
   * @ordered
   */
	protected boolean isPrimitive = IS_PRIMITIVE_EDEFAULT;





	/**
   * The cached value of the '{@link #getKeyParts() <em>Key Parts</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKeyParts()
   * @generated
   * @ordered
   */
	protected EList<KeyPart> keyParts;





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
   * The cached value of the '{@link #getOwnedDataValues() <em>Owned Data Values</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDataValues()
   * @generated
   * @ordered
   */
	protected EList<DataValue> ownedDataValues;





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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ClassImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.CLASS;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsPrimitive() {

    return isPrimitive;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsPrimitive(boolean newIsPrimitive) {

    boolean oldIsPrimitive = isPrimitive;
    isPrimitive = newIsPrimitive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.CLASS__IS_PRIMITIVE, oldIsPrimitive, isPrimitive));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<KeyPart> getKeyParts() {

    if (keyParts == null) {
      keyParts = new EObjectResolvingEList<KeyPart>(KeyPart.class, this, InformationPackage.CLASS__KEY_PARTS);
    }
    return keyParts;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StateMachine> getOwnedStateMachines() {

    if (ownedStateMachines == null) {
      ownedStateMachines = new EObjectContainmentEList.Resolving<StateMachine>(StateMachine.class, this, InformationPackage.CLASS__OWNED_STATE_MACHINES);
    }
    return ownedStateMachines;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<DataValue> getOwnedDataValues() {

    if (ownedDataValues == null) {
      ownedDataValues = new EObjectContainmentEList.Resolving<DataValue>(DataValue.class, this, InformationPackage.CLASS__OWNED_DATA_VALUES);
    }
    return ownedDataValues;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InformationRealization> getOwnedInformationRealizations() {

    if (ownedInformationRealizations == null) {
      ownedInformationRealizations = new EObjectContainmentEList.Resolving<InformationRealization>(InformationRealization.class, this, InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS);
    }
    return ownedInformationRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<org.polarsys.capella.core.data.information.Class> getRealizedClasses() {


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
    EAnnotation annotation = InformationPackage.Literals.CLASS__REALIZED_CLASSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.CLASS__REALIZED_CLASSES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<org.polarsys.capella.core.data.information.Class> resultAsList = (Collection<org.polarsys.capella.core.data.information.Class>) result;
    return new EcoreEList.UnmodifiableEList<org.polarsys.capella.core.data.information.Class>(this, InformationPackage.Literals.CLASS__REALIZED_CLASSES, resultAsList.size(), resultAsList.toArray());
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

	public EList<org.polarsys.capella.core.data.information.Class> getRealizingClasses() {


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
    EAnnotation annotation = InformationPackage.Literals.CLASS__REALIZING_CLASSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.CLASS__REALIZING_CLASSES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<org.polarsys.capella.core.data.information.Class> resultAsList = (Collection<org.polarsys.capella.core.data.information.Class>) result;
    return new EcoreEList.UnmodifiableEList<org.polarsys.capella.core.data.information.Class>(this, InformationPackage.Literals.CLASS__REALIZING_CLASSES, resultAsList.size(), resultAsList.toArray());
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
      case InformationPackage.CLASS__OWNED_STATE_MACHINES:
        return ((InternalEList<?>)getOwnedStateMachines()).basicRemove(otherEnd, msgs);
      case InformationPackage.CLASS__OWNED_DATA_VALUES:
        return ((InternalEList<?>)getOwnedDataValues()).basicRemove(otherEnd, msgs);
      case InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS:
        return ((InternalEList<?>)getOwnedInformationRealizations()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.CLASS__IS_PRIMITIVE:
        return isIsPrimitive();
      case InformationPackage.CLASS__KEY_PARTS:
        return getKeyParts();
      case InformationPackage.CLASS__OWNED_STATE_MACHINES:
        return getOwnedStateMachines();
      case InformationPackage.CLASS__OWNED_DATA_VALUES:
        return getOwnedDataValues();
      case InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS:
        return getOwnedInformationRealizations();
      case InformationPackage.CLASS__REALIZED_CLASSES:
        return getRealizedClasses();
      case InformationPackage.CLASS__REALIZING_CLASSES:
        return getRealizingClasses();
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
      case InformationPackage.CLASS__IS_PRIMITIVE:
          setIsPrimitive((Boolean)newValue);
        return;
      case InformationPackage.CLASS__KEY_PARTS:
        getKeyParts().clear();
        getKeyParts().addAll((Collection<? extends KeyPart>)newValue);
        return;
      case InformationPackage.CLASS__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        getOwnedStateMachines().addAll((Collection<? extends StateMachine>)newValue);
        return;
      case InformationPackage.CLASS__OWNED_DATA_VALUES:
        getOwnedDataValues().clear();
        getOwnedDataValues().addAll((Collection<? extends DataValue>)newValue);
        return;
      case InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS:
        getOwnedInformationRealizations().clear();
        getOwnedInformationRealizations().addAll((Collection<? extends InformationRealization>)newValue);
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
      case InformationPackage.CLASS__IS_PRIMITIVE:
        setIsPrimitive(IS_PRIMITIVE_EDEFAULT);
        return;
      case InformationPackage.CLASS__KEY_PARTS:
        getKeyParts().clear();
        return;
      case InformationPackage.CLASS__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        return;
      case InformationPackage.CLASS__OWNED_DATA_VALUES:
        getOwnedDataValues().clear();
        return;
      case InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS:
        getOwnedInformationRealizations().clear();
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
      case InformationPackage.CLASS__IS_PRIMITIVE:
        return isPrimitive != IS_PRIMITIVE_EDEFAULT;
      case InformationPackage.CLASS__KEY_PARTS:
        return keyParts != null && !keyParts.isEmpty();
      case InformationPackage.CLASS__OWNED_STATE_MACHINES:
        return ownedStateMachines != null && !ownedStateMachines.isEmpty();
      case InformationPackage.CLASS__OWNED_DATA_VALUES:
        return ownedDataValues != null && !ownedDataValues.isEmpty();
      case InformationPackage.CLASS__OWNED_INFORMATION_REALIZATIONS:
        return ownedInformationRealizations != null && !ownedInformationRealizations.isEmpty();
      case InformationPackage.CLASS__REALIZED_CLASSES:
        return !getRealizedClasses().isEmpty();
      case InformationPackage.CLASS__REALIZING_CLASSES:
        return !getRealizingClasses().isEmpty();
    }
    return super.eIsSet(featureID);
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
    result.append(" (isPrimitive: "); //$NON-NLS-1$
    result.append(isPrimitive);
    result.append(')');
    return result.toString();
  }


} //ClassImpl