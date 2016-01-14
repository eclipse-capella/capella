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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.la.LogicalActor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalActor#getOwnedLogicalActorRealizations <em>Owned Logical Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalActor#getLogicalActorRealizations <em>Logical Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalActor#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalActor#getRealizedLogicalActors <em>Realized Logical Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalActor#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalActor'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.PhysicalActor'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='External actor interacting with the system via physical interfaces' usage\040guideline='Used whenever there is a need to model an interaction between the outside of the physical system perimeter, and the inside, using one of the physical interfaces.\r\nPhysical actors are typically derived/refined from logical actors definition.' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='none' constraints='none'"
 * @generated
 */
public interface PhysicalActor extends AbstractPhysicalComponent, AbstractActor {





	/**
	 * Returns the value of the '<em><b>Owned Logical Actor Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalActorRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Actor Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Logical Actor Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor_OwnedLogicalActorRealizations()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the allocation links between physical actors and the logical actor(s) that they realize, stored/owned under this physical actor\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioredClassifier::interfaceRealization' explanation='Elements are contained in the nearest possible parent container.' constraints='uml::BehavioredClassifier::interfaceRealization elements on which LogicalActorRealization stereotype or any stereotype that inherits from it is applied'"
	 * @generated
	 */

	EList<LogicalActorRealization> getOwnedLogicalActorRealizations();







	/**
	 * Returns the value of the '<em><b>Logical Actor Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalActorRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Actor Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Actor Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor_LogicalActorRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the relationship links between this physical actor and the logical actor(s) that it realizes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalActorRealization> getLogicalActorRealizations();







	/**
	 * Returns the value of the '<em><b>Allocated Physical Functions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalActors <em>Allocator Physical Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Physical Functions</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor_AllocatedPhysicalFunctions()
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalActors
	 * @model opposite="allocatorPhysicalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalFunction> getAllocatedPhysicalFunctions();







	/**
	 * Returns the value of the '<em><b>Realized Logical Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalActor}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalActor#getRealizingPhysicalActors <em>Realizing Physical Actors</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Logical Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized Logical Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor_RealizedLogicalActors()
	 * @see org.polarsys.capella.core.data.la.LogicalActor#getRealizingPhysicalActors
	 * @model opposite="realizingPhysicalActors" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalActor> getRealizedLogicalActors();







	/**
	 * Returns the value of the '<em><b>Deployed Physical Components</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployed Physical Components</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalActor_DeployedPhysicalComponents()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalComponent> getDeployedPhysicalComponents();





} // PhysicalActor
