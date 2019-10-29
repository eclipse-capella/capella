/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa.NewInteractionScenarioAdapter;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

public class OESActivityExplorerTestCase extends FunctionalScenarioActivityExplorerTestCase {
  
  @Override
  public AbstractCapellaNewDiagramHyperlinkAdapter createLink() {
    return new NewInteractionScenarioAdapter() {
      @Override
      protected boolean useDefaultName() {
        return true;
      }
    };
  }

  @Override
  public ModelElement getTestModelElement() {
    OperationalAnalysis operationalAnalysis = ModelQueryHelper.getOperationalAnalysis(project);
    return ModelCreationHelper.selectCapabilityAndCreateScenario(project, operationalAnalysis, null);
  }

  @Override
  public AbstractCapabilityPkg getStructure() {
    return context.getSemanticElement(OA__OPERATIONAL_CAPABILITIES);

  }

  @Override
  public String getDefaultName() {
    return "[OES] OperationalCapability 1";
  }

}
