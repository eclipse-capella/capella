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

package org.polarsys.capella.core.data.cs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.core.properties.controllers.GeneralizableElementController;
import org.polarsys.capella.core.data.core.properties.fields.VisibilityKindGroup;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.CompositionMultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;

/**
 * The Interface section.
 */
public class InterfaceSection extends NamedElementSection {

  private VisibilityKindGroup _visibilityKindGroup;
  private CompositionMultipleSemanticField _superTypeWidget;
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

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    _superTypeWidget = new CompositionMultipleSemanticField(main, Messages.InterfaceSection_SuperType_Label, getWidgetFactory(), new GeneralizableElementController());
    _superTypeWidget.setDisplayedInWizard(displayedInWizard);

    Group exchangeItemGroup = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    exchangeItemGroup.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
    layoutData.horizontalSpan = 2;
    exchangeItemGroup.setLayoutData(layoutData);

    _containmentTableField = new ContainmentTableField(
      exchangeItemGroup, getWidgetFactory(),
      null,
      CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM,
      CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION,
      Messages.InterfaceSection_Table_Title,
      Messages.InterfaceSection_SelectionDialog_Message
    ) {
      /**
       * It must be possible to add several times an ExchangeItem to an Interface.<br>
       * So several ExchangeItemAllocations will be created.
       */
      @Override
      protected java.util.List<EObject> getAvailableElementsToAdd() {
        final List<EObject> availableElements = new ArrayList<>(0);
        AbstractReadOnlyCommand command = new AbstractReadOnlyCommand() {
          /**
           * {@inheritDoc}
           */
          @SuppressWarnings("synthetic-access")
          public void run() {
            IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), semanticFeature);
            if (null != query) {
              availableElements.addAll(query.getAvailableElements(semanticElement));
            }
          }
        };
        TransactionHelper.getExecutionManager(semanticElement).execute(command);
        return availableElements;
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

    _visibilityKindGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralClass_Visibility());
    _superTypeWidget.loadData(capellaElement, CapellacorePackage.eINSTANCE.getGeneralizableElement_Super(), CapellacorePackage.eINSTANCE.getGeneralizableElement_OwnedGeneralizations());
    _containmentTableField.loadData(capellaElement, CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object object) {
    EObject eObject = super.selection(object);
    return (null != eObject) && (CsPackage.Literals.INTERFACE == eObject.eClass());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<>();

    fields.addAll(super.getSemanticFields());
    fields.add(_containmentTableField);
    fields.add(_superTypeWidget);
    fields.add(_visibilityKindGroup);

    return fields;
  }
}
