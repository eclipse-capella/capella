/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.richtext.RichtextManager;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.richtext.fields.FallbackDescriptionGroup;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

public abstract class DescriptionPropertySection extends AbstractSection {

  protected CapellaElementDescriptionGroup descriptionGroup;

  /**
   * In case Richtext is disabled, we replace Richtext widget by this text group.
   */
  protected FallbackDescriptionGroup descriptionFallbackGroup;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);
    createDescriptionWidget(getWidgetFactory(), parent);
  }

  @Override
  protected int getColumnCount() {
    return 1;
  }

  @Override
  public boolean shouldUseExtraSpace() {
    return true;
  }

  /**
   * Create description widget.
   * 
   * @param widgetFactory
   * @param textGroup
   */
  protected void createDescriptionWidget(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent) {
    if (RichtextManager.getInstance().isRichTextEnabled()) {
      descriptionGroup = new CapellaElementDescriptionGroup(parent, widgetFactory, this);
    } else {
      descriptionFallbackGroup = new FallbackDescriptionGroup(parent, "", widgetFactory, true); //$NON-NLS-1$
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
  }

  @Override
  protected EObject adaptElement(EObject object) {
    // We want to add description on descriptors, so we don't adapt it to its semantic element
    if (object instanceof DRepresentationDescriptor) {
      return object;
    }
    return super.adaptElement(object);
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
  public void setEnabled(boolean enabled) {
    // Disable the editor when someone else is editing the same feature
    if (!enabled && null != descriptionGroup) {
      descriptionGroup.setEnabled(enabled);
    }
  }

  @Override
  protected void setInitialEnabledState(boolean enabled) {
    if (null != descriptionGroup) {
      if (enabled) {
        // Due to a richtext bug, we must disable and re-enable to activate the richtext widget
        descriptionGroup.setEnabled(false);
        descriptionGroup.setEnabled(true);
      } else {
        descriptionGroup.setEnabled(enabled);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    if (descriptionFallbackGroup != null) {
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
