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
package org.polarsys.capella.core.libraries.ui.views.libraryManager.referencesManager;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.ILibraryManager;

/**
 */
public class ReferencesLabelProvider extends LabelProvider {

  @Override
  public String getText(Object element) {
    StringBuffer buffer = new StringBuffer();
    IAbstractLibrary library = (IAbstractLibrary) element;
    buffer.append(library.getName());
    Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getReferencedLibraries(library, false);
    Iterator<IAbstractLibrary> iterator = libraries.iterator();
    if (libraries.size() > 0) {
      buffer.append(" (");
      while (iterator.hasNext()) {
        IAbstractLibrary referencedLibrary = iterator.next();
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
    CapellaLibrary library = (CapellaLibrary) element;
    return EObjectLabelProviderHelper.getImage(library.getProject());
  }
}
