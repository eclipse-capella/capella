/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.links.helpers.commands;

import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.model.links.helpers.LinkInfo;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public abstract class AbstractCreateLinksCommand extends AbstractCommand {
  protected Collection<? extends ModelElement> _sources;

  protected Collection<? extends ModelElement> _targets;

  protected final LinkStyle _linkStyle;

  public AbstractCreateLinksCommand(String label, LinkStyle linkStyle) {
    super(label);
    _linkStyle = linkStyle;
  }

  /**
   * @return the sources
   */
  public Collection<? extends ModelElement> getSources() {
    return _sources;
  }

  /**
   * @param sources the sources to set
   */
  public void setSources(Collection<? extends ModelElement> sources) {
    _sources = sources;
  }

  /**
   * @return the targets
   */
  public Collection<? extends ModelElement> getTargets() {
    return _targets;
  }

  public ModelElement getSource() {
    return _sources.iterator().next();
  }

  public ModelElement getTarget() {
    return _targets.iterator().next();
  }

  /**
   * @param targets the targets to set
   */
  public void setTargets(Collection<? extends ModelElement> targets) {
    _targets = targets;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void redo() {
  }

  public abstract EObject getCreatedLinkObject();

  public LinkInfo getLinkRepresentation() {
    return new LinkInfo(getSource(), getTarget(), _linkStyle);
  }
}
