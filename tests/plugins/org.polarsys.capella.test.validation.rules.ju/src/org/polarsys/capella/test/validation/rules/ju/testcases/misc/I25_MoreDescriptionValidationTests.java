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

import org.polarsys.capella.common.helpers.validation.xml.XHTMLEntities;
import org.polarsys.capella.core.data.core.validation.constraint.DWF_D_31;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Contains tests to validate sample element descriptions with Validation Rule I_25.
 */
public class I25_MoreDescriptionValidationTests extends BasicTestCase {
  
  DWF_D_31 validator;
  
  @Override
  public void setUp(){
    validator = new DWF_D_31();
  }

  /** 
   * Null descriptions are always valid
   */
  public void nullDescription() {
    valid(null);
  }
  
  /**
   * Empty descriptions are always valid
   */
  public void emptyDescription() {
    valid(""); //$NON-NLS-1$
  }
  
  /**
   * Descriptions with HTML 4 entity references are valid.
   */
  public void html4EntityReferences() {
    StringBuilder b = new StringBuilder();
    for (XHTMLEntities e : XHTMLEntities.values()){
      b.append(e.asCharacterEntityRef());
      b.append(" "); //$NON-NLS-1$
    }
    valid(b.toString());
  }
  
  /**
   * A description with unicode references &#xHHHH is valid
   */
  public void html4UnicodeReferences() {
    StringBuilder b = new StringBuilder();
    for (XHTMLEntities e : XHTMLEntities.values()){
      b.append(e.asHexadecimalEntityRef());
      b.append(" "); //$NON-NLS-1$
    }
    valid(b.toString());
  }
  
  /**
   * A description with a missing end tag is not valid
   */
  public void missingEndTag() {
    invalid("<toto>"); //$NON-NLS-1$
  }
  
  /**
   * 5 < 3 is not valid
   */
  public void reservedXMLCharacterLT() {
    invalid("5 < 3"); //$NON-NLS-1$
  }
  
  /**
   * 5 > 3 is not valid
   */
  public void reservedXMLCharacterGT() {
    invalid("5 < 3"); //$NON-NLS-1$
  }

  private void valid(String description){
    assertTrue(validator.validate(description).isEmpty());
  }
  
  
  private void invalid(String description) {
    assertFalse(validator.validate(description).isEmpty());
  }

  @Override
  public void test() throws Exception {
    nullDescription();
    emptyDescription();
    html4EntityReferences();
    html4UnicodeReferences();
    missingEndTag();
    reservedXMLCharacterLT();
    reservedXMLCharacterGT();
  }

}
