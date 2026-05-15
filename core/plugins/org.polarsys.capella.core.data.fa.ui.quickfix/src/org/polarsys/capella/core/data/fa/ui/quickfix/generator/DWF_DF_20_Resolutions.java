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
 * DWF_DF_20 - SequenceLink between the referenced Involvement Functions/OperationalActivities is in opposite direction to Involvement Links referencing them
 * them
 */
public class DWF_DF_20_Resolutions extends SequenceLink_Resolutions {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_20"; //$NON-NLS-1$
  public static final String labelQF = Messages.DWF_DF_20_Resolutions_1;
  public static final String labelQF_ALL = Messages.DWF_DF_20_Resolutions_2;

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
