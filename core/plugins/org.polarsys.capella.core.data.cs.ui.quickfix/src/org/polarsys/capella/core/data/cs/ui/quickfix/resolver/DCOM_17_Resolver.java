/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.resolver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.NavigateAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class DCOM_17_Resolver  extends AbstractCapellaMarkerResolution {

	  /**
	   * {@inheritDoc}
	   */
	  public void run(IMarker marker) {
	    final EObject modelElement = getModelElements(marker).get(0);
	    if ((null != modelElement)  && (modelElement instanceof FunctionPort)) {
		        FunctionPort functionPort = (FunctionPort)modelElement ;
				Set<ExchangeItem> functionPortExchangeItems = FunctionPortExt.getAllIncomingExchangeItems(functionPort);
				Set<Component> components = FunctionPortExt.getAllProvidedRealizedRequiredInterfaces(functionPort);
				Set<AbstractExchangeItem> exchangeItems = getAllExchangesInterfaceItems(components);
				boolean isOk = !functionPortExchangeItems.isEmpty() && exchangeItems.isEmpty() ? false : exchangeItems.containsAll(functionPortExchangeItems);
				if (!isOk) {
					if (!components.isEmpty() && (components.toArray()[0] instanceof Component)) {
						  Component component = (Component) components.toArray()[0];
						  showCapellaElement(component);
					}
				}
	     }

	  } 
	  
	  	/**
		 * @param components
		 */
		private Set<AbstractExchangeItem> getAllExchangesInterfaceItems(Set<Component> components) {
			Set<AbstractExchangeItem> interfacesExchangeItems = new HashSet<AbstractExchangeItem>();
			for (Iterator<Component> iterator1 = components.iterator(); iterator1.hasNext();) {
				Component containerComponent = iterator1.next();
				List<Interface> interfaces = (List<Interface>) ComponentExt.getRelatedInterfaces(containerComponent);
				for (Iterator<Interface> iterator = interfaces.iterator(); iterator.hasNext();) {
					Interface interfazz = iterator.next();
					Set<AbstractExchangeItem> interfaceExchangeItems = (Set<AbstractExchangeItem>) InterfaceExt.getAllExchangeItems(interfazz) ;
					interfacesExchangeItems.addAll(interfaceExchangeItems);
				}
			}
			return interfacesExchangeItems ;
		}
	  
	/**
	 * 	
	 * @param abstractExchangeItemToAdd
	 */
	 private void showCapellaElement(CapellaElement abstractExchangeItemToAdd) {
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		    CapellaCommonNavigator capellaCommonNavigator = (CapellaCommonNavigator) activePage.findView(CapellaCommonNavigator.ID);
		    // Create a navigate action that enables this navigation.
	        NavigateAction action = new NavigateAction(abstractExchangeItemToAdd, capellaCommonNavigator.getCommonViewer());
	        action.setText(EObjectLabelProviderHelper.getText(abstractExchangeItemToAdd));
	        action.setImageDescriptor(ExtendedImageRegistry.getInstance().getImageDescriptor(EObjectLabelProviderHelper.getImage(abstractExchangeItemToAdd)));
		    action.run();
	}
	
}
