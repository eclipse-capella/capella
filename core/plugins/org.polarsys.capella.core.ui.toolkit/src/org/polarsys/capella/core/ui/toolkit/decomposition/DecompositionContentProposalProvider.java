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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/**
 *
 */
public class DecompositionContentProposalProvider implements IContentProposalProvider {
  
  private List<DecompositionComponent> _components;
  private List<DecompositionReuseContentProposal> _proposals;
  
  public DecompositionContentProposalProvider(List<DecompositionComponent> components_p) {
    setComponents(components_p);
    setProposals(new ArrayList<DecompositionReuseContentProposal>(1));
    for(DecompositionComponent comp : _components) {
      _proposals.add(new DecompositionReuseContentProposal(comp));
    }
  }

  /**
   * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
   */
  public IContentProposal[] getProposals(String contents_p, int position_p) {
    return _proposals.toArray(new IContentProposal[0]);
  }

  /**
   * @return the components
   */
  public List<DecompositionComponent> getComponents() {
    return _components;
  }

  /**
   * @param components_p the components to set
   */
  public void setComponents(List<DecompositionComponent> components_p) {
    _components = components_p;
  }

  /**
   * @return the proposals
   */
  public List<DecompositionReuseContentProposal> getProposals() {
    return _proposals;
  }

  /**
   * @param proposals_p the proposals to set
   */
  public void setProposals(List<DecompositionReuseContentProposal> proposals_p) {
    _proposals = proposals_p;
  }

}
class DecompositionReuseContentProposal implements IContentProposal {
  private DecompositionComponent _component;
  
  public DecompositionReuseContentProposal(DecompositionComponent component_p) {
    setComponent(component_p);
  }
  /**
   * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
   */
  public String getContent() {
    return getComponent().getName();
  }

  /**
   * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
   */
  public int getCursorPosition() {
    return getContent().length();
  }

  /**
   * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
   */
  public String getDescription() {
    return null;
  }

  /**
   * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
   */
  public String getLabel() {
    return null;
  }

  /**
   * @return the component
   */
  public DecompositionComponent getComponent() {
    return _component;
  }

  /**
   * @param component_p the component to set
   */
  public void setComponent(DecompositionComponent component_p) {
    _component = component_p;
  }
  
}
