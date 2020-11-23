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
package org.polarsys.capella.core.model.handler.command;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;

@SuppressWarnings("restriction")
public class CapellaResourceNamingHelper {

  private CapellaResourceNamingHelper() {
    // prevent instantiation
  }

  /**
   * Invalid characters for Capella Resource names.
   */
  protected static final char[] INVALID_CAPELLA_RESOURCE_CHARACTERS = { '#', '%', '$' };

  /**
   * Validates the given string as the name of a resource valid for one of the given types.
   * <p>
   * In addition to the basic restrictions of valid names for workspace resources (see
   * {@link IWorkspace#validateName(String, int)}, a resource name must also not contain any characters or substrings
   * that are not valid for a Capella resource (see {@link #INVALID_CAPELLA_RESOURCE_CHARACTERS})
   * </p>
   * 
   * @param name
   *          the name to be checked
   * @param typeMask
   *          bitwise-or of the resource type constants ( <code>FILE</code>,<code>FOLDER</code>,<code>PROJECT</code> or
   *          <code>ROOT</code>) indicating expected resource type(s)
   * @return a status object with code <code>IStatus.OK</code> if the given string is valid as a resource name,
   *         otherwise a status object indicating what is wrong with the string
   * @see IResource#PROJECT
   * @see IResource#FOLDER
   * @see IResource#FILE
   * @see IStatus#OK
   */
  public static IStatus validateName(String name, int typeMask) {
    IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();
    IStatus workspaceStatus = workspace.validateName(name, typeMask);

    if (!workspaceStatus.isOK()) {
      return workspaceStatus;
    }

    char[] invalidChars = INVALID_CAPELLA_RESOURCE_CHARACTERS;

    for (char invalidChar : invalidChars) {
      if (name.indexOf(invalidChar) != -1) {
        String message = NLS.bind(Messages.CapellaResourceNamingHelper_invalidCharInName, String.valueOf(invalidChar),
            name);

        return new Status(IStatus.ERROR, ModelHandlerPlugin.PLUGIN_ID, message);
      }
    }

    return Status.OK_STATUS;
  }
}
