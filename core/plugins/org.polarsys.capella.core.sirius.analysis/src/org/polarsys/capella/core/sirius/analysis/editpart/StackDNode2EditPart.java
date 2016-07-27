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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;

public class StackDNode2EditPart extends DNodeContainer2EditPart {

  public StackDNode2EditPart(View view) {
    super(view);
  }

  protected void configureBorder(IFigure shapeFigure) {
    DiagramContainerEditPartOperation.configureBorder(this, shapeFigure);
  }

  @Override
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    if (isRegion()) {
      installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StackGraphicalNodeEditPolicy());
    }
  }

  /**
   * When a transition is created toward the region, we hightlight the owning state
   */
  public void showTargetFeedback(Request request) {
    if (!isEditModeEnabled()) {
      return;
    }
    getParent().getParent().showTargetFeedback(request);
  }

  /**
   * When a transition is created toward the region, we hightlight the owning state
   */
  public void eraseTargetFeedback(Request request) {
    if (!isEditModeEnabled()) {
      return;
    }
    getParent().getParent().eraseTargetFeedback(request);
  }
}
