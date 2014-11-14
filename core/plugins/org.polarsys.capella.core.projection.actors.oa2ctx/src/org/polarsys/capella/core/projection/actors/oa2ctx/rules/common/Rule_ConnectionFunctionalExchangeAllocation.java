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
package org.polarsys.capella.core.projection.actors.oa2ctx.rules.common;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.transfo.operationalactivity.TransformOperationalActivity;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class Rule_ConnectionFunctionalExchangeAllocation extends
    org.polarsys.capella.core.projection.common.rules.fa.Rule_ConnectionFunctionalExchangeAllocation {

  /**
   * @see org.polarsys.capella.core.projection.common.CommonRule#runSubTransition(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
    ComponentExchangeFunctionalExchangeAllocation componentFuncAlloc = (ComponentExchangeFunctionalExchangeAllocation) element_p;
    TraceableElement target = componentFuncAlloc.getTargetElement();

    if (target instanceof FunctionalExchange) {

      if (ProjectionPreferencesPlugin.getDefault().transitionFunctionalElementWhileComponentTransition()) {
        // OA to SystemFunction transformation
        FunctionalExchange oa = (FunctionalExchange) componentFuncAlloc.getTargetElement();
        TransformOperationalActivity transfOa = new TransformOperationalActivity();
        transfOa.setContext(oa);
        transfOa.execute();
      }

    }
  }

}
