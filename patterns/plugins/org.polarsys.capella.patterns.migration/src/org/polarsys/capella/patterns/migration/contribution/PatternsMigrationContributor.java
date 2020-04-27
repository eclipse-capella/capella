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

package org.polarsys.capella.patterns.migration.contribution;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;

/**
 * 
 */
public class PatternsMigrationContributor extends AbstractMigrationContributor {

  /**
   * @param member
   * @return
   */
  @Override
  public boolean isValidResource(IResource member) {
    return PatternCatalogResourceHelper.isPatternCatalogResource(member);
  }

  @Override
  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__PATTERN;
  }

  @Override
  public MigrationRunnable getRunnable(final IFile file) {
    return new ModelMigrationRunnable(file) {
      @Override
      public String getName() {
        return NLS.bind(Messages.MigrationAction_PatternMigration, getFile().getName());
      }
    };
  }

}