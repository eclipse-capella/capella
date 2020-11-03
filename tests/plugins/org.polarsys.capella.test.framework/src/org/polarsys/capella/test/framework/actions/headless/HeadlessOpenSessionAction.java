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
package org.polarsys.capella.test.framework.actions.headless;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;

/**
 * @author Erwan Brottier
 */
public class HeadlessOpenSessionAction extends OpenSessionAction {

  protected List<IFile> sessionFiles = new ArrayList<IFile>();

  public HeadlessOpenSessionAction(List<IFile> sessionFiles_p) {
    super();
    sessionFiles = sessionFiles_p;
  }

  @Override
  public IStructuredSelection getStructuredSelection() {
    return new StructuredSelection(sessionFiles);
  }

}
