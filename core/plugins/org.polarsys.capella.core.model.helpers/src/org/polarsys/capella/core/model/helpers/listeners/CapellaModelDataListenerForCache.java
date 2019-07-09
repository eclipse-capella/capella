/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.listeners;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.polarsys.capella.core.data.helpers.cache.CachedFunctionKey;
import org.polarsys.capella.core.data.helpers.cache.ModelCache;

public class CapellaModelDataListenerForCache extends CapellaModelDataListener {

  private final FillCacheJob fillCacheJob = new FillCacheJob();
  private static final int JOB_ADDITIONAL_DELAY = 4000;
  private static final int NUMBER_OF_EXECUTION_BEFORE_YIELD = 5;

  public CapellaModelDataListenerForCache() {
    super();
  }

  @Override
  public void notifyChanged(Notification msg) {
    synchronized (fillCacheJob) {
      int state = fillCacheJob.getState();
      if (state != Job.NONE && !fillCacheJob.cancel())
        fillCacheJob.forceQuit();

      fillCacheJob.addKeys(ModelCache.getCacheKeys());
      ModelCache.clearCache();
      fillCacheJob.schedule(JOB_ADDITIONAL_DELAY);
    }
  }

  public class FillCacheJob extends Job {
    private boolean forceQuit = false;
    int remainingForYield = NUMBER_OF_EXECUTION_BEFORE_YIELD;
    private Set<Object> keys = ConcurrentHashMap.newKeySet();
    private static final String FILL_CACHE_JOB_NAME = " fill Cache Job";

    public FillCacheJob() {
      super(FILL_CACHE_JOB_NAME);
      setPriority(Job.DECORATE);
      setSystem(true);

    }

    public void forceQuit() {
      forceQuit = true;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
      executeFunctionsFromKeys();
      return Status.OK_STATUS;
    }

    private void executeFunctionsFromKeys() {
      for (Object key : new HashSet<>(keys)) {
        if (forceQuit) {
          forceQuit = false;
          return;
        }
        executeFunctionFromKey(key);
      }
    }

    @SuppressWarnings("unchecked")
    private void executeFunctionFromKey(Object key) {
      if (forceQuit) {
        forceQuit = false;
        return;
      }

      CachedFunctionKey cfk = (CachedFunctionKey) key;
      Object[] values = cfk.getValues();

      if (values.length < 2 || values.length > 3)
        return;

      boolean isFunction = values[0] instanceof Function && values.length == 2;
      boolean isBiFunction = values[0] instanceof BiFunction && values.length == 3;

      if (isFunction) {
        getCache((Function<Object, Object>) values[0], values[1]);
      } else if (isBiFunction) {
        getCache((BiFunction<Object, Object, Object>) values[0], values[1], values[2]);
      }
      remainingForYield--;
      if (remainingForYield == 0) {
        if (yieldRule(new NullProgressMonitor()) == null) {
          Thread.yield();
        }
        remainingForYield = NUMBER_OF_EXECUTION_BEFORE_YIELD;
      }

    }

    public void addKeys(Set<Object> keys) {
      this.keys.addAll(keys);
    }
  }

}
