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
package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import static org.polarsys.capella.core.data.fa.OrientationPortKind.IN;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.INOUT;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.OUT;
import static org.polarsys.capella.core.data.fa.OrientationPortKind.UNSET;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Tests the ComponentExchangePort orientation consistency model constraint.
 */
public class ComponentPortOrientationConsistencyTest extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.fa.validation.I_20"});
  }

  private static final String exchangeName = "exchange"; //$NON-NLS-1$
  private static final String sourceName = "target"; //$NON-NLS-1$
  private static final String targetName = "exchange"; //$NON-NLS-1$

  
  public void componentExchangePortOrientation_OUT_IN(){
    ok(testImpl(OUT, IN));
  }
  
  public void componentExchangePortOrientation_OUT_UNSET(){
    ok(testImpl(OUT, UNSET));
  }
  
  public void componentExchangePortOrientation_OUT_INOUT(){
    ok(testImpl(OUT, INOUT));
  }
  
  public void componentExchangePortOrientation_OUT_NULL(){
    ok(testImpl(OUT, null));
  }
  
  public void componentExchangePortOrientation_INOUT_IN(){
    ok(testImpl(INOUT, IN));
  }
  
  public void componentExchangePortOrientation_INOUT_UNSET(){
    ok(testImpl(INOUT, UNSET));
  }
  
  public void componentExchangePortOrientation_INOUT_INOUT(){
    ok(testImpl(INOUT, INOUT));
  }
  
  public void componentExchangePortOrientation_INOUT_NULL(){
    ok(testImpl(INOUT, null));
  }
  
  public void componentExchangePortOrientation_UNSET_IN(){
    ok(testImpl(UNSET, IN));
  }
  
  public void componentExchangePortOrientation_UNSET_INOUT(){
    ok(testImpl(UNSET, INOUT));
  }
  
  public void componentExchangePortOrientation_UNSET_UNSET(){
    ok(testImpl(UNSET, UNSET));
  }
  
  public void componentExchangePortOrientation_UNSET_NULL(){
    ok(testImpl(UNSET, null));
  }
  
  public void componentExchangePortOrientation_NULL_IN(){
    ok(testImpl(null, IN));
  }
  
  public void componentExchangePortOrientation_NULL_INOUT(){
    ok(testImpl(null, INOUT));
  }
  
  public void componentExchangePortOrientation_NULL_UNSET(){
    ok(testImpl(null, UNSET));
  }
  
  public void componentExchangePortOrientation_NULL_NULL(){
    ok(testImpl(null, UNSET));
  }
  
  
  //// KO
  
  public void componentExchangePortOrientation_IN_IN(){
    ko(testImpl(IN, IN));
  }
  
  public void componentExchangePortOrientation_IN_INOUT(){
    ko(testImpl(IN, INOUT));
  }
  
  public void componentExchangePortOrientation_IN_UNSET(){
    ko(testImpl(IN, UNSET));
  }
  
  public void componentExchangePortOrientation_IN_NULL(){
    ko(testImpl(IN, null));
  }
  
  
  public void componentExchangePortOrientation_IN_OUT(){
    ko(testImpl(IN, OUT));
  }
  
  public void componentExchangePortOrientation_INOUT_OUT(){
    ko(testImpl(INOUT, OUT));
  }
  
  public void componentExchangePortOrientation_UNSET_OUT(){
    ko(testImpl(UNSET, OUT));
  }
  
  public void componentExchangePortOrientation_NULL_OUT(){
    ko(testImpl(null, OUT));
  }
  
  
  // Create a ComponentExchange and source/target ports with the given source/target orientation
  private ComponentExchange testImpl(OrientationPortKind sourceOrientation, OrientationPortKind targetOrientation){
    ComponentExchange exchange = FaFactory.eINSTANCE.createComponentExchange(exchangeName);
    ComponentPort source = FaFactory.eINSTANCE.createComponentPort(sourceName);
    source.setOrientation(sourceOrientation);
    ComponentPort target = FaFactory.eINSTANCE.createComponentPort(targetName);
    target.setOrientation(targetOrientation);
    exchange.setSource(source);
    exchange.setTarget(target);
    return exchange;
  }

  @Override
  public void test() throws Exception {
    componentExchangePortOrientation_IN_IN();
    componentExchangePortOrientation_IN_INOUT();
    componentExchangePortOrientation_IN_NULL();
    componentExchangePortOrientation_IN_OUT();
    componentExchangePortOrientation_IN_UNSET();

    componentExchangePortOrientation_INOUT_IN();
    componentExchangePortOrientation_INOUT_INOUT();
    componentExchangePortOrientation_INOUT_NULL();
    componentExchangePortOrientation_INOUT_OUT();
    componentExchangePortOrientation_INOUT_UNSET();

    componentExchangePortOrientation_NULL_IN();
    componentExchangePortOrientation_NULL_INOUT();
    componentExchangePortOrientation_NULL_NULL();
    componentExchangePortOrientation_NULL_OUT();
    componentExchangePortOrientation_NULL_UNSET();

    componentExchangePortOrientation_OUT_IN();
    componentExchangePortOrientation_OUT_INOUT();
    componentExchangePortOrientation_OUT_NULL();
    componentExchangePortOrientation_OUT_UNSET();

    componentExchangePortOrientation_UNSET_IN();
    componentExchangePortOrientation_UNSET_INOUT();
    componentExchangePortOrientation_UNSET_NULL();
    componentExchangePortOrientation_UNSET_OUT();
    componentExchangePortOrientation_UNSET_UNSET();
  }
}
