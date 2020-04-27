/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.core.data.fa.ui.quickfix.resolver.DWF_DF_18_Resolver;

/**
 * DWF_DF_18 - SequenceLink with no associated FunctionalChainInvolvementLinks
 */
public class DWF_DF_18_Resolutions extends SequenceLink_Resolutions {
  public static final String RULE_ID = "org.polarsys.capella.core.data.fa.validation.DWF_DF_18";
  public static final String labelQF = "Create associated link";

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();

    DWF_DF_18_Resolver resolver = new DWF_DF_18_Resolver(getLabelQF());
    resolutions.add(resolver);

    return resolutions.toArray(new IMarkerResolution[0]);
  }

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
    return "";
  }
}
