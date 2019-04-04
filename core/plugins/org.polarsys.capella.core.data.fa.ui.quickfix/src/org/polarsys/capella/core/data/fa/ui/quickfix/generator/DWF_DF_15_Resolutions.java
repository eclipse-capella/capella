/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

/**
 * DWF_DF_15 - SequenceLinks between FCIFunctions/Control Nodes with no Functional Exchanges connecting them
 */
public class DWF_DF_15_Resolutions extends SequenceLink_Resolutions {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_15";

  @Override
  protected String getRuleId() {
    return RULE_ID;
  }

  @Override
  protected String getLabelQF() {
    return "";
  }

  @Override
  protected String getLabelQF_ALL() {
    return "";
  }
}
