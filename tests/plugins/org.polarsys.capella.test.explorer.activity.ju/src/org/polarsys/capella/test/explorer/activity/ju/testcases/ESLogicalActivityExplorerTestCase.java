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

package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

public class ESLogicalActivityExplorerTestCase extends FunctionalScenarioActivityExplorerTestCase {
  
  @Override
  public AbstractCapellaNewDiagramHyperlinkAdapter createLink() {
    return new NewExchangeScenarioAdapter() {
      @Override
      protected boolean useDefaultName() {
        return true;
      }
    };
  }

  @Override
  public ModelElement getTestModelElement() {
    LogicalArchitecture logicalArchitecture = ModelQueryHelper.getLogicalArchitecture(project);
    return ModelCreationHelper.selectCapabilityAndCreateScenario(project, logicalArchitecture, null);
  }

  @Override
  public AbstractCapabilityPkg getStructure() {
    return context.getSemanticElement(LA__CAPABILITIES);
  }

  @Override
  public String getDefaultName() {
    return "[ES] CapabilityRealization 1";

  }

}
