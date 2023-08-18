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

package org.polarsys.capella.core.data.capellacommon.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Realization Involvement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl#getInvolved <em>Involved</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.CapabilityRealizationInvolvementImpl#getInvolvedCapabilityRealizationInvolvedElement <em>Involved Capability Realization Involved Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityRealizationInvolvementImpl extends RelationshipImpl implements CapabilityRealizationInvolvement {

	/**
   * The cached value of the '{@link #getInvolved() <em>Involved</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInvolved()
   * @generated
   * @ordered
   */
	protected InvolvedElement involved;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityRealizationInvolvementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InvolverElement getInvolver() {

    InvolverElement involver = basicGetInvolver();
    return involver != null && involver.eIsProxy() ? (InvolverElement)eResolveProxy((InternalEObject)involver) : involver;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InvolverElement basicGetInvolver() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVEMENT__INVOLVER.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, annotation);
    
    try {
      return (InvolverElement) result;
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

	public InvolvedElement getInvolved() {

    if (involved != null && involved.eIsProxy()) {
      InternalEObject oldInvolved = (InternalEObject)involved;
      involved = (InvolvedElement)eResolveProxy(oldInvolved);
      if (involved != oldInvolved) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED, oldInvolved, involved));
      }
    }
    return involved;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InvolvedElement basicGetInvolved() {

    return involved;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setInvolved(InvolvedElement newInvolved) {

    InvolvedElement oldInvolved = involved;
    involved = newInvolved;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED, oldInvolved, involved));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CapabilityRealizationInvolvedElement getInvolvedCapabilityRealizationInvolvedElement() {

    CapabilityRealizationInvolvedElement involvedCapabilityRealizationInvolvedElement = basicGetInvolvedCapabilityRealizationInvolvedElement();
    return involvedCapabilityRealizationInvolvedElement != null && involvedCapabilityRealizationInvolvedElement.eIsProxy() ? (CapabilityRealizationInvolvedElement)eResolveProxy((InternalEObject)involvedCapabilityRealizationInvolvedElement) : involvedCapabilityRealizationInvolvedElement;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CapabilityRealizationInvolvedElement basicGetInvolvedCapabilityRealizationInvolvedElement() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT, annotation);
    
    try {
      return (CapabilityRealizationInvolvedElement) result;
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVER:
        if (resolve) return getInvolver();
        return basicGetInvolver();
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED:
        if (resolve) return getInvolved();
        return basicGetInvolved();
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT:
        if (resolve) return getInvolvedCapabilityRealizationInvolvedElement();
        return basicGetInvolvedCapabilityRealizationInvolvedElement();
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
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED:
          setInvolved((InvolvedElement)newValue);
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
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED:
        setInvolved((InvolvedElement)null);
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
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVER:
        return basicGetInvolver() != null;
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED:
        return involved != null;
      case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVEMENT__INVOLVED_CAPABILITY_REALIZATION_INVOLVED_ELEMENT:
        return basicGetInvolvedCapabilityRealizationInvolvedElement() != null;
    }
    return super.eIsSet(featureID);
  }



} //CapabilityRealizationInvolvementImpl