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
package org.polarsys.capella.core.data.information.datavalue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Unary Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getUnaryOperator()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the kind of this unary operator' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum UnaryOperator implements Enumerator {
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
   * The '<em><b>NOT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #NOT_VALUE
   * @generated
   * @ordered
   */
	NOT(1, "NOT", "NOT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>POS</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #POS_VALUE
   * @generated
   * @ordered
   */
	POS(2, "POS", "POS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>VAL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #VAL_VALUE
   * @generated
   * @ordered
   */
	VAL(3, "VAL", "VAL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SUC</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SUC_VALUE
   * @generated
   * @ordered
   */
	SUC(4, "SUC", "SUC"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PRE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PRE_VALUE
   * @generated
   * @ordered
   */
	PRE(5, "PRE", "PRE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator is not initialized' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>NOT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #NOT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator refers to a NOT operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int NOT_VALUE = 1;

	/**
   * The '<em><b>POS</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #POS
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator refers to a position operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int POS_VALUE = 2;

	/**
   * The '<em><b>VAL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>VAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #VAL
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator refers to a value operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int VAL_VALUE = 3;

	/**
   * The '<em><b>SUC</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SUC
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator refers to a successor operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SUC_VALUE = 4;

	/**
   * The '<em><b>PRE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PRE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the unary operator refers to a predecessor operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PRE_VALUE = 5;

	/**
   * An array of all the '<em><b>Unary Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final UnaryOperator[] VALUES_ARRAY =
		new UnaryOperator[] {
      UNSET,
      NOT,
      POS,
      VAL,
      SUC,
      PRE,
    };

	/**
   * A public read-only list of all the '<em><b>Unary Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<UnaryOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Unary Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static UnaryOperator get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      UnaryOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Unary Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static UnaryOperator getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      UnaryOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Unary Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static UnaryOperator get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case NOT_VALUE: return NOT;
      case POS_VALUE: return POS;
      case VAL_VALUE: return VAL;
      case SUC_VALUE: return SUC;
      case PRE_VALUE: return PRE;
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
	private UnaryOperator(int value, String name, String literal) {
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
	
} //UnaryOperator
