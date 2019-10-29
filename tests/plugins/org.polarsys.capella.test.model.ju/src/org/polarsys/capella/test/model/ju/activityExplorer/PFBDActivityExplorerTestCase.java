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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewFunctionalBreakdownDiagramAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class PFBDActivityExplorerTestCase extends FunctionalDiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewFunctionalBreakdownDiagramAdapter();
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewFunctionalBreakdownDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewFunctionalBreakdownDiagramAdapter) link).getMyModelElement(project);
  }

  @Override
  public AbstractFunction getStructure() {
    return context.getSemanticElement(PA__ROOT_PF);
  }

  @Override
  public String getDefaultName() {
    return "[PFBD] Root Physical Function";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.PHYSICAL_FUNCTION_BREAKDOWN_DIAGRAM_NAME;

  }

  class MyNewFunctionalBreakdownDiagramAdapter extends NewFunctionalBreakdownDiagramAdapter {

    @Override
    protected boolean useDefaultName() {
      return true;
    }

    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return getModelElement(rootSemanticModel);
    }

    public boolean myCreateDiagram(final EObject project, final Session session) {
      return createDiagram(project, session);
    }
  }

}
