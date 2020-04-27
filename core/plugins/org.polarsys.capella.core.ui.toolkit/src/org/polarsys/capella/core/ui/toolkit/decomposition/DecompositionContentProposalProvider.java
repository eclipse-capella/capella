/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/**
 *
 */
public class DecompositionContentProposalProvider implements IContentProposalProvider {
  
  private List<DecompositionComponent> components;
  private List<DecompositionReuseContentProposal> proposals;
  
  public DecompositionContentProposalProvider(List<DecompositionComponent> components) {
    setComponents(components);
    setProposals(new ArrayList<DecompositionReuseContentProposal>(1));
    for(DecompositionComponent comp : components) {
      proposals.add(new DecompositionReuseContentProposal(comp));
    }
  }

  /**
   * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
   */
  public IContentProposal[] getProposals(String contents, int position) {
    return proposals.toArray(new IContentProposal[0]);
  }

  /**
   * @return the components
   */
  public List<DecompositionComponent> getComponents() {
    return components;
  }

  /**
   * @param components the components to set
   */
  public void setComponents(List<DecompositionComponent> components) {
    this.components = components;
  }

  /**
   * @return the proposals
   */
  public List<DecompositionReuseContentProposal> getProposals() {
    return proposals;
  }

  /**
   * @param proposals the proposals to set
   */
  public void setProposals(List<DecompositionReuseContentProposal> proposals) {
    this.proposals = proposals;
  }

}
class DecompositionReuseContentProposal implements IContentProposal {
  private DecompositionComponent component;
  
  public DecompositionReuseContentProposal(DecompositionComponent component) {
    setComponent(component);
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
    return component;
  }

  /**
   * @param component the component to set
   */
  public void setComponent(DecompositionComponent component) {
    this.component = component;
  }
  
}
