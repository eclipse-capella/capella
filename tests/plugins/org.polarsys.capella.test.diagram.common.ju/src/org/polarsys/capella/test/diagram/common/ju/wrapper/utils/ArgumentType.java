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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;

/**
 * Enum type defined in order to define the available arguments for ToolWrapper
 */
public enum ArgumentType {

  SOURCE("SOURCE", EObject.class, 0), //$NON-NLS-1$
  TARGET("TARGET", EObject.class, 1), //$NON-NLS-1$
  CONTAINER_VIEW("CONTAINER_VIEW", EObject.class, 2), //$NON-NLS-1$
  CONTAINER("CONTAINER", EObject.class, 3), //$NON-NLS-1$
  EDGE("EDGE", EObject.class, 4), //$NON-NLS-1$
  ON_DIAGRAM_ONLY("ON_DIAGRAM_ONLY", Boolean.class, 5), //$NON-NLS-1$
  // Table cell mask - can be "X" or ""
  TABLE_CELL_MASK("TABLE_CELL_MASK", String.class, 6), //$NON-NLS-1$
  COLLECTION("COLLECTION", Collection.class, 7), //$NON-NLS-1$
  PREDECESSOR("PREDECESSOR", EObject.class, 8), //$NON-NLS-1$
  STARTINGENDPREDECESSOR("STARTINGENDPREDECESSOR", EventEnd.class, 9), //$NON-NLS-1$
  FINISHINGENDPREDECESSOR("FINISHINGENDPREDECESSOR", EventEnd.class, 10), //$NON-NLS-1$
  DROPPEDELEMENT("DROPPEDELEMENT", EObject.class, 11); //$NON-NLS-1$

  /** Constructor */
  ArgumentType(String literal, Class<?> clazz, int value) {
    _literal = literal;
    _value = value;
    _clazz = clazz;
  }

  /** Understandable name of the enum */
  private String _literal;
  /** enum id, could be useful */
  private int _value;

  private Class<?> _clazz;

  /** accessor on the enum literal */
  public String getLiteral() {
    return _literal;
  }

  /** accessor on the enum value */
  public int getValue() {
    return _value;
  }

  /** accessor on the argument class type */
  public Class<?> getClassType() {
    return _clazz;
  }

}
