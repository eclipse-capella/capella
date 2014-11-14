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
	public Object getElementToDelete(Object obj_p) {
		if (obj_p instanceof ComponentExchangeEnd){
			ComponentExchangeEnd compExed = (ComponentExchangeEnd)obj_p;
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
			return obj_p;
		}
		if (obj_p instanceof PhysicalLinkEnd){
			final PhysicalLinkEnd phyLE = (PhysicalLinkEnd)obj_p;
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
			return obj_p;
		}
		return null;
	}
}
