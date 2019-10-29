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
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.NewBlankFunctionalDataflowDiagramAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class LDFBActivityExplorerTestCase extends FunctionalDiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewBlankFunctionalDataflowDiagramAdapter();
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewBlankFunctionalDataflowDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewBlankFunctionalDataflowDiagramAdapter) link).getMyModelElement(project);
  }

  @Override
  public AbstractFunction getStructure() {
    return context.getSemanticElement(LA__ROOT_LF);
  }

  @Override
  public String getDefaultName() {
    return "[LDFB] Root Logical Function";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME;

  }

  class MyNewBlankFunctionalDataflowDiagramAdapter extends NewBlankFunctionalDataflowDiagramAdapter {

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
