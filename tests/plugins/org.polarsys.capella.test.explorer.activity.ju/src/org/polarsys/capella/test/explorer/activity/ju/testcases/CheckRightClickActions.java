/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.ActivityExplorerEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.forms.editor.IFormPage;
import org.polarsys.capella.core.explorer.activity.ui.pages.AbstractCapellaPage;
import org.polarsys.capella.core.explorer.activity.ui.pages.EpbsPage;
import org.polarsys.capella.core.explorer.activity.ui.pages.LogicalArchitecturePage;
import org.polarsys.capella.core.explorer.activity.ui.pages.OperationalAnalysisPage;
import org.polarsys.capella.core.explorer.activity.ui.pages.PhysicalArchitecturePage;
import org.polarsys.capella.core.explorer.activity.ui.pages.SystemAnalysisPage;
import org.polarsys.capella.core.explorer.activity.ui.viewer.CapellaDiagramViewer;
import org.polarsys.capella.core.explorer.activity.ui.viewer.CapellaDiagramViewer.ShowInProjectExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CloneAction;
import org.polarsys.capella.core.sirius.ui.actions.DeleteRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.RenameRepresentationAction;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

/**
 * Tests if the Diagram Viewer in the Activity Explorer contains all the expected registered right click actions on
 * diagrams.
 */
public class CheckRightClickActions extends BasicTestCase {
  private ActivityExplorerEditor activityExplorer;

  public static final String TEST_PROJECT_NAME = "EmptyModel";

  /**
   * The Activity Explorer Page ids that will be tested.
   */
  private static final List<String> ACTIVITY_EXPLORER_PAGE_IDS = Arrays.asList(OperationalAnalysisPage.PAGE_ID,
      SystemAnalysisPage.PAGE_ID, LogicalArchitecturePage.PAGE_ID, PhysicalArchitecturePage.PAGE_ID, EpbsPage.PAGE_ID);

  /**
   * The expected Action classes that are supposed to be registered in the Diagram Viewer.
   */
  private static final List<Class<? extends BaseSelectionListenerAction>> EXPECTED_ACTION_CLASSES = Arrays.asList(
      OpenRepresentationsAction.class, RenameRepresentationAction.class, ShowInProjectExplorerAction.class,
      CloneAction.class, DeleteRepresentationAction.class);

  private static final String ERROR_MSG_TEMPLATE = "There is no action of class [%s] present for the page [%s]";

  @Override
  public List<String> getRequiredTestModels() {
    return Collections.singletonList(TEST_PROJECT_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(TEST_PROJECT_NAME);
    assertNotNull(session);

    IFile airdFile = getAirdFileForLoadedModel(TEST_PROJECT_NAME);
    GuiActions.launchOpenActivityExplorerAction(airdFile);

    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    assertTrue(activeEditor instanceof ActivityExplorerEditor);

    activityExplorer = (ActivityExplorerEditor) activeEditor;
  }

  @Override
  public void test() throws Exception {

    List<String> errorMessages = new ArrayList<>();

    for (String pageId : ACTIVITY_EXPLORER_PAGE_IDS) {
      IFormPage activePage = activityExplorer.setActivePage(pageId);
      assertTrue(activePage instanceof AbstractCapellaPage);

      AbstractCapellaPage activeCapellaPage = (AbstractCapellaPage) activePage;
      assertRightClickMenuActions(activeCapellaPage, errorMessages);
    }

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  private void assertRightClickMenuActions(AbstractCapellaPage page, List<String> errorMessages) {
    Optional<CapellaDiagramViewer> capellaDiagramViewerOptional = page.getCapellaDiagramViewer();
    assertTrue(capellaDiagramViewerOptional.isPresent());

    CapellaDiagramViewer capellaDiagramViewer = capellaDiagramViewerOptional.get();
    MenuManager menuManager = capellaDiagramViewer.getMenuManager();

    List<IAction> registeredActions = extractRegisteredActions(menuManager);

    for (Class<? extends BaseSelectionListenerAction> expectedActionClass : EXPECTED_ACTION_CLASSES) {

      Optional<IAction> registeredAction = registeredActions.stream()
          .filter(action -> expectedActionClass.isInstance(action))//
          .findAny();

      if (!registeredAction.isPresent()) {
        String errorMessage = String.format(ERROR_MSG_TEMPLATE, expectedActionClass, page);
        errorMessages.add(errorMessage);
      }
    }
  }

  private List<IAction> extractRegisteredActions(MenuManager menuManager) {
    return Arrays.stream(menuManager.getItems())//
        .filter(ActionContributionItem.class::isInstance) //
        .map(ActionContributionItem.class::cast) //
        .map(ActionContributionItem::getAction) //
        .collect(Collectors.toList());
  }

}
