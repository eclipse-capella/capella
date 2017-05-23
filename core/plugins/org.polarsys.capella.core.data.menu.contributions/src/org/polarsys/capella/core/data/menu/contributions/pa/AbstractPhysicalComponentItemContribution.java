/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.menu.contributions.pa;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class AbstractPhysicalComponentItemContribution implements IMDEMenuItemContribution {

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
	 */
	public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
		return true;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
	 */
	public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement, EStructuralFeature feature) {
		if (createdElement instanceof AbstractPhysicalComponent) {
			CompoundCommand cmd = new CompoundCommand();

			if (createdElement instanceof PhysicalNode) {
				cmd.append(new SetCommand(editingDomain, createdElement, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.HARDWARE));
			} else if (createdElement instanceof PhysicalComponent) {
				cmd.append(new SetCommand(editingDomain, createdElement, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.UNSET));
			} else if (createdElement instanceof PhysicalActor) {
				cmd.append(new SetCommand(editingDomain, createdElement, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.PERSON));
			}

			return cmd;
		}
		return null;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
	 */
	public EClass getMetaclass() {
		return PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT;
	}
}
