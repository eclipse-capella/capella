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
package org.polarsys.capella.test.diagram.common.ju.step.tools.titleblocks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AbstractExternalJavaAction;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

// show hide
public class InsertRemoveTitleBlockTool extends InsertRemoveTool {

  public InsertRemoveTitleBlockTool(DiagramContext context, String toolName) {
    super(context, toolName);
  }

  private DAnnotation getAnnotation(String uid) {
    DDiagram diagram = getDiagramContext().getDiagram();
    DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
    Optional<DAnnotation> op = descriptor.getEAnnotations().stream().filter(x -> x.getUid().equals(uid)).findFirst();

    if (op.isPresent()) {
      return op.get();
    }
    return null;
  }

  private boolean hasView(DAnnotation titleBlock) {
    DDiagram diagram = getDiagramContext().getDiagram();
    DDiagramElement graphicalElement = DiagramServices.getDiagramServices().getDiagramElement(diagram, titleBlock);
    return graphicalElement != null;
  }

  private List<EObject> getDAnnotationElements(String... ids) {
    List<EObject> objList = new ArrayList<EObject>();
    for (String id : ids) {
      objList.add(getAnnotation(id));
    }

    return objList;
  }

  @Override
  protected void checkPreconditions() {
    for (String uid : insertedElements) {
      assertFalse(hasView(getAnnotation(uid)));
    }
    for (String uid : removedElements) {
      assertTrue(hasView(getAnnotation(uid)));
    }
  }

  @Override
  protected void postRunTest() {
    DiagramHelper.refreshDiagram(getDiagramContext().getDiagram());
    
    for (String uid : insertedElements) {
      assertTrue(hasView(getAnnotation(uid)));
    }
    for (String uid : removedElements) {
      assertFalse(hasView(getAnnotation(uid)));
    }
  }

  /**
   * @return
   */
  @Override
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (insertAll) {
          return AbstractExternalJavaAction.getScope(parameters);
        } else if (removeAll) {
          return Collections.emptyList();
        }

        Collection<EObject> objects = new HashSet<EObject>();
        DiagramContext context = getDiagramContext();

        Collection<EObject> inserted = context.adaptTool(InsertRemoveTitleBlockTool.this, parameters,
            getDAnnotationElements(insertedElements));
        Collection<EObject> removed = context.adaptTool(InsertRemoveTitleBlockTool.this, parameters,
            getDAnnotationElements(removedElements));
        objects.addAll(AbstractExternalJavaAction.getInitialSelection(parameters));
        objects.addAll(inserted);
        objects.removeAll(removed);
        return new ArrayList<EObject>(objects);

      }
    };
  }

}
