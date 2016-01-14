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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Classifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partitionable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.PartitionableElement#getOwnedPartitions <em>Owned Partitions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.PartitionableElement#getRepresentingPartitions <em>Representing Partitions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getPartitionableElement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PartitionableElement'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='StructuredClassifier'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An element that can be structured into several partitions\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::StructuredClassifier' constraints='none'"
 * @generated
 */
public interface PartitionableElement extends Classifier {





	/**
	 * Returns the value of the '<em><b>Owned Partitions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.information.Partition}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Partitions</em>' reference list.
	 * @see org.polarsys.capella.core.data.information.InformationPackage#getPartitionableElement_OwnedPartitions()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedAttribute' featureOwner='StructuredClassifier'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedParts'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of partitions contained in this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::StructuredClassifier::ownedAttribute' explanation='Even though this reference is derived and transient, it is mapped, to a non-derived reference to simplify the transformation since the containment and derived references are inversed between MA and UML' constraints='uml::StructuredClassifier::owneddAttribute elements on which Partition stereotype or any stereotype that inherits from it is applied'"
	 * @generated
	 */

	EList<Partition> getOwnedPartitions();







	/**
	 * Returns the value of the '<em><b>Representing Partitions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.information.Partition}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representing Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Representing Partitions</em>' reference list.
	 * @see org.polarsys.capella.core.data.information.InformationPackage#getPartitionableElement_RepresentingPartitions()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='part' featureOwner='StructuredClassifier'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='parts'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of Partitions that are associated to this element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 * @generated
	 */

	EList<Partition> getRepresentingPartitions();






	/**

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getOwnedPartitions().size() > 0;'"
	 * @generated
	 */

	boolean isDecomposed();




} // PartitionableElement
