/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.descriptions.fields;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.helpers.NotificationHelper;

/**
 * A customized description group.
 * 
 * @author Joao Barata
 */
public class CapellaElementDescriptionGroup extends ElementDescriptionGroup {

  /**
   * @param parent
   * @param widgetFactory
   */
  public CapellaElementDescriptionGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory);
  }

  /**
   * Called by the section refresh coming from wherever.
   */
  public void loadData(EObject element) {
    loadData(element, CapellacorePackage.eINSTANCE.getCapellaElement_Description());
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object
   * @param feature
   * @param value
   */
  @Override
  protected void setDataValue(final EObject object, final EStructuralFeature feature, final Object value) {
    if (NotificationHelper.isNotificationRequired(object, feature, value)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          object.eSet(feature, value);
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Execute given command.
   * @param command
   */
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
}
