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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class AbstractStateRule extends AbstractCapellaElementRule {

  public AbstractStateRule() {
    registerUpdatedReference(ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    registerUpdatedReference(CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS);
    registerUpdatedReference(CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
  }

  @Override
  protected EClass getSourceType() {
    return CapellacommonPackage.Literals.ABSTRACT_STATE;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    if (source_p instanceof State) {
      result_p.addAll(((State) source_p).getOwnedRegions());
    }
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS));

    if (element_p instanceof State) {
      needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacommonPackage.Literals.STATE__DO_ACTIVITY));
    }
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS, context_p);

    if (element_p instanceof State) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__DO_ACTIVITY, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__ENTRY, context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacommonPackage.Literals.STATE__EXIT, context_p);
    }
  }
}
