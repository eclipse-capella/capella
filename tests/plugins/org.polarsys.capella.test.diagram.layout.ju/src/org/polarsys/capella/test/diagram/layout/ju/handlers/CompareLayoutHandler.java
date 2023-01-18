/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.CompareLayoutManager;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.LayoutMatchPolicy;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This handler allows to compare two diagrams. 
 * 
 * - Select two diagrams on project explorer, right click and Compare Layout.
 * - Select a diagram on project explorer and a layout file, right click and Compare Layout.
 * - Select two layout file, right click and Compare Layout.
 */
public class CompareLayoutHandler extends AbstractUiHandler {

  CompareLayoutManager manager = new CompareLayoutManager();
  
  public CompareLayoutManager getManager() {
    return manager;
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    List<DRepresentationDescriptor> diagrams = new ArrayList<DRepresentationDescriptor>();
    List<IFile> layouts = new ArrayList<>();
    
    for (Object selection : ((IStructuredSelection)HandlerUtil.getCurrentSelection(event)).toArray()) {
      if (selection instanceof DRepresentationDescriptor) {
        diagrams.add((DRepresentationDescriptor)selection);
      } else  if (selection instanceof ItemWrapper) {
        diagrams.add((DRepresentationDescriptor)((ItemWrapper) selection).getWrappedObject());
      } else if (selection instanceof IFile && ((IFile) selection).getFileExtension().equals("layout")) {
        layouts.add((IFile)selection);
      }
    }

    manager = new CompareLayoutManager();
    if (diagrams.size() == 2) {
      DRepresentationDescriptor descriptor = diagrams.get(0);
      DRepresentationDescriptor descriptor2 = diagrams.get(1);
      compare(descriptor, descriptor2, true);
      
    } else if (diagrams.size() == 1 && layouts.size() == 1) {
      compare(diagrams.get(0), layouts.get(0), true);
      
    } else if (layouts.size() == 2) {
      compare(layouts.get(0), layouts.get(1), true);
    }
    
    return null;
  }

  private void compare(DRepresentationDescriptor diagram1, IFile iFile, boolean ui) {
    Session session1 = SessionManager.INSTANCE.getSession(diagram1.getTarget());
    
    SessionLayout sessionLayout = LayoutFactory.eINSTANCE.createSessionLayout();
    DiagramLayout layout = manager.getCurrentLayout(session1, (DDiagram) diagram1.getRepresentation());
    sessionLayout.getOwnedLayouts().add(layout);

    ExecutionManager emanager = ExecutionManagerRegistry.getInstance().addNewManager();
    Resource res = emanager.getEditingDomain().getResourceSet().getResource(EcoreUtil2.getURI(iFile), true);
    SessionLayout sessionLayout2 = (SessionLayout)res.getContents().get(0);
    LayoutMatchPolicy m = new LayoutMatchPolicy();
    emanager.execute(new AbstractReadWriteCommand() {
      
      @Override
      public void run() {
        Iterator<DiagramLayout> l = sessionLayout2.getOwnedLayouts().iterator();
        while (l.hasNext()) {
          DiagramLayout ll = l.next();
          if (!(ll.getId().equals(((CapellaElement)diagram1.getTarget()).getId()) && ll.getName().equals(diagram1.getName()))) {
            l.remove();
          }
        }
      }
    });
    
    if (ui) {
      manager.uiAnalysis(sessionLayout, sessionLayout2);
    } else {
      manager.analysis(sessionLayout, sessionLayout2);
    }
  }

  private void compare(IFile iFile, IFile iFile2, boolean ui) {
    ExecutionManager emanager = ExecutionManagerRegistry.getInstance().addNewManager();
    Resource res = emanager.getEditingDomain().getResourceSet().getResource(EcoreUtil2.getURI(iFile), true);
    SessionLayout sessionLayout = (SessionLayout)res.getContents().get(0);
    
    ExecutionManager emanager2 = ExecutionManagerRegistry.getInstance().addNewManager();
    Resource res2 = emanager2.getEditingDomain().getResourceSet().getResource(EcoreUtil2.getURI(iFile2), true);
    SessionLayout sessionLayout2 = (SessionLayout)res2.getContents().get(0);
    
    if (ui) {
      manager.uiAnalysis(sessionLayout, sessionLayout2);
    } else {
      manager.analysis(sessionLayout, sessionLayout2);
    }
  }
  
  public void copyPasteLayout(DRepresentationDescriptor diagram1, DRepresentationDescriptor diagram2) {
    Session session = SessionManager.INSTANCE.getSession(diagram1.getTarget());
    SessionContext context = new SessionContext(session);
    DiagramContext c = new DiagramContext(context, ((DDiagram)diagram1.getRepresentation()));
    c.open();

    DiagramHelper.copyLayout(c.getDiagram());
    DiagramContext c2 = new DiagramContext(context, ((DDiagram)diagram2.getRepresentation()));
    c2.open();
    DiagramHelper.pasteLayout(c2.getDiagram());
  }
  
  public void compare(DRepresentationDescriptor diagram1, DRepresentationDescriptor diagram2, boolean ui) {
    Session session1 = SessionManager.INSTANCE.getSession(diagram1.getTarget());
    Session session2 = SessionManager.INSTANCE.getSession(diagram2.getTarget());
    
    SessionLayout sessionLayout = LayoutFactory.eINSTANCE.createSessionLayout();
    DiagramLayout layout = manager.getCurrentLayout(session1, (DDiagram) diagram1.getRepresentation());
    sessionLayout.getOwnedLayouts().add(layout);

    SessionLayout sessionLayout2 = LayoutFactory.eINSTANCE.createSessionLayout();
    DiagramLayout layout2 = manager.getCurrentLayout(session2, (DDiagram) diagram2.getRepresentation());
    sessionLayout2.getOwnedLayouts().add(layout2);
    layout2.setId(layout.getId());
    layout2.setName(layout.getName());
    
    if (ui) {
      manager.uiAnalysis(sessionLayout, sessionLayout2);
    } else {
      manager.analysis(sessionLayout, sessionLayout2);
    }
  }
}
