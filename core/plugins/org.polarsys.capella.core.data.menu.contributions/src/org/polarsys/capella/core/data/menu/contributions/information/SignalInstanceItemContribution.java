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
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.SignalInstance;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class SignalInstanceItemContribution implements IMDEMenuItemContribution {

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
	 */
	public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
		return false;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
	 */
	public Command executionContribution(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p, EStructuralFeature feature_p) {
		if (createdElement_p instanceof SignalInstance) {
			SignalInstance signalInstance = (SignalInstance) createdElement_p;

			CompoundCommand cmd = new CompoundCommand();

			cmd.append(new SetCommand(editingDomain_p, signalInstance, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((AbstractNamedElement) containerElement_p).getName()));
			cmd.append(new SetCommand(editingDomain_p, signalInstance, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement_p));

			return cmd;
		}
		return null;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
	 */
	public EClass getMetaclass() {
		return CommunicationPackage.Literals.SIGNAL_INSTANCE;
	}
}
