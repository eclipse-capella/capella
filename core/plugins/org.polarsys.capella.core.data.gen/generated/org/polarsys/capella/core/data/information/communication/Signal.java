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
package org.polarsys.capella.core.data.information.communication;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.behavior.AbstractSignal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.Signal#getSignalInstances <em>Signal Instances</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getSignal()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Signal'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Signal' stereotype='eng.Signal'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A signal is a specification of send request instances communicated between objects. The receiving object handles the\r\nreceived request instances as specified by its receptions. The data carried by a send request (which was passed to it by the\r\nsend invocation occurrence that caused that request) are represented as attributes of the signal. A signal is defined\r\nindependently of the classifiers handling the signal occurrence\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Signal' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Signal extends CommunicationItem, AbstractSignal {





	/**
   * Returns the value of the '<em><b>Signal Instances</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.SignalInstance}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Signal Instances</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getSignal_SignalInstances()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='ownedAttribute' featureOwner='Signal'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='signalInstances'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of signal instances associated with this Signal\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Signal::ownedAttribute' explanation='none' constraints='uml::Signal::ownedAttribute elements on which SignalInstance stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SignalInstance> getSignalInstances();





} // Signal
