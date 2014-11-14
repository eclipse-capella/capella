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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class ComponentExchangeThroughDelegationsCommand extends AbstractFixCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return Messages.ComponentExchangeThroughDelegations;
  }

  /**
   * @param modelElement_p
   */
  public ComponentExchangeThroughDelegationsCommand(Collection<ModelElement> selection_p) {
    this(selection_p, new NullProgressMonitor());
  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public ComponentExchangeThroughDelegationsCommand(Collection<ModelElement> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement_p
   * @return
   */
  @Override
  protected Collection<ModelElement> retrieveModelElements(ModelElement modelElement_p) {
    return Collections.singleton(modelElement_p);
  }

  @Override
  protected void process(ModelElement element_p) {

    if (element_p instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) element_p;
      Part sourcePart = ComponentExchangeExt.getSourcePart(exchange);
      Port sourcePort = ComponentExchangeExt.getSourcePort(exchange);
      Part targetPart = ComponentExchangeExt.getTargetPart(exchange);
      Port targetPort = ComponentExchangeExt.getTargetPort(exchange);

      if (sourcePart == null) {
        Component sourceComponent = ComponentExchangeExt.getSourceComponent(exchange);
        sourcePart = (Part) sourceComponent.getRepresentingPartitions().get(0);
      }
      if (targetPart == null) {
        Component targetComponent = ComponentExchangeExt.getTargetComponent(exchange);
        targetPart = (Part) targetComponent.getRepresentingPartitions().get(0);
      }

      if ((sourcePort == null) || (sourcePort instanceof ComponentPort)) {
        if ((targetPort == null) || (targetPort instanceof ComponentPort)) {
          if ((sourcePart != null) && (targetPart != null) && !ComponentExt.isBrothers(sourcePart, targetPart)) {
            if (!ComponentExt.isComponentExchangeThroughDelegationsExists(sourcePart, targetPart, sourcePort, targetPort)) {
              ComponentExt.createComponentExchangeThroughDelegations(sourcePart, (ComponentPort) sourcePort, targetPart, (ComponentPort) targetPort);
            }
          }
        }
      }
    }

  }

}
