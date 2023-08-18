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
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.AllocationImpl;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario Realization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl#getRealizedScenario <em>Realized Scenario</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.ScenarioRealizationImpl#getRealizingScenario <em>Realizing Scenario</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScenarioRealizationImpl extends AllocationImpl implements ScenarioRealization {








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ScenarioRealizationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.SCENARIO_REALIZATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Scenario getRealizedScenario() {

    Scenario realizedScenario = basicGetRealizedScenario();
    return realizedScenario != null && realizedScenario.eIsProxy() ? (Scenario)eResolveProxy((InternalEObject)realizedScenario) : realizedScenario;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Scenario basicGetRealizedScenario() {


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
    EAnnotation annotation = InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZED_SCENARIO.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZED_SCENARIO, annotation);
    
    try {
      return (Scenario) result;
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

	public Scenario getRealizingScenario() {

    Scenario realizingScenario = basicGetRealizingScenario();
    return realizingScenario != null && realizingScenario.eIsProxy() ? (Scenario)eResolveProxy((InternalEObject)realizingScenario) : realizingScenario;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Scenario basicGetRealizingScenario() {


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
    EAnnotation annotation = InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZING_SCENARIO.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.SCENARIO_REALIZATION__REALIZING_SCENARIO, annotation);
    
    try {
      return (Scenario) result;
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
      case InteractionPackage.SCENARIO_REALIZATION__REALIZED_SCENARIO:
        if (resolve) return getRealizedScenario();
        return basicGetRealizedScenario();
      case InteractionPackage.SCENARIO_REALIZATION__REALIZING_SCENARIO:
        if (resolve) return getRealizingScenario();
        return basicGetRealizingScenario();
    }
    return super.eGet(featureID, resolve, coreType);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case InteractionPackage.SCENARIO_REALIZATION__REALIZED_SCENARIO:
        return basicGetRealizedScenario() != null;
      case InteractionPackage.SCENARIO_REALIZATION__REALIZING_SCENARIO:
        return basicGetRealizingScenario() != null;
    }
    return super.eIsSet(featureID);
  }



} //ScenarioRealizationImpl