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
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _referencedValueField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("DataValueReference.ReferencedValue.Label"), getWidgetFactory(), new SimpleSemanticFieldController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);

        executeCommand(updateType(_semanticElement, _semanticFeature));
        refresh();
      }
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.BrowseSemanticField#handleDeleteButtonClicked()
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();

        executeCommand(removeType(_semanticElement));
        refresh();
      }
    };
    _referencedValueField.setDisplayedInWizard(displayedInWizard);

    _referencedPropertyField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("DataValueReference.ReferencedProperty.Label"), getWidgetFactory(), new SimpleSemanticFieldController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.SimpleSemanticField#handleOpenButtonClicked(org.eclipse.swt.widgets.Button)
       */
      @Override
      protected void handleOpenButtonClicked(Button button_p) {
        super.handleOpenButtonClicked(button_p);

        executeCommand(updateType(_semanticElement, _semanticFeature));
        refresh();
      }
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.BrowseSemanticField#handleDeleteButtonClicked()
       */
      @Override
      protected void handleDeleteButtonClicked() {
        super.handleDeleteButtonClicked();

        executeCommand(removeType(_semanticElement));
        refresh();
      }
    };
    _referencedPropertyField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @param semanticElement_p
   * @param semanticFeature_p
   * @return
   */
  protected AbstractReadWriteCommand updateType(final EObject semanticElement_p, final EStructuralFeature semanticFeature_p) {
    return new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        EObject obj = (EObject) semanticElement_p.eGet(semanticFeature_p);
        if (obj instanceof AbstractTypedElement) {
          semanticElement_p.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), ((AbstractTypedElement) obj).getAbstractType());
        }
      }
      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return "Edit " + semanticElement_p.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
  }

  /**
   * @param semanticElement_p
   * @return
   */
  protected AbstractReadWriteCommand removeType(final EObject semanticElement_p) {
    return new AbstractReadWriteCommand() {
      /**
       * {@inheritDoc}
       */
      public void run() {
        semanticElement_p.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), null);
      }
      /**
       * {@inheritDoc}
       */
      @Override
      public String getName() {
        return "Edit " + semanticElement_p.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_referencedPropertyField);
    fields.add(_referencedValueField);

    return fields;
  }
}
