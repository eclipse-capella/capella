/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public abstract class AbstractFixCommand extends AbstractReadWriteCommand {

  /** the modelElement */
  protected Collection<ModelElement> selection = null;

  /** a progress monitor */
  private IProgressMonitor progressMonitor = null;

  /**
   * @param modelElement
   */
  public AbstractFixCommand(Collection<ModelElement> selection) {
    this(selection, new NullProgressMonitor());

  }

  /**
   * @param modelElement
   * @param progressMonitor
   */
  public AbstractFixCommand(Collection<ModelElement> selection, IProgressMonitor progressMonitor) {
    this.selection = selection;
    this.progressMonitor = progressMonitor;
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement
   * @return
   */
  protected Collection<ModelElement> retrieveModelElements(ModelElement modelElement) {
    return Collections.singleton(modelElement);
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {

    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {

      for (ModelElement selectedElement : selection) {
        Collection<ModelElement> elements = retrieveModelElements(selectedElement);
        progressMonitor.beginTask(getName(), elements.size());

        // Perform a transition for all retrieved elements
        for (ModelElement element : elements) {
          process(element);
          progressMonitor.worked(1);
        }
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  abstract protected void process(ModelElement element);

}
