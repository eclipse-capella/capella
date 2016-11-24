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
package org.polarsys.capella.core.refinement.commands;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.command.UpdateLinks;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class SynchronizeLinksCommand extends AbstractReadWriteCommand {

  /**
   * Currently selected element
   */
  private ModelElement modelElement = null;

  /**
   * Progress monitor
   */
  private IProgressMonitor progressMonitor = null;

  /**
   * Constructor
   */
  public SynchronizeLinksCommand(ModelElement modelElement, IProgressMonitor progressMonitor) {
    this.modelElement = modelElement;
    this.progressMonitor = progressMonitor;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {
    if (modelElement != null) {
      UpdateLinks processor = new UpdateLinks();
      processor.setTarget((NamedElement) modelElement);
      processor.execute(progressMonitor);
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return "SynchronizeLinksProcessor"; //$NON-NLS-1$
  }
}
