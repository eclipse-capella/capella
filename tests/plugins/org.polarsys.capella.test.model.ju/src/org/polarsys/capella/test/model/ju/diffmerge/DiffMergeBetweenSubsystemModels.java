/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
import static org.polarsys.capella.core.compare.CapellaMatchPolicy.CRITERION_INTRINSIC_ID_SID;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.polarsys.capella.core.compare.CapellaMatchPolicy;

/* The testing environment was created by starting from a simple model, 
 * containing four physical components, PC01, PC02 and PC13, where PC13
 * is contained within PC01. By using the subsystem horizontal transition add-on
 * this model was projected in two different new projects, test_B and test_C.
 * At first they were perfectly identical, but at the last step we've added up
 * an extra component on each side: PC03 in test_B and PC04 in test_C, 
 * just to have a starting point for visibly different models.
 * the differences do not come from the PC01, PC02 andPC13, but from 
 * the physical components PC03 and PC04
 */
public class DiffMergeBetweenSubsystemModels extends DiffMergeTestCase {

  @Override
  protected CapellaMatchPolicy getMatchPolicy() {
    // Diff/merge between Capella subsystem models transitioned from the same system model
    // Configurator CONFIGURATOR_P2L
    CapellaMatchPolicy policy_p = new CapellaMatchPolicy();
    policy_p.setAllUsedCriteria(Arrays.asList(INTRINSIC_ID, EXTRINSIC_ID));
    policy_p.setAllUsedFineGrainedCriteria(
        Arrays.asList(CRITERION_INTRINSIC_ID_SID));
    policy_p.setUseCache(false);
    return policy_p;
  }

  @Override
  protected void checkDifferences(EComparisonImpl comparison) {
    assertCheckDifferences(comparison, Role.TARGET, getModelElementsSourceProject(getTargetDiffList()),
        getModelElementsSourceProject(getTargetNoDiffList()), false);
    assertCheckDifferences(comparison, Role.REFERENCE,
        getModelElementsTargetProject(getReferenceDiffList()), getModelElementsSourceProject(getReferenceNoDiffList()),
        false);
  }

  @Override
  protected List<String> getTargetDiffList() {
    return Arrays.asList(sourcePhysicalComponent03id);
  }

  @Override
  protected List<String> getReferenceDiffList() {
    return Arrays.asList(targetPhysicalComponent04id);
  }

  @Override
  protected List<String> getTargetNoDiffList() {
    return Arrays.asList(sourcePhysicalComponent01id, sourcePhysicalComponent02id, sourcePhysicalComponent13id);
  }

  @Override
  protected List<String> getReferenceNoDiffList() {
    return Arrays.asList(targetPhysicalComponent01id, targetPhysicalComponent02id, targetPhysicalComponent13id);
  }

  @Override
  protected String getSourcePrjName() {
    return "test_B";
  }

  @Override
  protected String getTargetPrjName() {
    return "test_C";
  }

}
