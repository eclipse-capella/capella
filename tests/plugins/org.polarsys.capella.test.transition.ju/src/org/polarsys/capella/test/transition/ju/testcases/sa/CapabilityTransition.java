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
package org.polarsys.capella.test.transition.ju.testcases.sa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.test.transition.ju.CodeHelper;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on "SA Capability to LA Capability Realization" from System Analysis to Logical Analysis
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below.
 * 
 * <pre>
 *     Model Used: CTX-LA-Projection
 *     Model is created with the following elements�
 *         1. Create Operational Capability capa1 and capa2 under Capability Pkg
 *         2. Create capa11 in the Capability Realization Pkg, inheriting from capa1
 * </pre>
 */
public class CapabilityTransition extends TopDownTransitionTestCase {

  // Capabilities
  private CapabilityPkg _rootcapaPkg;
  private CapabilityPkg _saSubCapaPkg;

  private Capability _saCapa1;
  private Capability _saCapa11;
  private Capability _saCapa2;
  private Capability _saCapa3;
  private Capability _saSubCapa1;

  private CapabilityRealizationPkg _rootCRPkg;
  private CapabilityRealizationPkg _laSubCapaPkg;
  private CapabilityRealization _laCapa1;
  private CapabilityRealization _laCapa11;
  private CapabilityRealization _laCapa2;
  private CapabilityRealization _laCapa3;
  private CapabilityRealization _laSubCapa1;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _rootcapaPkg = (CapabilityPkg) getObject(ModelCtxLa.rootCapaPkgId);
    _saCapa1 = (Capability) getObject(ModelCtxLa.capa1Id);
    _saCapa11 = (Capability) getObject(ModelCtxLa.capa11Id);
    _saCapa2 = (Capability) getObject(ModelCtxLa.capa2Id);
    _rootCRPkg = (CapabilityRealizationPkg) getObject(ModelCtxLa.rootCRPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
  }

  
    /**
     * Run the projection test "SA Capability to LA Capability Realization" from capa11
     * 
     * <pre>
     * Expected Result:\
     *               1. capa11 is projected towards LA Layer.\
     *               2. LA capa11 realizes OA capa11
     * </pre>
     */

  public void performTest1() throws Exception {
            performCapabilityTransition(Collections.singletonList(_saCapa11));

            _laCapa11 = mustBeTransitioned(ModelCtxLa.capa11Id);
            mustNotBeNull(_saCapa11);

            assertTrue(NLS.bind(Messages.RealizationError, _laCapa11.getName(), _saCapa11.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laCapa11) == _saCapa11));
          }

    /**
     * Run the projection test "SA Capability to LA Capability Rezalization" from Capability Pkg
     * 
     * <pre>
     * Expected Result:\
     *               1. capa1, capa2 are projected towards LA Layer.\
     *               2. Appropriate realization links are created
     * </pre>
     */

  public void performTest2() throws Exception {
            performCapabilityTransition(Collections.singletonList(_rootcapaPkg));

            _laCapa1 = (CapabilityRealization) CodeHelper.getChildTracingElement(_rootCRPkg, _saCapa1);
            mustNotBeNull(_laCapa1);
            assertTrue(NLS.bind(Messages.WrongSize, _saCapa1.getName()), (_saCapa1.getIncomingCapabilityAllocation()
                .size() == 1));
            assertTrue(NLS.bind(Messages.WrongParent, _laCapa1.getName()), (_laCapa1.eContainer() == _rootCRPkg));

            assertTrue(NLS.bind(Messages.RealizationError, _laCapa1.getName(), _saCapa1.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laCapa1) == _saCapa1));

            _laCapa2 = (CapabilityRealization) CodeHelper.getChildTracingElement(_rootCRPkg, _saCapa2);

            mustNotBeNull(_laCapa2);

            assertTrue(NLS.bind(Messages.WrongSize, _saCapa2.getName()), (_saCapa2.getIncomingCapabilityAllocation()
                .size() == 1));
            assertTrue(NLS.bind(Messages.WrongParent, _laCapa2.getName()), (_laCapa2.eContainer() == _rootCRPkg));

            assertTrue(NLS.bind(Messages.RealizationError, _laCapa2.getName(), _saCapa2.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laCapa2) == _saCapa2));
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
            performCapabilityTransition(Collections.singletonList(_rootcapaPkg));

            int saSize = _rootcapaPkg.getOwnedCapabilities().size();
            int laSize = _rootCRPkg.getOwnedCapabilityRealizations().size();
            // Check no component is allcapaated newly
            assertTrue(Messages.WrongSize, (saSize == laSize));
          }

    /**
     * Run the projection test "SA Capability to LA Capability Realization" from Capability Pkg again
     * 
     * <pre>
     * Make the following changes in the model\
     *               1.  Delete capa2\
     *               2.  Add new Capability capa3\
     *               3.  Add sub Capability Pkg SubCapaPkg\
     *               4.  Add Subcapa1 in SubcapaPkg\
     * Expected Result:\
     *               1. capa2 still remains in LA Layer without realization link\
     *               2. capa3, SubcapaPkg and Subcapa1 are refined to LA Layer with realization links
     * </pre>
     */

  public void performTest4() throws Exception {
            // Delete capa2
            getExecutionManager(_rootcapaPkg).execute(new AbstractReadWriteCommand() {

              public void run() {
                _saCapa2.destroy();
                _saCapa3 = CtxFactory.eINSTANCE.createCapability("capa3"); //$NON-NLS-1$
                _rootcapaPkg.getOwnedCapabilities().add(_saCapa3);

                _saSubCapaPkg = CtxFactory.eINSTANCE.createCapabilityPkg("SubCapaPkg"); //$NON-NLS-1$
                _rootcapaPkg.getOwnedCapabilityPkgs().add(_saSubCapaPkg);

                _saSubCapa1 = CtxFactory.eINSTANCE.createCapability("SubCapa1"); //$NON-NLS-1$
                _saSubCapaPkg.getOwnedCapabilities().add(_saSubCapa1);
              }
            });

            // Add new Capability capa3
            // Add sub Capability Pkg SubCapaPkg
            // Add SubCapa1 in SubCapaPkg

            performCapabilityTransition(Collections.singletonList(_rootcapaPkg));
            mustNotBeNull(_laCapa2);
            // capa2 still remains in Ctx Layer without realization link
            assertTrue(NLS.bind(Messages.RealizationError, _laCapa2.getName(), _saCapa2.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laCapa2) == null));

            // capa3, SubcapaPkg and Subcapa1 are refined to Ctx Layer with realization links
            _laCapa3 = (CapabilityRealization) CodeHelper.getChildTracingElement(_rootCRPkg, _saCapa3);
            mustNotBeNull(_laCapa3);
            assertTrue(NLS.bind(Messages.WrongSize, _saCapa3.getName()), (_saCapa3.getIncomingCapabilityAllocation()
                .size() == 1));
            assertTrue(NLS.bind(Messages.WrongParent, _laCapa3.getName()), (_laCapa3.eContainer() == _rootCRPkg));

            assertTrue(NLS.bind(Messages.RealizationError, _laCapa3.getName(), _saCapa3.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laCapa3) == _saCapa3));

            _laSubCapaPkg = _rootCRPkg.getOwnedCapabilityRealizationPkgs().get(0);
            mustNotBeNull(_laSubCapaPkg);
            assertTrue(NLS.bind(Messages.RealizationError, _laSubCapaPkg.getName(), _saSubCapaPkg.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laSubCapaPkg) == _saSubCapaPkg));

            _laSubCapa1 = _laSubCapaPkg.getOwnedCapabilityRealizations().get(0);
            mustNotBeNull(_laSubCapa1);
            assertTrue(NLS.bind(Messages.RealizationError, _laSubCapa1.getName(), _saSubCapa1.getName()),
                (ProjectionTestUtils.getRealizedTargetElement(_laSubCapa1) == _saSubCapa1));
          }

}
