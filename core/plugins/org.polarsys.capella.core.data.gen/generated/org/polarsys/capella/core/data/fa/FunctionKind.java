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
 * A representation of the literals of the enumeration '<em><b>Function Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionKind()
 * @model
 * @generated
 */
public enum FunctionKind implements Enumerator {
	/**
   * The '<em><b>FUNCTION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FUNCTION_VALUE
   * @generated
   * @ordered
   */
	FUNCTION(0, "FUNCTION", "FUNCTION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>DUPLICATE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DUPLICATE_VALUE
   * @generated
   * @ordered
   */
	DUPLICATE(1, "DUPLICATE", "DUPLICATE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>GATHER</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #GATHER_VALUE
   * @generated
   * @ordered
   */
	GATHER(2, "GATHER", "GATHER"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SELECT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SELECT_VALUE
   * @generated
   * @ordered
   */
	SELECT(3, "SELECT", "SELECT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SPLIT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SPLIT_VALUE
   * @generated
   * @ordered
   */
	SPLIT(4, "SPLIT", "SPLIT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ROUTE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ROUTE_VALUE
   * @generated
   * @ordered
   */
	ROUTE(5, "ROUTE", "ROUTE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FUNCTION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FUNCTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FUNCTION
   * @model
   * @generated
   * @ordered
   */
	public static final int FUNCTION_VALUE = 0;

	/**
   * The '<em><b>DUPLICATE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DUPLICATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DUPLICATE
   * @model
   * @generated
   * @ordered
   */
	public static final int DUPLICATE_VALUE = 1;

	/**
   * The '<em><b>GATHER</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GATHER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #GATHER
   * @model
   * @generated
   * @ordered
   */
	public static final int GATHER_VALUE = 2;

	/**
   * The '<em><b>SELECT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SELECT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SELECT
   * @model
   * @generated
   * @ordered
   */
	public static final int SELECT_VALUE = 3;

	/**
   * The '<em><b>SPLIT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SPLIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SPLIT
   * @model
   * @generated
   * @ordered
   */
	public static final int SPLIT_VALUE = 4;

	/**
   * The '<em><b>ROUTE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROUTE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ROUTE
   * @model
   * @generated
   * @ordered
   */
	public static final int ROUTE_VALUE = 5;

	/**
   * An array of all the '<em><b>Function Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final FunctionKind[] VALUES_ARRAY =
		new FunctionKind[] {
      FUNCTION,
      DUPLICATE,
      GATHER,
      SELECT,
      SPLIT,
      ROUTE,
    };

	/**
   * A public read-only list of all the '<em><b>Function Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<FunctionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Function Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      FunctionKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Function Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      FunctionKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Function Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionKind get(int value) {
    switch (value) {
      case FUNCTION_VALUE: return FUNCTION;
      case DUPLICATE_VALUE: return DUPLICATE;
      case GATHER_VALUE: return GATHER;
      case SELECT_VALUE: return SELECT;
      case SPLIT_VALUE: return SPLIT;
      case ROUTE_VALUE: return ROUTE;
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
	private FunctionKind(int value, String name, String literal) {
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
	
} //FunctionKind
