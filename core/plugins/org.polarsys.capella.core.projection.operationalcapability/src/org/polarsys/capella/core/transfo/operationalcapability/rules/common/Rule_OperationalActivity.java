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
package org.polarsys.capella.core.transfo.operationalcapability.rules.common;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import static org.polarsys.capella.core.data.helpers.DataHelpers.FunctionExt;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.fa.Rule_AbstractFunction;

/**
 */
public class Rule_OperationalActivity extends Rule_AbstractFunction {

  public Rule_OperationalActivity(EClass target_p) {
    super(OaPackage.Literals.OPERATIONAL_ACTIVITY, target_p);
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    return super.transformRequired(element_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    AbstractFunction sourceElement = (AbstractFunction) source_p;

    super.retrieveGoDeep(source_p, result_p, context_p);

    if (isRelatedToSource(sourceElement, context_p)) {

      result_p.addAll(sourceElement.getOwnedFunctions());
      result_p.addAll(FunctionExt.getOwnedFunctionPkgs(sourceElement));

    }

  }
}
