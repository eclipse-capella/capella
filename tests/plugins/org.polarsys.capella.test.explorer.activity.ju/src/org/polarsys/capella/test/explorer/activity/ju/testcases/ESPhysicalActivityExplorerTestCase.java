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
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewExchangeScenarioAdapter;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

public class ESPhysicalActivityExplorerTestCase extends FunctionalScenarioActivityExplorerTestCase {
  
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
  protected ModelElement getTestModelElement() {
    PhysicalArchitecture physicalArchitecture = ModelQueryHelper.getPhysicalArchitecture(project);
    return ModelCreationHelper.selectCapabilityAndCreateScenario(project, physicalArchitecture, null);
  }

  @Override
  public AbstractCapabilityPkg getStructure() {
    return context.getSemanticElement(PA__CAPABILITIES);
  }

  @Override
  public String getDefaultName() {
    return "[ES] CapabilityRealization 1";
  }

}
