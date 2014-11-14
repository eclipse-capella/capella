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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public class EditComponent extends AbstractReadWriteCommand {
  private Component element;

  public EditComponent(Component element_p) {
    element = element_p;
  }

  @Override
  public String getName() {
    return "Edit allocated functions"; //$NON-NLS-1$
  }

  public void run() {
    CapellaUIPropertiesPlugin.getDefault().openWizard(element);
  }

}
