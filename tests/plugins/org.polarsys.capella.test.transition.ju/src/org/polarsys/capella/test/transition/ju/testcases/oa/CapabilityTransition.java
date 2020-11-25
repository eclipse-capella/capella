/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.oa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelOaSa;

/**
 * Projection Tests on "OA Capability to SA Capability" from Operational Analysis to System Analysis
 * <P>
 * This is done with the model "OA-SA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: OA-SA-Projection
 *     Model is created with the following elements�
 *         1. Create Operational Capability OC1 and OC2 under Operational Capability Pkg
 *         2. Create OC11 in the Operational Capability Pkg, inheriting from OC1
 *         3. Allocate OA11 to OC1
 * </pre>
 * 
 * 
 */
public class CapabilityTransition extends TopDownTransitionTestCase {

  // Operational Capabilities
  private OperationalCapabilityPkg _rootOCPkg;
  private OperationalCapabilityPkg _subOCPkg;

  private OperationalCapability _oaOC1;
  private OperationalCapability _oaOC11;
  private OperationalCapability _oaOC2;
  private OperationalCapability _oaOC3;
  private OperationalCapability _oaSubOC1;
  private OperationalCapability OC1;

  private CapabilityPkg _rootCapabilityPkg;
  private CapabilityPkg _subCapabilityPkg;
  private Capability _ctxOC1;
  private Capability _ctxOC11;
  private Capability _ctxOC2;
  private Capability _ctxOC3;
  private Capability _ctxSubOC1;
  
  private SystemComponent SA_SYSTEM;
  private SystemComponent SA_OE3;
  private SystemComponent SA_OA1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _rootOCPkg = (OperationalCapabilityPkg) getObject(ModelOaSa.rootOCPkgId);
    _oaOC1 = (OperationalCapability) getObject(ModelOaSa.oc1Id);
    _oaOC11 = (OperationalCapability) getObject(ModelOaSa.oc11Id);
    _oaOC2 = (OperationalCapability) getObject(ModelOaSa.oc2Id);
    _rootCapabilityPkg = (CapabilityPkg) getObject(ModelOaSa.rootCapabilityPkgId);
    OC1 = (OperationalCapability) getObject(ModelOaSa.OC1);
    SA_SYSTEM = (SystemComponent) getObject(ModelOaSa.SA_SYSTEM);
    SA_OE3 = (SystemComponent) getObject(ModelOaSa.SA_OE3);
    SA_OA1 = (SystemComponent) getObject(ModelOaSa.SA_OA1);
    
    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
  }

  /**
   * Run the projection test "OA Capability to SA Capability" from OC11
   * 
   * <pre>
   * Expected Result:\
   *               1. OC11 is projected towards Ctx Layer.\
   *               2. Ctx OC11 realizes OA OC11
   * </pre>
   */
  public void performTest1() throws Exception {
    performCapabilityTransition(Collections.singletonList(_oaOC11));
    _ctxOC11 = mustBeTransitioned(ModelOaSa.oc11Id);

    assertTrue(NLS.bind(Messages.RealizationError, _ctxOC11.getName(), _oaOC11.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxOC11) == _oaOC11));
  }

  /**
   * Run the projection test "OA Capability to SA Capability" from Operational Capability Pkg
   * 
   * <pre>
   * Expected Result:\
   *               1. OC1, OC2 are projected towards Ctx Layer.\
   *               2. Appropriate realization links are created
   * </pre>
   */

  public void performTest2() throws Exception {
    performCapabilityTransition(Collections.singletonList(_rootOCPkg));
    _ctxOC1 = ProjectionTestUtils.getProjectedCapability(_oaOC1);
    mustNotBeNull(_ctxOC1);
    assertTrue(NLS.bind(Messages.WrongAllocation, _oaOC1.getName()),
        (_oaOC1.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, _ctxOC1.getName()),
        (_ctxOC1.eContainer() == _rootCapabilityPkg));

    assertTrue(NLS.bind(Messages.RealizationError, _ctxOC1.getName(), _oaOC1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxOC1) == _oaOC1));

    _ctxOC2 = ProjectionTestUtils.getProjectedCapability(_oaOC2);

    mustNotBeNull(_ctxOC2);

    assertTrue(NLS.bind(Messages.WrongAllocation, _oaOC2.getName()),
        (_oaOC2.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, _ctxOC2.getName()),
        (_ctxOC2.eContainer() == _rootCapabilityPkg));

    assertTrue(NLS.bind(Messages.RealizationError, _ctxOC2.getName(), _oaOC2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxOC2) == _oaOC2));
  }

  /**
   * Run the projection test "OA Capability to SA Capability" from Operational Capability Pkg again
   * 
   * <pre>
   * Expected Result:\
   *               1. No changes, and the elements are intact
   * </pre>
   */

  public void performTest3() throws Exception {
    performCapabilityTransition(Collections.singletonList(_rootOCPkg));
    int oaSize = _rootOCPkg.getOwnedOperationalCapabilities().size();
    int ctxSize = _rootCapabilityPkg.getOwnedCapabilities().size();
    // Check no component is allocated newly
    assertTrue(Messages.WrongAllocation, (oaSize == ctxSize));
  }

  /**
   * Run the projection test "OA Capability to SA Capability" from Operational Capability Pkg again
   * 
   * <pre>
   * Make the following changes in the model\
   *               1.  Delete OC2\
   *               2.  Add new Operational Capability OC3\
   *               3.  Add sub Operational Capability Pkg SubOCPkg\
   *               4.  Add SubOC1 in SubOCPkg\
   * Expected Result:\
   *               1. OC2 still remains in Ctx Layer without realization link\
   *               2. OC3, SubOCPkg and SubOC1 are refined to Ctx Layer with realization links
   * </pre>
   */

  public void performTest4() throws Exception {
    // Delete OC2

    // Add new Operational Capability OC3
    // Add sub Operational Capability Pkg SubOCPkg
    // Add SubOC1 in SubOCPkg
    getExecutionManager(_rootOCPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _oaOC2.destroy();
        _oaOC3 = OaFactory.eINSTANCE.createOperationalCapability("OC3"); //$NON-NLS-1$
        _rootOCPkg.getOwnedOperationalCapabilities().add(_oaOC3);

        _subOCPkg = OaFactory.eINSTANCE.createOperationalCapabilityPkg("Sub OC Pkg"); //$NON-NLS-1$
        _rootOCPkg.getOwnedOperationalCapabilityPkgs().add(_subOCPkg);

        _oaSubOC1 = OaFactory.eINSTANCE.createOperationalCapability("Sub OC1"); //$NON-NLS-1$
        _subOCPkg.getOwnedOperationalCapabilities().add(_oaSubOC1);
      }
    });

    performCapabilityTransition(Collections.singletonList(_rootOCPkg));

    mustNotBeNull(_ctxOC2);
    // OC2 still remains in Ctx Layer without realization link
    assertTrue(NLS.bind(Messages.RealizationError, _ctxOC2.getName(), _oaOC2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxOC2) == null));

    // OC3, SubOCPkg and SubOC1 are refined to Ctx Layer with realization links
    _ctxOC3 = ProjectionTestUtils.getProjectedCapability(_oaOC3);
    mustNotBeNull(_ctxOC3);
    assertTrue(NLS.bind(Messages.WrongAllocation, _oaOC3.getName()),
        (_oaOC3.getIncomingCapabilityAllocation().size() == 1));
    assertTrue(NLS.bind(Messages.WrongParent, _ctxOC3.getName()),
        (_ctxOC3.eContainer() == _rootCapabilityPkg));

    assertTrue(NLS.bind(Messages.RealizationError, _ctxOC3.getName(), _oaOC3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxOC3) == _oaOC3));

    _subCapabilityPkg = _rootCapabilityPkg.getOwnedCapabilityPkgs().get(0);
    mustNotBeNull(_subCapabilityPkg);
    assertTrue(NLS.bind(Messages.RealizationError, _subCapabilityPkg.getName(), _subOCPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_subCapabilityPkg) == _subOCPkg));

    _ctxSubOC1 = _subCapabilityPkg.getOwnedCapabilities().get(0);
    mustNotBeNull(_ctxSubOC1);
    assertTrue(NLS.bind(Messages.RealizationError, _ctxSubOC1.getName(), _oaSubOC1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_ctxSubOC1) == _oaSubOC1));
  }
  
  /**
   * Run the projection test "OA Capability to SA Capability" from OC3
   * 
   * <pre>
   * Expected Result:\
   *               1. OC1, OC2, OC3, OC4 are projected towards Ctx Layer.\
   *               2. Only one involvement is created between the transitioned OC1 and the System
   *               3. Involvements exist between OC1 and the transitioned OE3 and OA1
   *               4. Extension, inclusion and generalization links exist between OC1 and OC2, OC3, OC4
   * </pre>
   */
  public void performTest5() throws Exception {
    performCapabilityTransition(Collections.singletonList(OC1));
    Capability ctxOC1 = mustBeTransitioned(ModelOaSa.OC1);
    assertTrue("There must be only one involvement between the transitioned OC3 and the System ",
        ctxOC1.getOwnedCapabilityInvolvements().stream().filter(i -> i.getInvolved() == SA_SYSTEM).count() == 1);
    assertTrue("There must be an involvement between the transitioned OC3 and the transitioned OE3 ",
        ctxOC1.getOwnedCapabilityInvolvements().stream().filter(i -> i.getInvolved() == SA_OE3).count() == 1);
    assertTrue("There must be an involvement between the transitioned OC3 and the transitioned OA1 ",
        ctxOC1.getOwnedCapabilityInvolvements().stream().filter(i -> i.getInvolved() == SA_OA1).count() == 1);
    Capability ctxOC2 = mustBeTransitioned(ModelOaSa.OC2);
    Capability ctxOC3 = mustBeTransitioned(ModelOaSa.OC3);
    Capability ctxOC4 = mustBeTransitioned(ModelOaSa.OC4);
    ctxOC1.getExtendedAbstractCapabilities().contains(ctxOC2);
    ctxOC1.getIncludedAbstractCapabilities().contains(ctxOC3);
    ctxOC1.getSuper().contains(ctxOC4);
  }

}
