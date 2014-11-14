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
  public IStatus validate(IValidationContext ctx_p) {
    Map<String, CapellaElement> map = (Map<String, CapellaElement>) ctx_p.getCurrentConstraintData();
    if (map == null) {
      map = new HashMap<String, CapellaElement>(1024);
      ctx_p.putCurrentConstraintData(map);
    }
    CapellaElement target = (CapellaElement) ctx_p.getTarget();
    String id = target.getId();
    CapellaElement previous = map.put(id, target);
    if ((previous != null) && (previous != target) && (previous.eResource() != target.eResource())) {
      // conflicts for same resource are already handled by the basic EMF validation rules.
      ctx_p.addResult(previous);
      return ctx_p.createFailureStatus(id, target.getLabel(), previous.getLabel());
    }
    return ctx_p.createSuccessStatus();
  }
}
