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
package org.polarsys.capella.test.semantic.ui.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test case create double click on a representation in the left panel (referencing elements) of the semantic browser view.
 */
public class SemanticBrowserReferencingElementNavigationTest extends BasicTestCase {

  protected final String CAPELLA_PERSPECTIVE_ID = "capella.sirius.perspective";
  protected final String SEMANTIC_BROWSER_VIEW_ID = "org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserID";
  
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("SemanticBrowserNavigation");
  }

  @Override
  public void test() throws Exception {
    
    Scenario scenarioToBeSelectedInNavigator = null;
    Scenario scenarioToSelectRepresentationFrom = null;   
    
    IStructuredSelection navigatorSelection = null;
    IStructuredSelection semanticBrowserSelection = null;
    
    try {
          
      CapellaModel model = getTestModel(getRequiredTestModels().get(0));
      Session session = getSession(getRequiredTestModels().get(0));
      Project project = model.getProject(session.getTransactionalEditingDomain());
      SystemEngineering eng = ((SystemEngineering) (project.getOwnedModelRoots().get(0)));
      final SystemAnalysis systemAnalysisPkg = eng.getContainedSystemAnalysis().get(0);
      CapabilityPkg capabilityPkg = (CapabilityPkg) systemAnalysisPkg.getOwnedAbstractCapabilityPkg();
      Capability capability = capabilityPkg.getOwnedCapabilities().get(0);
    
    
      scenarioToSelectRepresentationFrom = capability.getOwnedScenarios().get(0);
      Collection<DRepresentationDescriptor> descriptors = RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(Collections.singleton(scenarioToSelectRepresentationFrom));
      DRepresentationDescriptor representationToBeSelectedInSemanticBrowser = descriptors.iterator().next();
      semanticBrowserSelection = new StructuredSelection(new EObjectWrapper(representationToBeSelectedInSemanticBrowser));
      
      scenarioToBeSelectedInNavigator = capability.getOwnedScenarios().get(1);
      navigatorSelection = new StructuredSelection(scenarioToBeSelectedInNavigator);
     
    } catch (Exception e) {
      fail("Model Elements could not be found");
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
    
    CommonViewer viewer = getViewer();
    viewer.getCommonNavigator().selectReveal(navigatorSelection);
        
    
    IViewPart semanticBrowserViewpart = activeWorkbenchWindow.getActivePage().showView(SEMANTIC_BROWSER_VIEW_ID);
    if(semanticBrowserViewpart != null) {
      
      SemanticBrowserView semanticBrowserView = (SemanticBrowserView) semanticBrowserViewpart;
      semanticBrowserView.setInput(scenarioToBeSelectedInNavigator);      
       
      
      TreeViewer referencingViewer = semanticBrowserView.getReferencingViewer();
      Tree referencingViewerTree = referencingViewer.getTree();      
      
      
      referencingViewer.expandAll();
      TreeItem[] items = referencingViewer.getTree().getItems();
      TreeItem toSelect = items[0].getItems()[0];
      referencingViewer.setSelection(semanticBrowserSelection);

      Display display = referencingViewer.getControl().getDisplay();
      
      
      Event doubleClickEvent = createDoubleClickEvent(referencingViewerTree, display, toSelect);      
           
      Listener[] listeners = referencingViewerTree.getListeners(SWT.DefaultSelection);
      if(listeners == null || listeners[0] == null) fail("Could not find a double click listener");
      
      Listener listener = listeners[0];
      
      listener.handleEvent(doubleClickEvent);      
      
      IEditorPart editor = EclipseUIUtil.getActiveEditor();
      assertTrue("We should have a DDiagramEditor", editor instanceof DDiagramEditor);

    } else {
      fail("Could not open Semantic Browser View");
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
  
  protected Event createDoubleClickEvent(Widget sourceWidget, Display display, Widget selectedItem) {
    Event event = new Event();
    event.type = SWT.DefaultSelection;
    event.button = 0 ;
    event.widget = sourceWidget;
    event.display = display;
    event.item = selectedItem;
    event.doit = true;
    
    return event;
  }
}
