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

import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.EXTRINSIC_ID;
import static org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.MatchCriterionKind.INTRINSIC_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.diffmerge.impl.policies.ConfigurableMatchPolicy.FineGrainedMatchCriterion;
import org.polarsys.capella.core.compare.CapellaMatchPolicy;

public class DiffMergeBetweenVersionsOfSameModelTestCase extends DiffMergeTestCase {
  @Override
  protected CapellaMatchPolicy getMatchPolicy() {
    // "Diff/merge between different versions of the same model" : CONFIGURATOR_VERSIONS
    CapellaMatchPolicy policy_p = new CapellaMatchPolicy();
    policy_p.setAllUsedCriteria(Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID));
    policy_p.setAllUsedFineGrainedCriteria(
        Collections.<FineGrainedMatchCriterion>emptySet());
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
    return Arrays.asList(refSystemFunction2Id, capabilityRealization1Id, capabilityRealization2Id,
        capabilityGeneralizationId, physicalComponentId, physicalPartId);
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
    return "DiffMergeSourceV1Prj";
  }

  @Override
  protected String getTargetResourceName() {
    return sourceModel + ".aird";
  }
}
