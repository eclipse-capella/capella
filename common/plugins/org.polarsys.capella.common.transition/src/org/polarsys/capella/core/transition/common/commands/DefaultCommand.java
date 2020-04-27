/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;

public abstract class DefaultCommand extends AbstractReadWriteCommand {

  /** current selection */
  protected Collection<?> selection = null;

  /** a progress monitor */
  private IProgressMonitor progressMonitor = null;

  private SharedWorkflowActivityParameter parameters = new SharedWorkflowActivityParameter();
    
  public SharedWorkflowActivityParameter getParameters() {
    return parameters;
  }

  public void addParameters(SharedWorkflowActivityParameter parameters) {
    this.parameters.merge(parameters);
  }
  
  public void addParameter(String idActivity, GenericParameter<?> parameter) {
    this.parameters.addParameter(idActivity, parameter);
  }
  
  public void addSharedParameter(GenericParameter<?> parameter) {
    this.parameters.addSharedParameter(parameter);
  }
  
  /** the name of the command */
  protected String name;

  /**
   * @param modelElement
   */
  public DefaultCommand(Collection<?> selection) {
    this(selection, new NullProgressMonitor());
  }

  /**
   * @param modelElement
   * @param progressMonitor
   */
  public DefaultCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
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
  protected Collection<Object> retrieveElements(Collection<?> selection) {
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
   * @param elements
   */
  protected abstract void performTransformation(Collection<?> elements);

  /**
   * @param rootElement
   * @return
   */
  protected Collection<Object> retrieveRelatedElements(Object rootElement) {
    return Collections.singleton(rootElement);
  }

}
