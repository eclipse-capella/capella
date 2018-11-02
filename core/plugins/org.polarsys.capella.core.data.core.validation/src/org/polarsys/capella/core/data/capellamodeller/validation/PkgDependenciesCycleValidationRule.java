/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.capellamodeller.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;

/**
 */
public class PkgDependenciesCycleValidationRule extends AbstractPkgDependenciesCycleValidationRule {

  protected boolean shouldValidate(IValidationContext ctx) {
    EMFEventType eType = ctx.getEventType();
    EObject target = getTarget(ctx);
    return eType == EMFEventType.NULL && (target instanceof SystemEngineering || target instanceof BlockArchitecture
        || target instanceof AbstractDependenciesPkg);
  }
}
