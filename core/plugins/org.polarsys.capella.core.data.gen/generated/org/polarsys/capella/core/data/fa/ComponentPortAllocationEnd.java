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
package org.polarsys.capella.core.data.fa;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Port;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Port Allocation End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPort <em>Port</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPart <em>Part</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getOwningComponentPortAllocation <em>Owning Component Port Allocation</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPortAllocationEnd()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ConnectorEnd' explanation='none' constraints='none'"
 * @generated
 */
public interface ComponentPortAllocationEnd extends CapellaElement {





	/**
	 * Returns the value of the '<em><b>Port</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' reference.
	 * @see #setPort(Port)
	 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPortAllocationEnd_Port()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='role' featureOwner='ConnectorEnd'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='port'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the port to which this communication endpoint is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorEnd::role' explanation='none' constraints='uml::ConnectorEnd::role elements on which PhysicalPort stereotype or any stereotype that inherits from it is applied'"
	 * @generated
	 */

	Port getPort();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPort <em>Port</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' reference.
	 * @see #getPort()
	 * @generated
	 */

	void setPort(Port value);







	/**
	 * Returns the value of the '<em><b>Part</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Part</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Part</em>' reference.
	 * @see #setPart(Part)
	 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPortAllocationEnd_Part()
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='partWithPort' featureOwner='ConnectorEnd'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='part'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the part to which this connect endpoint is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorEnd::partWithPort' explanation='none' constraints='none'"
	 * @generated
	 */

	Part getPart();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getPart <em>Part</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Part</em>' reference.
	 * @see #getPart()
	 * @generated
	 */

	void setPart(Part value);







	/**
	 * Returns the value of the '<em><b>Owning Component Port Allocation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocation#getOwnedComponentPortAllocationEnds <em>Owned Component Port Allocation Ends</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Component Port Allocation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Component Port Allocation</em>' container reference.
	 * @see #setOwningComponentPortAllocation(ComponentPortAllocation)
	 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPortAllocationEnd_OwningComponentPortAllocation()
	 * @see org.polarsys.capella.core.data.fa.ComponentPortAllocation#getOwnedComponentPortAllocationEnds
	 * @model opposite="ownedComponentPortAllocationEnds" transient="false"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='end' umlOppositeReferenceOwner='Connector'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='owningComponentPortAllocation'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the ComponentPortAllocation link that contains this endpoint\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Connector::end' constraints='none'"
	 * @generated
	 */

	ComponentPortAllocation getOwningComponentPortAllocation();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd#getOwningComponentPortAllocation <em>Owning Component Port Allocation</em>}' container reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Component Port Allocation</em>' container reference.
	 * @see #getOwningComponentPortAllocation()
	 * @generated
	 */

	void setOwningComponentPortAllocation(ComponentPortAllocation value);





} // ComponentPortAllocationEnd
