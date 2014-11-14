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
package org.polarsys.capella.core.transition.common.handlers.log;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffType;

public interface IDiffModelViewer {

  /**
   * @return the _relatedDiff
   */
  public IDifference getRelatedDiff();

  /**
   * @param diff the _relatedDiff to set
   */
  public void setRelatedDiff(IDifference diff);

  /**
   * @return the _textDiff
   */
  public String getTextDiff();

  /**
   * @return the _actionDiff
   */
  public FilterAction getActionDiff();

  /**
   * @return the _actionDiff
   */
  public FilterAction getDefaultActionDiff();

  public void setDefaultActionDiff(FilterAction diff);

  /**
   * @param diff the _actionDiff to set
   */
  public void setActionDiff(FilterAction diff);

  /**
   * @return the _typeDiff
   */
  public String getElementDiff();

  /**
   * @param diff the _typeDiff to set
   */
  public void setElementDiff(String diff);

  /**
   * @return the _image
   */
  public Image getImage();

  /**
   * @param diff the _typeDiff to set
   */
  public void setTypeDiff(DiffType diff);

  /**
   * @return the _typeDiff
   */
  public DiffType getTypeDiff();

  /**
   * @return the _scopeDiff
   */
  public DiffScope getScopeDiff();

  /**
   * @param diff the _scopeDiff to set
   */
  public void setScopeDiff(DiffScope diff);

  /**
   * @return the _detailedDiff
   */
  public String getDetailedDiff();

  /**
   * @param diff the _detailedDiff to set
   */
  public void setDetailedDiff(String diff);

  /**
   * @return the _relatedCapellaElt
   */
  public EObject getSemanticElementDiff();

  /**
   * @param semanticElt the _relatedCapellaElt to set
   */
  public void setSemanticElementDiff(EObject semanticElt);

  /**
   * @return the _relatedCapellaEltId
   */
  public String getRelatedSemanticEltId();

  /**
   * @param semanticEltId the _relatedCapellaEltId to set
   */
  public void setRelatedSemanticEltId(String semanticEltId);

  public boolean isReadOnly();

  /**
   * @param source_p
   */
  public void setRoot(IDiffModelViewer source_p);

  public IDiffModelViewer getRoot();

  /**
   * @return
   */
  public String getTraceability();

}
