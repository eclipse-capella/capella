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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.information.AbstractInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getDeployedParts <em>Deployed Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getDeployingParts <em>Deploying Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Part#getOwnedAbstractType <em>Owned Abstract Type</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getPart()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalPart'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Property' stereotype='eng.PhysicalPart'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='In SysML, a Part is an owned property of a Block\r\n[source: SysML glossary for SysML v1.0]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='should be mapped to uml::Property, but one of its concrete ancestors already is (Property), so avoid redefining it\r\nat this level to avoid profile generation issue' constraints='information::Property must have as base metaclass uml::Property'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Part extends AbstractInstance, InformationsExchanger, DeployableElement, DeploymentTarget, AbstractPathInvolvedElement {





	/**
   * Returns the value of the '<em><b>Provided Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provided Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_ProvidedInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='providedInterfaces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Part.type(self, component);\r\nComponent.providedInterfaces(component, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(computed) the provided interfaces associated to the classifier that this part represents\r\n[source: Capella study]\r\n\r\nThe interfaces that the component exposes to its environment.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getProvidedInterfaces();







	/**
   * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Required Interfaces</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_RequiredInterfaces()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='requiredInterfaces'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Part.type(self, component);\r\nComponent.requiredInterfaces(component, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(computed) the required interfaces associated to the classifier that this part represents\r\n[source: Capella study]\r\n\r\nThe interfaces that the component requires from other components in its environment in order to be able to offer\r\nits full set of provided functionality\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Interface> getRequiredInterfaces();







	/**
   * Returns the value of the '<em><b>Owned Deployment Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Deployment Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Deployment Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_OwnedDeploymentLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Deployment relationships that are stored/owned under this part' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='none' constraints='none'"
   * @generated
   */

	EList<AbstractDeploymentLink> getOwnedDeploymentLinks();







	/**
   * Returns the value of the '<em><b>Deployed Parts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deployed Parts</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_DeployedParts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='deploymentLinks.deployedElement'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Part> getDeployedParts();







	/**
   * Returns the value of the '<em><b>Deploying Parts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.Part}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deploying Parts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deploying Parts</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_DeployingParts()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='deployingLinks.location'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<Part> getDeployingParts();







	/**
   * Returns the value of the '<em><b>Owned Abstract Type</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Abstract Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Abstract Type</em>' containment reference.
   * @see #setOwnedAbstractType(AbstractType)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPart_OwnedAbstractType()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractType getOwnedAbstractType();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Part#getOwnedAbstractType <em>Owned Abstract Type</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Abstract Type</em>' containment reference.
   * @see #getOwnedAbstractType()
   * @generated
   */

	void setOwnedAbstractType(AbstractType value);





} // Part
