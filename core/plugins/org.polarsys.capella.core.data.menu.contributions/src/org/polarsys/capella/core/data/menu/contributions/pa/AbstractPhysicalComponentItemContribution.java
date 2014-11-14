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
	public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
		return true;
	}

	/**
	 * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
	 */
	public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p, EStructuralFeature feature_p) {
		if (createdElement_p instanceof AbstractPhysicalComponent) {
			CompoundCommand cmd = new CompoundCommand();

			if (createdElement_p instanceof PhysicalNode) {
				cmd.append(new SetCommand(editingDomain_p, createdElement_p, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.HARDWARE));
			} else if (createdElement_p instanceof PhysicalComponent) {
				cmd.append(new SetCommand(editingDomain_p, createdElement_p, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.SOFTWARE));
			} else if (createdElement_p instanceof PhysicalActor) {
				cmd.append(new SetCommand(editingDomain_p, createdElement_p, PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND, PhysicalComponentKind.PERSON));
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
