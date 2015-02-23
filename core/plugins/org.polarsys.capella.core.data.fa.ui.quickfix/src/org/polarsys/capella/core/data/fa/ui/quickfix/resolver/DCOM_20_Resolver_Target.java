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

package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NavigateAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DCOM_20_Resolver_Target extends AbstractCapellaMarkerResolution {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(IMarker marker) {
		final EObject modelElement = getModelElements(marker).get(0);
		if ((null != modelElement)
				&& (modelElement instanceof FunctionalExchange)) {
			AbstractFunction tarFunc = FunctionalExchangeExt
					.getTargetFunction((FunctionalExchange) modelElement);
			if (null != tarFunc) {
				showCapellaElement(tarFunc);
			}
		}
	}

	private void showCapellaElement(CapellaElement abstractExchangeItemToAdd) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		CapellaCommonNavigator capellaCommonNavigator = (CapellaCommonNavigator) activePage
				.findView(CapellaCommonNavigator.ID);
		// Create a navigate action that enables this navigation.
		NavigateAction action = new NavigateAction(abstractExchangeItemToAdd,
				capellaCommonNavigator.getCommonViewer());
		action.setText(EObjectLabelProviderHelper
				.getText(abstractExchangeItemToAdd));
		action.setImageDescriptor(ImageDescriptor
				.createFromImage(EObjectLabelProviderHelper
						.getImage(abstractExchangeItemToAdd)));
		action.run();
	}}
