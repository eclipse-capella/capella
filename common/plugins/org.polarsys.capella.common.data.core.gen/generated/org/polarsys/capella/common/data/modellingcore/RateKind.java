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
package org.polarsys.capella.common.data.modellingcore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Rate Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getRateKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='enumeration containing the possible caracterizations for the rate of a streaming parameter\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Stereotype that inherits from SysML::Activities::Rate (SysML::Activities::Continuous or SysML::Activities::Discrete)' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum RateKind implements Enumerator {
	/**
   * The '<em><b>Unspecified</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNSPECIFIED_VALUE
   * @generated
   * @ordered
   */
	UNSPECIFIED(0, "Unspecified", "Unspecified"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Continuous</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #CONTINUOUS_VALUE
   * @generated
   * @ordered
   */
	CONTINUOUS(1, "Continuous", "Continuous"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Discrete</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DISCRETE_VALUE
   * @generated
   * @ordered
   */
	DISCRETE(2, "Discrete", "Discrete"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Unspecified</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unspecified</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSPECIFIED
   * @model name="Unspecified"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the rate kind is not precised\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='Neither SysML::Activities::Continuous or SysML::Activities::Discrete stereotypes are applied' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSPECIFIED_VALUE = 0;

	/**
   * The '<em><b>Continuous</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Continuous</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #CONTINUOUS
   * @model name="Continuous"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the rate characterizes a continuous flow\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='SysML::Activities::Continuous' explanation='SysML::Activities::Continuous stereotype is applied' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int CONTINUOUS_VALUE = 1;

	/**
   * The '<em><b>Discrete</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Discrete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DISCRETE
   * @model name="Discrete"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when the rate characterizes a discrete flow\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='SysML::Activities::Discrete' explanation='SysML::Activities::Discrete' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DISCRETE_VALUE = 2;

	/**
   * An array of all the '<em><b>Rate Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final RateKind[] VALUES_ARRAY =
		new RateKind[] {
      UNSPECIFIED,
      CONTINUOUS,
      DISCRETE,
    };

	/**
   * A public read-only list of all the '<em><b>Rate Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<RateKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Rate Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static RateKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      RateKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Rate Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static RateKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      RateKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Rate Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static RateKind get(int value) {
    switch (value) {
      case UNSPECIFIED_VALUE: return UNSPECIFIED;
      case CONTINUOUS_VALUE: return CONTINUOUS;
      case DISCRETE_VALUE: return DISCRETE;
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
	private RateKind(int value, String name, String literal) {
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
	
} //RateKind
