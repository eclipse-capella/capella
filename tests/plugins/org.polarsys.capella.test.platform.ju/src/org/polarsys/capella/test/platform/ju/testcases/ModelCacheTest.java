package org.polarsys.capella.test.platform.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.cache.CachedFunction;
import org.polarsys.capella.core.data.helpers.cache.ModelCache;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class ModelCacheTest extends BasicTestCase {

  public static Integer get2(String toto) {
    return (int) (Math.random()*1000000)+100000;
  }
  
  public static Integer get(String toto, String tata) {
    return (int) (Math.random()*1000000)+100000;
  }
  
  public Integer get3(String toto) {
    return (int) (Math.random()*1000000)+100000;
  }

  Function<String, Integer> FunctionExt_getC = ModelCacheTest::get2;
  
  CachedFunction<String, Integer> Functioncccl = ModelCacheTest::get2;
  
  @Override
  public void test() throws Exception {
    assertTrue(true);
    
    String a = "oo";
    String b = "bb";

    Component e = null; 
    
    ModelCache.enable();

    ModelCacheTest  aaa = new ModelCacheTest();
    Integer a3c = ModelCache.getCache(aaa::get3, a);
    Integer a3c2 = ModelCache.getCache(aaa::get3, a);
    
    Integer a3 = ModelCache.getCache(ModelCacheTest::get2, a);
    Integer a2 = ModelCache.getCache(ModelCacheTest::get2, a);

    Integer a3CC = ModelCache.getCache(FunctionExt_getC, a);
    Integer a2CC = ModelCache.getCache(FunctionExt_getC, a);

    ModelCache.getCache(ComponentExt::getAllSubUsedComponents, e);
    ModelCache.getCache(ComponentExt::getAllSubUsedComponents, e);
    
    ComponentExt.GetAllSubUsedComponents.get(e);
    ComponentExt.GetAllSubUsedComponents.get(e);
    
    ComponentExt.GetAllSubUsedComponents.clearCache();
    
    Collection<Component> c = null;
    c.stream().map(ComponentExt.GetAllSubUsedComponents).flatMap(null);
    
    
    ModelCache.disable();
  }

}
