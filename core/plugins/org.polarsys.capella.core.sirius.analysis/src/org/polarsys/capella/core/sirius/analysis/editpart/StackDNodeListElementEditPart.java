/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;

/**
 * This class allows to customize the behavior of NodeList element (Entry/Exit/Do Activity) when an edge (Transition) is
 * created on it
 */
public class StackDNodeListElementEditPart extends DNodeListElementEditPart {

  public StackDNodeListElementEditPart(View view) {
    super(view);
  }

  @Override
  protected void createDefaultEditPolicies() {
    super.createDefaultEditPolicies();
    installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new StackNodeElementEditPolicy());
  }

  /**
   * When a transition is created toward the Entry/Exit/Do Activity list, we hightlight the owning state
   */
  @Override
  public void showTargetFeedback(Request request) {
    if (!isEditModeEnabled()) {
      return;
    }
    getParent().getParent().getParent().getParent().showTargetFeedback(request);
  }

  /**
   * When a transition is created toward the Entry/Exit/Do Activity list, we hightlight the owning state
   */
  @Override
  public void eraseTargetFeedback(Request request) {
    if (!isEditModeEnabled()) {
      return;
    }
    getParent().getParent().getParent().getParent().eraseTargetFeedback(request);
  }

  @Override
  public void showSourceFeedback(Request request) {
    if (!isActive())
      return;
    EditPolicyIterator i = getEditPolicyIterator();
    while (i.hasNext()) {
      EditPolicy next = i.next();
      if (next instanceof StackNodeElementEditPolicy && request instanceof CreateConnectionRequest) {
        next.showSourceFeedback(request);
      }
    }
  }
}
