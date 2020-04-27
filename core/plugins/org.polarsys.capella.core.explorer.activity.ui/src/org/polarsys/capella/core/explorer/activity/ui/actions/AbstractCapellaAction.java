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
package org.polarsys.capella.core.explorer.activity.ui.actions;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.PopupMenuLinkAdapter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Base class to implement actions triggered by {@link PopupMenuLinkAdapter}.
 */
public abstract class AbstractCapellaAction extends Action {
	/**
	 * ModelElement project.
	 */
	private ModelElement project;
	/**
	 * Sirius session.
	 */
	private Session session;

	/**
	 * Constructor.
	 * @param text
	 * @param image
	 * @param modelElement
	 * @param session
	 */
	public AbstractCapellaAction(String text, ImageDescriptor image, ModelElement modelElement, Session session) {
		super(text, image);
		project = modelElement;
		this.session = session;
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		doRun(project, session);
	}

	/**
	 * Do run this action.
	 * @param modelElement
	 * @param session
	 */
	protected abstract void doRun(ModelElement modelElement, Session session);
}
