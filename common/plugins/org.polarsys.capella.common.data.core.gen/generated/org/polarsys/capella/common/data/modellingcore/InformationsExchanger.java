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
package org.polarsys.capella.common.data.modellingcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Informations Exchanger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger#getInformationFlows <em>Information Flows</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getInformationsExchanger()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a model element that is the source and/or destination of information flows\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::NamedElement in order to map Capella AbstractInformationFlow::source and  AbstractInformationFlow::target to uml::InformationFlow::source and uml::InformationFlow::target' constraints='none'"
 * @generated
 */
public interface InformationsExchanger extends ModelElement {





	/**
	 * Returns the value of the '<em><b>Incoming Information Flows</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Information Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Information Flows</em>' reference list.
	 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getInformationsExchanger_IncomingInformationFlows()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of information flows coming towards this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::InformationFlow::informationSource' constraints='Order must be computed'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<AbstractInformationFlow> getIncomingInformationFlows();







	/**
	 * Returns the value of the '<em><b>Outgoing Information Flows</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Information Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Information Flows</em>' reference list.
	 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getInformationsExchanger_OutgoingInformationFlows()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of information flows coming out of this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::InformationFlow::informationTarget' constraints='Order must be computed'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<AbstractInformationFlow> getOutgoingInformationFlows();







	/**
	 * Returns the value of the '<em><b>Information Flows</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Information Flows</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Information Flows</em>' reference list.
	 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getInformationsExchanger_InformationFlows()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<AbstractInformationFlow> getInformationFlows();





} // InformationsExchanger
