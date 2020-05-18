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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.ShowInDiagramAction;

/**
 */
public class OpenAndShowInDiagramResolver implements IMarkerResolution2 {
  /**
   * QF's label pattern.
   */
  private static final String QUICK_FIX_LABEL_PATTERN = "Open and show in \"{0}\" ({1})"; //$NON-NLS-1$
  /**
   * QF's image.
   */
  private final Image image;
  /**
   * QF's label.
   */
  private final String label;
  /**
   * Element to select once the representation is open.
   */
  private final EObject modelElementToSelectInDiagram;
  /**
   * Session.
   */
  private final Session session;
  /**
   * Representation to open.
   */
  private final DRepresentation targetingRepresentation;

  /**
   * Constructor.
   * @param modelElementDiagramTarget model element which is associated to the diagram to show.
   * @param modelElementToSelectInDiagram model element to select in the diagram.
   */
  public OpenAndShowInDiagramResolver(EObject modelElementDiagramTarget, EObject modelElementToSelectInDiagram) {
    // Get session given target ModelElement.
    session = SessionManager.INSTANCE.getSession(modelElementDiagramTarget);
    // Find representations associated to target ModelElement.
    DRepresentation target = null;
    if (null != session) {
      Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(modelElementDiagramTarget, session);
      if (!representations.isEmpty()) {
        // Get the first found representation.
        target = representations.iterator().next();
      }
    } else {
      target = null;
    }
    targetingRepresentation = target;
    // Generate QF's label.
    String representationName = EObjectLabelProviderHelper.getText(targetingRepresentation);
    String representationClassName = EObjectLabelProviderHelper.getMetaclassLabel(targetingRepresentation, false);
    label = MessageFormat.format(QUICK_FIX_LABEL_PATTERN, representationName, representationClassName);

    image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(targetingRepresentation));
    this.modelElementToSelectInDiagram = modelElementToSelectInDiagram;
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
    // Precondition.
    if ((null == session) || (null == targetingRepresentation)) {
      // Can't open representation.
      return;
    }
    DialectUIManager.INSTANCE.openEditor(session, targetingRepresentation, new NullProgressMonitor());
    // Precondition.
    if (null == modelElementToSelectInDiagram) {
      // Can't show element in representation.
      return;
    }
    ShowInDiagramAction showInDiagramAction = new ShowInDiagramAction();
    showInDiagramAction.selectionChanged(new StructuredSelection(modelElementToSelectInDiagram));
    showInDiagramAction.run();
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return null;
  }
}
