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
package org.polarsys.capella.core.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * Confirm Capella elements deletion Tool dialog
 */
public class ConfirmDeleteCapellaElementDialog extends ImpactAnalysisDialog {

  public static final String DELETE_ANALYSIS_DIALOG_ELEMENTS = "org.polarsys.capella.core.ui.toolkit.dialogs.confirmDelete.elements";

  public static final String DELETE_ANALYSIS_DIALOG_REFERENCING_ELEMENTS = "org.polarsys.capella.core.ui.toolkit.dialogs.confirmDelete.referencingElements";

  /*
   * Referencing elements viewer to deleted elements.
   */
  private TreeViewer referencingElementsViewer;

  /*
   * elements viewer to be deleted viewer.
   */
  private TreeViewer elementsToDeleteViewer;

  /*
   * expended elements
   */
  private Object[] expendedElements;

  /*
  	 * 
  	 */
  private boolean isMultipleSelection;

  /*
  	 * 
  	 */
  private Button resourceCheckReferencingElemntButton;

  /**
   * @param elementsToDelete
   * @param label
   * @param confirmDeletionQuestion
   * @param question
   * @param choice
   * @param color
   * @param isMultipleSelection
   */
  public ConfirmDeleteCapellaElementDialog(List<?> elementsToDelete, boolean isMultipleSelection,
      Object[] expendedElements) {
    super(elementsToDelete, Messages.CapellaDeleteCommand_Label, Messages.CapellaDeleteCommand_ConfirmDeletionQuestion,
        MessageDialog.QUESTION, new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, SWT.COLOR_RED,
        isMultipleSelection);
    this.expendedElements = expendedElements;
    this.isMultipleSelection = isMultipleSelection;
    this.setBlockOnOpen(true);
  }

  protected Control createCustomArea(Composite parentComposite) {
    // Since we add a second viewer to display referencing elements for deleted ones.
    // Let's tweak the UI by the changing the layout data.
    GridLayout layout = (GridLayout) parentComposite.getLayout();
    layout.numColumns = 2;
    layout.makeColumnsEqualWidth = true;
    layout.marginWidth = 0; // To have the status bar nicely displayed.

    createElementsToDeleteViewer(parentComposite);
    createReferencingElementViewer(parentComposite);

    return parentComposite;
  }

  /**
   * Create a group to host the deleted element viewer
   */
  protected void createElementsToDeleteViewer(Composite parentComposite) {
    Group deletedElementsGroup = new Group(parentComposite, SWT.NONE);
    deletedElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Title);
    deletedElementsGroup.setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Tooltip);
    deletedElementsGroup.setLayout(new GridLayout());
    deletedElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the viewer area with this group as parent.
    elementsToDeleteViewer = this.createViewer(deletedElementsGroup, DELETE_ANALYSIS_DIALOG_ELEMENTS);
    elementsToDeleteViewer.setInput(getInitialInputData());
    // Add a button to display EMF resource as root nodes.
    createResourceCheckButton(deletedElementsGroup, elementsToDeleteViewer);

    // Set a label provider that allow decorator mechanism.
    elementsToDeleteViewer.setLabelProvider(createLabelProvider(
        new DecoratingLabelProvider(new ImpactAnalysisLabelProvider(elementsToDeleteViewer, SWT.COLOR_RED),
            PlatformUI.getWorkbench().getDecoratorManager())));
    elementsToDeleteViewer.setSelection(new StructuredSelection(expendedElements), true);
    elementsToDeleteViewer.getControl().setFocus();
  }

  /**
   * Create a second viewer to display referencing elements related to a deleted element.
   * 
   * @param parentComposite
   */
  protected void createReferencingElementViewer(Composite parentComposite) {
    // Create a group to host the referencing elements.
    Group referencingElementsGroup = new Group(parentComposite, SWT.NONE);
    referencingElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Title);
    referencingElementsGroup
        .setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Tooltip);
    referencingElementsGroup.setLayout(new GridLayout());
    referencingElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the referencing element with this group as parent.
    referencingElementsViewer = this.createViewer(referencingElementsGroup,
        DELETE_ANALYSIS_DIALOG_REFERENCING_ELEMENTS);
    // Set a label provider that allow decorator mechanism.
    referencingElementsViewer.setLabelProvider(createLabelProvider(new DecoratingLabelProvider(
        new ImpactAnalysisLabelProvider(referencingElementsViewer, DEFAULT_COLOR_FOR_RELEVANT_ELEMENTS),
        PlatformUI.getWorkbench().getDecoratorManager())));
    // Add a button to display EMF resource as root nodes.
    resourceCheckReferencingElemntButton = createResourceCheckButton(referencingElementsGroup,
        referencingElementsViewer);
    registerElementsToDeleteListener();
  }

  /**
   * Returns the label provider that will be used in viewers
   */
  protected ILabelProvider createLabelProvider(ILabelProvider provider) {
    return provider;
  }

  private void registerElementsToDeleteListener() {
    elementsToDeleteViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      @Override
      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event_p) {
        IStructuredSelection ssel = (IStructuredSelection) event_p.getSelection();
        List<?> selectedElements = ssel.toList();
        Set<EObject> referencingElements = new HashSet<EObject>(0);
        for (Object currentSelectedElement : selectedElements) {
          if (((TreeData) elementsToDeleteViewer.getInput()).isValid(currentSelectedElement)) {
            // Be careful, selected element could be an EMF Resource (if displayed).
            if (currentSelectedElement instanceof EObject) {
              referencingElements.addAll(getReferencingElements(currentSelectedElement));
            }
          }
        }
        // Compute the referencing elements.
        referencingElementsViewer.setInput(getTreeViewerItems(resourceCheckReferencingElemntButton.getSelection(),
            new ArrayList<Object>(referencingElements)));
      }
    });
  }

  /**
   * Returns the list of all referencing elements for the given element
   */
  protected Collection<? extends EObject> getReferencingElements(Object currentSelectedElement) {
    List<EObject> objects = CrossReferencerHelper.getReferencingElements((EObject) currentSelectedElement);
    objects.addAll(RepresentationHelper
        .getAllRepresentationDescriptorsAnnotatedBy(Collections.singletonList((EObject) currentSelectedElement)));
    return objects;
  }

  protected TreeViewer createViewer(Composite parentComposite, String location) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parentComposite, this.isMultipleSelection,
        IViewerStyle.SHOW_STATUS_BAR) {

      @Override
      public String getContextMenuLocation() {
        return location;
      }

    };

    TreeViewer viewer = treeViewer.getClientViewer();
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ImpactAnalysisLabelProvider(viewer, SWT.COLOR_RED));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new ImpactAnalysisSorter());
    constrainViewer(viewer, getViewerHeighthint());
    return viewer;
  }

  /**
   * Create a check button to display (or not) the resource.
   * 
   * @param parentComposite
   * @return
   */
  protected Button createResourceCheckButton(Composite parentComposite, final TreeViewer treeViewer) {
    // Add a check button to enable the viewer to represent elements by their containing resource.
    Button resourceCheckButton = new Button(parentComposite, SWT.CHECK);
    resourceCheckButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    resourceCheckButton.setText(Messages.ImpactAnalysisDialog_ResourceButton_Title);
    resourceCheckButton.setSelection(false);
    resourceCheckButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        handleResourceCheckButtonClicked(((Button) event_p.widget).getSelection(), treeViewer);
      }
    });
    return resourceCheckButton;
  }

  /**
   * Handle resource check button click.
   * 
   * @param selection_p
   */
  protected void handleResourceCheckButtonClicked(boolean isChecked_p, TreeViewer clientViewer) {
    // New input data.
    TreeData input = null;
    // Get the 'real' viewer.
    List<Object> referencingElements = ((TreeData) clientViewer.getInput()).getValidElements();
    input = super.getTreeViewerItems(isChecked_p, referencingElements);
    // Set the new input.
    clientViewer.setInput(input);
  }

  @Override
  protected void doCreateDialogArea(Composite dialogAreaComposite) {
    createCustomArea(dialogAreaComposite);
  }

  @Override
  protected Control createContents(Composite parent) {
    Control control = super.createContents(parent);
    updateMessageAndButtons();
    return control;
  }

  private void updateMessageAndButtons() {
    IStatus status = getStatus();
    if (!status.isOK()) {
      getButton(OK).setEnabled(false);
      getShell().setDefaultButton(getButton(CANCEL));
    } else {
      getShell().setDefaultButton(getButton(OK));
    }

    int intStatus = IMessageProvider.NONE;
    if (status.getSeverity() == IStatus.INFO) {
      intStatus = IMessageProvider.INFORMATION;
    } else if (status.getSeverity() == IStatus.WARNING) {
      intStatus = IMessageProvider.WARNING;
    } else if (status.getSeverity() == IStatus.ERROR) {
      intStatus = IMessageProvider.ERROR;
    }

    setMessage(status.getMessage(), intStatus);
  }

  protected IStatus getStatus() {
    return Status.OK_STATUS;
  }

}
