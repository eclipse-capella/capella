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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
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
  private final Image _image;
  /**
   * QF's label.
   */
  private final String _label;
  /**
   * Element to select once the representation is open.
   */
  private final EObject _modelElementToSelectInDiagram;
  /**
   * Session.
   */
  private final Session _session;
  /**
   * Representation to open.
   */
  private final DRepresentation _targetingRepresentation;

  /**
   * Constructor.
   * @param modelElementDiagramTarget_p model element which is associated to the diagram to show.
   * @param modelElementToSelectInDiagram_p model element to select in the diagram.
   */
  public OpenAndShowInDiagramResolver(EObject modelElementDiagramTarget_p,
      EObject modelElementToSelectInDiagram_p) {
    // Get session given target ModelElement.
    _session = SessionManager.INSTANCE.getSession(modelElementDiagramTarget_p);
    // Find representations associated to target ModelElement.
    DRepresentation targetingRepresentation = null;
    if (null != _session) {
      Collection<DRepresentation> representations =
          DialectManager.INSTANCE.getRepresentations(modelElementDiagramTarget_p, _session);
      if (!representations.isEmpty()) {
        // Get the first found representation.
        targetingRepresentation = representations.iterator().next();
      }
    } else {
      targetingRepresentation = null;
    }
    _targetingRepresentation = targetingRepresentation;
    // Generate QF's label.
    String representationName = EObjectLabelProviderHelper.getText(_targetingRepresentation);
    String representationClassName =
        EObjectLabelProviderHelper.getMetaclassLabel(_targetingRepresentation, false);
    _label =
        MessageFormat.format(QUICK_FIX_LABEL_PATTERN, representationName, representationClassName);

    _image = EObjectLabelProviderHelper.getImage(_targetingRepresentation);
    _modelElementToSelectInDiagram = modelElementToSelectInDiagram_p;
  }

  /**
   * {@inheritDoc}
   */
  public Image getImage() {
    return _image;
  }

  /**
   * {@inheritDoc}
   */
  public String getLabel() {
    return _label;

  }

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    // Precondition.
    if ((null == _session) || (null == _targetingRepresentation)) {
      // Can't open representation.
      return;
    }
    DialectUIManager.INSTANCE.openEditor(_session, _targetingRepresentation);
    // Precondition.
    if (null == _modelElementToSelectInDiagram) {
      // Can't show element in representation.
      return;
    }
    ShowInDiagramAction showInDiagramAction = new ShowInDiagramAction();
    showInDiagramAction.selectionChanged(new StructuredSelection(_modelElementToSelectInDiagram));
    showInDiagramAction.run();
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return null;
  }
}
