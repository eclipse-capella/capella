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
package org.polarsys.capella.core.data.fa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Component Exchange Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentExchangeKind()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ConnectionKind'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='ConnectorKind'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='ConnectorKind is an enumeration of the following literal values:\r\n- assembly\r\nIndicates that the connector is an assembly connector.\r\n- delegation\r\nIndicates that the connector is a delegation connector.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorKind' explanation='none' constraints='none'"
 * @generated
 */
public enum ComponentExchangeKind implements Enumerator {
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
   * The '<em><b>DELEGATION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DELEGATION_VALUE
   * @generated
   * @ordered
   */
	DELEGATION(1, "DELEGATION", "DELEGATION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>ASSEMBLY</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ASSEMBLY_VALUE
   * @generated
   * @ordered
   */
	ASSEMBLY(2, "ASSEMBLY", "ASSEMBLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FLOW</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FLOW_VALUE
   * @generated
   * @ordered
   */
	FLOW(3, "FLOW", "FLOW"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Communication kind is not set\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='' constraints='This value does not exist for uml::ConnectorKind'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>DELEGATION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELEGATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DELEGATION
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='DELEGATION'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates that the connector is a delegation connector.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorKind::delegation' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DELEGATION_VALUE = 1;

	/**
   * The '<em><b>ASSEMBLY</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASSEMBLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ASSEMBLY
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='ASSEMBLY'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Indicates that the connector is an assembly connector.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ConnectorKind::assembly' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ASSEMBLY_VALUE = 2;

	/**
   * The '<em><b>FLOW</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FLOW</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FLOW
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Describes a flow communication' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='' constraints='This value does not exist for uml::ConnectorKind'"
   * @generated
   * @ordered
   */
	public static final int FLOW_VALUE = 3;

	/**
   * An array of all the '<em><b>Component Exchange Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final ComponentExchangeKind[] VALUES_ARRAY =
		new ComponentExchangeKind[] {
      UNSET,
      DELEGATION,
      ASSEMBLY,
      FLOW,
    };

	/**
   * A public read-only list of all the '<em><b>Component Exchange Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<ComponentExchangeKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Component Exchange Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentExchangeKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ComponentExchangeKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Component Exchange Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentExchangeKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ComponentExchangeKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Component Exchange Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static ComponentExchangeKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case DELEGATION_VALUE: return DELEGATION;
      case ASSEMBLY_VALUE: return ASSEMBLY;
      case FLOW_VALUE: return FLOW;
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
	private ComponentExchangeKind(int value, String name, String literal) {
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
	
} //ComponentExchangeKind
