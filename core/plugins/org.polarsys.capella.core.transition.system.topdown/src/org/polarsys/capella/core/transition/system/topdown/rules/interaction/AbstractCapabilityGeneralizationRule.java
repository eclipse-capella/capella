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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class AbstractCapabilityGeneralizationRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION;
  }
  
  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    IStatus result = super.transformRequired(element, context);
    if (result.isOK()) {
      AbstractCapabilityGeneralization ce = (AbstractCapabilityGeneralization) element;

      if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
        return result;
      }

      if (ce.getSuper() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, "SourceNull");
      }
      if (ce.getSub() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, ".TargetNull");
      }

      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(ce.getSuper(), context).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            NLS.bind(".SourceBoundNotTransitioned", EObjectLabelProviderHelper.getText(ce.getSuper())));
      }
      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(ce.getSub(), context).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(".Target bound not transitioned", EObjectLabelProviderHelper.getText(ce.getSub())));
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    AbstractCapabilityGeneralization sourceElement = (AbstractCapabilityGeneralization) source;
    if (sourceElement.getSuper() != null) {
      result.add(sourceElement.getSuper());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);

    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER,
        context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB,
        context);

  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER));
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB));
  }

}
