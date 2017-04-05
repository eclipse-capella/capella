/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>access Type</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsPackage#getaccess_Type()
 * @model
 * @generated
 */
public enum access_Type implements Enumerator {
  /**
   * The '<em><b>Flat</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #FLAT_VALUE
   * @generated
   * @ordered
   */
  FLAT(0, "flat", "flat"),

  /**
   * The '<em><b>Subcomponents</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #SUBCOMPONENTS_VALUE
   * @generated
   * @ordered
   */
  SUBCOMPONENTS(1, "subcomponents", "subcomponents"),

  /**
   * The '<em><b>Full</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #FULL_VALUE
   * @generated
   * @ordered
   */
  FULL(2, "full", "full");

  /**
   * The '<em><b>Flat</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Flat</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #FLAT
   * @model name="flat"
   * @generated
   * @ordered
   */
  public static final int FLAT_VALUE = 0;

  /**
   * The '<em><b>Subcomponents</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Subcomponents</b></em>' literal object isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #SUBCOMPONENTS
   * @model name="subcomponents"
   * @generated
   * @ordered
   */
  public static final int SUBCOMPONENTS_VALUE = 1;

  /**
   * The '<em><b>Full</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Full</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #FULL
   * @model name="full"
   * @generated
   * @ordered
   */
  public static final int FULL_VALUE = 2;

  /**
   * An array of all the '<em><b>access Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static final access_Type[] VALUES_ARRAY = new access_Type[] { FLAT, SUBCOMPONENTS, FULL, };

  /**
   * A public read-only list of all the '<em><b>access Type</b></em>' enumerators. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public static final List<access_Type> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>access Type</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param literal
   *          the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static access_Type get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      access_Type result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>access Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param name
   *          the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static access_Type getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      access_Type result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>access Type</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static access_Type get(int value) {
    switch (value) {
    case FLAT_VALUE:
      return FLAT;
    case SUBCOMPONENTS_VALUE:
      return SUBCOMPONENTS;
    case FULL_VALUE:
      return FULL;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private access_Type(int value, String name, String literal) {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public int getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getLiteral() {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String toString() {
    return literal;
  }

} // access_Type
