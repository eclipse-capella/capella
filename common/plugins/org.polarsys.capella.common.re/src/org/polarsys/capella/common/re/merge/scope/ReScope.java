/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.merge.scope;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyChangeEvent;
import org.polarsys.capella.core.transition.common.handlers.notify.INotifyListener;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.capella.core.transition.common.merge.scope.ReferenceModelScope;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public abstract class ReScope extends ReferenceModelScope implements INotifyListener {

  CatalogElement element;
  
  Collection<? extends EObject> _initialElements;
  
  public ReScope(CatalogElement element, Collection<? extends EObject> elements, IContext context) {
    super(elements, context);
    this.element = element;
    this._initialElements = elements;
  }

  protected abstract boolean isSource();
  
  public Collection<? extends EObject> getInitialElements() {
    return _initialElements;
  }
  
  /**
   * @param value
   * @return
   */
  protected boolean shouldAddTraceability(EObject value, Role role, IContext context) {
    if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(value, context)) {
      return false;
    }

    return true;
  }
  
  /**
   * @param value
   */
  protected void addTraceabilityLink(EObject value, IContext contextParameter) {

    ExtendedComparison comparison = (ExtendedComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
    Role destinationRole = isSource() ? Role.REFERENCE : Role.TARGET;
    Role oppositeRole = !isSource() ? Role.REFERENCE : Role.TARGET;
    String destinationScope = isSource() ? IReConstants.SOURCE__ADDED_ELEMENTS : IReConstants.TARGET__ADDED_ELEMENTS;
    String oppositeScope = !isSource() ? IReConstants.SOURCE__ADDED_ELEMENTS : IReConstants.TARGET__ADDED_ELEMENTS;

    if (shouldAddTraceability(value, destinationRole, context)) {

      EMatch match = comparison.getMapping().getMatchFor(value, destinationRole);
      EObject oppositeValue = match.get(oppositeRole);

      CatalogElementLink link = ReplicableElementHandlerHelper.getInstance(context).addLink(context, element, value, oppositeValue);
      ContextScopeHandlerHelper.getInstance(context).add(destinationScope, link, context);

      if (!isSource()) {
        CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
        //In case where element is not traced into source replica, we add a link into it
        if (source != null) {
          if (oppositeValue != null) {
            if (shouldAddTraceability(value, oppositeRole, context)) {
              link = ReplicableElementHandlerHelper.getInstance(context).addLink(context, source, oppositeValue, value);
              ContextScopeHandlerHelper.getInstance(context).add(oppositeScope, link, context);
            }
          }
        }
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element) {
    System.out.println(NLS.bind("ADD {0}", new String[] { EObjectLabelProviderHelper.getText(element) }));

    if (element instanceof CatalogElement) {

      if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.UNMERGEABLE_ELEMENTS, element, context)) {
        ((CatalogElement) element).setKind(CatalogElementKind.RPL);
      }

    }
    addTraceabilityLink(element, context);

    return super.add(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element, boolean includeChildren) {
    System.out.println(NLS.bind("ADD-includeChildren {0}", new String[] { EObjectLabelProviderHelper.getText(element) }));
    return super.add(element, includeChildren);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject source, EReference reference, EObject value) {
    System.out.println(NLS.bind("ADD {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source), reference.getName(),
                                                                 EObjectLabelProviderHelper.getText(value) }));
    if (reference.isContainment()) {
      addTraceabilityLink(value, context);
    }

    if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(source, context)) {
      return false;
    }

    if (reference.isContainment()) {
      if (ReplicableElementHandlerHelper.getInstance(context).isUnmodifiableElement(value, context)) {
        return false;
      }
    }

    return super.add(source, reference, value);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject source, EAttribute attribute, Object value) {
    System.out.println(NLS.bind("ADD {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source), attribute.getName(),
                                                                 EObjectLabelProviderHelper.getText(value) }));

    return super.add(source, attribute, value);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void build(IModelScopeFilter filter) {
    super.build(filter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(EObject element) {
    return super.covers(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject element) {
    System.out.println(NLS.bind("REMOVE {0}", new String[] { EObjectLabelProviderHelper.getText(element) }));
    ReplicableElementHandlerHelper.getInstance(context).addDeletableElement(element, context);

    _roots.remove(element);
    removeFromScope(element);
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFromScope(EObject element) {
    System.out.println(NLS.bind("REMOVE-fromScope {0}", new String[] { EObjectLabelProviderHelper.getText(element) }));
    super.removeFromScope(element);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Object move(EObject source, EStructuralFeature feature, int newPosition, int oldPosition) {
    return super.move(source, feature, newPosition, oldPosition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject source, EAttribute attribute, Object value) {
    System.out.println(NLS.bind("REMOVE {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source), attribute.getName(),
                                                                    EObjectLabelProviderHelper.getText(value) }));
    return super.remove(source, attribute, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(EObject source, EReference reference, EObject value) {
    System.out.println(NLS.bind("REMOVE {0} {1} {2}", new String[] { EObjectLabelProviderHelper.getText(source), reference.getName(),
                                                                    EObjectLabelProviderHelper.getText(value) }));

    if (reference.isContainment()) {
      ReplicableElementHandlerHelper.getInstance(context).addDeletableElement(value, context);
      return true;
    }
    return super.remove(source, reference, value);

  }

  @Override
  protected List<EObject> retains(List<EObject> object) {
    List<EObject> result = super.retains(object);
    result.removeAll(ReplicableElementHandlerHelper.getInstance(context).getDeletableElements(context));
    return result;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void notifyChanged(INotifyChangeEvent event, IContext context) {

  }
}
