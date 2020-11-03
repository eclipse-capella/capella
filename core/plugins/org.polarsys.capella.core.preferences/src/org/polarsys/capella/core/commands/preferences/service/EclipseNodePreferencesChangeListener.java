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
package org.polarsys.capella.core.commands.preferences.service;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class EclipseNodePreferencesChangeListener implements IPreferenceChangeListener, IPageChangedListener {

  /**
   * @param event_p
   */
  public EclipseNodePreferencesChangeListener(PageChangedEvent event_p) {
  }

  /**
   * @param event_p
   */
  public EclipseNodePreferencesChangeListener() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void pageChanged(PageChangedEvent event_p) {

  }

  public void enablePreferences() {

    PreferenceManager preferenceManager = PlatformUI.getWorkbench().getPreferenceManager();

    IPreferenceNode[] nodes = preferenceManager.getRootSubNodes();
    for (IPreferenceNode iPreferenceNode : nodes) {
      if (iPreferenceNode.getId().equals("org.polarsys.capella.core.platform.sirius.ui.actions.Capella.page")) {
        IPreferenceNode[] subNodes = iPreferenceNode.getSubNodes();

        for (IPreferenceNode iPreferenceNode2 : subNodes) {

          if (iPreferenceNode2.getId().equals("org.polarsys.capella.core.platform.sirius.ui.actions.deletion.page")) {
            iPreferenceNode2.getPage().setVisible(false);
            iPreferenceNode2.getPage().getControl().setEnabled(false);
          }

        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void preferenceChange(PreferenceChangeEvent event_p) {
    
  }

}
