/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui.preferences;

import java.io.IOException;
import java.util.MissingResourceException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.vp.ms.access_Type;
import org.polarsys.capella.vp.ms.provider.MsEditPlugin;

public class InitializeConfigurationAccessDialog extends MessageDialog {

  public static final String[][] labelsAndValues;

  static {
    access_Type[] literals = access_Type.values();
    labelsAndValues = new String[literals.length][2];
    for (int i = 0; i < literals.length; i++) {
      String value = literals[i].getLiteral();
      String label = value;
      try {
        label = MsEditPlugin.INSTANCE.getString("_UI_access_Type_" + value + "_label"); //$NON-NLS-1$ //$NON-NLS-2$
      } catch (MissingResourceException e) {
        Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
      }
      labelsAndValues[i] = new String[] { label, value };
    }
  }

  private RadioGroupFieldEditor editor;
  private IPreferenceStore store;

  public InitializeConfigurationAccessDialog(Shell shell, IPreferenceStore store) {
    super(shell, Messages.InitializeConfigurationAccessDialog_title, null,
        Messages.InitializeConfigurationAccessDialog_message, INFORMATION,
        new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
    this.store = store;
  }

  protected Control createCustomArea(Composite parent) {
    editor = new RadioGroupFieldEditor(MsPreferenceConstants.PREF_DEFAULT_CONFIGURATION_ACCESS,
        Messages.InitializeConfigurationAccessDialog_label, 1, labelsAndValues, parent, false);
    editor.fillIntoGrid(parent, 1);
    editor.setPreferenceStore(store);
    editor.load();
    return parent;
  }

  @Override
  protected void buttonPressed(int id) {
    if (id == 0) {
      editor.store();
      try {
        ((ScopedPreferenceStore) store).save();
      } catch (IOException e) {
        Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
      }
    }
    super.buttonPressed(id);
  }

}
