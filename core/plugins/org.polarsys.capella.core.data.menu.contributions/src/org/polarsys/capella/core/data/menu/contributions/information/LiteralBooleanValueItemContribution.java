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
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class LiteralBooleanValueItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {

    if (feature == ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION){
      return true;
    }

    boolean select =
        DatatypePackage.Literals.BOOLEAN_TYPE.isInstance(modelElement) && !isCardMaxReached(modelElement, cls, feature)
            && !feature.equals(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES)
            && !feature.equals(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE);

    if (feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_ELEMENTS)
        || feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT)) {
      if (modelElement instanceof CollectionValue) {
        AbstractType cvType = ((CollectionValue) modelElement).getAbstractType();
        if (cvType instanceof Collection) {
          Type cType = ((Collection) cvType).getType();
          {
            if (!(cType instanceof BooleanType)) {
              select = false;
            }
          }
        }
      }
    }

    if (feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)
        || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)
        || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
        || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE)) {
      if (modelElement instanceof Collection) {
        Type cType = ((Collection) modelElement).getType();
        if (!(cType instanceof BooleanType)) {
          select = false;
        }
      }
    }

    return select;
  }

  /**
   * This method checks if the current element already have two or more children:<br>
   * a boolean type cannot have more than two LiteralBooleanValues
   * 
   * @param modelElement
   * @param cls
   * @param feature
   * @return
   */
  @SuppressWarnings("unchecked")
  private boolean isCardMaxReached(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    Object elts = modelElement.eGet(feature);
    if (elts instanceof java.util.Collection<?>) {
      int card = 0;
      for (EObject obj : ((java.util.Collection<? extends EObject>) elts)) {
        if (obj.eClass().equals(cls)) {
          card++;
        }
        if (card == 2) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {
    CompoundCommand cmd = new CompoundCommand();

    cmd.append(DataNamingHelper.getNamingCommand(editingDomain, createdElement, feature));

    if (containerElement instanceof BooleanType) {
      cmd.append(new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement));
    }

    return cmd;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.LITERAL_BOOLEAN_VALUE;
  }
}
