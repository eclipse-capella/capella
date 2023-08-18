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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.UnionProperty#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getUnionProperty()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='UnionProperty'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Property' stereotype='eng.UnionProperty'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(not used)' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='n/a' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Property' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface UnionProperty extends Property {





	/**
   * Returns the value of the '<em><b>Qualifier</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datavalue.DataValue}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Qualifier</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getUnionProperty_Qualifier()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='qualifier'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(not used)' constraints='n/a' comment/notes='n/a'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataValue> getQualifier();





} // UnionProperty
