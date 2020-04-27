/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.diffmerge;

import static org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy.CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT;
import static org.eclipse.emf.diffmerge.gmf.GMFMatchPolicy.CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.EXTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.INTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.SEMANTICS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.compare.CapellaMatchPolicy;

public class DiffMergeBetweenVersionsOfSameCapellaT4CModelTestCase extends DiffMergeTestCase {
  @Override
  protected CapellaMatchPolicy getMatchPolicy() {
    // "Diff/merge between different versions of the same Capella / Team for Capella model":
    // CONFIGURATOR_CAPELLA_DEFAULT
    CapellaMatchPolicy policy_p = new CapellaMatchPolicy();
    policy_p.setAllUsedCriteria(Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID, SEMANTICS));
    policy_p.setAllUsedFineGrainedCriteria(
        Arrays.asList(CRITERION_SEMANTICS_DIAGRAMS_VIEWBYELEMENT, CRITERION_SEMANTICS_DIAGRAMS_VIEWBYTYPE));
    policy_p.setUseCache(false);
    return policy_p;
  }

  @Override
  protected List<String> getTargetDiffList() {
    return new ArrayList<String>();
  }

  @Override
  protected List<String> getTargetNoDiffList() {
    return new ArrayList<String>();
  }

  @Override
  protected List<String> getReferenceDiffList() {
    return Arrays.asList(t4cPhysicalFunction1Id, t4cPhysicalFunction2Id, t4cFOP1PhysicalFunction1Id,
        t4cFIP1PhysicalFunction2Id, t4cFunctionalExchange1Id, t4cFunctionalChain1Id,
        t4cFunctionalChainInvolvementLinkId, t4cFunctionalChainInvolvementFunction1Id,
        t4cFunctionalChainInvolvementFunction2Id);
  }

  @Override
  protected List<String> getReferenceNoDiffList() {
    return new ArrayList<String>();
  }

  @Override
  protected String getSourcePrjName() {
    return "DiffMergeSourcePrj";
  }

  @Override
  protected String getTargetPrjName() {
    return "DiffMergeSourceT4CPrj";
  }

  @Override
  protected String getTargetResourceName() {
    return sourceModel + ".aird";
  }
}
