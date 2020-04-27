/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.commandline;

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
