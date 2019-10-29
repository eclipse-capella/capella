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
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewActivityScenarioAdapter;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

public class OASActivityExplorerTestCase extends FunctionalScenarioActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewActivityScenarioAdapter();
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewActivityScenarioAdapter) link).myCreateDiagram(scenario, session);
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((MyNewActivityScenarioAdapter) link).getMyModelElement(project);
  }

  @Override
  public AbstractCapabilityPkg getStructure() {
    return context.getSemanticElement(OA__OPERATIONAL_CAPABILITIES);
  }

  @Override
  public String getDefaultName() {
    return "[OAS] Scenario 1";
  }

  @Override
  public String getDiagramName() {
    return IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_SCENARIO_DIAGRAM_NAME;
  }

  class MyNewActivityScenarioAdapter extends NewActivityScenarioAdapter {

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
