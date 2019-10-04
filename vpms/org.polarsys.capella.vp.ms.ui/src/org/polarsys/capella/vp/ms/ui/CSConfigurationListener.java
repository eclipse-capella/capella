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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.common.ef.domain.AbstractEditingDomainResourceSetListenerImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.access_Type;
import org.polarsys.capella.vp.ms.ui.preferences.InitializeConfigurationAccessDialog;
import org.polarsys.capella.vp.ms.ui.preferences.MsPreferenceConstants;
import org.polarsys.kitalpha.emde.model.EmdePackage;

public class CSConfigurationListener extends AbstractEditingDomainResourceSetListenerImpl {

  public CSConfigurationListener() {
    super(NotificationFilter.createFeatureFilter(CsPackage.Literals.COMPONENT,
        EmdePackage.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS));
  }

  @Override
  public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
    Command result = null;
    Collection<CSConfiguration> addedConfigurations = new ArrayList<CSConfiguration>();

    for (Notification notif : event.getNotifications()) {
     if (notif.getEventType() == Notification.ADD) {
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
    
    Map<EObject, List<CSConfiguration>> rootToConfigs = 
        addedConfigurations.stream().collect(Collectors.groupingBy(t -> EcoreUtil.getRootContainer(t)));
    
    // this might contain objects that were added and deleted during the same transaction
    rootToConfigs.remove(null);
    
    result = new CompoundCommand();

    for (EObject root : rootToConfigs.keySet()) {

      Session session = SessionManager.INSTANCE.getSession(root);
      URI uri = session.getSessionResource().getURI();
      IProject project = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true)).getProject();
      IPreferenceStore store = new ScopedPreferenceStore(new ProjectScope(project), Activator.PLUGIN_ID);

      String accessLiteral = Platform.getPreferencesService().getString(org.polarsys.capella.vp.ms.ui.preferences.Activator.PLUGIN_ID,
          MsPreferenceConstants.PREF_DEFAULT_CONFIGURATION_ACCESS, "", null); //$NON-NLS-1$

      // String accessLiteral = store.getString(MsUIConstants.PREF_DEFAULT_CONFIGURATION_ACCESS);


      if (accessLiteral.isEmpty()) {
        new InitializeConfigurationAccessDialog(Display.getCurrent().getActiveShell(), store).open();
        accessLiteral = store.getString(MsPreferenceConstants.PREF_DEFAULT_CONFIGURATION_ACCESS);
        if (accessLiteral.isEmpty()) {
          throw new RollbackException(new Status(IStatus.CANCEL, Activator.PLUGIN_ID, "User canceled operation")); //$NON-NLS-1$
        }
      }
      
      access_Type access = access_Type.get(accessLiteral);
      Collection<CSConfiguration> added = rootToConfigs.get(root);

      ((CompoundCommand) result).append(new RecordingCommand(event.getEditingDomain()) {
        @Override
        protected void doExecute() {
          added.forEach(conf -> conf.setAccess(access));
        }
      });
    }
    return result;
  }

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

}
