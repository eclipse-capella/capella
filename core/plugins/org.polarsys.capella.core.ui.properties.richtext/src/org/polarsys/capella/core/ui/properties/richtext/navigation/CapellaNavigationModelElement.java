/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.navigation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.kitalpha.richtext.mde.tools.links.handlers.OpenModelElementStrategy;

public class CapellaNavigationModelElement implements OpenModelElementStrategy {

	public CapellaNavigationModelElement() {
	}

	@Override
	public void doOpen(EObject eObject, String link) {
		EObject obj = getElement(TransactionHelper.getEditingDomain(eObject), link);
	    if (obj != null) {
	      if (CapellaResourceHelper.isSemanticElement(obj)) {
	        UIUtil.getInstance().selectInPackageExplorer(obj);
	      } else if (obj instanceof DSemanticDiagram) {
	        Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) obj).getTarget());
	        DialectUIManager.INSTANCE.openEditor(session, (DRepresentation) obj, new NullProgressMonitor());
	      }else if(obj instanceof DTable){
	    	  Session session = SessionManager.INSTANCE.getSession(((DTable) obj).getTarget());
	          DialectUIManager.INSTANCE.openEditor(session, (DRepresentation) obj, new NullProgressMonitor());
	      }      
	    } else {
	      MessageBox msgBox = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
	      msgBox.setText("Error"); //$NON-NLS-1$
	      msgBox.setMessage("'" + link + "' is not a valid model resource."); //$NON-NLS-1$ //$NON-NLS-2$
	      msgBox.open();
	    }
	}

	private EObject getElement(final EditingDomain editingDomain, String uriFragment) {
		return IdManager.getInstance().getEObject(uriFragment, new IScope() {
			@Override
			public List<Resource> getResources() {
				return new ArrayList<Resource>(editingDomain.getResourceSet().getResources());
			}
		});
	}

}
