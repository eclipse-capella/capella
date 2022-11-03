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

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.ui.quicfix.helpers.QuickFixNavigationHelper;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DCOM_23_Resolver_Abstract_Function extends AbstractCapellaMarkerResolution {
  @Override
  public void run(IMarker marker) {
    final EObject modelElement = getModelElements(marker).get(0);
    if ((null != modelElement) && (modelElement instanceof FunctionalExchange)) {
      FunctionOutputPort port = ((FunctionalExchange) modelElement).getSourceFunctionOutputPort();
      if (null != port && port.eContainer() instanceof AbstractFunction) {
        QuickFixNavigationHelper.showCapellaElement((AbstractFunction) port.eContainer());
      }
    }
  }

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return false;
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker marker : markers) {
      if (!canResolve(marker)) {
        return false;
      }
    }
    return super.enabled(markers);
  }
}
