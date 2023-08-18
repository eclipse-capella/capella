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

package org.polarsys.capella.core.data.interaction.impl;

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
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl#getAbstractEnds <em>Abstract Ends</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InstanceRoleImpl#getRepresentedInstance <em>Represented Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InstanceRoleImpl extends NamedElementImpl implements InstanceRole {





	/**
   * The cached value of the '{@link #getRepresentedInstance() <em>Represented Instance</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRepresentedInstance()
   * @generated
   * @ordered
   */
	protected AbstractInstance representedInstance;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InstanceRoleImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.INSTANCE_ROLE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractEnd> getAbstractEnds() {


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
    EAnnotation annotation = InteractionPackage.Literals.INSTANCE_ROLE__ABSTRACT_ENDS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.INSTANCE_ROLE__ABSTRACT_ENDS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractEnd> resultAsList = (Collection<AbstractEnd>) result;
    return new EcoreEList.UnmodifiableEList<AbstractEnd>(this, InteractionPackage.Literals.INSTANCE_ROLE__ABSTRACT_ENDS, resultAsList.size(), resultAsList.toArray());
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

	public AbstractInstance getRepresentedInstance() {

    if (representedInstance != null && representedInstance.eIsProxy()) {
      InternalEObject oldRepresentedInstance = (InternalEObject)representedInstance;
      representedInstance = (AbstractInstance)eResolveProxy(oldRepresentedInstance);
      if (representedInstance != oldRepresentedInstance) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE, oldRepresentedInstance, representedInstance));
      }
    }
    return representedInstance;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractInstance basicGetRepresentedInstance() {

    return representedInstance;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setRepresentedInstance(AbstractInstance newRepresentedInstance) {

    AbstractInstance oldRepresentedInstance = representedInstance;
    representedInstance = newRepresentedInstance;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE, oldRepresentedInstance, representedInstance));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InteractionPackage.INSTANCE_ROLE__ABSTRACT_ENDS:
        return getAbstractEnds();
      case InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE:
        if (resolve) return getRepresentedInstance();
        return basicGetRepresentedInstance();
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
      case InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE:
          setRepresentedInstance((AbstractInstance)newValue);
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
      case InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE:
        setRepresentedInstance((AbstractInstance)null);
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
      case InteractionPackage.INSTANCE_ROLE__ABSTRACT_ENDS:
        return !getAbstractEnds().isEmpty();
      case InteractionPackage.INSTANCE_ROLE__REPRESENTED_INSTANCE:
        return representedInstance != null;
    }
    return super.eIsSet(featureID);
  }



} //InstanceRoleImpl