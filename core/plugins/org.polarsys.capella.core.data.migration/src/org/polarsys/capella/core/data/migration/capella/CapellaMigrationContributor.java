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
package org.polarsys.capella.core.data.migration.capella;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class CapellaMigrationContributor extends AbstractMigrationContributor {

  /**
   * @param member
   * @return
   */
  @Override
  public boolean isValidResource(IResource member) {
    return CapellaResourceHelper.isCapellaResource(member, true)
        // This is only required for Capella 5.0 since legacy resource must be migrated.
        // TODO remove this in the next version
        || CapellaResourceHelper.isLegacyCapellaResource(member, true);
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
