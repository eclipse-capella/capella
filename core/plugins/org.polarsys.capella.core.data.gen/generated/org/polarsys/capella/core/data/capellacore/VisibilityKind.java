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
package org.polarsys.capella.core.data.capellacore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Visibility Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.capellacore.CapellacorePackage#getVisibilityKind()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='enumeration listing the various possibilities regarding the visibility of a feature of a class\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::VisibilityKind' explanation='none' constraints='none'"
 * @generated
 */
public enum VisibilityKind implements Enumerator {
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
   * The '<em><b>PUBLIC</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PUBLIC_VALUE
   * @generated
   * @ordered
   */
	PUBLIC(1, "PUBLIC", "PUBLIC"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PROTECTED</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PROTECTED_VALUE
   * @generated
   * @ordered
   */
	PROTECTED(2, "PROTECTED", "PROTECTED"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PRIVATE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PRIVATE_VALUE
   * @generated
   * @ordered
   */
	PRIVATE(3, "PRIVATE", "PRIVATE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PACKAGE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PACKAGE_VALUE
   * @generated
   * @ordered
   */
	PACKAGE(4, "PACKAGE", "PACKAGE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when visibility is not precised\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>PUBLIC</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PUBLIC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PUBLIC
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the feature offers public access\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::VisibilityKind::public' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PUBLIC_VALUE = 1;

	/**
   * The '<em><b>PROTECTED</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROTECTED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PROTECTED
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the feature offers restricted visibility, only to children of the class\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::VisibilityKind::protected' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PROTECTED_VALUE = 2;

	/**
   * The '<em><b>PRIVATE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PRIVATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PRIVATE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the feature is only visible/accessible from the class itself\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::VisibilityKind::private' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PRIVATE_VALUE = 3;

	/**
   * The '<em><b>PACKAGE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PACKAGE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PACKAGE
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the feature is accessible from any element stored within the same package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::VisibilityKind::private' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PACKAGE_VALUE = 4;

	/**
   * An array of all the '<em><b>Visibility Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final VisibilityKind[] VALUES_ARRAY =
		new VisibilityKind[] {
      UNSET,
      PUBLIC,
      PROTECTED,
      PRIVATE,
      PACKAGE,
    };

	/**
   * A public read-only list of all the '<em><b>Visibility Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<VisibilityKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static VisibilityKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      VisibilityKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static VisibilityKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      VisibilityKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static VisibilityKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case PUBLIC_VALUE: return PUBLIC;
      case PROTECTED_VALUE: return PROTECTED;
      case PRIVATE_VALUE: return PRIVATE;
      case PACKAGE_VALUE: return PACKAGE;
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
	private VisibilityKind(int value, String name, String literal) {
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
	
} //VisibilityKind
