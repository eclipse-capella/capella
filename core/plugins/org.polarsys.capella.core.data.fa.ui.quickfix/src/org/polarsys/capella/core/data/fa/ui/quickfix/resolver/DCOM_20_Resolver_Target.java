/*******************************************************************************
 * Copyright (c) 2006, 2020, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.ui.quicfix.helpers.QuickFixNavigationHelper;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public abstract class DCOM_20_Resolver_Target extends AbstractCapellaMarkerResolution {
  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    final EObject modelElement = getModelElements(marker).get(0);
    if ((null != modelElement) && (modelElement instanceof FunctionalExchange)) {
      AbstractFunction tarFunc = FunctionalExchangeExt.getTargetFunction((FunctionalExchange) modelElement);
      if (null != tarFunc) {
        QuickFixNavigationHelper.showCapellaElement(tarFunc);
      }
    }
  }

  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker marker : markers) {
      EObject modelElement = getModelElements(marker).get(0);
      if ((null != modelElement) && (modelElement instanceof FunctionalExchange)) {
        AbstractFunction tarFunc = FunctionalExchangeExt.getTargetFunction((FunctionalExchange) modelElement);
        if (isEnabledAtThisLevel(tarFunc) && tarFunc.getOwnedFunctions().size() > 0)
          return true;
      }
    }
    return false;
  }

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return false;
  }

  protected abstract boolean isEnabledAtThisLevel(AbstractFunction tarFunc);
}
