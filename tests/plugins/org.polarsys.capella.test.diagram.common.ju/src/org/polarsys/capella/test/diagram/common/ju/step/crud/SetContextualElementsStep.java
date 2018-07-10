/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.crud;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.AbstractDiagramStep;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public class SetContextualElementsStep extends AbstractDiagramStep<DiagramContext> {

  String[] ids;

  public SetContextualElementsStep(DiagramContext context, String... ids) {
    super(context);
    this.ids = ids;
  }

  @Override
  public DiagramContext getResult() {
    return getExecutionContext();
  }

  @Override
  protected void runTest() {
    TestHelper.getExecutionManager(getExecutionContext().getSession()).execute(new AbstractReadWriteCommand() {
      public void run() {
        Collection<EObject> objects = new ArrayList<EObject>();
        for (String id : ids) {
          objects.add(getExecutionContext().getSemanticElement(id));
        }
        DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(getExecutionContext().getDiagram());
        ContextualDiagramHelper.getService().setContextualElements(descriptor, objects);
      }
    });
  }
}
