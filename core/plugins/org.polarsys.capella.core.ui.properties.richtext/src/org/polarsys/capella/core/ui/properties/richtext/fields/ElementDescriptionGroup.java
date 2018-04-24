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
package org.polarsys.capella.core.ui.properties.richtext.fields;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.common.intf.SaveStrategy;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaBasedRichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaRichTextConfiguration;
import org.polarsys.kitalpha.richtext.nebula.widget.MDERichTextConstants;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;
import org.polarsys.kitalpha.richtext.widget.helper.MDERichtextWidgetHelper;

/**
 * @author Joao Barata
 */
public abstract class ElementDescriptionGroup {

  /**
   * Current edited semantic element.
   */
  protected EObject semanticElement;

  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature semanticFeature;

  /**
   * Text widget.
   */
  protected MDERichTextWidget descriptionTextField;

  protected Group descriptionContainer;

  protected GridData existedEditorLayoutData;

  protected Label existedEditorLabel;

  protected Composite parentComposite;

  private static final String EXISTED_EDITOR_TEXT = "The description is currenty opened in an editor. Please use this editor to edit your description."; //$NON-NLS-1$

  /**
   * @param parent
   * @param widgetFactory
   */
  public ElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super();

    parentComposite = parent;

    existedEditorLabel = new Label(parent, SWT.NONE);
    existedEditorLayoutData = new GridData();
    existedEditorLabel.setLayoutData(existedEditorLayoutData);
    existedEditorLabel.setText(EXISTED_EDITOR_TEXT);

    // Do not propagate the focus event to the embedded editor
    descriptionContainer = new Group(parent, SWT.NONE) {
      @Override
      public boolean setFocus() {
        return true;
      }
      @Override
      protected void checkSubclass() {
        return;
      }
    };
    if (widgetFactory != null) {
      widgetFactory.adapt(descriptionContainer);
    }
    descriptionContainer.setLayout(new GridLayout());
    descriptionContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    descriptionTextField = createDescriptionField(descriptionContainer);
  }

  /**
   * 
   */
  public void dispose() {
    if (null != descriptionTextField) {
      descriptionTextField.dispose();
    }
  }

  /**
   * This method can be override to provide a customized text widget
   * 
   * @param parent
   * @return
   */
  protected MDERichTextWidget createDescriptionField(Composite parent) {
    MDERichTextFactory f = new MDERichTextFactory() {
      @Override
      protected MDERichTextFactory initializeMDEMinimalToolbar() {
        MDERichTextFactory factory = super.initializeMDEMinimalToolbar();
        ((MDENebulaRichTextConfiguration) getConfiguration()).removeToolbarItems(MDERichTextConstants.SUBSCRIPT,
            MDERichTextConstants.SUPERSCRIPT);
        return factory;
      }
    };
    MDERichTextWidget ee = f.createMinimalRichTextWidget(parent);

    ee.setSaveStrategy(new SaveStrategy() {
      @Override
      public void save(final String editorText, final EObject owner, final EStructuralFeature feature) {
        if (NotificationHelper.isNotificationRequired(owner, feature, editorText)) {
          AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
            public void run() {
              owner.eSet(feature, editorText);
            }
          };
          executeCommand(command);
        }
      }
    });

    return ee;
  }

  /**
   * Execute given command.
   * 
   * @param command
   */
  protected void executeCommand(final ICommand command) {
    // Precondition
    if ((null == command)) {
      return;
    }
    // Command to run.
    ICommand cmd = command;
    // Encapsulate given command in a new one to enable undo / redo refresh
    // based on getAffectedObjects.
    // AbstractSection call getAffectedObjects() against the command
    // provided by the OperationHistory.
    if (command instanceof AbstractReadWriteCommand) {
      cmd = new AbstractReadWriteCommand() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          command.run();
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getAffectedObjects()
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public Collection<?> getAffectedObjects() {
          return Collections.singletonList(semanticElement);
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#getName()
         */
        @Override
        public String getName() {
          return "Model Edition"; //$NON-NLS-1$
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#commandInterrupted()
         */
        @Override
        public void commandInterrupted() {
          commandRolledBack();
        }

        /**
         * @see org.polarsys.capella.common.tig.ef.command.AbstractCommand#commandRolledBack()
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void commandRolledBack() {
          // Reload data >> refresh the UI.
          loadData(semanticElement, semanticFeature);
        }
      };
    }
    // Execute it against the TED.
    TransactionHelper.getExecutionManager(semanticElement).execute(cmd);
  }

  /**
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
        // do nothing
  }

  /**
   * @param element
   * @param feature
   */
  public void loadData(EObject element, EStructuralFeature feature) {
    if (element != semanticElement) {
      semanticElement = element;
      semanticFeature = feature;
      if (descriptionTextField instanceof MDENebulaBasedRichTextWidget) {
        ((MDENebulaBasedRichTextWidget) descriptionTextField).setDirtyStateUpdated(true);
      }
      descriptionTextField.bind(semanticElement, semanticFeature);
    }
    else {
      try {
        descriptionTextField.loadContent();
      } catch (SWTException e) {
        // Catch SWT "Permission denied" exception
      }
    }
    
    updateDescriptionEditability();
  }

  /**
   * Set text value.
   * 
   * @param text
   * @param object
   * @param feature
   */
  protected void setTextValue(MDERichTextWidget text, EObject object, EStructuralFeature feature) {
    // Precondition:
    if ((null == text) || (null == object) || (null == feature)) {
      return;
    }
    Object value = object.eGet(feature);
    String currentTextValue = text.getText();
    String newTextValue = (String) ((value instanceof String) ? value : ICommonConstants.EMPTY_STRING);
    // Update the text value if needed.
    if (!currentTextValue.equals(newTextValue) || ICommonConstants.EMPTY_STRING.equals(currentTextValue)) {
      text.setText(newTextValue);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * 
   * @param object
   * @param feature
   * @param value
   */
  protected abstract void setDataValue(final EObject object, final EStructuralFeature feature, final Object value);

  /**
   * Save a description into related element.
   * 
   * @param newDescription
   */
  public void saveDescription(String newDescription) {
    setDataValue(semanticElement, semanticFeature, newDescription);
  }

  /**
   * @see org.eclipse.emf.IRichTextSaveable.runtime.capella.richtext.IEEFRichTextSaveable#save(java.lang.String)
   */
  public void save(String value) {
    saveDescription(value);
  }

  /**
   * @see org.eclipse.emf.IRichTextSaveable.runtime.capella.richtext.IEEFRichTextSaveable#getValueToSave()
   */
  public String getValueToSave() {
    return descriptionTextField.getText();
  }

  protected void updateDescriptionEditability() {
    // Only allow to edit the description using the property view when no
    // other editor concerning the same semantic element is opened
    boolean isEditable = MDERichtextWidgetHelper.getActiveMDERichTextEditors(semanticElement).isEmpty();
    descriptionContainer.setEnabled(isEditable);
    descriptionContainer.setVisible(isEditable);
    if (isEditable) {
      // Hide the text about "existed editor"
      existedEditorLabel.setVisible(false);
      existedEditorLayoutData.exclude = true;
    } else {
      // Show the text about "existed editor"
      existedEditorLabel.setVisible(true);
      existedEditorLayoutData.exclude = false;
    }
    // Recalculate the layout
    parentComposite.layout(true);
  }
}
