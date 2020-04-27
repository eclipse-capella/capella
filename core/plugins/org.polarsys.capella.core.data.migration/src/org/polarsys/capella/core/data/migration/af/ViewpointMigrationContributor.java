/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.af;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.polarsys.capella.core.af.integration.Messages;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata;

/**
 * @author Thomas Guiu
 *
 */
public class ViewpointMigrationContributor extends AbstractMigrationContributor {

	@Override
	public String getKind() {
		return MigrationConstants.MIGRATION_KIND__CHECK_MISSING_VP;
	}

	@Override
	public boolean isValidResource(IResource member) {
		return ViewpointMetadata.STORAGE_EXTENSION.equals(member.getFileExtension());
	}

	@Override
	public AbstractMigrationRunnable getRunnable(IFile file) {
		return new ModelMigrationRunnable(file) {
		      @Override
		      public String getName() {
		        return Messages.ViewpointMigrationContributor_Name;
		      }
		};
	}
}
