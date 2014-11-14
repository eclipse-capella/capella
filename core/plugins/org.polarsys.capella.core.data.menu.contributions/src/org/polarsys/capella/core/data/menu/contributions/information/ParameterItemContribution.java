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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class ParameterItemContribution implements IMDEMenuItemContribution {

  /**
   * 
   */
  private static final String _DEFAULT_CARDINALITY = "1"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    if (createdElement_p instanceof Parameter) {
      CompoundCommand cmd = new CompoundCommand();

      // Creates the min cardinality.
      final Command createMinCardCmd =
          CreateChildCommand.create(editingDomain_p, createdElement_p, new CommandParameter(createdElement_p,
              InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue()),
              Collections.EMPTY_LIST);
      cmd.append(createMinCardCmd);

      // Sets the min cardinality value.
      Command setMinCardValueCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = createMinCardCmd.getResult();
          if (res.size() == 1) {
            Object createdObj = res.iterator().next();
            if (createdObj instanceof EObject) {
              return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, _DEFAULT_CARDINALITY);
            }
          }
          return null;
        }
      };
      cmd.append(setMinCardValueCmd);

      // Creates the max cardinality.
      final Command createMaxCardCmd =
          CreateChildCommand.create(editingDomain_p, createdElement_p, new CommandParameter(createdElement_p,
              InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue()),
              Collections.EMPTY_LIST);
      cmd.append(createMaxCardCmd);

      // Sets the max cardinality value.
      Command setMaxCardValueCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = createMaxCardCmd.getResult();
          if (res.size() == 1) {
            Object createdObj = res.iterator().next();
            if (createdObj instanceof EObject) {
              return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, _DEFAULT_CARDINALITY);
            }
          }
          return null;
        }
      };
      cmd.append(setMaxCardValueCmd);

      return cmd;
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return InformationPackage.Literals.PARAMETER;
  }
}
