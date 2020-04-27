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
package org.polarsys.capella.core.data.interaction.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The InteractionUse section.
 */
public class InteractionUseSection extends NamedElementSection {

  private MultipleSemanticField coveredInstanceRolesField;
  private SimpleSemanticField referencedScenarioField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    namedElementGroup.enableNameField(false);

    Group main = getWidgetFactory().createGroup(parent, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    coveredInstanceRolesField = new MultipleSemanticField(main, Messages.getString("InteractionUseSection_CoveredInstanceRoles_Label"), getWidgetFactory(), //$NON-NLS-1$
        new IMultipleSemanticFieldController() {
          @Override
          public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, List<EObject> values) {
            InteractionFragment start = ((InteractionUse) semanticElement).getStart();
            if (start != null) {
              start.getCoveredInstanceRoles().clear();
              for (EObject value : values) {
                start.getCoveredInstanceRoles().add((InstanceRole) value);
              }
            }
            InteractionFragment finish = ((InteractionUse) semanticElement).getFinish();
            if (start != null) {
              finish.getCoveredInstanceRoles().clear();
              for (EObject value : values) {
                finish.getCoveredInstanceRoles().add((InstanceRole) value);
              }
            }
            UIUtil.getInstance().refreshActiveDiagram(null);
            return values;
          }

          @Override
          public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, boolean available) {
            List<EObject> result = new ArrayList<EObject>();
            if (available) {
              result.addAll(((Scenario) semanticElement.eContainer()).getOwnedInstanceRoles());
            } else {
              result.addAll(loadValues(semanticElement, semanticFeature));
            }
            return result;
          }

          @Override
          public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
            List<EObject> result = new ArrayList<EObject>();
            result.addAll(((InteractionUse) semanticElement).getStart().getCoveredInstanceRoles());
            return result;
          }
        }) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void doDeleteCommand(EObject element, EStructuralFeature feature) {
        InteractionFragment start = ((InteractionUse) element).getStart();
        if (start != null) {
          start.getCoveredInstanceRoles().clear();
        }
        InteractionFragment finish = ((InteractionUse) element).getFinish();
        if (start != null) {
          finish.getCoveredInstanceRoles().clear();
        }
        UIUtil.getInstance().refreshActiveDiagram(null);
        setValueTextField(null);
      }
    };
    coveredInstanceRolesField.setDisplayedInWizard(displayedInWizard);

    referencedScenarioField =
        new SimpleSemanticField(main,
            Messages.getString("InteractionUseSection_ReferencedScenario_Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    referencedScenarioField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    coveredInstanceRolesField.loadData(capellaElement, InteractionPackage.eINSTANCE.getInteractionFragment_CoveredInstanceRoles());
    referencedScenarioField.loadData(capellaElement, InteractionPackage.eINSTANCE.getInteractionUse_ReferencedScenario());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InteractionPackage.eINSTANCE.getInteractionUse()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(coveredInstanceRolesField);
    fields.add(referencedScenarioField);

    return fields;
  }
}
