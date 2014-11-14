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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;

/**
 */
public abstract class AbstractSemanticField implements SelectionListener, FocusListener, KeyListener {
  /**
   * Current edited semantic element.
   */
  protected CapellaElement _semanticElement;
  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature _semanticFeature;

  /**
   * The widget factory
   */
  protected TabbedPropertySheetWidgetFactory _widgetFactory;

  /**
   * Whether or not the field is displayed in a wizard.
   */
  private boolean _displayedInWizard;

  /**
   * Constructor.
   */
  protected AbstractSemanticField(TabbedPropertySheetWidgetFactory widgetFactory_p) {
    _widgetFactory = widgetFactory_p;
    _displayedInWizard = false;
  }

  /**
   * Is this field displayed in a wizard ?
   * @return the displayedInWizard
   */
  public boolean isDisplayedInWizard() {
    return _displayedInWizard;
  }

  /**
   * Set whether or not the field is displayed in a wizard.
   * @param displayedInWizard_p the displayedInWizard to set
   */
  public void setDisplayedInWizard(boolean displayedInWizard_p) {
    _displayedInWizard = displayedInWizard_p;
  }

  /**
   * Execute given command.
   * @param command_p
   */
  protected void executeCommand(ICommand command_p) {
    // Precondition
    if ((null == command_p)) {
      return;
    }
    // Command to run.
    ICommand command = command_p;
    if (isDisplayedInWizard()) {
      // In wizards, we are already running within a TIG read/write command.
      // To be able to cancel modifications we need to run given command (i.e the one specified as argument) as a non dirtying one.
      // Indeed, we don't want to have these sub commands on the command stack, only the main one would be stacked.
      // Thus, let's create a non dirtying that is used as a proxy of given one.
      command = createNonDirtyingCommand(command_p);
    } else {
      // Encapsulate given command in a new one to enable undo / redo refresh based on getAffectedObjects.
      // AbstractSection call getAffectedObjects() against the command provided by the OperationHistory.
      if (command_p instanceof AbstractReadWriteCommand) {
        command = createReadWriteCommand(command_p);
      }
    }
    // Execute it against the TED.
    verifiedExecution(command);
  }

  /**
   * Retrieve the execution manager
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_semanticElement);
  }

  /**
   * This method verifies if there is an active transaction.<br>
   * If such case, the command is simply run.<br>
   * If not, the command is executed through the execution manager.<br>
   * @param command_p the command to be executed
   */
  protected void verifiedExecution(ICommand command_p) {
    ExecutionManager executionManager = getExecutionManager();
    if (null != executionManager) {
      TransactionalEditingDomain editingDomain = executionManager.getEditingDomain();
      if (editingDomain instanceof InternalTransactionalEditingDomain) {
        InternalTransaction activeTransaction = ((InternalTransactionalEditingDomain) editingDomain).getActiveTransaction();
        if ((null != activeTransaction) && activeTransaction.isActive()) {
          command_p.run();
        } else {
          executionManager.execute(command_p);
        }
      }
    }
  }

  /**
   * @param command_p
   */
  private ICommand createReadWriteCommand(final ICommand command_p) {
    return new AbstractReadWriteCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        command_p.run();
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
       */
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singletonList(_semanticElement);
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
       */
      @Override
      public String getName() {
        return "Model Edition"; //$NON-NLS-1$
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#commandInterrupted()
       */
      @Override
      public void commandInterrupted() {
        commandRolledBack();
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#commandRolledBack()
       */
      @Override
      public void commandRolledBack() {
        // Reload data >> refresh the UI.
        loadData(_semanticElement);
      }
    };
  }

  /**
   * @param command_p
   */
  private ICommand createNonDirtyingCommand(final ICommand command_p) {
    return new AbstractNonDirtyingCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        command_p.run();
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#commandInterrupted()
       */
      @Override
      public void commandInterrupted() {
        commandRolledBack();
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#commandRolledBack()
       */
      @Override
      public void commandRolledBack() {
        // Reload data >> refresh the UI.
        loadData(_semanticElement);
      }
    };
  }

  /**
   * Load the field for given element and given feature.
   * @param semanticElement_p
   * @param semanticFeature_p
   */
  public void loadData(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
    _semanticElement = semanticElement_p;
    _semanticFeature = semanticFeature_p;
  }

  /**
   * Load the UI representation of given semantic element.
   * @param semanticElement_p
   */
  public abstract void loadData(CapellaElement semanticElement_p);

  /**
   * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
   */
  public void focusGained(final FocusEvent event_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
   */
  public void focusLost(FocusEvent event_p) {
    if (event_p != null) {
      Object source = event_p.getSource();
      if ((source != null) && (source instanceof Text)) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
   */
  public void keyPressed(KeyEvent event_p) {
    if ((event_p != null) && (event_p.character == SWT.CR)) {
      Object source = event_p.getSource();
      if ((source != null) && (source instanceof Text)) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
   */
  public void keyReleased(KeyEvent event_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetSelected(SelectionEvent event_p) {
    if (event_p != null) {
      Object source = event_p.getSource();
      if ((source != null) && (source instanceof CCombo)) {
        fillComboField((CCombo) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
   */
  public void widgetDefaultSelected(SelectionEvent event_p) {
    // Do nothing.
  }

  /**
   * Fill given text field.<br>
   * Default behavior does nothing.
   * @param textField_p text field to be filled
   */
  protected void fillTextField(Text textField_p) {
    // Do nothing.
  }

  /**
   * Fill given combo field.<br>
   * Default behavior does nothing.
   * @param CCombo combo field to be filled
   */
  protected void fillComboField(CCombo comboField_p) {
    // Do nothing.
  }

  /**
   * Set text value.
   * @param text_p
   * @param object_p
   * @param feature_p
   */
  protected void setTextValue(Text text_p, EObject object_p, EStructuralFeature feature_p) {
    Object value = object_p.eGet(feature_p);
    if (value instanceof String) {
      text_p.setText((String) value);
    } else if (value instanceof Integer) {
      text_p.setText(((Integer) value).toString());
    } else if (value instanceof Float) {
      text_p.setText(((Float) value).toString());
    } else {
      text_p.setText(ICommonConstants.EMPTY_STRING);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  protected void setDataValue(final EObject object_p, final EStructuralFeature feature_p, final Object value_p) {
    if (NotificationHelper.isNotificationRequired(object_p, feature_p, value_p)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          if (feature_p instanceof EAttribute) {
            EDataType type = ((EAttribute) feature_p).getEAttributeType();
            if (EcorePackage.eINSTANCE.getEString().equals(type)) {
              object_p.eSet(feature_p, value_p);
            } else if (EcorePackage.eINSTANCE.getEBoolean().equals(type)) {
              object_p.eSet(feature_p, value_p);
            } else if (EcorePackage.eINSTANCE.getEInt().equals(type)) {
              if (value_p instanceof String) {
                try {
                  object_p.eSet(feature_p, new Integer(Integer.parseInt((String) value_p)));
                } catch (NumberFormatException ex) {
                  //
                }
              }
            } else if (EcorePackage.eINSTANCE.getEFloat().equals(type)) {
              if (value_p instanceof String) {
                try {
                  object_p.eSet(feature_p, new Float(Float.parseFloat((String) value_p)));
                } catch (NumberFormatException ex) {
                  //
                }
              }
            }
          } else if (feature_p instanceof EReference) {
            if (((EReference) feature_p).isContainment()) {
              EObject oldValue = (EObject) object_p.eGet(feature_p);
              deleteContainmentValue(oldValue);
            }
            object_p.eSet(feature_p, value_p);
          }
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Add data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  protected void addDataValue(final EObject object_p, final EStructuralFeature feature_p, final Object value_p) {
    if (NotificationHelper.isNotificationRequired(object_p, feature_p, value_p)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public void run() {
          ((List) object_p.eGet(feature_p)).add(value_p);
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Move data value.
   * @param object_p
   * @param owner_p
   * @param feature_p
   */
  @SuppressWarnings("unchecked")
  protected void moveDataValue(final EObject object_p, final EObject owner_p, final EStructuralFeature feature_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        if (feature_p.isMany()) {
          ((List<EObject>) owner_p.eGet(feature_p)).add(object_p);
        } else {
          owner_p.eSet(feature_p, object_p);
        }
      }
    };
    executeCommand(command);
  }

  /**
   * Remove data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  protected void removeDataValue(final EObject object_p, final EStructuralFeature feature_p, final Object value_p) {
    if (NotificationHelper.isNotificationRequired(object_p, feature_p, value_p)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          if ((feature_p instanceof EReference) && ((EReference) feature_p).isContainment()) {
            deleteContainmentValue((EObject) value_p);
          } else {
            ((List<?>) object_p.eGet(feature_p)).remove(value_p);
          }
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Remove all data value from given object for given feature.
   * @param object_p
   * @param feature_p
   */
  @SuppressWarnings("unchecked")
  protected void removeAllDataValue(final EObject object_p, final EStructuralFeature feature_p) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      public void run() {
        if ((feature_p instanceof EReference) && ((EReference) feature_p).isContainment()) {
          List<EObject> containmentList = new ArrayList<EObject>((List<EObject>) object_p.eGet(feature_p));
          for (EObject containedObject : containmentList) {
            deleteContainmentValue(containedObject);
          }
        } else {
          ((List<?>) object_p.eGet(feature_p)).clear();
        }
      }
    };
    executeCommand(command);
  }

  /**
   * @param object_p
   */
  public static void deleteContainmentValue(EObject object_p) {
    CapellaDeleteCommand command =
        new CapellaDeleteCommand(TransactionHelper.getExecutionManager(object_p), Collections.singleton(object_p), false, false, true);
    if (command.canExecute()) {
      command.execute();
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  protected void setDataValue(final EObject object_p, final EStructuralFeature feature_p, final boolean value_p) {
    final Boolean value = Boolean.valueOf(value_p);
    if (NotificationHelper.isNotificationRequired(object_p, feature_p, value)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          object_p.eSet(feature_p, value);
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param button_p
   * @param object_p
   * @param feature_p
   */
  protected void setBooleanValue(Button button_p, EObject object_p, EStructuralFeature feature_p) {
    Object value = object_p.eGet(feature_p);
    if (value instanceof Boolean) {
      setBooleanValue(button_p, ((Boolean) value).booleanValue());
    }
  }

  /**
   * Set data value.
   * @param button_p
   */
  protected void setBooleanValue(Button button_p, boolean value_p) {
    button_p.setSelection(value_p);
  }

  /**
   * Set whether or not this semantic field is enabled or not.<br>
   * Enabled means all internal widgets are set to specified <code>enabled_p</code> value. Default implementation does nothing.
   * @param enabled_p
   */
  public abstract void setEnabled(boolean enabled_p);

  /**
   * Create a button with specified image and no label.
   * @param parent_p
   * @param image_p
   * @param tooltip_p
   * @return a not <code>null</code> instance.
   */
  protected Button createButton(Composite parent_p, Image image_p, String tooltip_p) {
    Button button = _widgetFactory.createButton(parent_p, ICommonConstants.EMPTY_STRING, SWT.PUSH);
    button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
    button.addSelectionListener(this);
    button.setImage(image_p);
    button.setToolTipText(tooltip_p);
    return button;
  }
}
