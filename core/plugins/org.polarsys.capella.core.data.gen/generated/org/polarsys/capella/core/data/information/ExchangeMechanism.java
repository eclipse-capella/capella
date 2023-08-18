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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Exchange Mechanism</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.InformationPackage#getExchangeMechanism()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Enumeration of the different exchange mechanisms\r\n[source:Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum ExchangeMechanism implements Enumerator {
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
   * The '<em><b>FLOW</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FLOW_VALUE
   * @generated
   * @ordered
   */
	FLOW(1, "FLOW", "FLOW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>OPERATION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #OPERATION_VALUE
   * @generated
   * @ordered
   */
	OPERATION(2, "OPERATION", "OPERATION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>EVENT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #EVENT_VALUE
   * @generated
   * @ordered
   */
	EVENT(3, "EVENT", "EVENT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SHARED DATA</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SHARED_DATA_VALUE
   * @generated
   * @ordered
   */
	SHARED_DATA(4, "SHARED_DATA", "SHARED_DATA"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Exchange mechanism not defined' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>FLOW</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FLOW
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Continuous supply of a data\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FLOW_VALUE = 1;

	/**
   * The '<em><b>OPERATION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OPERATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #OPERATION
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Sporadic supply of a data with a returned data\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int OPERATION_VALUE = 2;

	/**
   * The '<em><b>EVENT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EVENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #EVENT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Asynchronous information that is taken into account rapidly\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int EVENT_VALUE = 3;

	/**
   * The '<em><b>SHARED DATA</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SHARED DATA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SHARED_DATA
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Data taken into account (reading or writing) without taking care of producers or consumers\r\n[Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SHARED_DATA_VALUE = 4;

	/**
   * An array of all the '<em><b>Exchange Mechanism</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ExchangeMechanism[] VALUES_ARRAY =
		new ExchangeMechanism[] {
      UNSET,
      FLOW,
      OPERATION,
      EVENT,
      SHARED_DATA,
    };

	/**
   * A public read-only list of all the '<em><b>Exchange Mechanism</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ExchangeMechanism> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Exchange Mechanism</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ExchangeMechanism get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ExchangeMechanism result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Exchange Mechanism</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ExchangeMechanism getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ExchangeMechanism result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Exchange Mechanism</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ExchangeMechanism get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case FLOW_VALUE: return FLOW;
      case OPERATION_VALUE: return OPERATION;
      case EVENT_VALUE: return EVENT;
      case SHARED_DATA_VALUE: return SHARED_DATA;
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
	private ExchangeMechanism(int value, String name, String literal) {
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
	
} //ExchangeMechanism
