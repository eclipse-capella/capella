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
package org.polarsys.capella.common.command.recorder.core.variables.internal;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.variables.IValueVariable;
import org.eclipse.core.variables.IValueVariableInitializer;

/**
 * e.g. workspace/.metadata/.recorder
 */
public class RecorderPathVariableInitializer implements IValueVariableInitializer {
  
  /**
   * {@inheritDoc}
   */
  public void initialize(IValueVariable variable_p) {
    
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    String path = workspace.getRoot().getLocation().toString() + "/.metadata/.recorder"; //$NON-NLS-1$
    variable_p.setValue(path);
    
    return;
  }

}
