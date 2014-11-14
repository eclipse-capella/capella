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
package org.polarsys.capella.common.re.merge.scope;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyListener;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.ReferenceModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReSourceScope extends ReferenceModelScope implements INotifyListener {

  CatalogElement element;
  ITraceabilityHandler handler;

  Collection<? extends EObject> _initialElements;

  protected boolean isSource() {
    return true;
  }

  /**
   * @param elements_p
   * @param context_p
   */
  public ReSourceScope(CatalogElement element_p, ITraceabilityHandler handler_p, Collection<? extends EObject> elements_p, IContext context_p) {
    super(elements_p, context_p);
    element = element_p;
    handler = handler_p;
    _initialElements = elements_p;
  }

  public Collection<? extends EObject> getInitialElements() {
    return _initialElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element_p) {
    System.out.println(NLS.bind("ADD {0}", new String[] { EObjectLabelProviderHelper.getText(element_p) }));

    if (element_p instanceof CatalogElement) {

      if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.UNMERGEABLE_ELEMENTS, element_p, context)) {
        ((CatalogElement) element_p).setKind(CatalogElementKind.RPL);
      }

    }
    addTraceabilityLink(element_p, context);

    return super.add(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event_p, IContext context_p) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element_p, boolean includeChildren_p) {
    System.out.println(NLS.bind("ADD-includeChildren {0}", new String[] { EObjectLabelProviderHelper.getText(element_p) }));
    return super.add(element_p, includeChildren_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    System.out.println(NLS.bind("ADD {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source_p), reference_p.getName(),
                                                                 EObjectLabelProviderHelper.getText(value_p) }));
    if (reference_p.isContainment()) {
      addTraceabilityLink(value_p, context);
    }

    if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(source_p, context)) {
      return false;
    }

    if (reference_p.isContainment()) {
      if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(value_p, context)) {
        return false;
      }
    }

    return super.add(source_p, reference_p, value_p);

  }

  /**
   * @param value_p
   */
  protected void addTraceabilityLink(EObject value_p, IContext context_p) {

    ExtendedComparison comparison = (ExtendedComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
    Role destinationRole = isSource() ? Role.REFERENCE : Role.TARGET;
    Role oppositeRole = !isSource() ? Role.REFERENCE : Role.TARGET;

    String destinationScope = isSource() ? IReConstants.SOURCE__ADDED_ELEMENTS : IReConstants.TARGET__ADDED_ELEMENTS;
    String oppositeScope = !isSource() ? IReConstants.SOURCE__ADDED_ELEMENTS : IReConstants.TARGET__ADDED_ELEMENTS;

    if (shouldAddTraceability(value_p, destinationRole, context)) {

      IMatch match = comparison.getMapping().getMatchFor(value_p, destinationRole);
      EObject oppositeValue = match.get(oppositeRole);

      CatalogElementLink link = ReplicableElementHandlerHelper.getInstance(context).addLink(context, handler, element, value_p, oppositeValue);
      ContextScopeHandlerHelper.getInstance(context).add(destinationScope, link, context);

      if (!isSource()) {
        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        //In case where element is not traced into source replica, we add a link into it
        if (source != null) {
          if (oppositeValue != null) {
            if (shouldAddTraceability(value_p, oppositeRole, context)) {
              link = ReplicableElementHandlerHelper.getInstance(context).addLink(context, handler, source, oppositeValue, value_p);
              ContextScopeHandlerHelper.getInstance(context).add(oppositeScope, link, context);
            }
          }
        }
      }
    }
  }

  /**
   * @param value_p
   * @return
   */
  protected boolean shouldAddTraceability(EObject value_p, Role role_p, IContext context_p) {
    if (ReplicableElementHandlerHelper.getInstance(context_p).isUnmodifiableElement(value_p, context_p)) {
      return false;
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void build(IModelScopeFilter filter_p) {
    super.build(filter_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(EObject element_p) {
    return super.covers(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject element_p) {
    System.out.println(NLS.bind("REMOVE {0}", new String[] { EObjectLabelProviderHelper.getText(element_p) }));
    ReplicableElementHandlerHelper.getInstance(context).addDeletableElement(element_p, context);

    _roots.remove(element_p);
    removeFromScope(element_p);
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFromScope(EObject element_p) {
    System.out.println(NLS.bind("REMOVE-fromScope {0}", new String[] { EObjectLabelProviderHelper.getText(element_p) }));
    super.removeFromScope(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject source_p, EAttribute attribute_p, Object value_p) {
    System.out.println(NLS.bind("ADD {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source_p), attribute_p.getName(),
                                                                 EObjectLabelProviderHelper.getText(value_p) }));

    return super.add(source_p, attribute_p, value_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object move(EObject source_p, EStructuralFeature feature_p, int newPosition_p, int oldPosition_p) {
    return super.move(source_p, feature_p, newPosition_p, oldPosition_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject source_p, EAttribute attribute_p, Object value_p) {
    System.out.println(NLS.bind("REMOVE {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source_p), attribute_p.getName(),
                                                                    EObjectLabelProviderHelper.getText(value_p) }));
    return super.remove(source_p, attribute_p, value_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject source_p, EReference reference_p, EObject value_p) {
    System.out.println(NLS.bind("REMOVE {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source_p), reference_p.getName(),
                                                                    EObjectLabelProviderHelper.getText(value_p) }));

    if (reference_p.isContainment()) {
      ReplicableElementHandlerHelper.getInstance(context).addDeletableElement(value_p, context);
      return true;
    }
    return super.remove(source_p, reference_p, value_p);

  }

}
