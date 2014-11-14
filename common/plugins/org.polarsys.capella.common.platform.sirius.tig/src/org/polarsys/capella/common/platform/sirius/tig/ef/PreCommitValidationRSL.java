/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.tig.ef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

public class PreCommitValidationRSL implements ResourceSetListener {
  private static final String VALUE_ID = "PreCommitValider"; //$NON-NLS-1$
  private static final String ATTRIBUTE_NAME = "ValiderName"; //$NON-NLS-1$
  /**
   * Read checkers from extensions.
   */
  private List<PreCommitValidationChecker> _checkers;

  /**
   * protected constructor : only instantiated by SiriusEditinfDomainProvider
   */
  protected PreCommitValidationRSL() {
    _checkers = new ArrayList<PreCommitValidationChecker>(0);
    readExtensions();
  }

  /**
   * Set given editing domain on validation checkers.
   * @param domain_p
   */
  public void setDomain(EditingDomain domain_p) {
    for (PreCommitValidationChecker checker : _checkers) {
      checker.setDomain(domain_p);
    }
  }

  /**
   * Read extensions that declare a validation checker.
   */
  private void readExtensions() {
    String pluginId = PlatformSiriusTigActivator.getDefault().getPluginId();
    IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(pluginId, VALUE_ID);
    for (IConfigurationElement configurationElement : configurationElements) {
      PreCommitValidationChecker checker = (PreCommitValidationChecker) ExtensionPointHelper.createInstance(configurationElement, ATTRIBUTE_NAME);
      if (null != checker) {
        _checkers.add(checker);
      }
    }
  }

  public NotificationFilter getFilter() {
    return NotificationFilter.NOT_TOUCH;
  }

  public boolean isAggregatePrecommitListener() {
    return false;
  }

  public boolean isPostcommitOnly() {
    return false;
  }

  public boolean isPrecommitOnly() {
    return true;
  }

  public void resourceSetChanged(ResourceSetChangeEvent event_p) {
    // do nothing : we are in a transactional context
  }

  public Command transactionAboutToCommit(ResourceSetChangeEvent event_p) throws RollbackException {
    List<?> notifs = event_p.getNotifications();
    List<Command> lstCommands = new ArrayList<Command>();
    EObject elementToDelete = null;

    for (PreCommitValidationChecker checker : _checkers) {
      checker.init();
    }

    for (Object object : notifs) {
      if (object instanceof Notification) {
        Notification notif = (Notification) object;
        Object notifier = notif.getNotifier();
        // special case for deletion : If we remove an element from a
        // containment relationship, we check if this element
        // still have a container.
        if (notif.getFeature() instanceof EReference) {
          EReference eReference = (EReference) notif.getFeature();
          if (eReference.isContainment() && notif.getNewValue() == null) {
            // this is a deletion. Ask for this element
            Object objectToDelete = notif.getOldValue();
            if (objectToDelete instanceof EObject && objectToDelete != null) {
              EObject temp = (EObject) objectToDelete;
              if (temp.eContainer() == null) {
                elementToDelete = temp;
              }
            }
          }
        }

        for (PreCommitValidationChecker checker : _checkers) {
          Command cmd = checker.triggerObject(notifier, notif);
          if (cmd != null) {
            lstCommands.add(cmd);
          }

          if (!checker.validCommit(notifier, notif)) {
            error(checker);
          }
          if (elementToDelete != null) {
            if (!checker.validDeletion(elementToDelete)) {
              error(checker);
            }
          }
        }

      }
    }
    return lstCommands.isEmpty() ? null : new CompoundCommand(lstCommands);
  }

  private void error(PreCommitValidationChecker checker) throws RollbackException {
    throw new RollbackException(new Status(IStatus.ERROR, checker.getPluginId(), "rollbacked")); //$NON-NLS-1$
  }
}