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
package org.polarsys.capella.core.data.capellacore;

import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getAllocation()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Allocation'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Allocation is similar to SysML Allocate concept : It is a mechanism for associating elements of different types, or in\r\ndifferent hierarchies, at an abstract level. Allocate is used for assessing user model consistency and directing future design\r\nactivity. It is expected that an \"allocate\" relationship between model elements is a precursor to a more concrete\r\nrelationship between the elements, their properties, operations, attributes, or sub-classes.\r\n[source:SysML v1.1]' usage\040guideline='Allocation is an abstract concept and cannot be used directly.' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='Allocation is an abstract concept' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='SysML::Allocations::Allocate' constraints='none'"
 * @generated
 */
public interface Allocation extends Relationship, AbstractTrace {



} // Allocation
