/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.ui.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.commands.preferences.preferences.CapellaDiagramPreferences;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.ui.semantic.browser.handler.AbstractSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.handlers.NavigationSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test case create double click on a representation in the left panel (referencing elements) of the semantic
 * browser view.
 */
public class SemanticBrowserCurrentElementNavigationTest extends BasicTestCase {

  protected final String CAPELLA_PERSPECTIVE_ID = "capella.sirius.perspective";
  protected final String SEMANTIC_BROWSER_VIEW_ID = "org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserID";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SemanticBrowserNavigation");
  }

  @Override
  public void test() throws Exception {

    Capability capability = null;

    try {

      CapellaModel model = getTestModel(getRequiredTestModels().get(0));
      Session session = getSession(getRequiredTestModels().get(0));
      Project project = model.getProject(session.getTransactionalEditingDomain());
      SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
      final SystemAnalysis systemAnalysisPkg = eng.getContainedSystemAnalysis().get(0);
      CapabilityPkg capabilityPkg = (CapabilityPkg) systemAnalysisPkg.getOwnedAbstractCapabilityPkg();
      capability = capabilityPkg.getOwnedCapabilities().get(0);
      
    } catch (Exception e) {
      fail("Model Elements could not be found");
    }

    IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    if (activeWorkbenchWindow == null) {
      fail("No Active workbench window");
    }

    try {
      // Ensure that the welcome page is closed
      closeIntro();
    } catch (Exception E) {
      fail("Could not close intro page");
    }

    try {
      PlatformUI.getWorkbench().showPerspective(CAPELLA_PERSPECTIVE_ID, activeWorkbenchWindow);
    } catch (Exception e) {
      fail("Could not open Capella perspective");
    }

    IViewPart semanticBrowserViewpart = activeWorkbenchWindow.getActivePage().showView(SEMANTIC_BROWSER_VIEW_ID);
    if (semanticBrowserViewpart != null) {

      SiriusSemanticBrowserView semanticBrowserView = (SiriusSemanticBrowserView) semanticBrowserViewpart;
      semanticBrowserView.setInput(capability);
      
      TreeViewer viewer = semanticBrowserView.getCurrentViewer();
      Tree viewTree = viewer.getTree();
      viewer.expandAll();
      
      TreeItem[] items = viewTree.getItems(); 

      EObjectWrapper fcWrapper = (EObjectWrapper)items[0].getItems()[0].getItems()[0].getData();
      FunctionalChain fc = (FunctionalChain) fcWrapper.getElement();
      
      
      EObjectWrapper scWrapper = (EObjectWrapper)items[0].getItems()[1].getItems()[0].getData();
      Scenario sc = (Scenario) scWrapper.getElement();
      
      EObjectWrapper repWrapper = (EObjectWrapper)items[0].getItems()[2].getItems()[0].getData();
      DRepresentationDescriptor rep = (DRepresentationDescriptor) repWrapper.getElement();
      
      // With navigation preference set to TRUE
      
      //Double click on a Capability (not navigable, not a diagram) We expect to open the wizard
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), capability, false, true, false);
      
      //Double click on a Functional Chain (navigable, not a diagram) We expect to navigate
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), fc, true, false, false);
      //Double click on a Scenario (navigable, not a diagram) We expect to navigate
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), sc, true, false, false);
      //Double click on a Representation (a diagram) We expect to open the diagram
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), rep, false, false, true);
      
      // With navigation preference set to FALSE
      Activator.getDefault().getPreferenceStore().setValue(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, false);
      
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), capability, false, true, false);
      
      //We expect to open the wizard on element that were navigable
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), fc, false, true, false);      
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), sc, false, true, false);
      
      testDoubleClickBehaviors(semanticBrowserView, semanticBrowserView.getCurrentViewer(), rep, false, false, true);
      
      Activator.getDefault().getPreferenceStore().setValue(CapellaDiagramPreferences.PREF_DISPLAY_NAVIGATE_ON_DOUBLE_CLICK, true);
                  
    } else {
      fail("Could not open Semantic Browser View");
    }
  }

  protected void testDoubleClickBehaviors(SiriusSemanticBrowserView view, Viewer source, EObject element, boolean expectedNavigationBehavior, boolean expectedOpenWizardBehavior, boolean expectedOpenDiagramBehavior) {
    DoubleClickEvent event = new DoubleClickEvent(source,new StructuredSelection(element));
    AbstractSemanticBrowserDoubleClickHandler handler = view.getSemanticBrowserDoubleClickHandlerFor(event);
    
    if( ! (handler instanceof NavigationSemanticBrowserDoubleClickHandler)) {
      fail("the doubleClick handler should be an instanceof NavigationSemanticBrowserDoubleClickHandler");
    }
    NavigationSemanticBrowserDoubleClickHandler doubleClickHandler = (NavigationSemanticBrowserDoubleClickHandler) handler;
    
    assertEquals(expectedNavigationBehavior, doubleClickHandler.shouldNavigate(element));    
    assertEquals(expectedOpenWizardBehavior, doubleClickHandler.shouldOpenPropertyWizard(element));
    assertEquals(expectedOpenDiagramBehavior, doubleClickHandler.shouldOpenDiagram(element));
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

  protected Event createDoubleClickEvent(Widget sourceWidget, Display display, Widget selectedItem) {
    Event event = new Event();
    event.type = SWT.DefaultSelection;
    event.button = 0;
    event.widget = sourceWidget;
    event.display = display;
    event.item = selectedItem;
    event.doit = true;

    return event;
  }
  
  /**
   * Wait the end of the asynchronous calls waiting in UI thread.
   */
  protected static void synchronizationWithUIThread() {
      while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
          // Do nothing, just wait
      }
  }
}
