/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigatorManager;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ui.toolkit.dialogs.IDialog;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.properties.wizards.CustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.ICustomWizardHandler;
import org.polarsys.capella.core.ui.properties.wizards.OpenCustomWizardCommand;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class FinishCapellaWizardTest extends BasicTestCase {

  public static String MODEL_NAME = "NavigatorEmptyProject"; //$NON-NLS-1$
  public static String SYSTEM_ENGINEERING = "22a15265-0e59-4fe2-9398-a3fe1ac41a3e"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  private void activatePropertyView() {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IViewPart propertyView = (IViewPart) activePage.findView("org.eclipse.ui.views.PropertySheet");
    activePage.activate(propertyView);
  }

  private void selectOnNavigatorView(EObject selectedObject) {
    IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    CapellaCommonNavigator navigatorview = (CapellaCommonNavigator) activePage.findView("capella.project.explorer");
    CommonNavigatorManager manager = new CommonNavigatorManager(navigatorview);
    manager.selectionChanged(
        new SelectionChangedEvent(navigatorview.getCommonViewer(), new StructuredSelection(selectedObject)));
  }

  public void testFinishWizard(EObject objToTest) {
    activatePropertyView();
    selectOnNavigatorView(objToTest);

    OpenCustomWizardCommand c = new OpenCustomWizardCommand(objToTest) {
      @Override
      protected ICustomWizardHandler createCustomWizardHandler() {
        return new CustomWizardHandler() {
          @Override
          protected IDialog createWizardDialog(EObject element_p) {
            // mock an IDialog that directly returns the OK code when opened
            return new IDialog() {
              public int open() {
                return Window.OK;
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {

    ICapellaModel model = getTestModel(MODEL_NAME);
    assertNotNull(model);

    IScope scope = new ScopeModelWrapper(model);
    EObject object = IdManager.getInstance().getEObject(SYSTEM_ENGINEERING, scope);
    assertNotNull(object);

    testFinishWizard(object);
  }

}
