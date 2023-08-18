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
 * A representation of the literals of the enumeration '<em><b>Binary Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getBinaryOperator()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the kind of this binary operator' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum BinaryOperator implements Enumerator {
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
   * The '<em><b>ADD</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ADD_VALUE
   * @generated
   * @ordered
   */
	ADD(1, "ADD", "ADD"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>MUL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #MUL_VALUE
   * @generated
   * @ordered
   */
	MUL(2, "MUL", "MUL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SUB</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SUB_VALUE
   * @generated
   * @ordered
   */
	SUB(3, "SUB", "SUB"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>DIV</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DIV_VALUE
   * @generated
   * @ordered
   */
	DIV(4, "DIV", "DIV"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>POW</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #POW_VALUE
   * @generated
   * @ordered
   */
	POW(5, "POW", "POW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>MIN</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #MIN_VALUE
   * @generated
   * @ordered
   */
	MIN(6, "MIN", "MIN"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>MAX</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #MAX_VALUE
   * @generated
   * @ordered
   */
	MAX(7, "MAX", "MAX"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>EQU</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #EQU_VALUE
   * @generated
   * @ordered
   */
	EQU(8, "EQU", "EQU"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>IOR</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #IOR_VALUE
   * @generated
   * @ordered
   */
	IOR(9, "IOR", "IOR"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>XOR</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #XOR_VALUE
   * @generated
   * @ordered
   */
	XOR(10, "XOR", "XOR"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>AND</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #AND_VALUE
   * @generated
   * @ordered
   */
	AND(11, "AND", "AND"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator is not initialized' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>ADD</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ADD</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ADD
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to an addition' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ADD_VALUE = 1;

	/**
   * The '<em><b>MUL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MUL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #MUL
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a multiplication' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int MUL_VALUE = 2;

	/**
   * The '<em><b>SUB</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SUB
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a substraction' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SUB_VALUE = 3;

	/**
   * The '<em><b>DIV</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIV</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DIV
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a division' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DIV_VALUE = 4;

	/**
   * The '<em><b>POW</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>POW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #POW
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a power operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int POW_VALUE = 5;

	/**
   * The '<em><b>MIN</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MIN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #MIN
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a min operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int MIN_VALUE = 6;

	/**
   * The '<em><b>MAX</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MAX</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #MAX
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a max operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int MAX_VALUE = 7;

	/**
   * The '<em><b>EQU</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EQU</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #EQU
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to an equal operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int EQU_VALUE = 8;

	/**
   * The '<em><b>IOR</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #IOR
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a logical inclusive OR operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int IOR_VALUE = 9;

	/**
   * The '<em><b>XOR</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #XOR
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a logical exclusive OR operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int XOR_VALUE = 10;

	/**
   * The '<em><b>AND</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #AND
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the binary operator refers to a logical AND operation' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int AND_VALUE = 11;

	/**
   * An array of all the '<em><b>Binary Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final BinaryOperator[] VALUES_ARRAY =
		new BinaryOperator[] {
      UNSET,
      ADD,
      MUL,
      SUB,
      DIV,
      POW,
      MIN,
      MAX,
      EQU,
      IOR,
      XOR,
      AND,
    };

	/**
   * A public read-only list of all the '<em><b>Binary Operator</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<BinaryOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Binary Operator</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static BinaryOperator get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      BinaryOperator result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Binary Operator</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static BinaryOperator getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      BinaryOperator result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Binary Operator</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static BinaryOperator get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case ADD_VALUE: return ADD;
      case MUL_VALUE: return MUL;
      case SUB_VALUE: return SUB;
      case DIV_VALUE: return DIV;
      case POW_VALUE: return POW;
      case MIN_VALUE: return MIN;
      case MAX_VALUE: return MAX;
      case EQU_VALUE: return EQU;
      case IOR_VALUE: return IOR;
      case XOR_VALUE: return XOR;
      case AND_VALUE: return AND;
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
	private BinaryOperator(int value, String name, String literal) {
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
	
} //BinaryOperator
