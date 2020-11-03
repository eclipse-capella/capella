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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.viewAndController;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.Messages;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.ISelectInvokedOperationModel;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.SelectInvokedOperationModel;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

/**
 * Dialog to select an operation (i.e service).<br>
 * The viewer is mono-selection.
 */
public class SelectInvokedOperationView extends SelectElementsDialog {

  protected Button optionSelectionA_button;	
  protected Button optionSelectionB_button;
  protected Button optionSelectionC_button;
  
  protected Button optionCreationA_button;
  protected Button optionCreationB_button;  

  protected Button synchronousRadioButton;
  protected Button asynchronousRadioButton;

  // radio buttons corresponding to EI types (and its group)
  protected Button eventRadioButton;
  protected Button flowRadioButton;
  protected Button operationRadioButton;
  protected Button sharedRadioButton;
  protected Group eiTypeGroup;
  protected Button unsetRadioButton;
  
  /** Button to enable / disable creation mode. */
  protected Button createElementButton;
  /** Text field used to store the name of a created ExchangeItem (if it is the case). */
  protected Text exchangeItemNameText;
  /** Text field used to store the name and the interface (through widget data) instance that will have a new operation.<br>
   * If getData returns <code>null</code>, a new interface must be created with entered name. */
  protected Text interfaceText;
  /** Button that allows the end-user to select an existing interface when creating a new operation. */
  protected Button selectInterfaceButton;
  
  protected ISelectInvokedOperationModel model;
  protected SelectInvokedOperationController controller;

	public SelectInvokedOperationView(
			Shell parentShell, 
			String dialogTitle, 
			String dialogMessage, 
			ISelectInvokedOperationModel model,
			SelectInvokedOperationController controller
		) {
		super(
				parentShell, 
				new DataContentProvider(),
				new SelectInvokedOperationLabelProvider(model),	
				dialogTitle, 
				dialogMessage, 
				model.getPossibleElements(),
				false,
				null, 
				AbstractTreeViewer.ALL_LEVELS);
		this.model = model;
		this.controller = controller;
	}
	
	// Getter for the controller
	protected Shell getTheParentShell() {
		return getParentShell();
	}
	
  @Override
  public void create() {
    super.create();
    getShell().setMinimumSize(661, 800);
  }
	
  // this method is not well named in the super class. 
  // It is called when the selection changes.
  // We redirect this information to the controller
  @Override
  protected void updateButtons(ISelection selection) {
  	if (controller != null) {
  		controller.selectionHasChanged(selection);
  	}
  }
    
  public AbstractCommunication  getSelectedElement() {  	
  	IStructuredSelection selection = (IStructuredSelection) getViewer().getSelection();
  	if (selection == null || selection.isEmpty()) {
  		return null;
  	} else {
  		Object selectedElement = selection.getFirstElement();
  		if (selectedElement instanceof AbstractCommunication) {
  			return (AbstractCommunication) selectedElement;  			
  		} else {
  			return null;
  		}
  	}
  }
  
  @Override
  protected int getTreeViewerStyle() {
    return IViewerStyle.SHOW_STATUS_BAR;
  }

  // to make it visible for the controller
  protected TreeAndListViewer getTreeViewer() {
    return super.getViewer();
  }
	
	//to make it visible for the controller
	protected Button getOkButton() {
		return getButton(IDialogConstants.OK_ID);
	}

  @Override
  protected void doCreateDialogArea(Composite parent) {
    // add the GUI group for defining the sequence message kind
    createSequenceMessageKind_area(parent);
    // add the GUI group for the creation choice
    createElementCreation_area(parent,
        isCreateEIEnabeld(((SelectInvokedOperationModel) model).getSourceInstanceRole()));
    // add the GUI group for interface choice and renaming
    createInterface_area(parent);
    // add the tree viewer
    super.doCreateDialogArea(parent);
    TreeAndListViewer viewer = getViewer();
    viewer.getClientViewer().addFilter(new SelectInvokedOperationFilter(viewer, model));
    // add the GUI group for the selection options
    createSelectionOptions_area(parent);
    // add the GUI group for the creation options
    createCreationOptions_area(parent);
    // initialize controllers
    controller.initControlOnView(this);
  }
  
  private boolean isCreateEIEnabeld(InstanceRole instanceRole) {
    return !CapellaLayerCheckingExt.isAOrInEPBSLayer(instanceRole);
  }

  @Override
  protected void addDoubleCLickListener() {
  	// do nothing because we do not need this feature
  }
  
  @Override
  protected void createTreeViewerPart(Composite parent) {
    // Add a group surrounding the tree viewer part.
    Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_SelectExistingOperationGroup_Title);
    treeViewerPartGroup.setLayout(new GridLayout());
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    super.createTreeViewerPart(treeViewerPartGroup);
  }  
    
  private void createSequenceMessageKind_area(Composite parent) {
  	// define GUI
  	Group messageKindGroup = new Group(parent, SWT.NONE);
  	messageKindGroup.setText(Messages.SelectOperationDialog_SequenceMessageKind);
  	messageKindGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
  	messageKindGroup.setLayout(new GridLayout(2, false)); 
    synchronousRadioButton = new Button(messageKindGroup, SWT.RADIO);
    synchronousRadioButton.setText(Messages.SelectOperationDialog_SequenceMessageKind_synchronous);
    asynchronousRadioButton = new Button(messageKindGroup, SWT.RADIO);
    asynchronousRadioButton.setText(Messages.SelectOperationDialog_SequenceMessageKind_asynchronous);
    // set the current selection 
    boolean isSynchronous = model.getMessageKind() == MessageKind.SYNCHRONOUS_CALL;
    synchronousRadioButton.setSelection(isSynchronous);
    asynchronousRadioButton.setSelection(!isSynchronous);
    synchronousRadioButton.setEnabled(model.doesTheMessageReturnAValue());    
  }
  
  /**
   * Create creation operation widgets.
   * @param parent
   */
  private void createElementCreation_area(Composite parent, boolean enabled) {
    // Add a group surrounding the create operation part.
    final Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setEnabled(enabled);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(3, false)); // 3 columns one for the label, one the text and the last one for the button
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_CreateNewExchangeItem);

    createElementButton = new Button(treeViewerPartGroup, SWT.CHECK);
    createElementButton.setText(Messages.SelectOperationDialog_EnableCreationButton_Title);
    createElementButton.setEnabled(enabled);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    createElementButton.setLayoutData(layoutData);
    createElementButton.setSelection(model.doesElementMustBeCreated());    

    Label label = createLabel(treeViewerPartGroup, Messages.SelectOperationDialog_Operation_Title);
    label.setEnabled(enabled);
    
    exchangeItemNameText = createText(treeViewerPartGroup);
    exchangeItemNameText.setEnabled(enabled);
    ((GridData) exchangeItemNameText.getLayoutData()).horizontalSpan = 3; // No button following.

    eiTypeGroup = new Group(treeViewerPartGroup, SWT.NONE);
    eiTypeGroup.setText(Messages.SelectOperationDialog_2);
    eiTypeGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    eiTypeGroup.setLayout(new GridLayout(5, false));
    eventRadioButton = new Button(eiTypeGroup, SWT.RADIO);
    eventRadioButton.setText(Messages.SelectOperationDialog_3);

    flowRadioButton = new Button(eiTypeGroup, SWT.RADIO);
    flowRadioButton.setText(Messages.SelectOperationDialog_4);

    operationRadioButton = new Button(eiTypeGroup, SWT.RADIO);
    operationRadioButton.setText(Messages.SelectOperationDialog_5);

    sharedRadioButton = new Button(eiTypeGroup, SWT.RADIO);
    sharedRadioButton.setText(Messages.SelectOperationDialog_6);

    unsetRadioButton = new Button(eiTypeGroup, SWT.RADIO);
    unsetRadioButton.setText(Messages.SelectOperationDialog_7);
  }
  
  /**
   * Create creation operation widgets.
   * @param parent
   */
  private void createInterface_area(Composite parent) {
    // Add a group surrounding the create operation part.
    final Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_CreateOrSelectInterface);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(3, false)); // 3 columns one for the label, one the text and the last one for the button

    // Create a text field to host the Interface name.
    createLabel(treeViewerPartGroup, Messages.SelectOperationDialog_Interface_Title);
    interfaceText = createText(treeViewerPartGroup);

    selectInterfaceButton = new Button(treeViewerPartGroup, SWT.PUSH);
    selectInterfaceButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID));
  }
  
  protected void refreshPossibleElementsTree() {
  	getTreeViewer().getClientViewer().expandAll();
  	getViewer().getClientViewer().refresh();
  }
    
  private void createSelectionOptions_area(final Composite parent) {
    // add option A
  	optionSelectionA_button = new Button(getViewer().getControl(), SWT.CHECK);
  	optionSelectionA_button.setText(Messages.SelectOperationDialog_HideTechnicalInterfaceNamesButton_Title);    
  	optionSelectionA_button.setSelection(model.doesHideTechnicalInterfaceNames());
    // add option B
    optionSelectionB_button = new Button(getViewer().getControl(), SWT.CHECK);
    optionSelectionB_button.setText(Messages.SelectOperationDialog_RestrictedInterfacesButton_Title);
    optionSelectionB_button.setSelection(model.doesRestrictToExistingStaticCommunicationCompatibility());
    // add option C
    optionSelectionC_button = new Button(getViewer().getControl(), SWT.CHECK);
    optionSelectionC_button.setText(Messages.SelectOperationDialog_1);
    optionSelectionC_button.setSelection(model.doesAllowSelectionOfExistingExchangeItems());
  }
  
  private void createCreationOptions_area(final Composite parent) {
    optionCreationA_button = new Button(parent, SWT.CHECK);
    optionCreationA_button.setText(Messages.SelectOperationDialog_0);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    optionCreationA_button.setLayoutData(layoutData);
    optionCreationA_button.setSelection(model.doesPortsMustBeCreated());

    optionCreationB_button = new Button(parent, SWT.CHECK);
    optionCreationB_button.setText(Messages.SelectOperationDialog_8);
    layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    optionCreationB_button.setLayoutData(layoutData);
    optionCreationB_button.setSelection(model.doesPortsMustBeCreated());
  }  
  
  @Override
  protected List<? extends EObject> handleResult() {
  	return Collections.singletonList(model.createOrUpdateElement());
  }
  
}
