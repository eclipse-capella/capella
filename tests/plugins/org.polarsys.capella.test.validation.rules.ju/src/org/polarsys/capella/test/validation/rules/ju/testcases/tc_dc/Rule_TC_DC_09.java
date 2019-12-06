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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_dc;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

public class Rule_TC_DC_09 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return CtxPackage.Literals.SYSTEM_ANALYSIS;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.ctx.validation.TC_DC_09";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "4b52a407-ea27-493f-b259-bb4c1b1708da" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("4b52a407-ea27-493f-b259-bb4c1b1708da", 1) });
  }

}
