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
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.styles.StyleConfigurationRegistry;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class DiagramPartIcon extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    Part system = (Part)((Component)context.getSemanticElement(SA__SAB_SYSTEM)).getAbstractTypedElements().get(0);
    XABDiagram sab = XABDiagram.openDiagram(context, SA__SAB_DIAGRAM, BlockArchitectureExt.Type.SA);
    checkIcons(sab, (DDiagramElement)sab.getView(system));

    Part lsystem = (Part) context.getSemanticElement(LA__LAB_LOGICAL_SYSTEM_PART);
    XABDiagram lab = XABDiagram.openDiagram(context, LA__LAB_DIAGRAM, BlockArchitectureExt.Type.LA);
    checkIcons(lab, (DDiagramElement)lab.getView(lsystem));

    PABDiagram pab = PABDiagram.openDiagram(context, PA__PAB_DIAGRAM);
    pab.clearDiagram();
    pab.createNodeComponent("n", pab.getDiagramId());
    pab.createDeployedBehaviorComponent("b", "n");
    checkIcons(pab, (DDiagramElement) pab.getView("n"));
    checkIcons(pab, (DDiagramElement) pab.getView("b"));
    
  }

  private void checkIcons(XABDiagram diagram, DDiagramElement view) {
    StyleConfiguration c = StyleConfigurationRegistry.getInstance().getStyleConfiguration(view.getDiagramElementMapping(), view.getStyle());

    //Mono part
    updateApproach(ProjectApproach.SingletonComponents, diagram.getDiagramDescriptor().getTarget());
    Image imageCpt = c.getLabelIcon(view, null);
    Image imageCpt2 = c.getLabelIcon(view, null);
    assertTrue("Icon shall be equal between two calls", imageCpt == imageCpt2);

    //Multi part
    updateApproach(ProjectApproach.ReusableComponents, diagram.getDiagramDescriptor().getTarget());
    Image imagePart = c.getLabelIcon(view, null);
    Image imagePart2 = c.getLabelIcon(view, null);
    assertTrue("Icon shall be equal between two calls", imagePart == imagePart2);
    assertTrue("Icon on multipart shall be different than standard mode", imageCpt != imagePart);
    
    //Mono part with part without type
    ILogListener listener =new ILogListener() {
      
      @Override
      public void logging(IStatus status, String plugin) {
        assertTrue("Icon on part without type shall not raise an error message", false);
      }
    };
    DiagramPlugin.getDefault().getLog().addLogListener(listener);
    
    try {
      updateApproach(ProjectApproach.SingletonComponents, diagram.getDiagramDescriptor().getTarget());
      Image imagePart3 = c.getLabelIcon(view, null);
      removePartType(((Part) view.getTarget()));
      Image imagePart4 = c.getLabelIcon(view, null);
      Image imagePart5 = c.getLabelIcon(view, null);
      assertTrue("Icon shall be equal different than with a type", imagePart3 != imagePart4);
      assertTrue("Icon shall be equal between two calls", imagePart4 == imagePart5);
      
    } catch (Exception e) {
      assertTrue("Icon on part without type shall not raise an Exception", false);
    
    } finally {
      DiagramPlugin.getDefault().getLog().removeLogListener(listener);
    }
  }

  private void removePartType(Part source) {
    TransactionHelper.getExecutionManager(source).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        source.setAbstractType(null);
      }
    });
  }
  
  private void updateApproach(ProjectApproach approach, EObject source) {
    TransactionHelper.getExecutionManager(source).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        CapellaProjectHelper.setProjectWithApproach(ProjectExt.getProject(source), approach);
      }
    });
  }
  
  
}
