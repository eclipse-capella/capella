/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.capellacommon;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Event Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacommon.CapellacommonPackage#getTimeEventKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='TimeEventKind is an enumeration type.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TransitionKind' explanation='none' constraints='none'"
 * @generated
 */
public enum TimeEventKind implements Enumerator {
	//$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>AT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #AT_VALUE
   * @generated
   * @ordered
   */
	AT(0, "AT", "AT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>AFTER</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #AFTER_VALUE
   * @generated
   * @ordered
   */
	AFTER(1, "AFTER", "AFTER"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>AT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #AT
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An absolute time trigger is specified with the keyword \'at\' followed by an expression that\r\nevaluates to a time value, such as \'Jan. 1, 2000, Noon\'.\r\n[source: UML superstructure v2.4]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeEvent::isRelative = false' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int AT_VALUE = 0;

	/**
   * The '<em><b>AFTER</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AFTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #AFTER
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A relative time trigger is specified with the keyword \'after\' followed by an expression that evaluates to a time value, such\r\nas \'after (5 seconds)\'.\r\n[source: UML superstructure v2.4]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::TimeEvent::isRelative = true' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int AFTER_VALUE = 1;

	/**
   * An array of all the '<em><b>Time Event Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final TimeEventKind[] VALUES_ARRAY =
		new TimeEventKind[] {
      AT,
      AFTER,
    };

	/**
   * A public read-only list of all the '<em><b>Time Event Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<TimeEventKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Time Event Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TimeEventKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      TimeEventKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Time Event Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TimeEventKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      TimeEventKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Time Event Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static TimeEventKind get(int value) {
    switch (value) {
      case AT_VALUE: return AT;
      case AFTER_VALUE: return AFTER;
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
	private TimeEventKind(int value, String name, String literal) {
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
	
} //TimeEventKind
