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
 * - ExchangeItem should be transitioned, not the datatypes
 * 
 * </pre>
 */
public class Context_DT01_02 extends TopDownTransitionTestCase {

  private String id_byte = "d5c43983-9ad9-4af1-b6ab-1979c490a2ce";
  private String id_char = "6cce21a1-69e9-4173-bcd0-c2ba3b10d46f";
  private String id_ei2 = "f3715731-f856-45aa-9822-9c5b9e5755de";
  private String id_eie21 = "726ae47c-0ed9-44fc-b166-76ec5f6b1601";
  private String id_eie22 = "3d4ab35e-cba9-48f8-a047-01207b58cfeb";
  private String id_i1 = "48dff987-e058-4f31-8819-019c91e30aa4";
  private String id_exchangeitemallocation = "aa507c96-54e5-4a98-8be5-b00e34589e13";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__DATATYPE, false);
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
    mustBeTransitioned(id_ei2);
    mustBeTransitioned(id_eie21);
    mustBeTransitioned(id_eie22);
    mustBeTransitioned(id_i1);
    mustBeTransitioned(id_exchangeitemallocation);

    shouldNotBeTransitioned(id_byte);
    shouldNotBeTransitioned(id_char);
  }
}
