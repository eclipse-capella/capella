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
package org.polarsys.capella.core.preferences.transferer;

import java.util.ArrayList;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.activities.ITriggerPoint;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.activities.ws.WorkbenchTriggerPoints;
import org.eclipse.ui.internal.dialogs.DialogUtil;
import org.eclipse.ui.internal.dialogs.ImportExportPage;
import org.eclipse.ui.internal.dialogs.WizardActivityFilter;
import org.eclipse.ui.internal.dialogs.WizardContentProvider;
import org.eclipse.ui.internal.dialogs.WizardPatternFilter;
import org.eclipse.ui.internal.dialogs.WorkbenchWizardNode;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.wizards.IWizardCategory;

import org.polarsys.capella.core.commands.preferences.properties.ExportPreferencesHandler;

/**
 */
public class PreferencesImportExportPage extends ImportExportPage {

  private static final String STORE_SELECTED_EXPORT_WIZARD_ID = DIALOG_SETTING_SECTION_NAME + "STORE_SELECTED_EXPORT_WIZARD_ID"; //$NON-NLS-1$

  private static final String STORE_EXPANDED_EXPORT_CATEGORIES = DIALOG_SETTING_SECTION_NAME + "STORE_EXPANDED_EXPORT_CATEGORIES"; //$NON-NLS-1$

  CategorizedWizardSelectionTree exportTree;

  private WizardPreferencesTransfererExportPage wizardPreferencesTransfererExportPage;

  /**
   * Constructor for export wizard selection page.
   * @param aWorkbench
   * @param currentSelection
   */
  public PreferencesImportExportPage(IWorkbench aWorkbench, IStructuredSelection currentSelection) {
    super(aWorkbench, currentSelection);
  }

  @Override
  protected void initialize() {
    workbench.getHelpSystem().setHelp(getControl(), IWorkbenchHelpContextIds.EXPORT_WIZARD_SELECTION_WIZARD_PAGE);
  }

  @Override
  protected ITriggerPoint getTriggerPoint() {
    return getWorkbench().getActivitySupport().getTriggerPointManager().getTriggerPoint(WorkbenchTriggerPoints.EXPORT_WIZARDS);
  }

  @Override
  protected void updateMessage() {
    setMessage(WorkbenchMessages.ImportExportPage_chooseExportWizard);
    super.updateMessage();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Composite createTreeViewer(Composite parent) {
    IWizardCategory root = WorkbenchPlugin.getDefault().getExportWizardRegistry().getRootCategory();
    exportTree = new CategorizedWizardSelectionTree(root, WorkbenchMessages.ExportWizard_selectWizard);
    Composite exportComp = exportTree.createControl(parent);
    exportTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event) {
        listSelectionChanged(event.getSelection());
      }
    });
    exportTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        treeDoubleClicked(event);
      }
    });
    setTreeViewer(exportTree.getViewer());
    return exportComp;
  }

  /*
   * Class to create a control that shows a categorized tree of wizard types.
   */
  protected class CategorizedWizardSelectionTree {
    private final static int SIZING_LISTS_HEIGHT = 200;

    private IWizardCategory wizardCategories;
    private String message;
    private TreeViewer viewer;

    /**
     * Constructor for CategorizedWizardSelectionTree
     * @param categories root wizard category for the wizard type
     * @param msg message describing what the user should choose from the tree.
     */
    protected CategorizedWizardSelectionTree(IWizardCategory categories, String msg) {
      this.wizardCategories = categories;
      this.message = msg;
    }

    /**
     * Create the tree viewer and a message describing what the user should choose from the tree.
     * @param parent Composite on which the tree viewer is to be created
     * @return Comoposite with all widgets
     */
    protected Composite createControl(Composite parent) {
      Font font = parent.getFont();
      // create composite for page.
      Composite outerContainer = new Composite(parent, SWT.NONE);
      outerContainer.setLayout(new GridLayout());
      outerContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
      outerContainer.setFont(font);

      Label messageLabel = new Label(outerContainer, SWT.NONE);
      if (message != null) {
        messageLabel.setText(message);
      }
      messageLabel.setFont(font);
      createFilteredTree(outerContainer);
      layoutTopControl(viewer.getControl());

      return outerContainer;
    }

    /**
     * Create the categorized tree viewer.
     * @param parent
     */
    private void createFilteredTree(Composite parent) {
      // Create a FilteredTree for the categories and wizards
      FilteredTree filteredTree = new FilteredTree(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, new WizardPatternFilter(), true);
      viewer = filteredTree.getViewer();
      filteredTree.setFont(parent.getFont());

      viewer.setContentProvider(new WizardContentProvider());
      viewer.setLabelProvider(new WorkbenchLabelProvider());

      ArrayList inputArray = new ArrayList();
      boolean expandTop = false;

      if (wizardCategories != null) {
        if (wizardCategories.getParent() == null) {
          IWizardCategory[] children = wizardCategories.getCategories();
          for (IWizardCategory element : children) {
            inputArray.add(element);
          }
        } else {
          expandTop = true;
          inputArray.add(wizardCategories);
        }
      }

      // ensure the category is expanded. If there is a remembered expansion it will be set later.
      if (expandTop) {
        viewer.setAutoExpandLevel(2);
      }

      AdaptableList input = new AdaptableList(inputArray);

      // filter wizard list according to capabilities that are enabled
      viewer.addFilter(new WizardActivityFilter());

      viewer.setInput(input);
    }

    /**
     * @return the categorized tree viewer
     */
    protected TreeViewer getViewer() {
      return viewer;
    }

    /**
     * Layout for the given control.
     * @param control
     */
    private void layoutTopControl(Control control) {
      GridData data = new GridData(GridData.FILL_BOTH);

      int availableRows = DialogUtil.availableRows(control.getParent());

      // Only give a height hint if the dialog is going to be too small
      if (availableRows > 50) {
        data.heightHint = SIZING_LISTS_HEIGHT;
      } else {
        data.heightHint = availableRows * 3;
      }

      control.setLayoutData(data);
    }
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
   */
  @Override
  public IWizardPage getNextPage() {
    WorkbenchWizardNode selectedNode = (WorkbenchWizardNode) getSelectedNode();
    if (((selectedNode == null) || (selectedNode.getWizardElement() == null))
        || !(selectedNode.getWizardElement().getId().equals("org.eclipse.ui.wizards.export.Preferences"))) {
      return super.getNextPage();
    }
    ITriggerPoint triggerPoint = getTriggerPoint();

    if ((triggerPoint == null) || WorkbenchActivityHelper.allowUseOf(triggerPoint, getSelectedNode())) {
      wizardPreferencesTransfererExportPage = new WizardPreferencesTransfererExportPage("Export");
      wizardPreferencesTransfererExportPage.setWizard(ExportPreferencesHandler.getWizard());
      return wizardPreferencesTransfererExportPage;
    }
    return null;
  }

  @Override
  public void saveWidgetValues() {
    storeExpandedCategories(STORE_EXPANDED_EXPORT_CATEGORIES, exportTree.getViewer());
    storeSelectedCategoryAndWizard(STORE_SELECTED_EXPORT_WIZARD_ID, exportTree.getViewer());
    super.saveWidgetValues();
    if (wizardPreferencesTransfererExportPage != null) {
      wizardPreferencesTransfererExportPage.performFinish();
    }

  }

}
