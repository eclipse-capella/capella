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

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class DCOM_23_Resolver_Looping_Activity extends DCOM_23_Resolver_Abstract_Function {

  @Override
  protected boolean canResolve(IMarker marker) {
    final EObject modelElement = getModelElements(marker).get(0);
    if (!(BlockArchitectureExt.getRootBlockArchitecture(modelElement) instanceof OperationalAnalysis)) {
      return false;
    }
    return super.canResolve(marker);
  }
}
