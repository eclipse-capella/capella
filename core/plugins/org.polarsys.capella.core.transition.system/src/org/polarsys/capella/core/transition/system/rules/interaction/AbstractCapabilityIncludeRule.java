/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.rules.interaction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class AbstractCapabilityIncludeRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE;
  }

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
    result.add(((AbstractCapabilityInclude) element).getIncluded());
  }

  @Override
  protected void premicesRelated(EObject eObject, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject, needed);
    AbstractCapabilityInclude element = (AbstractCapabilityInclude) eObject;
    needed.addAll(createDefaultPrecedencePremices(element, InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED, context);
  }

  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    IStatus result = super.transformRequired(element, context);
    if (result.isOK()) {
      AbstractCapabilityInclude extend = (AbstractCapabilityInclude) element;

      if (extend.getInclusion() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            org.polarsys.capella.core.transition.system.constants.Messages.SourceNull);
      }
      if (extend.getIncluded() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            org.polarsys.capella.core.transition.system.constants.Messages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(extend.getInclusion(), context)
          .isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            NLS.bind(org.polarsys.capella.core.transition.system.constants.Messages.SourceBoundNotTransitioned,
                EObjectLabelProviderHelper.getText(extend.getInclusion())));
      }
      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(extend.getIncluded(), context)
          .isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation,
            NLS.bind(org.polarsys.capella.core.transition.system.constants.Messages.TargetBoundNotTransitioned,
                EObjectLabelProviderHelper.getText(extend.getIncluded())));
      }
    }
    return result;
  }
}
