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
package org.polarsys.capella.core.sirius.ui.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.ef.domain.AbstractEditingDomainResourceSetListenerImpl;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.IUserEnforcedHelper2;
import org.polarsys.capella.common.mdsofa.common.activator.SolFaCommonActivator;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.AbortedTransactionException;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.pre.condition.IFileModificationPreconditionChecker;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;

/**
 * File Modification Pre Commit listener.<br>
 * When resources are in RO, a dialog is prompted to the end-user to make the file writable.<br>
 * If the resources are not writable, an {@link AbortedTransactionException} is thrown in order to roll back the
 * transaction.
 */
public class FileModificationPreCommitListener extends AbstractEditingDomainResourceSetListenerImpl implements
    SessionManagerListener {
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(
      IReportManagerDefaultComponents.UI);
  /**
   * Disable the validate edit check.
   */
  private volatile boolean _disableValidateEdit;
  /**
   * Flag to register this pre commit listener as session listener without direct dependencies to avoid
   * {@link ClassCircularityError}.
   */
  // private volatile boolean _needRegistration;
  /**
   * File modification precondition checker used before files are made writable.
   */
  private static IFileModificationPreconditionChecker __fileModificationPrecondtionChecker;

  /**
   * Flag used to know if file modification precondition checker has been lookup.
   */
  private static boolean __alreadyLookup;

  /**
   * Constructor.
   */
  public FileModificationPreCommitListener() {
    super();
    // _needRegistration = true;
    SessionManager.INSTANCE.addSessionsListener(this);
  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    super.disposedEditingDomain(editingDomain);
    SessionManager.INSTANCE.removeSessionsListener(this);
  }
  
  /**
   * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isAggregatePrecommitListener()
   */
  @Override
  public boolean isAggregatePrecommitListener() {
    return true;
  }

  /**
   * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isPrecommitOnly()
   */
  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  /**
   * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
   */
  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    // if (_needRegistration) {
    // Used to avoid a ClassCircularityError: This class must not implement SessionListener.
    // SessionsWatchDog.enableSessionMonitoring(this);
    // Workaround
    // SessionsWatchDog.getResourceAccessPolicyListener().transactionAboutToCommit(event_p);
    // END Workaround
    // _needRegistration = false;
    // }
    // Precondition.
    if (event.getTransaction().isReadOnly()) {
      return null;
    }
    if (_disableValidateEdit) {
      return null;
    }
    // Use to check if resources that contain modified objects in given event are writable.
    List<?> notifications = event.getNotifications();
    Set<IFile> filesToMakeWritable = new HashSet<IFile>(0);
    Set<Resource> resourcesToMakeWritable = new HashSet<Resource>(0);

    Map<ResourceSetSync, Set<Resource>> additionalResourcesToChangeStatus = new HashMap<ResourceSetSync, Set<Resource>>(
        0);
    Map<EObject, Resource> removedElementFromResource = new HashMap<EObject, Resource>(0);
    // Collect all files that require to make them writable to be able to run the current transaction.
    for (Object currentNotification : notifications) {
      // Get the current notification.
      if (currentNotification instanceof Notification) {
        // Get the notifier.
        Notification notification = (Notification) currentNotification;
        Object notifier = notification.getNotifier();
        if (notifier instanceof EObject) {
          // Mark dirty dependent resources if necessary.
          if (CapellaResourceHelper.isSemanticElement(notifier)) {
            markDirtyDependentResources(resourcesToMakeWritable, additionalResourcesToChangeStatus, notification,
                (EObject) notifier, removedElementFromResource);
          }
          Resource resource = ((EObject) notifier).eResource();
          if ((null != resource)
              && !new NotificationQuery((Notification) currentNotification).isTransientNotification()) {
            // Check the notifier resource is writable.
            handleMakeResourceWritable(resourcesToMakeWritable, currentNotification, resource);
          }
        }
      }
    }

    // List to store writable resources
    Set<Resource> writableResources = new HashSet<Resource>();

    // Retrieve files of resources and make them writable if required.
    if (!resourcesToMakeWritable.isEmpty()) {
      for (Resource resource : resourcesToMakeWritable) {
        IFile file = EcoreUtil2.getFile(resource);
        boolean fileAdded = handleMakeFileWritable(filesToMakeWritable, file);
        if (!fileAdded)
          writableResources.add(resource);
      }
    }

    // Make files writable (if any).
    if (!filesToMakeWritable.isEmpty()) {
      try {
        makeFilesWritable(event.getEditingDomain(), filesToMakeWritable);
      } catch (RollbackException re) {
        // We cannot clear readonly flag, may be the user has no write access
        // => check the user write access and report pb if any
        doCheckUserWritePermission(resourcesToMakeWritable);
      }
    }

    // Check precondition for writable resources
    if (!writableResources.isEmpty()) {
      doCheckPrecondition(writableResources);
    }

    // The readonly flags are cleared, now we need to check that users has write access
    if (!resourcesToMakeWritable.isEmpty()) {
      doCheckUserWritePermission(resourcesToMakeWritable);
    }
    // He we are with no error: let's change the status for dependent resources.
    Iterator<Entry<ResourceSetSync, Set<Resource>>> iterator = additionalResourcesToChangeStatus.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<ResourceSetSync, Set<Resource>> entry = iterator.next();
      ResourceSetSync resourceSetSync = entry.getKey();
      Set<Resource> dependentResources = entry.getValue();
      for (Resource resource : dependentResources) {
        resourceSetSync.statusChanged(resource, ResourceStatus.SYNC, ResourceStatus.CHANGED /* new status */);
      }
    }
    return null;
  }

  public void doCheckPrecondition(Set<Resource> resourcesToMakeWritable) throws AbortedTransactionException {
    List<IFile> lstFile = new ArrayList<IFile>();
    for (Resource res : resourcesToMakeWritable)
      lstFile.add(EcoreUtil2.getFile(res));

    boolean result = true;
    // Get a file modification precondition delegator.
    final IFileModificationPreconditionChecker preConditionChecker = getFileModificationPreconditionChecker();
    if (null != preConditionChecker) {
      result = preConditionChecker.fulfillConditions(lstFile);
    }
    if (!result) {
      throw new AbortedTransactionException(Status.CANCEL_STATUS, "Pre-conditions are not satisfactory"); //$NON-NLS-1$
    }
  }

  /**
   * Check that current application has access to a set of resources.
   * 
   * @param resourcesToMakeWritable
   * @throws AbortedTransactionException
   *           if {@link #checkUserWritePermission(List)} does not return an OK status
   */
  private void doCheckUserWritePermission(Set<Resource> resourcesToMakeWritable) throws AbortedTransactionException {
    // check that the file is accessible (e.g. the file can have a cleared readonly flag but if the user do not have
    // write access the file is inaccessible)
    List<IFile> filesToCheck = new ArrayList<IFile>();
    for (Resource resource : resourcesToMakeWritable) {
      IFile file = EcoreUtil2.getFile(resource);
      if (null != file) {
        filesToCheck.add(file);
      }
    }
    
    IStatus userWritePermissionStatus = checkUserWritePermission(new ArrayList<IFile>(filesToCheck));
    if (!userWritePermissionStatus.isOK()) {
      throw new AbortedTransactionException(Status.CANCEL_STATUS,
          "End-user canceled to make the file writable or it failed to make it writable."); //$NON-NLS-1$
    }
  }

  /**
   * Mark dirty dependent resources if the specified notification contains object that are moved from a resource to a
   * new one.
   * 
   * @param filesToMakeWritable_p
   * @param additionalResourcesToChangeStatus
   * @param notification
   * @param notifier
   * @param removedElementFromResource
   */
  @SuppressWarnings("unchecked")
  protected void markDirtyDependentResources(final Set<Resource> resourcesToMakeWritable,
      final Map<ResourceSetSync, Set<Resource>> additionalResourcesToChangeStatus, Notification notification,
      EObject notifier, Map<EObject, Resource> removedElementFromResource) {
    // Collect additional resources to check out for current EObject.
    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (feature instanceof EReference) {
      // Search for a containment change where the eResource is modified.
      EReference reference = (EReference) feature;
      if (reference.isContainment() && !notification.isTouch()) {
        // New value objects collection.
        List<EObject> objectsToUpdate = new ArrayList<EObject>(1);
        Resource notifierResource = notifier.eResource();
        switch (notification.getEventType()) {
          case Notification.REMOVE:
          case Notification.UNSET:
            Object oldValue = notification.getOldValue();
            if (CapellaResourceHelper.isSemanticElement(oldValue)) {
              EObject removedElement = (EObject) oldValue;
              // Stored the removed object and its current resource to compare with another one in next future.
              removedElementFromResource.put(removedElement, notifierResource);
            }
            return; // Force to exit this method.
          case Notification.REMOVE_MANY:
          break;
        case Notification.SET:
        case Notification.ADD:
          objectsToUpdate.add((EObject) notification.getNewValue());
          break;
        case Notification.ADD_MANY:
          objectsToUpdate.addAll((Collection<? extends EObject>) notification.getNewValue());
          break;
        }
        // Loop over all objects that need an update.
        for (EObject objectToUpdate : objectsToUpdate) {
          // Check if the resource of the object to update is the same as is its new container ?
          Resource objectToUpdateResource = removedElementFromResource.get(objectToUpdate);
          if ((null != objectToUpdateResource) && !notifierResource.equals(objectToUpdateResource)) {
            Collection<Resource> dependentResources = RepresentationHelper.collectDependentResources(objectToUpdate);
            for (Resource dependentResource : dependentResources) {
              handleMakeResourceWritable(resourcesToMakeWritable, objectToUpdate, dependentResource);
              // Get the resourceSetSync for current editing domain.
              ResourceSetSync resourceSetSync = ResourceSetSync
                  .getOrInstallResourceSetSync((TransactionalEditingDomain) AdapterFactoryEditingDomain
                      .getEditingDomainFor(objectToUpdate));
              // Is it the container resource sync ?
              if (ResourceStatus.SYNC.equals(ResourceSetSync.getStatus(dependentResource))) {
                Set<Resource> resources = additionalResourcesToChangeStatus.get(resourceSetSync);
                if (null == resources) {
                  resources = new HashSet<Resource>(0);
                  additionalResourcesToChangeStatus.put(resourceSetSync, resources);
                }
                // Add it to the ones to change.
                resources.add(dependentResource);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Handle make resource writable.
   * 
   * @param filesToMakeWritable
   * @param notificationObject
   * @param file
   */
  public void handleMakeResourceWritable(final Set<Resource> resourcesToMakeWritable, Object notificationObject,
      Resource resource) {
    if ((null != resource)) {
      if (__logger.isDebugEnabled()) {
        // Avoid multi traces for the same file.
        if (!resourcesToMakeWritable.contains(resource)) {
          // Not I18n since it is debug traces.
          __logger
              .debug(new EmbeddedMessage(
                  StringHelper
                      .formatMessage(
                          "Make Resource ''{0}'' Writable (if required) due to notification:{1}", new String[] { resource.getURI().toString(), notificationObject.toString() }), //$NON-NLS-1$
                  IReportManagerDefaultComponents.UI));
        }
      }
      resourcesToMakeWritable.add(resource);
    }
  }

  /**
   * Handle make file writable.
   * 
   * @param filesToMakeWritable
   * @param notificationObject
   * @param file
   * @return true if file is added to filesToMakeWritable, otherwise false
   */
  public boolean handleMakeFileWritable(final Set<IFile> filesToMakeWritable, IFile file) {
    if ((null != file) && file.isReadOnly()) {
      if (__logger.isDebugEnabled()) {
        // Avoid multi traces for the same file.
        if (!filesToMakeWritable.contains(file)) {
          // Not I18n since it is debug traces.
          __logger.debug(new EmbeddedMessage(StringHelper.formatMessage(
              "Make File ''{0}'' Writable", new String[] { file.getFullPath().toString() }), //$NON-NLS-1$
              IReportManagerDefaultComponents.UI));
        }
      }
      // Add this RO resource to make it writable.
      filesToMakeWritable.add(file);
      return true;
    }
    return false;
  }

  /**
   * Get the file modification precondition checker.
   * 
   * @return <code>null</code> if none.
   */
  private static IFileModificationPreconditionChecker getFileModificationPreconditionChecker() {
    if (!__alreadyLookup && (null == __fileModificationPrecondtionChecker)) {
      IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(
          "org.polarsys.capella.core.model.handler", "fileModificationPreconditionChecker"); //$NON-NLS-1$ //$NON-NLS-2$
      if (configurationElements.length > 0) {
        __fileModificationPrecondtionChecker = (IFileModificationPreconditionChecker) ExtensionPointHelper
            .createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
      }
      __alreadyLookup = true; // Lookup through the platform is done.
    }
    return __fileModificationPrecondtionChecker;
  }

  /**
   * Make files writable. If end-user turns down (or operation fails), an exception is thrown.
   * 
   * @param event_p
   * @param filesToMakeWritable
   * @throws AbortedTransactionException
   */
  public static void makeFilesWritable(TransactionalEditingDomain editingDomain,
      final Collection<IFile> filesToMakeWritable) throws AbortedTransactionException {
    final boolean[] result = { true };
    // Get a file modification precondition delegator.
    final IFileModificationPreconditionChecker preConditionChecker = getFileModificationPreconditionChecker();
    final Display display = PlatformUI.getWorkbench().getDisplay();
    // Call to make file writable within the UI thread with a 'Privileged' runnable regarding transactions.
    // Indeed, making the file writable with CC team adapter cause UI refreshes that run read-only transaction within
    // the current RW transaction.
    // Without a 'Privileged' runnable, that causes dead locks.
    display.syncExec(editingDomain.createPrivilegedRunnable(new Runnable() {
      public void run() {
        if (null != preConditionChecker) {
          result[0] = preConditionChecker.fulfillConditions(filesToMakeWritable);
        }
        if (result[0]) {
          IUserEnforcedHelper userEnforcedHelper = SolFaCommonActivator.getDefault().getUserEnforcedHelper();
          // Make the files writable.
          if (userEnforcedHelper instanceof IUserEnforcedHelper2) {
            // Preconditions are fulfilled, make files writables.
            IStatus status = ((IUserEnforcedHelper2) userEnforcedHelper).makeFilesWritable(filesToMakeWritable
                .toArray(new IFile[filesToMakeWritable.size()]));
            if (!status.isOK()) {
              result[0] = false;
            }
          }
        }
      }
    }));
    if (!result[0]) {
      throw new AbortedTransactionException(Status.CANCEL_STATUS,
          "End-user canceled to make the file writable or it failed to make it writable."); //$NON-NLS-1$
    }
  }

  /**
   * Checks if the current application can access to filesToMakeWritable_p.
   * 
   * @param filesToMakeWritable_p
   * @return CANCEL_STATUS if the current application cannot access to filesToMakeWritable_p and OK_STATUS otherwise.
   */

  /**
   * Checks if the current application can access to filesToMakeWritable_p.
   * 
   * @param filesToMakeWritable
   * @return CANCEL_STATUS if the current application cannot access to filesToMakeWritable_p and OK_STATUS otherwise.
   */

  protected static IStatus checkUserWritePermission(List<IFile> filesToMakeWritable) {

    Set<File> filesWithNoWritePermission = new HashSet<File>();

    // collects file that has no write permission for the user
    for (IFile file : filesToMakeWritable) {

      IPath rawLocation = file.getRawLocation();
      if (null != rawLocation) {
        File fileSysFile = new File(rawLocation.toOSString());
        FileOutputStream fo = null;
        try {
          fo = new FileOutputStream(fileSysFile, true);
        } catch (FileNotFoundException e) {
          filesWithNoWritePermission.add(fileSysFile);
        } finally {
          if (fo != null) {
            try {
              fo.close();
            } catch (IOException e) {
            }
          }
        }
      }
    }
    // No file is inaccessible => OK status
    if (filesWithNoWritePermission.isEmpty()) {
      return Status.OK_STATUS;
    }

    final StringBuilder sb = new StringBuilder(
        "Following files are not accessible (may result from a write access denied)\n");
    for (File f : filesWithNoWritePermission) {
      sb.append(f.getAbsolutePath() + "\n");
    }
    CapellaSessionHelper.reportError(new Status(IStatus.ERROR,SiriusUIPlugin.getDefault().getBundle().getSymbolicName(), sb.toString()));
    return Status.CANCEL_STATUS;
  } 

  /**
   * Set whether or not valid edit is enabled or disabled.
   * 
   * @param disableValidateEdit
   */
  public void setDisableValidateEdit(boolean disableValidateEdit) {
    _disableValidateEdit = disableValidateEdit;
  }

  /**
   * Allows monitoring sessions to enable / disable the Capella pre commit listener on
   * SessionListener.ABOUT_TO_BE_REPLACED / SessionListener.REPLACED
   * 
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(Session, int)
   */
  @Override
  public void notify(Session updated, int notification) {
    switch (notification) {
    case SessionListener.ABOUT_TO_BE_REPLACED:
      // Deactivate the validateEdit check as the session is unloading / reloading some fragments.
      if (null != getTarget() && getTarget().equals(updated.getTransactionalEditingDomain())) {
        setDisableValidateEdit(true);
      }
      break;
    case SessionListener.REPLACED:
      // Activate the validateEdit check as the session completed unloading / reloading some fragments.
      if (null != getTarget() && getTarget().equals(updated.getTransactionalEditingDomain())) {
        setDisableValidateEdit(false);
      }
      break;
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(Session)
   */
  @Override
  public void notifyAddSession(Session newSession) {
    // unused
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(Session)
   */
  @Override
  public void notifyRemoveSession(Session removedSession) {
    // unused
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(Viewpoint)
   */
  @Override
  public void viewpointSelected(Viewpoint selectedSirius) {
    // unused
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(Viewpoint)
   */
  @Override
  public void viewpointDeselected(Viewpoint deselectedSirius) {
    // unused
  }
}
