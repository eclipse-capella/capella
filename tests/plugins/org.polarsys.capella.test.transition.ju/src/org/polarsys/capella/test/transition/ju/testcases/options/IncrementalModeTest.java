/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases.options;

import java.lang.reflect.Field;
import java.util.Collections;

import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.DefaultUserProperties;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory;
import org.polarsys.capella.common.re.ui.handlers.merge.ReMergeUIDifferencesHandler;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeEMFDiffNode;
import org.polarsys.capella.core.transition.common.ui.handlers.merge.MergeUIDifferencesHandler;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Test that the UI of diffmerge has properly activated the incremental mode
 */
public class IncrementalModeTest extends BasicTestCase {

  public void test() {
    // Topdown transition shall be incremental
    MergeEMFDiffNode diffNode = getUINode(new MXUIDifferencesHandler());
    assertTrue(diffNode.getUserPropertyValue(DefaultUserProperties.P_DEFAULT_INCREMENTAL_MODE));

    // RecRpl shall not be incremental
    MergeEMFDiffNode diffNode2 = getUINode(new ReU2DifferencesHandler());
    assertFalse(diffNode2.getUserPropertyValue(DefaultUserProperties.P_DEFAULT_INCREMENTAL_MODE));
  }

  private MergeEMFDiffNode getUINode(UIHandler handler) {

    IContext context = new TransitionContext();
    context.put(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN,
        new SemanticEditingDomainFactory().createEditingDomain());
    context.put(ITransitionConstants.MERGE_COMPARISON, new EComparisonImpl(
        new RootedModelScope(Collections.emptyList()), new RootedModelScope(Collections.emptyList())));

    MergeEMFDiffNode diffNode = handler.createDiffNode(context);
    DiffMergeDialog dialog = handler.createDiffDialog(context, Display.getCurrent(), diffNode);
    dialog.create();
    try {
      Field f = ReflectUtil.findField(dialog.getClass(), "viewer");
      f.setAccessible(true);
      Object e = f.get(dialog);
      ((AbstractComparisonViewer) e).setInput(diffNode);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
    dialog.close();
    return diffNode;
  }

  private interface UIHandler {

    public DiffMergeDialog createDiffDialog(IContext context, Display display, MergeEMFDiffNode diffNode);

    public MergeEMFDiffNode createDiffNode(IContext context);

  }

  private class MXUIDifferencesHandler extends MergeUIDifferencesHandler implements UIHandler {

    @Override
    public DiffMergeDialog createDiffDialog(IContext context, Display display, MergeEMFDiffNode diffNode) {
      return super.createDiffDialog(context, display, diffNode);
    }

    @Override
    public MergeEMFDiffNode createDiffNode(IContext context) {
      return super.createDiffNode(context);
    }

  };

  private class ReU2DifferencesHandler extends ReMergeUIDifferencesHandler implements UIHandler {

    @Override
    public DiffMergeDialog createDiffDialog(IContext context, Display display, MergeEMFDiffNode diffNode) {
      return super.createDiffDialog(context, display, diffNode);
    }

    @Override
    public MergeEMFDiffNode createDiffNode(IContext context) {
      return super.createDiffNode(context);
    }

  };
}
