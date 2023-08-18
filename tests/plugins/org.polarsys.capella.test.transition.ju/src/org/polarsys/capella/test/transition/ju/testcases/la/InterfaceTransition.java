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
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "Interfaces Transition" from Logical Architecture to Physical Architecture
 * <P>
 * 
 * <pre>
 *     Model Used: LA-PA-Projection
 *     Model is created with the following elements ...
 *         1. Create "[CDI] Logical System - Contextual Component Detailed Interfaces" in Logical System
 *         2.  Create Sub Interface, Super Interface in the diagram
 *         3.  Create Generalization link between Sub and Super
 *         4.  Create Exchange Items EventEI, OperationEI, FlowEI and DataEI in the diagram.
 *         5.  Create Boolean Data Type BT1, Collection1 in DataPkg of LA
 *         6.  Create Exchange Item Element in EventEI typed with BT1 and FlowEI typed with Collection1.
 *         7.  Create Exchange Item Allocation of EventEI and OperationEI to Sub Interface and FlowEI and DataEI to Super Interface.
 *         8.  Create Leaf Interface in the diagram.
 *         9.  Create Sub Interface Pkg under Root Interface Pkg of LA, and an interface Sub INT inside the sub interface pkg.
 *         10. Create an undefined Exchange Item inside Sub INT.
 *         11. Save the diagram.
 * 
 * </pre>
 * 
 * Datatype transition is not enabled And the tests are documented in {@link #performTest()} method
 */
public class InterfaceTransition extends TopDownTransitionTestCase {

  // Target Objects
  private InterfacePkg rootLogicalIntPkg;
  private Interface laInterface1;
  private Interface laInterface2;
  private Interface laSubInterface;
  private Interface laSuperInterface;
  private ExchangeItem laEventEI;
  private ExchangeItem laOperationEI;
  private ExchangeItem laFlowEI;
  private ExchangeItem laDataEI;
  private BooleanType laBT1;
  private Collection laCollection1;
  private ExchangeItemElement laExchEltBT1;
  private ExchangeItemElement laExchEltColl1;
  private ExchangeItemAllocation laEventEIAlloc;
  private ExchangeItemAllocation laOperationEIAlloc;
  private ExchangeItemAllocation laFlowEIAlloc;
  private ExchangeItemAllocation laDataEIAlloc;
  private Interface laLeafInterface;
  private InterfacePkg laSubIntPkg;
  private Interface laInnerInt1;
  private Interface laInnerInt2;
  private ExchangeItem laUndefinedEI;
  private ExchangeItemAllocation laUndefinedEIAlloc;

  // Physical Layer
  private InterfacePkg rootPhysicalIntPkg;
  private InterfacePkg paLogicalInterfacesPkg;
  private Interface paInterface1;
  private Interface paInterface2;
  private Interface paSubInterface;
  private Interface paSuperInterface;
  private ExchangeItem paEventEI;
   private ExchangeItem paOperationEI;
  private ExchangeItem paFlowEI;
  private ExchangeItem paDataEI;
  private ExchangeItemElement paExchEltBT1;
  private ExchangeItemElement paExchEltColl1;
  private ExchangeItemAllocation paEventEIAlloc;
  private ExchangeItemAllocation paOperationEIAlloc;
  private ExchangeItemAllocation paFlowEIAlloc;
  private ExchangeItemAllocation paDataEIAlloc;
  private Interface paLeafInterface;
  private InterfacePkg paSubIntPkg;
  private Interface paInnerInt1;
  private Interface paInnerInt2;
  private ExchangeItem paUndefinedEI;
  private ExchangeItemAllocation paUndefinedEIAlloc;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__DATATYPE, false);
    rootLogicalIntPkg = getObject(ModelLaPa.rootLogicalIntPkgId);
    laInterface1 = getObject(ModelLaPa.interface1Id);
    laInterface2 = getObject(ModelLaPa.interface2Id);
    laSubInterface = getObject(ModelLaPa.subInterfaceId);
    laSuperInterface = getObject(ModelLaPa.superInterfaceId);
    laEventEI = getObject(ModelLaPa.eventEIId);
    laOperationEI = getObject(ModelLaPa.operationEIId);
    laFlowEI = getObject(ModelLaPa.flowEIId);
    laDataEI = getObject(ModelLaPa.dataEIId);
    laBT1 = getObject(ModelLaPa.bt1Id);
    laCollection1 = getObject(ModelLaPa.collection1Id);
    laExchEltBT1 = getObject(ModelLaPa.exchEltBT1Id);
    laExchEltColl1 = getObject(ModelLaPa.exchEltColl1Id);
    laEventEIAlloc = getObject(ModelLaPa.eventEIAllocId);
    laOperationEIAlloc = getObject(ModelLaPa.operationEIAllocId);
    laFlowEIAlloc = getObject(ModelLaPa.flowEIAllocId);
    laDataEIAlloc = getObject(ModelLaPa.dataEIAllocId);
    laLeafInterface = getObject(ModelLaPa.leafInterfaceId);
    laSubIntPkg = getObject(ModelLaPa.subIntPkgId);
    laInnerInt1 = getObject(ModelLaPa.innerInt1Id);
    laUndefinedEI = getObject(ModelLaPa.undefinedEIId);
    laUndefinedEIAlloc = getObject(ModelLaPa.undefinedEIAllocId);

    rootPhysicalIntPkg = getObject(ModelLaPa.rootPhysicalIntPkgId);
  }

  /**
   * Tests on "Actor Transition" Projection command:
   * 
   * <pre>
   *         1. Test on one leaf entity (Leaf Interface)
   *         2. Test on non leaf entity (Inner INT1)
   *         4. Test on Sub Interface
   *         5. Test on Root Interface Pkg with changes
   * </pre>
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    leafInterfaceTransitionTest();
    innerIntTransitionTest();
    subInterfaceTransitionTest();
    rootInterfacePkgTransitionTest();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Run the projection test "Interfaces Transition" from Leaf Interface
   * 
   * <pre>
   * Expected Result:\
   *               1.  A Sub Interface Pkg "Logical Interfaces" is created under root Interface Pkg of Physical Layer\
   *               2.  Leaf Interface is created in Logical Interfaces Pkg of Physical Layer\
   *               3.  Interface Realization Link is created between the interfaces
   * </pre>
   */
  private void leafInterfaceTransitionTest() {
    performInterfaceTransition(Arrays.asList(laLeafInterface));
    paLogicalInterfacesPkg = rootPhysicalIntPkg.getOwnedInterfacePkgs().get(0);
    mustNotBeNull(paLogicalInterfacesPkg);

    assertTrue(NLS.bind(Messages.RealizationError, paLogicalInterfacesPkg.getName(), rootLogicalIntPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paLogicalInterfacesPkg) == rootLogicalIntPkg));

    paLeafInterface = paLogicalInterfacesPkg.getOwnedInterfaces().get(0);
    mustNotBeNull(paLeafInterface);

    assertTrue(NLS.bind(Messages.RealizationError, paLeafInterface.getName(), laLeafInterface.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paLeafInterface) == laLeafInterface));
  }

  /**
   * Run the projection test "Interfaces Transition" from Inner INT1
   * 
   * <pre>
   * Expected Result:\
   *               1. Sub Interface Pkg is created under "Logical Interfaces" of Physical Layer\
   *               2.  Inner INT1 and undefined Exchange Item are projected inside Sub Interface Pkg in Physical Layer\
   *               3.  Inner INT1 in Physical Layer allocates the undefined EI.
   * </pre>
   */
  private void innerIntTransitionTest() {
    performInterfaceTransition(Arrays.asList(laInnerInt1));
    paSubIntPkg = paLogicalInterfacesPkg.getOwnedInterfacePkgs().get(0);
    mustNotBeNull(paSubIntPkg);

    assertTrue(NLS.bind(Messages.RealizationError, paSubIntPkg.getName(), laSubIntPkg.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubIntPkg) == laSubIntPkg));

    paInnerInt1 = paSubIntPkg.getOwnedInterfaces().get(0);
    mustNotBeNull(paInnerInt1);

    assertTrue(NLS.bind(Messages.RealizationError, paInnerInt1.getName(), laInnerInt1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paInnerInt1) == laInnerInt1));

    paUndefinedEI = paSubIntPkg.getOwnedExchangeItems().get(0);
    mustNotBeNull(paUndefinedEI);

    assertTrue(NLS.bind(Messages.RealizationError, paUndefinedEI.getName(), laUndefinedEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paUndefinedEI) == laUndefinedEI));

    paUndefinedEIAlloc = paInnerInt1.getOwnedExchangeItemAllocations().get(0);
    mustNotBeNull(paUndefinedEIAlloc);

    assertTrue(NLS.bind(Messages.RealizationError, paUndefinedEIAlloc, laUndefinedEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paUndefinedEIAlloc) == laUndefinedEIAlloc));

    assertTrue(NLS.bind(Messages.WrongAllocation, laUndefinedEI, paInnerInt1),
        (paUndefinedEIAlloc.getAllocatedItem() == paUndefinedEI));
  }

  /**
   * Run the projection test "Interfaces Transition" from Sub Interface
   * 
   * <pre>
   * Expected Result:\
   *               1. Sub Interface, Super Interface are projected inside Logical Interfaces Pkg\
   *               2.  All the exchange items are projected to physical layer\
   *               3.  Sub Interface allocates EventEI and OperationEI and super interface allocates FlowEI and DataEI\
   *               4.  Exchange Item Elements are also projected inside EventEI typed with BT1 and inside FlowEI typed with Collection1 of Logical Data Pkg
   * </pre>
   */
  private void subInterfaceTransitionTest() {
    performInterfaceTransition(Arrays.asList(laSubInterface));
    paSubInterface = ProjectionTestUtils.getAllocatingInterface(laSubInterface);
    mustNotBeNull(paSubInterface);
    assertTrue(NLS.bind(Messages.RealizationError, paSubInterface.getName(), laSubInterface.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSubInterface) == laSubInterface));

    paSuperInterface = ProjectionTestUtils.getAllocatingInterface(laSuperInterface);
    mustNotBeNull(paSuperInterface);
    assertTrue(NLS.bind(Messages.RealizationError, paSuperInterface.getName(), laSuperInterface.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paSuperInterface) == laSuperInterface));

    int projectedInterfaceSize = 3;
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Integer.valueOf(projectedInterfaceSize)),
        projectedInterfaceSize, paLogicalInterfacesPkg.getOwnedInterfaces().size());

    assertEquals(NLS.bind(Messages.ProjectionSizeError, 4), 4, paLogicalInterfacesPkg.getOwnedExchangeItems().size());

    // Exchange Items NOT PROJECTED ???
    paEventEI = ProjectionTestUtils.getAllocatingExchangeItem(laEventEI);
    mustNotBeNull(paEventEI);
    assertTrue(NLS.bind(Messages.RealizationError, paEventEI.getName(), laEventEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paEventEI) == laEventEI));

    paExchEltBT1 = paEventEI.getOwnedElements().get(0);
    mustNotBeNull(paExchEltBT1);
    assertTrue(NLS.bind(Messages.RealizationError, paExchEltBT1.getName(), laExchEltBT1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paExchEltBT1) == laExchEltBT1));
    assertTrue(NLS.bind(Messages.WrongType, paExchEltBT1.getName(), laBT1.getName()),
        (paExchEltBT1.getAbstractType() == laBT1));

    paOperationEI = ProjectionTestUtils.getAllocatingExchangeItem(laOperationEI);
    mustNotBeNull(paOperationEI);
    assertTrue(NLS.bind(Messages.RealizationError, paOperationEI.getName(), laOperationEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paOperationEI) == laOperationEI));

    paFlowEI = ProjectionTestUtils.getAllocatingExchangeItem(laFlowEI);
    mustNotBeNull(paFlowEI);
    assertTrue(NLS.bind(Messages.RealizationError, paFlowEI.getName(), laFlowEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFlowEI) == laFlowEI));

    paExchEltColl1 = paFlowEI.getOwnedElements().get(0);
    mustNotBeNull(paExchEltColl1);
    assertTrue(NLS.bind(Messages.RealizationError, paExchEltColl1.getName(), laExchEltColl1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paExchEltColl1) == laExchEltColl1));
    assertTrue(
        NLS.bind(Messages.WrongType, paExchEltColl1.getName(), laCollection1.getName()),
        (paExchEltColl1.getAbstractType() == laCollection1));

    paDataEI = ProjectionTestUtils.getAllocatingExchangeItem(laDataEI);
    mustNotBeNull(paDataEI);
    assertTrue(NLS.bind(Messages.RealizationError, paDataEI.getName(), laDataEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paDataEI) == laDataEI));

    // Exchange Item Allocations POINTING TO LA
    paOperationEIAlloc = paSubInterface.getOwnedExchangeItemAllocations().get(0);
    mustNotBeNull(paOperationEIAlloc);
    assertTrue(NLS.bind(Messages.RealizationError, paOperationEIAlloc, laOperationEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paOperationEIAlloc) == laOperationEIAlloc));
    assertTrue(NLS.bind(Messages.WrongAllocation, paOperationEIAlloc, paSubInterface),
        (paOperationEIAlloc.getAllocatedItem() == paOperationEI));

    paEventEIAlloc = paSubInterface.getOwnedExchangeItemAllocations().get(1);
    mustNotBeNull(paEventEIAlloc);
    assertTrue(NLS.bind(Messages.RealizationError, paEventEIAlloc, laEventEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paEventEIAlloc) == laEventEIAlloc));
    assertTrue(NLS.bind(Messages.WrongAllocation, paEventEIAlloc, paSubInterface),
        (paEventEIAlloc.getAllocatedItem() == paEventEI));

    paFlowEIAlloc = paSuperInterface.getOwnedExchangeItemAllocations().get(1);
    mustNotBeNull(paFlowEIAlloc);
    assertTrue(NLS.bind(Messages.RealizationError, paFlowEIAlloc, laFlowEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paFlowEIAlloc) == laFlowEIAlloc));
    assertTrue(NLS.bind(Messages.WrongAllocation, paFlowEIAlloc, paSubInterface),
        (paFlowEIAlloc.getAllocatedItem() == paFlowEI));

    paDataEIAlloc = paSuperInterface.getOwnedExchangeItemAllocations().get(0);
    mustNotBeNull(paDataEIAlloc);
    assertTrue(NLS.bind(Messages.RealizationError, paDataEIAlloc, laDataEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paDataEIAlloc) == laDataEIAlloc));
    assertTrue(NLS.bind(Messages.WrongAllocation, paDataEIAlloc, paSubInterface),
        (paDataEIAlloc.getAllocatedItem() == paDataEI));
  }

  /**
   * Run the projection test "Interfaces Transition" from Sub Interface
   * 
   * <pre>
   * Expected Result:\
   *               1. Sub Interface, Super Interface are projected inside Logical Interfaces Pkg\
   *               2.  All the exchange items are projected to physical layer\
   *               3.  Sub Interface allocates EventEI and OperationEI and super interface allocates FlowEI and DataEI\
   *               4.  Exchange Item Elements are also projected inside EventEI typed with BT1 and inside FlowEI typed with Collection1 of Logical Data Pkg
   * </pre>
   */
  private void rootInterfacePkgTransitionTest() {
    // Remove FlowEI from physical layer DOESN't EXIST
    paFlowEI.destroy();
    // Add new Sub Interface Inner INT2 to the Sub Interface Pkg\
    laInnerInt2 = CsFactory.eINSTANCE.createInterface("Inner INT2"); //$NON-NLS-1$
    laSubIntPkg.getOwnedInterfaces().add(laInnerInt2);
    // Allocate all the Exchange Items (FlowEI, EventEI, OperationEI and DataEI) to Leaf Interface
    ExchangeItemAllocation leafIntEventEIAlloc = CsFactory.eINSTANCE.createExchangeItemAllocation();
    leafIntEventEIAlloc.setAllocatedItem(laEventEI);
    laLeafInterface.getOwnedExchangeItemAllocations().add(leafIntEventEIAlloc);

    ExchangeItemAllocation leafIntOperationEIAlloc = CsFactory.eINSTANCE.createExchangeItemAllocation();
    leafIntOperationEIAlloc.setAllocatedItem(laOperationEI);
    laLeafInterface.getOwnedExchangeItemAllocations().add(leafIntOperationEIAlloc);

    ExchangeItemAllocation leafIntFlowEIAlloc = CsFactory.eINSTANCE.createExchangeItemAllocation();
    leafIntFlowEIAlloc.setAllocatedItem(laFlowEI);
    laLeafInterface.getOwnedExchangeItemAllocations().add(leafIntFlowEIAlloc);

    ExchangeItemAllocation leafIntDataEIAlloc = CsFactory.eINSTANCE.createExchangeItemAllocation();
    leafIntDataEIAlloc.setAllocatedItem(laDataEI);
    laLeafInterface.getOwnedExchangeItemAllocations().add(leafIntDataEIAlloc);

    performInterfaceTransition(Arrays.asList(rootLogicalIntPkg));

    // Interfaces 1 & 2 are projected to physical layer (these were created during actor transition)
    paInterface1 = ProjectionTestUtils.getAllocatingInterface(laInterface1);
    mustNotBeNull(paInterface1);
    assertTrue(NLS.bind(Messages.RealizationError, paInterface1.getName(), laInterface1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paInterface1) == laInterface1));

    paInterface2 = ProjectionTestUtils.getAllocatingInterface(laInterface2);
    mustNotBeNull(paInterface2);
    assertTrue(NLS.bind(Messages.RealizationError, paInterface2.getName(), laInterface2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paInterface2) == laInterface2));

    int projectedInterfaceSize = 5;
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Integer.valueOf(projectedInterfaceSize)),
        paLogicalInterfacesPkg.getOwnedInterfaces().size(), projectedInterfaceSize);

    int projectedExchangeItemSize = 4;
    assertEquals(NLS.bind(Messages.ProjectionSizeError, Integer.valueOf(projectedExchangeItemSize)),
        paLogicalInterfacesPkg.getOwnedExchangeItems().size(), projectedExchangeItemSize);

    // Flow EI re-projected
    paFlowEI = ProjectionTestUtils.getAllocatingExchangeItem(laFlowEI);
    mustNotBeNull(paFlowEI);
    assertTrue(NLS.bind(Messages.RealizationError, paFlowEI.getName(), laFlowEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFlowEI) == laFlowEI));

    paEventEI = ProjectionTestUtils.getAllocatingExchangeItem(laEventEI);
    mustNotBeNull(paEventEI);
    assertTrue(NLS.bind(Messages.RealizationError, paEventEI.getName(), laEventEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paEventEI) == laEventEI));

    paOperationEI = ProjectionTestUtils.getAllocatingExchangeItem(laOperationEI);
    mustNotBeNull(paOperationEI);
    assertTrue(NLS.bind(Messages.RealizationError, paOperationEI.getName(), laOperationEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paOperationEI) == laOperationEI));

    paFlowEI = ProjectionTestUtils.getAllocatingExchangeItem(laFlowEI);
    mustNotBeNull(paFlowEI);
    assertTrue(NLS.bind(Messages.RealizationError, paFlowEI.getName(), laFlowEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paFlowEI) == laFlowEI));

    paDataEI = ProjectionTestUtils.getAllocatingExchangeItem(laDataEI);
    mustNotBeNull(paDataEI);
    assertTrue(NLS.bind(Messages.RealizationError, paDataEI.getName(), laDataEI.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paDataEI) == laDataEI));

    paExchEltColl1 = paFlowEI.getOwnedElements().get(0);
    mustNotBeNull(paExchEltColl1);
    assertTrue(NLS.bind(Messages.RealizationError, paExchEltColl1.getName(), laExchEltColl1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paExchEltColl1) == laExchEltColl1));
    assertTrue(
        NLS.bind(Messages.WrongType, paExchEltColl1.getName(), laCollection1.getName()),
        (paExchEltColl1.getAbstractType() == laCollection1));

    // Exchange Item Allocations
    paFlowEIAlloc = paSuperInterface.getOwnedExchangeItemAllocations().get(1);
    mustNotBeNull(paFlowEIAlloc);
    assertTrue(NLS.bind(Messages.RealizationError, paFlowEIAlloc, laFlowEIAlloc),
        (ProjectionTestUtils.getRealizedTargetElement(paFlowEIAlloc) == laFlowEIAlloc));
    assertTrue(NLS.bind(Messages.WrongAllocation, paFlowEIAlloc, paSubInterface),
        (paFlowEIAlloc.getAllocatedItem() == paFlowEI));

    // Inner INT2
    paInnerInt2 = paSubIntPkg.getOwnedInterfaces().get(1);
    mustNotBeNull(paInnerInt2);
    assertTrue(NLS.bind(Messages.RealizationError, paInnerInt2, laInnerInt2),
        (ProjectionTestUtils.getRealizedTargetElement(paInnerInt2) == laInnerInt2));

    // All exchange items in physical layer (FlowEI, EventEI, OperationEI and DataEI) are allocated by Leaf
    // Interface in Physical Layer
    assertTrue(Messages.WrongAllocation,
        paLeafInterface.getExchangeItems().containsAll(Arrays.asList(paFlowEI, paDataEI, paEventEI, paOperationEI)));

    // Exchange Item Allocations now projected
    paExchEltBT1 = paEventEI.getOwnedElements().get(0);
    mustNotBeNull(paExchEltBT1);
    assertTrue(NLS.bind(Messages.RealizationError, paExchEltBT1.getName(), laExchEltBT1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paExchEltBT1) == laExchEltBT1));
    assertTrue(NLS.bind(Messages.WrongType, paExchEltBT1.getName(), laBT1.getName()),
        (paExchEltBT1.getAbstractType() == laBT1));

    paExchEltColl1 = paFlowEI.getOwnedElements().get(0);
    mustNotBeNull(paExchEltColl1);
    assertTrue(NLS.bind(Messages.RealizationError, paExchEltColl1.getName(), laExchEltColl1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paExchEltColl1) == laExchEltColl1));
    assertTrue(
        NLS.bind(Messages.WrongType, paExchEltColl1.getName(), laCollection1.getName()),
        (paExchEltColl1.getAbstractType() == laCollection1));
  }
}
