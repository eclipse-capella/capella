/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oe2actor;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EntityRule extends org.polarsys.capella.core.transition.system.topdown.rules.oa.EntityRule {

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    EObject res = super.transformDirectElement(element, context);
    if (res instanceof Component) {
      ((Component) res).setActor(true);
    }
    return res;
  }
}
