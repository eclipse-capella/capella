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
 * A representation of the literals of the enumeration '<em><b>Object Node Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getObjectNodeKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of behavior of the object node with respect to incoming data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
 * @generated
 */
public enum ObjectNodeKind implements Enumerator {
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
   * The '<em><b>No Buffer</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #NO_BUFFER_VALUE
   * @generated
   * @ordered
   */
	NO_BUFFER(1, "NoBuffer", "NoBuffer"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Overwrite</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #OVERWRITE_VALUE
   * @generated
   * @ordered
   */
	OVERWRITE(2, "Overwrite", "Overwrite"); //$NON-NLS-1$ //$NON-NLS-2$

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
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Used when incoming object node management policy is not precised' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSPECIFIED_VALUE = 0;

	/**
   * The '<em><b>No Buffer</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No Buffer</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #NO_BUFFER
   * @model name="NoBuffer"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='When the \"nobuffer\" stereotype is applied to object nodes, tokens arriving at the node are discarded if they are refused by\r\noutgoing edges, or refused by actions for object nodes that are input pins\r\n[source: SysML specification v1.1]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int NO_BUFFER_VALUE = 1;

	/**
   * The '<em><b>Overwrite</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Overwrite</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #OVERWRITE
   * @model name="Overwrite"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='When the \"overwrite\" stereotype is applied to object nodes, a token arriving at a full object node replaces the ones\r\nalready there (a full object node has as many tokens as allowed by its upper bound)\r\n[source: SysML specification v1.1]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int OVERWRITE_VALUE = 2;

	/**
   * An array of all the '<em><b>Object Node Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ObjectNodeKind[] VALUES_ARRAY =
		new ObjectNodeKind[] {
      UNSPECIFIED,
      NO_BUFFER,
      OVERWRITE,
    };

	/**
   * A public read-only list of all the '<em><b>Object Node Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ObjectNodeKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Object Node Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ObjectNodeKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Object Node Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ObjectNodeKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Object Node Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ObjectNodeKind get(int value) {
    switch (value) {
      case UNSPECIFIED_VALUE: return UNSPECIFIED;
      case NO_BUFFER_VALUE: return NO_BUFFER;
      case OVERWRITE_VALUE: return OVERWRITE;
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
	private ObjectNodeKind(int value, String name, String literal) {
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
	
} //ObjectNodeKind
