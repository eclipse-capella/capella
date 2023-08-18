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
package org.polarsys.capella.core.data.information;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Aggregation Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.InformationPackage#getAggregationKind()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AggregationKind'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='AggregationKind'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='defines the specific kind of a relationship, as per UML definitions\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AggregationKind' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public enum AggregationKind implements Enumerator {
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
   * The '<em><b>ASSOCIATION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #ASSOCIATION_VALUE
   * @generated
   * @ordered
   */
	ASSOCIATION(1, "ASSOCIATION", "ASSOCIATION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>AGGREGATION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #AGGREGATION_VALUE
   * @generated
   * @ordered
   */
	AGGREGATION(2, "AGGREGATION", "AGGREGATION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>COMPOSITION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #COMPOSITION_VALUE
   * @generated
   * @ordered
   */
	COMPOSITION(3, "COMPOSITION", "COMPOSITION"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when value is not defined by the user\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='This enumeration literal has no UML-SysML equivalence'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>ASSOCIATION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASSOCIATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #ASSOCIATION
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='NONE'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An association specifies a semantic relationship that can occur between typed instances. It has at least two ends\r\nrepresented by properties, each of which is connected to the type of the end. More than one end of the association may\r\nhave the same type.\r\n[source: UML superstructure v2.2]\r\n\r\nIndicates that the property has no aggregation.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AggregationKind::none' explanation='' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int ASSOCIATION_VALUE = 1;

	/**
   * The '<em><b>AGGREGATION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AGGREGATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #AGGREGATION
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SHARED'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An aggregation specifies a semantic relationship between a part and a whole. The part has a lifecycle of its own, and is potentially shared among several aggregators\r\n[source: Capella study]\r\n\r\nIndicates that the property has a shared aggregation.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AggregationKind::shared' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int AGGREGATION_VALUE = 2;

	/**
   * The '<em><b>COMPOSITION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPOSITION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #COMPOSITION
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='COMPOSITE'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A composition specifies a semantic relationship between whole and its parts. The parts lifecycles are tied to that of the whole, and they are not shared with any other aggregator.\r\n[source: Capella study]\r\n\r\nIndicates that the property is aggregated compositely, i.e., the composite object has responsibility for the existence\r\nand storage of the composed objects.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::AggregationKind::composite' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int COMPOSITION_VALUE = 3;

	/**
   * An array of all the '<em><b>Aggregation Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final AggregationKind[] VALUES_ARRAY =
		new AggregationKind[] {
      UNSET,
      ASSOCIATION,
      AGGREGATION,
      COMPOSITION,
    };

	/**
   * A public read-only list of all the '<em><b>Aggregation Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<AggregationKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static AggregationKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      AggregationKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static AggregationKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      AggregationKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static AggregationKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case ASSOCIATION_VALUE: return ASSOCIATION;
      case AGGREGATION_VALUE: return AGGREGATION;
      case COMPOSITION_VALUE: return COMPOSITION;
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
	private AggregationKind(int value, String name, String literal) {
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
	
} //AggregationKind
