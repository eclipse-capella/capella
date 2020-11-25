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
package org.polarsys.capella.core.transition.system.topdown.rules.interaction;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AbstractCapabilityIncludeRule
    extends org.polarsys.capella.core.transition.system.rules.interaction.AbstractCapabilityIncludeRule {
  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    AbstractCapabilityInclude element = (AbstractCapabilityInclude) source;
    EObject targetElement = element.getIncluded();
    result.add(targetElement);
  }
}
