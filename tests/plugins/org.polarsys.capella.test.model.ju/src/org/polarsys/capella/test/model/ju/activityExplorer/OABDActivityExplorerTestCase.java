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
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewOperationalActivityBreakdownDiagramAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class OABDActivityExplorerTestCase extends FunctionalDiagramActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewOperationalActivityBreakdownDiagramAdapter();
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewOperationalActivityBreakdownDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewOperationalActivityBreakdownDiagramAdapter) link).getMyModelElement(project);
  }

  @Override
  public AbstractFunction getStructure() {
    return context.getSemanticElement(OA__OPERATIONAL_ACTIVITIES__ROOT_OA);
  }

  @Override
  public String getDefaultName() {
    return "[OABD] Root Operational Activity";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.OPERATIONAL_ACTIVITY_BREAKDOWN_DIAGRAM_NAME;
  }

  class MyNewOperationalActivityBreakdownDiagramAdapter extends NewOperationalActivityBreakdownDiagramAdapter {

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
