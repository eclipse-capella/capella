/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.core.properties.controllers.TypedElementController;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The TypedElement section.
 */
public abstract class TypedElementSection extends NamedElementSection {

  private SimpleSemanticField _abstractTypeField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _abstractTypeField = createTypeField();
    _abstractTypeField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * 
   */
  protected SimpleSemanticField createTypeField() {
    return new SimpleSemanticField(getReferencesGroup(), Messages.getString("TypedElement.TypeLabel"), getWidgetFactory(), new TypedElementController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);
        // force the update of the enable status
        refresh();
      }
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.BrowseSemanticField#handleDeleteButtonClicked()
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();
        // force the update of the enable status
        refresh();
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (capellaElement instanceof AbstractTypedElement) {
      _abstractTypeField.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType());
    } else if (capellaElement instanceof Collection) {
      _abstractTypeField.loadData(capellaElement, InformationPackage.eINSTANCE.getCollection_Type());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_abstractTypeField);

    return fields;
  }
}
