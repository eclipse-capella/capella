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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.libraries.flexibilityProperties.LibraryManagerModel;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.common.libraries.IAbstractLibrary;

public final class AccessPolicyLabelProvider extends LabelProvider implements ITableLabelProvider {

  private LibraryManagerModel model;

  public AccessPolicyLabelProvider(LibraryManagerModel model_p) {
    model = model_p;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    IAbstractLibrary library = (IAbstractLibrary) element;
    switch (columnIndex) {
      case 0:
        return library.getName();
      case 1:
        return model.getAccessPolicy(library).getLiteral();
      default:
        return null;
    }
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    CapellaLibrary library = (CapellaLibrary) element;
    switch (columnIndex) {
      case 0:
        return EObjectLabelProviderHelper.getImage(library.getProject());
      case 1:
        return null;
      default:
        return null;
    }
  }

}
