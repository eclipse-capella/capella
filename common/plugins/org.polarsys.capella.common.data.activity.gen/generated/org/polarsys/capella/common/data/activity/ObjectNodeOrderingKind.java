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
package org.polarsys.capella.common.data.activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Object Node Ordering Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNodeOrderingKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ObjectNodeOrderingKind is an enumeration indicating queuing order within a node\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNodeOrderingKind' explanation='none' constraints='none'"
 * @generated
 */
public enum ObjectNodeOrderingKind implements Enumerator {
	/**
   * The '<em><b>FIFO</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FIFO_VALUE
   * @generated
   * @ordered
   */
	FIFO(0, "FIFO", "FIFO"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>LIFO</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #LIFO_VALUE
   * @generated
   * @ordered
   */
	LIFO(1, "LIFO", "LIFO"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Ordered</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ORDERED_VALUE
   * @generated
   * @ordered
   */
	ORDERED(2, "ordered", "ordered"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Unordered</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNORDERED_VALUE
   * @generated
   * @ordered
   */
	UNORDERED(3, "unordered", "unordered"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FIFO</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FIFO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FIFO
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='First In First Out ordering' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNodeOrderingKind::FIFO' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FIFO_VALUE = 0;

	/**
   * The '<em><b>LIFO</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LIFO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #LIFO
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Last In First Out ordering' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNodeOrderingKind::LIFO' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int LIFO_VALUE = 1;

	/**
   * The '<em><b>Ordered</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Ordered</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ORDERED
   * @model name="ordered"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates that object node tokens are ordered.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNodeOrderingKind::ordered' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ORDERED_VALUE = 2;

	/**
   * The '<em><b>Unordered</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unordered</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNORDERED
   * @model name="unordered"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates that object node tokens are unordered.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ObjectNodeOrderingKind::unordered' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNORDERED_VALUE = 3;

	/**
   * An array of all the '<em><b>Object Node Ordering Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ObjectNodeOrderingKind[] VALUES_ARRAY =
		new ObjectNodeOrderingKind[] {
      FIFO,
      LIFO,
      ORDERED,
      UNORDERED,
    };

	/**
   * A public read-only list of all the '<em><b>Object Node Ordering Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ObjectNodeOrderingKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Object Node Ordering Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeOrderingKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ObjectNodeOrderingKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Object Node Ordering Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeOrderingKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ObjectNodeOrderingKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Object Node Ordering Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeOrderingKind get(int value) {
    switch (value) {
      case FIFO_VALUE: return FIFO;
      case LIFO_VALUE: return LIFO;
      case ORDERED_VALUE: return ORDERED;
      case UNORDERED_VALUE: return UNORDERED;
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
	private ObjectNodeOrderingKind(int value, String name, String literal) {
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
	
} //ObjectNodeOrderingKind
