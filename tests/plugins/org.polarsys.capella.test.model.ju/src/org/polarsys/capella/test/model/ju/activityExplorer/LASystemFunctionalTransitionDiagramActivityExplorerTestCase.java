/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.test.model.ju.activityExplorer;

import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.PerformAutomatedTransitionAdapter;

public class LASystemFunctionalTransitionDiagramActivityExplorerTestCase
    extends TransitionDiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyPerformAutomatedTransitionAdapter();
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyPerformAutomatedTransitionAdapter) link).getMyModelElement(project);
  }

  @Override
  public CapellaElement getContainer() {
    return context.getSemanticElement(SA__ROOT_SF);
  }

  @Override
  public AbstractFunction getRoot() {
    return context.getSemanticElement(LA__ROOT_LF);
  }

  @Override
  public void executeTransition() {
    ((MyPerformAutomatedTransitionAdapter) link).myLinkPressed(project, session);
  }

  class MyPerformAutomatedTransitionAdapter extends PerformAutomatedTransitionAdapter {
    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return super.getModelElement(rootSemanticModel);
    }

    public void myLinkPressed(final EObject rootSemanticModel, final Session session) {
      linkPressed(null, rootSemanticModel, session);
    }

    @Override
    protected void linkPressed(HyperlinkEvent event, EObject rootSemanticModel, Session session) {
      ModelElement modelElement = getModelElement(rootSemanticModel);
      if (modelElement != null) {
        TransactionHelper.getExecutionManager(modelElement)
            .execute(org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper.getInstance()
                .getFunctionalTransitionCommand(Collections.singleton((Object) modelElement),
                    new NullProgressMonitor()));

      }
    }
  }

}
