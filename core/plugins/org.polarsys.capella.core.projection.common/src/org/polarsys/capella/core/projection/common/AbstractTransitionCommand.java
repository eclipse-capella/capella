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
package org.polarsys.capella.core.projection.common;

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

/**
 */
public abstract class AbstractTransitionCommand extends AbstractReadWriteCommand {

  /** rootElements */
  protected Collection<EObject> rootElements = null;

  /** a progress monitor */
  private IProgressMonitor progressMonitor = null;
  
  /* elements that are transitioned */
  protected Collection<EObject> elements = null;

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return ProjectionMessages.transition_title;
  }

  /**
   * @param rootElements
   */
  public AbstractTransitionCommand(Collection<EObject> rootElements) {
    this(rootElements, new NullProgressMonitor());
  }

  /**
   * @param rootElements
   * @param progressMonitor
   */
  public AbstractTransitionCommand(Collection<EObject> rootElements, IProgressMonitor progressMonitor) {
    this.rootElements = rootElements;
    this.progressMonitor = progressMonitor;
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement
   * @return
   */
  protected Collection<EObject> retrieveModelElements(EObject modelElement) {
    return Collections.singleton(modelElement);
  }

  /**
   * Returns a transformation for the given model element
   * @param element
   * @return
   */
  protected abstract AbstractTransform getTransformation(EObject element);

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {

    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {

      if ((rootElements != null) && (rootElements.size() > 0)) {

        elements = new ArrayList<EObject>();

        for (EObject rootElement : rootElements) {
          elements.addAll(retrieveModelElements(rootElement));
        }

        progressMonitor.beginTask(getName() + ProjectionMessages.transition_processing, elements.size());

        // Perform a transition for all retrieved elements
        for (EObject relatedElement : elements) {
          if (relatedElement != null) {
            AbstractTransform transform = getTransformation(relatedElement);
            if (transform != null) {
              transform.setContext(relatedElement);
              transform.execute();
            }
          }
          progressMonitor.worked(1);
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
