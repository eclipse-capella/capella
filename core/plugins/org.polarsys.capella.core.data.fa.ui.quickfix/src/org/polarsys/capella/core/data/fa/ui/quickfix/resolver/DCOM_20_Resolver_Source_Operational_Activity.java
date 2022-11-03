/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;

public class DCOM_20_Resolver_Source_Operational_Activity extends DCOM_20_Resolver_Source {

  @Override
  protected boolean isEnabledAtThisLevel(AbstractFunction srcFunction) {
    return srcFunction instanceof OperationalActivity;
  }

}
