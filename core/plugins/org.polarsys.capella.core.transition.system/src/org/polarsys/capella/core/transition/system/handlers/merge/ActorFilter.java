/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ActorFilter extends EObjectCategoryFilter {

  public ActorFilter(IContext context) {
    super(context, CsPackage.Literals.COMPONENT);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof Component && (ComponentExt.isActor((Component)element));
  }

}
