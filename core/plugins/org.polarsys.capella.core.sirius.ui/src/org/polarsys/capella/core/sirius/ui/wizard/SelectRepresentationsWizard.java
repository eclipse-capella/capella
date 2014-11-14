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
package org.polarsys.capella.core.sirius.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.core.sirius.ui.Messages;

/**
 * This wizard asks the user for which representations he wants to externalize and create the new .aird files.
 */
public class SelectRepresentationsWizard extends Wizard {

  private Collection<DRepresentation> _representations;

  private RepresentationsSelectionWizardPage _selectElementPage;

  private Session _session;

  /**
   * Constructor.
   * @param session_p origin session.
   * @param preselection_p preselected diagrams
   */
  public SelectRepresentationsWizard(Session session_p, Collection<DRepresentation> preselection_p) {
    _session = session_p;
    _representations = preselection_p;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    _representations = _selectElementPage.getSelectedElements();
    return true;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    setWindowTitle(Messages.SelectRepresentationsWizard_Title);
    Collection<String> extensions = new ArrayList<String>();
    extensions.add(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
    _selectElementPage = new RepresentationsSelectionWizardPage(_session, _representations);
    addPage(_selectElementPage);
  }

  /**
   * @return The representation
   */
  public Collection<DRepresentation> getSelectedRepresentations() {
    return _representations;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();
    _selectElementPage.dispose();
  }

}
