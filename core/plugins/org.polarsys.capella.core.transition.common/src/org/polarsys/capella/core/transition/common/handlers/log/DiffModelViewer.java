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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffType;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DiffModelViewer implements IDiffModelViewer {

  private static final String TRACEABILITY_1 = "1"; //$NON-NLS-1$

  private static final String TRACEABILITY_N = "N"; //$NON-NLS-1$

  private static final String TRACEABILITY_SEPARATOR = "-"; //$NON-NLS-1$

  private transient IDifference _relatedDiff = null;

  private transient EObject _relatedSemanticElt = null;

  private String _relatedSemanticEltId;

  private String _textDiff = null;

  private String _elementDiff = "";

  private DiffType _typeDiff;

  private DiffScope _scopeDiff;

  private FilterAction _defaultActionDiff;
  private FilterAction _actionDiff;

  private String _detailedDiff = "?";

  String traceability = null;

  boolean isReadOnly = false;

  /**
   * @param isReadOnly 
   * @param diff
   * @param diffScope
   * @param diffAction
   */
  public DiffModelViewer(IDifference diff, DiffScope diffScope, FilterAction diffAction, IContext context, boolean isReadOnly) {
    this._relatedDiff = diff;

    this._scopeDiff = diffScope;
    this._defaultActionDiff = diffAction;
    this._actionDiff = diffAction;
    this.isReadOnly = isReadOnly;

    EObject me = null;
    EObject diffelt = null;

    Role scope = diffScope == DiffScope.Source ? Role.REFERENCE : Role.TARGET;

    // Difference on Reference of element
    if (diff instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) diff;
      this._typeDiff = DiffType.Reference;
      this._detailedDiff = rvp.toString();

      diffelt = rvp.getValue().get(scope);
      if (diffelt != null) {
        me = diffelt;
      }
    }

    // Difference on Presence of new element
    if (diff instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) diff;
      this._typeDiff = DiffType.Element;
      this._detailedDiff = ep.toString();
      diffelt = ep.getElement();
      if (diffelt != null) {
        me = diffelt;
      }
    }

    // Difference on Attribute of an element
    if (diff instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) diff;
      this._typeDiff = DiffType.Attribute;
      this._detailedDiff = avp.toString();
      diffelt = avp.getElementMatch().get(Role.REFERENCE);
      if (diffelt != null) {
        me = diffelt;
      }
    }

    if (diffelt != null) {
      computeElementProperties(diffelt);
      computeTraceability(_relatedDiff, scope, context);
    }

    if (me != null) {
      _relatedSemanticElt = me;
      _relatedSemanticEltId = EcoreUtil.getID(me);
    }

  }

  protected void computeTraceability(IDifference diffelt, Role scope, IContext context) {

    traceability = TRACEABILITY_1 + TRACEABILITY_SEPARATOR + TRACEABILITY_1;

    if (diffelt instanceof IElementRelativeDifference) {
      EObject object = ((IElementRelativeDifference) diffelt).getElementMatch().get(Role.REFERENCE);

      ITraceabilityHandler sourceMergeHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
      ITraceabilityHandler targetMergeHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

      ITraceabilityHandler sourceHandler = sourceMergeHandler;
      ITraceabilityHandler targetHandler = targetMergeHandler;

      if (scope == Role.TARGET) {
        sourceHandler = targetMergeHandler;
        targetHandler = sourceMergeHandler;
      }

      Collection<EObject> targetElements = new HashSet<EObject>();
      Collection<EObject> sourceElements = new HashSet<EObject>();

      Collection<EObject> ancestors = sourceHandler.retrieveSourceElements(object, context);
      for (EObject ancestor : ancestors) {

        Collection<EObject> targets = targetHandler.retrieveTracedElements(ancestor, context);
        targetElements.addAll(targets);

        for (EObject target : targets) {
          Collection<EObject> ancestorTargets = targetHandler.retrieveSourceElements(target, context);

          for (EObject ancestorTarget : ancestorTargets) {
            sourceElements.addAll(sourceHandler.retrieveTracedElements(ancestorTarget, context));
          }
        }
      }

      String sourceCount = sourceElements.size() <= 1 ? TRACEABILITY_1 : TRACEABILITY_N;
      String targetCount = targetElements.size() <= 1 ? TRACEABILITY_1 : TRACEABILITY_N;

      traceability = sourceCount + TRACEABILITY_SEPARATOR + targetCount;

    }
  }

  protected void computeElementProperties(EObject me) {
    _elementDiff = LogHelper.getInstance().getIdentifier(me);

  }

  @Override
  public IDifference getRelatedDiff() {
    return _relatedDiff;
  }

  @Override
  public void setRelatedDiff(IDifference diff) {
    _relatedDiff = diff;
  }

  @Override
  public String getTextDiff() {
    if (_textDiff == null) {
      _textDiff = LogHelper.getInstance().getText(this);
    }
    return _textDiff;
  }

  @Override
  public FilterAction getDefaultActionDiff() {
    return _defaultActionDiff;
  }

  @Override
  public FilterAction getActionDiff() {
    return _actionDiff;
  }

  @Override
  public void setActionDiff(FilterAction diff) {
    _actionDiff = diff;
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();

  }

  @Override
  public String getElementDiff() {
    return _elementDiff;
  }

  @Override
  public void setElementDiff(String diff) {
    _elementDiff = diff;
  }

  @Override
  public Image getImage() {
    return EObjectLabelProviderHelper.getImage(_relatedSemanticElt);
  }

  @Override
  public void setTypeDiff(DiffType diff) {
    _typeDiff = diff;
  }

  @Override
  public DiffType getTypeDiff() {
    return _typeDiff;
  }

  @Override
  public DiffScope getScopeDiff() {
    return _scopeDiff;
  }

  @Override
  public void setScopeDiff(DiffScope diff) {
    _scopeDiff = diff;
  }

  @Override
  public String getDetailedDiff() {
    return _detailedDiff;
  }

  @Override
  public void setDetailedDiff(String diff) {
    _detailedDiff = diff;
  }

  @Override
  public EObject getSemanticElementDiff() {
    return _relatedSemanticElt;
  }

  @Override
  public void setSemanticElementDiff(EObject capellaElt) {
    _relatedSemanticElt = capellaElt;
  }

  @Override
  public String getRelatedSemanticEltId() {
    return _relatedSemanticEltId;
  }

  @Override
  public void setRelatedSemanticEltId(String capellaEltId) {
    _relatedSemanticEltId = capellaEltId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReadOnly() {
    return isReadOnly;
  }

  IDiffModelViewer _root = this;

  /**
   * {@inheritDoc}
   */
  @Override
  public void setRoot(IDiffModelViewer source) {
    _root = source;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IDiffModelViewer getRoot() {
    return _root;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDefaultActionDiff(FilterAction diff) {
    _defaultActionDiff = diff;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTraceability() {
    return traceability;
  }
}
