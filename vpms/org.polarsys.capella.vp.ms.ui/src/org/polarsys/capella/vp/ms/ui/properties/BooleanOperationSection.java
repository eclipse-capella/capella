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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ContainmentTableField;
import org.polarsys.capella.vp.ms.MsPackage;

public class BooleanOperationSection extends CapellaElementSection {

  private ContainmentTableField _containmentTableField;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {

    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    Group exchangeItemGroup = getWidgetFactory().createGroup(rootParentComposite, ""); //$NON-NLS-1$
    exchangeItemGroup.setLayout(new GridLayout(1, false));
    GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
    layoutData.horizontalSpan = 2;
    exchangeItemGroup.setLayoutData(layoutData);

    _containmentTableField = new ContainmentTableField(exchangeItemGroup, getWidgetFactory(), null,
        MsPackage.Literals.IN_STATE_EXPRESSION__STATE, MsPackage.Literals.IN_STATE_EXPRESSION, "States/Modes :",
        "Select States") {

      // backport https://bugs.polarsys.org/show_bug.cgi?id=1164
      @Override
      @SuppressWarnings("unchecked")
      protected List<EObject> getReferencedElementsByContainedOnes() {
        List<EObject> containedElements = (List<EObject>) semanticElement.eGet(semanticFeature);
        ArrayList<EObject> referencedElements = new ArrayList<EObject>(0);
        // Collect referenced elements according specified referenced feature.
        for (EObject containedElement : containedElements) {
          if (_referencedFeature != null) {
            if (containedElement.eClass().getEAllReferences().contains(_referencedFeature)) {
              EObject target = (EObject) containedElement.eGet(_referencedFeature);
              if (target != null) {
                referencedElements.add(target);
              }
            }
          } else {
            referencedElements.add(containedElement);
          }
        }
        return referencedElements;
      }

      @Override
      protected List<? extends EObject> getAvailableElementsToAdd() {
        List<AbstractState> result = new ArrayList<AbstractState>();
        Component comp = (Component) EcoreUtil2.getFirstContainer(semanticElement, CsPackage.Literals.COMPONENT);
        for (Iterator<EObject> it = comp.eAllContents(); it.hasNext();) {
          EObject next = it.next();
          if (next instanceof AbstractState) {
            result.add((AbstractState) next);
          }
        }
        result.removeAll(getReferencedElementsByContainedOnes());
        return result;
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
    _containmentTableField.loadData(capellaElement, MsPackage.Literals.BOOLEAN_OPERATION__CHILDREN);
  }

  @Override
  public boolean select(Object toTest) {
    EObject obj = CapellaAdapterHelper.resolveEObject(toTest);
    return obj != null && obj.eClass().equals(org.polarsys.capella.vp.ms.MsPackage.eINSTANCE.getBooleanOperation());
  }

  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = super.getSemanticFields();
    fields.add(_containmentTableField);
    return fields;
  }

}
