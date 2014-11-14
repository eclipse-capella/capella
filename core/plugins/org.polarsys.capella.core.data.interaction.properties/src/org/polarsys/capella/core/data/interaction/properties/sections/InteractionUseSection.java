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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    namedElementGroup.enableNameField(false);

    Group main = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(5, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    coveredInstanceRolesField = new MultipleSemanticField(main, Messages.getString("InteractionUseSection_CoveredInstanceRoles_Label"), getWidgetFactory(), //$NON-NLS-1$
        new IMultipleSemanticFieldController() {
          @Override
          public List<EObject> writeOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, List<EObject> values_p) {
            InteractionFragment start = ((InteractionUse) semanticElement_p).getStart();
            if (start != null) {
              start.getCoveredInstanceRoles().clear();
              for (EObject value : values_p) {
                start.getCoveredInstanceRoles().add((InstanceRole) value);
              }
            }
            InteractionFragment finish = ((InteractionUse) semanticElement_p).getFinish();
            if (start != null) {
              finish.getCoveredInstanceRoles().clear();
              for (EObject value : values_p) {
                finish.getCoveredInstanceRoles().add((InstanceRole) value);
              }
            }
            UIUtil.getInstance().refreshActiveDiagram(null);
            return values_p;
          }

          @Override
          public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, boolean available_p) {
            List<EObject> result = new ArrayList<EObject>();
            if (available_p) {
              result.addAll(((Scenario) semanticElement_p.eContainer()).getOwnedInstanceRoles());
            } else {
              result.addAll(loadValues(semanticElement_p, semanticFeature_p));
            }
            return result;
          }

          @Override
          public List<EObject> loadValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
            List<EObject> result = new ArrayList<EObject>();
            result.addAll(((InteractionUse) semanticElement_p).getStart().getCoveredInstanceRoles());
            return result;
          }
        }) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void doDeleteCommand(EObject element_p, EStructuralFeature feature_p) {
        InteractionFragment start = ((InteractionUse) element_p).getStart();
        if (start != null) {
          start.getCoveredInstanceRoles().clear();
        }
        InteractionFragment finish = ((InteractionUse) element_p).getFinish();
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
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    coveredInstanceRolesField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getInteractionFragment_CoveredInstanceRoles());
    referencedScenarioField.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getInteractionUse_ReferencedScenario());
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
