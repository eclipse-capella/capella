/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;

public class SwitchExchangeCategoryTool extends InsertRemoveTool {

  public SwitchExchangeCategoryTool(DiagramContext context) {
    super(context, IToolNameConstants.TOOL_SDFB_SHOW_HIDE_FUNCTIONAL_EXCH_CATEGORIES);
  }

  @Override
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        return getExecutionContext().getSemanticElements(insertedElements);
      }
    };
  }
}
