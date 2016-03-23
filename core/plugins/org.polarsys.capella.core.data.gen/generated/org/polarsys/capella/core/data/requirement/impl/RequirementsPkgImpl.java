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

package org.polarsys.capella.core.data.requirement.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirements Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getContainedRequirementsTraces <em>Contained Requirements Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getAdditionalInformation <em>Additional Information</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getOwnedRequirements <em>Owned Requirements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.impl.RequirementsPkgImpl#getOwnedRequirementPkgs <em>Owned Requirement Pkgs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequirementsPkgImpl extends NamedElementImpl implements RequirementsPkg {

	/**
	 * The cached value of the '{@link #getOwnedTraces() <em>Owned Traces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTraces()
	 * @generated
	 * @ordered
	 */
	protected EList<Trace> ownedTraces;













	/**
	 * The cached value of the '{@link #getNamingRules() <em>Naming Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamingRules()
	 * @generated
	 * @ordered
	 */
	protected EList<NamingRule> namingRules;





	/**
	 * The cached value of the '{@link #getOwnedPropertyValuePkgs() <em>Owned Property Value Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPropertyValuePkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyValuePkg> ownedPropertyValuePkgs;





	/**
	 * The default value of the '{@link #getAdditionalInformation() <em>Additional Information</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalInformation()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDITIONAL_INFORMATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdditionalInformation() <em>Additional Information</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdditionalInformation()
	 * @generated
	 * @ordered
	 */
	protected String additionalInformation = ADDITIONAL_INFORMATION_EDEFAULT;





	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final String LEVEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected String level = LEVEL_EDEFAULT;





	/**
	 * The cached value of the '{@link #getOwnedRequirements() <em>Owned Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<Requirement> ownedRequirements;





	/**
	 * The cached value of the '{@link #getOwnedRequirementPkgs() <em>Owned Requirement Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRequirementPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<RequirementsPkg> ownedRequirementPkgs;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementsPkgImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RequirementPackage.Literals.REQUIREMENTS_PKG;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Trace> getOwnedTraces() {

		if (ownedTraces == null) {
			ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES);
		}
		return ownedTraces;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<GenericTrace> getContainedGenericTraces() {


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
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<GenericTrace> resultAsList = (Collection<GenericTrace>) result;
		return new EcoreEList.UnmodifiableEList<GenericTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<RequirementsTrace> getContainedRequirementsTraces() {


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
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<RequirementsTrace> resultAsList = (Collection<RequirementsTrace>) result;
		return new EcoreEList.UnmodifiableEList<RequirementsTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<NamingRule> getNamingRules() {

		if (namingRules == null) {
			namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES);
		}
		return namingRules;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PropertyValuePkg> getOwnedPropertyValuePkgs() {

		if (ownedPropertyValuePkgs == null) {
			ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS);
		}
		return ownedPropertyValuePkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public String getAdditionalInformation() {

		return additionalInformation;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setAdditionalInformation(String newAdditionalInformation) {

		String oldAdditionalInformation = additionalInformation;
		additionalInformation = newAdditionalInformation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementPackage.REQUIREMENTS_PKG__ADDITIONAL_INFORMATION, oldAdditionalInformation, additionalInformation));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public String getLevel() {

		return level;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setLevel(String newLevel) {

		String oldLevel = level;
		level = newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RequirementPackage.REQUIREMENTS_PKG__LEVEL, oldLevel, level));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Requirement> getOwnedRequirements() {

		if (ownedRequirements == null) {
			ownedRequirements = new EObjectContainmentEList<Requirement>(Requirement.class, this, RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS);
		}
		return ownedRequirements;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<RequirementsPkg> getOwnedRequirementPkgs() {

		if (ownedRequirementPkgs == null) {
			ownedRequirementPkgs = new EObjectContainmentEList.Resolving<RequirementsPkg>(RequirementsPkg.class, this, RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS);
		}
		return ownedRequirementPkgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES:
				return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
			case RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES:
				return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS:
				return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS:
				return ((InternalEList<?>)getOwnedRequirements()).basicRemove(otherEnd, msgs);
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS:
				return ((InternalEList<?>)getOwnedRequirementPkgs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES:
				return getOwnedTraces();
			case RequirementPackage.REQUIREMENTS_PKG__CONTAINED_GENERIC_TRACES:
				return getContainedGenericTraces();
			case RequirementPackage.REQUIREMENTS_PKG__CONTAINED_REQUIREMENTS_TRACES:
				return getContainedRequirementsTraces();
			case RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES:
				return getNamingRules();
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS:
				return getOwnedPropertyValuePkgs();
			case RequirementPackage.REQUIREMENTS_PKG__ADDITIONAL_INFORMATION:
				return getAdditionalInformation();
			case RequirementPackage.REQUIREMENTS_PKG__LEVEL:
				return getLevel();
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS:
				return getOwnedRequirements();
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS:
				return getOwnedRequirementPkgs();
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
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES:
				getOwnedTraces().clear();
				getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES:
				getNamingRules().clear();
				getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS:
				getOwnedPropertyValuePkgs().clear();
				getOwnedPropertyValuePkgs().addAll((Collection<? extends PropertyValuePkg>)newValue);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__ADDITIONAL_INFORMATION:
				// begin-extension-code
				if (newValue == null || newValue instanceof String) {
				// end-extension-code
					setAdditionalInformation((String)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case RequirementPackage.REQUIREMENTS_PKG__LEVEL:
				// begin-extension-code
				if (newValue == null || newValue instanceof String) {
				// end-extension-code
					setLevel((String)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS:
				getOwnedRequirements().clear();
				getOwnedRequirements().addAll((Collection<? extends Requirement>)newValue);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS:
				getOwnedRequirementPkgs().clear();
				getOwnedRequirementPkgs().addAll((Collection<? extends RequirementsPkg>)newValue);
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
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES:
				getOwnedTraces().clear();
				return;
			case RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES:
				getNamingRules().clear();
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS:
				getOwnedPropertyValuePkgs().clear();
				return;
			case RequirementPackage.REQUIREMENTS_PKG__ADDITIONAL_INFORMATION:
				setAdditionalInformation(ADDITIONAL_INFORMATION_EDEFAULT);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__LEVEL:
				setLevel(LEVEL_EDEFAULT);
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS:
				getOwnedRequirements().clear();
				return;
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS:
				getOwnedRequirementPkgs().clear();
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
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_TRACES:
				return ownedTraces != null && !ownedTraces.isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__CONTAINED_GENERIC_TRACES:
				return !getContainedGenericTraces().isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__CONTAINED_REQUIREMENTS_TRACES:
				return !getContainedRequirementsTraces().isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__NAMING_RULES:
				return namingRules != null && !namingRules.isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_PROPERTY_VALUE_PKGS:
				return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__ADDITIONAL_INFORMATION:
				return ADDITIONAL_INFORMATION_EDEFAULT == null ? additionalInformation != null : !ADDITIONAL_INFORMATION_EDEFAULT.equals(additionalInformation);
			case RequirementPackage.REQUIREMENTS_PKG__LEVEL:
				return LEVEL_EDEFAULT == null ? level != null : !LEVEL_EDEFAULT.equals(level);
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENTS:
				return ownedRequirements != null && !ownedRequirements.isEmpty();
			case RequirementPackage.REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS:
				return ownedRequirementPkgs != null && !ownedRequirementPkgs.isEmpty();
		}
		return super.eIsSet(featureID);
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
		result.append(" (additionalInformation: "); //$NON-NLS-1$
		result.append(additionalInformation);
		result.append(", level: "); //$NON-NLS-1$
		result.append(level);
		result.append(')');
		return result.toString();
	}


} //RequirementsPkgImpl