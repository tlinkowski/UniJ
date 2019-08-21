/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.tlinkowski.unij.api;

import java.util.*;

import kotlin.annotations.jvm.ReadOnly;
import lombok.experimental.UtilityClass;

/**
 * Provides static factory methods corresponding to those present in {@link Map} interface.
 *
 * @author Tomasz Linkowski
 */
@UtilityClass
public final class UniMaps {

  //region COPY OF

  /**
   * Equivalent of {@link Map#copyOf(Map)}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> copyOf(@ReadOnly Map<? extends K, ? extends V> map) {
    return UniJ.mapFactory().copyOf(map);
  }
  //endregion

  //region ENTRIES

  /**
   * Equivalent of {@link Map#ofEntries(Map.Entry[])}.
   */
  @SafeVarargs
  @ReadOnly
  public static <K, V> Map<K, V> ofEntries(@ReadOnly Map.Entry<? extends K, ? extends V>... entries) {
    return UniJ.mapFactory().ofEntries(entries);
  }

  /**
   * Equivalent of {@link Map#entry(Object, Object)}.
   */
  @ReadOnly
  public static <K, V> Map.Entry<K, V> entry(K k, V v) {
    return UniJ.mapFactory().entry(k, v);
  }
  //endregion

  //region OF

  /**
   * Equivalent of {@link Map#of()}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> of() {
    return UniJ.mapFactory().of();
  }

  /**
   * Equivalent of {@link Map#of(Object, Object)}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1) {
    return UniJ.mapFactory().of(k1, v1);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    return UniJ.mapFactory().of(k1, v1, k2, v2);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6,
          K k7, V v7) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7,
          K k8, V v8) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7,
          K k8, V v8, K k9, V v9) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9);
  }

  /**
   * Equivalent of {@link Map#of(Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object,
   * Object, Object, Object, Object, Object, Object, Object, Object, Object)}.
   */
  @SuppressWarnings("PMD.ExcessiveParameterList")
  @ReadOnly
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7,
          K k8, V v8, K k9, V v9, K k10, V v10) {
    return UniJ.mapFactory().of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10);
  }
  //endregion
}
