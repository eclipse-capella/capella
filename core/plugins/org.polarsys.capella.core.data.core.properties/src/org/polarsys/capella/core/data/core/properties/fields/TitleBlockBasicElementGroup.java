/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.fields;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;

public class TitleBlockBasicElementGroup extends AbstractSemanticField implements ModifyListener {
  protected Text nameTextField;
  protected Text contentTextField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public TitleBlockBasicElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true, true);
  }

  /**
   * @param parent
   * @param widgetFactory
   * @param hasNameField
   * @param hasContentField
   */
  public TitleBlockBasicElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean hasNameField, boolean hasContentField) {
    super(widgetFactory);

    Group textGroup = widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    // Name
    if (hasNameField) {
      nameTextField = createTextField(textGroup, Messages.getString("NamedElement.NameLabel"));
    }
    // Content
    if (hasContentField) {
      contentTextField = createTextField(textGroup, Messages.getString("NamedElement.ContentLabel"));
    }
  }

  /**
   * @param textGroup
   * @param textLabel
   */
  private Text createTextField(Group textGroup, String textLabel) {
    widgetFactory.createCLabel(textGroup, textLabel);

    Text textField = widgetFactory.createText(textGroup, ICommonConstants.EMPTY_STRING);
    textField.addFocusListener(this);
    textField.addKeyListener(this);
    textField.addModifyListener(this);
    textField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return textField;
  }

  /**
   * {@inheritDoc}
   */
  public void loadData(EObject semanticElement, String name, String content) {
    super.loadData(semanticElement, null);

    if (null != semanticElement) {
      if (null != nameTextField)
        setTextValue(nameTextField, name);
      if (null != contentTextField)
        setTextValue(contentTextField, content);
    }
  }

  protected void setTextValue(Text text, String value) {
    text.setText(value);
  }

  /**
   * @param textField
   *          text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(nameTextField)) {
      setNameValue(semanticElement, nameTextField.getText());
    } else if (textField.equals(contentTextField)) {
      setContentValue(semanticElement, contentTextField.getText());
    }
  }

  private void setNameValue(EObject object, final Object value) {
    if (!((DAnnotation) object).getDetails().get("Name:").equals(value.toString())) {
      DDiagram diagram = (DDiagram) object.eContainer();
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          ((DAnnotation) object).getDetails().put("Name:", value.toString());
          RefreshRepresentationsCommand refreshCommand = new RefreshRepresentationsCommand(
              TransactionUtil.getEditingDomain(diagram), new NullProgressMonitor(), diagram);
          refreshCommand.execute();
        }
      };
      executeCommand(command);
    }
  }

  private void setContentValue(EObject object, final Object value) {
    if (!((DAnnotation) object).getDetails().get("Content:").equals(value.toString())) {
      DDiagram diagram = (DDiagram) object.eContainer();
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          ((DAnnotation) object).getDetails().put("Content:", value.toString());
          RefreshRepresentationsCommand refreshCommand = new RefreshRepresentationsCommand(
              TransactionUtil.getEditingDomain(diagram), new NullProgressMonitor(), diagram);
          refreshCommand.execute();
        }
      };
      executeCommand(command);
    }
  }

  /**
   *
   */
  public void clearNameField() {
    if (null != nameTextField) {
      setDataValue(semanticElement, ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name(), "");
      nameTextField.setText("");
    }
  }

  /**
   * @param enabled
   *          whether or not the name text field is enabled
   */
  public void enableNameField(boolean enabled) {
    if (null != nameTextField && !nameTextField.isDisposed()) {
      nameTextField.setEnabled(enabled);
    }
  }

  /**
   * @param enabled
   *          whether or not the content text field is enabled
   */
  public void enableContentField(boolean enabled) {
    if (null != contentTextField && !contentTextField.isDisposed()) {
      contentTextField.setEnabled(enabled);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    LockHelper.getInstance().enable(nameTextField, enabled);
    LockHelper.getInstance().enable(contentTextField, enabled);
  }

  @Override
  public void loadData(EObject semanticElement) {
    // TODO Auto-generated method stub
  }

  @Override
  public void modifyText(ModifyEvent event) {
    if (event != null) {
      Object source = event.getSource();
      if (source instanceof Text) {
        fillTextField((Text) source);
      }
    }
  }
}
