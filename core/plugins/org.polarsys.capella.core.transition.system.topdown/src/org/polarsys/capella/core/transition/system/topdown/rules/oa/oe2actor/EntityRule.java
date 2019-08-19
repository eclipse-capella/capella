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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oe2actor;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class EntityRule extends org.polarsys.capella.core.transition.system.topdown.rules.oa.EntityRule {

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject res = super.transformDirectElement(element_p, context_p);
    if (res instanceof Component) {
      ((Component) res).setActor(true);
      ((Component) res).setHuman(true);
    }
    return res;
  }
}
