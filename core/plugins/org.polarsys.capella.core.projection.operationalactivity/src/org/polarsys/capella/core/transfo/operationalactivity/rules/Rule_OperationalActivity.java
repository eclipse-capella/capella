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
package org.polarsys.capella.core.transfo.operationalactivity.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_Function;

/**
 */
public class Rule_OperationalActivity extends Rule_Function {

  public Rule_OperationalActivity() {
    super(OaPackage.Literals.OPERATIONAL_ACTIVITY, CtxPackage.Literals.SYSTEM_FUNCTION);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    OperationalActivity sourceElement = (OperationalActivity) source_p;
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (isRelatedToSource(sourceElement, context_p)) {

      result_p.addAll(sourceElement.getIncoming());
      result_p.addAll(sourceElement.getOutgoing());
      result_p.addAll(sourceElement.getOwnedProcess());
    }
  }

}
