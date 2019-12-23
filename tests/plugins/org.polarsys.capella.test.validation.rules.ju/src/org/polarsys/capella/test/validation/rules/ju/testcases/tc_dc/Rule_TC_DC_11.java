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
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

public class Rule_TC_DC_11 extends AbstractRulesOnTransitionTest {

  @Override
  protected EClass getTargetedEClass() {
    return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.pa.validation.TC_DC_11";
  }

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "c2a61ba6-1173-4c7d-ab43-ecea4c9300ab" });
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] { new OracleDefinition("c2a61ba6-1173-4c7d-ab43-ecea4c9300ab", 1) });
  }

}
