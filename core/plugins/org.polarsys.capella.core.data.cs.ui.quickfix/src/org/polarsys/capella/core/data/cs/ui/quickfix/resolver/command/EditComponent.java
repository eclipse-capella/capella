/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver.command;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

public class EditComponent extends AbstractReadWriteCommand {
  private Component element;

  public EditComponent(Component element) {
    this.element = element;
  }

  @Override
  public String getName() {
    return "Edit allocated functions"; //$NON-NLS-1$
  }

  public void run() {
    CapellaUIPropertiesPlugin.getDefault().openWizard(element);
  }

}
