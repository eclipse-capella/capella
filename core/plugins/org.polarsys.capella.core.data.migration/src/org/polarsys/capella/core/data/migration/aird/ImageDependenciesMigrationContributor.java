/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.aird;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

/**
 * Migration contributor that will open the session and update the aird with the images project dependencies.
 * 
 * @author Laurent Fasani
 */
public class ImageDependenciesMigrationContributor extends AirdMigrationContributor {

  @Override
  public MigrationRunnable getRunnable(IFile file) {
    return new AirdMigrationRunnable(file) {
      @Override
      public String getName() {
        return Messages.MigrationAction_ImageProjectDependencies;
      }

      @Override
      public IStatus run(MigrationContext context, boolean checkVersion) {
        IFile airdFile = getFile();
        URI airdURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
        Session session = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
        if (session != null) {
          session.open(new NullProgressMonitor());

          session.getTransactionalEditingDomain().getCommandStack()
              .execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                  new ImageDependenciesAnnotationHelper((DAnalysisSessionImpl) session)
                      .updateAllImageProjectsDependencies();
                }
              });
          session.save(new NullProgressMonitor());
          session.close(new NullProgressMonitor());
        }
        return Status.OK_STATUS;
      }
    };
  }

  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__IMAGE_DEPENDENCIES;
  }
}
