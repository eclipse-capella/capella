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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.DeleteElementTool;

public class DeleteTitleBlockTool extends DeleteElementTool {

	List<DAnnotation> annotationsToDelete;
	DAnnotation titleBlock;

	  public DeleteTitleBlockTool(DiagramContext context, DiagramContext containingToolDiagramContext, String toolName) {
	    super(context, containingToolDiagramContext, toolName);
	  }

	  public void delete(DAnnotation titleBlock) {
	    DDiagramElement titleBlockView = DiagramServices.getDiagramServices().getDiagramElement(getDiagramContext().getDiagram(),
	        titleBlock);
	    this.titleBlock = titleBlock;
	    super.delete(titleBlockView);
	  }

	  @Override
	  protected void postRunTest() {
		List<DAnnotation> annotationsAfterDelete = TitleBlockHelper.getAllAnnotationsForTitleBlock(this.titleBlock);
		assertTrue(annotationsAfterDelete.isEmpty());	  
	  }
}
