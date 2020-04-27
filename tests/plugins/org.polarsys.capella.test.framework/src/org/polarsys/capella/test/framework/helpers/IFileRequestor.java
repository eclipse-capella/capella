/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Erwan Brottier
 */
public class IFileRequestor implements IResourceVisitor {

  protected List<IFile> result;
  protected String fileExtension = null;

  public List<IFile> search(IContainer container, String fileExtension_p) {
    result = new ArrayList<IFile>();
    fileExtension = fileExtension_p;
    try {
      container.accept(this);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  public List<IFile> search(IContainer container) {
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
    }
    return true;
  }

}
