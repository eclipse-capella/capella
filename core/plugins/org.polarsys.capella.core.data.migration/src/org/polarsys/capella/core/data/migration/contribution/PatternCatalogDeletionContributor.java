/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.contribution;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;

/**
 * This class delete catalog patterns
 */
public class PatternCatalogDeletionContributor extends AbstractMigrationContributor {

  @Override
  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__PATTERN;
  }

  @Override
  public boolean isValidResource(IResource member) {
    return "patterns".equals(member.getFileExtension());
  }

  @Override
  public AbstractMigrationRunnable getRunnable(IFile file) {
    return new AbstractMigrationRunnable(file) {
      @Override
      public IStatus run(MigrationContext context, boolean checkVersion) {
        try {
          IContainer parent = file.getParent();
          file.delete(true, new NullProgressMonitor());
          if (parent instanceof IFolder && parent.members().length == 0) {
            parent.delete(true, new NullProgressMonitor());
          }
        } catch (CoreException e) {
          return new Status(IStatus.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), e.getMessage(), e);
        }
        return Status.OK_STATUS;
      }
    };
  }

}
