/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.modellingcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Information Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getRealizations <em>Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getConvoyedInformations <em>Convoyed Informations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractInformationFlow()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An InformationFlow specifies that one or more information items circulates from its sources to its targets. Information\r\nflows require some kind of \'information channel\' for transmitting information items from the source to the destination.\r\nAn information channel is represented in various ways depending on the nature of its sources and targets.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::InformationFlow' constraints='none'"
 * @generated
 */
public interface AbstractInformationFlow extends AbstractNamedElement, AbstractRelationship {





	/**
   * Returns the value of the '<em><b>Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship#getRealizedFlow <em>Realized Flow</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizations</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractInformationFlow_Realizations()
   * @see org.polarsys.capella.common.data.modellingcore.AbstractRelationship#getRealizedFlow
   * @model opposite="realizedFlow"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Determines which Relationship will realize the specified flow.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InformationFlow::realization' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractRelationship> getRealizations();







	/**
   * Returns the value of the '<em><b>Convoyed Informations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Convoyed Informations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Convoyed Informations</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractInformationFlow_ConvoyedInformations()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the information items that may circulate on this information flow\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InformationFlow::conveyed' explanation='none' constraints='Order will not be preserved'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractExchangeItem> getConvoyedInformations();







	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(InformationsExchanger)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractInformationFlow_Source()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Defines from which source the conveyed information items are initiated\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InformationFlow::informationSource' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	InformationsExchanger getSource();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

	void setSource(InformationsExchanger value);







	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(InformationsExchanger)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractInformationFlow_Target()
   * @model required="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Defines to which target the conveyed information items are directed\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InformationFlow::informationTarget' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	InformationsExchanger getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(InformationsExchanger value);





} // AbstractInformationFlow
