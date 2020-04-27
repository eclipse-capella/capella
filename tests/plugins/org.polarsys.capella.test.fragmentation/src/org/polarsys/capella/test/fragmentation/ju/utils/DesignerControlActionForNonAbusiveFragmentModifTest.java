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
package org.polarsys.capella.test.fragmentation.ju.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler;
import org.eclipse.sirius.ui.tools.api.control.SiriusUncontrolHandler;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction;

/**
 * @see DesignerControlAction
 */
public class DesignerControlActionForNonAbusiveFragmentModifTest extends DesignerControlActionForNonAbusiveFragmentModif {

  /** For fragmentation purpose */
  protected List<DRepresentationDescriptor> _dRepresentationsToMove = null;

  /** For unfragmentation purpose */
  protected boolean _shouldUncontrolRepresentations;

  /** Write accessor */
  final public void setDRepresentationDescriptorsToMove(List<DRepresentationDescriptor> dRepresentations_p) {
    _dRepresentationsToMove = dRepresentations_p;
  }

  /** Write accessor */
  final public void setShouldUncontrolRepresentations(boolean value_p) {
    _shouldUncontrolRepresentations = value_p;
  }

  /**
   * @param domain_p
   */
  public DesignerControlActionForNonAbusiveFragmentModifTest() {
    super();
  }

  /**
   * @see org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction#fragment(org.eclipse.swt.widgets.Shell)
   */
  @Override
  protected void fragment(Shell shell__p) {

    SiriusControlHandler siriusControlHandler = new CapellaSiriusControlHandler(shell__p) {
      /**
       * @see org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler#getControledResourceURI(org.eclipse.swt.widgets.Shell,
       *      org.eclipse.emf.ecore.EObject)
       */
      @Override
      protected URI getControledResourceURI(final Shell shell, final EObject semanticRoot) {
        return URI.createURI(getDefaultControlURI(semanticRoot), true);
      }

      /**
       * @see org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction.CapellaSiriusControlHandler#getRepresentationsToMove(org.eclipse.swt.widgets.Shell,
       *      org.eclipse.sirius.business.api.session.Session, org.eclipse.emf.ecore.EObject)
       */
      @Override
      protected Collection<DRepresentationDescriptor> getRepresentationDescriptorsToMove(Shell shell_p, Session session_p, EObject semanticRoot_p) throws InterruptedException {
        return null == _dRepresentationsToMove ? new ArrayList<DRepresentationDescriptor>() : _dRepresentationsToMove;
      }
    };
    siriusControlHandler.performControl(shell__p, _eObject, new NullProgressMonitor());

    return;
  }

  @Override
  protected void unFragment(final Shell shell__p) {

    SiriusUncontrolHandler siriusUncontrolHandler = new CapellaSiriusUncontrolHandler() {

      /**
       * @see org.eclipse.sirius.ui.tools.api.control.SiriusUncontrolHandler#shouldUncontrolRepresentations(org.eclipse.swt.widgets.Shell)
       */
      @Override
      protected boolean shouldUncontrolRepresentations(Shell shell_p) {
        return _shouldUncontrolRepresentations;
      }

    };

    siriusUncontrolHandler.performUncontrol(shell__p, _eObject, new NullProgressMonitor());

    return;
  }
}
