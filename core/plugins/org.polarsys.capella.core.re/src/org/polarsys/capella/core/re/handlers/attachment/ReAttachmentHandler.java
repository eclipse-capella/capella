/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.attachment;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.system.handlers.attachment.CapellaDefaultAttachmentHandler;

public class ReAttachmentHandler extends CapellaDefaultAttachmentHandler {

  @Override
  protected boolean isHandlingOrdering(EObject sourceAttaching_p, EObject targetAttaching_p, EObject sourceAttached_p, EObject targetAttached_p,
      EReference sourceFeature_p, EReference targetFeature_p) {
    return false;
  }

}
