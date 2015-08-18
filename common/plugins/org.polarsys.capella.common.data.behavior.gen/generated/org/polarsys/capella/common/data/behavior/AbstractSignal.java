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
package org.polarsys.capella.common.data.behavior;

import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage#getAbstractSignal()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A signal is a specification of send request instances communicated between objects. The receiving object handles the\r\nreceived request instances as specified by its receptions. The data carried by a send request (which was passed to it by the\r\nsend invocation occurrence that caused that request) are represented as attributes of the signal. A signal is defined\r\nindependently of the classifiers handling the signal occurrence\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='the UML2 definition of this element contains an attribute \"ownedAttribute [0..*]\", that is absent here, because the Capella version is a simplified one.\r\nThis element should be removed, since it is only used in Information package, (Signal and references to the Signal), no need for a decoupling class located here.' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Signal' constraints='none'"
 * @generated
 */
public interface AbstractSignal extends AbstractType {



} // AbstractSignal
