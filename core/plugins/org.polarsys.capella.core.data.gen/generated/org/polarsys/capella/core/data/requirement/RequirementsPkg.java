/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.requirement;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirements Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getAdditionalInformation <em>Additional Information</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getLevel <em>Level</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirements <em>Owned Requirements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getOwnedRequirementPkgs <em>Owned Requirement Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#getRequirementsPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='RequirementPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a container for Requirement elements, and sub packages containing Requirements\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface RequirementsPkg extends Structure {





	/**
	 * Returns the value of the '<em><b>Additional Information</b></em>' attribute.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Additional Information</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Additional Information</em>' attribute.
	 * @see #setAdditionalInformation(String)
	 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#getRequirementsPkg_AdditionalInformation()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='additionalInformation' featureOwner='eng.RequirementPkg' fromStereotype='true'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='free information field to characterize this requirement package\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	String getAdditionalInformation();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getAdditionalInformation <em>Additional Information</em>}' attribute.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Additional Information</em>' attribute.
	 * @see #getAdditionalInformation()
	 * @generated
	 */

	void setAdditionalInformation(String value);







	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see #setLevel(String)
	 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#getRequirementsPkg_Level()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='level' featureOwner='eng.RequirementPkg' fromStereotype='true'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the design level to which this requirement package applies\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	String getLevel();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.requirement.RequirementsPkg#getLevel <em>Level</em>}' attribute.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see #getLevel()
	 * @generated
	 */

	void setLevel(String value);







	/**
	 * Returns the value of the '<em><b>Owned Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.requirement.Requirement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Requirements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Requirements</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#getRequirementsPkg_OwnedRequirements()
	 * @model containment="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedRequirements'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Requirements contained in this requirement package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Requirement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<Requirement> getOwnedRequirements();







	/**
	 * Returns the value of the '<em><b>Owned Requirement Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.requirement.RequirementsPkg}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Requirement Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Requirement Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#getRequirementsPkg_OwnedRequirementPkgs()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subRequirementPkgs'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-(Requirement) packages contained in this requirement package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which RequirementPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<RequirementsPkg> getOwnedRequirementPkgs();





} // RequirementsPkg
