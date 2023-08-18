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
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl#getReferencedScenario <em>Referenced Scenario</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.InteractionUseImpl#getActualGates <em>Actual Gates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionUseImpl extends AbstractFragmentImpl implements InteractionUse {

	/**
   * The cached value of the '{@link #getReferencedScenario() <em>Referenced Scenario</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedScenario()
   * @generated
   * @ordered
   */
	protected Scenario referencedScenario;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InteractionUseImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.INTERACTION_USE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Scenario getReferencedScenario() {

    if (referencedScenario != null && referencedScenario.eIsProxy()) {
      InternalEObject oldReferencedScenario = (InternalEObject)referencedScenario;
      referencedScenario = (Scenario)eResolveProxy(oldReferencedScenario);
      if (referencedScenario != oldReferencedScenario) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO, oldReferencedScenario, referencedScenario));
      }
    }
    return referencedScenario;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Scenario basicGetReferencedScenario() {

    return referencedScenario;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setReferencedScenario(Scenario newReferencedScenario) {

    Scenario oldReferencedScenario = referencedScenario;
    referencedScenario = newReferencedScenario;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO, oldReferencedScenario, referencedScenario));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Gate> getActualGates() {


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
    EAnnotation annotation = InteractionPackage.Literals.INTERACTION_USE__ACTUAL_GATES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.INTERACTION_USE__ACTUAL_GATES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Gate> resultAsList = (Collection<Gate>) result;
    return new EcoreEList.UnmodifiableEList<Gate>(this, InteractionPackage.Literals.INTERACTION_USE__ACTUAL_GATES, resultAsList.size(), resultAsList.toArray());
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
      case InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO:
        if (resolve) return getReferencedScenario();
        return basicGetReferencedScenario();
      case InteractionPackage.INTERACTION_USE__ACTUAL_GATES:
        return getActualGates();
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
      case InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO:
          setReferencedScenario((Scenario)newValue);
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
      case InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO:
        setReferencedScenario((Scenario)null);
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
      case InteractionPackage.INTERACTION_USE__REFERENCED_SCENARIO:
        return referencedScenario != null;
      case InteractionPackage.INTERACTION_USE__ACTUAL_GATES:
        return !getActualGates().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //InteractionUseImpl