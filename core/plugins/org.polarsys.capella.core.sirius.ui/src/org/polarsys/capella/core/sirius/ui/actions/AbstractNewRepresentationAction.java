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
package org.polarsys.capella.core.sirius.ui.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.sirius.ui.helper.DescriptionImageDescriptorHelper;

public abstract class AbstractNewRepresentationAction extends BaseSelectionListenerAction {

  protected boolean isCanceled;
  protected EObject selectedEObject;
  protected Session session;
  protected boolean openRepresentation;
  protected String message;

  protected AbstractNewRepresentationAction(String text) {
    super(text);
  }

  protected String getDescriptionLabel(RepresentationDescription description) {
    return MessageTranslator.INSTANCE.getMessage(description, new IdentifiedElementQuery(description).getLabel());
  }

  protected ImageDescriptor getDescriptionImageDescriptor(RepresentationDescription description) {
    return DescriptionImageDescriptorHelper.getDescriptionImageDescriptor(description);
  }

  public boolean isCanceled() {
    return isCanceled;
  }

  public void setCanceled(boolean isCanceled) {
    this.isCanceled = isCanceled;
  }
}
