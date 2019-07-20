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
package org.polarsys.capella.core.re.handlers.scope;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CapellaRelatedElementsRetriever implements IScopeRetriever {

  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  @Override
  public Collection<? extends EObject> retrieveSharedElements(IContext context) {
    return null;
  }

  @Override
  public Collection<? extends EObject> retrieveRelatedElements(EObject element, IContext context) {
    if (element instanceof Component) {
      Collection<EObject> childs = new HashSet<EObject>();
      for (ComponentPort port : ComponentExt.getOwnedComponentPort((Component) element)) {
        childs.addAll(port.getInformationFlows());
      }

      for (PhysicalPort port : ComponentExt.getOwnedPhysicalPort((Component) element)) {
        childs.addAll(port.getInvolvedLinks());
      }
      return childs;
    }
    return null;
  }
}
