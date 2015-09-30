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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacore.Classifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.SystemComponent#isDataComponent <em>Data Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.SystemComponent#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.SystemComponent#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getSystemComponent()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='SystemComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An entity, with discrete structure within the system, that interacts with other Components of the system, thereby contributing at its lowest level to the system properties and characteristics.\r\n[source: Sys EM , ISO/IEC CD 15288]' usage\040guideline='n/a' used\040in\040levels='n/a (abstract)' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 * @generated
 */
public interface SystemComponent extends Component, CapabilityRealizationInvolvedElement {





	/**
	 * Returns the value of the '<em><b>Data Component</b></em>' attribute.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Component</em>' attribute.
	 * @see #setDataComponent(boolean)
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getSystemComponent_DataComponent()
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether or not this is a data component\r\n[source: Capella light-light study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	boolean isDataComponent();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.cs.SystemComponent#isDataComponent <em>Data Component</em>}' attribute.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Component</em>' attribute.
	 * @see #isDataComponent()
	 * @generated
	 */

	void setDataComponent(boolean value);







	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.capellacore.Classifier}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getSystemComponent_DataType()
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='data type(s) associated to this component\r\n[source: Capella light-light study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 */

	EList<Classifier> getDataType();







	/**
	 * Returns the value of the '<em><b>Participations In Capability Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participations In Capability Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participations In Capability Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getSystemComponent_ParticipationsInCapabilityRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='supplier' umlOppositeReferenceOwner='Dependency'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='participationsInCapabilityRealizations'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the involvement relationships between this SystemComponent and CapabilityRealization elements\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<SystemComponentCapabilityRealizationInvolvement> getParticipationsInCapabilityRealizations();





} // SystemComponent
