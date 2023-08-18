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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl#getConstrainedElements <em>Constrained Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.ConstraintImpl#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintImpl extends NamedElementImpl implements Constraint {









	/**
   * The cached value of the '{@link #getConstrainedElements() <em>Constrained Elements</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConstrainedElements()
   * @generated
   * @ordered
   */
	protected EList<ModelElement> constrainedElements;





	/**
   * The cached value of the '{@link #getOwnedSpecification() <em>Owned Specification</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedSpecification()
   * @generated
   * @ordered
   */
	protected ValueSpecification ownedSpecification;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ConstraintImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.CONSTRAINT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ModelElement> getConstrainedElements() {

    if (constrainedElements == null) {
      constrainedElements = new EObjectResolvingEList<ModelElement>(ModelElement.class, this, CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS);
    }
    return constrainedElements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getOwnedSpecification() {

    if (ownedSpecification != null && ownedSpecification.eIsProxy()) {
      InternalEObject oldOwnedSpecification = (InternalEObject)ownedSpecification;
      ownedSpecification = (ValueSpecification)eResolveProxy(oldOwnedSpecification);
      if (ownedSpecification != oldOwnedSpecification) {
        InternalEObject newOwnedSpecification = (InternalEObject)ownedSpecification;
        NotificationChain msgs = oldOwnedSpecification.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, null, null);
        if (newOwnedSpecification.eInternalContainer() == null) {
          msgs = newOwnedSpecification.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, oldOwnedSpecification, ownedSpecification));
      }
    }
    return ownedSpecification;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetOwnedSpecification() {

    return ownedSpecification;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedSpecification(ValueSpecification newOwnedSpecification, NotificationChain msgs) {

    ValueSpecification oldOwnedSpecification = ownedSpecification;
    ownedSpecification = newOwnedSpecification;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, oldOwnedSpecification, newOwnedSpecification);
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
	public void setOwnedSpecification(ValueSpecification newOwnedSpecification) {

    if (newOwnedSpecification != ownedSpecification) {
      NotificationChain msgs = null;
      if (ownedSpecification != null)
        msgs = ((InternalEObject)ownedSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, null, msgs);
      if (newOwnedSpecification != null)
        msgs = ((InternalEObject)newOwnedSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, null, msgs);
      msgs = basicSetOwnedSpecification(newOwnedSpecification, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION, newOwnedSpecification, newOwnedSpecification));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelElement getContext() {

    ModelElement context = basicGetContext();
    return context != null && context.eIsProxy() ? (ModelElement)eResolveProxy((InternalEObject)context) : context;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelElement basicGetContext() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONTEXT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONTEXT, annotation);
    
    try {
      return (ModelElement) result;
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION:
        return basicSetOwnedSpecification(null, msgs);
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
      case CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
        return getConstrainedElements();
      case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION:
        if (resolve) return getOwnedSpecification();
        return basicGetOwnedSpecification();
      case CapellacorePackage.CONSTRAINT__CONTEXT:
        if (resolve) return getContext();
        return basicGetContext();
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
      case CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
        getConstrainedElements().clear();
        getConstrainedElements().addAll((Collection<? extends ModelElement>)newValue);
        return;
      case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION:
          setOwnedSpecification((ValueSpecification)newValue);
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
      case CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
        getConstrainedElements().clear();
        return;
      case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION:
        setOwnedSpecification((ValueSpecification)null);
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
      case CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
        return constrainedElements != null && !constrainedElements.isEmpty();
      case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION:
        return ownedSpecification != null;
      case CapellacorePackage.CONSTRAINT__CONTEXT:
        return basicGetContext() != null;
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
    if (baseClass == AbstractConstraint.class) {
      switch (derivedFeatureID) {
        case CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS: return ModellingcorePackage.ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS;
        case CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION: return ModellingcorePackage.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION;
        case CapellacorePackage.CONSTRAINT__CONTEXT: return ModellingcorePackage.ABSTRACT_CONSTRAINT__CONTEXT;
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
    if (baseClass == AbstractConstraint.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS: return CapellacorePackage.CONSTRAINT__CONSTRAINED_ELEMENTS;
        case ModellingcorePackage.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION: return CapellacorePackage.CONSTRAINT__OWNED_SPECIFICATION;
        case ModellingcorePackage.ABSTRACT_CONSTRAINT__CONTEXT: return CapellacorePackage.CONSTRAINT__CONTEXT;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //ConstraintImpl