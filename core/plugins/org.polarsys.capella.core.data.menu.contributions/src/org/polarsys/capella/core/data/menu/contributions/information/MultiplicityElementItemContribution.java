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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public abstract class MultiplicityElementItemContribution implements IMDEMenuItemContribution {

  protected static final String _ONE_CARDINALITY = "1"; //$NON-NLS-1$

  protected static final String _MANY_CARDINALITY = "*"; //$NON-NLS-1$

  protected Command getUniqueCommand(final EditingDomain domain_p, final EObject createdElement_p) {

    if (createdElement_p instanceof MultiplicityElement) {
      // Sets the unique value to true.
      Command result = new CommandWrapper() {
        @Override
        public Command createCommand() {
          return new SetCommand(domain_p, createdElement_p, InformationPackage.Literals.MULTIPLICITY_ELEMENT__UNIQUE, Boolean.TRUE);
        }
      };
      return result;
    }

    return null;
  }

  protected Command getCardinalitiesCommand(final EditingDomain editingDomain_p, final EObject createdElement_p, final String defaultMinCardinality_p,
      final String defaultMaxCardinality_p) {
    CompoundCommand cmd = new CompoundCommand();

    // Creates the min cardinality.
    final Command createMinCardCmd =
        CreateChildCommand.create(editingDomain_p, createdElement_p, new CommandParameter(createdElement_p,
            InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue()), Collections.EMPTY_LIST);
    cmd.append(createMinCardCmd);

    // Sets the min cardinality value.
    Command setMinCardValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createMinCardCmd.getResult();
        if (res.size() == 1) {
          Object createdObj = res.iterator().next();
          if (createdObj instanceof EObject) {
            return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, defaultMinCardinality_p);
          }
        }
        return null;
      }
    };
    cmd.append(setMinCardValueCmd);

    // Creates the max cardinality.
    final Command createMaxCardCmd =
        CreateChildCommand.create(editingDomain_p, createdElement_p, new CommandParameter(createdElement_p,
            InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD, DatavalueFactory.eINSTANCE.createLiteralNumericValue()), Collections.EMPTY_LIST);
    cmd.append(createMaxCardCmd);

    // Sets the max cardinality value.
    Command setMaxCardValueCmd = new CommandWrapper() {
      @Override
      public Command createCommand() {
        Collection<?> res = createMaxCardCmd.getResult();
        if (res.size() == 1) {
          Object createdObj = res.iterator().next();
          if (createdObj instanceof EObject) {
            return new SetCommand(editingDomain_p, (EObject) createdObj, DatavaluePackage.Literals.LITERAL_NUMERIC_VALUE__VALUE, defaultMaxCardinality_p);
          }
        }
        return null;
      }
    };
    cmd.append(setMaxCardValueCmd);
    return cmd;
  }
}
