/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.log;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.system.handlers.log.CapellaLogHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TopDownLogHandler extends CapellaLogHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return super.dispose(context_p);
  }

  @Override
  public String getReadableText(EObject object_p) {
    return NLS.bind("''{0}'' [{1}]", EObjectLabelProviderHelper.getText(object_p), EObjectLabelProviderHelper.getMetaclassLabel(object_p, false)); //$NON-NLS-1$
  }

  /**
   * @param reportComponent_p
   */
  public TopDownLogHandler(String reportComponent_p) {
    super(reportComponent_p);
  }

}
