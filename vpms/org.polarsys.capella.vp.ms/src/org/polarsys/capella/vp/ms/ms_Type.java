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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>ms Type</b></em>', and utility
 * methods for working with them. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsPackage#getms_Type()
 * @model
 * @generated
 */
public enum ms_Type implements Enumerator {
  /**
   * The '<em><b>Mixed</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #MIXED_VALUE
   * @generated
   * @ordered
   */
  MIXED(0, "mixed", "mixed"),

  /**
   * The '<em><b>State</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #STATE_VALUE
   * @generated
   * @ordered
   */
  STATE(1, "state", "state"),

  /**
   * The '<em><b>Mode</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #MODE_VALUE
   * @generated
   * @ordered
   */
  MODE(2, "mode", "mode");

  /**
   * The '<em><b>Mixed</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Mixed</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #MIXED
   * @model name="mixed"
   * @generated
   * @ordered
   */
  public static final int MIXED_VALUE = 0;

  /**
   * The '<em><b>State</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>State</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #STATE
   * @model name="state"
   * @generated
   * @ordered
   */
  public static final int STATE_VALUE = 1;

  /**
   * The '<em><b>Mode</b></em>' literal value. <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Mode</b></em>' literal object isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @see #MODE
   * @model name="mode"
   * @generated
   * @ordered
   */
  public static final int MODE_VALUE = 2;

  /**
   * An array of all the '<em><b>ms Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static final ms_Type[] VALUES_ARRAY = new ms_Type[] { MIXED, STATE, MODE, };

  /**
   * A public read-only list of all the '<em><b>ms Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @generated
   */
  public static final List<ms_Type> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>ms Type</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param literal
   *          the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static ms_Type get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ms_Type result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>ms Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   * 
   * @param name
   *          the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static ms_Type getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ms_Type result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>ms Type</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @param value
   *          the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static ms_Type get(int value) {
    switch (value) {
    case MIXED_VALUE:
      return MIXED;
    case STATE_VALUE:
      return STATE;
    case MODE_VALUE:
      return MODE;
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
  private ms_Type(int value, String name, String literal) {
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

} // ms_Type
