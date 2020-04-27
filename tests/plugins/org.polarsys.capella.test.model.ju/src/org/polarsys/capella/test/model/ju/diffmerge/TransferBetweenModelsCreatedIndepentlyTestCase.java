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

import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.CRITERION_SEMANTICS_DEFAULTCONTENTS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.CRITERION_STRUCTURE_ROOTS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.EXTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.INTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.SEMANTICS;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.STRUCTURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.compare.CapellaMatchPolicy;

public class TransferBetweenModelsCreatedIndepentlyTestCase extends DiffMergeTestCase {
  @Override
  protected CapellaMatchPolicy getMatchPolicy() {
    // Transfer of elements between models created independently as in
    // ConfigurableComparisonMethod.CONFIGURATOR_DATA_TRANSFER
    CapellaMatchPolicy policy_p = new CapellaMatchPolicy();
    policy_p.setAllUsedCriteria(Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID, STRUCTURE, SEMANTICS));
    policy_p
        .setAllUsedFineGrainedCriteria(Arrays.asList(CRITERION_STRUCTURE_ROOTS, CRITERION_SEMANTICS_DEFAULTCONTENTS));
    policy_p.setUseCache(false);
    return policy_p;
  }

  @Override
  protected List<String> getTargetDiffList() {
    return Arrays.asList(sourceSystemFunction1Id, sourceCapabilityRealization1, sourcePartLA2Id, sourcePartLA3Id,
        sourceLA2Id, sourceLA2Id, sourceCEId, sourceLA2_CP1Id, sourceLA3_CP1Id);
  }

  @Override
  protected List<String> getTargetNoDiffList() {
    return new ArrayList<String>();
  }

  @Override
  protected List<String> getReferenceDiffList() {
    return Arrays.asList(targetOperationalActivity1Id, targetClassId, targetSystemFunction1Id);
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
    return "DiffMergeTargetPrj";
  }
}
