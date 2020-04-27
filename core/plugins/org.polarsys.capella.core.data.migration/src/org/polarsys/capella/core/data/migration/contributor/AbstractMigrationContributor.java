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
package org.polarsys.capella.core.data.migration.contributor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;

/**
 *
 */
public abstract class AbstractMigrationContributor {

  public abstract String getKind();

  public Collection<IResource> getMigrableFiles(IResource resource) {
    Collection<IResource> files = new ArrayList<IResource>();

    try {
      if (resource instanceof IContainer) {
        for (IResource member : ((IContainer) resource).members()) {
          if (isValidResource(member)) {
            files.add(member);

          } else if (member instanceof IResource) {
            files.addAll(getMigrableFiles(member));
          }
        }
      } else if (isValidResource(resource)) {
        files.add(resource);
      }

    } catch (CoreException e) {
      e.printStackTrace();
    }

    return files;
  }

  public abstract boolean isValidResource(IResource member);

  public abstract AbstractMigrationRunnable getRunnable(IFile file);

}
