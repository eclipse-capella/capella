/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.helpers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ContextHelper {

  public static Project getSourceProject(IContext context_p) {
    return (Project) context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
  }

  public static SystemEngineering getSourceEngineering(IContext context) {
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (!selection.isEmpty()) {
      EObject sourceSelection = selection.iterator().next();
      return (SystemEngineering) EcoreUtil2.getFirstContainer(sourceSelection, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    }
    return null;
  }

  public static Project getTransformedProject(IContext context) {
    return (Project) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
  }

  public static EObject getTransformedEngineering(IContext context) {
    return SystemEngineeringExt.getSystemEngineering(getTransformedProject(context));
  }

  public static Project getTargetProject(IContext context) {
    return (Project) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
  }

}
