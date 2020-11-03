/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
	public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
		return false;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
	 */
	public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement, EStructuralFeature feature) {
		if (createdElement instanceof SignalInstance) {
			SignalInstance signalInstance = (SignalInstance) createdElement;

			CompoundCommand cmd = new CompoundCommand();

			cmd.append(new SetCommand(editingDomain, signalInstance, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((AbstractNamedElement) containerElement).getName()));
			cmd.append(new SetCommand(editingDomain, signalInstance, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement));

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
