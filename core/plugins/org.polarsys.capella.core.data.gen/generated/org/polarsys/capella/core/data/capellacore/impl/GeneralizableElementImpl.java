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

package org.polarsys.capella.core.data.capellacore.impl;

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
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generalizable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#getOwnedGeneralizations <em>Owned Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#getSuperGeneralizations <em>Super Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#getSubGeneralizations <em>Sub Generalizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralizableElementImpl#getSub <em>Sub</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class GeneralizableElementImpl extends TypeImpl implements GeneralizableElement {

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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected GeneralizableElementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.GENERALIZABLE_ELEMENT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT, oldAbstract, abstract_));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Generalization> getOwnedGeneralizations() {

    if (ownedGeneralizations == null) {
      ownedGeneralizations = new EObjectContainmentEList.Resolving<Generalization>(Generalization.class, this, CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS);
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS:
        return ((InternalEList<?>)getOwnedGeneralizations()).basicRemove(otherEnd, msgs);
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
      case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT:
        return isAbstract();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS:
        return getOwnedGeneralizations();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS:
        return getSuperGeneralizations();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS:
        return getSubGeneralizations();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER:
        return getSuper();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB:
        return getSub();
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
      case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT:
          setAbstract((Boolean)newValue);
        return;
      case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
        getOwnedGeneralizations().addAll((Collection<? extends Generalization>)newValue);
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
      case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS:
        getOwnedGeneralizations().clear();
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
      case CapellacorePackage.GENERALIZABLE_ELEMENT__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case CapellacorePackage.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS:
        return ownedGeneralizations != null && !ownedGeneralizations.isEmpty();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS:
        return !getSuperGeneralizations().isEmpty();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS:
        return !getSubGeneralizations().isEmpty();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUPER:
        return !getSuper().isEmpty();
      case CapellacorePackage.GENERALIZABLE_ELEMENT__SUB:
        return !getSub().isEmpty();
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
    result.append(" (abstract: "); //$NON-NLS-1$
    result.append(abstract_);
    result.append(')');
    return result.toString();
  }


} //GeneralizableElementImpl