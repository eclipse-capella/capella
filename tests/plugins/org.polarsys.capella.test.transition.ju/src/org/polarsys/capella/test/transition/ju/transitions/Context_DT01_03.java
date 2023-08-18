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

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 *  * - Rename InterfacePkg from Logical Architecture to Interfaces
 * - Create ExchangeItem into Interfaces named ExchangeItem 1
 * - Create ExchangeItemElement into ExchangeItem 1 to Class1 named ExchangeItemElement 1
 * - Rename LiteralNumericValue from ExchangeItemElement 1 to minCard
 * - Rename LiteralNumericValue from ExchangeItemElement 1 to maxCard
 * - Create ExchangeItemElement into ExchangeItem 1 to Class1_Final named ExchangeItemElement 2
 * - Rename LiteralNumericValue from ExchangeItemElement 2 to minCard
 * - Rename LiteralNumericValue from ExchangeItemElement 2 to maxCard
 * - Create Interface into Interfaces named Interface 1
 * - Create ExchangeItemAllocation into Interface 1 to ExchangeItem 1 named ExchangeItemAllocation
 * - Rename DataPkg from Logical Architecture to Data
 * - Create Class into Data named Class1
 * - Create Property into Class1 named P1
 * - Rename LiteralNumericValue from P1 to minCard
 * - Rename LiteralNumericValue from P1 to maxCard
 * - Create Class into Data named Class1_Final
 * - Create Property into Class1_Final named P2
 * - Rename LiteralNumericValue from P2 to minCard
 * - Rename LiteralNumericValue from P2 to maxCard
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create NumericType into Predefined Types from SA named Byte
 * - Rename LiteralNumericValue from Byte to min
 * - Rename LiteralNumericValue from Byte to max
 * - Create NumericType into Data named Double
 * - Create Property into Class1 named P3
 * - Rename LiteralNumericValue from P3 to minCard
 * - Rename LiteralNumericValue from P3 to maxCard
 * 
 * 
 * Expected Result:\
 * - Performing Interface transition of I1 should transition exchange item and Class1 non final
 * 
 * </pre>
 */
public class Context_DT01_03 extends TopDownTransitionTestCase {

  private String id_exchangeitem_1 = "4cc41251-4901-4341-b5e8-a822cba1903c";
  private String id_exchangeitemelement_1 = "1644e043-18b1-4891-b0bf-a31cb8c987b8";
  private String id_exchangeitemelement_2 = "ed438c8d-7477-4cac-8bd4-a91aed53b1f9";
  private String id_interface_1 = "8be983dd-70c1-4a63-824d-4905dcb4bef7";
  private String id_class1 = "b841885c-23f6-4db2-8e3a-d2f8147d2fe0";
  private String id_p1 = "e1b2b9f6-20ea-4ad5-af6a-2a7d5388d71c";
  private String id_class1_final = "1ff65f70-b710-4487-aaa0-5f916d354aea";
  private String id_p2 = "07f2f62e-03a0-4da8-8cec-a94f269bb12d";
  private String id_p3 = "677ad936-fc4a-4738-a4eb-c7cf886f3bba";
  private String id_double = "53b3ce39-5555-45ca-aa9c-ea5a86339eff";
  private String id_byte = "4707dc10-f1e5-4c7b-94da-131453322d18";
  private String id_float = "9252e1df-884a-4d66-b0c6-74934655893b";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__DATATYPE, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performInterfaceTransition(Arrays.asList(getObject(id_interface_1)));
    mustBeTransitioned(id_exchangeitem_1);
    mustBeTransitioned(id_exchangeitemelement_1);
    mustBeTransitioned(id_exchangeitemelement_2);
    mustBeTransitioned(id_interface_1);
    mustBeTransitioned(id_class1);
    mustBeTransitioned(id_p1);

    shouldNotBeTransitioned(id_class1_final);
    shouldNotBeTransitioned(id_p2);

    mustBeTransitioned(id_p3);

    // Retrieve the type of Property for projection only if belong the same current Property layer
    shouldNotBeTransitioned(id_byte);
    mustBeTransitioned(id_double);
    mustBeTransitioned(id_float);
  }
}
