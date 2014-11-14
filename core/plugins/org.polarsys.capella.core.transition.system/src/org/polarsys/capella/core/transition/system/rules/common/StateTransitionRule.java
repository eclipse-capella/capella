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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class StateTransitionRule extends AbstractCapellaElementRule {

  public StateTransitionRule() {
    super();
    registerUpdatedAttribute(CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER_DESCRIPTION);
    registerUpdatedAttribute(CapellacommonPackage.Literals.STATE_TRANSITION__GUARD);
    registerUpdatedAttribute(CapellacommonPackage.Literals.STATE_TRANSITION__KIND);
  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.STATE_TRANSITION;
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET));
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p)
        .attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__SOURCE, context_p);
    AttachmentHelper.getInstance(context_p)
        .attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__TARGET, context_p);
    AttachmentHelper.getInstance(context_p)
        .attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGER,
        context_p);
  }

}
