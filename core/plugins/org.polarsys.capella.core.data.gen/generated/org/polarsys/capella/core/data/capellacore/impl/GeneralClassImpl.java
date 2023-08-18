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
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.data.information.Operation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>General Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl#getContainedOperations <em>Contained Operations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl#getNestedGeneralClasses <em>Nested General Classes</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class GeneralClassImpl extends ClassifierImpl implements GeneralClass {

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
   * The cached value of the '{@link #getNestedGeneralClasses() <em>Nested General Classes</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNestedGeneralClasses()
   * @generated
   * @ordered
   */
	protected EList<GeneralClass> nestedGeneralClasses;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected GeneralClassImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.GENERAL_CLASS;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.GENERAL_CLASS__FINAL, oldFinal, final_));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.GENERAL_CLASS__VISIBILITY, oldVisibility, visibility));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Operation> getContainedOperations() {


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
    EAnnotation annotation = CapellacorePackage.Literals.GENERAL_CLASS__CONTAINED_OPERATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.GENERAL_CLASS__CONTAINED_OPERATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Operation> resultAsList = (Collection<Operation>) result;
    return new EcoreEList.UnmodifiableEList<Operation>(this, CapellacorePackage.Literals.GENERAL_CLASS__CONTAINED_OPERATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<GeneralClass> getNestedGeneralClasses() {

    if (nestedGeneralClasses == null) {
      nestedGeneralClasses = new EObjectContainmentEList<GeneralClass>(GeneralClass.class, this, CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES);
    }
    return nestedGeneralClasses;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES:
        return ((InternalEList<?>)getNestedGeneralClasses()).basicRemove(otherEnd, msgs);
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
      case CapellacorePackage.GENERAL_CLASS__FINAL:
        return isFinal();
      case CapellacorePackage.GENERAL_CLASS__VISIBILITY:
        return getVisibility();
      case CapellacorePackage.GENERAL_CLASS__CONTAINED_OPERATIONS:
        return getContainedOperations();
      case CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES:
        return getNestedGeneralClasses();
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
      case CapellacorePackage.GENERAL_CLASS__FINAL:
          setFinal((Boolean)newValue);
        return;
      case CapellacorePackage.GENERAL_CLASS__VISIBILITY:
          setVisibility((VisibilityKind)newValue);
        return;
      case CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES:
        getNestedGeneralClasses().clear();
        getNestedGeneralClasses().addAll((Collection<? extends GeneralClass>)newValue);
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
      case CapellacorePackage.GENERAL_CLASS__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case CapellacorePackage.GENERAL_CLASS__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES:
        getNestedGeneralClasses().clear();
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
      case CapellacorePackage.GENERAL_CLASS__FINAL:
        return final_ != FINAL_EDEFAULT;
      case CapellacorePackage.GENERAL_CLASS__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case CapellacorePackage.GENERAL_CLASS__CONTAINED_OPERATIONS:
        return !getContainedOperations().isEmpty();
      case CapellacorePackage.GENERAL_CLASS__NESTED_GENERAL_CLASSES:
        return nestedGeneralClasses != null && !nestedGeneralClasses.isEmpty();
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
    if (baseClass == FinalizableElement.class) {
      switch (derivedFeatureID) {
        case CapellacorePackage.GENERAL_CLASS__FINAL: return ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL;
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
    if (baseClass == FinalizableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL: return CapellacorePackage.GENERAL_CLASS__FINAL;
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
    result.append(", visibility: "); //$NON-NLS-1$
    result.append(visibility);
    result.append(')');
    return result.toString();
  }


} //GeneralClassImpl