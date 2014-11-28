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
   * @param parent_p
   * @param widgetFactory_p
   */
  public CapellaElementDescriptionGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p);
  }

  /**
   * Called by the section refresh coming from wherever.
   */
  public void loadData(EObject element_p) {
    loadData(element_p, CapellacorePackage.eINSTANCE.getCapellaElement_Description());
  }

  /**
   * Set data value i.e change given object for given feature with specified value.
   * @param object_p
   * @param feature_p
   * @param value_p
   */
  @Override
  protected void setDataValue(final EObject object_p, final EStructuralFeature feature_p, final Object value_p) {
    if (NotificationHelper.isNotificationRequired(object_p, feature_p, value_p)) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        public void run() {
          object_p.eSet(feature_p, value_p);
        }
      };
      executeCommand(command);
    }
  }

  /**
   * Execute given command.
   * @param command_p
   * @param feature_p
   */
  protected void executeCommand(final ICommand command_p) {
    // Precondition
    if ((null == command_p)) {
      return;
    }
    // Command to run.
    ICommand command = command_p;
    // Encapsulate given command in a new one to enable undo / redo refresh based on getAffectedObjects.
    // AbstractSection call getAffectedObjects() against the command provided by the OperationHistory.
    if (command_p instanceof AbstractReadWriteCommand) {
      command = new AbstractReadWriteCommand() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          command_p.run();
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
    TransactionHelper.getExecutionManager(_semanticElement).execute(command);
  }
}
