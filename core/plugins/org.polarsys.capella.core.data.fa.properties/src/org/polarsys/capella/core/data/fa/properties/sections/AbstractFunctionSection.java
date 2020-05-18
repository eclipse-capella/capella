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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.properties.controllers.AbstractFunction_AvailableInStatesController;
import org.polarsys.capella.core.data.fa.properties.controllers.FunctionRealizationsController;
import org.polarsys.capella.core.data.fa.properties.fields.FunctionKindGroup;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;

/**
 * The AbstractFunction section.
 */
public abstract class AbstractFunctionSection extends NamedElementSection {

  private boolean showKind;
  private boolean showRealizations;
  private MultipleSemanticField realizedWidget;
  private MultipleSemanticField availableInStatesField;
  private FunctionKindGroup functionKindGroup;
  protected TextValueGroup conditionField;

  /**
   * Default constructor.
   */
  public AbstractFunctionSection() {
    this(true, true);
  }

  /**
   * Constructor.
   * @param showKind
   * @param showRealizations
   */
  public AbstractFunctionSection(boolean showKind, boolean showRealizations) {
    this.showKind = showKind;
    this.showRealizations = showRealizations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);
    boolean displayedInWizard = isDisplayedInWizard();

    if (showKind) {
      functionKindGroup = new FunctionKindGroup(parent, getWidgetFactory(), true) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected void selectButton(Button button, Enumerator enumerated) {
          conditionField.setEnabled(FunctionKind.ROUTE.equals(enumerated) || FunctionKind.SELECT.equals(enumerated));
          super.selectButton(button, enumerated);
        }
      };
      functionKindGroup.setDisplayedInWizard(displayedInWizard);

      conditionField = new TextValueGroup(parent, Messages.AbstractFunctionSection_Condition_Label, getWidgetFactory());
      conditionField.setDisplayedInWizard(displayedInWizard);
    }

    availableInStatesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.AbstractFunctionSection_AvailableInStates_Label, getWidgetFactory(), new AbstractFunction_AvailableInStatesController());
    availableInStatesField.setDisplayedInWizard(displayedInWizard);

    if (showRealizations) {
      realizedWidget = new MultipleSemanticField(getReferencesGroup(),
          ICommonConstants.EMPTY_STRING, getWidgetFactory(), new FunctionRealizationsController());
      realizedWidget.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != functionKindGroup) {
      functionKindGroup.loadData(capellaElement, FaPackage.eINSTANCE.getAbstractFunction_Kind());
    }
    if (null != conditionField) {
      conditionField.loadData(capellaElement, FaPackage.eINSTANCE.getAbstractFunction_Condition());
    }
    if (null != availableInStatesField) {
      availableInStatesField.loadData(capellaElement, FaPackage.eINSTANCE.getAbstractFunction_AvailableInStates());
    }
    if (null != realizedWidget) {
      realizedWidget.loadData(capellaElement, FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionRealizations());
      
      if (EcoreUtil2.isContainedBy(capellaElement, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
        realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedActivities_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
        realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedSystemFunctions_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
        realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedLogicalFunctions_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
        realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedPhysicalFunctions_Label);
      } else {
        realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedFunctions_Label);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(functionKindGroup);
    fields.add(conditionField);
    fields.add(availableInStatesField);
    fields.add(realizedWidget);

    return fields;
  }
}
