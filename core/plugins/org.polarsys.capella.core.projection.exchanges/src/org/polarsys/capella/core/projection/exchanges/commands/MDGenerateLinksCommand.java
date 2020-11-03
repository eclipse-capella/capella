/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.exchanges.commands;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.exchanges.LinkCreatorFactory;

/**
 */
public class MDGenerateLinksCommand extends AbstractReadWriteCommand {

  protected Collection<EObject> _rootElements = null;

  public MDGenerateLinksCommand(Collection<EObject> rootElements_p) {
    _rootElements = rootElements_p;
  }

  public void run() {
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      for (EObject rootElement : _rootElements) {
        Component component = null;
        Part part = null;

        if (rootElement instanceof Component) {
          component = (Component) rootElement;

        } else if (rootElement instanceof Part) {
          part = (Part) rootElement;
          if ((part.getAbstractType() != null) && (part.getAbstractType() instanceof Component)) {
            component = (Component) part.getAbstractType();
          }
        }

        if (component != null) {
          LinkCreatorFactory.createConnectionCreator(component, part).createExchanges();
        }
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    if ((_rootElements != null) && (_rootElements.size() > 0)) {
      EObject element = _rootElements.iterator().next();
      if (element != null) {
        if (TransitionHelper.getService().isCommunicationMeansGenerationAvailable(element)) {
          return Messages.generateCommunicationMeans_label;

        } else if (TransitionHelper.getService().isComponentExchangesGenerationAvailable(element)) {
          return Messages.generateComponentExchanges_label;

        } else if (TransitionHelper.getService().isPhysicalLinksGenerationAvailable(element)) {
          return Messages.generatePhysicalLinks_label;

        }
      }
    }
    return Messages.generatePhysicalLinksComponentExchanges_label;
  }
}
