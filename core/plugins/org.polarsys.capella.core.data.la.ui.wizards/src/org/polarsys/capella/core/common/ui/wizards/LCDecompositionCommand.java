/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.common.ui.wizards;

import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class LCDecompositionCommand extends AbstractReadWriteCommand {

  private ModelElement modelElement = null;

  /**
   * Constructor
   */
  public LCDecompositionCommand(ModelElement modelElement) {
    this.modelElement = modelElement;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {
    if (modelElement instanceof LogicalComponent) {
        LCDecompositionController controller = new LCDecompositionController();
        controller.createAndShowDecompositionWizard((LogicalComponent) modelElement);
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return "LCDecomposition"; //$NON-NLS-1$
  }
}
