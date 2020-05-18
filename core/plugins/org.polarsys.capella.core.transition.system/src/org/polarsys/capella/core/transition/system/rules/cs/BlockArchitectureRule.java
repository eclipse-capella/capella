/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class BlockArchitectureRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.BLOCK_ARCHITECTURE;
  }

  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);

    //We don't set the name as the source element, we use the metaclass name
    if (result instanceof AbstractNamedElement) {
      AbstractNamedElement r = (AbstractNamedElement) result;
      r.setName(EObjectLabelProviderHelper.getMetaclassLabel(result, false) + Messages.TransitionedElement_Suffix);
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    //Retrieve the existing architecture if any
    EObject result = null;

    EClass targetType = getTargetType(element, context);

    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    SystemEngineering target =
        (SystemEngineering) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context,
            CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    if (target != null) {
      for (ModellingArchitecture archi : target.getOwnedArchitectures()) {
        if ((targetType != null) && targetType.isInstance(archi)) {
          result = archi;
          break;
        }
      }
    }

    if (result == null) {
      result = super.transformDirectElement(element, context);
    }

    //Theoretically, this should not be performed here, but log message requires a valid name
    if (result instanceof AbstractNamedElement) {
      //We don't set the name as the source element, we use the metaclass name
      ((AbstractNamedElement) result).setName(EObjectLabelProviderHelper.getMetaclassLabel(result, false) + Messages.TransitionedElement_Suffix);
    }

    return result;
  }

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    //Nothing here, we are already in the root
  }

  @Override
  protected void premicesContainement(EObject element, ArrayList<IPremise> needed) {
    //Nothing here, we are already in the root
  }

}
