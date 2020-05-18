/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fragmentation.ju.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.AbortedTransactionException;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction;

/**
 * A specific control action handling representations.
 */
public class DesignerControlActionForNonAbusiveFragmentModif extends DesignerControlAction {

  Set<IFile> _files;

  public void setExpectedFilesToBeModified(Set<IFile> p) {
    _files = p;
  }

  public Set<IFile> getExpectedFilesToBeModified() {
    return _files;
  }

  /**
   * Create a new action to control the models.
   */
  public DesignerControlActionForNonAbusiveFragmentModif() {
    super();
  }

  /**
   * Do execute Control/UnControl command within an {@link AbstractNonDirtyingCommand}. Hence these commands are not
   * available for undo/redo.<br>
   * In addition, {@link ResourceSetSync} is manually updated to reflect control( or uncontrol) commands that don't emit
   * EMF notifications.<br>
   * 
   * @param semanticRoot
   * @param representationResources
   * @param realCommand
   */
  @Override
  protected void doExecuteCommand(final EObject semanticRoot, final Collection<Resource> representationResources,
      final Command realCommand) {

    final Map<Resource, ResourceStatus> initialResourceWithStatus = new HashMap<Resource, ResourceStatus>(1);
    try {
      TransactionHelper.getExecutionManager(semanticRoot).execute(new AbstractNonDirtyingCommand() {
        /**
         * Change resource status for specified parameters.
         * 
         * @param resourceSetSync
         * @param resourceWithStatus
         * @param handleResource
         * @param handleResourceStatus
         */
        private void changeResourceSyncStatus(ResourceSetSync resourceSetSync,
            Map<Resource, ResourceStatus> resourceWithStatus, Resource handleResource,
            ResourceStatus handleResourceStatus) {
          // Store resource and its initial status if we need to rollback to initial state.
          resourceWithStatus.put(handleResource, handleResourceStatus);
          resourceSetSync.statusChanged(handleResource, handleResourceStatus, ResourceStatus.CHANGED /* new status */);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void commandInterrupted() {
          commandRolledBack();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void commandRolledBack() {
          restoreResourceSyncStatus(initialResourceWithStatus,
              ResourceSetSync.getOrInstallResourceSetSync(TransactionHelper.getEditingDomain(semanticRoot)));
        }

        /**
         * Restore specified resources in the map to their initial sync status.<br>
         * This method must be called when rollbacking and/or interrupting the command.
         */
        protected void restoreResourceSyncStatus(Map<Resource, ResourceStatus> initialResourceWithStatus,
            ResourceSetSync resourceSetSync) {
          // Restore all modified resources to their initial status.
          Iterator<Entry<Resource, ResourceStatus>> iterator = initialResourceWithStatus.entrySet().iterator();
          while (iterator.hasNext()) {
            Map.Entry<Resource, ResourceStatus> entry = iterator.next();
            resourceSetSync.statusChanged(entry.getKey(), ResourceStatus.CHANGED /* status set in command execution */,
                entry.getValue());
          }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
          ResourceSetSync resourceSetSync = ResourceSetSync
              .getOrInstallResourceSetSync(TransactionHelper.getEditingDomain(semanticRoot));
          // Handle Semantic resource.
          changeResourceSyncStatus(resourceSetSync, initialResourceWithStatus, semanticRoot.eResource(),
              ResourceSetSync.getStatus(semanticRoot.eResource()));
          // Handle representations resources.
          for (Resource representationResource : representationResources) {
            changeResourceSyncStatus(resourceSetSync, initialResourceWithStatus, representationResource,
                ResourceSetSync.getStatus(representationResource));
          }
          // Try to make file writable (if necessary).
          try {
            Collection<IFile> filesToMakeWritable = new ArrayList<IFile>(initialResourceWithStatus.keySet().size());
            for (Resource currentResource : initialResourceWithStatus.keySet()) {
              IFile iFile = EcoreUtil2.getFile(currentResource);
              try {
                iFile.refreshLocal(IFile.DEPTH_ZERO, new NullProgressMonitor());
                filesToMakeWritable.add(iFile);
              } catch (CoreException e) {
                e.printStackTrace();
              }
            }
            // Throws exception if an issue occurs.
            FragmentModificationListener listener = new FragmentModificationListener(_files);
            FragmentModificationListener.checkFilesWritables(filesToMakeWritable, _files);
            listener.makeFilesWritable(TransactionHelper.getEditingDomain(semanticRoot), filesToMakeWritable);
            realCommand.execute();
          } catch (AbortedTransactionException exception) {
            commandRolledBack();
          }
        }
      });
    } finally {
      // Clear the map since everything is OK or rollback.
      initialResourceWithStatus.clear();
    }
  }
}
