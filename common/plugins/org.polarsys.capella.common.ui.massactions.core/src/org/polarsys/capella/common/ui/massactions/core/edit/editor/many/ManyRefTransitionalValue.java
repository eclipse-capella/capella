/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.edit.editor.many;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

/**
 * When multiple "many" references are edited, the new selected values must be added to the existing ones, or some
 * existing values must be removed. This edition is therefore context dependent, since a list of the existing values for
 * the current cell is needed. This class represents a "transitional" value, that can adapt the common selected values
 * according to the context (existing cell values).
 *
 * @author Sandu Postaru
 * 
 */
public class ManyRefTransitionalValue {

  /**
   * Common user selected values.
   */
  private List<EObject> commonSelectedValues;

  /**
   * Common existing cell values.
   */
  private List<EObject> commonCurrentValues;

  public ManyRefTransitionalValue(List<EObject> commonSelectedValues, List<EObject> commonCurrentValues) {
    this.commonSelectedValues = commonSelectedValues;
    this.commonCurrentValues = commonCurrentValues;
  }

  public List<EObject> getCommonSelectedValues() {
    return commonSelectedValues;
  }

  /**
   * Adapts the current transitional value according to the current context.
   * 
   * @param currentCellValues
   *          the current context
   * @return an adapted value that replaces the existing cell value.
   */
  public List<EObject> adapt(List<EObject> currentCellValues) {

    Set<EObject> valuesToAdd = new HashSet<>(commonSelectedValues);
    valuesToAdd.removeAll(currentCellValues);

    Set<EObject> valuesToRemove = new HashSet<>(commonCurrentValues);
    valuesToRemove.removeAll(commonSelectedValues);

    Set<EObject> newValues = new HashSet<>(currentCellValues);
    newValues.removeAll(valuesToRemove);
    newValues.addAll(valuesToAdd);

    return new ArrayList<>(newValues);
  }
}
