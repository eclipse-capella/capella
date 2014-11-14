/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
