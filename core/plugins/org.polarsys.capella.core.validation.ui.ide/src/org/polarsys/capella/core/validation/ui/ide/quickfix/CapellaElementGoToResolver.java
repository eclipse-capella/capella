/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;

/**
 * This class can be used to create "Go to" Quick Fixes.
 */
public class CapellaElementGoToResolver implements IMarkerResolution2 {
  /**
   * QF's label pattern.
   */
  private static final String QUICK_FIX_LABEL_PATTERN = "Show {0} {1} ({2})"; //$NON-NLS-1$
  /**
   * QF's image.
   */
  private final Image image;
  /**
   * QF's label.
   */
  private final String label;
  /**
   * Model element to go to.
   */
  private final EObject modelElement;

  /**
   * @param elementToGoToDescription
   * @param modelElement
   */
  public CapellaElementGoToResolver(String elementToGoToDescription, EObject modelElement) {
    // Generate label.
    String modelElementClassName =
        EObjectLabelProviderHelper.getMetaclassLabel(modelElement, false);
    String modelElementName = EObjectLabelProviderHelper.getText(modelElement);
    label =
        MessageFormat.format(QUICK_FIX_LABEL_PATTERN, elementToGoToDescription, modelElementName,
            modelElementClassName);
    // Image.
    image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(modelElement));

    this.modelElement = modelElement;
  }

  /**
   * @param elementToGoToDescription
   * @param modelElement
   */
  public CapellaElementGoToResolver(String elementToGoToDescription, EObject modelElement,
      String modelElementClassName) {
    // Generate label.
    String modelElementName = EObjectLabelProviderHelper.getText(modelElement);
    label = MessageFormat.format(QUICK_FIX_LABEL_PATTERN, elementToGoToDescription, modelElementName,
        modelElementClassName);
    // Image.
    image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(modelElement));

    this.modelElement = modelElement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage() {
    return image;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLabel() {
    return label;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(IMarker marker) {
    LocateInCapellaExplorerAction selectElementAction = new LocateInCapellaExplorerAction();
    selectElementAction.selectionChanged(null, new StructuredSelection(modelElement));
    selectElementAction.shouldIgnoreWorkbenchPartSite(true);
    selectElementAction.run(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return null;
  }
  
  public EObject getModelElement() {
    return modelElement;
  }
}
