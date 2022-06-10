/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.diffmerge.sirius.SiriusImageHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A model scope covering target model scope.
 */
public class TargetModelScope extends ContextModelScope implements ITargetModelScope, ITargetModelScope.Edit {

    /**
     * This helper contains the required adaptations of the get, add and remove methods to handle image file paths
     * renaming
     */
    private SiriusImageHelper siriusImageHelper;

    public TargetModelScope(List<? extends EObject> elements, IContext context) {
        super(elements, context);
        initializeSiriusImageHelper(context, true);
    }
    
    /**
     * Handles the initialization of the adaptation for image file paths renaming.
     * 
     * @param context
     *            The context of the transition
     * @param active
     *            Whether or not the code adaptation is active
     */
    protected void initializeSiriusImageHelper(IContext context, boolean active) {
        Resource scopeResource = (Resource) context.get(ITransitionConstants.TRANSITION_TARGET_RESOURCE);
        siriusImageHelper = new TransitionSiriusImageHelper(context, scopeResource, active);
    }

    protected boolean dirty;

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public boolean add(EObject element) {
        setDirty(true);
        return super.add(element);
    }

    @Override
    public boolean add(EObject element, boolean includeChildren) {
        setDirty(true);
        return super.add(element, includeChildren);
    }

    @Override
    public boolean add(EObject source, EReference reference, EObject value) {
        setDirty(true);
        return super.add(source, reference, value);
    }

    /**
     * @see org.eclipse.emf.diffmerge.impl.scopes.AbstractEditableModelScope#get(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EAttribute)
     */
    @Override
    public List<Object> get(EObject source, EAttribute attribute) {
        List<Object> objects = super.get(source, attribute);
        objects = siriusImageHelper.adaptGetValue(source, attribute, objects);
        return objects;
    }

    @Override
    public boolean remove(EObject element) {
        setDirty(true);
        return super.remove(element);
    }

    @Override
    public void removeFromScope(EObject element) {
        dirty = true;
        super.removeFromScope(element);
    }

    @Override
    public boolean add(EObject source, EAttribute attribute, Object value) {
        setDirty(true);
        Object newValue = siriusImageHelper.adaptAddValue(source, attribute, value);
        return super.add(source, attribute, newValue);
    }

    @Override
    public Object move(EObject source, EStructuralFeature feature, int newPosition, int oldPosition) {
        setDirty(true);
        return super.move(source, feature, newPosition, oldPosition);
    }

    @Override
    public boolean remove(EObject source, EAttribute attribute, Object value) {
        setDirty(true);
        Object newValue = siriusImageHelper.adaptRemoveValue(source, attribute, value);
        return super.remove(source, attribute, newValue);
    }

    @Override
    public boolean remove(EObject source, EReference reference, EObject value) {
        setDirty(true);
        return super.remove(source, reference, value);
    }

    @Override
    protected boolean removeValue(EObject source, EStructuralFeature feature, Object value) {
        setDirty(true);
        return super.removeValue(source, feature, value);
    }

    @Override
    public Object getOriginator() {
        return "Resulting model";
    }

    public Collection<EObject> retrieveTransformedElementsFromTarget(EObject targetElement) {

        ITraceabilityHandler sourceHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_SOURCE_MERGE_HANDLER);
        ITraceabilityHandler targetHandler = (ITraceabilityHandler) context.get(ITransitionConstants.TRACEABILITY_TARGET_MERGE_HANDLER);

        Collection<EObject> bounds = targetHandler.retrieveSourceElements(targetElement, context);

        if (!bounds.isEmpty()) {
            Iterator<EObject> objects = bounds.iterator();
            if (objects.hasNext()) {
                // Retrieve a transformed element which is linked to all sourceElements.
                EObject object = objects.next();
                Collection<EObject> retains = sourceHandler.retrieveTracedElements(object, context);
                while (objects.hasNext()) {
                    object = objects.next();
                    retains.retainAll(sourceHandler.retrieveTracedElements(object, context));
                }
                return retains;
            }
        }

        return Collections.emptyList();

    }

    @Override
    protected List<EObject> retains(List<EObject> object) {
        // If object is contained into resource of scope, but not in the scope we need to exclude it.
        // Otherwise, if it is contained into an other resource, we can add it. (this can happen using the traceability
        // link)
        EObject sourceRoot = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);

        for (EObject obj : new ArrayList<EObject>(object)) {
            if (!_inScope.contains(obj)) {
                if (!(EcoreUtil2.isOrIsContainedBy(obj, sourceRoot))) {
                    object.remove(obj);
                }
            }
        }

        return object;
    }

}
