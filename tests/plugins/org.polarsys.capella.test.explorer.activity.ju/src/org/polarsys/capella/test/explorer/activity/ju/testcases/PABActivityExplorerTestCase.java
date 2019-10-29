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
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewPhysicalArchitectureDiagramAdapter;

public class PABActivityExplorerTestCase extends DiagramActivityExplorerTestCase {
  
  @Override
  protected AbstractCapellaNewDiagramHyperlinkAdapter createLink() {
    return new NewPhysicalArchitectureDiagramAdapter() {
      @Override
      protected boolean useDefaultName() {
        return true;
      }
    };
  }

  @Override
  protected ModelElement getTestModelElement() {
    return ((NewPhysicalArchitectureDiagramAdapter) link).getModelElement(project);
  }

  @Override
  protected ComponentPkg getStructure() {
    return context.getSemanticElement(PA__PHYSICAL_CONTEXT);
  }

  @Override
  protected String getDefaultName() {
    return "[PAB] Structure";
  }

}
