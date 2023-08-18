/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.la;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "LA Capability Realization to PA Capability Realization" from Logical Analysis to Physical
 * Analysis
 * <P>
 * This is done with the model "LA-PA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: LA-PA-Projection
 *     Model is created with the following elements�
 *         1. Create Capability Realization CR1 and CR2 under LA Capability Realization Pkg
 *         2. Create Capa11 in the LA Capability Realization Pkg, inheriting from CR1
 * </pre>
 */
public class CapabilityTransition extends TopDownTransitionTestCase {

  // Capabilities
  private CapabilityRealizationPkg laRootCRPkg;
  private CapabilityRealizationPkg laSubCRPkg;

  private CapabilityRealization laCR1;
  private CapabilityRealization laCR2;
  private CapabilityRealization laCR11;
  private CapabilityRealization laCR3;
  private CapabilityRealization laSubCR1;
  private CapabilityRealization CR1;

  private CapabilityRealizationPkg paRootCRPkg;
  private CapabilityRealizationPkg paSubCRPkg;
  private CapabilityRealization paCR1;
  private CapabilityRealization paCR11;
  private CapabilityRealization paCR2;
  private CapabilityRealization paCR3;
  private CapabilityRealization paSubCR1;
  
  private PhysicalComponent PA_LC1;
  private PhysicalComponent PA_LA1;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    laRootCRPkg = getObject(ModelLaPa.rootLACRPkgId);
    laCR1 = getObject(ModelLaPa.CR1Id);
    laCR2 = getObject(ModelLaPa.CR2Id);
    laCR11 = getObject(ModelLaPa.CR11Id);
    paRootCRPkg = getObject(ModelLaPa.rootPACRPkgId);
    CR1 = getObject(ModelLaPa.CR1);
    PA_LC1 = (PhysicalComponent) getObject(ModelLaPa.PA_LC1);
    PA_LA1 = (PhysicalComponent) getObject(ModelLaPa.PA_LA1);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Tests on "LA Capability Realization to PA Capability Realization" Projection command should be applied:
   * 
   * <pre>
   *         1. Test on one leaf entity (CR11)
   *         2. Test on non leaf entity (root CR Pkg)
   *         4. Repetition Test on Root CR Pkg without any changes
   *         5. Test on Root CRPkg with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    CR11TransitionTest();
    rootCRPkgProjection1Test();
    rootcapaPkgProjection2Test();
    rootcapaPkgProjection3Test();
    performTestOnCR1();
  }

  /**
   * Run the projection test "LA Capability Realization to PA Capability Realization" from CR11
   * 
   * <pre>
   * Expected Result:\
   *               1. CR11 is projected towards LA Layer.\
   *               2. PA CR11 realizes LA CR11
   * </pre>
   */
  private void CR11TransitionTest() {
    performCapabilityTransition(Arrays.asList(laCR11));
    paCR11 = paRootCRPkg.getOwnedCapabilityRealizations().get(0);
    mustNotBeNull(laCR11);

    assertTrue(NLS.bind(Messages.RealizationError, paCR11.getName(), laCR11.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paCR11) == laCR11));
  }

  /**
   * Run the projection test "LA Capability Realization to PA Capability Rezalization" from Capability Realization Pkg
   * 
   * <pre>
   * Expected Result:\
   *               1. CR1, CR2 are projected towards PA Layer.\
   *               2. Appropriate realization links are created
   * </pre>
   */
  private void rootCRPkgProjection1Test() {
    performCapabilityTransition(Arrays.asList(laRootCRPkg));
    paCR1 = (CapabilityRealization) CodeHelper.getChildTracingElement(paRootCRPkg, laCR1);
    mustNotBeNull(paCR1);
    assertTrue(NLS.bind(Messages.WrongSize, laCR1.getName()),
        (laCR1.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, paCR1.getName()),
        (paCR1.eContainer() == paRootCRPkg));

    assertTrue(NLS.bind(Messages.RealizationError, paCR1.getName(), laCR1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paCR1) == laCR1));

    paCR2 = (CapabilityRealization) CodeHelper.getChildTracingElement(paRootCRPkg, laCR2);

    mustNotBeNull(paCR2);

    assertTrue(NLS.bind(Messages.WrongSize, laCR2.getName()),
        (laCR2.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, paCR2.getName()),
        (paCR2.eContainer() == paRootCRPkg));

    assertTrue(NLS.bind(Messages.RealizationError, paCR2.getName(), laCR2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paCR2) == laCR2));
  }

  /**
   * Run the projection test "LA Capability Realization to LA Capability Realization" from Capability Realization Pkg
   * again
   * 
   * <pre>
   * Expected Result:\
   *               1. No changes, and the elements are intact
   * </pre>
   */
  private void rootcapaPkgProjection2Test() {
    performCapabilityTransition(Arrays.asList(laRootCRPkg));
    int laSize = laRootCRPkg.getOwnedCapabilityRealizations().size();
    int paSize = paRootCRPkg.getOwnedCapabilityRealizations().size();
    // Check no component is allcapaated newly
    assertTrue(Messages.WrongSize, (laSize == paSize));
  }

  /**
   * Run the projection test "LA Capability Realization to PA Capability Realization" from Capability Realization Pkg
   * again
   * 
   * <pre>
   * Make the following changes in the model\
   *               1.  Delete CR2\
   *               2.  Add new Capability Realization CR3\
   *               3.  Add sub Capability Realization Pkg SubCRPkg\
   *               4.  Add SubCR1 in SubCRPkg\
   * Expected Result:\
   *               1. CR2 still remains in PA Layer without realization link\
   *               2. CR3, SubCRPkg and SubCR1 are refined to PA Layer with realization links
   * </pre>
   */
  private void rootcapaPkgProjection3Test() {
    // Delete capa2
    laCR2.destroy();
    // Add new Capability Realization CR3
    // Add sub Capability Realization Pkg SubCRPkg
    // Add SubCR1 in SubCRPkg
    laCR3 = LaFactory.eINSTANCE.createCapabilityRealization("CR3"); //$NON-NLS-1$
    laRootCRPkg.getOwnedCapabilityRealizations().add(laCR3);

    laSubCRPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg("SubCRPkg"); //$NON-NLS-1$
    laRootCRPkg.getOwnedCapabilityRealizationPkgs().add(laSubCRPkg);

    laSubCR1 = LaFactory.eINSTANCE.createCapabilityRealization("SubCR1"); //$NON-NLS-1$
    laSubCRPkg.getOwnedCapabilityRealizations().add(laSubCR1);
    performCapabilityTransition(Arrays.asList(laRootCRPkg));
    mustNotBeNull(paCR2);
    // capa2 still remains in Ctx Layer without realization link
    assertTrue(NLS.bind(Messages.RealizationError, paCR2.getName(), laCR2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paCR2) == null));

    // capa3, SubcapaPkg and Subcapa1 are refined to Ctx Layer with realization links
    paCR3 = (CapabilityRealization) CodeHelper.getChildTracingElement(paRootCRPkg, laCR3);
    mustNotBeNull(paCR3);
    assertTrue(NLS.bind(Messages.WrongSize, laCR3.getName()),
        (laCR3.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, paCR3.getName()),
        (paCR3.eContainer() == paRootCRPkg));

    assertTrue(NLS.bind(Messages.RealizationError, paCR3.getName(), laCR3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paCR3) == laCR3));

    paSubCRPkg = paRootCRPkg.getOwnedCapabilityRealizationPkgs().get(0);
    mustNotBeNull(paSubCRPkg);
    assertTrue(NLS.bind(Messages.RealizationError, paSubCRPkg.getName(), laSubCRPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubCRPkg) == laSubCRPkg));

    paSubCR1 = paSubCRPkg.getOwnedCapabilityRealizations().get(0);
    mustNotBeNull(paSubCR1);
    assertTrue(NLS.bind(Messages.RealizationError, paSubCR1.getName(), laSubCR1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubCR1) == laSubCR1));
  }
  
  /**
   * Run the projection test from CR4
   * 
   * <pre>
   * Expected Result:\
   *               1. CR1, CR2, CR3, CR4 are projected towards PA Layer.\
   *               2. Involvements exist between CR1 and the transitioned LC1 and LA1
   *               3. Extension, inclusion and generalization links exist between CR1 and CR2, CR3, CR4
   * </pre>
   */
  public void performTestOnCR1() throws Exception {
    performCapabilityTransition(Collections.singletonList(CR1));
    CapabilityRealization paCR1 = mustBeTransitioned(ModelLaPa.CR1);
    assertTrue("There must be an involvement between the transitioned CR3 and the transitioned LC1 ",
        paCR1.getOwnedCapabilityRealizationInvolvements().stream().filter(i -> i.getInvolved() == PA_LC1).count() == 1);
    assertTrue("There must be an involvement between the transitioned CR3 and the transitioned LA1 ",
        paCR1.getOwnedCapabilityRealizationInvolvements().stream().filter(i -> i.getInvolved() == PA_LA1).count() == 1);
    CapabilityRealization laCR2 = mustBeTransitioned(ModelLaPa.CR2);
    CapabilityRealization laCR3 = mustBeTransitioned(ModelLaPa.CR3);
    CapabilityRealization laCR4 = mustBeTransitioned(ModelLaPa.CR4);
    paCR1.getExtendedAbstractCapabilities().contains(laCR2);
    paCR1.getIncludedAbstractCapabilities().contains(laCR3);
    paCR1.getSuper().contains(laCR4);
  }

}
