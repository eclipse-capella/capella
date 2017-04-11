/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.Result;
import org.polarsys.capella.vp.ms.access_Type;
import org.polarsys.kitalpha.emde.model.EmdePackage;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class CSConfigurationListener extends ResourceSetListenerImpl implements IEditingDomainListener {

  public CSConfigurationListener() {
    super(NotificationFilter.createFeatureFilter(CsPackage.Literals.COMPONENT,
        EmdePackage.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS));
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    Command result = null;
    Collection<CSConfiguration> removedConfigurations = new ArrayList<CSConfiguration>();
    Collection<CSConfiguration> addedConfigurations = new ArrayList<CSConfiguration>();

    for (Notification notif : event.getNotifications()) {
      if (notif.getEventType() == Notification.REMOVE) {
        if (notif.getOldValue() instanceof Comparison) {
          Comparison iObj = (Comparison) notif.getOldValue();
          iObj.destroy();
        } else if (notif.getOldValue() instanceof Result) {
          Result iObj = (Result) notif.getOldValue();
          iObj.destroy();
        } else {
          removedConfigurations.add((CSConfiguration) notif.getOldValue());
        }
      } else if (notif.getEventType() == Notification.REMOVE_MANY) {
        for (Object o : ((Collection<?>) notif.getOldValue())) {
          if (o instanceof CSConfiguration) {
            removedConfigurations.add((CSConfiguration) o);
          }
        }
      } else if (notif.getEventType() == Notification.ADD) {
        if (notif.getNewValue() instanceof CSConfiguration) {
          addedConfigurations.add((CSConfiguration) notif.getNewValue());
        }
      } else if (notif.getEventType() == Notification.ADD_MANY) {
        for (Object o : (Collection<?>) notif.getNewValue()) {
          if (o instanceof CSConfiguration) {
            addedConfigurations.add((CSConfiguration) o);
          }
        }
      }
    }

    addedConfigurations.removeAll(removedConfigurations);

    if (addedConfigurations.size() > 0) {
      Multimap<EObject, CSConfiguration> rootToConfigs = ArrayListMultimap.create();
      for (CSConfiguration config : addedConfigurations) {
        rootToConfigs.put(EcoreUtil.getRootContainer(config), config);
      }

      result = new CompoundCommand();

      for (EObject root : rootToConfigs.keySet()) {

        Session session = SessionManager.INSTANCE.getSession(root);
        URI uri = session.getSessionResource().getURI();
        IProject project = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true)).getProject();
        IPreferenceStore store = new ScopedPreferenceStore(new ProjectScope(project), Activator.PLUGIN_ID);

        String accessLiteral = Platform.getPreferencesService().getString(Activator.PLUGIN_ID,
            MsUIConstants.PREF_DEFAULT_CONFIGURATION_ACCESS, "", null); //$NON-NLS-1$

        // String accessLiteral = store.getString(MsUIConstants.PREF_DEFAULT_CONFIGURATION_ACCESS);

        final Collection<CSConfiguration> added = rootToConfigs.get(root);

        if (accessLiteral.isEmpty()) {
          new InitializeConfigurationAccessDialog(Display.getCurrent().getActiveShell(), store).open();
          accessLiteral = store.getString(MsUIConstants.PREF_DEFAULT_CONFIGURATION_ACCESS);
          if (accessLiteral.isEmpty()) {
            throw new RollbackException(new Status(IStatus.CANCEL, Activator.PLUGIN_ID, "User canceled operation")); //$NON-NLS-1$
          }
        }

        final access_Type access = access_Type.get(accessLiteral);

        ((CompoundCommand) result).append(new RecordingCommand(event.getEditingDomain()) {
          @Override
          protected void doExecute() {
            for (CSConfiguration cs : added) {
              cs.setAccess(access);
            }
          }
        });
      }
    }
    return result;
  }

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).addResourceSetListener(this);

  }

  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain) editingDomain).removeResourceSetListener(this);
  }

}
