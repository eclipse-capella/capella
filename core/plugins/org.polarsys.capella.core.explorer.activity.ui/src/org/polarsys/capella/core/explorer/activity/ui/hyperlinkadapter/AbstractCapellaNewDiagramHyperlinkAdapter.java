/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.polarsys.capella.core.data.capellamodeller.Project;

public abstract class AbstractCapellaNewDiagramHyperlinkAdapter extends AbstractNewDiagramHyperlinkAdapter {


	private Session _session;

	public AbstractCapellaNewDiagramHyperlinkAdapter() {
		super(getCapellaProject(), ActivityExplorerManager.INSTANCE.getSession());
		_session = ActivityExplorerManager.INSTANCE.getSession();
	}

	@Override
	public void linkActivated(HyperlinkEvent event) {

		RefreshEditorsPrecommitListener repl = _session.getRefreshEditorsListener();
		repl.notify(SessionListener.REPRESENTATION_CHANGE);
		repl.notify(SessionListener.SEMANTIC_CHANGE);

		linkPressed(event, getModelElement(_root), _session);
	}


	protected static Project getCapellaProject() {
		return (Project) ActivityExplorerManager.INSTANCE.getRootSemanticModel();
	}

}
