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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
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
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextValueGroup;

/**
 * The AbstractFunction section.
 */
public abstract class AbstractFunctionSection extends NamedElementSection {

  private boolean _showKind;
  private boolean _showRealizations;
  private MultipleSemanticField _realizedWidget;
  private MultipleSemanticField _availableInStatesField;
  private FunctionKindGroup _functionKindGroup;
  protected TextValueGroup _conditionField;

  /**
   * Default constructor.
   */
  public AbstractFunctionSection() {
    this(true, true);
  }

  /**
   * Constructor.
   * @param showKind_p
   * @param showRealizations_p
   */
  public AbstractFunctionSection(boolean showKind_p, boolean showRealizations_p) {
    _showKind = showKind_p;
    _showRealizations = showRealizations_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    super.createControls(parent_p, aTabbedPropertySheetPage_p);

    boolean displayedInWizard = isDisplayedInWizard();

    if (_showKind) {
      _functionKindGroup = new FunctionKindGroup(_rootParentComposite, getWidgetFactory(), true) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected void selectButton(Button button_p, Enumerator enumerated_p) {
          _conditionField.setEnabled(FunctionKind.ROUTE.equals(enumerated_p) || FunctionKind.SELECT.equals(enumerated_p));
          super.selectButton(button_p, enumerated_p);
        }
      };
      _functionKindGroup.setDisplayedInWizard(displayedInWizard);

      _conditionField = new TextValueGroup(_rootParentComposite, Messages.AbstractFunctionSection_Condition_Label, getWidgetFactory());
      _conditionField.setDisplayedInWizard(displayedInWizard);
    }

    _availableInStatesField = new MultipleSemanticField(getReferencesGroup(),
        Messages.AbstractFunctionSection_AvailableInStates_Label, getWidgetFactory(), new AbstractFunction_AvailableInStatesController());
    _availableInStatesField.setDisplayedInWizard(displayedInWizard);

    if (_showRealizations) {
      _realizedWidget = new MultipleSemanticField(getReferencesGroup(),
          ICommonConstants.EMPTY_STRING, getWidgetFactory(), new FunctionRealizationsController());
      _realizedWidget.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(CapellaElement capellaElement_p) {
    super.loadData(capellaElement_p);

    if (null != _functionKindGroup) {
      _functionKindGroup.loadData(capellaElement_p, FaPackage.eINSTANCE.getAbstractFunction_Kind());
    }
    if (null != _conditionField) {
      _conditionField.loadData(capellaElement_p, FaPackage.eINSTANCE.getAbstractFunction_Condition());
    }
    if (null != _availableInStatesField) {
      _availableInStatesField.loadData(capellaElement_p, FaPackage.eINSTANCE.getAbstractFunction_AvailableInStates());
    }
    if (null != _realizedWidget) {
      _realizedWidget.loadData(capellaElement_p, FaPackage.eINSTANCE.getAbstractFunction_OwnedFunctionRealizations());
      
      if (EcoreUtil2.isContainedBy(capellaElement_p, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
        _realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedActivities_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement_p, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
        _realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedSystemFunctions_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
        _realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedLogicalFunctions_Label);
      } else if (EcoreUtil2.isContainedBy(capellaElement_p, EpbsPackage.Literals.EPBS_ARCHITECTURE)) {
        _realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedPhysicalFunctions_Label);
      } else {
        _realizedWidget.setLabel(Messages.AbstractFunctionSection_RealizedFunctions_Label);
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
    fields.add(_functionKindGroup);
    fields.add(_conditionField);
    fields.add(_availableInStatesField);
    fields.add(_realizedWidget);

    return fields;
  }
}
