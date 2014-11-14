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
package org.polarsys.capella.core.transition.system.rules.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
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
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);
    result_p.add(((Involvement) element_p).getInvolved());
    result_p.add(((Involvement) element_p).getInvolver());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    Involvement element = (Involvement) element_p;
    needed_p.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED));
    needed_p.addAll(createDefaultPrecedencePremices(element, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, context_p);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      Involvement ce = (Involvement) element_p;

      if (ce.getInvolved() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, org.polarsys.capella.core.transition.system.constants.Messages.SourceNull);
      }
      if (ce.getInvolver() == null) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, org.polarsys.capella.core.transition.system.constants.Messages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getInvolved(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(
            org.polarsys.capella.core.transition.system.constants.Messages.SourceBoundNotTransitioned, EObjectLabelProviderHelper.getText(ce.getInvolved())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getInvolver(), context_p).isOK()) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind(
            org.polarsys.capella.core.transition.system.constants.Messages.TargetBoundNotTransitioned, EObjectLabelProviderHelper.getText(ce.getInvolver())));
      }
    }
    return result;
  }
}
