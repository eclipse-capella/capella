/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;

public abstract class AbstractCapellaHyperlinkAdapter extends AbstractHyperlinkAdapter {

  public AbstractCapellaHyperlinkAdapter(EObject root) {
    super(root);
  }
  
  @Override
  public void linkActivated(HyperlinkEvent event) {
    Session session = ActivityExplorerManager.INSTANCE.getSession();
    IModel model = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    Project project = ((ICapellaModel) model).getProject(session.getTransactionalEditingDomain());
    linkPressed(event, project, session);
  }
}
