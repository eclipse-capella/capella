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
package org.polarsys.capella.core.data.interaction;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.ExecutionEnd#getExecution <em>Execution</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getExecutionEnd()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ExecutionEnd'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='ExecutionOccurrenceSpecification' stereotype='eng.ExecutionEnd'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This concept can be compared to UML ExecutionOccurrenceSpecification : \r\nAn ExecutionOccurrenceSpecification represents moments in time at which actions or behaviors start or finish.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed ExecutionOccurrenceSpecification to map UML concept' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ExecutionOccurrenceSpecification' explanation='none' constraints='none'"
 * @generated
 */
public interface ExecutionEnd extends AbstractEnd {





	/**
   * Returns the value of the '<em><b>Execution</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Execution</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getExecutionEnd_Execution()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='execution' featureOwner='ExecutionOccurrenceSpecification'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='execution'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Execution to which this ExecutionEnd is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExecutionOccurrenceSpecification::execution' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Execution.start(target, self);\r\n} or {\r\n\tExecution.finish(target, self);'"
   * @generated
   */

	Execution getExecution();





} // ExecutionEnd
