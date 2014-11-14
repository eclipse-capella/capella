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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
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
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
  }

  @Override
  protected void updateElement(EObject element_p, EObject result_p, IContext context_p) {
    super.updateElement(element_p, result_p, context_p);

    //We don't set the name as the source element, we use the metaclass name
    if (result_p instanceof AbstractNamedElement) {
      AbstractNamedElement r = (AbstractNamedElement) result_p;
      r.setName(EObjectLabelProviderHelper.getMetaclassLabel(result_p, false) + Messages.TransitionedElement_Suffix);
    }
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    //Retrieve the existing architecture if any
    EObject result = null;

    EClass targetType = getTargetType(element_p, context_p);

    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    SystemEngineering target =
        (SystemEngineering) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p,
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
      result = super.transformDirectElement(element_p, context_p);
    }

    //Theoretically, this should not be performed here, but log message requires a valid name
    if (result instanceof AbstractNamedElement) {
      //We don't set the name as the source element, we use the metaclass name
      ((AbstractNamedElement) result).setName(EObjectLabelProviderHelper.getMetaclassLabel(result, false) + Messages.TransitionedElement_Suffix);
    }

    return result;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    //Nothing here, we are already in the root
  }

  @Override
  protected void premicesContainement(EObject element_p, ArrayList<IPremise> needed_p) {
    //Nothing here, we are already in the root
  }

}
