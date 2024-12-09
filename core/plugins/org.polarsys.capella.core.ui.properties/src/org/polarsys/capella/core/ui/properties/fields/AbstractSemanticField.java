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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;

/**
 */
public abstract class AbstractSemanticField implements SelectionListener, FocusListener, KeyListener {
  /**
   * Current edited semantic element.
   */
  protected EObject semanticElement;
  /**
   * Handle semantic element's feature handled by this field.
   */
  protected EStructuralFeature semanticFeature;

  /**
   * The widget factory
   */
  protected TabbedPropertySheetWidgetFactory widgetFactory;

  /**
   * Whether or not the field is displayed in a wizard.
   */
  private boolean displayedInWizard;

  /**
   * Constructor.
   */
  protected AbstractSemanticField(TabbedPropertySheetWidgetFactory widgetFactory) {
    this.widgetFactory = widgetFactory;
    this.displayedInWizard = false;
  }

  /**
   * Is this field displayed in a wizard ?
   * 
   * @return the displayedInWizard
   */
  public boolean isDisplayedInWizard() {
    return displayedInWizard;
  }

  /**
   * Set whether or not the field is displayed in a wizard.
   * 
   * @param displayedInWizard
   *          the displayedInWizard to set
   */
  public void setDisplayedInWizard(boolean displayedInWizard) {
    this.displayedInWizard = displayedInWizard;
  }

  /**
   * Execute given command.
   * 
   * @param command
   */
  protected void executeCommand(ICommand command) {
    // Precondition
    if ((null == command)) {
      return;
    }
    // Command to run.
    ICommand cmd = command;
    if (isDisplayedInWizard()) {
      // In wizards, we are already running within a TIG read/write command.
      // To be able to cancel modifications we need to run given command (i.e the one specified as argument) as a non
      // dirtying one.
      // Indeed, we don't want to have these sub commands on the command stack, only the main one would be stacked.
      // Thus, let's create a non dirtying that is used as a proxy of given one.
      cmd = createNonDirtyingCommand(command);
    } else {
      // Encapsulate given command in a new one to enable undo / redo refresh based on getAffectedObjects.
      // AbstractSection call getAffectedObjects() against the command provided by the OperationHistory.
      if (command instanceof AbstractReadWriteCommand) {
        cmd = createReadWriteCommand(command);
      }
    }
    // Execute it against the TED.
    verifiedExecution(cmd);
  }

  /**
   * Retrieve the execution manager
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(semanticElement);
  }

  /**
   * This method verifies if there is an active transaction.<br>
   * If such case, the command is simply run.<br>
   * If not, the command is executed through the execution manager.<br>
   * 
   * @param command
   *          the command to be executed
   */
  protected void verifiedExecution(ICommand command) {
    ExecutionManager executionManager = getExecutionManager();
    if (null != executionManager) {
      TransactionalEditingDomain editingDomain = executionManager.getEditingDomain();
      if (editingDomain instanceof InternalTransactionalEditingDomain) {
        InternalTransaction activeTransaction = ((InternalTransactionalEditingDomain) editingDomain)
            .getActiveTransaction();
        if ((null != activeTransaction) && activeTransaction.isActive()) {
          command.run();
        } else {
          executionManager.execute(command);
        }
      }
    }
  }

  /**
   * @param command
   */
  private ICommand createReadWriteCommand(final ICommand command) {
    return new AbstractReadWriteCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        command.run();
      }

      /**
       * @see org.polarsys.capella.common.ef.command.AbstractCommand#getAffectedObjects()
       */
      @Override
      public Collection<?> getAffectedObjects() {
        return Collections.singletonList(semanticElement);
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
        loadData(semanticElement);
      }
    };
  }

  /**
   * @param command
   */
  private ICommand createNonDirtyingCommand(final ICommand command) {
    return new AbstractNonDirtyingCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        command.run();
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
        loadData(semanticElement);
      }
    };
  }

  /**
   * Load the field for given element and given feature.
   * 
   * @param semanticElement
   * @param semanticFeature
   */
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    this.semanticElement = semanticElement;
    this.semanticFeature = semanticFeature;
  }

  /**
   * Load the UI representation of given semantic element.
   * 
   * @param semanticElement
   */
  public abstract void loadData(EObject semanticElement);

  /**
   * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
   */
  @Override
  public void focusGained(final FocusEvent event) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
   */
  @Override
  public void focusLost(FocusEvent event) {
    if (event != null) {
      Object source = event.getSource();
      if (source instanceof Text) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent event) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent event) {
    if (event != null && (displayedInWizard || event.character == SWT.CR)) {
      // this field can be used in two different contexts:
      // - in the property view: we don't want to create a command for each modification (unless it's a CR character)
      // - in a wizard dialog: all the modifications will be embedded in a single command
      Object source = event.getSource();
      if (source instanceof Text) {
        fillTextField((Text) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event) {
    if (event != null) {
      Object source = event.getSource();
      if (source instanceof CCombo) {
        fillComboField((CCombo) source);
      }
    }
  }

  /**
   * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetDefaultSelected(SelectionEvent event) {
    // Do nothing.
  }

  /**
   * Fill given text field.<br>
   * Default behavior does nothing.
   * 
   * @param textField
   *          text field to be filled
   */
  protected void fillTextField(Text textField) {
    // Do nothing.
  }

  /**
   * Fill given combo field.<br>
   * Default behavior does nothing.
   * 
   * @param CCombo
   *          combo field to be filled
   */
  protected void fillComboField(CCombo comboField) {
    // Do nothing.
  }

  /**
   * Set text value.
   * 
   * @param text
   * @param object
   * @param feature
   */
  protected void setTextValue(Text text, EObject object, EStructuralFeature feature) {
    Object value = object.eGet(feature);
    if (value instanceof String) {
      text.setText((String) value);
    } else if (value instanceof Integer) {
      text.setText(((Integer) value).toString());
    } else if (value instanceof Float) {
      text.setText(((Float) value).toString());
    } else if (value instanceof Double) {
      text.setText(((Double) value).toString());
    } else {
      text.setText(ICommonConstants.EMPTY_STRING);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * 
   * @param object
   * @param feature
   * @param value
   */
  protected void setDataValue(final EObject object, final EStructuralFeature feature, final Object value) {
    if (NotificationHelper.isNotificationRequired(object, feature, value)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          if (feature instanceof EAttribute) {
            EDataType type = ((EAttribute) feature).getEAttributeType();
            if (EcorePackage.eINSTANCE.getEString().equals(type)) {
              object.eSet(feature, value);
            } else if (EcorePackage.eINSTANCE.getEBoolean().equals(type)) {
              object.eSet(feature, value);
            } else if (EcorePackage.eINSTANCE.getEInt().equals(type)) {
              if (value instanceof String) {
                try {
                  object.eSet(feature, Integer.valueOf(Integer.parseInt((String) value)));
                } catch (NumberFormatException ex) {
                  //
                }
              }
            } else if (EcorePackage.eINSTANCE.getEFloat().equals(type)) {
              if (value instanceof String) {
                try {
                  object.eSet(feature, new Float(Float.parseFloat((String) value)));
                } catch (NumberFormatException ex) {
                  //
                }
              }
            } else if (EcorePackage.eINSTANCE.getEDouble().equals(type)) {
              if (value instanceof String) {
                try {
                  object.eSet(feature, new Double(Double.parseDouble((String) value)));
                } catch (NumberFormatException ex) {
                  //
                }
              }
            }
          } else if (feature instanceof EReference) {
            if (((EReference) feature).isContainment()) {
              EObject oldValue = (EObject) object.eGet(feature);
              deleteContainmentValue(oldValue);
            }
            object.eSet(feature, value);
          }
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Add data value i.e change given object for given feature with specified value.
   * 
   * @param object
   *          The EMF EObject to which you want to add the element.
   * @param feature
   *          The multivalued feature of the EObject to which you want to add the element.
   * @param value
   *          The value to add to the feature of the object.
   * @return The command if available, <code>Optional.empty</code> otherwise
   */
  protected Optional<AbstractReadWriteCommand> createAddDataValueCommand(final EObject object,
      final EStructuralFeature feature, final Object value) {
    if (NotificationHelper.isNotificationRequired(object, feature, value)) {
      return Optional.of(new AbstractReadWriteCommand() {
        @Override
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public void run() {
          ((List) object.eGet(feature)).add(value);
        }
      });
    } else {
      return Optional.empty();
    }
  }

  /**
   * Move data value.
   * 
   * @param object
   *          The object to move
   * @param owner
   *          On which you want to move the object
   * @param feature
   *          The owner feature in which you want to move the object.
   */
  @SuppressWarnings("unchecked")
  protected Optional<AbstractReadWriteCommand> createMoveDataValueCommand(final EObject object, final EObject owner,
      final EStructuralFeature feature) {
    return Optional.of(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        if (feature.isMany()) {
          ((List<EObject>) owner.eGet(feature)).add(object);
        } else {
          owner.eSet(feature, object);
        }
      }
    });
  }

  /**
   * Remove data value i.e change given object for given feature with specified value.
   * 
   * @param object
   *          The EMF EObject to which you want to remove the element.
   * @param feature
   *          The multivalued feature of the EObject to which you want to remove the element.
   * @param value
   *          The value to remove to the feature of the object.
   * @return The command if available, <code>Optional.empty</code> otherwise
   */
  protected Optional<AbstractReadWriteCommand> createRemoveDataValueCommand(final EObject object,
      final EStructuralFeature feature, final Object value) {
    if (NotificationHelper.isNotificationRequired(object, feature, value)) {
      return Optional.of(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          if ((feature instanceof EReference) && ((EReference) feature).isContainment()) {
            deleteContainmentValue((EObject) value);
          } else {
            ((List<?>) object.eGet(feature)).remove(value);
          }
        }
      });
    } else {
      return Optional.empty();
    }
  }

  /**
   * Remove all data value from given object for given feature.
   * 
   * @param object
   * @param feature
   */
  @SuppressWarnings("unchecked")
  protected void removeAllDataValue(final EObject object, final EStructuralFeature feature) {
    AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        if ((feature instanceof EReference) && ((EReference) feature).isContainment()) {
          List<EObject> containmentList = new ArrayList<>((List<EObject>) object.eGet(feature));
          for (EObject containedObject : containmentList) {
            deleteContainmentValue(containedObject);
          }
        } else {
          ((List<?>) object.eGet(feature)).clear();
        }
      }
    };
    executeCommand(command);
  }

  /**
   * @param object
   */
  public static void deleteContainmentValue(EObject object) {
    CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(object),
        Collections.singleton(object), false, false, true);
    if (command.canExecute()) {
      command.execute();
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * 
   * @param object
   * @param feature
   * @param value
   */
  protected void setDataValue(final EObject object, final EStructuralFeature feature, final boolean value) {
    final Boolean val = Boolean.valueOf(value);
    if (NotificationHelper.isNotificationRequired(object, feature, val)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          object.eSet(feature, val);
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * 
   * @param button
   * @param object
   * @param feature
   */
  protected void setBooleanValue(Button button, EObject object, EStructuralFeature feature) {
    Object value = object.eGet(feature);
    if (value instanceof Boolean) {
      setBooleanValue(button, ((Boolean) value).booleanValue());
    }
  }

  /**
   * Set data value.
   * 
   * @param button
   */
  protected void setBooleanValue(Button button, boolean value) {
    button.setSelection(value);
  }

  /**
   * Set whether or not this semantic field is enabled or not.<br>
   * Enabled means all internal widgets are set to specified <code>enabled</code> value. Default implementation does
   * nothing.
   * 
   * @param enabled
   */
  public abstract void setEnabled(boolean enabled);

  /**
   * Create a button with specified image and no label.
   * 
   * @param parent
   * @param image
   * @param tooltip
   * @return a not <code>null</code> instance.
   */
  protected Button createButton(Composite parent, Image image, String tooltip) {
    Button button = widgetFactory.createButton(parent, ICommonConstants.EMPTY_STRING, SWT.PUSH);
    button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
    button.addSelectionListener(this);
    button.setImage(image);
    button.setToolTipText(tooltip);
    return button;
  }
}
