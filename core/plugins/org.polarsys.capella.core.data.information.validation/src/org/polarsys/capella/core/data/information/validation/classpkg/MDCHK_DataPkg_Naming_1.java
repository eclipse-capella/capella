/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.classpkg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * This check insures that an element doesn't contain a naming conflict between different type of elements in same package.
 */
public class MDCHK_DataPkg_Naming_1 extends AbstractValidationRule {
  /**
   * Type suffix for validation error console
   */
  private static final String TYPE_SUFFIX = ") "; //$NON-NLS-1$
  /**
   * Type prefix for validation error console
   */
  private static final String TYPE_PREFIX = " ("; //$NON-NLS-1$

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // check "naming conflicts" under dataPkg
      if (eObj instanceof DataPkg) {
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        boolean hasConflict = false;
        // Creates a Map which will map each unique instance name with a list of the instances type
        Map<String, List<String>> nameAndTypes = new HashMap<String, List<String>>();
        CapellaElement elt = (CapellaElement) eObj;
        for (EObject obj : elt.eContents()) {
          // Iterates over the inner elements
          if (obj instanceof AbstractNamedElement) {
            AbstractNamedElement namedElement = (AbstractNamedElement) obj;
            // Gets the name and the type of the current element
            String currentElementName = namedElement.getName();
            String currentElementType = namedElement.eClass().getName();
            if (!(namedElement instanceof DataValue && (currentElementName.equalsIgnoreCase(ICommonConstants.EMPTY_STRING) || currentElementName
                .equalsIgnoreCase("null")))) { //$NON-NLS-1$
              if (containsIgnoredCaseString(nameAndTypes.keySet(), currentElementName)) {
                // if (nameAndTypes.containsKey(currentElementName)) {
                List<String> list = nameAndTypes.get(getIgnoredCaseString(nameAndTypes.keySet(), currentElementName));
                // create the map entry with unique type
                if (!list.contains(currentElementType)) {
                  list.add(currentElementType);
                }
              } else {
                // This type doesn't have a map entry
                // Creates the map entry for the type
                List<String> typesName = new ArrayList<String>();
                typesName.add(currentElementType);
                nameAndTypes.put(currentElementName, typesName);
              }
            }
          }
        }

        // For each unique instance name
        for (String key : nameAndTypes.keySet()) {
          List<String> list = nameAndTypes.get(key);
          // Here we check weather each instance name has naming conflict with different types of elements
          if (list.size() > 1) {
            String conflictTypeNames = ""; //$NON-NLS-1$
            for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
              conflictTypeNames = conflictTypeNames + iter.next();
              if (iter.hasNext()) {
                conflictTypeNames = conflictTypeNames + ", "; //$NON-NLS-1$
              }
            }
            // There is a conflict!!
            hasConflict = true;
            // Creates a conflict status
            String currentConflict = TYPE_PREFIX + conflictTypeNames + TYPE_SUFFIX + key;
            IStatus failureStatus = ctx.createFailureStatus(new Object[] { elt.eClass().getName(), elt.getLabel(), currentConflict });
            statuses.add(failureStatus);
          }
        }

        if (hasConflict) {
          // There are conflicts
          // Returns them as a multi-statuses status
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    // No conflict found
    return ctx.createSuccessStatus();
  }

  public boolean containsIgnoredCaseString(Set<String> list, String str) {
    for (String string : list) {
      if (string.equalsIgnoreCase(str)) {
        return true;
      }
    }
    return false;
  }

  public String getIgnoredCaseString(Set<String> list, String str) {
    for (String string : list) {
      if (string.equalsIgnoreCase(str)) {
        return string;
      }
    }
    return "";//$NON-NLS-1$
  }
}
