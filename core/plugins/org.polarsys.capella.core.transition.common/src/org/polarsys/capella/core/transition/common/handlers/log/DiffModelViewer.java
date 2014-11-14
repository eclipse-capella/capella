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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
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

public class DiffModelViewer implements Serializable, IDiffModelViewer {
  /**
    * 
    */
  private static final long serialVersionUID = 3257866368548591539L;

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

  boolean isMultiTraceability = false;

  boolean isReadOnly = false;

  /**
   * @param isReadOnly_p 
   * @param diff
   * @param diffScope
   * @param diffAction
   */
  public DiffModelViewer(IDifference diff_p, DiffScope diffScope_p, FilterAction diffAction_p, IContext context_p, boolean isReadOnly_p) {
    this._relatedDiff = diff_p;

    this._scopeDiff = diffScope_p;
    this._defaultActionDiff = diffAction_p;
    this._actionDiff = diffAction_p;
    this.isReadOnly = isReadOnly_p;

    EObject me = null;
    EObject diffelt = null;

    Role scope = diffScope_p == DiffScope.Source ? Role.REFERENCE : Role.TARGET;

    // Difference on Reference of element
    if (diff_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) diff_p;
      this._typeDiff = DiffType.Reference;
      this._detailedDiff = rvp.toString();

      diffelt = rvp.getValue().get(scope);
      if (diffelt != null) {
        me = diffelt;
      }
    }

    // Difference on Presence of new element
    if (diff_p instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) diff_p;
      this._typeDiff = DiffType.Element;
      this._detailedDiff = ep.toString();
      diffelt = ep.getElement();
      if (diffelt != null) {
        me = diffelt;
      }
    }

    // Difference on Attribute of an element
    if (diff_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) diff_p;
      this._typeDiff = DiffType.Attribute;
      this._detailedDiff = avp.toString();
      diffelt = avp.getElementMatch().get(Role.REFERENCE);
      if (diffelt != null) {
        me = diffelt;
      }
    }

    if (diffelt != null) {
      computeElementProperties(diffelt);
      computeTraceability(diffelt, scope, context_p);

    }

    if (me != null) {
      _relatedSemanticElt = me;
      _relatedSemanticEltId = EcoreUtil.getID(me);
    }

  }

  /**
   * @param diffelt_p
   */
  protected void computeTraceability(EObject diffelt_p, Role scope, IContext context_p) {

    ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
    ITraceabilityHandler targetHandler = (ITraceabilityHandler) context_p.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

    ITraceabilityHandler handler = sourceHandler;
    if (scope == Role.TARGET) {
      handler = targetHandler;
    }

    int nb = 0;
    Collection<EObject> sources = handler.retrieveSourceElements(diffelt_p, context_p);
    for (EObject source : sources) {
      nb += targetHandler.retrieveTracedElements(source, context_p).size();
    }

    isMultiTraceability = nb > 1;

  }

  /**
   * @param me
   */
  protected void computeElementProperties(EObject me) {
    _elementDiff = LogHelper.getInstance().getIdentifier(me);

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getRelatedDiff()
   */
  public IDifference getRelatedDiff() {
    return _relatedDiff;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setRelatedDiff(org.polarsys.capella.common.consonance.data.diff.IDifference)
   */
  public void setRelatedDiff(IDifference diff) {
    _relatedDiff = diff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getTextDiff()
   */
  public String getTextDiff() {
    if (_textDiff == null) {
      _textDiff = LogHelper.getInstance().getText(this);
    }
    return _textDiff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getActionDiff()
   */
  public FilterAction getDefaultActionDiff() {
    return _defaultActionDiff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getActionDiff()
   */
  public FilterAction getActionDiff() {
    return _actionDiff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setActionDiff(int)
   */
  public void setActionDiff(FilterAction diff) {
    _actionDiff = diff;
  }

  /**
   * @param out
   * @throws IOException
   */
  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
  }

  /**
   * @param in
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();

  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getElementDiff()
   */
  public String getElementDiff() {
    return _elementDiff;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setElementDiff(org.polarsys.capella.core.transition.common.ui.viewer.model
   * .DiffModelViewer.DiffElement)
   */
  public void setElementDiff(String diff) {
    _elementDiff = diff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getImage()
   */
  public Image getImage() {
    return EObjectLabelProviderHelper.getImage(_relatedSemanticElt);
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setTypeDiff(org.polarsys.capella.core.transition.common.ui.viewer.model.
   * DiffModelViewer.DiffType)
   */
  public void setTypeDiff(DiffType diff) {
    _typeDiff = diff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getTypeDiff()
   */
  public DiffType getTypeDiff() {
    return _typeDiff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getScopeDiff()
   */
  public DiffScope getScopeDiff() {
    return _scopeDiff;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setScopeDiff(org.polarsys.capella.core.transition.common.ui.viewer.model
   * .DiffModelViewer.DiffScope)
   */
  public void setScopeDiff(DiffScope diff) {
    _scopeDiff = diff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getDetailedDiff()
   */
  public String getDetailedDiff() {
    return _detailedDiff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setDetailedDiff(java.lang.String)
   */
  public void setDetailedDiff(String diff) {
    _detailedDiff = diff;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getCapellaElementDiff()
   */
  public EObject getSemanticElementDiff() {
    return _relatedSemanticElt;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setCapellaElementDiff(org.polarsys.capella.core.data.capellacore.CapellaElement
   * )
   */
  public void setSemanticElementDiff(EObject capellaElt) {
    _relatedSemanticElt = capellaElt;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#getRelatedCapellaEltId()
   */
  public String getRelatedSemanticEltId() {
    return _relatedSemanticEltId;
  }

  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.core.transition.common.ui.viewer.model.IDiffModelViewer#setRelatedCapellaEltId(java.lang.String)
   */
  public void setRelatedSemanticEltId(String capellaEltId) {
    _relatedSemanticEltId = capellaEltId;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isReadOnly() {
    return isReadOnly;
  }

  IDiffModelViewer _root = this;

  /**
   * {@inheritDoc}
   */
  public void setRoot(IDiffModelViewer source_p) {
    _root = source_p;
  }

  /**
   * {@inheritDoc}
   */
  public IDiffModelViewer getRoot() {
    return _root;
  }

  /**
   * {@inheritDoc}
   */
  public void setDefaultActionDiff(FilterAction diff_p) {
    _defaultActionDiff = diff_p;

  }

  /**
   * {@inheritDoc}
   */
  public String getTraceability() {
    if (isMultiTraceability) {
      return "1-N";
    }
    return "1-1";
  }
}
