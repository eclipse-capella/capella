/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.properties.Messages;
import org.polarsys.capella.core.data.information.properties.controllers.Service_RealizedExchangeItemsController;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The Service section.
 */
public class ServiceSection extends NamedElementSection {

  private MultipleSemanticField _thrownExceptionsField;
  private MultipleSemanticField _realizedExchangeItemsField;
  private VisibilityKindGroup _visibilityKindGroup;
  private ContainmentTableField _containmentTableField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _visibilityKindGroup = new VisibilityKindGroup(parent, getWidgetFactory());
    _visibilityKindGroup.setDisplayedInWizard(displayedInWizard);

    _thrownExceptionsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ServiceSection_ThrownExceptions_Label"), getWidgetFactory(), new AbstractMultipleSemanticFieldController() { //$NON-NLS-1$
          @Override
          protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
            return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), InformationPackage.eINSTANCE.getService_ThrownExceptions());
          }
        });
    _thrownExceptionsField.setDisplayedInWizard(displayedInWizard);

    _realizedExchangeItemsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("ServiceSection_RealizedExchangeItems_Label"), getWidgetFactory(), new Service_RealizedExchangeItemsController()); //$NON-NLS-1$
    _realizedExchangeItemsField.setDisplayedInWizard(displayedInWizard);

    Group exchangeItemGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    exchangeItemGroup.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
    layoutData.horizontalSpan = 2;
    exchangeItemGroup.setLayoutData(layoutData);

    _containmentTableField = new ContainmentTableField(
      exchangeItemGroup,
      getWidgetFactory(),
      null, null,
      InformationPackage.Literals.PARAMETER,
      Messages.getString("ServiceSection_Table_Title"), //$NON-NLS-1$
      Messages.getString("ServiceSection_SelectionDialog_Message")) //$NON-NLS-1$
    {
      /**
       * @see org.polarsys.capella.core.ui.properties.fields.AbstractStructuredRepresentationField.properties.fields.ReferencesTableField#handleAdd()
       */
      @SuppressWarnings("unchecked")
      @Override
      protected void handleAdd() {
        AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
          @SuppressWarnings("synthetic-access")
          public void run() {
            Parameter item = InformationFactory.eINSTANCE.createParameter();
            ((List<EObject>) semanticElement.eGet(semanticFeature)).add(item);
            EditingDomain domain = TransactionHelper.getEditingDomain(item);
            Command cmd = CreationHelper.getAdditionnalCommand(domain, item);
            domain.getCommandStack().execute(cmd);

            CapellaUIPropertiesPlugin.getDefault().openWizard(item);
          }
        };
        TransactionHelper.getExecutionManager(semanticElement).execute(command);
        refreshViewer();
      }
    };
    _containmentTableField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _visibilityKindGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getFeature_Visibility());
    _thrownExceptionsField.loadData(capellaElement, InformationPackage.eINSTANCE.getService_ThrownExceptions());
    _realizedExchangeItemsField.loadData(capellaElement, InformationPackage.eINSTANCE.getOperation_OwnedExchangeItemRealizations());
    _containmentTableField.loadData(capellaElement, InformationPackage.eINSTANCE.getOperation_OwnedParameters());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InformationPackage.eINSTANCE.getService()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(_realizedExchangeItemsField);
    fields.add(_containmentTableField);
    fields.add(_thrownExceptionsField);
    fields.add(_visibilityKindGroup);

    return fields;
  }
}
