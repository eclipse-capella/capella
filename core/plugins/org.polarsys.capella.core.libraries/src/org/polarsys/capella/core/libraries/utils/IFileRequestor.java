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
package org.polarsys.capella.core.libraries.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 */
public class IFileRequestor implements IResourceVisitor {

  protected List<IFile> result;
  protected String fileExtension = null;
  protected boolean recursive = false;

  public List<IFile> search(IContainer container, String fileExtension_p, boolean recursive_p) {
    recursive = recursive_p;
    result = new ArrayList<IFile>();
    fileExtension = fileExtension_p;
    try {
      container.accept(this);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  public List<IFile> search(IContainer container, boolean recursive_p) {
    recursive = recursive_p;
    result = new ArrayList<IFile>();
    fileExtension = null;
    try {
      container.accept(this);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean visit(IResource resource) throws CoreException {
    if (resource instanceof IFile) {
      IFile file = (IFile) resource;
      String extension = file.getFileExtension();
      if ((fileExtension == null) || ((extension != null) && extension.equals(fileExtension))) {
        result.add(file);
      }
    } else if (resource instanceof IFolder) {
      return recursive;
    }
    return true;
  }
}
