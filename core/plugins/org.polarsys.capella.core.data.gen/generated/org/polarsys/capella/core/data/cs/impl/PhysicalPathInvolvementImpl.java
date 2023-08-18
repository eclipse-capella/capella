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

package org.polarsys.capella.core.data.cs.impl;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Path Involvement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getInvolver <em>Involver</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getInvolved <em>Involved</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getNextInvolvements <em>Next Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getPreviousInvolvements <em>Previous Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getInvolvedElement <em>Involved Element</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalPathInvolvementImpl#getInvolvedComponent <em>Involved Component</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalPathInvolvementImpl extends RelationshipImpl implements PhysicalPathInvolvement {

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
   * The cached value of the '{@link #getNextInvolvements() <em>Next Involvements</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getNextInvolvements()
   * @generated
   * @ordered
   */
	protected EList<PhysicalPathInvolvement> nextInvolvements;
















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalPathInvolvementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED, oldInvolved, involved));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED, oldInvolved, involved));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalPathInvolvement> getNextInvolvements() {

    if (nextInvolvements == null) {
      nextInvolvements = new EObjectResolvingEList<PhysicalPathInvolvement>(PhysicalPathInvolvement.class, this, CsPackage.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS);
    }
    return nextInvolvements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalPathInvolvement> getPreviousInvolvements() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalPathInvolvement> resultAsList = (Collection<PhysicalPathInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalPathInvolvement>(this, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public AbstractPathInvolvedElement getInvolvedElement() {

    AbstractPathInvolvedElement involvedElement = basicGetInvolvedElement();
    return involvedElement != null && involvedElement.eIsProxy() ? (AbstractPathInvolvedElement)eResolveProxy((InternalEObject)involvedElement) : involvedElement;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractPathInvolvedElement basicGetInvolvedElement() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT, annotation);
    
    try {
      return (AbstractPathInvolvedElement) result;
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

	public Component getInvolvedComponent() {

    Component involvedComponent = basicGetInvolvedComponent();
    return involvedComponent != null && involvedComponent.eIsProxy() ? (Component)eResolveProxy((InternalEObject)involvedComponent) : involvedComponent;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Component basicGetInvolvedComponent() {


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
    EAnnotation annotation = CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT, annotation);
    
    try {
      return (Component) result;
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
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVER:
        if (resolve) return getInvolver();
        return basicGetInvolver();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED:
        if (resolve) return getInvolved();
        return basicGetInvolved();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS:
        return getNextInvolvements();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS:
        return getPreviousInvolvements();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT:
        if (resolve) return getInvolvedElement();
        return basicGetInvolvedElement();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT:
        if (resolve) return getInvolvedComponent();
        return basicGetInvolvedComponent();
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
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED:
          setInvolved((InvolvedElement)newValue);
        return;
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS:
        getNextInvolvements().clear();
        getNextInvolvements().addAll((Collection<? extends PhysicalPathInvolvement>)newValue);
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
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED:
        setInvolved((InvolvedElement)null);
        return;
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS:
        getNextInvolvements().clear();
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
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVER:
        return basicGetInvolver() != null;
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED:
        return involved != null;
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS:
        return nextInvolvements != null && !nextInvolvements.isEmpty();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__PREVIOUS_INVOLVEMENTS:
        return !getPreviousInvolvements().isEmpty();
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_ELEMENT:
        return basicGetInvolvedElement() != null;
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT__INVOLVED_COMPONENT:
        return basicGetInvolvedComponent() != null;
    }
    return super.eIsSet(featureID);
  }



} //PhysicalPathInvolvementImpl