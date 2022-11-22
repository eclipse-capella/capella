/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.core.commands.Command;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.PopupMenuExtender;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.NavigatorActionService;
import org.eclipse.ui.services.IServiceLocator;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ShowInPropertiesDialogTest extends BasicTestCase {

  protected final String CAPELLA_PERSPECTIVE_ID = "capella.sirius.perspective";
  protected final String SHOW_IN_PROPERTIES_DIALOG_COMMAND_ID = "org.polarsys.capella.core.ui.properties.showInPropertiesDialog";
  protected NavigatorActionService _actionService;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("NavigableElements");
  }

  //This test ensure that the "show in Properties Dialog" command is in the popup menu 
  //Ensures that the command properly opens a CapellaWizardDialog
  @Override
  public void test() throws Exception {
    IStructuredSelection navigatorSelection = null;

    try {

      CapellaModel model = getTestModel(getRequiredTestModels().get(0));
      Session session = getSession(getRequiredTestModels().get(0));
      Project project = model.getProject(session.getTransactionalEditingDomain());
      SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
      final SystemAnalysis systemAnalysisPkg = eng.getContainedSystemAnalysis().get(0);
      SystemFunctionPkg systemFunctionPkg = systemAnalysisPkg.getContainedSystemFunctionPkg();
      SystemFunction rootSystemFunction = systemFunctionPkg.getOwnedSystemFunctions().get(0);
      navigatorSelection = new StructuredSelection(rootSystemFunction);
    } catch (Exception e) {
      fail("Couldn't find model elements");
    }

    IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    if(activeWorkbenchWindow == null) {
      fail("No Active workbench window");
    }


    try {
      //Ensure that the welcome page is closed
      closeIntro();
    }catch(Exception E) {
      fail("Could not close intro page");
    }

    try {
      PlatformUI.getWorkbench().showPerspective(CAPELLA_PERSPECTIVE_ID, activeWorkbenchWindow);
    } catch(Exception e) {
      fail("Could not open Capella perspective");
    }

    //Set Selection t viewer
    CommonViewer viewer = getViewer();
    CapellaCommonNavigator navigator = (CapellaCommonNavigator) viewer.getCommonNavigator();   
    navigator.selectReveal(navigatorSelection);
    viewer.setSelection(navigatorSelection);

    MenuManager menuMgr = new MenuManager();
    navigator.getNavigatorActionService().fillContextMenu(menuMgr);

    //Create PopupMenuExtender to populate the contextual menu
    PopupMenuExtender extender = new PopupMenuExtender("capella.project.explorer#PopupMenu", menuMgr, viewer, navigator, ((PartSite)navigator.getSite()).getContext());

    Menu contextMenu = menuMgr.createContextMenu(viewer.getControl());
    contextMenu.notifyListeners(SWT.Show, null);

    IConfigurationElement extension = null;

    IConfigurationElement[] config = Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.eclipse.ui.commands");
    for(IConfigurationElement element : config) {
      String id = element.getAttribute("id");
      if(id != null) {
        if(id.equals(SHOW_IN_PROPERTIES_DIALOG_COMMAND_ID)) {
          extension = element;
          break;
        }
      }
    }
    
    if( extension == null) {
      fail("Couldn't find command");
    }

    IContributionItem item = null;
    for(IContributionItem contribution : menuMgr.getItems()) {
      if(SHOW_IN_PROPERTIES_DIALOG_COMMAND_ID.equals(contribution.getId())) {
        item = contribution;
        break;
      }
    }
    if(item == null) {
      fail("Couldn't find command in contextual menu");
    }
   

    // Retrieve the corresponding Services
    IHandlerService handlerService = (IHandlerService) ((IServiceLocator) PlatformUI.getWorkbench())
        .getService(IHandlerService.class);
    ICommandService commandService = (ICommandService) ((IServiceLocator) PlatformUI.getWorkbench())
        .getService(ICommandService.class);

    // Retrieve the command
    Command showInPropertiesDialogCommand = commandService.getCommand(SHOW_IN_PROPERTIES_DIALOG_COMMAND_ID);
    if (showInPropertiesDialogCommand == null) {
      fail("Couldn't find command in command service");
    }
  }



  /**
   * @return
   */
  protected CommonViewer getViewer() {
    final CommonViewer[] viewer = new CommonViewer[1];
    IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
        .findView(CapellaCommonNavigator.ID);

    viewer[0] = ((CapellaCommonNavigator) part).getCommonViewer();
    return viewer[0];
  }  

  protected void closeIntro() {
    IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
    PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
  }
}
