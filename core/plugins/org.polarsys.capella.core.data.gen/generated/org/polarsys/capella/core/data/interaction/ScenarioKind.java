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
package org.polarsys.capella.core.data.interaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Scenario Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getScenarioKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public enum ScenarioKind implements Enumerator {
	/**
   * The '<em><b>UNSET</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNSET_VALUE
   * @generated
   * @ordered
   */
	UNSET(4, "UNSET", "UNSET"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>INTERFACE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #INTERFACE_VALUE
   * @generated
   * @ordered
   */
	INTERFACE(0, "INTERFACE", "INTERFACE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>DATA FLOW</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DATA_FLOW_VALUE
   * @generated
   * @ordered
   */
	DATA_FLOW(1, "DATA_FLOW", "DATA_FLOW"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>INTERACTION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #INTERACTION_VALUE
   * @generated
   * @ordered
   */
	INTERACTION(2, "INTERACTION", "INTERACTION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FUNCTIONAL</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FUNCTIONAL_VALUE
   * @generated
   * @ordered
   */
	FUNCTIONAL(3, "FUNCTIONAL", "FUNCTIONAL"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 4;

	/**
   * The '<em><b>INTERFACE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTERFACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #INTERFACE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int INTERFACE_VALUE = 0;

	/**
   * The '<em><b>DATA FLOW</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATA FLOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DATA_FLOW
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DATA_FLOW_VALUE = 1;

	/**
   * The '<em><b>INTERACTION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTERACTION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #INTERACTION
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int INTERACTION_VALUE = 2;

	/**
   * The '<em><b>FUNCTIONAL</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FUNCTIONAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FUNCTIONAL
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FUNCTIONAL_VALUE = 3;

	/**
   * An array of all the '<em><b>Scenario Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ScenarioKind[] VALUES_ARRAY =
		new ScenarioKind[] {
      UNSET,
      INTERFACE,
      DATA_FLOW,
      INTERACTION,
      FUNCTIONAL,
    };

	/**
   * A public read-only list of all the '<em><b>Scenario Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ScenarioKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Scenario Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ScenarioKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ScenarioKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Scenario Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ScenarioKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ScenarioKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Scenario Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ScenarioKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case INTERFACE_VALUE: return INTERFACE;
      case DATA_FLOW_VALUE: return DATA_FLOW;
      case INTERACTION_VALUE: return INTERACTION;
      case FUNCTIONAL_VALUE: return FUNCTIONAL;
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
	private ScenarioKind(int value, String name, String literal) {
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
	
} //ScenarioKind
