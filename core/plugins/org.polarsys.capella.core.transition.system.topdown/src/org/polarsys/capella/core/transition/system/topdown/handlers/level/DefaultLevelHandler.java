/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.level;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.selection.LevelSelectionContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultLevelHandler implements ILevelHandler {

  EClass[] clazzes = new EClass[] { OaPackage.Literals.OPERATIONAL_ANALYSIS, CtxPackage.Literals.SYSTEM_ANALYSIS, LaPackage.Literals.LOGICAL_ARCHITECTURE,
                                   PaPackage.Literals.PHYSICAL_ARCHITECTURE, EpbsPackage.Literals.EPBS_ARCHITECTURE };

  String[] levelContext = new String[] { ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N2_ARCHITECTURE,
                                        ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N1_ARCHITECTURE,
                                        ITopDownConstants.SELECTION_CONTEXT__SOURCE_ARCHITECTURE, ITopDownConstants.SELECTION_CONTEXT__TARGET_ARCHITECTURE };

  /**
   * {@inheritDoc}
   */
  @Override
  public void addScope(Level index_p, EObject result_p, IContext context_p) {
    String idContext = "";

    if (Level.PREVIOUS_N2.equals(index_p)) {
      idContext = ITopDownConstants.SOURCE_N2_ARCHITECTURE_ELEMENTS;

    } else if (Level.PREVIOUS_N1.equals(index_p)) {
      idContext = ITopDownConstants.SOURCE_N1_ARCHITECTURE_ELEMENTS;

    } else if (Level.SOURCE.equals(index_p)) {
      idContext = ITopDownConstants.SOURCE_ARCHITECTURE_ELEMENTS;

    } else if (Level.TARGET.equals(index_p)) {
      idContext = ITopDownConstants.TARGET_ARCHITECTURE_ELEMENTS;

    }

    ContextScopeHandlerHelper.getInstance(context_p).add(idContext, result_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  public ISelectionContext getSelectionContext(Level index_p, IContext context_p) {
    String idContext = "";

    if (Level.PREVIOUS_N2.equals(index_p)) {
      idContext = ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N2_ARCHITECTURE;

    } else if (Level.PREVIOUS_N1.equals(index_p)) {
      idContext = ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N1_ARCHITECTURE;

    } else if (Level.SOURCE.equals(index_p)) {
      idContext = ITopDownConstants.SELECTION_CONTEXT__SOURCE_ARCHITECTURE;

    } else if (Level.TARGET.equals(index_p)) {
      idContext = ITopDownConstants.SELECTION_CONTEXT__TARGET_ARCHITECTURE;

    }

    if ((idContext == null) || idContext.isEmpty()) {
      return null;
    }
    return SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, idContext);

  }

  public void initializeSelectionContexts(IContext context_p, CompoundSelectionContextHandler handler_p) {
    handler_p.addSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N2_ARCHITECTURE, new LevelSelectionContext(Level.PREVIOUS_N2));
    handler_p.addSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__PREVIOUS_N1_ARCHITECTURE, new LevelSelectionContext(Level.PREVIOUS_N1));
    handler_p.addSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__SOURCE_ARCHITECTURE, new LevelSelectionContext(Level.SOURCE));
    handler_p.addSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__TARGET_ARCHITECTURE, new LevelSelectionContext(Level.TARGET) {

      /**
       * 
       * {@inheritDoc}
       */
      @Override
      public boolean match(EObject source_p, EObject target_p, IContext context_p) {
        EClass clazz = TransformationHandlerHelper.getInstance(context_p).getTargetType(source_p, context_p);
        if (((clazz != null) && !clazz.isInstance(target_p)) && !(target_p instanceof BlockArchitecture)) {
          return false;
        }

        EClass clazzLevel = LevelHandlerHelper.getInstance(context_p).getLevel(context_p, index);
        BlockArchitecture target = BlockArchitectureExt.getRootBlockArchitecture(target_p);
        if (clazzLevel == null) {
          return false;
        }
        if (target == null) {
          return true;
        }
        return clazzLevel.isInstance(target);
      }

    });
  }

  private int getIndexLevel(IContext context_p, EClass wanted_p) {
    int iWanted = 0;
    for (EClass clazze : clazzes) {
      if (wanted_p.equals(clazze)) {
        break;
      }
      iWanted++;
    }
    return iWanted;
  }

  private int getIndexLevel(IContext context_p, Level level_p) {
    int index = 0;

    if (Level.PREVIOUS_N2.equals(level_p)) {
      index = -2;

    } else if (Level.PREVIOUS_N1.equals(level_p)) {
      index = -1;

    } else if (Level.SOURCE.equals(level_p)) {
      index = 0;

    } else if (Level.TARGET.equals(level_p)) {
      index = 1;

    }
    return index;
  }

  /**
   * @param source_p
   * @param context_p
   * @return
   */
  public BlockArchitecture getSourceArchitecture(IContext context_p) {
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      return BlockArchitectureExt.getRootBlockArchitecture(source);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<Level> getLevels(IContext context_p) {
    EClass source = getSourceLevel(context_p);
    int index = getIndexLevel(context_p, source);

    LinkedList<Level> levels = new LinkedList<ILevelHandler.Level>();
    if (index - 2 >= 0) {
      levels.add(Level.PREVIOUS_N2);
    }
    if (index - 1 >= 0) {
      levels.add(Level.PREVIOUS_N1);
    }
    if (index >= 0) {
      levels.add(Level.SOURCE);
    }
    if (index + 1 >= 0) {
      levels.add(Level.TARGET);
    }
    return levels;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Level> getLevels(IContext context_p, EObject element_p) {
    Collection<Level> levels = new LinkedList<ILevelHandler.Level>();

    return levels;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getLevel(IContext context_p, Level level_p) {
    int index = getIndexLevel(context_p, level_p);

    EClass source = getSourceLevel(context_p);
    index = index + getIndexLevel(context_p, source);

    if ((index >= 0) && (index < clazzes.length)) {
      return clazzes[index];
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public Level getLevel(IContext context_p, EClass class_p) {
    int index = getIndexLevel(context_p, class_p);

    EClass source = getSourceLevel(context_p);
    index = index - getIndexLevel(context_p, source);

    if (index == -2) {
      return Level.PREVIOUS_N2;
    }
    if (index == -1) {
      return Level.PREVIOUS_N1;
    }
    if (index == 0) {
      return Level.SOURCE;
    }
    if (index == 1) {
      return Level.TARGET;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getSourceLevel(IContext context_p) {
    BlockArchitecture sourceArchitecture = getSourceArchitecture(context_p);
    if (sourceArchitecture != null) {
      return sourceArchitecture.eClass();
    }
    return null;
  }

  /**
   * @param source_p
   * @param context_p
   * @return
   */
  public EClass getTargetLevel(EObject source_p, IContext context_p) {
    BlockArchitecture architecture = getSourceArchitecture(context_p);
    return TransformationHandlerHelper.getInstance(context_p).getTargetType(architecture, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectionContext getSelectionContext(EObject element_p, IContext context_p) {
    EObject sourceElement = TraceabilityHandlerHelper.getInstance(context_p).retrieveSourceElements(element_p, context_p).iterator().next();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(sourceElement);
    if (architecture != null) {
      Level level = getLevel(context_p, architecture.eClass());
      return getSelectionContext(level, context_p);
    }
    return getSelectionContext((Level) null, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

}
