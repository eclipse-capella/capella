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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.activeStateManager;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.libraries.IModel;

public class ActiveStateLabelProvider extends LabelProvider {

  @Override
  public String getText(Object element) {
    IModel library = (IModel) element;
    return library.getIdentifier().getName();
  }

  @Override
  public Image getImage(Object element) {
    ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin("org.polarsys.capella.core.data.gen.edit", "icons/full/obj16/Library.gif");
    if (desc != null) {
      return desc.createImage();
    }
    return null;
  }
}
