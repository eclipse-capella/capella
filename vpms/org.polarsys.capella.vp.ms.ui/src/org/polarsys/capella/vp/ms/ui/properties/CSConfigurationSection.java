/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui.properties;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static org.polarsys.capella.vp.ms.MsPackage.Literals.CS_CONFIGURATION__ELEMENTS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.provider.MsEditPlugin;

import com.google.common.collect.Collections2;

public class CSConfigurationSection extends NamedElementSection {

  private MultipleSemanticField functionsField;
  private MultipleSemanticField functionalChainsField;
  private MultipleSemanticField portsField;
  private MultipleSemanticField componentsField;
  private MultipleSemanticField scenariosField;
  private MultipleSemanticField elementsField;

  private MultipleSemanticField contextField;

  private SelectorGroup selectorGroup;

  private MultipleSemanticField childConfigurationsField;

  private KindGroup kindGroup;

  @Override
  public boolean select(Object toTest) {
    EObject obj = CapellaAdapterHelper.resolveSemanticObject(toTest);

    return obj != null && obj.eClass().equals(org.polarsys.capella.vp.ms.MsPackage.eINSTANCE.getCSConfiguration());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    selectorGroup = new SelectorGroup(rootParentComposite, getWidgetFactory(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_selector_feature"), 2); //$NON-NLS-1$
    selectorGroup.setDisplayedInWizard(displayedInWizard);

    kindGroup = new KindGroup(rootParentComposite, getWidgetFactory(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_kind_feature"), 2); //$NON-NLS-1$

    IMultipleSemanticFieldController elementsController = new DerivedElementsController();

    functionsField = new DerivedMultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_functions_feature"), getWidgetFactory(), //$NON-NLS-1$
        elementsController, CS_CONFIGURATION__ELEMENTS);
    functionsField.setDisplayedInWizard(displayedInWizard);

    functionalChainsField = new DerivedMultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_functionalChains_feature"), //$NON-NLS-1$
        getWidgetFactory(), elementsController, CS_CONFIGURATION__ELEMENTS);
    functionalChainsField.setDisplayedInWizard(displayedInWizard);

    componentsField = new DerivedMultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_components_feature"), getWidgetFactory(), //$NON-NLS-1$
        elementsController, CS_CONFIGURATION__ELEMENTS);
    componentsField.setDisplayedInWizard(displayedInWizard);

    portsField = new DerivedMultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_ports_feature"), getWidgetFactory(), elementsController, //$NON-NLS-1$
        CS_CONFIGURATION__ELEMENTS);
    portsField.setDisplayedInWizard(displayedInWizard);

    scenariosField = new DerivedMultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_scenarios_feature"), getWidgetFactory(), elementsController, //$NON-NLS-1$
        CS_CONFIGURATION__ELEMENTS);
    scenariosField.setDisplayedInWizard(displayedInWizard);

    elementsField = new MultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_other_elements"), getWidgetFactory(), elementsController); //$NON-NLS-1$
    elementsField.setDisplayedInWizard(displayedInWizard);

    childConfigurationsField = new MultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_childConfigurations_feature"), getWidgetFactory(), //$NON-NLS-1$
        new ItemProviderFieldController());

    contextField = new MultipleSemanticField(getReferencesGroup(),
        MsEditPlugin.INSTANCE.getString("_UI_CSConfiguration_context_feature"), getWidgetFactory(), //$NON-NLS-1$
        new ItemProviderFieldController());

  }

  /**
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    functionsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__FUNCTIONS);
    functionalChainsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__FUNCTIONAL_CHAINS);
    componentsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__COMPONENTS);
    portsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__PORTS);
    scenariosField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__SCENARIOS);
    elementsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__ELEMENTS);
    childConfigurationsField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__CHILD_CONFIGURATIONS);
    selectorGroup.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__SELECTOR);
    kindGroup.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__KIND);
    contextField.loadData(capellaElement, MsPackage.Literals.CS_CONFIGURATION__CONTEXT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());
    fields.add(functionsField);
    fields.add(functionalChainsField);
    fields.add(componentsField);
    fields.add(portsField);
    fields.add(scenariosField);
    fields.add(elementsField);
    fields.add(selectorGroup);
    fields.add(kindGroup);
    fields.add(childConfigurationsField);
    fields.add(contextField);
    return fields;
  }

  static class DerivedMultipleSemanticField extends MultipleSemanticField {

    final EStructuralFeature delegate;

    public DerivedMultipleSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
        IMultipleSemanticFieldController controller, EStructuralFeature delegate) {
      super(parent, label, widgetFactory, controller);
      this.delegate = delegate;
    }

    @Override
    protected void removeAllDataValue(final EObject object, final EStructuralFeature feature) {
      AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          ((Collection<?>) (((CSConfiguration) object).eGet(delegate))).removeAll((Collection<?>) object.eGet(feature));
        }
      };
      executeCommand(command);
    }
  }

  /**
   * This is a field controller that delegates reading the list of available values to the elements
   * ItemPropertyDescriptor implementation.
   */
  static class ItemProviderFieldController extends AbstractMultipleSemanticFieldController {

    @Override
    public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
        boolean available) {
      List<EObject> current = loadValues(semanticElement, semanticFeature);
      List<EObject> result = null;
      if (available) {
        AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
            .getEditingDomainFor(semanticElement);
        IItemPropertySource propertySource = (IItemPropertySource) domain.getAdapterFactory().adapt(semanticElement,
            IItemPropertySource.class);
        IItemPropertyDescriptor descriptor = propertySource.getPropertyDescriptor(semanticElement, semanticFeature);
        result = new ArrayList<EObject>((Collection<? extends EObject>) descriptor.getChoiceOfValues(semanticElement));
        result.removeAll(current);
      } else {
        result = current;
      }
      return result;
    }

    @Override
    protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
      throw new UnsupportedOperationException();
    }

  }

  /*
   *  A specialized controller that
   *   - always stores elements in CSConfiguration.elements
   *   - filters values so that the 'other elements' field does not show elements
   *     from one of the typed elements fields, and vice-versa.
   */
  private class DerivedElementsController extends ItemProviderFieldController {

    @Override
    public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
      List<EObject> values = super.loadValues(semanticElement, semanticFeature);
      if (semanticFeature == MsPackage.Literals.CS_CONFIGURATION__ELEMENTS) {
        values = new ArrayList<EObject>(Collections2.filter(values,
            not(
              or(
               instanceOf(AbstractFunction.class),
               instanceOf(FunctionalChain.class),
               instanceOf(Component.class),
               instanceOf(Port.class),
               instanceOf(Scenario.class)))));
      }
      return values;
    }

    @Override
    protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
        EObject object) {
      ((CSConfiguration) semanticElement).getElements().add((ModelElement) object);
    }

    @Override
    protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature,
        EObject object) {
      ((CSConfiguration) semanticElement).getElements().remove(object);
    }

  }

}
