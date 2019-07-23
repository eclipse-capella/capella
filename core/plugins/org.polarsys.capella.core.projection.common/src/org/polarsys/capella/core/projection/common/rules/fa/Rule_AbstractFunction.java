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
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public abstract class Rule_AbstractFunction extends Rule_CapellaElement {

  public Rule_AbstractFunction(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p, FaPackage.Literals.FUNCTION_REALIZATION);
    registerAttributeUpdate(FaPackage.Literals.ABSTRACT_FUNCTION__CONDITION);
    registerAttributeUpdate(FaPackage.Literals.ABSTRACT_FUNCTION__KIND);
  }

  @Override
  protected void updateElement(EObject element_p, EObject targetElement, IContext context_p) {
    AbstractFunction sourceElement = (AbstractFunction) element_p;

    super.updateElement(element_p, targetElement, context_p);

    if (targetElement != null) {
      for (State abstractStateMode : sourceElement.getAvailableInStates()) {
        for (AbstractTrace trace : abstractStateMode.getIncomingTraces()) {
          if (trace instanceof AbstractStateRealization) {
            AbstractState state = ((AbstractStateRealization) trace).getRealizingAbstractState();
            if (state instanceof State) {
              AttachmentHelper.getInstance(context_p).attachElementByRel(targetElement, state, FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
            }
          }
        }
      }
    }

  }

  protected boolean isRootFunction(EObject element_p) {
    return ((element_p.eContainer() instanceof FunctionPkg) && (element_p.eContainer().eContainer() instanceof BlockArchitecture));
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    if (isRootFunction(element_p)) {
      BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      return BlockArchitectureExt.getFunctionPkg(architecture);
    }

    AbstractFunction root = FunctionExt.getRootFunction((AbstractFunction) element_p);
    AbstractFunction newRoot = (AbstractFunction) Query.retrieveFirstTransformedElement(root, context_p.getTransfo(), FaPackage.Literals.ABSTRACT_FUNCTION);
    return newRoot;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {
      List<?> elements =
          TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(parent, context_p,
              TransformationHandlerHelper.getInstance(context_p).getTargetType(parent, context_p));
      if (elements.size() > 0) {
        EObject commonElement = retrieveCommonAncestor(elements);
        return commonElement;
      }

      parent = parent.eContainer();
    }
    return null;
  }

  /**
   * @param elements_p
   * @return
   */
  private EObject retrieveCommonAncestor(List<?> elements_p) {
    EObject ancestor = null;
    for (Object element : elements_p) {
      if (element instanceof EObject) {
        ancestor = getCommonAncestor((EObject) element, ancestor);
      }
    }
    return ancestor;
  }

  public EObject getCommonAncestor(EObject e1, EObject e2) {
    if (e2 == null) {
      return e1;
    } else if (e1 == null) {
      return e2;
    } else if (e1 == e2) {
      return e1;
    } else if (EcoreUtil.isAncestor(e1, e2)) {
      return e1;
    } else if (EcoreUtil.isAncestor(e2, e1)) {
      return e2;
    }

    EObject contextContainer = e1.eContainer();
    while (!EcoreUtil.isAncestor(contextContainer, e2) && (contextContainer != null)) {
      contextContainer = contextContainer.eContainer();
    }
    return contextContainer;
  }

}
