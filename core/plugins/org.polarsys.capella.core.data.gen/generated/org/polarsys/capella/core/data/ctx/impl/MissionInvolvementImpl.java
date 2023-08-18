/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.ctx.impl;

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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mission Involvement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl#getInvolved <em>Involved</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl#getSystemComponent <em>System Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionInvolvementImpl#getMission <em>Mission</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MissionInvolvementImpl extends RelationshipImpl implements MissionInvolvement {





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
	protected MissionInvolvementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.MISSION_INVOLVEMENT;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.MISSION_INVOLVEMENT__INVOLVED, oldInvolved, involved));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.MISSION_INVOLVEMENT__INVOLVED, oldInvolved, involved));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SystemComponent getSystemComponent() {

    SystemComponent systemComponent = basicGetSystemComponent();
    return systemComponent != null && systemComponent.eIsProxy() ? (SystemComponent)eResolveProxy((InternalEObject)systemComponent) : systemComponent;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public SystemComponent basicGetSystemComponent() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION_INVOLVEMENT__SYSTEM_COMPONENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION_INVOLVEMENT__SYSTEM_COMPONENT, annotation);
    
    try {
      return (SystemComponent) result;
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

	public Mission getMission() {

    Mission mission = basicGetMission();
    return mission != null && mission.eIsProxy() ? (Mission)eResolveProxy((InternalEObject)mission) : mission;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Mission basicGetMission() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION_INVOLVEMENT__MISSION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION_INVOLVEMENT__MISSION, annotation);
    
    try {
      return (Mission) result;
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
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVER:
        if (resolve) return getInvolver();
        return basicGetInvolver();
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVED:
        if (resolve) return getInvolved();
        return basicGetInvolved();
      case CtxPackage.MISSION_INVOLVEMENT__SYSTEM_COMPONENT:
        if (resolve) return getSystemComponent();
        return basicGetSystemComponent();
      case CtxPackage.MISSION_INVOLVEMENT__MISSION:
        if (resolve) return getMission();
        return basicGetMission();
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
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVED:
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
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVED:
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
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVER:
        return basicGetInvolver() != null;
      case CtxPackage.MISSION_INVOLVEMENT__INVOLVED:
        return involved != null;
      case CtxPackage.MISSION_INVOLVEMENT__SYSTEM_COMPONENT:
        return basicGetSystemComponent() != null;
      case CtxPackage.MISSION_INVOLVEMENT__MISSION:
        return basicGetMission() != null;
    }
    return super.eIsSet(featureID);
  }



} //MissionInvolvementImpl