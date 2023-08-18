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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.UnionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.UnionImpl#getDiscriminant <em>Discriminant</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.UnionImpl#getDefaultProperty <em>Default Property</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.UnionImpl#getContainedUnionProperties <em>Contained Union Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionImpl extends ClassImpl implements Union {

	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final UnionKind KIND_EDEFAULT = UnionKind.UNION;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected UnionKind kind = KIND_EDEFAULT;





	/**
   * The cached value of the '{@link #getDiscriminant() <em>Discriminant</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDiscriminant()
   * @generated
   * @ordered
   */
	protected UnionProperty discriminant;





	/**
   * The cached value of the '{@link #getDefaultProperty() <em>Default Property</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDefaultProperty()
   * @generated
   * @ordered
   */
	protected UnionProperty defaultProperty;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected UnionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.UNION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnionKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(UnionKind newKind) {

    UnionKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.UNION__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnionProperty getDiscriminant() {

    if (discriminant != null && discriminant.eIsProxy()) {
      InternalEObject oldDiscriminant = (InternalEObject)discriminant;
      discriminant = (UnionProperty)eResolveProxy(oldDiscriminant);
      if (discriminant != oldDiscriminant) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.UNION__DISCRIMINANT, oldDiscriminant, discriminant));
      }
    }
    return discriminant;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnionProperty basicGetDiscriminant() {

    return discriminant;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDiscriminant(UnionProperty newDiscriminant) {

    UnionProperty oldDiscriminant = discriminant;
    discriminant = newDiscriminant;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.UNION__DISCRIMINANT, oldDiscriminant, discriminant));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnionProperty getDefaultProperty() {

    if (defaultProperty != null && defaultProperty.eIsProxy()) {
      InternalEObject oldDefaultProperty = (InternalEObject)defaultProperty;
      defaultProperty = (UnionProperty)eResolveProxy(oldDefaultProperty);
      if (defaultProperty != oldDefaultProperty) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.UNION__DEFAULT_PROPERTY, oldDefaultProperty, defaultProperty));
      }
    }
    return defaultProperty;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public UnionProperty basicGetDefaultProperty() {

    return defaultProperty;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDefaultProperty(UnionProperty newDefaultProperty) {

    UnionProperty oldDefaultProperty = defaultProperty;
    defaultProperty = newDefaultProperty;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.UNION__DEFAULT_PROPERTY, oldDefaultProperty, defaultProperty));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<UnionProperty> getContainedUnionProperties() {


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
    EAnnotation annotation = InformationPackage.Literals.UNION__CONTAINED_UNION_PROPERTIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.UNION__CONTAINED_UNION_PROPERTIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<UnionProperty> resultAsList = (Collection<UnionProperty>) result;
    return new EcoreEList.UnmodifiableEList<UnionProperty>(this, InformationPackage.Literals.UNION__CONTAINED_UNION_PROPERTIES, resultAsList.size(), resultAsList.toArray());
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InformationPackage.UNION__KIND:
        return getKind();
      case InformationPackage.UNION__DISCRIMINANT:
        if (resolve) return getDiscriminant();
        return basicGetDiscriminant();
      case InformationPackage.UNION__DEFAULT_PROPERTY:
        if (resolve) return getDefaultProperty();
        return basicGetDefaultProperty();
      case InformationPackage.UNION__CONTAINED_UNION_PROPERTIES:
        return getContainedUnionProperties();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case InformationPackage.UNION__KIND:
          setKind((UnionKind)newValue);
        return;
      case InformationPackage.UNION__DISCRIMINANT:
          setDiscriminant((UnionProperty)newValue);
        return;
      case InformationPackage.UNION__DEFAULT_PROPERTY:
          setDefaultProperty((UnionProperty)newValue);
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
      case InformationPackage.UNION__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case InformationPackage.UNION__DISCRIMINANT:
        setDiscriminant((UnionProperty)null);
        return;
      case InformationPackage.UNION__DEFAULT_PROPERTY:
        setDefaultProperty((UnionProperty)null);
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
      case InformationPackage.UNION__KIND:
        return kind != KIND_EDEFAULT;
      case InformationPackage.UNION__DISCRIMINANT:
        return discriminant != null;
      case InformationPackage.UNION__DEFAULT_PROPERTY:
        return defaultProperty != null;
      case InformationPackage.UNION__CONTAINED_UNION_PROPERTIES:
        return !getContainedUnionProperties().isEmpty();
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
    result.append(" (kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(')');
    return result.toString();
  }


} //UnionImpl