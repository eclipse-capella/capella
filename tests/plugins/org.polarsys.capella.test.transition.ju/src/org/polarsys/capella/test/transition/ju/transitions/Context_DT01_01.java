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
 * - Create NumericType into Predefined Types named Byte
 * - Rename LiteralNumericValue from Byte to min
 * - Rename LiteralNumericValue from Byte to max
 * - Create StringType into Predefined Types named Char
 * - Rename LiteralNumericValue from Char to minLength
 * - Rename LiteralNumericValue from Char to maxLength
 * - Rename InterfacePkg from Logical Architecture to Interfaces
 * - Create ExchangeItem into Interfaces named EI2
 * - Create ExchangeItemElement into EI2 to Byte named EIE21
 * - Rename LiteralNumericValue from EIE21 to LNV211
 * - Rename LiteralNumericValue from EIE21 to LNV211
 * - Create ExchangeItemElement into EI2 to Char named EIE22
 * - Rename LiteralNumericValue from EIE22 to LNV221
 * - Rename LiteralNumericValue from EIE22 to LNV221
 * - Create Interface into Interfaces named I1
 * - Create ExchangeItemAllocation into I1 to EI2 named ExchangeItemAllocation
 * 
 * Perform transition on I1
 * 
 * Expected Result:\
 * - Exchange item and datatypes should be transitioned
 * 
 * </pre>
 */
public class Context_DT01_01 extends TopDownTransitionTestCase {

  private String id_byte = "a4e58b3b-1cc7-46b9-b1fe-093b1ee363e6";
  private String id_char = "1d3b958c-cb47-432d-abb1-55bc4682e6a3";
  private String id_ei2 = "dced8f3f-5b4c-468d-83ee-2bd09b650255";
  private String id_eie21 = "8a55949e-fbdf-492a-b4c3-c9b2cea49068";
  private String id_eie22 = "f16471f9-596b-4871-a844-3559201abf05";
  private String id_i1 = "b3db9990-a91f-472f-8b17-a93515cb194f";
  private String id_exchangeitemallocation = "5a967c3e-bb8b-4dcb-b0b9-f3133595324d";

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
    performInterfaceTransition(Arrays.asList(getObject(id_i1)));
    mustBeTransitioned(id_i1);
    mustBeTransitioned(id_exchangeitemallocation);
    mustBeTransitioned(id_eie22);
    mustBeTransitioned(id_eie21);
    mustBeTransitioned(id_ei2);
    mustBeTransitioned(id_byte);
    mustBeTransitioned(id_char);
  }
}
