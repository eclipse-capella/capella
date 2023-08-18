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

package org.polarsys.capella.core.data.epbs.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.AllocationImpl;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Artifact Realization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl#getRealizedPhysicalArtifact <em>Realized Physical Artifact</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.PhysicalArtifactRealizationImpl#getRealizingConfigurationItem <em>Realizing Configuration Item</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalArtifactRealizationImpl extends AllocationImpl implements PhysicalArtifactRealization {








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalArtifactRealizationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractPhysicalArtifact getRealizedPhysicalArtifact() {

    AbstractPhysicalArtifact realizedPhysicalArtifact = basicGetRealizedPhysicalArtifact();
    return realizedPhysicalArtifact != null && realizedPhysicalArtifact.eIsProxy() ? (AbstractPhysicalArtifact)eResolveProxy((InternalEObject)realizedPhysicalArtifact) : realizedPhysicalArtifact;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractPhysicalArtifact basicGetRealizedPhysicalArtifact() {


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
    EAnnotation annotation = EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT, annotation);
    
    try {
      return (AbstractPhysicalArtifact) result;
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

	public ConfigurationItem getRealizingConfigurationItem() {

    ConfigurationItem realizingConfigurationItem = basicGetRealizingConfigurationItem();
    return realizingConfigurationItem != null && realizingConfigurationItem.eIsProxy() ? (ConfigurationItem)eResolveProxy((InternalEObject)realizingConfigurationItem) : realizingConfigurationItem;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ConfigurationItem basicGetRealizingConfigurationItem() {


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
    EAnnotation annotation = EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, EpbsPackage.Literals.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM, annotation);
    
    try {
      return (ConfigurationItem) result;
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
      case EpbsPackage.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT:
        if (resolve) return getRealizedPhysicalArtifact();
        return basicGetRealizedPhysicalArtifact();
      case EpbsPackage.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM:
        if (resolve) return getRealizingConfigurationItem();
        return basicGetRealizingConfigurationItem();
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
      case EpbsPackage.PHYSICAL_ARTIFACT_REALIZATION__REALIZED_PHYSICAL_ARTIFACT:
        return basicGetRealizedPhysicalArtifact() != null;
      case EpbsPackage.PHYSICAL_ARTIFACT_REALIZATION__REALIZING_CONFIGURATION_ITEM:
        return basicGetRealizingConfigurationItem() != null;
    }
    return super.eIsSet(featureID);
  }



} //PhysicalArtifactRealizationImpl