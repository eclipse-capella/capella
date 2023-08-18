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

package org.polarsys.capella.core.data.capellamodeller.impl;

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
import org.polarsys.capella.core.data.capellacore.impl.AbstractModellingStructureImpl;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Engineering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedOperationalAnalysis <em>Contained Operational Analysis</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedSystemAnalysis <em>Contained System Analysis</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedLogicalArchitectures <em>Contained Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedPhysicalArchitectures <em>Contained Physical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedEPBSArchitectures <em>Contained EPBS Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl#getContainedSharedPkgs <em>Contained Shared Pkgs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemEngineeringImpl extends AbstractModellingStructureImpl implements SystemEngineering {
























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected SystemEngineeringImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellamodellerPackage.Literals.SYSTEM_ENGINEERING;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalAnalysis> getContainedOperationalAnalysis() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalAnalysis> resultAsList = (Collection<OperationalAnalysis>) result;
    return new EcoreEList.UnmodifiableEList<OperationalAnalysis>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemAnalysis> getContainedSystemAnalysis() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemAnalysis> resultAsList = (Collection<SystemAnalysis>) result;
    return new EcoreEList.UnmodifiableEList<SystemAnalysis>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalArchitecture> getContainedLogicalArchitectures() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalArchitecture> resultAsList = (Collection<LogicalArchitecture>) result;
    return new EcoreEList.UnmodifiableEList<LogicalArchitecture>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalArchitecture> getContainedPhysicalArchitectures() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalArchitecture> resultAsList = (Collection<PhysicalArchitecture>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalArchitecture>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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

	public EList<EPBSArchitecture> getContainedEPBSArchitectures() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<EPBSArchitecture> resultAsList = (Collection<EPBSArchitecture>) result;
    return new EcoreEList.UnmodifiableEList<EPBSArchitecture>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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

	public EList<SharedPkg> getContainedSharedPkgs() {


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
    EAnnotation annotation = CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SharedPkg> resultAsList = (Collection<SharedPkg>) result;
    return new EcoreEList.UnmodifiableEList<SharedPkg>(this, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS, resultAsList.size(), resultAsList.toArray());
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
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS:
        return getContainedOperationalAnalysis();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS:
        return getContainedSystemAnalysis();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES:
        return getContainedLogicalArchitectures();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES:
        return getContainedPhysicalArchitectures();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES:
        return getContainedEPBSArchitectures();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS:
        return getContainedSharedPkgs();
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
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_OPERATIONAL_ANALYSIS:
        return !getContainedOperationalAnalysis().isEmpty();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_SYSTEM_ANALYSIS:
        return !getContainedSystemAnalysis().isEmpty();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_LOGICAL_ARCHITECTURES:
        return !getContainedLogicalArchitectures().isEmpty();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_PHYSICAL_ARCHITECTURES:
        return !getContainedPhysicalArchitectures().isEmpty();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_EPBS_ARCHITECTURES:
        return !getContainedEPBSArchitectures().isEmpty();
      case CapellamodellerPackage.SYSTEM_ENGINEERING__CONTAINED_SHARED_PKGS:
        return !getContainedSharedPkgs().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //SystemEngineeringImpl