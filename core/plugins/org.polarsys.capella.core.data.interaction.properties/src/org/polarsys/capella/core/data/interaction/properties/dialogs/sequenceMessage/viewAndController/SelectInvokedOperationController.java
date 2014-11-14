/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.viewAndController;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.Messages;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.SelectInvokedOperationModel;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

public class SelectInvokedOperationController {

	protected SelectInvokedOperationModel model;
	protected SelectInvokedOperationView view;
	
	public SelectInvokedOperationController(SelectInvokedOperationModel model_p) {
		this.model = model_p;
	}
		
	public void selectionHasChanged(ISelection selection_p) {
  	Object selectedElement = (Object) (selection_p == null || selection_p.isEmpty() ? null : ((IStructuredSelection) selection_p).getFirstElement());
  	if (selectedElement instanceof AbstractCommunication) {
  		model.setSelectedElement((AbstractCommunication) selectedElement);  		
  	} else {
  		model.setSelectedElement(null);
  	}
  	updateWindow();		
	}
	
	protected void initControlOnView(SelectInvokedOperationView view_p) {
		this.view = view_p;
		// initialize controls ...
    // ... for createSequenceMessageKind_area
    view.synchronousRadioButton.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent event_p) {
    		model.setMessageKind(MessageKind.SYNCHRONOUS_CALL);
    		if (model.doesElementMustBeCreated()) {
    			updateRadioButtons();    			
    		} else {
    			view.refreshPossibleElementsTree();    			
    		}
    	}
    });
    view.asynchronousRadioButton.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent event_p) {
    		model.setMessageKind(MessageKind.ASYNCHRONOUS_CALL);
    		if (model.doesElementMustBeCreated()) {
    			updateRadioButtons();    			
    		} else {
    			view.refreshPossibleElementsTree();    			
    		}
    	}
    });    
    // ... for createElementCreation_area
    view.createElementButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	boolean elementCreation = ((Button) event_p.widget).getSelection();
      	model.setElementMustBeCreated(elementCreation);
      	if (elementCreation) {
      		model.setSelectedElement(null);
      	} else {
      		model.setSelectedElement(view.getSelectedElement());
      	}
      	updateWindow();
      	if (!elementCreation) {
      		view.refreshPossibleElementsTree();
      	}
      }
		});
    SelectionAdapter listener = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	Button button = ((Button) event_p.widget);
        if (button == view.operationRadioButton) {
        	model.setSelectedExchangeMechanism(ExchangeMechanism.OPERATION);
        } else if (button == view.eventRadioButton) {
        	model.setSelectedExchangeMechanism(ExchangeMechanism.EVENT);
        } else if (button == view.flowRadioButton) {
        	model.setSelectedExchangeMechanism(ExchangeMechanism.FLOW);
        } else if (button == view.sharedRadioButton) {
        	model.setSelectedExchangeMechanism(ExchangeMechanism.SHARED_DATA);
        } else {
        	model.setSelectedExchangeMechanism(ExchangeMechanism.UNSET);
        }
      }
		};
		view.operationRadioButton.addSelectionListener(listener);
		view.eventRadioButton.addSelectionListener(listener);
		view.sharedRadioButton.addSelectionListener(listener);
		view.flowRadioButton.addSelectionListener(listener);
		view.unsetRadioButton.addSelectionListener(listener);

		view.exchangeItemNameText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e_p) {
      	model.setCreatedElementName(view.exchangeItemNameText.getText());
      	updateOkButton();
      }
    });
    // ... for createInterface_area
    view.interfaceText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e_p) {
        model.setSelectedInterfaceName(view.interfaceText.getText());
        if (!model.isValidInterfaceName()) {
        	view.setErrorMessage("A technical interface already exists with the given name. Choose another name."); //$NON-NLS-1$
        } else {
        	if (view.getErrorMessage() != null) {
        		view.setErrorMessage(null);
        	}
        	if (model.doesInterfaceExist()) {
        		view.setMessage("The selected interface exists."); //$NON-NLS-1$
        	} else {
        		view.setMessage(null);
        	}        	
        }
      	updateOkButton();
      }
    });
    view.selectInterfaceButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        SelectElementsDialog selectInterfaceDialog =
            new SelectElementsDialog(view.getTheParentShell(),
                TransactionHelper.getEditingDomain(model.getSelectedElement()),
                CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(),
                Messages.SelectOperationDialog_SelectInterfaceDialog_Title, Messages.SelectOperationDialog_SelectInterfaceDialog_Message,
                model.getInterfaces(true, false));
        if (Window.OK == selectInterfaceDialog.open()) {
          AbstractNamedElement selectedInterface = (AbstractNamedElement) selectInterfaceDialog.getResult().get(0);
          view.interfaceText.setData(selectedInterface);
          model.setSelectedInterface((Interface) selectedInterface);
          view.interfaceText.setText(selectedInterface.getName());
          model.setSelectedInterfaceName(selectedInterface.getName());
        }
      }
    });
    
    // ... for createSelectionOptions_area
    view.optionSelectionA_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setHideTechnicalInterfaceNames(((Button) event_p.widget).getSelection());
      	view.refreshPossibleElementsTree();
      }
    });
    view.optionSelectionB_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setRestrictToExistingStaticCommunicationCompatibility(((Button) event_p.widget).getSelection());
      	view.refreshPossibleElementsTree();
      }
    });
    view.optionSelectionC_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setAllowSelectionOfExistingExchangeItems(((Button) event_p.widget).getSelection());
      	view.refreshPossibleElementsTree();
      }
    });
    // ... for createCreationOptions_area
    view.optionCreationA_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setPortsMustBeCreated(((Button) event_p.widget).getSelection());
      }
    });
    view.optionCreationB_button.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent event_p) {
      	model.setCommunicationLinksMustBeCreated(((Button) event_p.widget).getSelection());
      }
    });
    // initialize the view state
    updateWindow();
	}
	
  /**
   * Enable or disable part of window according context.
   */
  protected void updateWindow() {
    if (view != null) {//because the tree viewer raise one event while the controler has not been initialized
    	updateCreationArea();
    	upddateInterfaceArea();
    	view.getTreeViewer().setEnabled(!model.doesElementMustBeCreated());
    	updateSelectionOptionButtons();
    	updateCreationOptionButtons();    
    	updateOkButton();
    }
  }  

  private void updateCreationArea() {
    view.eiTypeGroup.setEnabled(model.doesElementMustBeCreated() || model.getSelectedElement() instanceof ExchangeItem);
    view.exchangeItemNameText.setEnabled(model.doesElementMustBeCreated());
    updateRadioButtons(); 	
  }
  
  /** Updates radio button */
  // Dynamically update according to the model while it could be static for the current specification (based on the message kind).
  protected void updateRadioButtons() {
  	boolean elementIsCreated = model.doesElementMustBeCreated();
  	// enabled/disabled state update
  	List<ExchangeMechanism> compatibleMechanisms = model.getCompatibleExchangeMechanism();
  	view.unsetRadioButton.setEnabled(elementIsCreated && compatibleMechanisms.contains(ExchangeMechanism.UNSET));
  	view.eventRadioButton.setEnabled(elementIsCreated && compatibleMechanisms.contains(ExchangeMechanism.EVENT));
  	view.flowRadioButton.setEnabled(elementIsCreated && compatibleMechanisms.contains(ExchangeMechanism.FLOW));
  	view.sharedRadioButton.setEnabled(elementIsCreated && compatibleMechanisms.contains(ExchangeMechanism.SHARED_DATA));
  	view.operationRadioButton.setEnabled(elementIsCreated && compatibleMechanisms.contains(ExchangeMechanism.OPERATION));
  	ExchangeMechanism mechanism = model.getSelectedExchangeMechanism();
  	view.unsetRadioButton.setSelection(mechanism == ExchangeMechanism.UNSET);
  	view.eventRadioButton.setSelection(mechanism == ExchangeMechanism.EVENT);
  	view.flowRadioButton.setSelection(mechanism == ExchangeMechanism.FLOW);
  	view.sharedRadioButton.setSelection(mechanism == ExchangeMechanism.SHARED_DATA);
  	view.operationRadioButton.setSelection(mechanism == ExchangeMechanism.OPERATION);  	
  }  
  
  private void upddateInterfaceArea() {
  	view.interfaceText.setEnabled(model.doesInterfaceNameCanBeEdited());    
  	view.selectInterfaceButton.setEnabled(model.doesInterfaceCanBeChosen());
  	String interfaceNameInModel = model.getSelectedInterfaceName();
  	if (!view.interfaceText.getText().equals(interfaceNameInModel)) {
  		view.interfaceText.setText(interfaceNameInModel);  		
  	}
  }
  
  private void updateSelectionOptionButtons() {
  	boolean createExchangeItemIsChecked = model.doesElementMustBeCreated();
  	view.optionSelectionA_button.setEnabled(!createExchangeItemIsChecked);
  	view.optionSelectionB_button.setEnabled(!createExchangeItemIsChecked);
  	view.optionSelectionC_button.setEnabled(!createExchangeItemIsChecked);
  }

  private void updateCreationOptionButtons() {
    // update option A    
  	view.optionCreationA_button.setEnabled(model.doesPortsCreationCanBeToggled());
  	view.optionCreationA_button.setSelection(model.doesPortsMustBeCreated());
    // update option B
  	view.optionCreationB_button.setEnabled(model.doesCommunicationLinksCreationCanBeToggled());
  	view.optionCreationB_button.setSelection(model.doesCommunicationLinksMustBeCreated());
  }
  
  protected void updateOkButton() {
		// Update the OK button.
		Button okButton = view.getOkButton();
		if ((null != okButton) && !okButton.isDisposed()) {
			okButton.setEnabled(model.isValid());
		}  		
  }
}
