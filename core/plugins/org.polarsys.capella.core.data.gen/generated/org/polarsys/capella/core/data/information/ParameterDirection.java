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
 * A representation of the literals of the enumeration '<em><b>Parameter Direction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.InformationPackage#getParameterDirection()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ParameterDirection'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='ParameterDirectionKind'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the direction in which data is passed along through a parameter \r\n[source: Capella study]' constraints='none' comment/notes='could be renamed ParameterDirectionKind to match UML'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterDirectionKind' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum ParameterDirection implements Enumerator {
	/**
   * The '<em><b>IN</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #IN_VALUE
   * @generated
   * @ordered
   */
	IN(0, "IN", "IN"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>OUT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #OUT_VALUE
   * @generated
   * @ordered
   */
	OUT(1, "OUT", "OUT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>INOUT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #INOUT_VALUE
   * @generated
   * @ordered
   */
	INOUT(2, "INOUT", "INOUT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>RETURN</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #RETURN_VALUE
   * @generated
   * @ordered
   */
	RETURN(3, "RETURN", "RETURN"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>EXCEPTION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #EXCEPTION_VALUE
   * @generated
   * @ordered
   */
	EXCEPTION(4, "EXCEPTION", "EXCEPTION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNSET_VALUE
   * @generated
   * @ordered
   */
	UNSET(5, "UNSET", "UNSET"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>IN</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #IN
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='IN'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the parameter represents an input of the operation it is used in\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterDirectionKind::in' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int IN_VALUE = 0;

	/**
   * The '<em><b>OUT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #OUT
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='OUT'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the parameter represents an output of the operation it is used in\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterDirectionKind::out' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int OUT_VALUE = 1;

	/**
   * The '<em><b>INOUT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INOUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #INOUT
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='INOUT'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the parameter represents both an input and on output of the operation it is used in\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterDirectionKind::inout' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int INOUT_VALUE = 2;

	/**
   * The '<em><b>RETURN</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RETURN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #RETURN
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='RETURN'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the parameter represents the return value of the operation it is used in\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterDirectionKind::return' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int RETURN_VALUE = 3;

	/**
   * The '<em><b>EXCEPTION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EXCEPTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #EXCEPTION
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the parameter is like an exception' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int EXCEPTION_VALUE = 4;

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
	public static final int UNSET_VALUE = 5;

	/**
   * An array of all the '<em><b>Parameter Direction</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ParameterDirection[] VALUES_ARRAY =
		new ParameterDirection[] {
      IN,
      OUT,
      INOUT,
      RETURN,
      EXCEPTION,
      UNSET,
    };

	/**
   * A public read-only list of all the '<em><b>Parameter Direction</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ParameterDirection> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Parameter Direction</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterDirection get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ParameterDirection result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Parameter Direction</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterDirection getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ParameterDirection result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Parameter Direction</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterDirection get(int value) {
    switch (value) {
      case IN_VALUE: return IN;
      case OUT_VALUE: return OUT;
      case INOUT_VALUE: return INOUT;
      case RETURN_VALUE: return RETURN;
      case EXCEPTION_VALUE: return EXCEPTION;
      case UNSET_VALUE: return UNSET;
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
	private ParameterDirection(int value, String name, String literal) {
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
	
} //ParameterDirection
