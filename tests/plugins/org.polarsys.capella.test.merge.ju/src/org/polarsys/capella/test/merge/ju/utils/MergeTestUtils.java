/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.merge.ju.utils;

import junit.framework.Assert;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.exception.MergeException;
import org.polarsys.capella.core.refinement.merge.merger.IScenarioMerger;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.merge.ju.testcases.MergeTestingMessages;

/**
 * Utility class for test about merge operations.
 */
public class MergeTestUtils {

  public static class MergeCommand extends AbstractReadWriteCommand {
    
    private Scenario _scenario;
    private IScenarioMerger _merger;
    private Scenario _result = null;
    
    public Scenario getResult(){ return _result;}
    
    public MergeCommand(Scenario scenario, IScenarioMerger merger) {
      super();
      _scenario = scenario;
      _merger = merger;
    }
    
    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
      try {
        _result = _merger.doMerge(_scenario);
      } catch (MergeException exception) {
        Assert.fail(MergeTestingMessages.mergeDoesNotSucceed);
      }  
    }
  }
  
  static public Scenario performMerge(Scenario sc, IScenarioMerger merger) {
    final MergeCommand cmd = new MergeCommand(sc, merger);
    TestHelper.getExecutionManager(sc).execute(cmd);    
    return cmd.getResult();
  }
}
