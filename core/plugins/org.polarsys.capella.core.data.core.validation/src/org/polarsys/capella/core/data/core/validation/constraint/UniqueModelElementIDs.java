/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * Tests for ModelElement.id collisions.
 */
public class UniqueModelElementIDs extends AbstractModelConstraint {

  @Override
  public IStatus validate(IValidationContext ctx) {
    Map<String, CapellaElement> map = (Map<String, CapellaElement>) ctx.getCurrentConstraintData();
    if (map == null) {
      map = new HashMap<String, CapellaElement>(1024);
      ctx.putCurrentConstraintData(map);
    }
    CapellaElement target = (CapellaElement) ctx.getTarget();
    String id = target.getId();
    CapellaElement previous = map.put(id, target);
    if ((previous != null) && (previous != target) && (previous.eResource() != target.eResource())) {
      // conflicts for same resource are already handled by the basic EMF validation rules.
      ctx.addResult(previous);
      return ctx.createFailureStatus(id, target.getLabel(), previous.getLabel());
    }
    return ctx.createSuccessStatus();
  }
}
