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
package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 */
public class InvolvementRule extends AbstractCapellaElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected EClass getSourceType() {
    return CapellacorePackage.Literals.INVOLVEMENT;
  }

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);
    result.add(((Involvement) element).getInvolved());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void premicesRelated(EObject eObject1, ArrayList<IPremise> needed) {
    super.premicesRelated(eObject1, needed);
    Involvement element = (Involvement) eObject1;
    needed.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, context);
  }

  @Override
  public IStatus transformRequired(EObject element, IContext context) {
    IStatus result = super.transformRequired(element, context);
    if (result.isOK()) {
      Involvement ce = (Involvement) element;

      if (ce.getInvolved() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, org.polarsys.capella.core.transition.system.constants.Messages.SourceNull);
      }
      if (ce.getInvolver() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, org.polarsys.capella.core.transition.system.constants.Messages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(ce.getInvolved(), context).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(
            org.polarsys.capella.core.transition.system.constants.Messages.SourceBoundNotTransitioned, EObjectLabelProviderHelper.getText(ce.getInvolved())));
      }
      if (!TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(ce.getInvolver(), context).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(
            org.polarsys.capella.core.transition.system.constants.Messages.TargetBoundNotTransitioned, EObjectLabelProviderHelper.getText(ce.getInvolver())));
      }
    }
    return result;
  }
}
