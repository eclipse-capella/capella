/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class I_32_Resolver extends AbstractDeleteCommandResolver {

	@Override
	public Object getElementToDelete(Object obj) {
		if (obj instanceof ComponentExchangeEnd){
			ComponentExchangeEnd compExed = (ComponentExchangeEnd)obj;
			final ComponentExchange compEx = (ComponentExchange)compExed.eContainer();
			if(compEx.getSource()==compExed){
				TransactionalEditingDomain domain = TransactionHelper.getExecutionManager(compExed).getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
				   public void doExecute() {
						compEx.setSource((InformationsExchanger) compEx.getSourcePort());
				   }
				});
			}
			if(compEx.getTarget()==compExed){
				TransactionalEditingDomain domain = TransactionHelper.getExecutionManager(compExed).getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
				   public void doExecute() {
						compEx.setTarget((InformationsExchanger) compEx.getTargetPort());
				   }
				});			
			}	
			return obj;
		}
		if (obj instanceof PhysicalLinkEnd){
			final PhysicalLinkEnd phyLE = (PhysicalLinkEnd)obj;
			final PhysicalLink phyLi = (PhysicalLink)phyLE.eContainer();
			if(phyLi.getLinkEnds().contains(phyLE)){
				TransactionalEditingDomain domain = TransactionHelper.getExecutionManager(phyLE).getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
				   public void doExecute() {
					   phyLi.getLinkEnds().add(phyLE.getPort());
					   phyLi.getLinkEnds().remove(phyLE);
				   }
				});							
			}
			return obj;
		}
		return null;
	}
}
