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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Function Abstract Capability Involvement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl#getInvolved <em>Involved</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractFunctionAbstractCapabilityInvolvementImpl#getFunction <em>Function</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractFunctionAbstractCapabilityInvolvementImpl extends RelationshipImpl implements AbstractFunctionAbstractCapabilityInvolvement {

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
	protected AbstractFunctionAbstractCapabilityInvolvementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED, oldInvolved, involved));
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
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED, oldInvolved, involved));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability getCapability() {

    AbstractCapability capability = basicGetCapability();
    return capability != null && capability.eIsProxy() ? (AbstractCapability)eResolveProxy((InternalEObject)capability) : capability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability basicGetCapability() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY, annotation);
    
    try {
      return (AbstractCapability) result;
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

	public AbstractFunction getFunction() {

    AbstractFunction function = basicGetFunction();
    return function != null && function.eIsProxy() ? (AbstractFunction)eResolveProxy((InternalEObject)function) : function;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractFunction basicGetFunction() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION, annotation);
    
    try {
      return (AbstractFunction) result;
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
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVER:
        if (resolve) return getInvolver();
        return basicGetInvolver();
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED:
        if (resolve) return getInvolved();
        return basicGetInvolved();
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY:
        if (resolve) return getCapability();
        return basicGetCapability();
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION:
        if (resolve) return getFunction();
        return basicGetFunction();
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
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED:
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
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED:
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
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVER:
        return basicGetInvolver() != null;
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__INVOLVED:
        return involved != null;
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY:
        return basicGetCapability() != null;
      case InteractionPackage.ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION:
        return basicGetFunction() != null;
    }
    return super.eIsSet(featureID);
  }



} //AbstractFunctionAbstractCapabilityInvolvementImpl