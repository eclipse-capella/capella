/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.SystemComponentImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.SystemComponentImpl#getInvolvingCapabilityRealizationInvolvements <em>Involving Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.SystemComponentImpl#isDataComponent <em>Data Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.SystemComponentImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.SystemComponentImpl#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SystemComponentImpl extends ComponentImpl implements SystemComponent {









	/**
	 * The default value of the '{@link #isDataComponent() <em>Data Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataComponent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DATA_COMPONENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDataComponent() <em>Data Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataComponent()
	 * @generated
	 * @ordered
	 */
	protected boolean dataComponent = DATA_COMPONENT_EDEFAULT;





	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected EList<Classifier> dataType;








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemComponentImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CsPackage.Literals.SYSTEM_COMPONENT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Involvement> getInvolvingInvolvements() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Involvement> resultAsList = (Collection<Involvement>) result;
		return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityRealizationInvolvement> getInvolvingCapabilityRealizationInvolvements() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<CapabilityRealizationInvolvement> resultAsList = (Collection<CapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<CapabilityRealizationInvolvement>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public boolean isDataComponent() {

		return dataComponent;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setDataComponent(boolean newDataComponent) {

		boolean oldDataComponent = dataComponent;
		dataComponent = newDataComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT, oldDataComponent, dataComponent));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Classifier> getDataType() {

		if (dataType == null) {
			dataType = new EObjectResolvingEList<Classifier>(Classifier.class, this, CsPackage.SYSTEM_COMPONENT__DATA_TYPE);
		}
		return dataType;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemComponentCapabilityRealizationInvolvement> getParticipationsInCapabilityRealizations() {


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
    EAnnotation annotation = CsPackage.Literals.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemComponentCapabilityRealizationInvolvement> resultAsList = (Collection<SystemComponentCapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<SystemComponentCapabilityRealizationInvolvement>(this, CsPackage.Literals.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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
			case CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS:
				return getInvolvingInvolvements();
			case CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS:
				return getInvolvingCapabilityRealizationInvolvements();
			case CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
				return isDataComponent();
			case CsPackage.SYSTEM_COMPONENT__DATA_TYPE:
				return getDataType();
			case CsPackage.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return getParticipationsInCapabilityRealizations();
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
			case CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
				// begin-extension-code
				if (newValue == null || newValue instanceof Boolean) {
				// end-extension-code
					setDataComponent((Boolean)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CsPackage.SYSTEM_COMPONENT__DATA_TYPE:
				getDataType().clear();
				getDataType().addAll((Collection<? extends Classifier>)newValue);
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
			case CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
				setDataComponent(DATA_COMPONENT_EDEFAULT);
				return;
			case CsPackage.SYSTEM_COMPONENT__DATA_TYPE:
				getDataType().clear();
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
			case CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS:
				return !getInvolvingInvolvements().isEmpty();
			case CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS:
				return !getInvolvingCapabilityRealizationInvolvements().isEmpty();
			case CsPackage.SYSTEM_COMPONENT__DATA_COMPONENT:
				return dataComponent != DATA_COMPONENT_EDEFAULT;
			case CsPackage.SYSTEM_COMPONENT__DATA_TYPE:
				return dataType != null && !dataType.isEmpty();
			case CsPackage.SYSTEM_COMPONENT__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return !getParticipationsInCapabilityRealizations().isEmpty();
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == InvolvedElement.class) {
			switch (derivedFeatureID) {
				case CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
				default: return -1;
			}
		}
		if (baseClass == CapabilityRealizationInvolvedElement.class) {
			switch (derivedFeatureID) {
				case CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == InvolvedElement.class) {
			switch (baseFeatureID) {
				case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return CsPackage.SYSTEM_COMPONENT__INVOLVING_INVOLVEMENTS;
				default: return -1;
			}
		}
		if (baseClass == CapabilityRealizationInvolvedElement.class) {
			switch (baseFeatureID) {
				case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS: return CsPackage.SYSTEM_COMPONENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dataComponent: "); //$NON-NLS-1$
		result.append(dataComponent);
		result.append(')');
		return result.toString();
	}


} //SystemComponentImpl