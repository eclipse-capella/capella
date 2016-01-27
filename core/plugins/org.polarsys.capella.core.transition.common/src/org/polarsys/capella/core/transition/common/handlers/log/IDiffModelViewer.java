/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

/**
 *
 */
public interface IDiffModelViewer {

  /**
   * @return the _relatedDiff
   */
  IDifference getRelatedDiff();

  /**
   * @param diff the _relatedDiff to set
   */
  void setRelatedDiff(IDifference diff);

  /**
   * @return the _textDiff
   */
  String getTextDiff();

  /**
   * @return the _actionDiff
   */
  FilterAction getActionDiff();

  /**
   * @return the _actionDiff
   */
  FilterAction getDefaultActionDiff();

  void setDefaultActionDiff(FilterAction diff);

  /**
   * @param diff the _actionDiff to set
   */
  void setActionDiff(FilterAction diff);

  /**
   * @return the _typeDiff
   */
  String getElementDiff();

  /**
   * @param diff the _typeDiff to set
   */
  void setElementDiff(String diff);

  /**
   * @return the _image
   */
  Image getImage();

  /**
   * @param diff the _typeDiff to set
   */
  void setTypeDiff(DiffType diff);

  /**
   * @return the _typeDiff
   */
  DiffType getTypeDiff();

  /**
   * @return the _scopeDiff
   */
  DiffScope getScopeDiff();

  /**
   * @param diff the _scopeDiff to set
   */
  void setScopeDiff(DiffScope diff);

  /**
   * @return the _detailedDiff
   */
  String getDetailedDiff();

  /**
   * @param diff the _detailedDiff to set
   */
  void setDetailedDiff(String diff);

  /**
   * @return the _relatedCapellaElt
   */
  EObject getSemanticElementDiff();

  /**
   * @param semanticElt the _relatedCapellaElt to set
   */
  void setSemanticElementDiff(EObject semanticElt);

  /**
   * @return the _relatedCapellaEltId
   */
  String getRelatedSemanticEltId();

  /**
   * @param semanticEltId the _relatedCapellaEltId to set
   */
  void setRelatedSemanticEltId(String semanticEltId);

  boolean isReadOnly();

  /**
   * @param source
   */
  void setRoot(IDiffModelViewer source);

  IDiffModelViewer getRoot();

  /**
   * @return
   */
  String getTraceability();

}
