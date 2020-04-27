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
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.data.interaction.properties.fields.InteractionOperatorKindGroup;
import org.polarsys.capella.core.model.helpers.AbstractFragmentExt;
import org.polarsys.capella.core.ui.properties.controllers.IMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The CombinedFragment section.
 */
public class CombinedFragmentSection extends NamedElementSection {

  private MultipleSemanticField coveredInstanceRolesWidget;
  private InteractionOperatorKindGroup interactionOperatorKindGroup;

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

    interactionOperatorKindGroup = new InteractionOperatorKindGroup(parent, getWidgetFactory(), true);
    interactionOperatorKindGroup.setDisplayedInWizard(displayedInWizard);

    coveredInstanceRolesWidget = new MultipleSemanticField(main, Messages.getString("CombinedFragmentSection_CoveredInstanceRoles_Label"), getWidgetFactory(), //$NON-NLS-1$
      new IMultipleSemanticFieldController() {
        public List<EObject> writeOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, List<EObject> values) {
          Set<InstanceRole> minima = AbstractFragmentExt.getMinimalCoveredInstanceRoles((CombinedFragment) semanticElement);
          for (InstanceRole instanceRole : minima) {
      			if (!values.contains(instanceRole)) {
      				values.add(instanceRole);
      				// log !
      				final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
      				__logger.info(new EmbeddedMessage("Unable to remove covered instance role : "+ instanceRole.getName(), "Validation")); //$NON-NLS-1$ //$NON-NLS-2$
      			}
    		  }
          for (InteractionOperand operand : ((CombinedFragment) semanticElement).getReferencedOperands()) {
            operand.getCoveredInstanceRoles().clear();
            for (EObject value : values) {
              operand.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          InteractionFragment start = ((CombinedFragment) semanticElement).getStart();
          if (start != null) {
            start.getCoveredInstanceRoles().clear();
            for (EObject value : values) {
              start.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          InteractionFragment finish = ((CombinedFragment) semanticElement).getFinish();
          if (start != null) {
            finish.getCoveredInstanceRoles().clear();
            for (EObject value : values) {
              finish.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          UIUtil.getInstance().refreshActiveDiagram(null);
          return values;
        }
        public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, boolean available) {
          List<EObject> result = new ArrayList<EObject>();
          if (available) {
            result.addAll(((Scenario) semanticElement.eContainer()).getOwnedInstanceRoles());
          } else {
            result.addAll(loadValues(semanticElement, semanticFeature));
          }
          return result;
        }
        public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
          List<EObject> result = new ArrayList<EObject>();
          result.addAll(AbstractFragmentExt.getCoveredInstanceRoles(
              (FragmentEnd) ((CombinedFragment) semanticElement).getStart(),
              (FragmentEnd) ((CombinedFragment) semanticElement).getFinish(),
              (Scenario) semanticElement.eContainer()));
          return result;
        }
      }) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void doDeleteCommand(EObject element, EStructuralFeature feature) {
       
        // clear action lead to set the covering to its minimal usage for model correctness : 
        // just keeping instance roles with messages within the combined fragment.
        Set<InstanceRole> covered = AbstractFragmentExt.getMinimalCoveredInstanceRoles((CombinedFragment)element);
        
        for (InteractionOperand operand : ((CombinedFragment) element).getReferencedOperands()) {
          operand.getCoveredInstanceRoles().clear();
          operand.getCoveredInstanceRoles().addAll(covered);
        }
        InteractionFragment start = ((CombinedFragment) element).getStart();
        if (start != null) {
          start.getCoveredInstanceRoles().clear();
          start.getCoveredInstanceRoles().addAll(covered);
        }
        InteractionFragment finish = ((CombinedFragment) element).getFinish();
        if (start != null) {
          finish.getCoveredInstanceRoles().clear();
          finish.getCoveredInstanceRoles().addAll(covered);
        }
        UIUtil.getInstance().refreshActiveDiagram(null);
        
        setValueTextField(covered);
      }
    };
    coveredInstanceRolesWidget.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    interactionOperatorKindGroup.loadData(capellaElement, InteractionPackage.eINSTANCE.getCombinedFragment_Operator());
    coveredInstanceRolesWidget.loadData(capellaElement, InteractionPackage.eINSTANCE.getInteractionFragment_CoveredInstanceRoles());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == InteractionPackage.eINSTANCE.getCombinedFragment()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(coveredInstanceRolesWidget);
    fields.add(interactionOperatorKindGroup);

    return fields;
  }
}
