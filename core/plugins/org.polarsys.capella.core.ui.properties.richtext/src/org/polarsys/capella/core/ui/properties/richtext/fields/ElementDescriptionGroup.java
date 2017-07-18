/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.eclipse.kitalpha.richtext.common.intf.SaveStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;

/**
 * @author Joao Barata
 */
public abstract class ElementDescriptionGroup implements SelectionListener, FocusListener, KeyListener {

  /**
   * Current edited semantic element.
   */
  protected EObject _semanticElement;

  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature _semanticFeature;

  /**
   * Text widget.
   */
  protected MDERichTextWidget _descriptionTextField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public ElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super();

    Group textGroup = new Group(parent, SWT.NONE);
    if (widgetFactory != null) {
      widgetFactory.adapt(textGroup);
    }
    textGroup.setLayout(new GridLayout());
    textGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    createDescriptionField(textGroup, widgetFactory);

  }

  /**
   * 
   */
  public void dispose() {
    if (null != _descriptionTextField) {
      _descriptionTextField.dispose();
    }
  }

  /**
   * @param textGroup
   * @param widgetFactory
   */
  private void createDescriptionField(Group textGroup, TabbedPropertySheetWidgetFactory widgetFactory) {
    _descriptionTextField = createDescriptionField(textGroup);
    //GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    //_descriptionTextField.setLayoutData(gd);
    // _descriptionTextField.setEditable(false);
    _descriptionTextField.addFocusListener(this);
  }

  /**
   * This method can be override to provide a customized text widget
   * @param parent
   * @return
   */
  protected MDERichTextWidget createDescriptionField(Composite parent) {
//    RichTextEditorConfiguration conf = new RichTextEditorConfiguration();
//    conf.setOption(RichTextEditorConfiguration.TOOLBAR_GROUPS, "");
//    RichTextEditor editor = new RichTextEditor(parent, conf);
//    editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//    return editor;
    MDERichTextFactory f = new MDERichTextFactory();
	final MDERichTextWidget ee = f.createDefaultRichTextWidget(parent);
	ee.addModifyListener(new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			ee.saveContent();
		}
	});
	
	
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
		
		protected void executeCommand(final ICommand command) {
		    // Precondition
		    if ((null == command)) {
		      return;
		    }
		    // Command to run.
		    ICommand cmd = command;
		    // Encapsulate given command in a new one to enable undo / redo refresh based on getAffectedObjects.
		    // AbstractSection call getAffectedObjects() against the command provided by the OperationHistory.
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
		          return Collections.singletonList(_semanticElement);
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
		          loadData(_semanticElement, _semanticFeature);
		        }
		      };
		    }
		    // Execute it against the TED.
		    TransactionHelper.getExecutionManager(_semanticElement).execute(cmd);
		  }
	});
	
	
	return ee;
  }

  /**
   * @param enabled
   */
  public void setEnabled(boolean enabled) {
//    if (_descriptionTextField != null) {
//      _descriptionTextField.setEditable(enabled);
//    }
  }

  /**
   * @param element
   * @param feature
   */
  public void loadData(EObject element, EStructuralFeature feature) {
    _semanticElement = element;
    _semanticFeature = feature;
   _descriptionTextField.bind(_semanticElement, _semanticFeature);
    //setTextValue(_descriptionTextField, _semanticElement, _semanticFeature);
  }

  /**
   * Set text value.
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
   * @param object
   * @param feature
   * @param value
   */
  protected abstract void setDataValue(final EObject object, final EStructuralFeature feature, final Object value);

  /**
   * Save a description into related element.
   * @param newDescription
   */
  public void saveDescription(String newDescription) {
    setDataValue(_semanticElement, _semanticFeature, newDescription);
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
    return _descriptionTextField.getText();
  }

  /**
   * @param textField text field to be filled
   */
  protected void fillTextField(Text textField) {
//    if (textField.equals(_descriptionTextField)) {
//      setDataValue(_semanticElement, _semanticFeature, _descriptionTextField.getText());
//    }
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
   */
  public void focusGained(final FocusEvent event) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
   */
  public void focusLost(FocusEvent event) {
//    if (event != null) {
//      Object source = event.getSource();
//      if ((source != null) && (source instanceof Text)) {
//        fillTextField((Text) source);
//      }
//    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
   */
  public void keyPressed(KeyEvent event) {
//    if ((event != null) && (event.character == SWT.CR)) {
//      Object source = event.getSource();
//      if ((source != null) && (source instanceof Text)) {
//        fillTextField((Text) source);
//      }
//    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
   */
  public void keyReleased(KeyEvent event) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent event) {
	// Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent event) {
    // Do nothing.
  }
}
