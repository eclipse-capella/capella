/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.cs;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public abstract class Rule_ActorPkg extends Rule_CapellaElement {

  public Rule_ActorPkg(EClass source, EClass target) {
    super(source, target);
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    //Return the root pkg
    if ((element_p.eContainer() instanceof BlockArchitecture)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      if (architecture != null) {
        Structure result = BlockArchitectureExt.getActorPkg(architecture);
        if (result != null) {
          LogHelper.getInstance().info(
              NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                  EObjectLabelProviderHelper.getText(result)), new Object[] { element_p, result }, ProjectionMessages.Activity_Transformation);
          return result;
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return (EObject) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
  }

}
