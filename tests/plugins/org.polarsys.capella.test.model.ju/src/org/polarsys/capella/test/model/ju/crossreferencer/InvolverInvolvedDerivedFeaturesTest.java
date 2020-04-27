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
package org.polarsys.capella.test.model.ju.crossreferencer;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 */
public class InvolverInvolvedDerivedFeaturesTest extends AbstractReflectiveCrossReferencerTest {
  /**
   * Test derived features for all {@link InvolvedElement}, {@link Involvement} and {@link InvolverElement}.
   * 
   * @throws Exception
   */
  public void testInvolverInvolvedDerivedFeaturesForAll() throws Exception {
    final int involvedsCount = getSubTypesCount(CapellacorePackage.Literals.INVOLVED_ELEMENT);
    final int involvementsCount = getSubTypesCount(CapellacorePackage.Literals.INVOLVEMENT);
    final int involversCount = getSubTypesCount(CapellacorePackage.Literals.INVOLVER_ELEMENT);
    assertTrue("Ensure involvedElements were found", involvedsCount > 0);
    System.out.println("InvolvedElement Types count = " + involvedsCount);
    assertTrue("Ensure involvements were found", involvementsCount > 0);
    System.out.println("Involvement Types count = " + involvementsCount);
    assertTrue("Ensure involvers were found", involversCount > 0);
    System.out.println("InvolverElement Types count = " + involversCount);
    executeTestCommand(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        // Cycle through involvements.
        for (int involvementPosition = 0; involvementPosition < involvementsCount; involvementPosition++) {
          // Create involvement.
          EClass involvementEClass = getSubType(CapellacorePackage.Literals.INVOLVEMENT, involvementPosition);
          Involvement involvement = (Involvement) createType(involvementEClass);
          // Cycle through involvers.
          for (int involverPosition = 0; involverPosition < involversCount; involverPosition++) {
            // Create involver.
            EClass involverEClass = getSubType(CapellacorePackage.Literals.INVOLVER_ELEMENT, involverPosition);
            InvolverElement involver = (InvolverElement) createType(involverEClass);
            // Are the Involver and the involvement matched ?
            boolean isMatched = false;
            for (EReference reference : involverEClass.getEAllReferences()) {
              if (reference.isContainment() && !involvementEClass.isAbstract()
                  && reference.getEReferenceType() == involvementEClass) {
                isMatched = true;
                if (involver instanceof Capability && involvement instanceof CapabilityInvolvement)
                  ((Capability) involver).getOwnedCapabilityInvolvements()
                      .add((CapabilityInvolvement) involvement);
                else if (involver instanceof CapabilityRealization
                    && involvement instanceof CapabilityRealizationInvolvement)
                  ((CapabilityRealization) involver).getOwnedCapabilityRealizationInvolvements()
                      .add((CapabilityRealizationInvolvement) involvement);
                else if (involver instanceof OperationalCapability
                    && involvement instanceof EntityOperationalCapabilityInvolvement)
                  ((OperationalCapability) involver).getOwnedEntityOperationalCapabilityInvolvements()
                      .add((EntityOperationalCapabilityInvolvement) involvement);
                else if (involver instanceof FunctionalChain)
                  ((FunctionalChain) involver).getOwnedFunctionalChainInvolvements()
                      .add((FunctionalChainInvolvement) involvement);
                else if (involver instanceof Mission && involvement instanceof MissionInvolvement)
                  ((Mission) involver).getOwnedMissionInvolvements().add((MissionInvolvement) involvement);
                else if (involver instanceof PhysicalPath)
                  ((PhysicalPath) involver).getOwnedPhysicalPathInvolvements()
                      .add((PhysicalPathInvolvement) involvement);
                else
                  isMatched = false;
                break;
              }
            }

            if (isMatched) {
              assertEquals("Ensure involver", involvement, involver.getInvolvedInvolvements().get(0));
              // Cycle through involveds.
              for (int involvedPosition = 0; involvedPosition < involvedsCount; involvedPosition++) {
                // Create involved.
                InvolvedElement involved = (InvolvedElement) createType(
                    getSubType(CapellacorePackage.Literals.INVOLVED_ELEMENT, involvedPosition));
                involvement.setInvolved(involved);
                assertEquals("Ensure involved", involvement, involved.getInvolvingInvolvements().get(0));
                // Free memory.
                involvement.setInvolved(null);
                removeFromResource(involved);
              }
            }
            // Free memory.
            removeFromResource(involver);
          }
          // Free memory.
          removeFromResource(involvement);
        }
      }
    });
  }

  @Override
  protected List<EClass> getRootTypes() {
    return Arrays.asList(CapellacorePackage.Literals.INVOLVED_ELEMENT, CapellacorePackage.Literals.INVOLVEMENT,
        CapellacorePackage.Literals.INVOLVER_ELEMENT);
  }

  @Override
  public void test() throws Exception {
    testInvolverInvolvedDerivedFeaturesForAll();
  }
}
