/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.views.libraryManager;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 */
public class LibraryContentProvider implements IStructuredContentProvider {

  @Override
  public Object[] getElements(Object inputElement) {
    return (Object[]) inputElement;
  }

  @Override
  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }
}
