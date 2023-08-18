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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelLaPa;

/**
 * Projection Tests on "Exchange Item Transition" from Logical Analysis to Physical Layer
 * <P>
 * This is done with the model "LA-PA-Projection". The model is created as explained below. And the tests are documented
 * in {@link #performTest()} method
 */
public class ExchangeItemTransition extends TopDownTransitionTestCase {

  // Data Pkg
  private DataPkg laDataPkg;

  // Exchange Items & Exchange Item Elements
  private ExchangeItem laei1;
  private ExchangeItemElement laeie1_1;
  private ExchangeItemElement laeie1_2;
  private ExchangeItem laei2;
  private ExchangeItemElement laeie2_1;
  private ExchangeItem laei3;
  private ExchangeItemElement laeie3_1;
  private ExchangeItem laei4;
  private ExchangeItemElement laeie4_1;
  private ExchangeItem laei5;
  private ExchangeItemElement laeie5_1;
  private ExchangeItemElement laeie5_2;

  private DataPkg paDataPkg;
  // Exchange Items & Exchange Item Elements
  private ExchangeItem paei1;
  private ExchangeItemElement paeie1_1;
  private ExchangeItemElement paeie1_2;
  private ExchangeItem paei2;
  private ExchangeItemElement paeie2_1;
  private ExchangeItem paei3;
  private ExchangeItemElement paeie3_1;
  private ExchangeItem paei4;
  private ExchangeItemElement paeie4_1;
  private ExchangeItem paei5;
  private ExchangeItemElement paeie5_1;
  private ExchangeItemElement paeie5_2;

  private void initSession() {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    context = new SessionContext(session);
    laDataPkg = getObject(ModelLaPa.laDataPkgId);
    laei1 = getObject(ModelLaPa.ei1Id);
    laeie1_1 = getObject(ModelLaPa.eie1_1Id);
    laei2 = getObject(ModelLaPa.ei2Id);
    laeie2_1 = getObject(ModelLaPa.eie2_1Id);
    laei3 = getObject(ModelLaPa.ei3Id);
    laeie3_1 = getObject(ModelLaPa.eie3_1Id);
    laei4 = getObject(ModelLaPa.ei4Id);
    laeie4_1 = getObject(ModelLaPa.eie4_1Id);
    laei5 = getObject(ModelLaPa.ei5Id);
    laeie5_1 = getObject(ModelLaPa.eie5_1Id);
    laeie5_2 = getObject(ModelLaPa.eie5_2Id);
    paDataPkg = getObject(ModelLaPa.paDataPkgId);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  /**
   * Tests on "Exchange Item Transition" Projection command should be applied:
   * 
   * @see org.polarsys.capella.test.common.AbstractExtendedTestSuite#getTests()
   */
  @Override
  public void performTest() throws Exception {
    initSession();
    exchangeItem1TransitionTest();
    exchangeItem2TransitionTest();
    exchangeItem3TransitionTest();
    exchangeItem3TransitionTest();
    exchangeItem4TransitionTest();
    exchangeItem4TransitionTest();
    exchangeItem5TransitionTest();
    exchangeItem1Transition2Test();
    ei1TransitionAfterChangesTest();
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 1
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 1 is transitioned to PA Layer keeping its mechanism (EVENT).\
   *               2. ExchangeItemElement1_1 is also transitioned keeping its  type (MyBooleanType)
   * </pre>
   */
  private void exchangeItem1TransitionTest() {
    performExchangeItemTransition(Arrays.asList(laei1));
    // ExchangeItem 1
    paei1 = paDataPkg.getOwnedExchangeItems().get(0);
    assertNotNull(Messages.NullError, paei1);
    assertTrue(NLS.bind(Messages.RealizationError, paei1.getName(), laei1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paei1) == laei1));
    ExchangeMechanism paei1Mechanism = paei1.getExchangeMechanism();
    ExchangeMechanism laei1Mechanism = laei1.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei1.getName(),
        paei1Mechanism.getName(), laei1Mechanism.getName()), paei1Mechanism.equals(laei1Mechanism));

    // ExchangeItem1_1
    paeie1_1 = paei1.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, paeie1_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie1_1.getName(), laeie1_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie1_1) == laeie1_1));
    Type paeie1_1Type = paeie1_1.getType();
    Type laeie1_1Type = laeie1_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie1_1.getName(),
        paeie1_1Type.getName(), laeie1_1Type.getName()), paeie1_1Type.equals(laeie1_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 2
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 2 is transitioned to PA Layer keeping its mechanism (OPERATION).\
   *               2. ExchangeItemElement2_1 is also transitioned keeping its  type (Byte)
   * </pre>
   */
  private void exchangeItem2TransitionTest() {
    performExchangeItemTransition(Arrays.asList(laei2));
    // ExchangeItem 2
    paei2 = paDataPkg.getOwnedExchangeItems().get(1);
    assertNotNull(Messages.NullError, paei2);
    assertTrue(NLS.bind(Messages.RealizationError, paei2.getName(), laei2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paei2) == laei2));
    ExchangeMechanism paei2Mechanism = paei2.getExchangeMechanism();
    ExchangeMechanism laei2Mechanism = laei2.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei2.getName(),
        paei2Mechanism.getName(), laei2Mechanism.getName()), paei2Mechanism.equals(laei2Mechanism));

    // ExchangeItem2_1
    paeie2_1 = paei2.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, paeie2_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie2_1.getName(), laeie2_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie2_1) == laeie2_1));
    Type paeie2_1Type = paeie2_1.getType();
    Type laeie2_1Type = laeie2_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie2_1.getName(),
        paeie2_1Type.getName(), laeie2_1Type.getName()), paeie2_1Type.equals(laeie2_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 3
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 3 is transitioned to PA Layer keeping its mechanism (FLOW).\
   *               2. ExchangeItemElement3_1 is also transitioned keeping its  type (String)
   * </pre>
   */
  private void exchangeItem3TransitionTest() {
    performExchangeItemTransition(Arrays.asList(laei3));
    // ExchangeItem 3
    paei3 = paDataPkg.getOwnedExchangeItems().get(2);
    assertNotNull(Messages.NullError, paei3);
    assertTrue(NLS.bind(Messages.RealizationError, paei3.getName(), laei3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paei3) == laei3));
    ExchangeMechanism paei3Mechanism = paei3.getExchangeMechanism();
    ExchangeMechanism laei3Mechanism = laei3.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei3.getName(),
        paei3Mechanism.getName(), laei3Mechanism.getName()), paei3Mechanism.equals(laei3Mechanism));

    // ExchangeItem3_1
    paeie3_1 = paei3.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, paeie3_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie3_1.getName(), laeie3_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie3_1) == laeie3_1));
    Type paeie3_1Type = paeie3_1.getType();
    Type laeie3_1Type = laeie3_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie3_1.getName(),
        paeie3_1Type.getName(), laeie3_1Type.getName()), paeie3_1Type.equals(laeie3_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 4
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 4 is transitioned to PA Layer keeping its mechanism (SHARED_DATA).\
   *               2. ExchangeItemElement4_1 is also transitioned keeping its  type (Double)
   * </pre>
   */
  private void exchangeItem4TransitionTest() {
    performExchangeItemTransition(Arrays.asList(laei4));
    // ExchangeItem 4
    paei4 = paDataPkg.getOwnedExchangeItems().get(3);
    assertNotNull(Messages.NullError, paei4);
    assertTrue(NLS.bind(Messages.RealizationError, paei4.getName(), laei4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paei4) == laei4));
    ExchangeMechanism paei4Mechanism = paei4.getExchangeMechanism();
    ExchangeMechanism laei4Mechanism = laei4.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei4.getName(),
        paei4Mechanism.getName(), laei4Mechanism.getName()), paei4Mechanism.equals(laei4Mechanism));

    // ExchangeItem4_1
    paeie4_1 = paei4.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, paeie4_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie4_1.getName(), laeie4_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie4_1) == laeie4_1));
    Type paeie4_1Type = paeie4_1.getType();
    Type laeie4_1Type = laeie4_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie4_1.getName(),
        paeie4_1Type.getName(), laeie4_1Type.getName()), paeie4_1Type.equals(laeie4_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 5
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 5 is transitioned to PA Layer keeping its mechanism (UNSET).\
   *               2. ExchangeItemElement5_1 is also transitioned keeping its  type (Hexadecimal)\
   *               3. ExchangeItemElement5_2 is also transitioned keeping its  type (Integer)
   * </pre>
   */
  private void exchangeItem5TransitionTest() {
    performExchangeItemTransition(Arrays.asList(laei5));
    // ExchangeItem 5
    paei5 = paDataPkg.getOwnedExchangeItems().get(4);
    assertNotNull(Messages.NullError, paei5);
    assertTrue(NLS.bind(Messages.RealizationError, paei5.getName(), laei5.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paei5) == laei5));
    ExchangeMechanism paei5Mechanism = paei5.getExchangeMechanism();
    ExchangeMechanism laei5Mechanism = laei5.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei5.getName(),
        paei5Mechanism.getName(), laei5Mechanism.getName()), paei5Mechanism.equals(laei5Mechanism));

    // ExchangeItem5_1
    paeie5_1 = paei5.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, paeie5_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie5_1.getName(), laeie5_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie5_1) == laeie5_1));
    Type paeie5_1Type = paeie5_1.getType();
    Type laeie5_1Type = laeie5_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie5_1.getName(),
        paeie5_1Type.getName(), laeie5_1Type.getName()), paeie5_1Type.equals(laeie5_1Type));

    // ExchangeItem5_2
    paeie5_2 = paei5.getOwnedElements().get(1);
    assertNotNull(Messages.NullError, paeie5_2);
    assertTrue(NLS.bind(Messages.RealizationError, paeie5_2.getName(), laeie5_2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie5_2) == laeie5_2));
    Type paeie5_2Type = paeie5_2.getType();
    Type laeie5_2Type = laeie5_2.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, paeie5_2.getName(),
        paeie5_2Type.getName(), laeie5_2Type.getName()), paeie5_2Type.equals(laeie5_2Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 1 a second time
   * 
   * <pre>
   * Expected Result:\
   *             1. No changes occur, and the elements are intact.
   * </pre>
   */
  private void exchangeItem1Transition2Test() {
    performExchangeItemTransition(Arrays.asList(laei1));
    assertEquals(Messages.ProjectionSizeError, laDataPkg.getOwnedExchangeItems().size(),
        paDataPkg.getOwnedExchangeItems().size());
    assertEquals(Messages.ProjectionSizeError, laei1.getOwnedElements().size(), paei1.getOwnedElements().size());
    ExchangeMechanism paei1Mechanism = paei1.getExchangeMechanism();
    ExchangeMechanism laei1Mechanism = laei1.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, paei1.getName(),
        paei1Mechanism.getName(), laei1Mechanism.getName()), paei1Mechanism.equals(laei1Mechanism));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 1 after making the changes.
   * 
   * <pre>
   * Make the following changes in the elements\
   *                 1.  Delete ExchangeItemElement1_1\
   *                 2.  Create new ExchangeItemElement1_2\
   *                 3.  set ExchangeItem 1 mechanism to EVENT\ 
   * Expected Result:\
   *                 1.  ExchangeItem1_1 in Logical Layer still exists but the realization link is removed.\
   *                 2.  ExchangeItemElement1_2 is created in Logical Layer with the association link\
   *                 3.  ExchangeItem 1 mechanism has changed\
   * </pre>
   */
  private void ei1TransitionAfterChangesTest() {
    // Delete ExchangeItemElement1_1
    laeie1_1.destroy();
    // Create new ExchangeItemElement1_2
    // set ExchangeItem 1 mechanism to FLOW
    laeie1_2 = InformationFactory.eINSTANCE.createExchangeItemElement("ExchangeItemElement1_2");//$NON-NLS-1$
    laei1.getOwnedElements().add(laeie1_2);

    laei1.setExchangeMechanism(ExchangeMechanism.FLOW);
    performExchangeItemTransition(Arrays.asList(laei1));
    assertNotNull(Messages.NullError, paeie1_1);
    assertTrue(NLS.bind(Messages.RealizationError, paeie1_1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(paeie1_1) == null));
    // check that ExchangeItemElement1_2 is added
    paeie1_2 = paei1.getOwnedElements().get(1);
    assertNotNull(Messages.NullError, paeie1_2);
    assertTrue(NLS.bind(Messages.RealizationError, paeie1_2.getName(), laeie1_2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(paeie1_2) == laeie1_2));

    // check that ExchangeItem 1 mechanism has changed to FLOW
    assertTrue(
        MessageFormat.format(Messages.WrongValue, laei1.getName(),
            laei1.getExchangeMechanism().getName(), ExchangeMechanism.FLOW.getName()),
        paei1.getExchangeMechanism().equals(ExchangeMechanism.FLOW));
  }
}
