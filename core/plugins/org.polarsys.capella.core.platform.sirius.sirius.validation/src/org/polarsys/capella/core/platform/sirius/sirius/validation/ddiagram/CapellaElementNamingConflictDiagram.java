/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.sirius.validation.ddiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This is a  rule for diagram naming conflicts rules
 */
public class CapellaElementNamingConflictDiagram extends AbstractValidationRule {

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  /**
   * Type suffix for validation error console
   */
  private static final String TYPE_SUFFIX = ") "; //$NON-NLS-1$
  /**
   * Type prefix for validation error console
   */
  private static final String TYPE_PREFIX = " ("; //$NON-NLS-1$

  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();
    if (eType == EMFEventType.NULL) {

      // Do not check "naming conflicts" under scenarios and capabilities
      if (eObj instanceof CapellaElement) {
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        boolean hasConflict = false;
        // Creates a Map which will map each type with a list of the instances names of this type
        Map<String, List<String>> typesAndNames = new HashMap<String, List<String>>();
        CapellaElement elt = (CapellaElement) eObj;

        // look for diagram naming conflict
        if (!(elt instanceof AbstractRelationship)) {
          // get session
          final Session curSession = SessionManager.INSTANCE.getSession(elt);
          if (curSession != null) {
            // get all diagram element contained by given element
            final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getRepresentations(elt, curSession);
            for (DRepresentation dRepresentation : allRepresentations) {
              if (dRepresentation instanceof DDiagram) {
                DDiagram diagram = (DDiagram) dRepresentation;
                // Gets the name and the type of the current element
                String currentElementName = diagram.getName();
                String currentElementType = diagram.eClass().getName();
                hasConflict = checkTheNamingConflict(ctx_p, statuses, hasConflict, typesAndNames, elt, currentElementName, currentElementType);
              }
            }
          }
        }

        if (hasConflict) {
          // There are conflicts
          // Returns them as a multiStatuses status
          return ConstraintStatus.createMultiStatus(ctx_p, statuses);
        }
      }
    }
    // No conflict found
    return ctx_p.createSuccessStatus();
  }

  /**
   * create failure message if any naming conflict
   * @param ctx_p
   * @param statuses
   * @param hasConflict
   * @param typesAndNames
   * @param elt
   * @param currentElementName
   * @param currentElementType
   * @return
   */
  private boolean checkTheNamingConflict(IValidationContext ctx_p, Collection<IStatus> statuses, boolean hasConflict_p,
      Map<String, List<String>> typesAndNames, CapellaElement elt, String currentElementName, String currentElementType) {
    boolean hasConflict = hasConflict_p;
    if (!typesAndNames.containsKey(currentElementType)) {
      // This type doesn't have a map entry
      // Creates the map entry for the type
      List<String> names = new ArrayList<String>();
      names.add(currentElementName);
      typesAndNames.put(currentElementType, names);
    } else {
      // this type already has a map entry
      List<String> existingNamesForTypes = typesAndNames.get(currentElementType);
      boolean alreadyExist = false;
      for (String existingName : existingNamesForTypes) {
        // Checks every existing name for this type, IGNORING THE CASE
        if (existingName.equalsIgnoreCase(currentElementName)) {
          // There is a conflict!!
          hasConflict = true;
          alreadyExist = true;
          // Creates a conflict status
          String currentConflict = TYPE_PREFIX + currentElementType + TYPE_SUFFIX + currentElementName;
          IStatus failureStatus = createFailureStatus(ctx_p, new Object[] { elt.eClass().getName(), elt.getLabel(), currentConflict });
          statuses.add(failureStatus);
          break;
        }
      }
      if (!alreadyExist) {
        // This name is not register for this type yet
        // so it is registered here
        existingNamesForTypes.add(currentElementName);
      }
    }
    return hasConflict;
  }
}
