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
package org.polarsys.capella.core.explorer.activity.ui.viewer;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.viewers.AbstractActivityExplorerViewer;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;

public class CapellaSessionManagerListener implements SessionManagerListener {

	private AbstractActivityExplorerViewer viewer;

	public CapellaSessionManagerListener(AbstractActivityExplorerViewer viewer) {
		this.viewer = viewer;
	}


	@Override
	public void notifyAddSession(Session newSession) {

	}

	@Override
	public void notifyRemoveSession(Session removedSession) {

	}

	@Override
	public void viewpointSelected(Viewpoint selectedSirius) {

	}

	@Override
	public void viewpointDeselected(Viewpoint deselectedSirius) {

	}

	@Override
	public void notify(Session updated, int notification) {
		Session session = ActivityExplorerManager.INSTANCE.getSession();


		if (!updated.equals(session)) {
			return;
		}

		final SharedHeaderFormEditor editor = (SharedHeaderFormEditor)ActivityExplorerManager.INSTANCE.getEditorFromSession(updated);

		Runnable runnable = null;
		switch (notification) {
		case SessionListener.CLOSING: /* Closing event is used to have a chance to persist the editor input at workbench shutdown */
			runnable = new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				@Override
				public void run() {
					// Close this editor.

					editor.close(false);
				}
			};
			break;
		case SessionListener.REPRESENTATION_CHANGE:
			runnable = new Runnable() {
				/**
				 * @see java.lang.Runnable#run()
				 */
				@Override
				@SuppressWarnings("synthetic-access")
				public void run() {
					// Handle pages to mark them as dirty.
					((CapellaDiagramViewer)viewer).markDiagramViewerAsDirty();
					IManagedForm headerForm = editor.getHeaderForm();
					if (null != headerForm) {
						headerForm.dirtyStateChanged();
					}
				}
			};
			break;
		case SessionListener.DIRTY:
		case SessionListener.SYNC:
		case SessionListener.SEMANTIC_CHANGE: // Listening to changes to mark the Activity Explorer editor dirty hence saveable.
			runnable = new Runnable() {
				/**
				 * {@inheritDoc}
				 */
				@Override
				public void run() {
					IManagedForm headerForm = editor.getHeaderForm();
					if (null != headerForm) {
						headerForm.dirtyStateChanged();
					}
				}
			};
			break;
		case SessionListener.REPLACED:
			notifyUpdatedSession(updated);
		}
		if (null != runnable) {
			
			IEditorSite editorSite = editor.getEditorSite();
			
			if (null != editorSite){
				editorSite.getShell().getDisplay().asyncExec(runnable);
			}
		}
	}

	/**
	 * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
	 */
	public void notifyUpdatedSession(Session updatedSession) {
		// Fake a representation change
		notify(updatedSession, SessionListener.REPRESENTATION_CHANGE);
	}

}
