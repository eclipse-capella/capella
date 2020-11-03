/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.MessageFormat;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.AbortedTransactionException;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.kitalpha.emde.model.Element;

/**
 * codes quite similar to FileModificationPreCommitListener
 * <p>
 * The goals of this listener is:
 * <ul>
 * <li>to know which files are to be modified</li>
 * <li>to check if these files are the same than expected ones</li>
 * <li>to make this files writable to avoid the confirmation windows of FileModificationPreCommitListener</li>
 * </ul>
 */
public class FragmentModificationListener implements ResourceSetListener {

  private final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  private Set<IFile> _filesToMakeWritable = new HashSet<IFile>(0);
  private Set<IFile> _expectedFilesToMakeWritable = new HashSet<IFile>(0);

  public FragmentModificationListener(Set<IFile> expectedSet) {
    _expectedFilesToMakeWritable = expectedSet;
  }

  @Override
  public NotificationFilter getFilter() {
    return null;
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
    List<?> notifications = event_p.getNotifications();
    Set<IFile> filesToMakeWritable = new HashSet<IFile>(0);
    Map<ResourceSetSync, Set<Resource>> additionalResourcesToChangeStatus = new HashMap<ResourceSetSync, Set<Resource>>(
        0);
    Map<Element, Resource> removedElementFromResource = new HashMap<Element, Resource>(0);
    // Collect all files that require to make them writable to be able to run the current transaction.
    for (Object currentNotification : notifications) {
      // Get the current notification.
      if (currentNotification instanceof Notification) {
        // Get the notifier.
        Notification notification = (Notification) currentNotification;
        Object notifier = notification.getNotifier();
        if (notifier instanceof EObject) {
          // Mark dirty dependent resources if necessary.
          if (notifier instanceof Element) {
            markDirtyDependentResources(filesToMakeWritable, additionalResourcesToChangeStatus, notification,
                (Element) notifier, removedElementFromResource);
          }
          Resource resource = ((EObject) notifier).eResource();
          if ((null != resource)
              && !new NotificationQuery((Notification) currentNotification).isTransientNotification()) {
            // Check the notifier resource is writable.
            IFile file = EcoreUtil2.getFile(resource);
            handleMakeFileWritable(filesToMakeWritable, currentNotification, file);
          }
        }
      }
    }

    _filesToMakeWritable = filesToMakeWritable;

    // Make files writable (if any).
    if (!filesToMakeWritable.isEmpty()) {
      // check if the files to make writable are the same than the expected ones
      checkFilesWritables();
      makeFilesWritable(event_p.getEditingDomain(), filesToMakeWritable);
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

  /**
   * Mark dirty dependent resources if the specified notification contains object that are moved from a resource to a
   * new one.
   * 
   * @param filesToMakeWritable_p
   * @param additionalResourcesToChangeStatus_p
   * @param notification_p
   * @param notifier_p
   * @param removedElementFromResource_p
   */
  @SuppressWarnings("unchecked")
  protected void markDirtyDependentResources(final Set<IFile> filesToMakeWritable_p,
      final Map<ResourceSetSync, Set<Resource>> additionalResourcesToChangeStatus_p, Notification notification_p,
      Element notifier_p, Map<Element, Resource> removedElementFromResource_p) {
    // Collect additional resources to check out for current EObject.
    EStructuralFeature feature = (EStructuralFeature) notification_p.getFeature();
    if (feature instanceof EReference) {
      // Search for a containment change where the eResource is modified.
      EReference reference = (EReference) feature;
      if (reference.isContainment() && !notification_p.isTouch()) {
        // New value objects collection.
        List<EObject> objectsToUpdate = new ArrayList<EObject>(1);
        switch (notification_p.getEventType()) {
        case Notification.REMOVE:
        case Notification.UNSET:
          Object oldValue = notification_p.getOldValue();
          if (oldValue instanceof Element) {
            Element removedElement = (Element) oldValue;
            // Stored the removed object and its current resource to compare with another one in next future.
            removedElementFromResource_p.put(removedElement, notifier_p.eResource());
          }
          return; // Force to exit this method.
        case Notification.REMOVE_MANY:
          break;
        case Notification.SET:
        case Notification.ADD:
          objectsToUpdate.add((EObject) notification_p.getNewValue());
          break;
        case Notification.ADD_MANY:
          objectsToUpdate.addAll((Collection<? extends EObject>) notification_p.getNewValue());
          break;
        }
        Resource notifierResource = notifier_p.eResource();
        // Loop over all objects that need an update.
        for (EObject objectToUpdate : objectsToUpdate) {
          // Check if the resource of the object to update is the same as is its new container ?
          Resource objectToUpdateResource = removedElementFromResource_p.get(objectToUpdate);
          if ((null != objectToUpdateResource) && !notifierResource.equals(objectToUpdateResource)) {
            Collection<Resource> dependentResources = RepresentationHelper.collectDependentResources(objectToUpdate);
            for (Resource dependentResource : dependentResources) {
              IFile file = EcoreUtil2.getFile(dependentResource);
              handleMakeFileWritable(filesToMakeWritable_p, objectToUpdate, file);
              // Get the resourceSetSync for current editing domain.
              ResourceSetSync resourceSetSync = ResourceSetSync.getOrInstallResourceSetSync(
                  (TransactionalEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(objectToUpdate));
              // Is it the container resource sync ?
              if (ResourceStatus.SYNC.equals(ResourceSetSync.getStatus(dependentResource))) {
                Set<Resource> resources = additionalResourcesToChangeStatus_p.get(resourceSetSync);
                if (null == resources) {
                  resources = new HashSet<Resource>(0);
                  additionalResourcesToChangeStatus_p.put(resourceSetSync, resources);
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
   * Handle make file writable.
   * 
   * @param filesToMakeWritable
   * @param notificationObject
   * @param file
   */
  public void handleMakeFileWritable(final Set<IFile> filesToMakeWritable, Object notificationObject, IFile file) {
    if ((null != file) && file.isReadOnly()) {
      if (__logger.isDebugEnabled()) {
        // Avoid multi traces for the same file.
        if (!filesToMakeWritable.contains(file)) {
          // Not I18n since it is debug traces.
          __logger.debug(new EmbeddedMessage(
              StringHelper.formatMessage("Make File ''{0}'' Writable due to notification:{1}", //$NON-NLS-1$
                  new String[] { file.getFullPath().toString(), notificationObject.toString() }),
              IReportManagerDefaultComponents.UI));
        }
      }
      // Add this RO resource to make it writable.
      filesToMakeWritable.add(file);
    }
  }

  /**
   * Make files writable. If end-user turns down (or operation fails), an exception is thrown.
   * 
   * @param event_p
   * @param filesToMakeWritable_p
   * @throws AbortedTransactionException
   */
  public void makeFilesWritable(TransactionalEditingDomain editingDomain_p,
      final Collection<IFile> filesToMakeWritable_p) throws AbortedTransactionException {
    try {
      FragmentUtils.setIFileListWrite(filesToMakeWritable_p);
    } catch (CoreException e) {
      fail(e.getMessage());
    }

  }

  /**
   * Assertion to check if the actual set of files to make writable is the same than the expected one
   */
  @SuppressWarnings("nls")
  public void checkFilesWritables() {
    // check if the files to make writable are in read only or not
    // if not, do not do the check
    boolean areReadOnly = false;
    for (IFile file : _filesToMakeWritable) {
      if (file.isReadOnly()) {
        areReadOnly = true;
      } else {
        areReadOnly = false;
        break;
      }
    }

    if (areReadOnly) {
      // compare if the actual and the expected sets are similar
      Set<IFile> expectedFilesToBeModified = _expectedFilesToMakeWritable;
      boolean haveSameFiles = _filesToMakeWritable.equals(expectedFilesToBeModified);

      Set<String> fileNameToMakeWritable = new HashSet<String>();
      for (IFile file : _filesToMakeWritable) {
        fileNameToMakeWritable.add(file.getName());
      }

      assertTrue(MessageFormat.format(
          "The actual and expected sets of files to make writable are different. The actual set is the following {0}. Check the expected set.",
          fileNameToMakeWritable), haveSameFiles);
    }

  }

  /**
   * compare if a collection of file to make writable is the same than the expected set of files to make writable
   * 
   * @param actual_p
   * @param expected_p
   */
  @SuppressWarnings("nls")
  public static void checkFilesWritables(Collection<IFile> actual_p, Set<IFile> expected_p) {
    // check if the files to make writable are in read only or not
    // if not, do not do the check
    boolean areReadOnly = false;
    for (IFile file : actual_p) {
      if (file.isReadOnly()) {
        areReadOnly = true;
      } else {
        areReadOnly = false;
        break;
      }
    }

    if (areReadOnly) {
      // compare if the actual and the expected sets are similar
      boolean haveSameFiles = true;
      for (IFile currentFile : expected_p) {
        if (!actual_p.contains(currentFile)) {
          haveSameFiles = false;
          break;
        }
      }

      Set<String> fileNameToMakeWritable = new HashSet<String>();
      for (IFile file : actual_p) {
        fileNameToMakeWritable.add(file.getName());
      }

      assertTrue(MessageFormat.format(
          "The actual and expected sets of files to make writable are different. The actual set is the following {0}. Check the expected set.",
          fileNameToMakeWritable), haveSameFiles && (actual_p.size() == expected_p.size()));
    }

  }

  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event_p) { /* do nothing */
  }

  @Override
  public boolean isAggregatePrecommitListener() {
    return false;
  }

  /**
   * the listener is defined as PrecommitOnly, not as AggregatePrecommitListener
   * <p>
   * then this listener appears before FileModificationPreCommitListener
   */
  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  @Override
  public boolean isPostcommitOnly() {
    return false;
  }
}
