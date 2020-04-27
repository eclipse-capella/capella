/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;

/**
 * @author Thomas Guiu
 * 
 */
public abstract class ContributoryMigrationRunnable extends MigrationRunnable {

  public ContributoryMigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  protected IStatus analyseResourceSet(ResourceSet resourceSet) {
	return CapellaSessionHelper.checkModelsCompliancy(resourceSet) ? Status.OK_STATUS : Status.CANCEL_STATUS;
  }

  @Override
  protected IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {
	return MigrationHelpers.getInstance().preMigrationExecute(_file, context, checkVersion);
  }
  
  @Override
  protected void registerExtendedMetaData(ResourceSet resourceSet, MigrationContext context) {
	MigrationHelpers.getInstance().contributePackageRegistry(resourceSet.getPackageRegistry(), context);
  }

  @Override
  protected void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) throws IOException {
	MigrationHelpers.getInstance().postMigrationExecute(executionManager, resourceSet, context);
  }
  
  @Override
  protected void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
	MigrationHelpers.getInstance().postMigrationExecuteCommands(executionManager, resourceSet, context);
  }
  
  @Override
  protected void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context) {
	MigrationHelpers.getInstance().preSaveResource(executionManager, resource, context);
  }

  @Override
  protected void postDispose(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
  	MigrationHelpers.getInstance().dispose(executionManager, resourceSet, context);
  }

  @Override
  protected void newResource(Resource resource, MigrationContext context) {
	MigrationHelpers.getInstance().newResource(resource, context);
  }

}
