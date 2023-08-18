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
package org.polarsys.capella.core.data.pa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Physical Component Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponentKind()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalComponentType'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='PhysicalComponentType'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='allows to categorize a physical component, with respect to real life physical entities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public enum PhysicalComponentKind implements Enumerator {
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
   * The '<em><b>HARDWARE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #HARDWARE_VALUE
   * @generated
   * @ordered
   */
	HARDWARE(1, "HARDWARE", "HARDWARE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>HARDWARE COMPUTER</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #HARDWARE_COMPUTER_VALUE
   * @generated
   * @ordered
   */
	HARDWARE_COMPUTER(2, "HARDWARE_COMPUTER", "HARDWARE_COMPUTER"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SOFTWARE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_VALUE
   * @generated
   * @ordered
   */
	SOFTWARE(3, "SOFTWARE", "SOFTWARE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SOFTWARE DEPLOYMENT UNIT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_DEPLOYMENT_UNIT_VALUE
   * @generated
   * @ordered
   */
	SOFTWARE_DEPLOYMENT_UNIT(4, "SOFTWARE_DEPLOYMENT_UNIT", "SOFTWARE_DEPLOYMENT_UNIT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SOFTWARE EXECUTION UNIT</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_EXECUTION_UNIT_VALUE
   * @generated
   * @ordered
   */
	SOFTWARE_EXECUTION_UNIT(5, "SOFTWARE_EXECUTION_UNIT", "SOFTWARE_EXECUTION_UNIT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SOFTWARE APPLICATION</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_APPLICATION_VALUE
   * @generated
   * @ordered
   */
	SOFTWARE_APPLICATION(6, "SOFTWARE_APPLICATION", "SOFTWARE_APPLICATION"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FIRMWARE</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FIRMWARE_VALUE
   * @generated
   * @ordered
   */
	FIRMWARE(7, "FIRMWARE", "FIRMWARE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PERSON</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PERSON_VALUE
   * @generated
   * @ordered
   */
	PERSON(8, "PERSON", "PERSON"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>FACILITIES</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #FACILITIES_VALUE
   * @generated
   * @ordered
   */
	FACILITIES(9, "FACILITIES", "FACILITIES"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>DATA</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #DATA_VALUE
   * @generated
   * @ordered
   */
	DATA(10, "DATA", "DATA"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>MATERIALS</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #MATERIALS_VALUE
   * @generated
   * @ordered
   */
	MATERIALS(11, "MATERIALS", "MATERIALS"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>SERVICES</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #SERVICES_VALUE
   * @generated
   * @ordered
   */
	SERVICES(12, "SERVICES", "SERVICES"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>PROCESSES</b></em>' literal object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #PROCESSES_VALUE
   * @generated
   * @ordered
   */
	PROCESSES(13, "PROCESSES", "PROCESSES"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
   * The '<em><b>UNSET</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #UNSET
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component kind is not precised\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int UNSET_VALUE = 0;

	/**
   * The '<em><b>HARDWARE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HARDWARE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #HARDWARE
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='HARDWARE'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a hardware resource\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int HARDWARE_VALUE = 1;

	/**
   * The '<em><b>HARDWARE COMPUTER</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HARDWARE COMPUTER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #HARDWARE_COMPUTER
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='HARDWARE_COMPUTER'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a computing resource\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int HARDWARE_COMPUTER_VALUE = 2;

	/**
   * The '<em><b>SOFTWARE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOFTWARE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SOFTWARE
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SOFTWARE'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a software entity\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SOFTWARE_VALUE = 3;

	/**
   * The '<em><b>SOFTWARE DEPLOYMENT UNIT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOFTWARE DEPLOYMENT UNIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_DEPLOYMENT_UNIT
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SOFTWARE_DEPLOYMENT_UNIT'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a software deployment unit\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SOFTWARE_DEPLOYMENT_UNIT_VALUE = 4;

	/**
   * The '<em><b>SOFTWARE EXECUTION UNIT</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOFTWARE EXECUTION UNIT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_EXECUTION_UNIT
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SOFTWARE_EXECUTION_UNIT'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a software execution unit\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SOFTWARE_EXECUTION_UNIT_VALUE = 5;

	/**
   * The '<em><b>SOFTWARE APPLICATION</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SOFTWARE APPLICATION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SOFTWARE_APPLICATION
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SOFTWARE_APPLICATION'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a software application\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SOFTWARE_APPLICATION_VALUE = 6;

	/**
   * The '<em><b>FIRMWARE</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FIRMWARE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FIRMWARE
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='FIRMWARE'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a firmware part\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FIRMWARE_VALUE = 7;

	/**
   * The '<em><b>PERSON</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PERSON</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PERSON
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='PERSON'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component is a person\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PERSON_VALUE = 8;

	/**
   * The '<em><b>FACILITIES</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FACILITIES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #FACILITIES
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='FACILITIES'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component refers to Facilities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int FACILITIES_VALUE = 9;

	/**
   * The '<em><b>DATA</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATA</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #DATA
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='DATA'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component represents a set of data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int DATA_VALUE = 10;

	/**
   * The '<em><b>MATERIALS</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MATERIALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #MATERIALS
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='MATERIALS'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component represents a bunch of materials\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int MATERIALS_VALUE = 11;

	/**
   * The '<em><b>SERVICES</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SERVICES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #SERVICES
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SERVICES'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical components represents a set of services\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int SERVICES_VALUE = 12;

	/**
   * The '<em><b>PROCESSES</b></em>' literal value.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROCESSES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @see #PROCESSES
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='PROCESSES'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the physical component represents a set of processes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   * @ordered
   */
	public static final int PROCESSES_VALUE = 13;

	/**
   * An array of all the '<em><b>Physical Component Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static final PhysicalComponentKind[] VALUES_ARRAY =
		new PhysicalComponentKind[] {
      UNSET,
      HARDWARE,
      HARDWARE_COMPUTER,
      SOFTWARE,
      SOFTWARE_DEPLOYMENT_UNIT,
      SOFTWARE_EXECUTION_UNIT,
      SOFTWARE_APPLICATION,
      FIRMWARE,
      PERSON,
      FACILITIES,
      DATA,
      MATERIALS,
      SERVICES,
      PROCESSES,
    };

	/**
   * A public read-only list of all the '<em><b>Physical Component Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static final List<PhysicalComponentKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
   * Returns the '<em><b>Physical Component Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static PhysicalComponentKind get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      PhysicalComponentKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Physical Component Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static PhysicalComponentKind getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      PhysicalComponentKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

	/**
   * Returns the '<em><b>Physical Component Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
	public static PhysicalComponentKind get(int value) {
    switch (value) {
      case UNSET_VALUE: return UNSET;
      case HARDWARE_VALUE: return HARDWARE;
      case HARDWARE_COMPUTER_VALUE: return HARDWARE_COMPUTER;
      case SOFTWARE_VALUE: return SOFTWARE;
      case SOFTWARE_DEPLOYMENT_UNIT_VALUE: return SOFTWARE_DEPLOYMENT_UNIT;
      case SOFTWARE_EXECUTION_UNIT_VALUE: return SOFTWARE_EXECUTION_UNIT;
      case SOFTWARE_APPLICATION_VALUE: return SOFTWARE_APPLICATION;
      case FIRMWARE_VALUE: return FIRMWARE;
      case PERSON_VALUE: return PERSON;
      case FACILITIES_VALUE: return FACILITIES;
      case DATA_VALUE: return DATA;
      case MATERIALS_VALUE: return MATERIALS;
      case SERVICES_VALUE: return SERVICES;
      case PROCESSES_VALUE: return PROCESSES;
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
	private PhysicalComponentKind(int value, String name, String literal) {
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
	
} //PhysicalComponentKind
