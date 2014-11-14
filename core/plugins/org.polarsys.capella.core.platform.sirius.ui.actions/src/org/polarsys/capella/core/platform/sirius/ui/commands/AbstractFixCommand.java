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

import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

public abstract class AbstractFixCommand extends AbstractReadWriteCommand {

  /** the modelElement */
  protected Collection<ModelElement> _selection = null;

  /** a progress monitor */
  private IProgressMonitor _progressMonitor = null;

  /**
   * @param modelElement_p
   */
  public AbstractFixCommand(Collection<ModelElement> selection_p) {
    this(selection_p, new NullProgressMonitor());

  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public AbstractFixCommand(Collection<ModelElement> selection_p, IProgressMonitor progressMonitor_p) {
    _selection = selection_p;
    _progressMonitor = progressMonitor_p;
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement_p
   * @return
   */
  protected Collection<ModelElement> retrieveModelElements(ModelElement modelElement_p) {
    return Collections.singleton(modelElement_p);
  }

  /**
   * @see org.polarsys.capella.common.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {

    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {

      for (ModelElement selectedElement : _selection) {
        Collection<ModelElement> elements = retrieveModelElements(selectedElement);
        _progressMonitor.beginTask(getName(), elements.size());

        // Perform a transition for all retrieved elements
        for (ModelElement element : elements) {
          process(element);
          _progressMonitor.worked(1);
        }
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  abstract protected void process(ModelElement element_p);

}
