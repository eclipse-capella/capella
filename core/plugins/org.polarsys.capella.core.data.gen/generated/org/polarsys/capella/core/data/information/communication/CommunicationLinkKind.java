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
 * A representation of the literals of the enumeration '<em><b>Link Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#getCommunicationLinkKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='enumeration listing the various possibilities of communication links\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum CommunicationLinkKind implements Enumerator {
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
   * The '<em><b>PRODUCE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PRODUCE_VALUE
   * @generated
   * @ordered
   */
	PRODUCE(1, "PRODUCE", "PRODUCE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>CONSUME</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #CONSUME_VALUE
   * @generated
   * @ordered
   */
	CONSUME(2, "CONSUME", "CONSUME"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SEND</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SEND_VALUE
   * @generated
   * @ordered
   */
	SEND(3, "SEND", "SEND"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>RECEIVE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #RECEIVE_VALUE
   * @generated
   * @ordered
   */
	RECEIVE(4, "RECEIVE", "RECEIVE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>CALL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #CALL_VALUE
   * @generated
   * @ordered
   */
	CALL(5, "CALL", "CALL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>EXECUTE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #EXECUTE_VALUE
   * @generated
   * @ordered
   */
	EXECUTE(6, "EXECUTE", "EXECUTE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>WRITE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #WRITE_VALUE
   * @generated
   * @ordered
   */
	WRITE(7, "WRITE", "WRITE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ACCESS</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ACCESS_VALUE
   * @generated
   * @ordered
   */
	ACCESS(8, "ACCESS", "ACCESS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ACQUIRE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ACQUIRE_VALUE
   * @generated
   * @ordered
   */
	ACQUIRE(9, "ACQUIRE", "ACQUIRE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>TRANSMIT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #TRANSMIT_VALUE
   * @generated
   * @ordered
   */
	TRANSMIT(10, "TRANSMIT", "TRANSMIT"); //$NON-NLS-1$ //$NON-NLS-2$

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
   * The '<em><b>PRODUCE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRODUCE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PRODUCE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a production of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PRODUCE_VALUE = 1;

	/**
   * The '<em><b>CONSUME</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONSUME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #CONSUME
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a comsumption of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int CONSUME_VALUE = 2;

	/**
   * The '<em><b>SEND</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SEND
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a sending of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SEND_VALUE = 3;

	/**
   * The '<em><b>RECEIVE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RECEIVE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #RECEIVE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a reception of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int RECEIVE_VALUE = 4;

	/**
   * The '<em><b>CALL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #CALL
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a call of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int CALL_VALUE = 5;

	/**
   * The '<em><b>EXECUTE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXECUTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #EXECUTE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe an execution of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int EXECUTE_VALUE = 6;

	/**
   * The '<em><b>WRITE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #WRITE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a writing of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int WRITE_VALUE = 7;

	/**
   * The '<em><b>ACCESS</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ACCESS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ACCESS
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe an access to the ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ACCESS_VALUE = 8;

	/**
   * The '<em><b>ACQUIRE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ACQUIRE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ACQUIRE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe an acquisition of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ACQUIRE_VALUE = 9;

	/**
   * The '<em><b>TRANSMIT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSMIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #TRANSMIT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the CommunicationLink is used to describe a transmission of ExchangeItem' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int TRANSMIT_VALUE = 10;

	/**
   * An array of all the '<em><b>Link Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final CommunicationLinkKind[] VALUES_ARRAY =
		new CommunicationLinkKind[] {
      UNSET,
      PRODUCE,
      CONSUME,
      SEND,
      RECEIVE,
      CALL,
      EXECUTE,
      WRITE,
      ACCESS,
      ACQUIRE,
      TRANSMIT,
    };

	/**
   * A public read-only list of all the '<em><b>Link Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<CommunicationLinkKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Link Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CommunicationLinkKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Link Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CommunicationLinkKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Link Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CommunicationLinkKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case PRODUCE_VALUE: return PRODUCE;
      case CONSUME_VALUE: return CONSUME;
      case SEND_VALUE: return SEND;
      case RECEIVE_VALUE: return RECEIVE;
      case CALL_VALUE: return CALL;
      case EXECUTE_VALUE: return EXECUTE;
      case WRITE_VALUE: return WRITE;
      case ACCESS_VALUE: return ACCESS;
      case ACQUIRE_VALUE: return ACQUIRE;
      case TRANSMIT_VALUE: return TRANSMIT;
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
	private CommunicationLinkKind(int value, String name, String literal) {
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
	
} //CommunicationLinkKind
