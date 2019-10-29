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
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewOperationalInteractionBlankDiagramAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class OAIBActivityExplorerTestCase extends FunctionalDiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewOperationalInteractionBlankDiagramAdapter();
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewOperationalInteractionBlankDiagramAdapter) link).getMyModelElement(project);
  }

  @Override
  public AbstractFunction getStructure() {
    return context.getSemanticElement(OA__OPERATIONAL_ACTIVITIES__ROOT_OA);
  }

  @Override
  public String getDefaultName() {
    return "[OAIB] Root Operational Activity";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME;
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewOperationalInteractionBlankDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  class MyNewOperationalInteractionBlankDiagramAdapter extends NewOperationalInteractionBlankDiagramAdapter {
    
    @Override
    protected boolean useDefaultName() {
      return true;
    }

    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return (ModelElement) getModelElement(rootSemanticModel);
    }

    public boolean myCreateDiagram(final EObject project, final Session session) {
      return createDiagram(project, session);
    }
  }

}
