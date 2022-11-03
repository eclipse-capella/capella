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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class DCOM_20_Resolver_Delete extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {
    if (obj instanceof FunctionalExchange) {
      return obj;
    }
    return null;
  }

  @Override
  protected boolean quickFixAllSimilarEnabled(Collection<IMarker> markers) {
    return true;
  }

}
