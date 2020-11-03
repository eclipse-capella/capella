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
package org.polarsys.capella.core.preferences.transferer;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.wizards.preferences.PreferencesExportWizard;

/**
 * We replace the page of the preference export wizard
 */
public class CapellaPreferencesExportWizard extends PreferencesExportWizard {

  private WizardPreferencesTransfererExportPage mainPage;

  private IEventBroker eventBroker;

  public void addPages() {
    // super.addPages();
    mainPage = new WizardPreferencesTransfererExportPage();
    addPage(mainPage);
  }

  public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
    super.init(workbench, currentSelection);
    eventBroker = workbench.getService(IEventBroker.class);
  }

  @Override
  public boolean performFinish() {
    sendEvent(EVENT_EXPORT_BEGIN);
    boolean success = mainPage.finish();
    sendEvent(EVENT_EXPORT_END);
    return success;
  }

  private void sendEvent(String topic) {
    if (eventBroker != null) {
      eventBroker.send(topic, null);
    }
  }

}
