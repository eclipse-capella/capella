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
package org.polarsys.capella.core.projection.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerView;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.projection.common.AbstractTransform;

/**
 */
public abstract class AbstractTransitionCommand extends AbstractReadWriteCommand {

  /** rootElements */
  protected Collection<EObject> _rootElements = null;

  /** a progress monitor */
  private IProgressMonitor _progressMonitor = null;

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.transition_title;
  }

  /**
   * @param modelElement_p
   */
  public AbstractTransitionCommand(Collection<EObject> rootElements_p) {
    this(rootElements_p, new NullProgressMonitor());
  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public AbstractTransitionCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    _rootElements = rootElements_p;
    _progressMonitor = progressMonitor_p;
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement_p
   * @return
   */
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {
    return Collections.singleton(modelElement_p);
  }

  /**
   * Returns a transformation for the given model element
   * @param element_p
   * @return
   */
  protected abstract AbstractTransform getTransformation(EObject element_p);

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {

    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {

      if ((_rootElements != null) && (_rootElements.size() > 0)) {

        Collection<EObject> elements = new ArrayList<EObject>();

        for (EObject rootElement : _rootElements) {
          elements.addAll(retrieveModelElements(rootElement));
        }

        _progressMonitor.beginTask(getName() + Messages.transition_processing, elements.size());

        // Perform a transition for all retrieved elements
        for (EObject relatedElement : elements) {
          if (relatedElement != null) {
            AbstractTransform transform = getTransformation(relatedElement);
            if (transform != null) {
              transform.setContext(relatedElement);
              transform.execute();
            }
          }
          _progressMonitor.worked(1);
        }
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());

      // Display information view to provide user feedback
      // TODO check if we want it to be done in the LongRunningListenersRegistry that is used to refresh the Capella Explorer.
      try {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MarkerView.VIEW_ID);
      } catch (PartInitException e) {
        CapellaActionsActivator.getDefault().log(IStatus.ERROR, e.getMessage(), e);
      }
    }
  }
}
