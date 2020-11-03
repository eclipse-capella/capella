/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commandline.core;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

/**
 * 
 */
public interface IFileImporter {

  /**
   * @param file
   * @return
   */
  Collection<IProject> importFile(IFile file, boolean forceImport);

}
