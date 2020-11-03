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
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

/**
 * DWF_DF_19 - SequenceLink with inconsistent associated FunctionalChainInvolvementLink
 */
public class DWF_DF_19_Resolutions extends SequenceLink_Resolutions {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_19";
  public static final String labelQF = "Remove invalid association of ({0}) to {1} ({2})";
  public static final String labelQF_ALL = "Remove all invalid associations of ({0})";

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
