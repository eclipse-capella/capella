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
package org.polarsys.capella.common.data.modellingcore.impl;

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
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.kitalpha.emde.model.impl.ExtensibleElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Model Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl#getSid <em>Sid</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl#getOwnedMigratedElements <em>Owned Migrated Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelElementImpl extends ExtensibleElementImpl implements ModelElement {

	/**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
	protected static final String ID_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
	protected String id = ID_EDEFAULT;





	/**
   * The default value of the '{@link #getSid() <em>Sid</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSid()
   * @generated
   * @ordered
   */
	protected static final String SID_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getSid() <em>Sid</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSid()
   * @generated
   * @ordered
   */
	protected String sid = SID_EDEFAULT;









	/**
   * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConstraints()
   * @generated
   * @ordered
   */
	protected EList<AbstractConstraint> ownedConstraints;




	/**
   * The cached value of the '{@link #getOwnedMigratedElements() <em>Owned Migrated Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMigratedElements()
   * @generated
   * @ordered
   */
	protected EList<ModelElement> ownedMigratedElements;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelElementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ModellingcorePackage.Literals.MODEL_ELEMENT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public String getId() {

    return id;
  }

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setId(String newId) {

    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.MODEL_ELEMENT__ID, oldId, id));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getSid() {

    return sid;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSid(String newSid) {

    String oldSid = sid;
    sid = newSid;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.MODEL_ELEMENT__SID, oldSid, sid));

  }






	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractConstraint> getConstraints() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.MODEL_ELEMENT__CONSTRAINTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.MODEL_ELEMENT__CONSTRAINTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractConstraint> resultAsList = (Collection<AbstractConstraint>) result;
    return new EcoreEList.UnmodifiableEList<AbstractConstraint>(this, ModellingcorePackage.Literals.MODEL_ELEMENT__CONSTRAINTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractConstraint> getOwnedConstraints() {

    if (ownedConstraints == null) {
      ownedConstraints = new EObjectContainmentEList.Resolving<AbstractConstraint>(AbstractConstraint.class, this, ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS);
    }
    return ownedConstraints;
  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ModelElement> getOwnedMigratedElements() {

    if (ownedMigratedElements == null) {
      ownedMigratedElements = new EObjectContainmentEList.Resolving<ModelElement>(ModelElement.class, this, ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS);
    }
    return ownedMigratedElements;
  }

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void destroy() {
    org.eclipse.emf.ecore.util.EcoreUtil.delete(this);
  }




	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	@Override
	public String getFullLabel() {
    return org.polarsys.capella.common.model.label.LabelRetriever.getFullLabel(this);
  }




	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	@Override
	public String getLabel() {
    return org.polarsys.capella.common.model.label.LabelRetriever.getLabel(this);
  }




	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */

	@Override
	public boolean hasUnnamedLabel() {
    return org.polarsys.capella.common.model.label.LabelRetriever.UNNAMED_ELEMENT.equals(this.getLabel());
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS:
        return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS:
        return ((InternalEList<?>)getOwnedMigratedElements()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ModellingcorePackage.MODEL_ELEMENT__ID:
        return getId();
      case ModellingcorePackage.MODEL_ELEMENT__SID:
        return getSid();
      case ModellingcorePackage.MODEL_ELEMENT__CONSTRAINTS:
        return getConstraints();
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS:
        return getOwnedConstraints();
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS:
        return getOwnedMigratedElements();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ModellingcorePackage.MODEL_ELEMENT__ID:
          setId((String)newValue);
        return;
      case ModellingcorePackage.MODEL_ELEMENT__SID:
          setSid((String)newValue);
        return;
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS:
        getOwnedConstraints().clear();
        getOwnedConstraints().addAll((Collection<? extends AbstractConstraint>)newValue);
        return;
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS:
        getOwnedMigratedElements().clear();
        getOwnedMigratedElements().addAll((Collection<? extends ModelElement>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case ModellingcorePackage.MODEL_ELEMENT__ID:
        setId(ID_EDEFAULT);
        return;
      case ModellingcorePackage.MODEL_ELEMENT__SID:
        setSid(SID_EDEFAULT);
        return;
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS:
        getOwnedConstraints().clear();
        return;
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS:
        getOwnedMigratedElements().clear();
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case ModellingcorePackage.MODEL_ELEMENT__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case ModellingcorePackage.MODEL_ELEMENT__SID:
        return SID_EDEFAULT == null ? sid != null : !SID_EDEFAULT.equals(sid);
      case ModellingcorePackage.MODEL_ELEMENT__CONSTRAINTS:
        return !getConstraints().isEmpty();
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_CONSTRAINTS:
        return ownedConstraints != null && !ownedConstraints.isEmpty();
      case ModellingcorePackage.MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS:
        return ownedMigratedElements != null && !ownedMigratedElements.isEmpty();
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (id: "); //$NON-NLS-1$
    result.append(id);
    result.append(", sid: "); //$NON-NLS-1$
    result.append(sid);
    result.append(')');
    return result.toString();
  }
} //ModelElementImpl
