/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * The DataType customized section class.
 */
public class NavigableCheckbox extends AbstractSemanticCheckboxGroup {
  protected Button _isNavigableBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public NavigableCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory);

    _isNavigableBtn = createButton(null, Messages.getString("Property.IsNavigablelabel"), parent); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement property) {
    loadData(property, null);

    EObject assoc = _semanticElement.eContainer();
    if (assoc instanceof Classifier) {
      List<EObject> lst = EObjectExt.getReferencers(_semanticElement, InformationPackage.Literals.ASSOCIATION, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS);
      if (!lst.isEmpty()) {
        assoc = lst.get(0);
      }
    }

    if (null != assoc) {
      List<?> lst = (List<?>) assoc.eGet(InformationPackage.eINSTANCE.getAssociation_NavigableMembers());
      _isNavigableBtn.setSelection(lst.contains(_semanticElement));
    }
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event) {
    EObject ownerElement = _semanticElement.eContainer();
    EObject typeElement = (EObject) _semanticElement.eGet(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);

    if (_isNavigableBtn.getSelection()) {
      if ((ownerElement instanceof Association) && (typeElement instanceof Classifier)) {
        Property oppositeMember = getOppositeMember((Association) ownerElement, (Property) _semanticElement);
        if (oppositeMember != null) {
          EObject oppositeTypeElement = (EObject) oppositeMember.eGet(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
          if (oppositeTypeElement != null) {
            moveDataValue(_semanticElement, oppositeTypeElement, CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES);
            addDataValue(ownerElement, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS, _semanticElement);
          }
        }
      }
    }
    else {
      EObject referencerElement = null;
      List<EObject> lst = EObjectExt.getReferencers(_semanticElement, InformationPackage.Literals.ASSOCIATION, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS);
      if (!lst.isEmpty()) {
        referencerElement = lst.get(0);
      }

      if ((typeElement instanceof Classifier) && (referencerElement instanceof Association)) {
        moveDataValue(_semanticElement, referencerElement, InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS);
        removeDataValue(referencerElement, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS, _semanticElement);
      }
    }
  }

  /**
   * @param association
   * @param member
   * @return
   */
  private Property getOppositeMember(Association association, Property member) {
    List<Property> members = new ArrayList<Property>();
    members.addAll(association.getOwnedMembers());
    members.addAll(association.getNavigableMembers());
    members.remove(member);
    return members.get(0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_isNavigableBtn);

    return fields;
  }
}
