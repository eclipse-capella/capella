/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.accelerators;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

public class SelectOrCreateFunctionalExchangeDialog extends SelectElementsDialog {
  
  public class NewFEData {
    String name;
    AbstractFunction source;
    AbstractFunction target;
    
    public NewFEData(String name, AbstractFunction source, AbstractFunction target) {
      this.name = name;
      this.source = source;
      this.target = target;
    }
    
    public String getName() {
      return name;
    }
    
    public AbstractFunction getSource() {
      return source;
    }
    
    public AbstractFunction getTarget() {
      return target;
    }
  }

  public static final int CREATION = 5;
  public static final int SELECTION = 10;

  Button creationChecker;
  boolean creationChecked;

  Text feNameInputText;
  Button sourceBrowseButton;
  Button targetBrowseButton;

  Set<FunctionalExchange> availableFCILinks;
  Set<AbstractFunction> availableFCIFunctions;

  AbstractFunction newFESource;
  AbstractFunction newFETarget;
  String newFEName = "";

  Shell parentShell;

  Set<AbstractFunction> availableSourceFunctions;
  Set<AbstractFunction> availableTargetFunctions;

  public SelectOrCreateFunctionalExchangeDialog(Shell parentShell, Set<FunctionalExchange> availableFEs,
      Set<AbstractFunction> availableSourceFunctions, Set<AbstractFunction> availableTargetFunctions) {
    super(parentShell, "Select or create Functional Exchange", "Select or create Functional Exchange", availableFEs);
    this.availableFCILinks = availableFEs;
    this.availableFCIFunctions = availableSourceFunctions;

    this.parentShell = parentShell;

    this.availableSourceFunctions = availableSourceFunctions;
    this.availableTargetFunctions = availableTargetFunctions;
  }

  @Override
  protected void okPressed() {
    super.okPressed();
    int returnCode = super.getReturnCode();
    if (Window.OK == returnCode) {
      if (creationChecked) {
        setReturnCode(CREATION);
      } else {
        setReturnCode(SELECTION);
      }
    }
  }

  @Override
  protected void doCreateDialogArea(Composite parent) {
    createCreationForm(parent);
    super.doCreateDialogArea(parent);
  }

  public void createCreationForm(Composite parent) {
    Group creationGroup = new Group(parent, SWT.NONE);
    creationGroup.setText("Create a new Functional Exchange");
    creationGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    creationGroup.setLayout(new GridLayout(3, false));

    creationChecker = new Button(creationGroup, SWT.CHECK);
    creationChecker.setText("Create a new Functional Exchange");
    creationChecker.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false, 4, 1));

    creationChecker.addSelectionListener(new SelectionListener() {

      @Override
      public void widgetSelected(SelectionEvent e) {
        handleCreationChecker();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        handleCreationChecker();
      }
    });

    creationChecker.setSelection(false);

    Label feNameLabel = new Label(creationGroup, SWT.NONE);
    feNameLabel.setText("Name of Functional Exchange");
    feNameLabel.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));

    feNameInputText = new Text(creationGroup, SWT.SINGLE | SWT.BORDER);
    feNameInputText.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
    feNameInputText.setEnabled(false);
    feNameInputText.addModifyListener(e -> {
      newFEName = feNameInputText.getText();
      updateOKButton();
    });

    String labelTextForSourceFunction = "Select Function as source";
    createFunctionLabel(creationGroup, labelTextForSourceFunction);
    Text sourceInputText = createFunctionInputText(creationGroup);
    sourceBrowseButton = createFunctionBrowseButton(creationGroup);
    sourceBrowseButton.addSelectionListener(new SelectionListener() {
      private void update() {
        newFESource = selectAbstractFunction(availableSourceFunctions, labelTextForSourceFunction);
        sourceInputText.setText(getLabelProvider().getText(newFESource));
        updateOKButton();
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        update();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        update();
      }
    });

    String labelTextForTargetFunction = "Select Function as target";
    createFunctionLabel(creationGroup, labelTextForTargetFunction);
    Text targetInputText = createFunctionInputText(creationGroup);
    targetBrowseButton = createFunctionBrowseButton(creationGroup);
    targetBrowseButton.addSelectionListener(new SelectionListener() {
      private void update() {
        newFETarget = selectAbstractFunction(availableTargetFunctions, labelTextForTargetFunction);
        targetInputText.setText(getLabelProvider().getText(newFETarget));
        updateOKButton();
      }
      
      @Override
      public void widgetSelected(SelectionEvent e) {
        update();
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        update();
      }
    });
  }
  
  private AbstractFunction selectAbstractFunction(Set<AbstractFunction> availableSourceFunctions2, String mesage) {
    SelectElementsDialog dialog = new SelectElementsDialog(parentShell, "Select a Function", mesage, availableSourceFunctions2);
    if (dialog.open() == OK) {
      EObject selectedElement = dialog.getResult().stream().findFirst().orElse(null);
      if (selectedElement instanceof AbstractFunction) {
        return (AbstractFunction) selectedElement;
      }
    }
    return null;
  }

  private Button createFunctionBrowseButton(Group creationGroup) {
    Button browseButton = new Button(creationGroup, SWT.PUSH);
    browseButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID));
    browseButton.setEnabled(false);

    return browseButton;
  }

  private Text createFunctionInputText(Group creationGroup) {
    Text inputText = new Text(creationGroup, SWT.SINGLE | SWT.BORDER);
    inputText.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
    inputText.setEnabled(false);

    return inputText;
  }

  private Label createFunctionLabel(Group creationGroup, String labelText) {
    Label label = new Label(creationGroup, SWT.NONE);
    label.setText(labelText);
    label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false));

    return label;
  }

  protected void handleCreationChecker() {
    this.creationChecked = creationChecker.getSelection();
    getViewer().setEnabled(!creationChecked);
    feNameInputText.setEnabled(creationChecked);
    sourceBrowseButton.setEnabled(creationChecked);
    targetBrowseButton.setEnabled(creationChecked);
    updateOKButton();
  }

  public NewFEData getCreation() {
    return new NewFEData(newFEName, newFESource, newFETarget);
  }

  public List<FunctionalExchange> getSelection() {
    return getResult().stream().map(FunctionalExchange.class::cast).collect(Collectors.toList());
  }

  private void updateOKButton() {
    Button okButton = getButton(IDialogConstants.OK_ID);
    if (creationChecked) {
      boolean creationDataFullfilled = !newFEName.isEmpty();
      creationDataFullfilled &= (newFESource != null);
          creationDataFullfilled &= (newFETarget != null);
      okButton.setEnabled(creationDataFullfilled);
    } else {
      boolean selectionDataFullfilled = (getResult() != null) && (!getResult().isEmpty());
      okButton.setEnabled(selectionDataFullfilled);
    }
  }
}