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
 * A representation of the literals of the enumeration '<em><b>Parameter Effect Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getParameterEffectKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The datatype ParameterEffectKind is an enumeration that indicates the effect of a behavior on values passed in or out of\r\nits parameters\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterEffectKind' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum ParameterEffectKind implements Enumerator {
	/**
   * The '<em><b>Create</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #CREATE_VALUE
   * @generated
   * @ordered
   */
	CREATE(2, "create", "create"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Read</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #READ_VALUE
   * @generated
   * @ordered
   */
	READ(0, "read", "read"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Update</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UPDATE_VALUE
   * @generated
   * @ordered
   */
	UPDATE(1, "update", "update"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Delete</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DELETE_VALUE
   * @generated
   * @ordered
   */
	DELETE(3, "delete", "delete"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Create</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Create</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #CREATE
   * @model name="create"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='referenced parameter value is being created upon behavior execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterEffectKind::create' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int CREATE_VALUE = 2;

	/**
   * The '<em><b>Read</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Read</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #READ
   * @model name="read"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='referenced parameter value is only being read upon behavior execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterEffectKind::read' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int READ_VALUE = 0;

	/**
   * The '<em><b>Update</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Update</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UPDATE
   * @model name="update"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='referenced parameter value is being updated upon behavior execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterEffectKind::update' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UPDATE_VALUE = 1;

	/**
   * The '<em><b>Delete</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Delete</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DELETE
   * @model name="delete"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='referenced parameter value is being deleted upon behavior execution\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ParameterEffectKind::delete' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DELETE_VALUE = 3;

	/**
   * An array of all the '<em><b>Parameter Effect Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ParameterEffectKind[] VALUES_ARRAY =
		new ParameterEffectKind[] {
      CREATE,
      READ,
      UPDATE,
      DELETE,
    };

	/**
   * A public read-only list of all the '<em><b>Parameter Effect Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ParameterEffectKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Parameter Effect Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterEffectKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ParameterEffectKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Parameter Effect Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterEffectKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ParameterEffectKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Parameter Effect Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ParameterEffectKind get(int value) {
    switch (value) {
      case CREATE_VALUE: return CREATE;
      case READ_VALUE: return READ;
      case UPDATE_VALUE: return UPDATE;
      case DELETE_VALUE: return DELETE;
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
	private ParameterEffectKind(int value, String name, String literal) {
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
	
} //ParameterEffectKind
