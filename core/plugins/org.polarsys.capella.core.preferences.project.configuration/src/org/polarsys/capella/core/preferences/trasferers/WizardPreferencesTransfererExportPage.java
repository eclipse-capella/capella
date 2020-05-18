/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.trasferers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.preferences.PreferenceTransferElement;
import org.eclipse.ui.internal.wizards.preferences.PreferencesMessages;
import org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 */
public class WizardPreferencesTransfererExportPage extends WizardPreferencesPage {

  // constants
  private static final String PREFERENCESEXPORTPAGE1 = "preferencesExportPage1"; // //$NON-NLS-1$

  /**
   * Create an instance of this class
   */
  protected WizardPreferencesTransfererExportPage(String name) {
    super(name);
    setTitle(PreferencesMessages.WizardPreferencesExportPage1_exportTitle);
    setDescription(PreferencesMessages.WizardPreferencesExportPage1_exportDescription);
  }

  /**
   * Create an instance of this class
   */
  public WizardPreferencesTransfererExportPage() {
    this(PREFERENCESEXPORTPAGE1);
  }

  protected String getOutputSuffix() {
    return ".epf"; //$NON-NLS-1$
  }

  /**
   * Answer the contents of self's destination specification widget
   * @return java.lang.String
   */
  @Override
  protected String getDestinationValue() {
    String idealSuffix = getOutputSuffix();
    String destinationText = super.getDestinationValue();

    // only append a suffix if the destination doesn't already have a . in
    // its last path segment.
    // Also prevent the user from selecting a directory. Allowing this will
    // create a ".epf" file in the directory
    if ((destinationText.length() != 0) && !destinationText.endsWith(File.separator)) {
      int dotIndex = destinationText.lastIndexOf('.');
      if (dotIndex != -1) {
        // the last path seperator index
        int pathSepIndex = destinationText.lastIndexOf(File.separator);
        if ((pathSepIndex != -1) && (dotIndex < pathSepIndex)) {
          destinationText += idealSuffix;
        }
      } else {
        destinationText += idealSuffix;
      }
    }

    return destinationText;
  }

  @Override
  protected String getAllButtonText() {
    return PreferencesMessages.WizardPreferencesExportPage1_all;
  }

  @Override
  protected String getChooseButtonText() {
    return PreferencesMessages.WizardPreferencesExportPage1_choose;
  }

  /**
   * @param composite
   */
  @Override
  protected void createTransferArea(Composite composite) {
    createTransfersList(composite);
    createDestinationGroup(composite);
    createOptionsGroup(composite);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage# createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);
    PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), IWorkbenchHelpContextIds.EXPORT_WIZARD_SELECTION_WIZARD_PAGE);

  }

  /**
   * Answer the string to display in self as the destination type
   * @return java.lang.String
   */
  @Override
  protected String getDestinationLabel() {
    return PreferencesMessages.WizardPreferencesExportPage1_file;
  }

  /*
   * return the PreferenceTransgerElements specified
   */
  @Override
  protected PreferenceTransferElement[] getTransfers() {
    PreferenceTransferElement[] elements = super.getTransfers();
    PreferenceTransferElement[] returnElements = new PreferenceTransferElement[elements.length];
    IPreferenceFilter[] filters = new IPreferenceFilter[1];
    IPreferenceFilter[] matches;
    IPreferencesService service = Platform.getPreferencesService();
    int count = 0;
    try {
      for (PreferenceTransferElement element : elements) {
        filters[0] = element.getFilter();
        matches = service.matches((IEclipsePreferences) service.getRootNode().node("instance"), filters); //$NON-NLS-1$
        if (matches.length > 0) {
          returnElements[count++] = element;
        }
      }
      elements = new PreferenceTransferElement[count];
      System.arraycopy(returnElements, 0, elements, 0, count);
    } catch (CoreException e) {
      WorkbenchPlugin.log(e.getMessage(), e);
      return new PreferenceTransferElement[0];
    }
    return elements;
  }

  /**
   * @param transfers
   * @return <code>true</code> if the transfer was succesful, and <code>false</code> otherwise
   */
  @Override
  protected boolean transfer(IPreferenceFilter[] transfers) {
    File exportFile = new File(getDestinationValue());
    if (!ensureTargetIsValid(exportFile)) {
      return false;
    }
    FileOutputStream fos = null;
    try {
      if (transfers.length > 0) {
        try {
          fos = new FileOutputStream(exportFile);
        } catch (FileNotFoundException e) {
          WorkbenchPlugin.log(e.getMessage(), e);
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), new String(), e.getLocalizedMessage(), SWT.SHEET);
          return false;
        }
        IPreferencesService service = Platform.getPreferencesService();
        try {
          service.exportPreferences(service.getRootNode(), transfers, fos);
        } catch (CoreException e) {
          WorkbenchPlugin.log(e.getMessage(), e);
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), new String(), e.getLocalizedMessage(), SWT.SHEET);
          return false;
        }
      }
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          WorkbenchPlugin.log(e.getMessage(), e);
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), new String(), e.getLocalizedMessage(), SWT.SHEET);
          return false;
        }
      }
    }
    return true;
  }

  @Override
  protected String getFileDialogTitle() {
    return PreferencesMessages.WizardPreferencesExportPage1_title;
  }

  @Override
  protected int getFileDialogStyle() {
    return SWT.SAVE | SWT.SHEET;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage#getInvalidDestinationMessage()
   */
  @Override
  protected String getInvalidDestinationMessage() {
    return PreferencesMessages.WizardPreferencesExportPage1_noPrefFile;
  }

  /*
   * (non-Javadoc)
   * @seeorg.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage# shouldSaveTransferAll()
   */
  @Override
  protected boolean shouldSaveTransferAll() {
    return true;
  }


  /**
   * Handle all events and enablements for widgets in this page
   * @param e Event
   */
  @Override
  public void handleEvent(Event e) {
    Widget source = e.widget;
    source.getData();
    handleDestinationBrowseButtonPressed();

    updatePageCompletion();
  }

  /**
   * Open an appropriate destination browser so that the user can specify a source to import from
   */
  @Override
  protected void handleDestinationBrowseButtonPressed() {

    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    ListSelectionDialog dlg =
        new ListSelectionDialog(window.getShell(), ResourcesPlugin.getWorkspace().getRoot(), new BaseWorkbenchContentProvider(), new WorkbenchLabelProvider(),
            "Select the Project:");
    dlg.setTitle("Project Selection");
    dlg.open();
  }

}
