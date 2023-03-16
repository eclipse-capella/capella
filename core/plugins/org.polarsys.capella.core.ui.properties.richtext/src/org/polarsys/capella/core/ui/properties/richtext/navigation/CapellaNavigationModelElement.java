/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.navigation;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.kitalpha.richtext.widget.tools.ext.intf.OpenLinkStrategy;

public class CapellaNavigationModelElement implements OpenLinkStrategy {

    public CapellaNavigationModelElement() {
    }

    private void doOpen(EObject eObject, String link) {
        EObject obj = getElement(TransactionHelper.getEditingDomain(eObject), link);
        if (obj != null) {
            if (CapellaResourceHelper.isSemanticElement(obj)) {
                UIUtil.getInstance().selectInPackageExplorer(obj);
            } else if (obj instanceof DRepresentationDescriptor) {
                DRepresentation representation = ((DRepresentationDescriptor) obj).getRepresentation();
                if (representation instanceof DSemanticDecorator) {
                  Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
                  DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
                }
            }
        } else {
            MessageBox msgBox = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
            msgBox.setText("Error"); //$NON-NLS-1$
            msgBox.setMessage("'" + link + "' is not a valid model resource."); //$NON-NLS-1$ //$NON-NLS-2$
            msgBox.open();
        }
    }

    private EObject getElement(final EditingDomain editingDomain, String uriFragment) {
        EObject semanticElement = RepresentationHelper
            .getRepresentationDescriptorOrSemanticObject(editingDomain.getResourceSet(), uriFragment);
        return semanticElement;
    }

	@Override
	public void openLink(Object object, String link) {
		doOpen((EObject) object, link);
	}

}
