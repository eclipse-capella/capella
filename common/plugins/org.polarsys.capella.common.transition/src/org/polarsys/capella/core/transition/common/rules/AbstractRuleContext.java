/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * In progress, not yet used
 */
public abstract class AbstractRuleContext extends AbstractRule implements IRuleContext {

  private class IDefinitionContext {
  }

  private IDefinitionContext DEFAULT_CONTEXT = new IDefinitionContext();

  protected List<IDefinitionContext> getDefinitionContexts(EObject element) {
    return Collections.singletonList(DEFAULT_CONTEXT);
  }

  @Override
  protected Collection<EObject> transformElement(EObject eObject1, IContext iContext1) {
    Collection<EObject> results = new ArrayList<EObject>();

    for (IDefinitionContext context : getDefinitionContexts(eObject1)) {
      EObject transitioned = transformDirectElement(eObject1, iContext1);
      results.add(transitioned);
    }

    return results;
  }

  public void registerContext(EObject object, String keyContext, EObject contextElement) {
  }

  public IDefinitionContext retrieveContext(EObject object) {
    return DEFAULT_CONTEXT;
  }

}
