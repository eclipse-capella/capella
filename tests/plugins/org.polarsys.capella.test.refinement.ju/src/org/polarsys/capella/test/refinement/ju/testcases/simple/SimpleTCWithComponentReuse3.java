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
package org.polarsys.capella.test.refinement.ju.testcases.simple;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.interaction.Scenario;

public class SimpleTCWithComponentReuse3 extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithComponentReuse3() {
    super(Messages.SimpleTCWithComponentReuse_Name3,
        Messages.SimpleTCWithComponentReuse_Desc3,
        Arrays.asList(Messages.ComponentReuse_TC_LC3_1a_Part, Messages.ComponentReuse_TC_LC3_1b_Part),
        Arrays.asList(Messages.ComponentReuse_TC_LC3_1a_Part, Messages.ComponentReuse_TC_LC3_1b_Part),
        Messages.SimpleTCWithComponentReuse_Src3, Messages.SimpleTCWithComponentReuse_Ref3,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Arrays.asList(Messages.ComponentReuse_TC_LC3_1a_InstanceRole, Messages.ComponentReuse_TC_LC3_1b_InstanceRole));
  }

  /**
   * @see org.polarsys.capella.test.refinement.ju.testcases.RefinementTest#additionalCheck(org.polarsys.capella.core.data.interaction.Scenario, org.polarsys.capella.core.data.interaction.Scenario)
   */
  @Override
  protected void additionalCheck(Scenario refinedScenario, Scenario referenceScenario) {
    EObject lc3_1a = _semanticObjectMap.get(Messages.ComponentReuse_TC_LC3_1a_InstanceRole);
    EObject lc3_1b = _semanticObjectMap.get(Messages.ComponentReuse_TC_LC3_1b_InstanceRole);
    Assert.assertNotNull(lc3_1a);
    Assert.assertTrue(Messages.eltShallHaveIncomingLink, ((TraceableElement) lc3_1a).getIncomingTraces().size() > 0);
    Assert.assertNotNull(lc3_1b);
    Assert.assertTrue(Messages.eltShallHaveIncomingLink, ((TraceableElement) lc3_1b).getIncomingTraces().size() > 0);
  }
}
