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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Link Protocol</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkProtocol()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='enumeration listing the various possibilities for the protocol of the communication link' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum CommunicationLinkProtocol implements Enumerator {
	/**
   * The '<em><b>UNSET</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNSET_VALUE
   * @generated
   * @ordered
   */
	UNSET(0, "UNSET", "UNSET"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNICAST</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNICAST_VALUE
   * @generated
   * @ordered
   */
	UNICAST(1, "UNICAST", "UNICAST"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>MULTICAST</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #MULTICAST_VALUE
   * @generated
   * @ordered
   */
	MULTICAST(2, "MULTICAST", "MULTICAST"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>BROADCAST</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #BROADCAST_VALUE
   * @generated
   * @ordered
   */
	BROADCAST(3, "BROADCAST", "BROADCAST"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SYNCHRONOUS</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SYNCHRONOUS_VALUE
   * @generated
   * @ordered
   */
	SYNCHRONOUS(4, "SYNCHRONOUS", "SYNCHRONOUS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ASYNCHRONOUS</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ASYNCHRONOUS_VALUE
   * @generated
   * @ordered
   */
	ASYNCHRONOUS(5, "ASYNCHRONOUS", "ASYNCHRONOUS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>READ</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #READ_VALUE
   * @generated
   * @ordered
   */
	READ(6, "READ", "READ"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ACCEPT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ACCEPT_VALUE
   * @generated
   * @ordered
   */
	ACCEPT(7, "ACCEPT", "ACCEPT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink protocol is not yet set' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>UNICAST</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNICAST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNICAST
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a sending of ExchangeItem using the unicast protocol' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNICAST_VALUE = 1;

	/**
   * The '<em><b>MULTICAST</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MULTICAST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #MULTICAST
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a sending of ExchangeItem using the multicast protocol' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int MULTICAST_VALUE = 2;

	/**
   * The '<em><b>BROADCAST</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BROADCAST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #BROADCAST
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a sending of ExchangeItem using the broadcast protocol' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int BROADCAST_VALUE = 3;

	/**
   * The '<em><b>SYNCHRONOUS</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYNCHRONOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SYNCHRONOUS
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a call of ExchangeItem using the synchronous protocol' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SYNCHRONOUS_VALUE = 4;

	/**
   * The '<em><b>ASYNCHRONOUS</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASYNCHRONOUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ASYNCHRONOUS
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a call of ExchangeItem using the asynchronous protocol' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ASYNCHRONOUS_VALUE = 5;

	/**
   * The '<em><b>READ</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READ</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #READ
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a access to the ExchangeItem by reading it' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int READ_VALUE = 6;

	/**
   * The '<em><b>ACCEPT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ACCEPT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ACCEPT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a access to the ExchangeItem by accepting it' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ACCEPT_VALUE = 7;

	/**
   * An array of all the '<em><b>Link Protocol</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final CommunicationLinkProtocol[] VALUES_ARRAY =
		new CommunicationLinkProtocol[] {
      UNSET,
      UNICAST,
      MULTICAST,
      BROADCAST,
      SYNCHRONOUS,
      ASYNCHRONOUS,
      READ,
      ACCEPT,
    };

	/**
   * A public read-only list of all the '<em><b>Link Protocol</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<CommunicationLinkProtocol> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Link Protocol</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkProtocol get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CommunicationLinkProtocol result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Link Protocol</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkProtocol getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CommunicationLinkProtocol result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Link Protocol</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkProtocol get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case UNICAST_VALUE: return UNICAST;
      case MULTICAST_VALUE: return MULTICAST;
      case BROADCAST_VALUE: return BROADCAST;
      case SYNCHRONOUS_VALUE: return SYNCHRONOUS;
      case ASYNCHRONOUS_VALUE: return ASYNCHRONOUS;
      case READ_VALUE: return READ;
      case ACCEPT_VALUE: return ACCEPT;
    }
    return null;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private final int value;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private final String name;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private final String literal;

	/**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private CommunicationLinkProtocol(int value, String name, String literal) {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int getValue() {
    return value;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getName() {
    return name;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getLiteral() {
    return literal;
  }

	/**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    return literal;
  }
	
} //CommunicationLinkProtocol
