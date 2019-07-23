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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.common.data.activity.ActivityPackage;

/**
 */
public class Rule_FunctionOutputPort extends Rule_CapellaElement {

  public Rule_FunctionOutputPort() {
    super(FaPackage.Literals.FUNCTION_OUTPUT_PORT, FaPackage.Literals.FUNCTION_OUTPUT_PORT, InformationPackage.Literals.PORT_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      if (!(TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(element_p.eContainer(), context_p).isOK())) {
        result = new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.ContainerNotTransitioned);
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
    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS,
        context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    FunctionOutputPort sourceElement = (FunctionOutputPort) source_p;
    result_p.addAll(sourceElement.getIncoming());
    result_p.addAll(sourceElement.getOutgoing());

    if (sourceElement.getOutgoingExchangeItems().size() > 0) {
      
      PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
      if (preferenceHelper.transitionExchangeItemWhileFunctionalTransition()) {
        result_p.addAll(sourceElement.getOutgoingExchangeItems());
      }
    }
  }

}
