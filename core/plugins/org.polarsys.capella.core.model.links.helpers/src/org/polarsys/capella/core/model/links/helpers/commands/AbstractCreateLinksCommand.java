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

  public AbstractCreateLinksCommand(String label_p, LinkStyle linkStyle_p) {
    super(label_p);
    _linkStyle = linkStyle_p;
  }

  /**
   * @return the sources
   */
  public Collection<? extends ModelElement> getSources() {
    return _sources;
  }

  /**
   * @param sources_p the sources to set
   */
  public void setSources(Collection<? extends ModelElement> sources_p) {
    _sources = sources_p;
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
   * @param targets_p the targets to set
   */
  public void setTargets(Collection<? extends ModelElement> targets_p) {
    _targets = targets_p;
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
