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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.accessPolicyManager;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.properties.LibraryManagerModel;

public final class AccessPolicyLabelProvider extends LabelProvider implements ITableLabelProvider {

  private LibraryManagerModel model;

  public AccessPolicyLabelProvider(LibraryManagerModel model_p) {
    model = model_p;
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {
    IModel library = (IModel) element;
    switch (columnIndex) {
    case 0:
      return library.getIdentifier().getName();
    case 1:
      return model.getAccessPolicy(library).getLiteral();
    default:
      return null;
    }
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {
    if (columnIndex == 0) {
      ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin("org.polarsys.capella.core.data.gen.edit",
          "icons/full/obj16/Library.gif");
      if (desc != null) {
        return desc.createImage();
      }
    }
    return null;
  }

}
