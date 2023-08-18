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
package org.polarsys.capella.core.data.capellacommon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transition Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getTransitionKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='TransitionKind is an enumeration type.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TransitionKind' explanation='none' constraints='none'"
 * @generated
 */
public enum TransitionKind implements Enumerator {
	/**
   * The '<em><b>Internal</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #INTERNAL_VALUE
   * @generated
   * @ordered
   */
	INTERNAL(0, "internal", "internal"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Local</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #LOCAL_VALUE
   * @generated
   * @ordered
   */
	LOCAL(1, "local", "local"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>External</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #EXTERNAL_VALUE
   * @generated
   * @ordered
   */
	EXTERNAL(2, "external", "external"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Internal</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Internal</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #INTERNAL
   * @model name="internal"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='kind=internal implies that the transition, if triggered, occurs without exiting or entering the source state. Thus, it does not\r\ncause a state change. This means that the entry or exit condition of the source state will not be invoked. An internal\r\ntransition can be taken even if the state machine is in one or more regions nested within this state.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TransitionKind::internal' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int INTERNAL_VALUE = 0;

	/**
   * The '<em><b>Local</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #LOCAL
   * @model name="local"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='kind=local implies that the transition, if triggered, will not exit the composite (source) state, but it will apply to any state\r\nwithin the composite state, and these will be exited and entered.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TransitionKind::local' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int LOCAL_VALUE = 1;

	/**
   * The '<em><b>External</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>External</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #EXTERNAL
   * @model name="external"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='kind=external implies that the transition, if triggered, will exit the composite (source) state.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TransitionKind::external' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int EXTERNAL_VALUE = 2;

	/**
   * An array of all the '<em><b>Transition Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final TransitionKind[] VALUES_ARRAY =
		new TransitionKind[] {
      INTERNAL,
      LOCAL,
      EXTERNAL,
    };

	/**
   * A public read-only list of all the '<em><b>Transition Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<TransitionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Transition Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TransitionKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      TransitionKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Transition Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TransitionKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      TransitionKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Transition Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TransitionKind get(int value) {
    switch (value) {
      case INTERNAL_VALUE: return INTERNAL;
      case LOCAL_VALUE: return LOCAL;
      case EXTERNAL_VALUE: return EXTERNAL;
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
	private TransitionKind(int value, String name, String literal) {
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
	
} //TransitionKind
