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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;

/**
 */
public class ReferencesLabelProvider extends LabelProvider {

  @Override
  public String getText(Object element) {
    StringBuffer buffer = new StringBuffer();
    IModel library = (IModel) element;
    buffer.append(library.getIdentifier().getName());
    Collection<IModelIdentifier> libraries = library.getReferences();
    Iterator<IModelIdentifier> iterator = libraries.iterator();
    if (libraries.size() > 0) {
      buffer.append(" (");
      while (iterator.hasNext()) {
        IModelIdentifier referencedLibrary = iterator.next();
        buffer.append(referencedLibrary.getName());
        if (iterator.hasNext()) {
          buffer.append(", ");
        }
      }
      buffer.append(")");
    }
    return buffer.toString();
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
