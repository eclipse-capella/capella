/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalCommandStack;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.common.mdsofa.common.activator.SolFaCommonActivator;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;

/**
 * 
 */
public abstract class MigrationRunnable extends AbstractMigrationRunnable {

  public MigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  public String getName() {
    return getClass().getName();
  }

  protected boolean isTrackedResource(Resource resource) {
    return true;
  }

  protected IStatus preMigrationExecute(IResource fileToMigrate, MigrationContext context, boolean checkVersion) {
    return Status.OK_STATUS;
  }

  protected void postMigrationExecuteCommands(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) {
  }

  protected void preSaveResource(ExecutionManager executionManager, Resource resource, MigrationContext context) {
  }

  protected void postDispose(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {
  }

  protected void newResource(Resource resource, MigrationContext context) {
  }

  @Override
  public IStatus run(MigrationContext context, boolean checkVersion) {

    LongRunningListenersRegistry.getInstance().operationStarting(getClass());

    context.setResource((IFile) getFile());

    IStatus result = preMigrationExecute(_file, context, checkVersion);
    if (!result.isOK()) {
      return result;
    }

    ExecutionManager executionManager = ExecutionManagerRegistry.getInstance().addNewManager();

    IProgressMonitor monitor = context.getProgressMonitor();
    monitor.setTaskName(getName());
    monitor.beginTask(getName(), 100);

    // Precondition
    if (null == executionManager) {
      return Status.CANCEL_STATUS;
    }

    // Get the resource set.
    ResourceSet resourceSet = executionManager.getEditingDomain().getResourceSet();

    // Create a local resource factory and set it as default one.
    resourceSet.setResourceFactoryRegistry(createLocalResourceFactory(context));

    // Register the mapping description.
    registerExtendedMetaData(resourceSet, context);

    // Create a tracking modification adapter.
    TrackingModificationAdapter trackingModificationAdapter = new TrackingModificationAdapter();
    resourceSet.eAdapters().add(trackingModificationAdapter);

    // Execute migration against the execution manager.
    try {

      if (result.isOK()) {
        // Load all resources. A first step of migration is performed at loading (ecore2ecore and resource's
        // loaderHelper create a migrated content of model)
        context.setProgressMonitor(SubMonitor.convert(monitor, 40));
        result = performLoadResources(getFile(), executionManager, resourceSet, context);
      }

      if (result.isOK()) {
        // Perform additionalStuff into another command (a second step of migration is performed after model is loaded)
        context.setProgressMonitor(SubMonitor.convert(monitor, 40));
        result = performPostMigrationExecute(executionManager, resourceSet, context);
      }

      if (result.isOK()) {
        // Save all required resources
        context.setProgressMonitor(SubMonitor.convert(monitor, 20));
        result = performSaveResources(executionManager, resourceSet, context);
      }

    } catch (OutOfMemoryError error) {
      MigrationHelpers.getInstance().onOutOfMemoryError(error, context);
      throw error;

    } finally {
      dispose(executionManager, resourceSet, context);
      monitor.done();
    }
    return result;
  }

  /**
   * Track modifications that set the related resource as modified.<br>
   * This class is an EContentAdapter instance. Hence, add it to the resource set, will add it automatically to
   * contained resources in the resource set.
   */
  class TrackingModificationAdapter extends EContentAdapter {
    /**
     * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(Notification notification) {
      super.notifyChanged(notification);
      // Get the notifier.
      Object notifier = notification.getNotifier();
      // Searching for its resource container.
      Resource resource = null;
      if (notifier instanceof EObject) {
        resource = ((EObject) notifier).eResource();
      } else if (notifier instanceof Resource) {
        resource = (Resource) notifier;
      }
      // Preconditions.
      if ((null == resource) || (resource.getURI().isPlatformPlugin())) {
        return;
      }
      if (!isTrackedResource(resource)) {
        return;
      }

      switch (notification.getEventType()) {
      case Notification.SET:
      case Notification.UNSET:
      case Notification.MOVE:
      case Notification.ADD:
      case Notification.REMOVE:
      case Notification.ADD_MANY:
      case Notification.REMOVE_MANY: {
        setResourceAsModified(resource);
        break;
      }
      }
    }

    /**
     * Set given resource as modified.
     * 
     * @param resource
     */
    private void setResourceAsModified(Resource resource) {
      if (!resource.isModified()) {
        resource.setModified(true);
      }
    }
  }

  /**
   * Create a Capella resource that supports migration.
   */
  public abstract XMLResource doCreateResource(URI uri, MigrationContext context);

  /**
   * Create a resource factory that delegates capella resource creation to
   * {@link #doCreateResource(ExecutionManager, URI)}.
   * 
   * @return a not <code>null</code> instance.
   */
  protected Registry createLocalResourceFactory(final MigrationContext context) {
    Registry localResourceFactoryRegistry = new ResourceFactoryRegistryImpl() {
      private Resource.Factory _factory;

      /**
       * @see org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl#delegatedGetFactory(org.eclipse.emf.common.util.URI)
       */
      @Override
      public Resource.Factory delegatedGetFactory(URI uri) {
        Resource.Factory result = null;
        if (isHandledUri(uri)) {
          if (!shallCreateResource(uri)) {
            return null;
          }
          if (null == _factory) {
            _factory = new ResourceFactoryImpl() {
              /**
               * @see org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl#createResource(org.eclipse.emf.common.util.URI)
               */
              @Override
              public Resource createResource(URI uri2) {
                Resource resource = doCreateResource(uri2, context);
                if (resource == null) {
                  resource = Resource.Factory.Registry.INSTANCE.getFactory(uri2).createResource(uri2);
                }
                if (resource != null) {
                  newResource(resource, context);
                }
                return resource;
              }
            };
          }
          result = _factory;
        } else {
          result = Resource.Factory.Registry.INSTANCE.getFactory(uri);
        }
        return result;
      }
    };
    return localResourceFactoryRegistry;
  }

  /**
   * Returns whether the given resource shall be handled while this migration
   * 
   * In that case, we can create a resource through doCreateResource or it will be created by the default resource
   * factory
   */
  protected boolean isHandledUri(URI uri) {
    return true;
  }

  /**
   * Returns whether the given resource shall be created if required.
   * 
   * For instance, in a semantic migration we don't want to load aird resources.
   * 
   * In that case, there will be no resourceFactory for the given uri.
   */
  protected boolean shallCreateResource(URI uri) {
    return true;
  }

  protected void registerExtendedMetaData(ResourceSet resourceSet, MigrationContext context) {

  }

  /**
   * This method load all resources into a command
   * 
   * @param executionManager
   * @param resourceSet
   * @param subProgressMonitor
   * @return
   */
  protected IStatus performLoadResources(final IFile modelFileToMigrate, final ExecutionManager executionManager,
      final ResourceSet resourceSet, final MigrationContext context) {
    final IStatus[] result = new IStatus[] { Status.OK_STATUS };

    executionManager.execute(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void commandRolledBack() {
        result[0] = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, getName(), null);
      }

      public void run() {
        context.getProgressMonitor().beginTask(Messages.MigrationAction_Command_LoadResources, 1); // We are not able to
        // know how resources
        // will be loaded
        context.getProgressMonitor().subTask(Messages.MigrationAction_Command_LoadResources);
        try {
          // Add resource to migration resource set and loads it.
          resourceSet.getResource(FileHelper.getFileFullUri(modelFileToMigrate.getFullPath().toString()), true);
          // Make sure the model is fully loaded.
          int resourcesCount = resourceSet.getResources().size();
          int previousResourcesCount = 0;
          while (resourcesCount != previousResourcesCount) {
            previousResourcesCount = resourcesCount;
            EcoreUtil.resolveAll(resourceSet);
            resourcesCount = resourceSet.getResources().size();
          }

          result[0] = analyseResourceSet(resourceSet);

          context.getProgressMonitor().worked(1);
        } catch (Exception exception) {
          result[0] = CapellaSessionHelper.handleLoadingErrors(exception);

        } finally {
          context.getProgressMonitor().done();
        }
      }
    });
    return result[0];
  }

  protected IStatus analyseResourceSet(ResourceSet resourceSet) {
    return Status.OK_STATUS;
  }

  /**
   * This method calls the sub-method postMigrationExecute into a transaction command
   * 
   * @param executionManager
   * @param resourceSet
   */
  protected IStatus performPostMigrationExecute(final ExecutionManager executionManager, final ResourceSet resourceSet,
      final MigrationContext context) {
    final IStatus[] result = new IStatus[] { Status.OK_STATUS };

    executionManager.execute(new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void commandRolledBack() {
        result[0] = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, getName(), null);
      }

      public void run() {
        try {
          // Do additional stuff.
          context.getProgressMonitor().beginTask(Messages.MigrationAction_Command_ProcessingMigration, 1);
          context.getProgressMonitor().subTask(Messages.MigrationAction_Command_ProcessingMigration);
          postMigrationExecute(executionManager, resourceSet, context);
          context.getProgressMonitor().worked(1);
        } catch (Exception exception) {
          result[0] = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, getName() + ": " + exception.getMessage(), //$NON-NLS-1$
              exception);
        } finally {
          context.getProgressMonitor().done();
        }
      }
    });

    postMigrationExecuteCommands(executionManager, resourceSet, context);
    return result[0];
  }

  /**
   * This method save all required resources into a command
   * 
   * @param executionManager
   * @param resourceSet
   * @param subProgressMonitor
   * @return
   */
  protected IStatus performSaveResources(final ExecutionManager executionManager, final ResourceSet resourceSet,
      final MigrationContext context) {
    final IStatus[] result = new IStatus[] { Status.OK_STATUS };
    // Run the save operation within another command to make sure validateEdit prompt a dialog to make files writable
    // with an SCM.
    executionManager.execute(new AbstractReadWriteCommand() {
      public void run() {
        try {
          // Pre-condition.
          if (Status.OK_STATUS != result[0]) {
            return;
          }

          context.getProgressMonitor().beginTask(Messages.MigrationAction_Command_SaveResources,
              resourceSet.getResources().size());
          context.getProgressMonitor().subTask(Messages.MigrationAction_Command_SaveResources);
          // No Error raised, let'save the modified resources.
          for (Resource resource : resourceSet.getResources()) {
            if (resource.isModified()
                // crossreferencer resource is modified but shall not be saved
                && resource.getURI().isPlatformResource()) {
              // Force to make file writeable : mandatory for aird migration since no notification is received with an
              // EObject as notifier.
              // Without that, files are made writable silently that is not consistent with .melodymodeller migration
              // process.
              context.getProgressMonitor()
                  .subTask(NLS.bind(Messages.MigrationAction_Command_SaveResource, resource.getURI().toString()));

              preSaveResource(executionManager, resource, context);

              IUserEnforcedHelper userEnforcedHelper = SolFaCommonActivator.getDefault().getUserEnforcedHelper();
              userEnforcedHelper.makeFileWritable(org.polarsys.capella.common.helpers.EcoreUtil2.getFile(resource));
              resource.save(Collections.emptyMap());
            }
            context.getProgressMonitor().worked(1);
          }

        } catch (Exception exception) {
          result[0] = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, getName() + ": " + exception.getMessage(), //$NON-NLS-1$
              exception);

        } finally {
          context.getProgressMonitor().done();
        }
      }
    });
    return result[0];
  }

  protected void postMigrationExecute(ExecutionManager executionManager, ResourceSet resourceSet,
      MigrationContext context) throws IOException {
  }

  /**
   * Call after migration execution.<br>
   * This method is not run within TED transactions.
   */
  protected void dispose(ExecutionManager executionManager, ResourceSet resourceSet, MigrationContext context) {

    // Remove the execution manager from the registry.
    ExecutionManagerRegistry.getInstance().removeManager(executionManager);
    // Dispose the editing domain that is supposed to clean all the previous stuff !
    TransactionalEditingDomain editingDomain = executionManager.getEditingDomain();
    EList<Resource> resources = resourceSet.getResources();
    // Additional clean stuffs on the resource set since we can't do that within the TED dispose due to memory leaks
    // cause by the Sirius session when
    // disposing...

    for (Resource resource : resources) {
      resource.unload();
      resource.eAdapters().clear(); // remove any adapters
    }

    // Don't put the unload loop in the dispose of the ED otherwise we will have memory leaks on Sirius session close
    // operation.
    editingDomain.dispose();
    if ((editingDomain.getCommandStack() != null)
        && (editingDomain.getCommandStack() instanceof InternalTransactionalCommandStack)) {
      ((InternalTransactionalCommandStack) editingDomain.getCommandStack()).dispose();
    }
    // Finally clear the resource set.
    resources.clear();

    postDispose(executionManager, resourceSet, context);
    LongRunningListenersRegistry.getInstance().operationEnded(getClass());
  }
}
