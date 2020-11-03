/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers.transformation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.ISelectionContext;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.IRuleTransformation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRule;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 */
public class TigerTransformationHandler implements ITransformationHandler {

  public IStatus isOrWillBeTransformed(EObject element_p, IContext context_p) {
    ITransfo transfo_p = context_p.getTransfo();
    boolean result = false;

    if (element_p != null) {

      result = Query.isElementTransformed(element_p, transfo_p);

      try {
        ITransfoRule ruleSource = transfo_p.findCachedMatchingRule(element_p);
        if (ruleSource != null && ruleSource instanceof IRuleTransformation) {
          EClass clazz = getTargetType(element_p, context_p);
          if (clazz != null) {
            result |= ((IRuleTransformation) ruleSource).transformRequired(element_p, context_p).isOK();
          }
        }
      } catch (TransfoException exception_p) {
        //Something happened, the result depends now of the previously state of the transitioned element
      }
    }

    if (result) {
      return Status.OK_STATUS;
    }
    return Status.CANCEL_STATUS;

  }

  public IStatus isOrWillBeTransformedTo(EObject element_p, IContext context_p, EClass target) {
    ITransfo transfo_p = context_p.getTransfo();

    if (element_p != null) {

      if (Query.retrieveTransformedElement(element_p, transfo_p, target) != null) {
        return Status.OK_STATUS;
      }

      try {
        ITransfoRule ruleSource = transfo_p.findCachedMatchingRule(element_p);
        if (ruleSource != null && ruleSource instanceof IRuleTransformation) {
          if (EcoreUtil2.isEqualOrSuperClass(target, ruleSource.getTargetType())) {
            return ((IRuleTransformation) ruleSource).transformRequired(element_p, context_p);
          }
        }
      } catch (TransfoException exception_p) {
        //Something happened, the result depends now of the previously state of the transitioned element
      }
    }

    return Status.CANCEL_STATUS;

  }

  /**
   * {@inheritDoc}
   */
  public void init(IContext context_p) {
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  public void dispose(IContext context_p) {
    //Nothing here
  }

  /**
   * Retrieve the best traced element which feature_p is applicable
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p) {
    final EClass targetType = getTargetType(source_p, context_p);

    ISelectionContext context = new ISelectionContext() {
      public boolean match(EObject matchSource_p, EObject matchTarget_p, IContext matchContext_p) {
        return targetType == null || targetType.isInstance(matchTarget_p);
      }
    };
    return getBestTracedElement(source_p, context_p, context);
  }

  /**
   * Retrieve the best traced element which feature_p is applicable
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p, final EClass class_p) {
    ISelectionContext context = new ISelectionContext() {
      public boolean match(EObject matchSource_p, EObject matchTarget_p, IContext matchContext_p) {
        return class_p == null || class_p.isInstance(matchTarget_p);
      }
    };
    return getBestTracedElement(source_p, context_p, context);
  }

  /**
   * Experimental {@inheritDoc}
   */
  public EObject getBestTracedElement(EObject source_p, IContext context_p, ISelectionContext sContext_p) {
    //If some choices, we should ask to the user to make a choice

    //Retrieve the first matching element
    for (EObject target : TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(source_p, context_p)) {
      if (sContext_p == null || sContext_p.match(source_p, target, context_p)) {
        return target;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public EClass getTargetType(EObject element_p, IContext context_p) {
    ITransfo transfo_p = context_p.getTransfo();

    if (element_p != null) {

      try {
        ITransfoRule ruleSource = transfo_p.findCachedMatchingRule(element_p);
        if (ruleSource != null) {
          return ruleSource.getTargetType();
        }
      } catch (TransfoException exception_p) {
        //Something happened, the result depends now of the previously state of the transitioned element
      }
    }

    return null;
  }

}
