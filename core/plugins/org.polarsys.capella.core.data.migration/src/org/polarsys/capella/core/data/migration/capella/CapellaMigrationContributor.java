/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.capella;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.contributor.IMigrationContributor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class CapellaMigrationContributor implements IMigrationContributor {

  public Collection<IResource> getMigrableFiles(IResource resource) {
    Collection<IResource> files = new ArrayList<IResource>();

    try {
      if (resource instanceof IContainer) {
        for (IResource member : ((IContainer) resource).members()) {
          if (isValidResource(member)) {
            files.add(member);
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

  /**
   * @param member
   * @return
   */
  public boolean isValidResource(IResource member) {
    return CapellaResourceHelper.isCapellaResource(member);
  }

  @Override
  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__SEMANTIC;
  }

  @Override
  public MigrationRunnable getRunnable(IFile file) {
    return new ModelMigrationRunnable(file);
  }
}
