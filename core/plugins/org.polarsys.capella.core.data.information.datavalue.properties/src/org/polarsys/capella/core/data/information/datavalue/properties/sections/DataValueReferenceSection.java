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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The DataValueReference section.
 */
public abstract class DataValueReferenceSection extends DataValueSection {

  protected SimpleSemanticField _referencedValueField;
  protected SimpleSemanticField _referencedPropertyField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _referencedValueField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("DataValueReference.ReferencedValue.Label"), getWidgetFactory(), new SimpleSemanticFieldController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);

        executeCommand(updateType(semanticElement, semanticFeature));
        refresh();
      }
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.BrowseSemanticField#handleDeleteButtonClicked()
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();

        executeCommand(removeType(semanticElement));
        refresh();
      }
    };
    _referencedValueField.setDisplayedInWizard(displayedInWizard);

    _referencedPropertyField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("DataValueReference.ReferencedProperty.Label"), getWidgetFactory(), new SimpleSemanticFieldController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button) {
        super.handleOpenButtonClicked(button);

        executeCommand(updateType(semanticElement, semanticFeature));
        refresh();
      }
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.BrowseSemanticField#handleDeleteButtonClicked()
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();

        executeCommand(removeType(semanticElement));
        refresh();
      }
    };
    _referencedPropertyField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @param semanticElement
   * @param semanticFeature
   * @return
   */
  protected AbstractReadWriteCommand updateType(final EObject semanticElement, final EStructuralFeature semanticFeature) {
    return new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject obj = (EObject) semanticElement.eGet(semanticFeature);
        if (obj instanceof AbstractTypedElement) {
          semanticElement.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) obj).getAbstractType());
        }
      }
      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
  }

  /**
   * @param semanticElement
   * @return
   */
  protected AbstractReadWriteCommand removeType(final EObject semanticElement) {
    return new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        semanticElement.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), null);
      }
      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return "Edit " + semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(_referencedPropertyField);
    fields.add(_referencedValueField);

    return fields;
  }
}
