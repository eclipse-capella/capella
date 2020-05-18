/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 */
public abstract class AbstractProcessingRunnable<T> extends AbstractLongRunnable {

  /** current selection */
  protected Collection<T> _elements = null;

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public AbstractProcessingRunnable(Collection<T> elements_p) {
    _elements = elements_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  @Override
  public final void performCommand(IProgressMonitor monitor_p) {
    initialize(monitor_p);
    Collection<T> elements = retrieveElements(_elements);
    performCommand(elements, monitor_p);
  }

  /**
   * @param monitor_p
   */
  protected void initialize(IProgressMonitor monitor_p) {
    // Nothing yet
  }

  /**
   * @param element_p
   */
  protected void performCommand(Collection<T> elements_p, IProgressMonitor monitor_p) {
    // Nothing yet
  }

  /**
   * @param elements_p
   * @return
   */
  protected Collection<T> retrieveElements(Collection<T> elements_p) {
    Collection<T> elements = new ArrayList<T>();

    if ((elements_p != null)) {
      for (T object : elements_p) {
        elements.addAll(retrieveRelatedElements(object));
      }
    }
    return elements;
  }

  /**
   * @param rootElement_p
   * @return
   */
  protected Collection<T> retrieveRelatedElements(T rootElement_p) {
    return Collections.singleton(rootElement_p);
  }

}
