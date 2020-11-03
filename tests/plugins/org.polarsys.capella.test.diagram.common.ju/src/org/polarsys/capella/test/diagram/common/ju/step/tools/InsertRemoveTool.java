/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.AbstractExternalJavaAction;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class InsertRemoveTool extends AbstractToolStep {

  protected boolean initialized = false;

  protected boolean insertAll = false;

  protected boolean removeAll = false;

  protected boolean autoRefresh = true;

  protected String containerId;
  protected String[] toInsert;
  protected String[] toRemove;
  protected String[] insertedElements;
  protected String[] removedElements;
  protected EReference[] insertedReferencedElementsFeatures;
  protected EReference[] removedReferencedElementsFeatures;
  protected EReference[] insertedReferencingElementsFeatures;
  protected EReference[] removedReferencingElementsFeatures;

  public InsertRemoveTool(DiagramContext context, String toolName) {
    this(context, toolName, context.getDiagramId());
  }

  public InsertRemoveTool(DiagramContext context, String[] toolIdentifier) {
    this(context, toolIdentifier, context.getDiagramId());
  }

  public InsertRemoveTool(DiagramContext context, String toolName, String containerId) {
    super(context, toolName);
    this.containerId = containerId;
  }

  public InsertRemoveTool(DiagramContext context, String[] toolIdentifier, String containerId) {
    super(context, toolIdentifier[0], toolIdentifier[1]);
    this.containerId = containerId;
  }

  public InsertRemoveTool(DiagramContext context, String toolName, String containerId, boolean autoRefresh) {
    this(context, toolName, containerId);
    this.autoRefresh = autoRefresh;
  }

  protected void initialize(boolean insertAll, boolean removeAll) {
    this.insertAll = insertAll;
    this.removeAll = removeAll;
    initialize(null, null, null, null);
  }

  protected void initialize(String[] toInsert, String[] toRemove, String[] insertedElements, String[] removedElements) {
    this.toInsert = toInsert;
    if (toInsert == null) {
      this.toInsert = new String[0];
    }
    this.toRemove = toRemove;
    if (toRemove == null) {
      this.toRemove = new String[0];
    }
    this.insertedElements = insertedElements;
    if (insertedElements == null) {
      this.insertedElements = new String[0];
    }
    this.removedElements = removedElements;
    if (removedElements == null) {
      this.removedElements = new String[0];
    }
    initialized = true;
  }

  @Override
  public Object run() {
    if (!initialized) {
      fail("Please use insert/remove methods instead of run.");
    }
    return super.run();
  }

  public void insertAll() {
    initialize(true, false);
    run();
  }

  public void insert(String... toInsert) {
    initialize(toInsert, null, toInsert, null);
    run();
  }

  public void insert(String[] toInsert, String[] insertedElements, String[] removedElements) {
    initialize(toInsert, null, insertedElements, removedElements);
    run();
  }

  public void remove(String... toRemove) {
    initialize(null, toRemove, null, toRemove);
    run();
  }

  public void remove(String[] toRemove, String[] insertedElements, String[] removedElements) {
    initialize(null, toRemove, insertedElements, removedElements);
    run();
  }

  public void insertRemove(String[] toInsert, String[] toRemove, String[] insertedElements, String[] removedElements) {
    initialize(null, toRemove, insertedElements, removedElements);
    run();
  }

  protected void checkPreconditions() {
    for (String identifier : insertedElements) {
      getDiagramContext().hasntView(identifier);
    }
    for (String identifier : removedElements) {
      getDiagramContext().hasView(identifier);
    }
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(createOperation());
    checkPreconditions();
    super.preRunTest();
  }

  /**
   * @return
   */
  protected IHeadlessResult createOperation() {
    return new IHeadlessResult() {

      @Override
      @SuppressWarnings({ "unchecked", "synthetic-access", "rawtypes" })
      public Object getResult(java.util.Collection<? extends EObject> selections, Map<String, Object> parameters) {
        if (insertAll) {
          return AbstractExternalJavaAction.getScope(parameters);
        } else if (removeAll) {
          return Collections.emptyList();
        }

        Collection<EObject> objects = new HashSet<EObject>();
        DiagramContext context = getDiagramContext();
        SessionContext sessionContext = context.getSessionContext();

        Collection<EObject> inserted = context.adaptTool(InsertRemoveTool.this, parameters,
            sessionContext.getSemanticElements(insertedElements));
        Collection<EObject> removed = context.adaptTool(InsertRemoveTool.this, parameters,
            sessionContext.getSemanticElements(removedElements));
        
        Collection<EObject> initialSelection = AbstractExternalJavaAction.getInitialSelection(parameters);
        objects.addAll(initialSelection);
        objects.addAll(inserted);
        objects.removeAll(removed);
        
        if (removed.size() > 0) {
          assertTrue("All removed elements were not available on right pane", initialSelection.containsAll(removed));
        }
        return new ArrayList<EObject>(objects);
      }
    };
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.steps.AbstractExecuteToolCmdStep.tool.AbstractExecuteToolCmdTest#initToolArguments()
   */
  @Override
  protected void initToolArguments() {
    DSemanticDecorator containerView = getDiagramContext().getView(this.containerId);
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, containerView.getTarget());
    _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, containerView);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();
    if (autoRefresh)
      DiagramHelper.refreshDiagram(getDiagramContext().getDiagram());

    checkPostconditions();

    for (String identifier : insertedElements) {
      getDiagramContext().hasView(identifier);
    }
    for (String identifier : removedElements) {
      getDiagramContext().hasntView(identifier);
    }
  }

  protected void checkPostconditions() {
    initializeFeatures();

    if (insertedElements.length > 0) {
      if (insertedReferencedElementsFeatures != null) {
        insertedElements = applyReferencedFeatures(insertedReferencedElementsFeatures, insertedElements);
      } else if (insertedReferencingElementsFeatures != null) {
        insertedElements = applyReferencingFeatures(insertedReferencingElementsFeatures, insertedElements);
      }
    }

    if (removedElements.length > 0) {
      if (removedReferencedElementsFeatures != null) {
        removedElements = applyReferencedFeatures(removedReferencedElementsFeatures, removedElements);
      } else if (removedReferencingElementsFeatures != null) {
        removedElements = applyReferencingFeatures(removedReferencingElementsFeatures, removedElements);
      }
    }
  }

  protected String[] applyReferencedFeatures(EReference[] features, String... elements) {
    // apply feature in chain: (obj.eGet(feature1)).eGet(feature2)
    for (EReference feature : features) {
      elements = getReferencedElements(feature, elements);
    }
    return elements;
  }

  protected String[] applyReferencingFeatures(EReference[] features, String... elements) {
    // apply feature in chain: (obj.eGet(feature1)).eGet(feature2)
    for (EReference feature : features) {
      elements = getReferencingElements(feature, elements);
    }
    return elements;
  }

  @SuppressWarnings("unchecked")
  protected String[] getReferencedElements(EReference feature, String... elements) {
    Collection<CapellaElement> objs = new HashSet<CapellaElement>();
    for (String element : elements) {
      EObject obj = getExecutionContext().getSemanticElement(element);
      Object object = obj.eGet(feature);
      if (object instanceof EList) {
        objs.addAll((EList<CapellaElement>) object);
      } else if (object instanceof CapellaElement) {
        objs.add(((CapellaElement) object));
      }
    }
    return filterResults(objs);
  }

  @SuppressWarnings("unchecked")
  protected String[] getReferencingElements(EReference feature, String... elements) {
    Collection<CapellaElement> objs = new HashSet<CapellaElement>();
    for (String element : elements) {
      objs.addAll((Collection<? extends CapellaElement>) EObjectExt
          .getReferencers(getExecutionContext().getSemanticElement(element), feature).stream()
          .collect(Collectors.toList()));
    }
    return filterResults(objs);
  }

  protected String[] filterResults(Collection<CapellaElement> objs) {
    DRepresentationDescriptor cRDescriptor = getDiagramContext().getDiagramDescriptor();
    String[] results = objs.stream().filter(x -> x.eContainer() == cRDescriptor.getTarget()).map(x -> x.getId())
        .toArray(size -> new String[size]);

    assertTrue(results.length > 0);
    return results;
  }

  protected void initializeFeatures() {
  }

  @Override
  public Object getResult() {
    return null;
  }
}
