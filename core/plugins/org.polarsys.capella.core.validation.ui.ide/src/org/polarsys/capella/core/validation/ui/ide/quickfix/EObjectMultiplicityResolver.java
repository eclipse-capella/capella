/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import org.eclipse.core.resources.IMarker;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;

/**
 * A resolver for org.eclipse.emf.ecore.1 style markers
 * (Current multiplicity of a feature does not match the declared multiplicity).
 * 
 * This fix simply deletes the element that violates this constraint,
 * which is a reasonable fix for 'link' elements like 'Realization'
 * 
 */
public class EObjectMultiplicityResolver extends AbstractDeleteCommandResolver {

  protected boolean canResolve(IMarker marker) {
    return MarkerViewHelper.isEcore(marker);
  }

  @Override
  public Object getElementToDelete(Object obj) {
    return obj;
  }
  
}