/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.preferences.PreferenceTransferElement;
import org.eclipse.ui.internal.wizards.preferences.PreferencesMessages;
import org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.osgi.service.prefs.BackingStoreException;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class WizardPreferencesTransfererExportPage extends WizardPreferencesPage {

  // constants
  private static final String PREFERENCESEXPORTPAGE1 = "preferencesExportPage1"; // //$NON-NLS-1$

  private static final String CONFIGURABLE_PROJECT_SELECTION_TITLE = "Project Selection";// //$NON-NLS-1$
  private static final String CONFIGURABLE_PROJECT_SELECTION_MESSAGE = "Select the project destination form the tree :";// //$NON-NLS-1$

  /*
   * 
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

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
    String activePerspectiveId = PreferencesHelper.getActivePerpectiveId();
    createTransfersList(composite);
    if ("capella.sirius.perspective".equals(activePerspectiveId)) {

      createCustomDestinationGroup(composite);

    } else {
      super.createDestinationGroup(composite);
    }
    createOptionsGroup(composite);

  }

  @Override
  protected void createDestinationGroup(Composite parent) {

  };

  /**
   * Create the export destination specification widgets
   * @param parent org.eclipse.swt.widgets.Composite
   */
  protected void createCustomDestinationGroup(Composite parent) {
    // destination specification group
    Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 4;
    destinationSelectionGroup.setLayout(layout);
    destinationSelectionGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));

    Label dest = new Label(destinationSelectionGroup, SWT.NONE);
    dest.setText(getDestinationLabel());

    // destination name entry field
    destinationNameField = new Combo(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);
    destinationNameField.addListener(SWT.Modify, this);
    destinationNameField.addListener(SWT.Selection, this);
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
    destinationNameField.setLayoutData(data);

    // destination browse button
    Button destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
    destinationBrowseButton.setText("Workspace...");
    setButtonLayoutData(destinationBrowseButton);
    destinationBrowseButton.setToolTipText("B&rowse Workspace ...");
    // destination browse button
    Button destinationExplorerBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
    destinationExplorerBrowseButton.setText("File System...");
    setButtonLayoutData(destinationExplorerBrowseButton);
    destinationExplorerBrowseButton.addListener(SWT.Selection, this);
    destinationExplorerBrowseButton.setToolTipText("B&rowse File System...");
    destinationBrowseButton.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e_p) {
        handleProjectSelectionButtonPressed();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e_p) {

      }
    });

    new Label(parent, SWT.NONE); // vertical spacer
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
    String activePerspectiveId = PreferencesHelper.getActivePerpectiveId();

    if ("capella.sirius.perspective".equals(activePerspectiveId)) {

      PreferenceTransferElement[] elements = getCapellaTransfers();
      PreferenceTransferElement[] returnElements = new PreferenceTransferElement[elements.length + 20];
      IPreferenceFilter[] filters = new IPreferenceFilter[1];
      IPreferenceFilter[] matches;
      IPreferencesService service = Platform.getPreferencesService();
      int count = 0;
      try {
        for (PreferenceTransferElement element : elements) {
          filters[0] = element.getFilter();
          matches = service.matches((IEclipsePreferences) service.getRootNode().node("default"), filters); //$NON-NLS-1$ 

          if (matches.length > 0) {
            returnElements[count] = element;
            count++;
          }
        }

        for (PreferenceTransferElement element : elements) {
          filters[0] = element.getFilter();
          matches = service.matches((IEclipsePreferences) service.getRootNode().node("instance"), filters); //$NON-NLS-1$ 

          if (matches.length > 0) {
            returnElements[count] = element;
            count++;
          }
        }
        elements = new PreferenceTransferElement[count];
        System.arraycopy(returnElements, 0, elements, 0, count);
      } catch (CoreException e) {
        WorkbenchPlugin.log(e.getMessage(), e);
        return new PreferenceTransferElement[0];
      }
      return elements;
    } else {
      return super.getTransfers();
    }
  }

  /**
   * 
   */
  private PreferenceTransferElement[] getCapellaTransfers() {

    PreferenceTransferElement[] transfers = super.getTransfers() != null ? super.getTransfers() : new PreferenceTransferElement[] {};
    List<PreferenceTransferElement> capellaTransfers = new ArrayList<PreferenceTransferElement>();
    for (PreferenceTransferElement currentPreferenceTransferElement : transfers) {
      if (currentPreferenceTransferElement.getID().contains("capella")) {
        capellaTransfers.add(currentPreferenceTransferElement);
      }

    }

    transfers = new PreferenceTransferElement[capellaTransfers.size()];
    for (int i = 0; i < capellaTransfers.size(); i++) {
      PreferenceTransferElement preferenceTransferElement = capellaTransfers.get(i);
      transfers[i] = preferenceTransferElement;

    }

    return transfers;
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
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), "", e.getLocalizedMessage(), SWT.SHEET);
          return false;
        }
        IPreferencesService service = Platform.getPreferencesService();
        try {
          service.exportPreferences(service.getRootNode(), transfers, fos);
        } catch (CoreException e) {
          WorkbenchPlugin.log(e.getMessage(), e);
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), "", e.getLocalizedMessage(), SWT.SHEET);
          return false;
        }
      }
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          WorkbenchPlugin.log(e.getMessage(), e);
          MessageDialog.open(MessageDialog.ERROR, getControl().getShell(), "", e.getLocalizedMessage(), SWT.SHEET);
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
    if ((source instanceof Button)) {
      handleDestinationBrowseButtonPressed();
    }
  }

  /**
   * Open an appropriate destination browser so that the user can specify a source to import from
   */
  @Override
  protected void handleDestinationBrowseButtonPressed() {
    FileDialog dialog = new FileDialog(getContainer().getShell(), getFileDialogStyle());
    dialog.setText(getFileDialogTitle());
    dialog.setFilterPath(getDestinationValue());
    dialog.setFilterExtensions(new String[] { "*.epf", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
    String selectedFileName = dialog.open();

    if (selectedFileName != null) {
      setDestinationValue(selectedFileName);

      if ((this.getWizard() != null) && (this.getWizard() instanceof CapellaImportExportPreferences)) {
        CapellaImportExportPreferences wizard = (CapellaImportExportPreferences) this.getWizard();
        wizard.setPageCompleted(!selectedFileName.isEmpty());
        updatePageCompletion();
      }

    }
  }

  public boolean performFinish() {
    IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
    for (IProject project : projects) {
      if (PreferencesHelper.hasConfigurationProject(project)) {
        try {
          IProject configProject = PreferencesHelper.getReferencedProjectConfiguration(project);
          new ProjectScope(configProject).getNode(Activator.PLUGIN_ID).flush();
          configProject.refreshLocal(IResource.DEPTH_INFINITE, null);
          project.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (BackingStoreException exception) {
          StringBuilder loggerMessage = new StringBuilder("WizardPreferencesTransfererExportPage.performFinish(..) _ "); //$NON-NLS-1$
          __logger.warn(loggerMessage.toString(), exception);
          return super.finish();
        } catch (CoreException exception) {
          StringBuilder loggerMessage = new StringBuilder("WizardPreferencesTransfererExportPage.performFinish(..) _ "); //$NON-NLS-1$
          __logger.warn(loggerMessage.toString(), exception);
        }
      }
    }

    return super.finish();

  }

  /**
   * Open an appropriate destination browser so that the user can specify a source to import from
   */
  // @Override
  protected void handleProjectSelectionButtonPressed() {

    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    PreferenceProjectSelectionDialog dialog =
        new PreferenceProjectSelectionDialog(this, window.getShell(), new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());

    dialog.setTitle(CONFIGURABLE_PROJECT_SELECTION_TITLE);

    dialog.setMessage(CONFIGURABLE_PROJECT_SELECTION_MESSAGE);

    dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());

    dialog.addFilter(new PreferencesProjectFilter());

    dialog.open();

  }

  @Override
  protected void addDestinationItem(String value) {
    super.addDestinationItem(value);
    setDestinationValue(value);

    CapellaImportExportPreferences wizard = (CapellaImportExportPreferences) this.getWizard();
    wizard.setPageCompleted((value != null) && !value.isEmpty());

    updatePageCompletion();
  }

}
