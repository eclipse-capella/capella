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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Informations Exchanger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.InformationsExchangerImpl#getInformationFlows <em>Information Flows</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class InformationsExchangerImpl extends ModelElementImpl implements InformationsExchanger {












	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InformationsExchangerImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractInformationFlow> getIncomingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getOutgoingInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractInformationFlow> getInformationFlows() {


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
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
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
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS:
        return getIncomingInformationFlows();
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS:
        return getOutgoingInformationFlows();
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS:
        return getInformationFlows();
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
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS:
        return !getIncomingInformationFlows().isEmpty();
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS:
        return !getOutgoingInformationFlows().isEmpty();
      case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS:
        return !getInformationFlows().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //InformationsExchangerImpl