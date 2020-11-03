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
package org.polarsys.capella.test.navigator.ju;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.window.Window;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ui.toolkit.dialogs.IDialog;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.ui.properties.wizards.CustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.ICustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.OpenCustomWizardCommand;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Test if CustomWizardHandler generates errors in the error log in case the CapellaWizard it will open is cancelled.
 */
public class CancelCapellaWizardTest extends BasicTestCase {

  @Override
  public void test() throws Exception {

    // Delete all errors after opening the platform
    Platform.getLogFileLocation().toFile().delete();

    OpenCustomWizardCommand c = new OpenCustomWizardCommand(
        CapellamodellerFactory.eINSTANCE.createSystemEngineering()) {
      @Override
      protected ICustomWizardHandler createCustomWizardHandler() {
        return new CustomWizardHandler() {
          @Override
          protected IDialog createWizardDialog(EObject element_p) {

            // mock an IDialog that directly returns the Cancel code when opened
            return new IDialog() {
              public int open() {
                return Window.CANCEL;
              }

              public boolean close() {
                return true;
              }
            };
          }
        };
      }
    };

    ExecutionManager em = ExecutionManagerRegistry.getInstance().addNewManager();
    em.execute(c);
    ExecutionManagerRegistry.getInstance().removeManager(em);

    assertEquals(true, c.isCanceled());

    // no errors should have been logged by the platform.
    assertEquals(0, Platform.getLogFileLocation().toFile().length());
  }

}
