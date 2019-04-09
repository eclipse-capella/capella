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
 * DWF_DF_20 - SequenceLink is in opposite direction to involved FunctionalExchange
 */
public class DWF_DF_20_Resolutions extends DWF_DF_19_Resolutions {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_20";
  public static final String labelQF = "Remove association in opposite direction of ({0}) to {1} ({2})";
  public static final String labelQF_ALL = "Remove all associations in opposite direction of ({0})";

  @Override
  protected String getRuleId() {
    return RULE_ID;
  }

  @Override
  protected String getLabelQF() {
    return labelQF;
  }

  @Override
  protected String getLabelQF_ALL() {
    return labelQF_ALL;
  }
}
