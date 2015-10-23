/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools.xdfb;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.headless.ILinksTransfertWizardResult;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class SwitchExchangeCategoryTool extends InsertRemoveTool {

  public SwitchExchangeCategoryTool(DiagramContext context) {
    super(context, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES);
  }

  @Override
  protected IHeadlessResult createOperation() {
    return new ILinksTransfertWizardResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElements(insertedElements);
      }
    };
  }
}
