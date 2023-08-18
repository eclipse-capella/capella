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
 * A representation of the literals of the enumeration '<em><b>Functional Chain Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Enumeration of the different functional chains\r\n[source:Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public enum FunctionalChainKind implements Enumerator {
	/**
   * The '<em><b>SIMPLE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SIMPLE_VALUE
   * @generated
   * @ordered
   */
	SIMPLE(0, "SIMPLE", "SIMPLE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>COMPOSITE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #COMPOSITE_VALUE
   * @generated
   * @ordered
   */
	COMPOSITE(1, "COMPOSITE", "COMPOSITE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FRAGMENT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FRAGMENT_VALUE
   * @generated
   * @ordered
   */
	FRAGMENT(2, "FRAGMENT", "FRAGMENT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SIMPLE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SIMPLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SIMPLE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='simple functional chain' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SIMPLE_VALUE = 0;

	/**
   * The '<em><b>COMPOSITE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPOSITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #COMPOSITE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='composite functional chain' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int COMPOSITE_VALUE = 1;

	/**
   * The '<em><b>FRAGMENT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FRAGMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FRAGMENT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='fragment functional chain' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FRAGMENT_VALUE = 2;

	/**
   * An array of all the '<em><b>Functional Chain Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final FunctionalChainKind[] VALUES_ARRAY =
		new FunctionalChainKind[] {
      SIMPLE,
      COMPOSITE,
      FRAGMENT,
    };

	/**
   * A public read-only list of all the '<em><b>Functional Chain Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<FunctionalChainKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Functional Chain Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionalChainKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      FunctionalChainKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Functional Chain Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionalChainKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      FunctionalChainKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Functional Chain Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static FunctionalChainKind get(int value) {
    switch (value) {
      case SIMPLE_VALUE: return SIMPLE;
      case COMPOSITE_VALUE: return COMPOSITE;
      case FRAGMENT_VALUE: return FRAGMENT;
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
	private FunctionalChainKind(int value, String name, String literal) {
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
	
} //FunctionalChainKind
