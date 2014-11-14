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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    namedElementGroup.enableNameField(false);

    Group main = getWidgetFactory().createGroup(_rootParentComposite, ""); //$NON-NLS-1$
    main.setLayout(new GridLayout(5, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    interactionOperatorKindGroup = new InteractionOperatorKindGroup(_rootParentComposite, getWidgetFactory(), true);
    interactionOperatorKindGroup.setDisplayedInWizard(displayedInWizard);

    coveredInstanceRolesWidget = new MultipleSemanticField(main, Messages.getString("CombinedFragmentSection_CoveredInstanceRoles_Label"), getWidgetFactory(), //$NON-NLS-1$
      new IMultipleSemanticFieldController() {
        public List<EObject> writeOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, List<EObject> values_p) {
          Set<InstanceRole> minima = AbstractFragmentExt.getMinimalCoveredInstanceRoles((CombinedFragment) semanticElement_p);
          for (InstanceRole instanceRole : minima) {
      			if (!values_p.contains(instanceRole)) {
      				values_p.add(instanceRole);
      				// log !
      				final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
      				__logger.info(new EmbeddedMessage("Unable to remove covered instance role : "+ instanceRole.getName(), "Validation")); //$NON-NLS-1$ //$NON-NLS-2$
      			}
    		  }
          for (InteractionOperand operand : ((CombinedFragment) semanticElement_p).getReferencedOperands()) {
            operand.getCoveredInstanceRoles().clear();
            for (EObject value : values_p) {
              operand.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          InteractionFragment start = ((CombinedFragment) semanticElement_p).getStart();
          if (start != null) {
            start.getCoveredInstanceRoles().clear();
            for (EObject value : values_p) {
              start.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          InteractionFragment finish = ((CombinedFragment) semanticElement_p).getFinish();
          if (start != null) {
            finish.getCoveredInstanceRoles().clear();
            for (EObject value : values_p) {
              finish.getCoveredInstanceRoles().add((InstanceRole) value);
            }
          }
          UIUtil.getInstance().refreshActiveDiagram(null);
          return values_p;
        }
        public List<EObject> readOpenValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, boolean available_p) {
          List<EObject> result = new ArrayList<EObject>();
          if (available_p) {
            result.addAll(((Scenario) semanticElement_p.eContainer()).getOwnedInstanceRoles());
          } else {
            result.addAll(loadValues(semanticElement_p, semanticFeature_p));
          }
          return result;
        }
        public List<EObject> loadValues(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p) {
          List<EObject> result = new ArrayList<EObject>();
          result.addAll(AbstractFragmentExt.getCoveredInstanceRoles(
              (FragmentEnd) ((CombinedFragment) semanticElement_p).getStart(),
              (FragmentEnd) ((CombinedFragment) semanticElement_p).getFinish(),
              (Scenario) semanticElement_p.eContainer()));
          return result;
        }
      }) {
      /**
       * {@inheritDoc}
       */
      @Override
      protected void doDeleteCommand(EObject element_p, EStructuralFeature feature_p) {
       
        // clear action lead to set the covering to its minimal usage for model correctness : 
        // just keeping instance roles with messages within the combined fragment.
        Set<InstanceRole> covered = AbstractFragmentExt.getMinimalCoveredInstanceRoles((CombinedFragment)element_p);
        
        for (InteractionOperand operand : ((CombinedFragment) element_p).getReferencedOperands()) {
          operand.getCoveredInstanceRoles().clear();
          operand.getCoveredInstanceRoles().addAll(covered);
        }
        InteractionFragment start = ((CombinedFragment) element_p).getStart();
        if (start != null) {
          start.getCoveredInstanceRoles().clear();
          start.getCoveredInstanceRoles().addAll(covered);
        }
        InteractionFragment finish = ((CombinedFragment) element_p).getFinish();
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
   * @see org.polarsys.capella.core.ui.properties.sections.AbstractSection#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    interactionOperatorKindGroup.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getCombinedFragment_Operator());
    coveredInstanceRolesWidget.loadData(capellaElement_p, InteractionPackage.eINSTANCE.getInteractionFragment_CoveredInstanceRoles());
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
