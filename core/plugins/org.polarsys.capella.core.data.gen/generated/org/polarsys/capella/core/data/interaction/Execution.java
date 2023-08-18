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
 * A representation of the model object '<em><b>Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.Execution#getCovered <em>Covered</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getExecution()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Execution'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='BehaviorExecutionSpecification' stereotype='eng.Execution'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An Execution Specification is a specification of the execution of a unit of behavior or action within the Lifeline. The\r\nduration of an ExecutionSpecification is represented by two ExecutionOccurrenceSpecifications, the start\r\nExecutionOccurrenceSpecification and the finish ExecutionOccurrenceSpecification.\r\n\r\nExecution can be compared to UML Execution Specification.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed ExecutionSpecification to map UML concept' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::BehaviorExecutionSpecification' explanation='none' constraints='none'"
 * @generated
 */
public interface Execution extends TimeLapse {





	/**
   * Returns the value of the '<em><b>Covered</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Covered</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Covered</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getExecution_Covered()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='covers'"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='covered' featureOwner='InteractionFragment'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the instance role that performs this Execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InteractionFragment::covered' explanation='none' constraints='Multiplicity must be [1..1]'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Execution.start.coveredInstanceRoles(self, target);\r\n} or {\r\n\tExecution.finish.coveredInstanceRoles(self, target);'"
   * @generated
   */

	InstanceRole getCovered();





} // Execution
