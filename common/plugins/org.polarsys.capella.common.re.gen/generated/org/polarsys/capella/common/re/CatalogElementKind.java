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
package org.polarsys.capella.common.re;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Catalog Element Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.re.RePackage#getCatalogElementKind()
 * @model
 * @generated
 */
public enum CatalogElementKind implements Enumerator {
	/**
   * The '<em><b>REC</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #REC_VALUE
   * @generated
   * @ordered
   */
	REC(0, "REC", "REC"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>RPL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #RPL_VALUE
   * @generated
   * @ordered
   */
	RPL(1, "RPL", "RPL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>REC RPL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #REC_RPL_VALUE
   * @generated
   * @ordered
   */
	REC_RPL(2, "REC_RPL", "REC_RPL"), /**
   * The '<em><b>GROUPING</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #GROUPING_VALUE
   * @generated
   * @ordered
   */
	GROUPING(3, "GROUPING", "GROUPING"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>REC</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #REC
   * @model
   * @generated
   * @ordered
   */
	public static final int REC_VALUE = 0;

	/**
   * The '<em><b>RPL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RPL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #RPL
   * @model
   * @generated
   * @ordered
   */
	public static final int RPL_VALUE = 1;

	/**
   * The '<em><b>REC RPL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REC RPL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #REC_RPL
   * @model
   * @generated
   * @ordered
   */
	public static final int REC_RPL_VALUE = 2;

	/**
   * The '<em><b>GROUPING</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GROUPING</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #GROUPING
   * @model
   * @generated
   * @ordered
   */
	public static final int GROUPING_VALUE = 3;

	/**
   * An array of all the '<em><b>Catalog Element Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final CatalogElementKind[] VALUES_ARRAY =
		new CatalogElementKind[] {
      REC,
      RPL,
      REC_RPL,
      GROUPING,
    };

	/**
   * A public read-only list of all the '<em><b>Catalog Element Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<CatalogElementKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Catalog Element Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CatalogElementKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CatalogElementKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Catalog Element Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CatalogElementKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CatalogElementKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Catalog Element Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static CatalogElementKind get(int value) {
    switch (value) {
      case REC_VALUE: return REC;
      case RPL_VALUE: return RPL;
      case REC_RPL_VALUE: return REC_RPL;
      case GROUPING_VALUE: return GROUPING;
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
	private CatalogElementKind(int value, String name, String literal) {
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
	
} //CatalogElementKind
