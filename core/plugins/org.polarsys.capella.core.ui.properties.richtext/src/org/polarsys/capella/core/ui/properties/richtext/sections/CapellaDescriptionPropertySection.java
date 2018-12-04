/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;
import org.polarsys.capella.core.ui.properties.richtext.RichtextManager;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * @author Joao Barata
 */
public class CapellaDescriptionPropertySection extends AbstractSection {
    /**
     * Because of the description property section is used in both the view properties and the wizard dialog (when user double clicks on an capella element).
     * We use here a static map to keep track of the instances of this class.
     * The idea is: when a section in a wizard is disposed, the current opening section in the view properties could be notified to be refresh. (This is the bug we have with richtext editor)
     */
    private static Map<CapellaDescriptionPropertySection, EObject> mapDescriptionSectionToEObject = new HashMap<>();
    /**
     *
     */
    protected CapellaElementDescriptionGroup descriptionGroup;
    
    /**
     * In case Richtext is disabled, we replace Richtext widget by this text group.
     */
    private TextAreaValueGroup descriptionFallbackGroup;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);

        rootParentComposite.setLayout(new GridLayout());
        rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        if(RichtextManager.getInstance().isRichTextEnabled()) {
            descriptionGroup = new CapellaElementDescriptionGroup(rootParentComposite, (aTabbedPropertySheetPage != null) ? getWidgetFactory() : null, this);            
        }else {
            descriptionFallbackGroup = new TextAreaValueGroup(rootParentComposite, "", getWidgetFactory(), true);
            descriptionFallbackGroup.setDisplayedInWizard(isDisplayedInWizard());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        
        if (null != descriptionGroup) {
            descriptionGroup.dispose();
            descriptionGroup = null;
        }
        
        // On disposing, remove the instance from the map
        mapDescriptionSectionToEObject.remove(this);
        // If the disposing section is displayed in the wizard, then notify the section (if there is) in the view properties to be refreshed.
        if (isDisplayedInWizard()) {
          Set<CapellaDescriptionPropertySection> availableDescriptionSections = mapDescriptionSectionToEObject.keySet();
          for (CapellaDescriptionPropertySection descriptionSection : availableDescriptionSections) {
            if (descriptionSection != null && !descriptionSection.isDisplayedInWizard()) {
              descriptionSection.refresh();
              descriptionSection.aboutToBeShown();
            }
          }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleParentBackground(Color color, Composite parent) {
        // Do nothing.
    }
    
    @Override
    public void aboutToBeHidden() {
      if (descriptionGroup != null)
        descriptionGroup.aboutToBeHidden();
      super.aboutToBeHidden();
    }
    
    @Override
    public void aboutToBeShown() {
      if (descriptionGroup != null)
        descriptionGroup.aboutToBeShown();
      super.aboutToBeShown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadData(EObject capellaElement) {
        super.loadData(capellaElement);
        // On loading data, add the instance to the map.
        mapDescriptionSectionToEObject.put(this, capellaElement);
        if (null != descriptionGroup) {
            descriptionGroup.loadData(capellaElement);
        }else if(descriptionFallbackGroup != null) {
            descriptionFallbackGroup.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_Description());
        }
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject elt = CapellaAdapterHelper.resolveSemanticObject(((StructuredSelection) selection).getFirstElement());
      if (elt instanceof CapellaElement) {
        if (elt.eClass().equals(CsPackage.eINSTANCE.getPart())) {
          boolean allowMultiplePart = TriStateBoolean.True
              .equals(CapellaProjectHelper.isReusableComponentsDriven((Part) elt));
          if (!allowMultiplePart) {
            AbstractType type = ((Part) elt).getAbstractType();
            if ((type != null) && !(type instanceof ConfigurationItem)) {
              super.setInput(part, new StructuredSelection(type));
              loadData((CapellaElement) type);
              return;
            }
          }
        }
        loadData((CapellaElement) elt);
      }
    }
    super.setInput(part, selection);
  }

    /**
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    @Override
    public boolean select(Object toTest) {
        EObject eObj = CapellaAdapterHelper.resolveSemanticObject(toTest);
        return eObj instanceof CapellaElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (null != descriptionGroup) {
            descriptionGroup.setEnabled(enabled);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AbstractSemanticField> getSemanticFields() {
        if(descriptionFallbackGroup != null) {
            return Collections.singletonList(descriptionFallbackGroup);
        }
        return Collections.emptyList();
    }

    @Override
    public void refresh() {
        if (shouldRefresh()) {
            super.refresh();
        }
    }

    public boolean shouldRefresh() {
        return descriptionGroup == null || descriptionGroup.shouldRefresh();
    }
    
    @Override
    public void performFinish() {
      descriptionGroup.save();
    }
}
