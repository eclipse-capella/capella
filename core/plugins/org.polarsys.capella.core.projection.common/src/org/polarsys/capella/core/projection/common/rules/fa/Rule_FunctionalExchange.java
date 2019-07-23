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
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;

/**
 */
public class Rule_FunctionalExchange extends Rule_CapellaElement {

  public Rule_FunctionalExchange() {
    super(FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE, FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      FunctionalExchange ce = (FunctionalExchange) element_p;

      if (ce.getSource() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.SourceNull);
      }
      if (ce.getTarget() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getSource(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.SourceBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getSource())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getTarget(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TargetBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getTarget())));
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    FunctionalExchange transfoSource = (FunctionalExchange) element_p;
    FunctionalExchange exchange = (FunctionalExchange) result_p;

    if (isFirstAttach(element_p, result_p, context_p)) {
      TigerRelationshipHelper.attachTransformedContainedElementsInBestContainer(element_p, context_p.getTransfo(),
          FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES, getTargetType(),
          FaPackage.Literals.ABSTRACT_FUNCTION);

      // FunctionInputPort / FunctionOutputPort
      ActivityNode inSrc = transfoSource.getTarget();
      ActivityNode inTgt = (ActivityNode) Query.retrieveFirstTransformedElement(inSrc, context_p.getTransfo());
      ActivityNode outSrc = transfoSource.getSource();
      ActivityNode outTgt = (ActivityNode) Query.retrieveFirstTransformedElement(outSrc, context_p.getTransfo());
      exchange.setSource(outTgt);
      exchange.setTarget(inTgt);
    }

    TigerRelationshipHelper.attachToBestElement(element_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS, context_p.getTransfo());
    TigerRelationshipHelper.invertedAttachTransformedRelatedElements(element_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES, context_p.getTransfo());

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    FunctionalExchange sourceElement = (FunctionalExchange) source_p;

    if (sourceElement.getExchangedItems().size() > 0) {
      
      PreferenceHelper preferencesHelper = PreferenceHelper.getInstance();
      if (preferencesHelper.transitionExchangeItemWhileFunctionalTransition()) {
        result_p.addAll(sourceElement.getExchangedItems());
      }
    }

    result_p.add(sourceElement.getSource());
    result_p.add(sourceElement.getTarget());
    result_p.addAll(sourceElement.getCategories());
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
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

      List<?> elements = TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(parent, context_p);
      if (elements.size() > 0) {
        EObject commonElement = (EObject) elements.get(0);
        return commonElement;
      }

      parent = parent.eContainer();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    //Nothing to do. We add the exchange in the best existing container
  }

}
