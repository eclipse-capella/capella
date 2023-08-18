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

package org.polarsys.capella.core.data.oa.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.fa.impl.FunctionalChainImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalProcess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalProcessImpl#getInvolvingOperationalCapabilities <em>Involving Operational Capabilities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalProcessImpl extends FunctionalChainImpl implements OperationalProcess {




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalProcessImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_PROCESS;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalCapability> getInvolvingOperationalCapabilities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalCapability> resultAsList = (Collection<OperationalCapability>) result;
    return new EcoreEList.UnmodifiableEList<OperationalCapability>(this, OaPackage.Literals.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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
      case OaPackage.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES:
        return getInvolvingOperationalCapabilities();
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
      case OaPackage.OPERATIONAL_PROCESS__INVOLVING_OPERATIONAL_CAPABILITIES:
        return !getInvolvingOperationalCapabilities().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //OperationalProcessImpl