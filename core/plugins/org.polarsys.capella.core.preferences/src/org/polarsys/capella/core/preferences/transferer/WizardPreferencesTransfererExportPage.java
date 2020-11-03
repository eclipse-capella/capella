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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferenceFilter;
import org.eclipse.core.runtime.preferences.IPreferencesService;
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
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;

public class WizardPreferencesTransfererExportPage extends org.eclipse.ui.internal.wizards.preferences.WizardPreferencesExportPage1 {

  private static final String CONFIGURABLE_PROJECT_SELECTION_TITLE = "Project Selection"; //$NON-NLS-1$
  
  private static final String CONFIGURABLE_PROJECT_SELECTION_MESSAGE = "Select the project destination form the tree :"; //$NON-NLS-1$

  private static final String CAPELLA_PERSPECTIVE = "capella.sirius.perspective"; //$NON-NLS-1$

  @Override
  protected void createDestinationGroup(Composite parent) {
    String activePerspectiveId = PreferencesHelper.getActivePerpectiveId();
    if (CAPELLA_PERSPECTIVE.equals(activePerspectiveId)) {
      createCustomDestinationGroup(parent);
    } else {
      super.createDestinationGroup(parent);
    }
  }

  private Button destinationBrowseButton;
  
  private Button destinationExplorerBrowseButton;
  
  /**
   * Handle all events and enablements for widgets in this page
   *
   * @param e Event
   */
  @Override
  public void handleEvent(Event e) {
    Widget source = e.widget;

    if (source == destinationBrowseButton) {
      handleProjectSelectionButtonPressed();
      
    } else if (source == destinationExplorerBrowseButton) {
      handleDestinationBrowseButtonPressed();
    }

    updatePageCompletion();
  }

  
  /**
   * Create the export destination specification widgets
   * 
   * @param parent
   *          org.eclipse.swt.widgets.Composite
   */
  protected void createCustomDestinationGroup(Composite parent) {
    // destination specification group
    Composite destinationSelectionGroup = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = 4;
    destinationSelectionGroup.setLayout(layout);
    destinationSelectionGroup
        .setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));

    Label dest = new Label(destinationSelectionGroup, SWT.NONE);
    dest.setText(getDestinationLabel());

    // destination name entry field
    destinationNameField = new Combo(destinationSelectionGroup, SWT.SINGLE | SWT.BORDER);
    destinationNameField.addListener(SWT.Modify, this);
    destinationNameField.addListener(SWT.Selection, this);
    GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
    destinationNameField.setLayoutData(data);

    // destination browse button
    destinationBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
    destinationBrowseButton.setText("Workspace...");
    setButtonLayoutData(destinationBrowseButton);
    destinationBrowseButton.setToolTipText("B&rowse Workspace ...");
    destinationBrowseButton.addListener(SWT.Selection, this);

    // destination browse button
    destinationExplorerBrowseButton = new Button(destinationSelectionGroup, SWT.PUSH);
    destinationExplorerBrowseButton.setText("File System...");
    setButtonLayoutData(destinationExplorerBrowseButton);
    destinationExplorerBrowseButton.addListener(SWT.Selection, this);
    destinationExplorerBrowseButton.setToolTipText("B&rowse File System...");
    
    new Label(parent, SWT.NONE); // vertical spacer
  }

  /*
   * return the PreferenceTransgerElements specified
   */
  @Override
  protected PreferenceTransferElement[] getTransfers() {
    PreferenceTransferElement[] parents = super.getTransfers();
    String activePerspectiveId = PreferencesHelper.getActivePerpectiveId();
    if (CAPELLA_PERSPECTIVE.equals(activePerspectiveId)) {
      PreferenceTransferElement[] transfers = parents != null ? parents : new PreferenceTransferElement[0];
      List<PreferenceTransferElement> capellaTransfers = new ArrayList<PreferenceTransferElement>();
      for (PreferenceTransferElement currentPreferenceTransferElement : transfers) {
        if (currentPreferenceTransferElement.getID().contains("capella")) {
          capellaTransfers.add(currentPreferenceTransferElement);
        }
      }
      return capellaTransfers.toArray(new PreferenceTransferElement[0]);
    }
    return parents;   
  }

  /**
   * Open an appropriate destination browser so that the user can specify a source to import from
   */
  protected void handleProjectSelectionButtonPressed() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    PreferenceProjectSelectionDialog dialog = new PreferenceProjectSelectionDialog(this, window.getShell(),
        new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());

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
    updatePageCompletion();
  }

}
