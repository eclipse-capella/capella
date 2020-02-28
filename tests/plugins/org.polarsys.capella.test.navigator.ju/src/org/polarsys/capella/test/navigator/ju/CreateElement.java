/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.navigator.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.menu.dynamic.DynamicActionContributionItem;
import org.polarsys.capella.core.menu.dynamic.DynamicCreationAction;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * This test case create some elements in the project and test that selection is newly created elements
 */
public class CreateElement extends BasicTestCase {

  static final String COMMAND_NAME = UUID.randomUUID().toString();

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("NavigatorEmptyProject");
  }

  @Override
  public void test() throws Exception {
    CapellaModel model = getTestModel(getRequiredTestModels().get(0));
    Session session = getSession(getRequiredTestModels().get(0));
    Project project = model.getProject(session.getTransactionalEditingDomain());
    SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
    final DataPkg pkgRoot = BlockArchitectureExt.getDataPkg(eng.getContainedSystemAnalysis().get(0));

    CommonViewer viewer = getViewer();

    EObject child = createChild(pkgRoot, InformationPackage.Literals.DATA_PKG, viewer);
    assertTrue(child != null);
    assertTrue(!child.equals(pkgRoot));

    EObject subchild = createChild(child, InformationPackage.Literals.CLASS, viewer);
    assertTrue(subchild != null);
    assertTrue(!subchild.equals(child));

    EObject subchild2 = createChild(child, InformationPackage.Literals.CLASS, viewer);
    assertTrue(subchild2 != null);
    assertTrue(!subchild2.equals(child));

    EObject child2 = createChild(pkgRoot, InformationPackage.Literals.CLASS, viewer);
    assertTrue(child2 != null);
    assertTrue(!child2.equals(pkgRoot));

  }

  /**
   * @return
   */
  protected CommonViewer getViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {
        IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .findView(CapellaCommonNavigator.ID);

        viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
      }
    });
    return viewer[0];
  }

  /**
   * Create a customized Add Element menu to be able to retrieve the wanted menu
   */
  protected DynamicCreationAction createAction(Shell shell, ISelectionProvider selectionProvider,
      final EClass wantedClass) {
    return new DynamicCreationAction(shell, selectionProvider) {
      @Override
      protected String getMetaclassLabel(EClass clazz, EObject object) {
        if (wantedClass.equals(clazz)) {
          return COMMAND_NAME;
        }
        return super.getMetaclassLabel(clazz, object);
      }
    };
  }

  /**
   * Create a child into source of the given eClass using the Add Element menu
   */
  protected EObject createChild(final EObject source, final EClass clazz, final CommonViewer viewer) {
    Display.getDefault().syncExec(new Runnable() {

      @Override
      public void run() {

        DynamicCreationAction actions = createAction(viewer.getTree().getShell(), viewer, clazz);

        ISelection selection = new StructuredSelection(source);
        viewer.setSelection(selection);

        if (actions.isSelectionCompatible()) {
          Collection<IContributionItem> items = new ArrayList<IContributionItem>();
          items.addAll(actions.getStructuralDynamicActions());
          items.addAll(actions.getNonStructuralDynamicActions());
          items.addAll(actions.getExtensionDynamicActions());

          for (IContributionItem item : items) {

            if (item instanceof DynamicActionContributionItem) {
              if (((DynamicActionContributionItem) item).getText().contains(COMMAND_NAME)) {
                ((DynamicActionContributionItem) item).getAction().run();
                return;
              }
            }
          }
        }
      }
    });

    GuiActions.flushASyncGuiJobs();
    ISelection selection3 = viewer.getSelection();
    EObject a = (EObject) ((IStructuredSelection) selection3).getFirstElement();
    return a;
  }

}
