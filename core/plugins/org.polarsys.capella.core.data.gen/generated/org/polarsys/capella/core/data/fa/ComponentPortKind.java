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
package org.polarsys.capella.core.data.fa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Component Port Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentPortKind()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ComponentPortKind'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ComponentPortKind is an enumeration of the following literal values:\r\nstandard:\r\nA port is an interaction point between a Block or sub-Block and its environment that supports Exchanges with other ports.\r\nflow:\r\nA FlowPorts is an interaction point through which input and/or output of items such as data, material, or energy may flow' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public enum ComponentPortKind implements Enumerator {
	/**
   * The '<em><b>STANDARD</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #STANDARD_VALUE
   * @generated
   * @ordered
   */
	STANDARD(0, "STANDARD", "STANDARD"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FLOW</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FLOW_VALUE
   * @generated
   * @ordered
   */
	FLOW(1, "FLOW", "FLOW"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>STANDARD</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>STANDARD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #STANDARD
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Describes a standard port : \r\nA port is an interaction point between a Block or sub-Block and its environment that supports Exchanges with other ports.\r\n[source: SysML glossary for SysML v1.0]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='' constraints=''"
   * @generated
   * @ordered
   */
	public static final int STANDARD_VALUE = 0;

	/**
   * The '<em><b>FLOW</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FLOW
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Describes a flow port : \r\nA FlowPorts is an interaction point through which input and/or output of items such as data, material, or energy may flow\r\n[source: SysML specification v1.1]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='' constraints=''"
   * @generated
   * @ordered
   */
	public static final int FLOW_VALUE = 1;

	/**
   * An array of all the '<em><b>Component Port Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ComponentPortKind[] VALUES_ARRAY =
		new ComponentPortKind[] {
      STANDARD,
      FLOW,
    };

	/**
   * A public read-only list of all the '<em><b>Component Port Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ComponentPortKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Component Port Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentPortKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ComponentPortKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Component Port Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentPortKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ComponentPortKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Component Port Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentPortKind get(int value) {
    switch (value) {
      case STANDARD_VALUE: return STANDARD;
      case FLOW_VALUE: return FLOW;
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
	private ComponentPortKind(int value, String name, String literal) {
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
	
} //ComponentPortKind
