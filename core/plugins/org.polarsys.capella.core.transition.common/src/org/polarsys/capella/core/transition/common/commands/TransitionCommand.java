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
package org.polarsys.capella.core.transition.common.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public abstract class TransitionCommand extends AbstractReadWriteCommand {

  /** current selection */
  protected Collection<Object> _selection = null;

  /** a progress monitor */
  private IProgressMonitor _progressMonitor = null;

  /**
   * @param modelElement_p
   */
  public TransitionCommand(Collection<Object> selection_p) {
    this(selection_p, new NullProgressMonitor());
  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public TransitionCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    _selection = selection_p;
    _progressMonitor = progressMonitor_p;
  }

  /**
   * @return the progressMonitor
   */
  public IProgressMonitor getProgressMonitor() {
    return _progressMonitor;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {

    Collection<Object> elements = retrieveElements(_selection);

    // Perform a transition for all retrieved elements
    IProgressMonitor monitor = getProgressMonitor();
    monitor.beginTask(getName(), 1);
    performTransformation(elements);
    monitor.worked(1);

  }

  /**
   * @param selection_p
   * @return
   */
  protected Collection<Object> retrieveElements(Collection<Object> selection_p) {
    Collection<Object> elements = new ArrayList<Object>();

    if ((selection_p != null) && (selection_p instanceof Collection)) {

      Iterator<?> iterator = selection_p.iterator();
      while (iterator.hasNext()) {
        Object selectedElement = iterator.next();
        elements.addAll(retrieveRelatedElements(selectedElement));
      }
    }
    return elements;
  }

  /**
   * @param element_p
   */
  protected void performTransformation(Collection<Object> elements_p) {
    // Nothing yet
  }

  /**
   * @param rootElement_p
   * @return
   */
  protected Collection<Object> retrieveRelatedElements(Object rootElement_p) {
    return Collections.singleton(rootElement_p);
  }

}
