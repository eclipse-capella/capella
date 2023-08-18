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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Rename InterfacePkg in SA, SAI
 * - Create Interface SA1 in SA
 * - Create InterfacePkg SASI in System
 * - Create Interface SS1 in System
 * - Create Actor1
 * - Create InterfacePkg SAAI in Actor
 * - Create Interface SAC1 in Actor
 * 
 * - Rename InterfacePkg in LA, LAI
 * - Create Interface LA1 in LA
 * - Create InterfacePkg LASI in System
 * - Create Interface LS1 in System
 * - Create LC1
 * - Create InterfacePkg LASSI in Actor
 * - Create Interface LSS1 in Actor
 * - Delete InterfacePkg of PhysicalSystem
 * 
 * Perform transition on SAI, SASI, SAAI, LASSI
 * 
 * Expected Result:\
 * - SAI package should be transitioned as LAI
 * - SAAI package should be transitioned into LAI, since actor is not transitioned
 * - SASI into LogicalSystem
 * - LASSI into a sub folder of physical architecture
 * - Actor1 should not be transitioned
 * 
 * </pre>
 * 
 */
public class Exception_IP01_01 extends TopDownTransitionTestCase {
  private String id_sai = "a5a6bef3-9f01-47c4-8ff6-37da26b0d12a";
  private String id_sa1 = "0407c5aa-d00c-47ee-94ec-9925d55d041d";
  private String id_sasi = "2b8e4d77-cc2e-43e6-a55a-18164f37c70f";
  private String id_ss1 = "3a49a6e1-71fb-47d8-87e3-679493886f8f";
  private String id_saai = "e1fdcd64-3f30-49ce-b6e6-51789d5454f2";
  private String id_sac1 = "0e090ebb-87f6-46e5-8d77-1e16b0999a0f";
  private String id_lai = "cafde6c9-959b-4b67-8cbc-10acd5c8f334";
  private String id_la1 = "ffd856b2-88a1-46e4-89db-c9d7b9715ec4";
  private String id_lasi = "c444b209-e989-4787-b697-993a256d3bd4";
  private String id_lassi = "0aa9e56e-c953-4b58-bcd8-6110acdbd652";
  private String id_lss1 = "e8f696ec-e9c7-484d-b0ed-024a137020b2";

  private String id_actor_1 = "0a70f75c-038d-48e6-8df2-81ab7422331a";
  private String id_logical_system = "0f258a1d-ddc4-4d93-a8c2-1ba585cd318c";
  private String id_physical_architecture = "e7bad592-08a8-4417-b169-9b426574bd49";
  private String id_logicalcomponent_1 = "e64c4f54-6c40-4fc5-8f86-38a86239fd81";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
  }

  private void step1() {
    performInterfaceTransition(Arrays.asList(getObject(id_sai)));
    InterfacePkg sai = getObject(id_sai);
    assertNotNull(NLS.bind(Messages.NullElement, "SAI"), sai);

    // SAI must be transitioned (not required, a traceability link between root pkgs)
    // NamedElement sait = (InterfacePkg) ProjectionTestUtils.getAllocatingElement(sai);
    // assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sai.getName()), sait);

    Interface sa1 = getObject(id_sa1);
    assertNotNull(NLS.bind(Messages.NullElement, "SA1"), sa1);
    // SA1 must be transitioned
    NamedElement sa1t = (Interface) ProjectionTestUtils.getAllocatingElement(sa1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sa1.getName()), sa1t);

    // SAI ShouldBeRealizedBy LAI
    InterfacePkg lai = getObject(id_lai);
    // assertNotNull(NLS.bind(Messages.NullElement, "lai"), lai);
    // assertTrue(NLS.bind(Messages.ShouldBeRealizedBy, sai.getName(), lai.getName()), sait == lai);

    // sa1 ShouldBeContainedBy LAI
    assertNotNull(NLS.bind(Messages.NullElement, "lai"), lai);
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sai.getName(), lai.getName()), sa1t.eContainer() == lai);
  }

  private void step2() {
    performInterfaceTransition(Arrays.asList(getObject(id_sasi)));
    InterfacePkg sasi = getObject(id_sasi);
    assertNotNull(NLS.bind(Messages.NullElement, "SASI"), sasi);

    Interface ss1 = getObject(id_ss1);
    assertNotNull(NLS.bind(Messages.NullElement, "SS1"), ss1);
    // SS1 must be transitioned
    NamedElement ss1t = (Interface) ProjectionTestUtils.getAllocatingElement(ss1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ss1t.getName()), ss1t);

    LogicalComponent ls = getObject(id_logical_system);
    InterfacePkg lasi = getObject(id_lasi);
    // sasit ShouldBeContainedBy ls interfacepkg
    assertNotNull(NLS.bind(Messages.NullElement, "sasit"), lasi);
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, lasi.getName(), ls.getName()), lasi.eContainer() == ls);

    // ss1t ShouldBeContainedBy sasit
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, ss1t.getName(), lasi.getName()), ss1t.eContainer() == lasi);
  }

  private void step3() {
    performInterfaceTransition(Arrays.asList(getObject(id_saai)));
    InterfacePkg saai = getObject(id_saai);
    assertNotNull(NLS.bind(Messages.NullElement, "SAAI"), saai);
    // SAAI must be transitioned
    NamedElement saait = (InterfacePkg) ProjectionTestUtils.getAllocatingElement(saai);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, saai.getName()), saait);

    Interface sac1 = getObject(id_sac1);
    assertNotNull(NLS.bind(Messages.NullElement, "SAC1"), sac1);
    // SAC1 must be transitioned
    NamedElement sac1t = (Interface) ProjectionTestUtils.getAllocatingElement(sac1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sac1.getName()), sac1t);

    SystemComponent ac1 = getObject(id_actor_1);
    assertNotNull(NLS.bind(Messages.NullElement, "SAC1"), sac1);
    // ac1 mustn't be transitioned
    NamedElement ac1t = (Interface) ProjectionTestUtils.getAllocatingElement(ac1);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, ac1.getName()), ac1t);

    // SAI ShouldBeRealizedBy LAI
    InterfacePkg lai = getObject(id_lai);
    assertNotNull(NLS.bind(Messages.NullElement, "lai"), lai);
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, saait.getName(), lai.getName()), saait.eContainer() == lai);

    Interface la1 = getObject(id_la1);
    assertNotNull(NLS.bind(Messages.NullElement, "SAAI"), saai);
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, saait.getName(), lai.getName()), la1.eContainer() == lai);
  }

  private void step4() {
    performInterfaceTransition(Arrays.asList(getObject(id_lassi)));
    InterfacePkg lassi = getObject(id_lassi);
    assertNotNull(NLS.bind(Messages.NullElement, "LASSI"), lassi);
    // LASSI must be transitioned
    NamedElement lassit = (InterfacePkg) ProjectionTestUtils.getAllocatingElement(lassi);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, lassi.getName()), lassit);

    Interface lss1 = getObject(id_lss1);
    assertNotNull(NLS.bind(Messages.NullElement, "LSS1"), lss1);
    // LSS1 must be transitioned
    NamedElement lss1t = (Interface) ProjectionTestUtils.getAllocatingElement(lss1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, lss1.getName()), lss1t);

    LogicalComponent logicalcomponent_1 = getObject(id_logicalcomponent_1);
    assertNotNull(NLS.bind(Messages.NullElement, "LOGICALCOMPONENT_1"), logicalcomponent_1);
    // LOGICALCOMPONENT_1 should not be transitioned
    NamedElement logicalcomponent_1t = (LogicalComponent) ProjectionTestUtils.getAllocatingElement(logicalcomponent_1);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, logicalcomponent_1.getName()), logicalcomponent_1t);

    // lassi ShouldBeContainedBy physical architecture
    PhysicalArchitecture ps = getObject(id_physical_architecture);
    assertNotNull(NLS.bind(Messages.NullElement, "ps.interfacePkg"), ps.getOwnedInterfacePkg());
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, lassit.getName(), ps.getOwnedInterfacePkg().getName()),
        ps.getOwnedInterfacePkg() == lassit.eContainer());
  }

}
