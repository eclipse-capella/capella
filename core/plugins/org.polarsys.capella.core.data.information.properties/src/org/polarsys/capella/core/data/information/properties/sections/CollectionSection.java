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
package org.polarsys.capella.core.data.information.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.Collection_IndexController;
import org.polarsys.capella.core.data.information.properties.controllers.Collection_ValueController;
import org.polarsys.capella.core.data.information.properties.fields.AggregationKindGroup;
import org.polarsys.capella.core.data.information.properties.fields.CollectionBooleanPropertiesCheckbox;
import org.polarsys.capella.core.data.information.properties.fields.CollectionKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleEditableSemanticField;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * The Collection section.
 */
public class CollectionSection extends MultiplicityElementSection {

  private CollectionBooleanPropertiesCheckbox collectionBooleanPropertiesCheckbox;

  private VisibilityKindGroup visibilityKindGroup;
  private CollectionKindGroup collectionKindGroup;
  private AggregationKindGroup aggregationKindGroup;

  private MultipleSemanticField indexField;

  private CompositionMultipleSemanticField superTypes;
  private SimpleEditableSemanticField minValueField;
  private SimpleEditableSemanticField maxValueField;
  private SimpleEditableSemanticField defaultValueField;

  /**
   * Default constructor.
   */
  public CollectionSection() {
    super(true, true, true, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    collectionBooleanPropertiesCheckbox = new CollectionBooleanPropertiesCheckbox(getCheckGroup(), getWidgetFactory(), true, true);
    collectionBooleanPropertiesCheckbox.setDisplayedInWizard(displayedInWizard);

    collectionKindGroup = new CollectionKindGroup(parent, getWidgetFactory());
    collectionKindGroup.setDisplayedInWizard(displayedInWizard);

    aggregationKindGroup = new AggregationKindGroup(parent, getWidgetFactory());
    aggregationKindGroup.setDisplayedInWizard(displayedInWizard);

    superTypes = new CompositionMultipleSemanticField(getReferencesGroup(),
        Messages.getString("GeneralizableElementSection_SuperType_Label"), getWidgetFactory(), new GeneralizableElementController()); //$NON-NLS-1$
    superTypes.setDisplayedInWizard(displayedInWizard);

    indexField = new MultipleSemanticField(getReferencesGroup(), Messages.getString("Collection.IndexLabel"), getWidgetFactory(), new Collection_IndexController()) { //$NON-NLS-1$
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.MultipleSemanticField#openTransferDialog(org.eclipse.swt.widgets.Button,
       *      java.util.List, java.util.List, java.lang.String, java.lang.String)
       */
      @Override
      protected List<EObject> openTransferDialog(Button button, List<EObject> currentElements, List<EObject> availableElements, String title, String message) {
        return SelectionDialogHelper.openOrderedUniqueTransferDialog(availableElements, currentElements, button.getShell(), title, message);
      }
    };
    indexField.setDisplayedInWizard(displayedInWizard);

    minValueField = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("MultiplicityElement.MinValueLabel"), getWidgetFactory(), "", new Collection_ValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    minValueField.setDisplayedInWizard(displayedInWizard);
    maxValueField = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("MultiplicityElement.MaxValueLabel"), getWidgetFactory(), "", new Collection_ValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    maxValueField.setDisplayedInWizard(displayedInWizard);
    defaultValueField = new SimpleEditableSemanticField(getReferencesGroup(),
        Messages.getString("MultiplicityElement.DefaultValueLabel"), getWidgetFactory(), "", new Collection_ValueController()); //$NON-NLS-1$ //$NON-NLS-2$
    defaultValueField.setDisplayedInWizard(displayedInWizard);

    visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    visibilityKindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    collectionBooleanPropertiesCheckbox.loadData(capellaElement);
    visibilityKindGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getCollection_Visibility());
    collectionKindGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getCollection_Kind());
    aggregationKindGroup.loadData(capellaElement, InformationPackage.eINSTANCE.getCollection_AggregationKind());
    superTypes.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(), CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    indexField.loadData(capellaElement, InformationPackage.eINSTANCE.getCollection_Index());
    minValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMinValue());
    maxValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedMaxValue());
    defaultValueField.loadData(capellaElement, InformationPackage.eINSTANCE.getMultiplicityElement_OwnedDefaultValue());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getCollection()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(aggregationKindGroup);
    fields.add(collectionBooleanPropertiesCheckbox);
    fields.add(collectionKindGroup);
    fields.add(defaultValueField);
    fields.add(indexField);
    fields.add(maxValueField);
    fields.add(minValueField);
    fields.add(superTypes);
    fields.add(visibilityKindGroup);

    return fields;
  }
}
