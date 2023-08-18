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
package org.polarsys.capella.core.data.epbs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Configuration Item Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItemKind()
 * @model
 * @generated
 */
public enum ConfigurationItemKind implements Enumerator {
	/**
   * The '<em><b>Unset</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #UNSET_VALUE
   * @generated
   * @ordered
   */
	UNSET(0, "Unset", "Unset"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>COTSCI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #COTSCI_VALUE
   * @generated
   * @ordered
   */
	COTSCI(1, "COTSCI", "COTSCI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>CSCI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #CSCI_VALUE
   * @generated
   * @ordered
   */
	CSCI(2, "CSCI", "CSCI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>HWCI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #HWCI_VALUE
   * @generated
   * @ordered
   */
	HWCI(3, "HWCI", "HWCI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Interface CI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #INTERFACE_CI_VALUE
   * @generated
   * @ordered
   */
	INTERFACE_CI(4, "InterfaceCI", "InterfaceCI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>NDICI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #NDICI_VALUE
   * @generated
   * @ordered
   */
	NDICI(5, "NDICI", "NDICI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Prime Item CI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PRIME_ITEM_CI_VALUE
   * @generated
   * @ordered
   */
	PRIME_ITEM_CI(6, "PrimeItemCI", "PrimeItemCI"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>System CI</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SYSTEM_CI_VALUE
   * @generated
   * @ordered
   */
	SYSTEM_CI(7, "SystemCI", "SystemCI"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>Unset</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unset</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model name="Unset"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>COTSCI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COTSCI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #COTSCI
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Commercial Off The Shelves Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int COTSCI_VALUE = 1;

	/**
   * The '<em><b>CSCI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CSCI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #CSCI
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Computer Software Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int CSCI_VALUE = 2;

	/**
   * The '<em><b>HWCI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HWCI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #HWCI
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Hardware Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int HWCI_VALUE = 3;

	/**
   * The '<em><b>Interface CI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Interface CI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #INTERFACE_CI
   * @model name="InterfaceCI"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Interface Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int INTERFACE_CI_VALUE = 4;

	/**
   * The '<em><b>NDICI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NDICI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #NDICI
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Non Developmental Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int NDICI_VALUE = 5;

	/**
   * The '<em><b>Prime Item CI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Prime Item CI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PRIME_ITEM_CI
   * @model name="PrimeItemCI"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Prime Item Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int PRIME_ITEM_CI_VALUE = 6;

	/**
   * The '<em><b>System CI</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>System CI</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SYSTEM_CI
   * @model name="SystemCI"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='System Configuration Item' usage\040guideline='n/a' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   * @generated
   * @ordered
   */
	public static final int SYSTEM_CI_VALUE = 7;

	/**
   * An array of all the '<em><b>Configuration Item Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ConfigurationItemKind[] VALUES_ARRAY =
		new ConfigurationItemKind[] {
      UNSET,
      COTSCI,
      CSCI,
      HWCI,
      INTERFACE_CI,
      NDICI,
      PRIME_ITEM_CI,
      SYSTEM_CI,
    };

	/**
   * A public read-only list of all the '<em><b>Configuration Item Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ConfigurationItemKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Configuration Item Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ConfigurationItemKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ConfigurationItemKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Configuration Item Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ConfigurationItemKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ConfigurationItemKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Configuration Item Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ConfigurationItemKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case COTSCI_VALUE: return COTSCI;
      case CSCI_VALUE: return CSCI;
      case HWCI_VALUE: return HWCI;
      case INTERFACE_CI_VALUE: return INTERFACE_CI;
      case NDICI_VALUE: return NDICI;
      case PRIME_ITEM_CI_VALUE: return PRIME_ITEM_CI;
      case SYSTEM_CI_VALUE: return SYSTEM_CI;
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
	private ConfigurationItemKind(int value, String name, String literal) {
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
	
} //ConfigurationItemKind
