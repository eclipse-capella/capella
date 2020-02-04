/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.commandline;

import org.polarsys.capella.core.commandline.core.CommandLineArgumentHelper;

public class RemoveHiddenElementsArgumentHelper extends CommandLineArgumentHelper {

  private boolean unsyncDiags = false;

  /**
   * {@inheritDoc}
   */
  @Override
  public void parseArgs(String[] args) {
    super.parseArgs(args);

    // parse validation specific args
    for (int i = 0; i < args.length; i++) {
      String arg = args[i].toLowerCase();

      if (RemoveHiddenElementsCommandLineConstants.UNSYNC_DIAGS.equalsIgnoreCase(arg)) {
        unsyncDiags = true;
        ++i;
      }
    }
  }

  /**
   * @return the sync
   */
  public boolean getUnsyncDiags() {
    return unsyncDiags;
  }
}
