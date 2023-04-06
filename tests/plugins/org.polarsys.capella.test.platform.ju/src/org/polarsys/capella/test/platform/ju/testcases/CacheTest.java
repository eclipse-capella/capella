package org.polarsys.capella.test.platform.ju.testcases;


import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.polarsys.capella.common.helpers.cache.CachedBiFunction;
import org.polarsys.capella.common.helpers.cache.CachedFunction;
import org.polarsys.capella.common.helpers.cache.ModelCache;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CacheTest extends BasicTestCase {
  public static Integer get2(String toto) {
    return (int) (Math.random() * 1000000) + 100000;
  }

  public static Integer get(String toto, String tata) {
    return (int) (Math.random() * 1000000) + 100000;
  }

  Function<String, Integer> function_get2 = CacheTest::get2;
  CachedFunction<String, Integer> cachedFunction_get2 = CacheTest::get2;

  CachedBiFunction<String, String, Integer> cachedBiFunction_get = CacheTest::get;


  @Override
  public void test() throws Exception {
    assertTrue(true);

    String a = "oo";
    String b = "bb";

    String[] array = { a, b };
    ModelCache.enable();

    Integer get2_onfunction_1 = ModelCache.getCache(function_get2, a);
    Integer get2_onfunction_2 = ModelCache.getCache(function_get2, a);

    assertTrue(get2_onfunction_1 == get2_onfunction_2);

    Integer get2_oncached_1 = cachedFunction_get2.get(b);
    Integer get2_oncached_2 = cachedFunction_get2.get(b);

    assertTrue(get2_oncached_1 == get2_oncached_2);
    cachedFunction_get2.clearCache();

    get2_oncached_1 = cachedFunction_get2.withoutCache(b);
    get2_oncached_2 = cachedFunction_get2.withoutCache(b);

    assertFalse(get2_oncached_1 == get2_oncached_2);

    List<Integer> strs2 = Arrays.stream(array).map(cachedFunction_get2).collect(Collectors.toList());
    List<Integer> strs3 = Arrays.stream(array).map(cachedFunction_get2).collect(Collectors.toList());

    assertTrue(strs2.equals(strs3));

    Integer get_oncached_1 = cachedBiFunction_get.get(a, b);
    Integer get_oncached_2 = cachedBiFunction_get.get(a, b);

    assertTrue(get_oncached_1 == get_oncached_2);
    cachedBiFunction_get.clearCache();

    get_oncached_1 = cachedBiFunction_get.withoutCache(a, b);
    get_oncached_2 = cachedBiFunction_get.withoutCache(a, b);

    assertFalse(get2_oncached_1 == get2_oncached_2);

    ModelCache.disable();
  }
}
