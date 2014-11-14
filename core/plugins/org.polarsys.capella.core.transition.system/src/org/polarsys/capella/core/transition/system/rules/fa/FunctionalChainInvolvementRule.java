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
package org.polarsys.capella.core.transition.system.rules.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class FunctionalChainInvolvementRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT;
  }

  @Override
  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveRequired(element_p, result_p, context_p);
    result_p.add(((Involvement) element_p).getInvolved());
    result_p.add(((Involvement) element_p).getInvolver());
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS, context_p);

    if (element_p instanceof FunctionalChainReference) {
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
          FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN, context_p);
    }
  }

  @Override
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    super.premicesRelated(element_p, needed_p);
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS));

    if (element_p instanceof FunctionalChainReference) {
      needed_p.addAll(createDefaultPrecedencePremices(element_p, FaPackage.Literals.FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN));
    }
  }
}
