/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.actions.SelectElementAction;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;

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
    image = EObjectImageProviderHelper.getImage(modelElement);

    this.modelElement = modelElement;
  }

  /**
   * {@inheritDoc}
   */
  public Image getImage() {
    return image;
  }

  /**
   * {@inheritDoc}
   */
  public String getLabel() {
    return label;
  }

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker) {
    SelectElementAction selectElementAction = new SelectElementAction(modelElement);
    selectElementAction.run();
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return null;
  }
}
