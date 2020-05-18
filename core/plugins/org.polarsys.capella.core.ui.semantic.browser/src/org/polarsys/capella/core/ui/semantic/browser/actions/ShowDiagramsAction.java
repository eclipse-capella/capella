/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.actions;

import java.net.URL;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.ui.semantic.browser.view.Messages;

public class ShowDiagramsAction extends Action {

  private ISemanticBrowserViewPart semanticBrowserViewPart;

  public ShowDiagramsAction(ISemanticBrowserViewPart semanticBrowserViewPart) {
    super(null, IAction.AS_CHECK_BOX);
    this.semanticBrowserViewPart = semanticBrowserViewPart;
    setChecked(semanticBrowserViewPart.getModel().doesShowDiagrams());
    setToolTipText(Messages.SemanticBrowserView_ShowDiagramsAction_Tooltip);
    setImageDescriptor(getImage(ViewpointFactory.eINSTANCE.createDAnalysis()));
  }

  @Override
  public void run() {
    semanticBrowserViewPart.getModel().setShowDiagrams(isChecked());
    Object input = semanticBrowserViewPart.getCurrentViewer().getInput();
    semanticBrowserViewPart.setInputOnViewers(input);
  }

  private ImageDescriptor getImage(EObject object) {
    IItemLabelProvider l = ((IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory()
        .adapt(object, IItemLabelProvider.class));
    URL imageUrl = (URL) l.getImage(object);
    return ImageDescriptor.createFromURL(imageUrl);
  }
}
