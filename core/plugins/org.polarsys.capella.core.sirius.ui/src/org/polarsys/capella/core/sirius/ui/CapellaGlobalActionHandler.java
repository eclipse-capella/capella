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
package org.polarsys.capella.core.sirius.ui;

import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.tools.internal.clipboard.SiriusClipboardGlobalActionHandler;

public class CapellaGlobalActionHandler extends
		SiriusClipboardGlobalActionHandler {

	@Override
	public boolean canPaste(IGlobalActionContext cntxt) {
		return super.canPaste(cntxt) && isDiagram (cntxt);
	}


	@Override
	protected boolean canCopy(IGlobalActionContext cntxt) {
		return isNote (cntxt);
	}

	@Override
	protected boolean canCut(IGlobalActionContext cntxt) {
		return super.canCut(cntxt) && isNote (cntxt);
	}

	private boolean isNote(IGlobalActionContext cntxt) {
		ISelection selection = cntxt.getSelection();
		boolean allNotes = true;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.isEmpty()) return false;
			for (Object select : iss.toArray()) {
				allNotes = allNotes && (select instanceof NoteEditPart || select instanceof TextEditPart);
			}
		}
		return allNotes;
	}


	private boolean isDiagram(IGlobalActionContext cntxt) {
		ISelection selection = cntxt.getSelection();
		boolean diagram = true;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iss = (IStructuredSelection) selection;
			if (iss.isEmpty()) return false;
			for (Object select : iss.toArray()) {
				diagram = diagram && (select instanceof DiagramEditPart);
			}
		}
		return diagram;

	}
}
