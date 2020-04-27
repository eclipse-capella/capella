/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;
import org.polarsys.capella.test.transition.ju.model.ModelCtxLa;

/**
 * Projection Tests on "Exchange Item Transition" from System Analysis to Logical Layer
 * <P>
 * This is done with the model "CTX-LA-Projection". The model is created as explained below. And the tests are
 * documented in {@link #getTests()} method
 */
public class ExchangeItemTransition extends TopDownTransitionTestCase {

  // Data Pkg
  private DataPkg _ctxDataPkg;

  // Exchange Items & Exchange Item Elements
  private ExchangeItem _saei1;
  private ExchangeItemElement _saeie1_1;
  private ExchangeItemElement _saeie1_2;
  private ExchangeItem _saei2;
  private ExchangeItemElement _saeie2_1;
  private ExchangeItem _saei3;
  private ExchangeItemElement _saeie3_1;
  private ExchangeItem _saei4;
  private ExchangeItemElement _saeie4_1;
  private ExchangeItem _saei5;
  private ExchangeItemElement _saeie5_1;
  private ExchangeItemElement _saeie5_2;

  private DataPkg _laDataPkg;
  // Exchange Items & Exchange Item Elements
  private ExchangeItem _laei1;
  private ExchangeItemElement _laeie1_1;
  private ExchangeItemElement _laeie1_2;
  private ExchangeItem _laei2;
  private ExchangeItemElement _laeie2_1;
  private ExchangeItem _laei3;
  private ExchangeItemElement _laeie3_1;
  private ExchangeItem _laei4;
  private ExchangeItemElement _laeie4_1;
  private ExchangeItem _laei5;
  private ExchangeItemElement _laeie5_1;
  private ExchangeItemElement _laeie5_2;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AllProjectionModels");
  }

  public void performTest() throws Exception {
    // Assign the objects
    _ctxDataPkg = (DataPkg) getObject(ModelCtxLa.ctxDataPkgId);
    _saei1 = (ExchangeItem) getObject(ModelCtxLa.ei1Id);
    _saeie1_1 = (ExchangeItemElement) getObject(ModelCtxLa.eie1_1Id);
    _saei2 = (ExchangeItem) getObject(ModelCtxLa.ei2Id);
    _saeie2_1 = (ExchangeItemElement) getObject(ModelCtxLa.eie2_1Id);
    _saei3 = (ExchangeItem) getObject(ModelCtxLa.ei3Id);
    _saeie3_1 = (ExchangeItemElement) getObject(ModelCtxLa.eie3_1Id);
    _saei4 = (ExchangeItem) getObject(ModelCtxLa.ei4Id);
    _saeie4_1 = (ExchangeItemElement) getObject(ModelCtxLa.eie4_1Id);
    _saei5 = (ExchangeItem) getObject(ModelCtxLa.ei5Id);
    _saeie5_1 = (ExchangeItemElement) getObject(ModelCtxLa.eie5_1Id);
    _saeie5_2 = (ExchangeItemElement) getObject(ModelCtxLa.eie5_2Id);
    _laDataPkg = (DataPkg) getObject(ModelCtxLa.laDataPkgId);

    performTest1();
    performTest2();
    performTest3();
    performTest4();
    performTest5();
    performTest6();
    performTest7();
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 1
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 1 is transitioned to LA Layer keeping its mechanism (EVENT).\
   *               2. ExchangeItemElement1_1 is also transitioned keeping its  type (MyBooleanType)
   * </pre>
   */

  public void performTest1() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei1));
    // ExchangeItem 1
    _laei1 = _laDataPkg.getOwnedExchangeItems().get(0);
    assertNotNull(Messages.NullError, _laei1);
    assertTrue(NLS.bind(Messages.RealizationError, _laei1.getName(), _saei1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laei1) == _saei1));
    ExchangeMechanism laei1Mechanism = _laei1.getExchangeMechanism();
    ExchangeMechanism saei1Mechanism = _saei1.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei1.getName(),
        laei1Mechanism.getName(), saei1Mechanism.getName()), laei1Mechanism.equals(saei1Mechanism));

    // ExchangeItem1_1
    _laeie1_1 = _laei1.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, _laeie1_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie1_1.getName(), _saeie1_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie1_1) == _saeie1_1));
    Type laeie1_1Type = _laeie1_1.getType();
    Type saeie1_1Type = _saeie1_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie1_1.getName(),
        laeie1_1Type.getName(), saeie1_1Type.getName()), laeie1_1Type.equals(saeie1_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 2
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 2 is transitioned to LA Layer keeping its mechanism (OPERATION).\
   *               2. ExchangeItemElement2_1 is also transitioned keeping its  type (Byte)
   * </pre>
   */

  public void performTest2() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei2));
    // ExchangeItem 2
    _laei2 = _laDataPkg.getOwnedExchangeItems().get(1);
    assertNotNull(Messages.NullError, _laei2);
    assertTrue(NLS.bind(Messages.RealizationError, _laei2.getName(), _saei2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laei2) == _saei2));
    ExchangeMechanism laei2Mechanism = _laei2.getExchangeMechanism();
    ExchangeMechanism saei2Mechanism = _saei2.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei2.getName(),
        laei2Mechanism.getName(), saei2Mechanism.getName()), laei2Mechanism.equals(saei2Mechanism));

    // ExchangeItem2_1
    _laeie2_1 = _laei2.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, _laeie2_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie2_1.getName(), _saeie2_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie2_1) == _saeie2_1));
    Type laeie2_1Type = _laeie2_1.getType();
    Type saeie2_1Type = _saeie2_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie2_1.getName(),
        laeie2_1Type.getName(), saeie2_1Type.getName()), laeie2_1Type.equals(saeie2_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 3
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 3 is transitioned to LA Layer keeping its mechanism (FLOW).\
   *               2. ExchangeItemElement3_1 is also transitioned keeping its  type (String)
   * </pre>
   */

  public void performTest3() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei3));
    // ExchangeItem 3
    _laei3 = _laDataPkg.getOwnedExchangeItems().get(2);
    assertNotNull(Messages.NullError, _laei3);
    assertTrue(NLS.bind(Messages.RealizationError, _laei3.getName(), _saei3.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laei3) == _saei3));
    ExchangeMechanism laei3Mechanism = _laei3.getExchangeMechanism();
    ExchangeMechanism saei3Mechanism = _saei3.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei3.getName(),
        laei3Mechanism.getName(), saei3Mechanism.getName()), laei3Mechanism.equals(saei3Mechanism));

    // ExchangeItem3_1
    _laeie3_1 = _laei3.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, _laeie3_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie3_1.getName(), _saeie3_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie3_1) == _saeie3_1));
    Type laeie3_1Type = _laeie3_1.getType();
    Type saeie3_1Type = _saeie3_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie3_1.getName(),
        laeie3_1Type.getName(), saeie3_1Type.getName()), laeie3_1Type.equals(saeie3_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 4
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 4 is transitioned to LA Layer keeping its mechanism (SHARED_DATA).\
   *               2. ExchangeItemElement4_1 is also transitioned keeping its  type (Double)
   * </pre>
   */

  public void performTest4() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei4));
    // ExchangeItem 4
    _laei4 = _laDataPkg.getOwnedExchangeItems().get(3);
    assertNotNull(Messages.NullError, _laei4);
    assertTrue(NLS.bind(Messages.RealizationError, _laei4.getName(), _saei4.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laei4) == _saei4));
    ExchangeMechanism laei4Mechanism = _laei4.getExchangeMechanism();
    ExchangeMechanism saei4Mechanism = _saei4.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei4.getName(),
        laei4Mechanism.getName(), saei4Mechanism.getName()), laei4Mechanism.equals(saei4Mechanism));

    // ExchangeItem4_1
    _laeie4_1 = _laei4.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, _laeie4_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie4_1.getName(), _saeie4_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie4_1) == _saeie4_1));
    Type laeie4_1Type = _laeie4_1.getType();
    Type saeie4_1Type = _saeie4_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie4_1.getName(),
        laeie4_1Type.getName(), saeie4_1Type.getName()), laeie4_1Type.equals(saeie4_1Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 5
   * 
   * <pre>
   * Expected Result:\
   *               1. ExchangeItem 5 is transitioned to LA Layer keeping its mechanism (UNSET).\
   *               2. ExchangeItemElement5_1 is also transitioned keeping its  type (Hexadecimal)\
   *               3. ExchangeItemElement5_2 is also transitioned keeping its  type (Integer)
   * </pre>
   */

  public void performTest5() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei5));
    // ExchangeItem 5
    _laei5 = _laDataPkg.getOwnedExchangeItems().get(4);
    assertNotNull(Messages.NullError, _laei5);
    assertTrue(NLS.bind(Messages.RealizationError, _laei5.getName(), _saei5.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laei5) == _saei5));
    ExchangeMechanism laei5Mechanism = _laei5.getExchangeMechanism();
    ExchangeMechanism saei5Mechanism = _saei5.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei5.getName(),
        laei5Mechanism.getName(), saei5Mechanism.getName()), laei5Mechanism.equals(saei5Mechanism));

    // ExchangeItem5_1
    _laeie5_1 = _laei5.getOwnedElements().get(0);
    assertNotNull(Messages.NullError, _laeie5_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie5_1.getName(), _saeie5_1.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie5_1) == _saeie5_1));
    Type laeie5_1Type = _laeie5_1.getType();
    Type saeie5_1Type = _saeie5_1.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie5_1.getName(),
        laeie5_1Type.getName(), saeie5_1Type.getName()), laeie5_1Type.equals(saeie5_1Type));

    // ExchangeItem5_2
    _laeie5_2 = _laei5.getOwnedElements().get(1);
    assertNotNull(Messages.NullError, _laeie5_2);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie5_2.getName(), _saeie5_2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie5_2) == _saeie5_2));
    Type laeie5_2Type = _laeie5_2.getType();
    Type saeie5_2Type = _saeie5_2.getType();
    assertTrue(MessageFormat.format(Messages.WrongType, _laeie5_2.getName(),
        laeie5_2Type.getName(), saeie5_2Type.getName()), laeie5_2Type.equals(saeie5_2Type));
  }

  /**
   * Run the projection test "Exchange Item Transition" from ExchangeItem 1 a second time
   * 
   * <pre>
   * Expected Result:\
   *             1. No changes occur, and the elements are intact.
   * </pre>
   */

  public void performTest6() throws Exception {
    performExchangeItemTransition(Collections.singletonList(_saei1));
    assertEquals(Messages.ProjectionSizeError, _ctxDataPkg.getOwnedExchangeItems().size(),
        _laDataPkg.getOwnedExchangeItems().size());
    assertEquals(Messages.ProjectionSizeError, _saei1.getOwnedElements().size(), _laei1.getOwnedElements().size());
    ExchangeMechanism laei1Mechanism = _laei1.getExchangeMechanism();
    ExchangeMechanism saei1Mechanism = _saei1.getExchangeMechanism();
    assertTrue(MessageFormat.format(Messages.WrongValue, _laei1.getName(),
        laei1Mechanism.getName(), saei1Mechanism.getName()), laei1Mechanism.equals(saei1Mechanism));
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

  public void performTest7() throws Exception {
    // Delete ExchangeItemElement1_1
    // Create new ExchangeItemElement1_2
    // set ExchangeItem 1 mechanism to FLOW
    getExecutionManager(_laDataPkg).execute(new AbstractReadWriteCommand() {

      public void run() {
        _saeie1_1.destroy();
        _saeie1_2 = InformationFactory.eINSTANCE.createExchangeItemElement("ExchangeItemElement1_2");//$NON-NLS-1$
        _saei1.getOwnedElements().add(_saeie1_2);

        _saei1.setExchangeMechanism(ExchangeMechanism.FLOW);
      }
    });

    performExchangeItemTransition(Collections.singletonList(_saei1));
    assertNotNull(Messages.NullError, _laeie1_1);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie1_1.getName(), null),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie1_1) == null));

    // check that ExchangeItemElement1_2 is added
    _laeie1_2 = _laei1.getOwnedElements().get(1);
    assertNotNull(Messages.NullError, _laeie1_2);
    assertTrue(NLS.bind(Messages.RealizationError, _laeie1_2.getName(), _saeie1_2.getName()),
        (ProjectionTestUtils.getRealizedTargetElement(_laeie1_2) == _saeie1_2));

    // check that ExchangeItem 1 mechanism has changed to FLOW
    assertTrue(
        MessageFormat.format(Messages.WrongValue, _laei1.getName(),
            _laei1.getExchangeMechanism().getName(), ExchangeMechanism.FLOW.getName()),
        _laei1.getExchangeMechanism().equals(ExchangeMechanism.FLOW));

  }

}
