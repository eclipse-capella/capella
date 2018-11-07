/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.compare.CompareLayoutManager;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This handler allows to compare two diagrams. 
 * Select two diagrams on project explorer, right click and Compare Layout.
 */
public class CompareLayoutHandler extends AbstractUiHandler {

  CompareLayoutManager manager = new CompareLayoutManager();
  
  public CompareLayoutManager getManager() {
    return manager;
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    List<DRepresentationDescriptor> diagrams = new ArrayList<DRepresentationDescriptor>();
    for (Object selection : ((IStructuredSelection)HandlerUtil.getCurrentSelection(event)).toArray()) {
      if (selection instanceof DRepresentationDescriptor) {
        diagrams.add((DRepresentationDescriptor)selection);
      } else  if (selection instanceof ItemWrapper) {
        diagrams.add((DRepresentationDescriptor)((ItemWrapper) selection).getWrappedObject());
      }
    }
    
    DRepresentationDescriptor descriptor = diagrams.get(0);
    DRepresentationDescriptor descriptor2 = diagrams.get(1);
    
    manager = new CompareLayoutManager();
    compare(descriptor, descriptor2, true);
    return null;
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
    Session session = SessionManager.INSTANCE.getSession(diagram1.getTarget());
    
    SessionLayout sessionLayout = LayoutFactory.eINSTANCE.createSessionLayout();
    DiagramLayout layout = manager.getCurrentLayout(session, (DDiagram) diagram1.getRepresentation());
    sessionLayout.getOwnedLayouts().add(layout);

    SessionLayout sessionLayout2 = LayoutFactory.eINSTANCE.createSessionLayout();
    DiagramLayout layout2 = manager.getCurrentLayout(session, (DDiagram) diagram2.getRepresentation());
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
