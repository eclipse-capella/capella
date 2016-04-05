/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
 *
 */
public abstract class TransitionCommand extends AbstractReadWriteCommand {

  /** current selection */
  protected Collection<Object> selection = null;

  /** a progress monitor */
  private IProgressMonitor progressMonitor = null;

  /** the name of the command */
  protected String name;

  /**
   * @param modelElement
   */
  public TransitionCommand(Collection<Object> selection) {
    this(selection, new NullProgressMonitor());
  }

  /**
   * @param modelElement
   * @param progressMonitor
   */
  public TransitionCommand(Collection<Object> selection, IProgressMonitor progressMonitor) {
    this.selection = selection;
    this.progressMonitor = progressMonitor;
    setName(getClass().getSimpleName());
  }

  /**
   * Sets the name of the command
   * @param the command's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the name of the command
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @return the progressMonitor
   */
  public IProgressMonitor getProgressMonitor() {
    return progressMonitor;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {

    Collection<Object> elements = retrieveElements(selection);

    // Perform a transition for all retrieved elements
    IProgressMonitor monitor = getProgressMonitor();
    monitor.beginTask(getName(), 1);
    performTransformation(elements);
    monitor.worked(1);

  }

  /**
   * @param selection
   * @return
   */
  protected Collection<Object> retrieveElements(Collection<Object> selection) {
    Collection<Object> elements = new ArrayList<Object>();

    if (selection != null) {
      Iterator<?> iterator = selection.iterator();
      while (iterator.hasNext()) {
        Object selectedElement = iterator.next();
        elements.addAll(retrieveRelatedElements(selectedElement));
      }
    }
    return elements;
  }

  /**
   * @param element
   */
  protected void performTransformation(Collection<Object> elements) {
    // Nothing yet
  }

  /**
   * @param rootElement
   * @return
   */
  protected Collection<Object> retrieveRelatedElements(Object rootElement) {
    return Collections.singleton(rootElement);
  }

}
